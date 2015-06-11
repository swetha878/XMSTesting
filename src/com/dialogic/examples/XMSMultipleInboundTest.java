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
public class XMSMultipleInboundTest {

    public void start() {
        try {

            Connector connector1 = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            Connector connector2 = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5061);
//        XMSObjectFactory myFactory = new XMSObjectFactory();
//        XMSConnector myConnector = myFactory.CreateConnector("XMSConnectorConfig.xml");
//        XMSCall myCall = myFactory.CreateCall(myConnector); - msml call 
            MsmlCall call1 = new MsmlCall(connector1);
            MsmlCall call2 = new MsmlCall(connector2);
            call1.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
            call2.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            XMSReturnCode result = call1.waitCall();

            call2.waitCall();

            XMSReturnCode confResult = call1.createConfAndJoin("one");

            call2.add("one");

//
//            call1.dropCall();
        } catch (Exception ex) {
            Logger.getLogger(XMSMultipleInboundTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
