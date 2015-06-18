///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.dialogic.examples;
//
//import com.dialogic.clientLibrary.XMSCall;
//import com.dialogic.clientLibrary.XMSEvent;
//import com.dialogic.clientLibrary.XMSEventCallback;
//
///**
// *
// * @author dwolansk
// */
//enum AppState {
//
//    WAITCALL,
//    RECORD,
//    MAKECALL,
//    PLAY
//}
//
//public class EchoAsyncMyCallbacks implements XMSEventCallback {
//
//    String addr = "";
//    AppState myState = AppState.WAITCALL;
//    boolean isDone = false;
//
//    @Override
//    public void ProcessEvent(XMSEvent a_event) {
//        //throw new UnsupportedOperationException("Not supported yet.");
//        XMSCall myCall = a_event.getCall();
//        switch (a_event.getEventType()) {
//            case CALL_CONNECTED:
//                if (myState == AppState.MAKECALL) {
//                    myCall.play("echotest.wav");
//                } else {  // is waitcall
//                    //Save the connection address for later
//                    addr = myCall.getConnectionAddress();
//                    //Set the Record options to only record for 10seconds
//                    myCall.recordOptions.SetMaxTime(10);
//                    //Record a file
//                    myState = AppState.RECORD;
//                    myCall.record("echotest.wav");
//                }
//                break;
//            case CALL_RECORD_END:
//                myCall.dropCall();
//                myState = AppState.MAKECALL;
//                myCall.makeCall(addr);
//                break;
//            case CALL_PLAY_END:
//                myCall.dropCall();
//                isDone = true;
//                break;
//            case CALL_DISCONNECTED:  // The far end hung up will simply wait for the media
//
//                break;
//            default:
//                System.out.println("Unknown Event Type!!");
//        }
//
//    }
//
//}
