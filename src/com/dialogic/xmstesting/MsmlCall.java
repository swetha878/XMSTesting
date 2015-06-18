/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSCallState;
import com.dialogic.clientLibrary.XMSEvent;
import com.dialogic.clientLibrary.XMSEventType;
import com.dialogic.clientLibrary.XMSReturnCode;
import java.io.FileInputStream;
import java.io.StringReader;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import org.xml.sax.InputSource;

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
    Call conf;
    static int counter = 0;

    public MsmlCall(Connector connector) {
        try {
            //this.connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            this.connector = connector;
            m_state = XMSCallState.NULL;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public XMSReturnCode Waitcall() {
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

    public XMSReturnCode waitCallAsyn() {
        caller = new Call(this.connector);
        caller.addObserver(this);
        callMode = CallMode.INBOUND;
        m_state = XMSCallState.WAITCALL;
        caller.addToWaitList();
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode Makecall(String dest) {
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
                if (caller != null && caller.getRemoteSdp() != null) {
                    msmlSip.setLocalSdp(caller.getRemoteSdp());
                }
                if (!MakecallOptions.m_ACKOn200Enabled) {
                    msmlSip.setACKOn200(Boolean.FALSE);
                }
                if (!MakecallOptions.m_OKOnInfoEnabled) {
                    msmlSip.setOKOnInfo(Boolean.FALSE);
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
    public XMSReturnCode Acceptcall() {
        try {
            if (caller != null) {
                caller.createRingingResponse(caller.getInviteRequest());
                if (!WaitcallOptions.m_autoConnectEnabled) {
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode Answercall() {
        try {
            if (caller != null) {
                caller.createInviteOk(caller.getInviteRequest());
                if (!WaitcallOptions.m_autoConnectEnabled) {
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode Dropcall() {
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
                    if (counter == 1) {
                        if (conf != null) {
                            conf.createBye();
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode Play(String filename) {
        try {
            if (msmlSip != null && filename != null) {
                msmlSip.sendInfo(buildPlayMsml(filename));
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
                    }

                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode Record(String filename) {
        try {
            if (msmlSip != null && filename != null) {
                msmlSip.sendInfo(buildRecordMsml(filename, 10));
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
                        m_state = XMSCallState.RECORD_END;
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    public XMSReturnCode createConf(String name) {
        try {
            if (msmlSip == null) {
                conf = new Call(connector);
                conf.addObserver(this);
                setXMSInfo(conf);
                conf.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                callMode = CallMode.CONF;
                conf.setLocalSdp(getNullSdp());
                conf.createInviteRequest(conf.getToUser(), conf.getToAddress());
                synchronized (m_synclock) {
                    while (!isBlocked) {
                        m_synclock.wait();
                    }
                    isBlocked = false;
                    //conf.sendInfo(buildConfMsml(name));
                    conf.sendInfo(buildConfVideoMsml(name));
                    m_state = XMSCallState.CONF;
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                        counter++;
                    }
//                    if (mediaStatusCode == 200) {
//                        return XMSReturnCode.SUCCESS;
//                    } else {
//                        return XMSReturnCode.FAILURE;
//                    }
                }
            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    public XMSReturnCode add(String name) {
        try {
            if (msmlSip != null && name != null) {
                //msmlSip.sendInfo(buildJoinConfMsml(name));
                msmlSip.sendInfo(buildJoinConfVideoMsml(name));
                synchronized (m_synclock) {
                    while (!isBlocked) {
                        m_synclock.wait();
                    }
                    isBlocked = false;
                    counter++;
                }
//                if (mediaStatusCode == 200) {
//                    return XMSReturnCode.SUCCESS;
//                } else {
//                    return XMSReturnCode.FAILURE;
//                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode PlayCollect(String filename) {
        try {
            if (msmlSip != null && filename != null) {
                msmlSip.sendInfo(buildPlayCollectMsml(filename));
                synchronized (m_synclock) {
                    while (!isBlocked) {
                        m_synclock.wait();
                    }
                    isBlocked = false;
                    // waiting for digits
                    synchronized (m_synclock) {
                        while (!isBlocked) {
                            m_synclock.wait();
                        }
                        isBlocked = false;
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode CollectDigits() {
        try {

            if (msmlSip != null) {
                //msmlSip.
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
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
                Makecall("");
            } else if (this.callMode == CallMode.OUTBOUND) {
            }
        } else if (e.getType().equals(EventType.RINGING)) {
            if (this.callMode == CallMode.INBOUND) {
                if (WaitcallOptions.m_autoConnectEnabled) {
                    Acceptcall();
                } else {
                    synchronized (m_synclock) {
                        isBlocked = true;
                        m_synclock.notifyAll();
                    }
                }

            } else if (this.callMode == CallMode.OUTBOUND) {
            }
        } else if (e.getType().equals(EventType.CONNECTING)) {
            if (this.callMode == CallMode.INBOUND) {
                if (WaitcallOptions.m_autoConnectEnabled) {
                    if (caller.getLocalSdp() == null) {
                        caller.setLocalSdp(msmlSip.getRemoteSdp());
                    }
                    Answercall();
                } else {
                    synchronized (m_synclock) {
                        isBlocked = true;
                        m_synclock.notifyAll();
                    }
                }
            } else if (this.callMode == CallMode.OUTBOUND) {
                if (e.getCall() == caller) {
                    msmlSip.createAckRequest(e.getRes());
                    caller.createAckRequest(e.getRes());
                } else if (caller == null) {
                    if (callerToAdr != msmlSip.getToAddress()) {
                        Makecall(callerToUserId + "@" + callerToAdr);
                    }
                }
            } else {
                synchronized (m_synclock) {
                    isBlocked = true;
                    m_synclock.notifyAll();
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
            String reponseMessage = new String(e.getRes().getRawContent());
            if (m_state != XMSCallState.CUSTOM) {
                XMSEvent xmsEvent = new XMSEvent();
                xmsEvent.CreateEvent(XMSEventType.CALL_CUSTOM, this, "", "", reponseMessage);
                setLastEvent(xmsEvent);
            } else if (m_state != XMSCallState.DISCONNECTED) {
                Pattern pattern = Pattern.compile("response=\\\"(.*?)\\\"");
                Matcher m = pattern.matcher(reponseMessage);
                if (m.find()) {
                    String event = m.group(1);

                    if (Long.parseLong(event) == 200) {
                        System.out.println("Response 200 received");
                        mediaStatusCode = Integer.parseInt(event);
                        synchronized (m_synclock) {
                            isBlocked = true;
                            m_synclock.notifyAll();
                        }
                    } else if (Long.parseLong(event) == 400) {
                        System.out.println("Response 400 received");
                        mediaStatusCode = Integer.parseInt(event);
                    }
                }
            }
        } else if (e.getType().equals(EventType.INFOREQUEST)) {
            if (!MakecallOptions.m_OKOnInfoEnabled) {
                msmlSip.createInfoResponse(e.getReq());
            }
            String info = new String(e.getReq().getRawContent());
            if (m_state != XMSCallState.CUSTOM) {
                XMSEvent xmsEvent = new XMSEvent();
                xmsEvent.CreateEvent(XMSEventType.CALL_CUSTOM, this, "", "", info);
                setLastEvent(xmsEvent);
            } else {
                String name = null;
                Pattern eventPattern = Pattern.compile("name=\\\".*?\\\"");
                Matcher eventMatcher = eventPattern.matcher(info);
                if (eventMatcher.find()) {
                    String eventName = eventMatcher.group(0);
                    Pattern namePattern = Pattern.compile("\\\"(.*?)\\\"");
                    Matcher nameMatcher = namePattern.matcher(eventName);
                    if (nameMatcher.find()) {
                        name = nameMatcher.group(1);
                    }
                }
                if (name != null && name.equalsIgnoreCase("moml.exit")) {
                    Pattern dialogPattern = Pattern.compile("dialog:(.*?)\\\"");
                    Matcher dialogMatcher = dialogPattern.matcher(info);
                    String dialogType = null;
                    if (dialogMatcher.find()) {
                        dialogType = dialogMatcher.group(1);
                    }
                    if (dialogType != null) {
                        if (dialogType.equals("Play")) {
                            String reason = "";
                            String amt = "";
                            Pattern event = Pattern.compile("<name>(.+?)</name><value>(.+?)</value>");
                            Matcher matcher = event.matcher(info);
                            String nameType;
                            String valueType;
                            while (matcher.find()) {
                                nameType = matcher.group(1);
                                valueType = matcher.group(2);
                                System.out.println("Testing 1->" + matcher.group(1));
                                System.out.println("Testing 2->" + matcher.group(2));
                                if (nameType.equalsIgnoreCase("play.amt")) {
                                    amt = valueType;
                                } else if (nameType.equalsIgnoreCase("play.end")) {
                                    reason = valueType;
                                }
                            }
                            XMSEvent xmsEvent = new XMSEvent();
                            xmsEvent.CreateEvent(XMSEventType.CALL_PLAY_END, this, amt, reason, info);
                            xmsEvent.setReason(reason);
                            xmsEvent.setInternalData(info);
                            setLastEvent(xmsEvent);
                        } else if (dialogType.equals("Record")) {
                            String reason = "";
                            String len = "";
                            Pattern event = Pattern.compile("<name>(.+?)</name><value>(.+?)</value>");
                            Matcher matcher = event.matcher(info);
                            String nameType;
                            String valueType;
                            while (matcher.find()) {
                                nameType = matcher.group(1);
                                valueType = matcher.group(2);
                                System.out.println("Testing 1->" + matcher.group(1));
                                System.out.println("Testing 2->" + matcher.group(2));
                                if (nameType.equalsIgnoreCase("record.len")) {
                                    len = valueType;
                                } else if (nameType.equalsIgnoreCase("record.end")) {
                                    reason = valueType;
                                }
                            }
                            XMSEvent xmsEvent = new XMSEvent();
                            xmsEvent.CreateEvent(XMSEventType.CALL_RECORD_END, this, len, reason, info);
                            xmsEvent.setReason(reason);
                            xmsEvent.setInternalData(info);
                            setLastEvent(xmsEvent);
                        }
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
                } else if (name != null && name.equalsIgnoreCase("detect")) {

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
                        MakecallOptions.EnableACKOn200(false);
                        MakecallOptions.EnableOKOnInfo(false);
                        if (counter > 1) {
                            counter--;
                        }
                        m_synclock.notifyAll();
                    }
                } else if (m_state != XMSCallState.DISCONNECTED) {
                    if (e.getReq() != null) {
                        m_state = XMSCallState.DISCONNECTED;
                        msmlSip.doByeOk(e.getReq());
                        caller.createBye();
                    }
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
                        if (e.getReq() != null) {
                            m_state = XMSCallState.DISCONNECTED;
                            if (counter > 1) {
                                counter--;
                            }
                            caller.doByeOk(e.getReq());
                            msmlSip.createBye();
                            if (counter == 1) {
                                conf.createBye();
                            }
                        }
                    }
                }
            }
        } else if (e.getType().equals(EventType.CANCEL)) {
            if (e.getCall() == caller) {
                msmlSip.createCancelRequest();
                caller.createCancelResponse(e.getReq());
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
                + "		<playexit>\n"
                + "		<exit namelist = \"play.end play.amt\"/>\n"
                + "		</playexit>\n"
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
                //+ "			<send target=\"group\" event=\"terminate\"/>\n"
                + "		<exit namelist = \"record.end record.len\"/>\n"
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

    private static String buildConfMsml(String name) {
        String msml = "<msml version=\"1.1\">\n"
                + "<createconference name=\"" + name + "\" deletewhen=\"nocontrol\" mark=\"1\" term=\"true\">\n"
                + "<audiomix id=\"mix491230000001\"/>\n"
                + "</createconference>\n"
                + "</msml>";
        return msml;
    }

    private static String buildConfVideoMsml(String name) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<msml version=\"1.1\">\n"
                + "<createconference name=\"" + name + "\" deletewhen=\"nomedia\">\n"
                + "<videolayout>\n"
                + "<root size=\"VGA\" />\n"
                + "<region id=\"1\" left=\"0\" top=\"0\" relativesize=\"1/2\" />\n"
                + "<region id=\"2\" left=\"50%\" top=\"0\" relativesize=\"1/2\" />\n"
                + "<region id=\"3\" left=\"0\" top=\"50%\" relativesize=\"1/2\" />\n"
                + "<region id=\"4\" left=\"50%\" top=\"50%\" relativesize=\"1/2\" />\n"
                + "</videolayout>\n"
                + "</createconference>\n"
                + "</msml>";
        return msml;
    }

    private static String buildJoinConfMsml(String name) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<msml version=\"1.1\">\n"
                + "<join id1=\"conf:" + name + "\" id2=\"conn:1234\" mark=\"2\">\n"
                + "<stream media=\"audio\"/>\n"
                + "</join>\n"
                + "</msml>";
        return msml;
    }

    private static String buildJoinConfVideoMsml(String name) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<msml version=\"1.1\">\n"
                + "<join id1=\"conn:1234\" id2=\"conf:" + name + "\">\n"
                + "<stream media=\"audio\" />\n"
                + "<stream media=\"video\" dir=\"from-id1\" display=\"1\"/>\n"
                + "<stream media=\"video\" dir=\"to-id1\" />\n"
                + "</join>\n"
                + "</msml>";
        return msml;
    }

    private static String buildDestroyConfMsml(String name) {
        String msml = "<msml version=\"1.1\">\n"
                + "<destroyconference id=\"conf:" + name + "\" mark=\"1\" />\n"
                + "</msml>";
        return msml;
    }

    private static String buildPlayCollectMsml(String filename) {
        String msml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                + "<msml version=\"1.1\">\n"
                + "<dialogstart target=\"conn:1234\" type=\"application/moml+xml\">\n"
                + "<group topology=\"parallel\">\n"
                + "<play barge=\"true\" cleardb=\"true\">\n"
                + "	<audio uri=\"" + filename + "\"/>\n"
                + " <playexit>\n"
                + "	 <send target=\"collect\" event=\"starttimer\"/>\n"
                + " </playexit>\n"
                + "</play>\n"
                + "<collect fdt=\"3s\" idt=\"10s\">\n"
                + "	<pattern digits=\"x\">\n"
                + "		<send target=\"source\" event=\"match\" namelist=\"dtmf.end dtmf.digits\"/>\n"
                + "	</pattern>\n"
                + "	<detect>\n"
                + "		<send target=\"source\" event=\"detect\" namelist=\"dtmf.end dtmf.digits\"/>\n"
                + "	</detect>\n"
                + "	<noinput>\n"
                + "		<send target=\"source\" event=\"noinput\" namelist=\"dtmf.end dtmf.digits\"/>\n"
                + "	</noinput>\n"
                + "	<nomatch>\n"
                + "		<send target=\"source\" event=\"nomatch\" namelist=\"dtmf.end dtmf.digits\"/>	\n"
                + "	</nomatch>\n"
                + "</collect>\n"
                + "</group>\n"
                + "</dialogstart>\n"
                + "</msml>";
        return msml;
    }

    private static String buildCollectDigit() {
        String msml = "";
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
            Logger.getLogger(Call.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        connectionAddress = aConnectionAddress;
    }

    private String getNullSdp() {
        String sdp = "v=0 \n"
                + "o=test 53655765 2353687637 IN IP4 0.0.0.0 \n"
                + "s=test Server \n"
                + "c=IN IP4 0.0.0.0 \n"
                + "t=0 0";
        return sdp;
    }

    public void sendCustomScript(String msml) {
        if (msmlSip != null && msml != null) {
            m_state = XMSCallState.CUSTOM;
            msmlSip.sendInfo(msml);
        }
    }
}
