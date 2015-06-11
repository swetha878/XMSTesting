/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.clientLibrary.XMSReturnCode;
import com.dialogic.xmstesting.Connector;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.dialogic.xmstesting.MsmlCall;

/**
 *
 * @author ssatyana
 */
public class XMSOutboundTest {

    String makecalldest = "toto@10.20.120.24:5060";

    public void start() {
        try {

            Connector connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
//        XMSObjectFactory myFactory = new XMSObjectFactory();
//        XMSConnector myConnector = myFactory.CreateConnector("XMSConnectorConfig.xml");
//        XMSCall myCall = myFactory.CreateCall(myConnector); - msml call 
            MsmlCall call = new MsmlCall(connector);
            call.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            call.MakecallOptions.EnableACKOn200(false);
            call.MakecallOptions.EnableOKOnInfo(false);
            XMSReturnCode result = call.makeCall(makecalldest);

            System.out.println("RESULT" + result);

            XMSReturnCode playResult = call.play("file://verification/greeting.wav");

            System.out.println("RESULT" + playResult);

            call.dropCall();
        } catch (Exception ex) {
            Logger.getLogger(XMSOutboundTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
