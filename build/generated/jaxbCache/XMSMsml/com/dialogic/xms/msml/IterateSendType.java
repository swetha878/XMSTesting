//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.22 at 04:30:21 PM EDT 
//


package com.dialogic.xms.msml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for iterateSendType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="iterateSendType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;group ref="{}sendType"/>
 *       &lt;attribute name="iterate" type="{}iterate.datatype" default="1" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "iterateSendType", propOrder = {
    "send",
    "exit",
    "disconnect"
})
public class IterateSendType {

    protected List<Send> send;
    protected ExitType exit;
    protected ExitType disconnect;
    @XmlAttribute(name = "iterate")
    protected String iterate;

    /**
     * Gets the value of the send property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the send property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSend().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Send }
     * 
     * 
     */
    public List<Send> getSend() {
        if (send == null) {
            send = new ArrayList<Send>();
        }
        return this.send;
    }

    /**
     * Gets the value of the exit property.
     * 
     * @return
     *     possible object is
     *     {@link ExitType }
     *     
     */
    public ExitType getExit() {
        return exit;
    }

    /**
     * Sets the value of the exit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExitType }
     *     
     */
    public void setExit(ExitType value) {
        this.exit = value;
    }

    /**
     * Gets the value of the disconnect property.
     * 
     * @return
     *     possible object is
     *     {@link ExitType }
     *     
     */
    public ExitType getDisconnect() {
        return disconnect;
    }

    /**
     * Sets the value of the disconnect property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExitType }
     *     
     */
    public void setDisconnect(ExitType value) {
        this.disconnect = value;
    }

    /**
     * Gets the value of the iterate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIterate() {
        if (iterate == null) {
            return "1";
        } else {
            return iterate;
        }
    }

    /**
     * Sets the value of the iterate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIterate(String value) {
        this.iterate = value;
    }

}
