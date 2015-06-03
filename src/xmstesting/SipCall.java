/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmstesting;

import java.util.Map;
import javax.sip.header.CSeqHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.Response;

/**
 *
 * @author ssatyana
 */
public interface SipCall {

    public void makeCall(String dest);

    public void waitCall();

    public void acceptCall();

    public void answerCall();

    public void dropCall();

    public void ackCall(Response response);

    public void play(String content);

    public void record(String content);

    public String getLocalSdp();

    public void setLocalSdp(String content);

    public String getRemoteSdp();

    public void setRemoteSdp(String content);

    public void sendInfo();

    public String getCallId();

    public void setCallId(String callId);

    public String getRemoteTag();

    public void setRemoteTag(String tag);

    public String getFromAddress();

    public void setFromAddress(String frmAddr);

    public String getToAddress();

    public void setToAddress(String toAddr);

    public FromHeader getFromHeader(String adr);

    public void setFromHeader(String user, String address);

    public ToHeader getToHeader(String adr);

    public void setToHeader(String user, String address);

    public ContentTypeHeader getContentTypeHeader(String contentSubType);

    public void setContentTypeHeader(String contentType, String contentSubType);

    public ContactHeader getContactHeader(String adr);

    public void setContactHeader(String user, String address, int port);

    public ViaHeader getViaHeader(String adr);

    public void setViaHeader(String host, int port, String transport, String branch);

    public CSeqHeader getCSeqHeader(String method);

    public void setCSeqHeader(long sequenceNumber, String method);

}
