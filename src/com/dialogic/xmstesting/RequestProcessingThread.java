/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import java.util.Set;
import javax.sip.RequestEvent;
import javax.sip.message.Request;

/**
 *
 * @author ssatyana
 */
public class RequestProcessingThread extends Thread {

    Request request;
    RequestEvent requestEvent;
    Call call;

    public RequestProcessingThread(RequestEvent requestEvent, Call call) {

        this.request = requestEvent.getRequest();
        this.requestEvent = requestEvent;
        this.call = call;
        //this.start();
    }

    @Override
    public void run() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("Active Threads Count : " + threadSet.size());
        call.handleStackRequest(requestEvent);
    }
}
