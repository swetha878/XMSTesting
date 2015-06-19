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
    static XMSEvent xmsEvent;

    public MsmlCall(Connector connector) {
        try {
            //this.connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            this.connector = connector;
            m_connector = connector;
            this.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
            //m_state = XMSCallState.NULL;
            setState(XMSCallState.NULL);
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
            //m_state = XMSCallState.WAITCALL;
            setState(XMSCallState.WAITCALL);
            caller.addToWaitList();
//            synchronized (m_synclock) {
//                while (!isBlocked) {
//                    m_synclock.wait();
//                }
//                isBlocked = false;
//            }
            if (WaitcallOptions.isAutoConnect()) {
                BlockIfNeeded(XMSEventType.CALL_CONNECTED);
            } else {
                BlockIfNeeded(XMSEventType.CALL_OFFERED);
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
            //m_state = XMSCallState.MAKECALL;
            setState(XMSCallState.MAKECALL);
            if (!dest.isEmpty()) {
                String[] params = dest.split("@");
                if (params.length != 0) {
                    callerToUserId = params[0];
                    callerToAdr = params[1];
                }
            }

            if (isDropCall) {
                // reset                 
                this.callMode = CallMode.OUTBOUND;
                msmlSip = null;
                caller = null;
                MakecallOptions.EnableACKOn200(false);
                MakecallOptions.EnableOKOnInfo(false);
                isDropCall = false;
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
//                    synchronized (m_synclock) {
//                        while (!isBlocked) {
//                            m_synclock.wait();
//                        }
//                        isBlocked = false;
//                    }
                    BlockIfNeeded(XMSEventType.CALL_CONNECTED);
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
//                    synchronized (m_synclock) {
//                        while (!isBlocked) {
//                            m_synclock.wait();
//                        }
//                        isBlocked = false;
//                    }
                    BlockIfNeeded(XMSEventType.CALL_UPDATED);
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
//                    synchronized (m_synclock) {
//                        while (!isBlocked) {
//                            m_synclock.wait();
//                        }
//                        isBlocked = false;
//                    }
                    BlockIfNeeded(XMSEventType.CALL_CONNECTED);
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
//                synchronized (m_synclock) {
//                    while (!isBlocked) {
//                        m_synclock.wait();
//                    }
//                    isBlocked = false;
//                }
                BlockIfNeeded(XMSEventType.CALL_DISCONNECTED);
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
                //m_state = XMSCallState.PLAY;
                setState(XMSCallState.PLAY);
//                synchronized (m_synclock) {
//                    while (!isBlocked) {
//                        m_synclock.wait();
//                    }
//                    isBlocked = false;
//                }
                BlockIfNeeded(XMSEventType.CALL_INFO);
                if (mediaStatusCode == 200) {
//                    synchronized (m_synclock) {
//                        while (!isBlocked) {
//                            m_synclock.wait();
//                        }
//                        isBlocked = false;
//                        //m_state = XMSCallState.PLAY_END;
//                        setState(XMSCallState.PLAY_END);
//                    }
                    BlockIfNeeded(XMSEventType.CALL_PLAY_END);
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
                //m_state = XMSCallState.RECORD;
                setState(XMSCallState.RECORD);
//                synchronized (m_synclock) {
//                    while (!isBlocked) {
//                        m_synclock.wait();
//                    }
//                    isBlocked = false;
//                }
                BlockIfNeeded(XMSEventType.CALL_INFO);
                if (mediaStatusCode == 200) {
//                    synchronized (m_synclock) {
//                        while (!isBlocked) {
//                            m_synclock.wait();
//                        }
//                        isBlocked = false;
//                        //m_state = XMSCallState.RECORD_END;
//                        setState(XMSCallState.RECORD_END);
//                    }
                    BlockIfNeeded(XMSEventType.CALL_RECORD_END);
                }
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
                setState(XMSCallState.PLAYCOLLECT);
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
//                    synchronized (m_synclock) {
//                        isBlocked = true;
//                        m_synclock.notifyAll();
//                    }
                    setState(XMSCallState.OFFERED);
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_OFFERED, this, "", "", "");
                    UnblockIfNeeded(xmsEvent);
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
//                    synchronized (m_synclock) {
//                        isBlocked = true;
//                        m_synclock.notifyAll();
//                    }
                    if (caller.getLocalSdp() == null) {
                        caller.setLocalSdp(msmlSip.getRemoteSdp());
                    }
                    setState(XMSCallState.ACCEPTED);
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_UPDATED, this, "", "", "");
                    UnblockIfNeeded(xmsEvent);
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
//                    synchronized (m_synclock) {
//                        isBlocked = true;
//                        m_synclock.notifyAll();
//                    }
                    setState(XMSCallState.CONNECTED);
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_CONNECTED, this, "", "", "");
                    UnblockIfNeeded(xmsEvent);
                }
            } else if (this.callMode == CallMode.OUTBOUND) {
                if (e.getCall() == caller) {
//                    synchronized (m_synclock) {
//                        isBlocked = true;
//                        m_synclock.notifyAll();
//                    }
                    setState(XMSCallState.CONNECTED);
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_CONNECTED, this, "", "", "");
                    UnblockIfNeeded(xmsEvent);
                }
            }
        } else if (e.getType().equals(EventType.INFORESPONSE)) {
            String reponseMessage = new String(e.getRes().getRawContent());
            if (getState() == XMSCallState.CUSTOM) {
                XMSEvent xmsEvent = new XMSEvent();
                xmsEvent.CreateEvent(XMSEventType.CALL_CUSTOM, this, "", "", reponseMessage);
                setLastEvent(xmsEvent);
            } else if (getState() != XMSCallState.DISCONNECTED) {
                Pattern pattern = Pattern.compile("response=\\\"(.*?)\\\"");
                Matcher m = pattern.matcher(reponseMessage);
                if (m.find()) {
                    String event = m.group(1);

                    if (Long.parseLong(event) == 200) {
                        System.out.println("Response 200 received");
                        mediaStatusCode = Integer.parseInt(event);
//                        synchronized (m_synclock) {
//                            isBlocked = true;
//                            m_synclock.notifyAll();
//                        }                        
                        XMSEvent xmsEvent = new XMSEvent();
                        xmsEvent.CreateEvent(XMSEventType.CALL_INFO, this, event, "", reponseMessage);
                        UnblockIfNeeded(xmsEvent);
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

            if (getState() == XMSCallState.CUSTOM) {
                xmsEvent = new XMSEvent();
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
                            xmsEvent = new XMSEvent();
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
                            xmsEvent = new XMSEvent();
                            xmsEvent.CreateEvent(XMSEventType.CALL_RECORD_END, this, len, reason, info);
                            xmsEvent.setReason(reason);
                            xmsEvent.setInternalData(info);
                            setLastEvent(xmsEvent);
                        }
                    }
                } else if (name != null && name.equalsIgnoreCase("msml.dialog.exit")) {
                    if (getState() != XMSCallState.DISCONNECTED) {

                        Pattern dialogPattern = Pattern.compile("dialog:(.*?)\\\"");
                        Matcher dialogMatcher = dialogPattern.matcher(info);
                        String dialogType = null;
                        if (dialogMatcher.find()) {
                            dialogType = dialogMatcher.group(1);
                        }
                        if (dialogType != null) {
                            if (dialogType.equals("Play")) {
                                //m_state = XMSCallState.PLAY_END;
                                setState(XMSCallState.PLAY_END);
                            } else if (dialogType.equals("Record")) {
                                //m_state = XMSCallState.RECORD_END;
                                setState(XMSCallState.RECORD_END);
                            }
                        }
//                        synchronized (m_synclock) {
//                            isBlocked = true;
//                            m_synclock.notifyAll();
//                        }
                        UnblockIfNeeded(xmsEvent);
                    } else {
                        msmlSip.createBye();
                    }
                } else if (name != null && name.equalsIgnoreCase("detect")) {

                }
            }
        } else if (e.getType().equals(EventType.DISCONNECTED)) {
            if (e.getCall() == msmlSip) {
                if (isDropCall) {
//                    synchronized (m_synclock) {
//                        isBlocked = true;
//                        isDropCall = false;
//                        this.callMode = CallMode.OUTBOUND;
//                        msmlSip = null;
//                        caller = null;
//                        MakecallOptions.EnableACKOn200(false);
//                        MakecallOptions.EnableOKOnInfo(false);
//                        if (counter > 1) {
//                            counter--;
//                        }
//                        m_synclock.notifyAll();
//                    }
                    isDropCall = false;
                    this.callMode = CallMode.OUTBOUND;
                    msmlSip = null;
                    caller = null;
                    MakecallOptions.EnableACKOn200(false);
                    MakecallOptions.EnableOKOnInfo(false);
                    setState(XMSCallState.DISCONNECTED);
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_DISCONNECTED, this, "", "", "");
                    UnblockIfNeeded(xmsEvent);
                } else if (getState() != XMSCallState.DISCONNECTED) {
                    if (e.getReq() != null) {
                        //m_state = XMSCallState.DISCONNECTED;
                        setState(XMSCallState.DISCONNECTED);
                        msmlSip.doByeOk(e.getReq());
                        caller.createBye();
                    }
                }
            } else if (e.getCall() == caller) {
                if (getState() != XMSCallState.DISCONNECTED) {
                    if (getState() == XMSCallState.PLAY) {
                        //media active, send dialog exit before bye
                        msmlSip.createDialogEndRequest(buildDialogExit("Play"));
                        //m_state = XMSCallState.DISCONNECTED;
                        setState(XMSCallState.DISCONNECTED);
                        caller.doByeOk(e.getReq());
                    } else if (getState() == XMSCallState.RECORD) {
                        msmlSip.createDialogEndRequest(buildDialogExit("Record"));
                        //m_state = XMSCallState.DISCONNECTED;
                        setState(XMSCallState.DISCONNECTED);
                        caller.doByeOk(e.getReq());
                    } else {
                        if (e.getReq() != null) {
                            //m_state = XMSCallState.DISCONNECTED;
                            setState(XMSCallState.DISCONNECTED);
                            caller.doByeOk(e.getReq());
                            msmlSip.createBye();
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

    public void sendCustomScript(String msml) {
        if (msmlSip != null && msml != null) {
            m_state = XMSCallState.CUSTOM;
            msmlSip.sendInfo(msml);
        }
    }
}
