/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.clientLibrary.XMSReturnCode;
import java.net.Inet4Address;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.dialogic.xmstesting.MsmlCall;

/**
 *
 * @author ssatyana
 */
public class XMSInboundEchoTest {

    public void start() {
        try {

            //connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
//        XMSObjectFactory myFactory = new XMSObjectFactory();
//        XMSConnector myConnector = myFactory.CreateConnector("XMSConnectorConfig.xml");
//        XMSCall myCall = myFactory.CreateCall(myConnector); - msml call 
            MsmlCall call = new MsmlCall();
            call.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            XMSReturnCode result = call.waitCall();

            System.out.println("RESULT call connected" + result);

            XMSReturnCode recordResult = call.record("file://recorded/Test.wav");

            System.out.println("RESULT record end" + recordResult);

            XMSReturnCode dropResult = call.dropCall();

            System.out.println("RESULT drop call" + dropResult);

            String adr = call.getConnectionAddress();

            XMSReturnCode makeResult = call.makeCall(adr);

            System.out.println("RESULT make call" + makeResult);

            XMSReturnCode playResult = call.play("file://recorded/Test.wav");

            System.out.println("RESULT play end" + playResult);

            call.dropCall();
        } catch (Exception ex) {
            Logger.getLogger(XMSInboundEchoTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
