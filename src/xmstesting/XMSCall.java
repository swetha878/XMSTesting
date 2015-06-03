/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmstesting;

import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public abstract class XMSCall extends Observable implements Observer {

    private static Logger m_logger = Logger.getLogger(XMSCall.class.getName());
    String type = "msml";
    EventType callState = EventType.IDLE;

    //public XMSPlayOptions PlayOptions = new XMSPlayOptions();
    public Operation play(String fileName) {

        if (type == "msml") {
            MsmlCall msmlCall = new MsmlCall();
        }

        return Operation.SUCCESS;
    }

    public XMSCall(Connector connector) {
        PropertyConfigurator.configure("log4j.properties");

        Call call = new Call(connector);
        call.waitCall();
        //m_state = XMSCallState.NULL;
    }

    @Override
    public void update(Observable o, Object o1) {
        System.out.println("TESTING OBSERVER ->" + o1);
        if (o1.equals(EventType.INCOMING)) {
            setValue(EventType.INCOMING);
        } else if (o1.equals(EventType.CONNECTING)) {
            setValue(EventType.CONNECTING);
        } else if (o1.equals(EventType.CONNECTED)) {

        }
    }

    public void setValue(EventType value) {
        this.callState = value;
        setChanged();
        notifyObservers(this.callState);
    }

    public EventType getValue() {
        return this.callState;
    }

}
