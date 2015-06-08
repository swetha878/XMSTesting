/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dialogic.xmstesting;

import gov.nist.javax.sip.header.CSeq;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sip.ClientTransaction;
import javax.sip.Dialog;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.SipFactory;
import javax.sip.SipStack;
import javax.sip.PeerUnavailableException;
import javax.sip.SipProvider;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.ServerTransaction;
import javax.sip.SipException;
import javax.sip.address.AddressFactory;
import javax.sip.SipListener;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionAlreadyExistsException;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.TransactionUnavailableException;
import javax.sip.TransportNotSupportedException;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;

/**
 * Connector that creates a sip stack to handle requests/responses. Implements
 * the SipListener interface to interact with the SIP stack
 *
 * @author ssatyana
 */
public class Connector implements SipListener {

    static final Logger logger = Logger.getLogger(Connector.class.getName());

    // Objects used to communicate to the JAIN SIP API.
    SipFactory sipFactory;          // Used to access the SIP API.
    SipStack sipStack;              // The SIP stack.
    SipProvider sipProvider;        // Used to send SIP messages.
    MessageFactory messageFactory;  // Used to create SIP message factory.
    HeaderFactory headerFactory;    // Used to create SIP headers.
    AddressFactory addressFactory;  // Used to create SIP address factory.
    ListeningPoint listeningPoint;  // SIP listening IP address/port.

    //Objects keeping local configuration.
    String protocol = "udp";        // The local protocol (UDP).
    static private Map<String, Call> callMap = new HashMap<>();
    ClientTransaction clientTransaction = null;
    public static String responseMessage;
    private List<Call> waitCallList = new ArrayList();
    private Map<String, Call> activeCallMap = new HashMap<>();

