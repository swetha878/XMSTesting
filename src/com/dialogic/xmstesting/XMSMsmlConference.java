/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSCallState;
import com.dialogic.clientLibrary.XMSConference;
import com.dialogic.clientLibrary.XMSConnector;
import com.dialogic.clientLibrary.XMSEvent;
import com.dialogic.clientLibrary.XMSEventType;
import com.dialogic.clientLibrary.XMSReturnCode;
import static com.dialogic.xmstesting.MsmlCall.isBlocked;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author ssatyana
 */
public class XMSMsmlConference extends XMSConference implements Observer {

    private static Logger m_logger = Logger.getLogger(XMSMsmlConference.class.getName());

    private Connector connector;
    Call conf;
    static int counter = 0;
    static XMSCallState m_state = XMSCallState.NULL;

    public XMSMsmlConference() {
        m_type = "MSML";
        m_Name = "XMSMsmlConference:" + m_objectcounter++;

        PropertyConfigurator.configure("log4j.properties");
        //m_logger.setLevel(Level.ALL);
        m_logger.info("Creating " + m_Name);

    }

    public XMSMsmlConference(XMSConnector a_connector) {
        m_callIdentifier = null;
        m_Name = "XMSConference:" + m_objectcounter++;
        //m_Name = "conf1";
        PropertyConfigurator.configure("log4j.properties");
        //m_logger.setLevel(Level.ALL);
        m_logger.info("Creating " + m_Name);
        m_state = XMSCallState.CONF;

        m_type = a_connector.getType();
        connector = (Connector) a_connector;
        m_connector = a_connector;
        createConf(m_Name);

    }

    public XMSReturnCode createConf(String name) {
        try {

            conf = new Call(connector);
            conf.addObserver(this);
            setXMSInfo(conf);
            conf.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
            conf.setLocalSdp(getNullSdp());
            conf.createInviteRequest(conf.getToUser(), conf.getToAddress());
//            synchronized (m_synclock) {
//                while (!isBlocked) {
//                    m_synclock.wait();
//                }
//                isBlocked = false;
//                //conf.sendInfo(buildConfMsml(name));
//                conf.sendInfo(buildConfVideoMsml(name));
//                m_state = XMSCallState.CONF;
//                synchronized (m_synclock) {
//                    while (!isBlocked) {
//                        m_synclock.wait();
//                    }
//                    isBlocked = false;
//                    counter++;
//                }
//            }
            BlockIfNeeded(XMSEventType.CALL_CONNECTED);
            conf.sendInfo(buildConfVideoMsml(name));
            BlockIfNeeded(XMSEventType.CALL_INFO);
            counter++;
        } catch (Exception ex) {
            m_logger.error(ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public XMSReturnCode Add(XMSCall call) {
        MsmlCall msmlCall = (MsmlCall) call;
        try {
            if (msmlCall != null && msmlCall.msmlSip != null) {
                msmlCall.msmlSip.sendInfo(buildJoinConfVideoMsml(m_Name));
                counter++;
            }
        } catch (Exception ex) {
            m_logger.error(ex.getMessage(), ex);
        }
        return XMSReturnCode.SUCCESS;
    }

    @Override
    public void update(Observable o, Object o1) {
        Event e = (Event) o1;
        if (e.getType().equals(EventType.RINGING)) {

        } else if (e.getType().equals(EventType.CONNECTING)) {

        } else if (e.getType().equals(EventType.CONNECTED)) {
            XMSEvent xmsEvent = new XMSEvent();
            xmsEvent.CreateEvent(XMSEventType.CALL_CONNECTED, this, "", "", "");
            UnblockIfNeeded(xmsEvent);
        } else if (e.getType().equals(EventType.INFORESPONSE)) {
            String reponseMessage = new String(e.getRes().getRawContent());
            Pattern pattern = Pattern.compile("response=\\\"(.*?)\\\"");
            Matcher m = pattern.matcher(reponseMessage);
            if (m.find()) {
                String event = m.group(1);

                if (Long.parseLong(event) == 200) {
                    System.out.println("Response 200 received");
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_INFO, this, event, "", reponseMessage);
                    UnblockIfNeeded(xmsEvent);
                } else if (Long.parseLong(event) == 400) {
                    System.out.println("Response 400 received");
                }
            }
        } else if (e.getType().equals(EventType.INFOREQUEST)) {
            String info = new String(e.getReq().getRawContent());
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
            if (name != null && name.equalsIgnoreCase("msml.conf.nomedia")) {
                conf.createBye();
            }
        } else if (e.getType().equals(EventType.DISCONNECTED)) {
            if (counter > 1) {
                counter--;
            }
            if (counter == 1) {
                conf.createBye();
            }
        }
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
            java.util.logging.Logger.getLogger(Call.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getNullSdp() {
        String sdp = "v=0 \n"
                + "o=test 53655765 2353687637 IN IP4 0.0.0.0 \n"
                + "s=test Server \n"
                + "c=IN IP4 0.0.0.0 \n"
                + "t=0 0";
        return sdp;
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

}
