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
public class XMSConference {

    public void start() {
        try {

            Connector connector = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
//        XMSObjectFactory myFactory = new XMSObjectFactory();
//        XMSConnector myConnector = myFactory.CreateConnector("XMSConnectorConfig.xml");
//        XMSCall myCall = myFactory.CreateCall(myConnector); - msml call 
            MsmlCall call1 = new MsmlCall(connector);
            MsmlCall call2 = new MsmlCall(connector);

            // factory creates the conference
            call1.createConf("conf1");

            call1.waitCall();

            call1.add("conf1");

            call2.waitCall();
            call2.add("conf1");

        } catch (Exception ex) {
            Logger.getLogger(XMSConference.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
