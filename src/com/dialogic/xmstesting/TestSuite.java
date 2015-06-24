/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import com.dialogic.examples.XMSConfDemoSync;
import com.dialogic.examples.XMSDigitDetect;
import com.dialogic.examples.XMSEchoTestAsync;
import com.dialogic.examples.XMSMultipleInvites;
import com.dialogic.examples.XMSInboundEchoTest;
import com.dialogic.examples.XMSInboundNoAutoConnectTest;
import com.dialogic.examples.XMSInboundRecordTest;
import com.dialogic.examples.XMSInboundTest;
import com.dialogic.examples.XMSMultipleInboundTest;
import com.dialogic.examples.XMSOutboundTest;

/**
 *
 * @author ssatyana
 */
public class TestSuite {

    public static void main(String[] args) {

        //new XMSInboundTest().start();
        //new XMSOutboundTest().start();
        //new XMSInboundRecordTest().start();
        //new XMSInboundEchoTest().start();
        //new XMSInboundNoAutoConnectTest().start();
        //new XMSMultipleInvites().start();
        //new XMSMultipleInboundTest().start();
        new XMSConfDemoSync().start();
        //new XMSDigitDetect().start();
        //new XMSEchoTestAsync().start();
    }
}
