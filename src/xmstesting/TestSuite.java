/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmstesting;

/**
 *
 * @author ssatyana
 */
public class TestSuite {

    public static void main(String[] args) {
        //new XMSInboundTest().start();
        //new XMSOutboundTest().start();
        new XMSOutboundEchoTest().start();

    }
}
