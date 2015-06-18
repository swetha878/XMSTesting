///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dialogic.examples;
//
//import com.dialogic.XMSClientLibrary.*;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author dwolansk
// */
//public class XMSEchoTestAsync {
//
//    /**
//     * @param args the command line arguments
//     */
//    public void start() {
//        // TODO code application logic here
//        XMSObjectFactory myFactory = new XMSObjectFactory();
//        XMSConnector myConnector = myFactory.CreateConnector("XMSConnectorConfig.xml");
//        XMSCall myCall = myFactory.CreateCall(myConnector);
//        MyCallbacks myCallback = new MyCallbacks();
//
//        //Enable all events to go back to my event handler
//        myCall.EnableAllEvents(myCallback);
//
//        //Wait for an inbound call and start the state machine
//        myCall.Waitcall();
//
//        //At this point event handler thread will process all events so this thread just waits to complete
//        while (!myCallback.isDone) {
//            Sleep(1000);
//        }
//
//    }
//
//    public static void Sleep(int time) {
//        try {
//            Thread.sleep(time);
//        } catch (InterruptedException ex) {
//            System.out.print(ex);
//        }
//
//    }
//}
