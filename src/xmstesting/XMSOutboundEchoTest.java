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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ssatyana
 */
public class XMSOutboundEchoTest implements Observer {

    Call call1;
    Call call2;
    Call call3;
    Connector connector;
    String makecalldest = "toto@10.20.120.24:5060";
    static boolean isRecorded = false;
    static boolean isRecordEnded = false;

    public void start() {
        try {

            connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            call1 = new Call(connector);
            call1.addObserver(this);
            call1.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            call1.setACKOn200(Boolean.FALSE);
            call1.makeCall("msml@146.152.64.141");

        } catch (Exception ex) {
            Logger.getLogger(XMSOutboundEchoTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void update(Observable o, Object o1) {
        Event e = (Event) o1;
        System.out.println(e.getType());
        try {
            if (e.getType().equals(EventType.INCOMING)) {

            } else if (e.getType().equals(EventType.RINGING)) {

            } else if (e.getType().equals(EventType.CONNECTING)) {
                if (e.getCall() == call2 || e.getCall() == call3) {
                    // if ack on 200 is set to false
                    if (isRecorded) {
                        call3.ackCall(e.getRes());
                    } else {
                        call1.ackCall(e.getRes());
                        call2.ackCall(e.getRes());
                    }
                } else if (call2 == null && call3 == null) {
                    Thread.sleep(500);
                    call2 = new Call(connector);
                    call2.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                    call2.setLocalSdp(new String(e.getRes().getRawContent()));
                    call2.addObserver(this);
                    call2.makeCall(makecalldest);
                }
            } else if (e.getType().equals(EventType.CONNECTED)) {
                if (e.getCall() == call2 || e.getCall() == call3) {
                    if (isRecorded) {
                        call1.play(MsmlCall.buildPlayMsml("file://recorded/Test.wav"));
                    } else {
                        call1.record(MsmlCall.buildRecordMsml("file://recorded/Test.wav", 10));
                        isRecorded = true;
                    }
                }
            } else if (e.getType().equals(EventType.INFORESPONSE)) {
                String reponseMessage = new String(e.getRes().getRawContent());
            } else if (e.getType().equals(EventType.INFOREQUEST)) {
                call1.createInfoResponse(e.getReq());
                String info = new String(e.getReq().getRawContent());
                String name = null;
                Pattern pattern = Pattern.compile("name=\\\".*?\\\"");
                Matcher m = pattern.matcher(info);
                if (m.find()) {
                    String event = m.group(0);
                    Pattern p = Pattern.compile("\\\"(.*?)\\\"");
                    Matcher matcher = p.matcher(event);
                    if (matcher.find()) {
                        name = matcher.group(1);
                    }
                }
                if (name != null && name.equalsIgnoreCase("msml.dialog.exit")) {
                    if (isRecordEnded) {
                        //do nothing
                    } else {
                        call2.dropCall();
                        isRecordEnded = true;
                        Thread.sleep(500);
                        call3 = new Call(connector);
                        call3.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
                        call3.setLocalSdp(call2.getLocalSdp());
                        call3.addObserver(this);
                        call3.makeCall(makecalldest);
                    }
                }
            } else if (e.getType().equals(EventType.DISCONNECTED)) {
                if (e.getCall() == call1) {
                    call1.doByeOk(e.getReq());
                    call2.dropCall();
                } else if (e.getCall() == call2) {
                    call2.doByeOk(e.getReq());
                    call1.dropCall();
                } else if (e.getCall() == call3) {
                    call3.doByeOk(e.getReq());
                    call1.dropCall();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(XMSOutboundEchoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
