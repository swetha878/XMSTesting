/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import com.dialogic.examples.XMSInboundEchoTest;
import com.dialogic.examples.XMSInboundRecordTest;
import com.dialogic.examples.XMSInboundTest;
import com.dialogic.examples.XMSOutboundTest;

/**
 *
 * @author ssatyana
 */
public class TestSuite {

    public static void main(String[] args) {

        new XMSInboundTest().start();
        //new XMSOutboundTest().start();
        //new XMSInboundRecordTest().start();
        //new XMSInboundEchoTest().start();

    }
}