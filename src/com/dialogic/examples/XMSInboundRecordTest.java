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
public class XMSInboundRecordTest {

    public void start() {
        try {

            //connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
//        XMSObjectFactory myFactory = new XMSObjectFactory();
//        XMSConnector myConnector = myFactory.CreateConnector("XMSConnectorConfig.xml");
//        XMSCall myCall = myFactory.CreateCall(myConnector); - msml call 
            MsmlCall call = new MsmlCall();
            call.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            XMSReturnCode result = call.waitCall();

            System.out.println("RESULT" + result);

            XMSReturnCode recordResult = call.record("file://recorded/Test.wav");

            System.out.println("RESULT" + recordResult);

            //call.dropCall();
        } catch (Exception ex) {
            Logger.getLogger(XMSInboundRecordTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
