/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSConnector;
import com.dialogic.clientLibrary.XMSMediaType;
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
public class XMSInboundRecordTest {

    public void start() {
        try {

            XMSObjectFactory myFactory = new XMSObjectFactory();
            XMSConnector myConnector = myFactory.CreateConnector("ConnectorConfig.xml");
            XMSCall call = myFactory.CreateCall(myConnector);

            XMSReturnCode result = call.Waitcall();

            System.out.println("RESULT" + result);

            call.RecordOptions.SetMediaType(XMSMediaType.VIDEO);
            XMSReturnCode recordResult = call.Record("file://recorded/Test");

            System.out.println("RESULT" + recordResult);

            System.out.println("RECORD END REASON -> " + call.getLastEvent().getReason());
            call.Dropcall();
        } catch (Exception ex) {
            Logger.getLogger(XMSInboundRecordTest.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
