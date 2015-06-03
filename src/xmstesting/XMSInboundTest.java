/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmstesting;

import java.io.FileInputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 * @author ssatyana
 */
public class XMSInboundTest implements Observer {

    static boolean isIncoming = false;
    static boolean isConnecting = false;
    static boolean isConnected = false;
    Call call1;
    Call call2;
    Connector connector;

    public void start() {
        try {

            connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            call1 = new Call(connector);
            call1.addObserver(this);
            call1.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            call1.waitCall();

            //call.dropCall();
        } catch (Exception ex) {
            Logger.getLogger(XMSInboundTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        Event e = (Event) o1;
        try {
            if (e.getType().equals(EventType.INCOMING)) {
                // incoming call, send an invite to xms
                call2 = new Call(connector);
                if (call2.getToUser() == null || call2.getToAddress() == null) {
                    setXMSInfo(call2);
                }
                call2.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                if (e.getReq().getRawContent() != null) {
                    call2.setLocalSdp(new String(e.getReq().getRawContent()));
                }
                call2.addObserver(this);
                call2.makeCall(call2.getToUser() + "@" + call2.getToAddress());
                //xmsCall.createInviteRequest(xmsCall.getToUser(), xmsCall.getToAddress());
            } else if (e.getType().equals(EventType.RINGING)) {
                // send 180 to the inbound call
                call1.acceptCall();
            } else if (e.getType().equals(EventType.CONNECTING)) {
                // send 200 to the inbound call
                call1.answerCall();
            } else if (e.getType().equals(EventType.CONNECTED)) {
                // recieved ack from inbound call and its connected
                if (e.getCall() == call1) {
                    call2.play(MsmlCall.buildPlayMsml("file://verification/greeting.wav"));
                }
            } else if (e.getType().equals(EventType.INFORESPONSE)) {
                String reponseMessage = new String(e.getRes().getRawContent());
            } else if (e.getType().equals(EventType.INFOREQUEST)) {
                call2.createInfoResponse(e.getReq());
            } else if (e.getType().equals(EventType.DISCONNECTED)) {
                if (e.getCall() == call2) {
                    call2.doByeOk(e.getReq());
                    call1.dropCall();
                } else if (e.getCall() == call1) {
                    call1.doByeOk(e.getReq());
                    call2.dropCall();
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(XMSInboundTest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Call.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
