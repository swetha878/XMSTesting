/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSConference;
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
public class XMSConfDemoSync {

    public void start() {
        try {

            XMSObjectFactory myFactory = new XMSObjectFactory();
            XMSConnector myConnector = myFactory.CreateConnector("ConnectorConfig.xml");
            XMSCall call1 = myFactory.CreateCall(myConnector);
            XMSCall call2 = myFactory.CreateCall(myConnector);

            XMSConference myConf = myFactory.CreateConference(myConnector);

            call1.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call1.Waitcall();
            myConf.Add(call1);

            call2.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call2.Waitcall();
            myConf.Add(call2);

            Sleep(30000);
            call1.Dropcall();
            call2.Dropcall();
        } catch (Exception ex) {
            Logger.getLogger(XMSConfDemoSync.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static void Sleep(int time) {
        try {

            Thread.sleep(time);
        } catch (InterruptedException ex) {
            System.out.print(ex);
        }

    }
}