    /**
     * Creates the sip stack, sip provider and factories for
     * address,header,messages.
     *
     * @param myIpAddress
     * @param myPort
     */
    public Connector(String myIpAddress, int myPort) {
        sipFactory = SipFactory.getInstance();
        logger.log(Level.INFO, "Getting a sip instance {0}", sipFactory);
        //System.out.println("Getting a sip instance" + sipFactory);
        sipFactory.setPathName("gov.nist"); // denotes the SIP stack

        Properties properties = new Properties();
        properties.setProperty("javax.sip.STACK_NAME", "SipCall");
        properties.setProperty("javax.sip.IP_ADDRESS", myIpAddress);
        properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");

        properties.setProperty("gov.nist.javax.sip.SERVER_LOG", "SipCall.txt");
        properties.setProperty("gov.nist.javax.sip.DEBUG_LOG", "SipCall.log");

        try {
            // Creating a sip stack
            sipStack = sipFactory.createSipStack(properties);
            logger.log(Level.INFO, "SipStack Created {0}", sipStack);
            //System.out.println(timeStamp() + "sipStack created -> " + sipStack);

            // Sip provider with listening point
            ListeningPoint lp = sipStack.createListeningPoint(myIpAddress, myPort, "udp");
            sipProvider = sipStack.createSipProvider(lp);
            logger.log(Level.INFO, "SipProvider Created {0}", sipProvider);
            //System.out.println(timeStamp() + "sipProvider created -> " + sipProvider);

            SipListener sipListener = this;
            sipProvider.addSipListener(sipListener);

            this.headerFactory = sipFactory.createHeaderFactory();
            this.messageFactory = sipFactory.createMessageFactory();
            this.addressFactory = sipFactory.createAddressFactory();
        } catch (PeerUnavailableException | TransportNotSupportedException | InvalidArgumentException | ObjectInUseException | TooManyListenersException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    private String timeStamp() {
        return new SimpleDateFormat("[HH:mm:ss.SSS] ").format(Calendar.getInstance().getTime());
    }

    /**
     * Processes a Request received on a SipProvider upon which this SipListener
     * is registered.
     *
     * @param requestEvent
     */
    @Override
    public void processRequest(RequestEvent requestEvent) {
        Request request = requestEvent.getRequest();
        ServerTransaction serverTransaction = requestEvent.getServerTransaction();
        try {
            if (serverTransaction == null) {
                serverTransaction = sipProvider.getNewServerTransaction(request);
            }
        } catch (TransactionAlreadyExistsException | TransactionUnavailableException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        Call call;
        String CallId = ((CallIdHeader) request.getHeader(CallIdHeader.NAME)).getCallId();
        switch (request.getMethod()) {
            case Request.INVITE:
                logger.log(Level.FINE, "INVITE RECIEVED {0}", request);
                System.out.println(timeStamp() + "INVITE RECIEVED -> " + request);
                if (waitCallList.size() > 0) {
                    for (Call c1 : waitCallList) {
                        System.out.println(((CallIdHeader) request.getHeader(CallIdHeader.NAME)).getCallId());
                        call = activeCallMap.get(CallId);
                        if (call != null) {
                            // reinvite
                            c1.setServerTransaction(serverTransaction);
                            c1.handleStackRequest(requestEvent);
                        } else {
                            c1.setInviteRequest(request);
                            c1.setServerTransaction(serverTransaction);
                            activeCallMap.put(CallId, c1);
                            c1.handleStackRequest(requestEvent);
                        }
                    }
                } else {
                    // send 481
                }
                break;
            case Request.OPTIONS:
                System.out.println(timeStamp() + "OPTIONS RECIEVED -> " + request);
                if (waitCallList.size() > 0) {
                    for (Call c1 : waitCallList) {
                        c1.setServerTransaction(serverTransaction);
                        c1.handleStackRequest(requestEvent);
                    }
                }
                break;
            case Request.INFO:
                System.out.println(timeStamp() + "INFO RECIEVED -> " + request);
                call = callMap.get(requestEvent.getDialog().getCallId().getCallId());
                call.setServerTransaction(serverTransaction);
                call.handleStackRequest(requestEvent);
                break;
            case Request.ACK:
                System.out.println(timeStamp() + "ACK RECIEVED -> " + request);
                call = activeCallMap.get(requestEvent.getDialog().getCallId().getCallId());
                call.setDialog(requestEvent.getDialog());
                call.setServerTransaction(serverTransaction);
                call.handleStackRequest(requestEvent);
                break;
            case Request.BYE:
                System.out.println(timeStamp() + "BYE RECIEVED -> " + request);
                call = activeCallMap.get(requestEvent.getDialog().getCallId().getCallId());
                if (call != null) {
                    call.setServerTransaction(serverTransaction);
                    call.setCallId(requestEvent.getDialog().getCallId().getCallId());
                    call.handleStackRequest(requestEvent);
                }
                break;
            case Request.CANCEL:
                System.out.println(timeStamp() + "CANCEL RECIEVED -> " + request);
                call = callMap.get(requestEvent.getDialog().getCallId().getCallId());

                call.setServerTransaction(serverTransaction);
                call.handleStackRequest(requestEvent);
                break;
        }
    }

    /**
     * Processes a Response received on a SipProvider upon which this
     * SipListener is registered.
     *
     * @param responseEvent
     */
    @Override
    public void processResponse(ResponseEvent responseEvent) {
        Response response = responseEvent.getResponse();
        CSeqHeader cSeq = (CSeqHeader) response.getHeader(CSeq.NAME);
        Dialog dialog = responseEvent.getDialog();
        Call call = callMap.get(dialog.getCallId().getCallId());
        call.setClientTransaction(responseEvent.getClientTransaction());
        switch (response.getStatusCode()) {
            case Response.OK:
                switch (cSeq.getMethod()) {
                    case Request.INVITE:
                        System.out.println(timeStamp() + "RESPONSE 200OK FOR INVITE -> " + responseEvent.getResponse());
                        call.setDialog(dialog);
                        call = activeCallMap.get(dialog.getCallId().getCallId());
                        if (call != null) {
                            call.setDialog(dialog);
                            call.handleStackResponse(response, cSeq, dialog);
                        }
                        break;
                    case Request.OPTIONS:
                        break;
                    case Request.INFO:
                        System.out.println(timeStamp() + "RESPONSE 200OK FOR INFO -> " + responseEvent.getResponse());
                        call = activeCallMap.get(dialog.getCallId().getCallId());
                        if (call != null) {
                            call.handleStackResponse(response, cSeq, dialog);

                        }
                        break;
                    case Request.BYE:
                        System.out.println(timeStamp() + "RESPONSE 200OK FOR BYE -> " + responseEvent.getResponse());
                        call = activeCallMap.get(dialog.getCallId().getCallId());
                        if (call != null) {
                            call.handleStackResponse(response, cSeq, dialog);

                        }
                        activeCallMap.remove(dialog.getCallId().getCallId());
                        System.out.println(timeStamp() + "hashmap size -> " + activeCallMap.size());
                        break;
                    case Request.CANCEL:
                        System.out.println(timeStamp() + "RESPONSE 200OK FOR CANCEL -> " + responseEvent.getResponse());
                        callMap.remove(dialog.getCallId().getCallId());
                        System.out.println(timeStamp() + "hashmap size -> " + callMap.size());
                        break;
                }
                break;
            case Response.TRYING:
                System.out.println(timeStamp() + "RESPONSE 100 TRYING RECIEVED -> " + responseEvent.getResponse());
                call.handleStackResponse(response, cSeq, dialog);
                break;
            case Response.RINGING:
                System.out.println(timeStamp() + "RESPONSE 180 RINGING RECIEVED -> " + responseEvent.getResponse());
                call = activeCallMap.get(dialog.getCallId().getCallId());
                if (call != null) {
                    call.handleStackResponse(response, cSeq, dialog);
                }
                break;
            case Response.BUSY_HERE:
                System.out.println(timeStamp() + "RESPONSE BUSY HERE RECIEVED -> " + responseEvent.getResponse());
                break;
            case Response.DECLINE:
                System.out.println(timeStamp() + "RESPONSE DECLINE RECIEVED -> " + responseEvent.getResponse());
                callMap.remove(dialog.getCallId().getCallId());
                System.out.println(timeStamp() + "hashmap size -> " + callMap.size());
                break;
            case Response.REQUEST_TERMINATED:
                System.out.println(timeStamp() + "RESPONSE REQUEST TERMINATED RECIEVED -> " + responseEvent.getResponse());
                call.handleStackResponse(response, cSeq, dialog);
                break;
        }
    }

    /**
     * Registers the created call. Maintains a map to track all the calls.
     *
     * @param call
     */
    public void register(Call call) {
        if (call != null) {
            callMap.put(call.getCallId(), call);
            System.out.println("Registered: Contents of the call map" + callMap);
        }
    }

    /**
     * Methods used to send the requests. Ex: INVITE,INFO,BYE,CANCEL, etc.
     *
     * @param request
     * @param call
     */
    public void sendRequest(Request request, Call call) {
        System.out.println("SEND " + request.getMethod() + " REQUEST -> " + request);
        try {
            Dialog dialog = call.getDialog();
            clientTransaction = sipProvider.getNewClientTransaction(request);
            call.setClientTransaction(clientTransaction);
            if (dialog != null) {
                dialog.sendRequest(clientTransaction);
            } else {
                clientTransaction.sendRequest();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Sends an ACK request.
     *
     * @param ackRequest
     * @param dialog
     */
    public void sendAck(Request ackRequest, Dialog dialog) {
        Call call = callMap.get(dialog.getCallId().getCallId());
        try {
            dialog.sendAck(ackRequest);
            System.out.println("SEND ACK REQUEST -> " + ackRequest);
        } catch (SipException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        call.setRemoteTag(call.getDialog().getRemoteTag());
        System.out.println("REMOTE TAG -> " + call.getDialog().getRemoteTag());
    }

    /**
     * Method used to send responses. Ex: 200OK,TRYING,RINGING, etc.
     *
     * @param response
     * @param call
     */
    public void sendResponse(Response response, Call call) {
        ServerTransaction st = call.getServerTransaction();
        CSeqHeader cSeq = (CSeqHeader) response.getHeader(CSeq.NAME);
        if (st != null) {
            try {
                switch (response.getStatusCode()) {
                    case Response.OK:
                        switch (cSeq.getMethod()) {
                            case Request.BYE:
                                System.out.println(timeStamp() + "Received Bye, sending OK");
                                System.out.println("200 OK for BYE REQUEST -> " + response);
                                st.sendResponse(response);
                                activeCallMap.remove(call.getCallId());
                                break;
                            case Request.INFO:
                                System.out.println(timeStamp() + "Received Info, sending OK");
                                System.out.println("200 OK for INFO REQUEST -> " + response);
                                st.sendResponse(response);
                                break;
                            case Request.CANCEL:
                                System.out.println(timeStamp() + "Received Cancel, sending OK");
                                System.out.println("200 OK for CANCEL REQUEST -> " + response);
                                st.sendResponse(response);
                                break;
                            case Request.INVITE:
                                System.out.println(timeStamp() + "Sending 200 OK for invite");
                                System.out.println("200 OK for INVITE REQUEST -> " + response);
                                st.sendResponse(response);
                                break;
                            case Request.OPTIONS:
                                System.out.println(timeStamp() + "Sending 200 OK for options");
                                System.out.println("200 OK for OPTIONS REQUEST -> " + response);
                                st.sendResponse(response);
                                break;
                        }
                        break;
                    case Response.TRYING:
                        System.out.println("SENT 100 TRYING RESPONSE -> " + response);
                        st.sendResponse(response);
                        break;
                    case Response.RINGING:
                        System.out.println("SENT 180 RINGING RESPONSE -> " + response);
                        st.sendResponse(response);
                        break;
                }
            } catch (SipException | InvalidArgumentException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    /**
     * Sends an ACK for request termination.
     *
     * @param ackRequest
     * @param dialog
     */
    public void sendTerminationAck(Request ackRequest, Dialog dialog) {
        try {
            dialog.sendAck(ackRequest);
            System.out.println(" TERMINATION ACK -> " + ackRequest);
        } catch (SipException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        callMap.remove(dialog.getCallId().getCallId());
        System.out.println(timeStamp() + "hashmap size -> " + callMap.size());
    }

    /**
     * Processes a retransmit or expiration Timeout of an underlying Transaction
     * handled by this SipListener.
     * <p>
     * This Event notifies the application that a retransmission or transaction
     * Timer expired in the SipProvider's transaction state machine. The
     * TimeoutEvent encapsulates the specific timeout type and the transaction
     * identifier either client or server upon which the timeout occurred.
     * </p>
     *
     * @param te. The timeout event.
     */
    @Override
    public void processTimeout(TimeoutEvent te) {
        System.out.println(timeStamp() + "-> Process timeout");
        System.out.println("Type of timeout -> " + te.getTimeout().getValue());
    }

    /**
     * Process an asynchronously reported IO Exception.
     * <p>
     * Asynchronous IO Exceptions may occur as a result of errors during
     * retransmission of requests. The transaction state machine requires to
     * report IO Exceptions to the application immediately (according to RFC
     * 3261). This method enables an implementation to propagate the
     * asynchronous handling of IO Exceptions to the application.
     * </p>
     *
     * @param ioee. The IOException event.
     */
    @Override
    public void processIOException(IOExceptionEvent ioee) {
        System.out.println(timeStamp() + "-> IO Exception");
    }

    /**
     * Process an asynchronously reported TransactionTerminatedEvent.
     * <p>
     * When a transaction transitions to the Terminated state, the stack keeps
     * no further records of the transaction. This notification can be used by
     * applications to clean up any auxiliary data that is being maintained for
     * the given transaction.
     * </p>
     *
     * @param tte. The transaction terminated event.
     */
    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent tte) {
        System.out.println(timeStamp() + "-> Transaction Terminated");
    }

    /**
     * Process an asynchronously reported DialogTerminatedEvent.
     * <p>
     * When a dialog transitions to the Terminated state, the stack keeps no
     * further records of the dialog. This notification can be used by
     * applications to clean up any auxiliary data that is being maintained for
     * the given dialog. A dialog transitions to the "terminated" state when it
     * is completed and ready for garbage collection.
     * </p>
     *
     * @param dte. The dialog terminated event.
     */
    @Override
    public void processDialogTerminated(DialogTerminatedEvent dte) {
        System.out.println(timeStamp() + "-> Dialog Terminated");
    }

    /**
     * Returns the address factory created using sipFactory.
     *
     * @return addressFactory.
     */
    public AddressFactory getAddressFactory() {
        return this.addressFactory;
    }

    /**
     * Returns the message factory created using sipFactory.
     *
     * @return messageFactory.
     */
    public MessageFactory getMessageFactory() {
        return this.messageFactory;
    }

    /**
     * Returns the header factory created using sipFactory.
     *
     * @return headerFactory.
     */
    public HeaderFactory getHeaderFactory() {
        return this.headerFactory;
    }

    public void addToWaitList(Call call) {
        waitCallList.add(call);
    }

    public void addToActiveMap(String id, Call call) {
        activeCallMap.put(id, call);
    }
}
