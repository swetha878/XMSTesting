/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.examples;

import com.dialogic.xmstesting.Call;
import com.dialogic.xmstesting.Connector;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssatyana
 */
public class XMSMultipleInvites {

    public void start() {
        try {
            Connector connector1 = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5070);
            Connector connector2 = new Connector(Inet4Address.getLocalHost().getHostAddress(), 5061);

            Call call1 = new Call(connector1);
            Call call2 = new Call(connector2);

            call1.setFromAddress(Inet4Address.getLocalHost().getHostAddress());
            call2.setFromAddress(Inet4Address.getLocalHost().getHostAddress());

            call1.createInviteRequest("msml", "146.152.64.141");
            call2.createInviteRequest("msml", "146.152.64.141");
        } catch (UnknownHostException ex) {
            Logger.getLogger(XMSMultipleInvites.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
