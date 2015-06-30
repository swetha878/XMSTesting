/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSConnector;
import com.dialogic.clientLibrary.XMSObjectFactory;
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

            XMSObjectFactory myFactory = new XMSObjectFactory();
            XMSConnector myConnector = myFactory.CreateConnector("ConnectorConfig.xml");
            XMSCall call = myFactory.CreateCall(myConnector);

            call.MakecallOptions.EnableACKOn200(false);
            call.MakecallOptions.EnableOKOnInfo(false);
            XMSReturnCode result = call.Makecall(makecalldest);

            System.out.println("RESULT" + result);

            XMSReturnCode playResult = call.Play("file://verification/greeting");

            System.out.println("RESULT" + playResult);

            call.Dropcall();
        } catch (Exception ex) {
            Logger.getLogger(XMSOutboundTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
