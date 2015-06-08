/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

/**
 *
 * @author ssatyana
 */
public enum EventType {

    IDLE,
    INCOMING, // incoming call
    RINGING, // send 180
    CONNECTING, // send 200
    CONNECTED, // send ACK
    INFORESPONSE, // info response
    INFOREQUEST, // info request(terminate, dialog exit etc)
    DISCONNECTED // hangup
}
