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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssatyana
 */
public class XMSInboundEchoTest {

    public void start() {
        try {

            XMSObjectFactory myFactory = new XMSObjectFactory();
            XMSConnector myConnector = myFactory.CreateConnector("ConnectorConfig.xml");
            XMSCall call = myFactory.CreateCall(myConnector);

            XMSReturnCode result = call.Waitcall();

            System.out.println("RESULT call connected" + result);

            XMSReturnCode recordResult = call.Record("file://recorded/Test.wav");

            System.out.println("RESULT record end" + recordResult);

            XMSReturnCode dropResult = call.Dropcall();

            System.out.println("RESULT drop call" + dropResult);

            String adr = call.getConnectionAddress();

            XMSReturnCode makeResult = call.Makecall(adr);

            System.out.println("RESULT make call" + makeResult);

            XMSReturnCode playResult = call.Play("file://recorded/Test.wav");

            System.out.println("RESULT play end" + playResult);

            call.Dropcall();
        } catch (Exception ex) {
            Logger.getLogger(XMSInboundEchoTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
