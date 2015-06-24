/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSCallState;
import com.dialogic.clientLibrary.XMSConference;
import com.dialogic.clientLibrary.XMSConferenceOptions;
import com.dialogic.clientLibrary.XMSConnector;
import com.dialogic.clientLibrary.XMSEvent;
import com.dialogic.clientLibrary.XMSEventType;
import com.dialogic.clientLibrary.XMSMediaType;
import com.dialogic.clientLibrary.XMSReturnCode;
import com.dialogic.xms.msml.AudioMixType;
import com.dialogic.xms.msml.BasicAudioMixType;
import com.dialogic.xms.msml.BooleanDatatype;
import com.dialogic.xms.msml.Msml;
import com.dialogic.xms.msml.ObjectFactory;
import com.dialogic.xms.msml.RootType;
import com.dialogic.xms.msml.StreamType;
import com.dialogic.xms.msml.VideoLayoutType;
import static com.dialogic.xmstesting.MsmlCall.logger;
import static com.dialogic.xmstesting.MsmlCall.objectFactory;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
    static ObjectFactory objectFactory = new ObjectFactory();

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

            if (e.getRes().getRawContent() != null) {
                Msml msml = unmarshalObject(new ByteArrayInputStream((byte[]) e.getRes().getRawContent()));
                Msml.Result result = msml.getResult();
                if (result.getResponse().equalsIgnoreCase("200")) {
                    System.out.println("Response received" + result.getResponse());
                    XMSEvent xmsEvent = new XMSEvent();
                    xmsEvent.CreateEvent(XMSEventType.CALL_INFO, this, result.getResponse(), "", reponseMessage);
                    UnblockIfNeeded(xmsEvent);
                } else if (result.getResponse().equalsIgnoreCase("400")) {
                    System.out.println("Response 400 received");
                }
            }
        } else if (e.getType().equals(EventType.INFOREQUEST)) {
            String info = new String(e.getReq().getRawContent());

            Msml msml = unmarshalObject(new ByteArrayInputStream((byte[]) e.getReq().getRawContent()));
            Msml.Event event = msml.getEvent();
            String eventName = event.getName();
            if (eventName != null && eventName.equalsIgnoreCase("msml.conf.nomedia")) {
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
            m_logger.error(ex.getMessage(), ex);
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

    private String buildConfMsml(String name) {
        java.io.StringWriter sw = new StringWriter();

        Msml msml = objectFactory.createMsml();
        msml.setVersion("1.1");

        Msml.Createconference createConf = objectFactory.createMsmlCreateconference();
        createConf.setName(name);
        createConf.setDeletewhen("nocontrol");
        createConf.setMark("1");
        createConf.setTerm(BooleanDatatype.TRUE);

        AudioMixType audiomix = objectFactory.createAudioMixType();
        audiomix.setId("mix1234");

        createConf.setAudiomix(audiomix);
        msml.getMsmlRequest().add(createConf);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(msml, sw);

        } catch (JAXBException ex) {
            m_logger.error(ex.getMessage(), ex);
        }

        System.out.println("MSML CONF -> " + sw.toString());
        return sw.toString();
    }

    private String buildConfVideoMsml(String name) {
        java.io.StringWriter sw = new StringWriter();

        Msml msml = objectFactory.createMsml();
        msml.setVersion("1.1");

        Msml.Createconference createConf = objectFactory.createMsmlCreateconference();
        createConf.setName(name);
        createConf.setDeletewhen("nomedia");
        createConf.setMark("1");
        createConf.setTerm(BooleanDatatype.TRUE);

        VideoLayoutType videoLayout = objectFactory.createVideoLayoutType();

        RootType rootType = objectFactory.createRootType();
        rootType.setSize("VGA");

        if (ConferenceOptions.m_MediaType == XMSMediaType.AUDIO) {
            AudioMixType audiomix = objectFactory.createAudioMixType();
            audiomix.setId("mix1234");
            AudioMixType.NLoudest nLoudest = objectFactory.createAudioMixTypeNLoudest();
            nLoudest.setN(3);
            audiomix.setNLoudest(nLoudest);
            AudioMixType.Asn asn = objectFactory.createAudioMixTypeAsn();
            asn.setRi("10s");
            audiomix.setAsn(asn);
            createConf.setAudiomix(audiomix);
        } else {
            VideoLayoutType.Region region1 = objectFactory.createVideoLayoutTypeRegion();
            region1.setId("1");
            region1.setLeft("0");
            region1.setTop("0");
            region1.setRelativesize("1/2");

            VideoLayoutType.Region region2 = objectFactory.createVideoLayoutTypeRegion();
            region2.setId("2");
            region2.setLeft("50%");
            region2.setTop("0");
            region2.setRelativesize("1/2");

            VideoLayoutType.Region region3 = objectFactory.createVideoLayoutTypeRegion();
            region3.setId("3");
            region3.setLeft("0");
            region3.setTop("50%");
            region3.setRelativesize("1/2");

            VideoLayoutType.Region region4 = objectFactory.createVideoLayoutTypeRegion();
            region4.setId("4");
            region4.setLeft("50%");
            region4.setTop("50%");
            region4.setRelativesize("1/2");

            videoLayout.setRoot(rootType);
            videoLayout.getRegion().add(region1);
            videoLayout.getRegion().add(region2);
            videoLayout.getRegion().add(region3);
            videoLayout.getRegion().add(region4);

            createConf.setVideolayout(videoLayout);
        }
        msml.getMsmlRequest().add(createConf);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(msml, sw);

        } catch (JAXBException ex) {
            m_logger.error(ex.getMessage(), ex);
        }

        System.out.println("MSML CONF -> " + sw.toString());
        return sw.toString();
    }

    private static String buildJoinConfMsml(String name) {
        java.io.StringWriter sw = new StringWriter();

        Msml msml = objectFactory.createMsml();
        msml.setVersion("1.1");

        Msml.Join join = objectFactory.createMsmlJoin();
        join.setId1("conf:" + name);
        join.setId2("conn:1234");
        join.setMark("2");

        StreamType streamType = objectFactory.createStreamType();
        streamType.setMedia("audio");

        join.getStream().add(streamType);
        msml.getMsmlRequest().add(join);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(msml, sw);

        } catch (JAXBException ex) {
            m_logger.error(ex.getMessage(), ex);
        }

        System.out.println("MSML JOIN -> " + sw.toString());
        return sw.toString();
    }

    private static String buildJoinConfVideoMsml(String name) {
        java.io.StringWriter sw = new StringWriter();

        Msml msml = objectFactory.createMsml();
        msml.setVersion("1.1");

        Msml.Join join = objectFactory.createMsmlJoin();
        join.setId2("conf:" + name);
        join.setId1("conn:1234");
        join.setMark("2");

        StreamType streamType1 = objectFactory.createStreamType();
        streamType1.setMedia("audio");

        StreamType streamType2 = objectFactory.createStreamType();
        streamType2.setMedia("video");
        streamType2.setDir("from-id1");
        streamType2.setDisplay("1");

        StreamType streamType3 = objectFactory.createStreamType();
        streamType3.setMedia("video");
        streamType3.setDir("to-id1");

        join.getStream().add(streamType1);
        join.getStream().add(streamType2);
        join.getStream().add(streamType3);

        msml.getMsmlRequest().add(join);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(msml, sw);

        } catch (JAXBException ex) {
            m_logger.error(ex.getMessage(), ex);
        }

        System.out.println("MSML JOIN -> " + sw.toString());
        return sw.toString();
    }

    private static String buildDestroyConfMsml(String name) {
        java.io.StringWriter sw = new StringWriter();

        Msml msml = objectFactory.createMsml();
        msml.setVersion("1.1");

        Msml.Destroyconference destroyConf = objectFactory.createMsmlDestroyconference();
        destroyConf.setId("conf:" + name);
        destroyConf.setMark("1");

        msml.getMsmlRequest().add(destroyConf);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(msml, sw);

        } catch (JAXBException ex) {
            m_logger.error(ex.getMessage(), ex);
        }

        System.out.println("MSML DESTROY CONF -> " + sw.toString());
        return sw.toString();
    }

    public Msml unmarshalObject(ByteArrayInputStream sdp) {
        Msml msml = objectFactory.createMsml();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Msml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            msml = (Msml) unmarshaller.unmarshal(sdp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msml;
    }
}
