/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmstesting;

import java.net.Inet4Address;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssatyana
 */
public class XMSOutboundTest implements Observer {

    Call call1;
    Call call2;
    Connector connector;
    String makecalldest = "toto@10.20.120.24:5060";

    public void start() {
        try {

            connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            call1 = new Call(connector);
            call1.addObserver(this);
            call1.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            call1.setACKOn200(Boolean.FALSE);
            call1.makeCall("msml@146.152.64.141");

        } catch (Exception ex) {
            Logger.getLogger(XMSOutboundTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        Event e = (Event) o1;
        try {
            if (e.getType().equals(EventType.INCOMING)) {

            } else if (e.getType().equals(EventType.RINGING)) {

            } else if (e.getType().equals(EventType.CONNECTING)) {
                if (e.getCall() == call2) {
                    call1.createAckRequest(e.getRes());
                    call2.createAckRequest(e.getRes());
                } else if (call2 == null) {
                    Thread.sleep(500);
                    call2 = new Call(connector);
                    call2.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                    call2.setLocalSdp(new String(e.getRes().getRawContent()));
                    call2.addObserver(this);
                    call2.makeCall(makecalldest);
                }
            } else if (e.getType().equals(EventType.CONNECTED)) {
                if (e.getCall() == call2) {
                    call1.play(MsmlCall.buildPlayMsml("file://verification/greeting.wav"));
                }
            } else if (e.getType().equals(EventType.INFORESPONSE)) {
                String reponseMessage = new String(e.getRes().getRawContent());
            } else if (e.getType().equals(EventType.INFOREQUEST)) {
                call1.createInfoResponse(e.getReq());
            } else if (e.getType().equals(EventType.DISCONNECTED)) {
                if (e.getCall() == call1) {
                    call1.doByeOk(e.getReq());
                    call2.createBye();
                } else if (e.getCall() == call2) {
                    call2.doByeOk(e.getReq());
                    call1.createBye();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(XMSOutboundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
