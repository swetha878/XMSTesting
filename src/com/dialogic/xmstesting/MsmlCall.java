/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSCallState;
import com.dialogic.clientLibrary.XMSReturnCode;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sip.address.Address;
import javax.sip.header.FromHeader;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 * @author ssatyana
 */
public class MsmlCall extends XMSCall implements Observer {

    static final Logger logger = Logger.getLogger(MsmlCall.class.getName());

    Call caller;
    Call msmlSip;
    Connector connector;
    private String fromAddr;
    private static CallMode callMode = CallMode.OUTBOUND;
    String callerToUserId = null;
    String callerToAdr = null;
    private final Object m_synclock = new Object();
    static boolean isBlocked = false;
    static boolean isACKOn200;
    static boolean isDropCall = false;
    static int mediaStatusCode;
    private static String connectionAddress;

    public MsmlCall() {
        try {
            this.connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            m_state = XMSCallState.NULL;
        } catch (UnknownHostException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public XMSReturnCode waitCall() {
        try {
            caller = new Call(this.connector);
            caller.addObserver(this);
            callMode = CallMode.INBOUND;
            m_state = XMSCallState.WAITCALL;
            caller.addToWaitList();
            synchronized (m_synclock) {
                while (!isBlocked) {
                    m_synclock.wait();
                }
                isBlocked = false;
            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode makeCall(String dest) {
        try {
            m_state = XMSCallState.MAKECALL;
            if (!dest.isEmpty()) {
                String[] params = dest.split("@");
                if (params.length != 0) {
                    callerToUserId = params[0];
                    callerToAdr = params[1];
                }
            }
            if (msmlSip == null) {
                msmlSip = new Call(this.connector);
                msmlSip.addObserver(this);

                // get XMS ip address and user from config file
                setXMSInfo(msmlSip);
                msmlSip.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                if (caller != null) {
                    msmlSip.setLocalSdp(caller.getRemoteSdp());
                } else {
                    msmlSip.setACKOn200(Boolean.FALSE);
                }
                msmlSip.createInviteRequest(msmlSip.getToUser(), msmlSip.getToAddress());

                if (caller == null) {
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                    }
                }
            } else if (caller == null && msmlSip != null) {
                Thread.sleep(500);
                caller = new Call(this.connector);
                caller.addObserver(this);
                caller.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                caller.setLocalSdp(msmlSip.getRemoteSdp());
                caller.createInviteRequest(callerToUserId, callerToAdr);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode acceptCall() {
        if (caller != null) {
            caller.createRingingResponse(caller.getInviteRequest());
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode answerCall() {
        if (caller != null) {
            caller.createInviteOk(caller.getInviteRequest());
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode dropCall() {
        try {
            if (caller != null && msmlSip != null) {
                isDropCall = true;
                caller.createBye();
                msmlSip.createBye();
                synchronized (m_synclock) {
                    while (!isBlocked) {
                        m_synclock.wait();
                    }
                    isBlocked = false;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode play(String filename) {
        try {
            if (msmlSip != null && filename != null) {
                msmlSip.createInfoRequest(buildPlayMsml(filename));
                m_state = XMSCallState.PLAY;
                synchronized (m_synclock) {
                    while (!isBlocked) {
                        m_synclock.wait();
                    }
                    isBlocked = false;
                }
                if (mediaStatusCode == 200) {
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                        m_state = XMSCallState.PLAY_END;
                        return XMSReturnCode.SUCCESS;
                    }
                } else {
                    return XMSReturnCode.FAILURE;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.FAILURE;
    }

    @Override
    public XMSReturnCode record(String filename) {
        try {
            if (msmlSip != null && filename != null) {
                msmlSip.createInfoRequest(buildRecordMsml(filename, 10));
                m_state = XMSCallState.RECORD;
                synchronized (m_synclock) {
                    while (!isBlocked) {
                        m_synclock.wait();
                    }
                    isBlocked = false;
                }
                if (mediaStatusCode == 200) {
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                        return XMSReturnCode.SUCCESS;
                    }
                } else {
                    return XMSReturnCode.FAILURE;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.FAILURE;
    }

    public String getFromAddress() {
        return this.fromAddr;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddr = fromAddress;
    }

    @Override
    public void update(Observable o, Object o1) {
        Event e = (Event) o1;
        if (e.getType().equals(EventType.INCOMING)) {
            if (this.callMode == CallMode.INBOUND) {

                FromHeader fromHeader = (FromHeader) e.getReq().getHeader("From");
                Address reqToAddress = fromHeader.getAddress();
                String incomingAdr = reqToAddress.toString();
                Pattern pattern = Pattern.compile("sip:(.*?)>");
                Matcher m = pattern.matcher(incomingAdr);
                if (m.find()) {
                    String event = m.group(1);
                    setConnectionAddress(event);
                }
                makeCall("");
            } else if (this.callMode == CallMode.OUTBOUND) {
            }
        } else if (e.getType().equals(EventType.RINGING)) {
            if (this.callMode == CallMode.INBOUND) {
                acceptCall();
            } else if (this.callMode == CallMode.OUTBOUND) {
            }
        } else if (e.getType().equals(EventType.CONNECTING)) {
            if (this.callMode == CallMode.INBOUND) {
                answerCall();
            } else if (this.callMode == CallMode.OUTBOUND) {
                if (e.getCall() == caller) {
                    msmlSip.createAckRequest(e.getRes());
                    caller.createAckRequest(e.getRes());
                } else if (caller == null) {
                    makeCall(callerToUserId + "@" + callerToAdr);
                }
            }
        } else if (e.getType().equals(EventType.CONNECTED)) {
            // recieved ack from inbound call and its connected
            if (this.callMode == CallMode.INBOUND) {
                if (e.getCall() == caller) {
                    synchronized (m_synclock) {
                        isBlocked = true;
                        m_synclock.notifyAll();
                    }
                }
            } else if (this.callMode == CallMode.OUTBOUND) {
                if (e.getCall() == caller) {
                    synchronized (m_synclock) {
                        isBlocked = true;
                        m_synclock.notifyAll();
                    }
                }
            }
        } else if (e.getType().equals(EventType.INFORESPONSE)) {
            if (m_state != XMSCallState.DISCONNECTED) {
                String reponseMessage = new String(e.getRes().getRawContent());

                Pattern pattern = Pattern.compile("response=\\\"(.*?)\\\"");
                Matcher m = pattern.matcher(reponseMessage);
                if (m.find()) {
                    String event = m.group(1);

                    if (Long.parseLong(event) == 200) {
                        System.out.println("Response 200 received");
                        mediaStatusCode = Integer.parseInt(event);
                    } else if (Long.parseLong(event) == 400) {
                        System.out.println("Response 400 received");
                        mediaStatusCode = Integer.parseInt(event);
                    }
                }
                synchronized (m_synclock) {
                    isBlocked = true;
                    m_synclock.notifyAll();
                }
            }
        } else if (e.getType().equals(EventType.INFOREQUEST)) {
            msmlSip.createInfoResponse(e.getReq());

            String info = new String(e.getReq().getRawContent());
            String name = null;
            Pattern eventPattern = Pattern.compile("name=\\\".*?\\\"");
            Matcher eventMatcher = eventPattern.matcher(info);
            if (eventMatcher.find()) {
                String event = eventMatcher.group(0);
                Pattern p = Pattern.compile("\\\"(.*?)\\\"");
                Matcher matcher = p.matcher(event);
                if (matcher.find()) {
                    name = matcher.group(1);
                }
            }
            if (name != null && name.equalsIgnoreCase("msml.dialog.exit")) {
                if (m_state != XMSCallState.DISCONNECTED) {
                    synchronized (m_synclock) {
                        isBlocked = true;

                        Pattern dialogPattern = Pattern.compile("dialog:(.*?)\\\"");
                        Matcher dialogMatcher = dialogPattern.matcher(info);
                        String dialogType = null;
                        if (dialogMatcher.find()) {
                            dialogType = dialogMatcher.group(1);
                        }
                        if (dialogType != null) {
                            if (dialogType.equals("Play")) {
                                m_state = XMSCallState.PLAY_END;
                            } else if (dialogType.equals("Record")) {
                                m_state = XMSCallState.RECORD_END;
                            }
                        }
                        m_synclock.notifyAll();
                    }
                } else {
                    msmlSip.createBye();
                }
            }
        } else if (e.getType().equals(EventType.DISCONNECTED)) {
            if (e.getCall() == msmlSip) {
                if (isDropCall) {
                    synchronized (m_synclock) {
                        isBlocked = true;
                        isDropCall = false;
                        this.callMode = CallMode.OUTBOUND;
                        msmlSip = null;
                        caller = null;
                        m_synclock.notifyAll();
                    }
                } else if (m_state != XMSCallState.DISCONNECTED) {
                    m_state = XMSCallState.DISCONNECTED;
                    msmlSip.doByeOk(e.getReq());
                    caller.createBye();
                }
            } else if (e.getCall() == caller) {
                if (m_state != XMSCallState.DISCONNECTED) {
                    if (m_state == XMSCallState.PLAY) {
                        //media active, send dialog exit before bye
                        msmlSip.createDialogEndRequest(buildDialogExit("Play"));
                        m_state = XMSCallState.DISCONNECTED;
                        caller.doByeOk(e.getReq());
                    } else if (m_state == XMSCallState.RECORD) {
                        msmlSip.createDialogEndRequest(buildDialogExit("Record"));
                        m_state = XMSCallState.DISCONNECTED;
                        caller.doByeOk(e.getReq());
                    } else {
                        m_state = XMSCallState.DISCONNECTED;
                        caller.doByeOk(e.getReq());
                        msmlSip.createBye();
                    }
                }
            }
        }
    }

    // change to xml beans/jaxb objects
    private static String buildPlayMsml(String fileName) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<msml version=\"1.1\">\n"
                + "<dialogstart target=\"conn:1234\" type=\"application/moml+xml\" name=\"Play\">\n"
                + "	<play >\n"
                + "		<audio uri=\"" + fileName + "\" />\n"
                + "	</play>\n"
                + "</dialogstart>\n"
                + "</msml>";
        return msml;

    }

    private static String buildRecordMsml(String filename, int time) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<msml version=\"1.1\">\n"
                + "<dialogstart target=\"conn:1234\" type=\"application/moml+xml\" name=\"Record\">\n"
                + "	<record beep=\"true\" dest=\"" + filename + "\" format=\"audio/wav\" maxtime=\"" + time + "s\">\n"
                + "		<recordexit>\n"
                + "			<send target=\"group\" event=\"terminate\"/>\n"
                + "		</recordexit>\n"
                + "	</record>\n"
                + "</dialogstart>\n"
                + "</msml>";
        return msml;
    }

    private static String buildDialogExit(String type) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<msml version=\"1.1\">\n"
                + " <dialogend id=\"conn:1234/dialog:" + type + "\"/>\n"
                + "</msml>";
        return msml;
    }

    private void setXMSInfo(Call c) {
        try {
            FileInputStream xmlFile = new FileInputStream("ConnectorConfig.xml");
            Document doc = new Builder().build(xmlFile);
            Element root = doc.getRootElement();
            Elements entries = root.getChildElements();
            for (int x = 0; x < entries.size(); x++) {
                Element element = entries.get(x);
                if (element.getLocalName().equals("user")) {
                    c.setToUser(element.getValue());
                }
                if (element.getLocalName().equals("xmsAddress")) {
                    c.setToAddress(element.getValue());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Call.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAckOn200(Boolean value) {
        this.isACKOn200 = value;
    }

    /**
     * @return the connectionAddress
     */
    public String getConnectionAddress() {
        return connectionAddress;
    }

    /**
     * @param aConnectionAddress the connectionAddress to set
     */
    public void setConnectionAddress(String aConnectionAddress) {
        System.out.println("CONNECTION ADDRESS ->" + aConnectionAddress);
        connectionAddress = aConnectionAddress;
    }
}
