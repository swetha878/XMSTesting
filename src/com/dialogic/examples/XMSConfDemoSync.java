/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.clientLibrary.Layout;
import com.dialogic.clientLibrary.XMSCall;
import com.dialogic.clientLibrary.XMSConference;
import com.dialogic.clientLibrary.XMSConnector;
import com.dialogic.clientLibrary.XMSMediaType;
import com.dialogic.clientLibrary.XMSObjectFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            XMSCall call3 = myFactory.CreateCall(myConnector);
            XMSCall call4 = myFactory.CreateCall(myConnector);
            XMSCall call5 = myFactory.CreateCall(myConnector);
            XMSCall call6 = myFactory.CreateCall(myConnector);
            XMSCall call7 = myFactory.CreateCall(myConnector);
            XMSCall call8 = myFactory.CreateCall(myConnector);
            XMSCall call9 = myFactory.CreateCall(myConnector);

            XMSConference myConf = myFactory.CreateConference(myConnector);

            call1.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call1.Waitcall();
//            call1.PlayOptions.SetMediaType(XMSMediaType.VIDEO);
//            call1.Play("file://verification/video_clip_newscast");
            myConf.Add(call1);

            call2.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call2.Waitcall();
            myConf.Add(call2);

            call3.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call3.Waitcall();
            myConf.Add(call3);

            call4.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call4.Waitcall();
            myConf.Add(call4);

            call5.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call5.Waitcall();
            myConf.Add(call5);

            call6.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call6.Waitcall();
            myConf.Add(call6);

            call7.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call7.Waitcall();
            myConf.Add(call7);

            call8.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call8.Waitcall();
            myConf.Add(call8);

            call9.WaitcallOptions.SetMediaType(XMSMediaType.VIDEO);
            call9.Waitcall();
            myConf.Add(call9);

            Sleep(30000);
            call1.Dropcall();
            call2.Dropcall();
            call3.Dropcall();
            call4.Dropcall();
            call5.Dropcall();
            call6.Dropcall();
            call7.Dropcall();
            call8.Dropcall();
            call9.Dropcall();
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
