//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.30 at 04:32:58 PM EDT 
//


package com.dialogic.xms.msml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}primitiveType">
 *       &lt;sequence>
 *         &lt;element name="dtmfgenexit" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{}sendType"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="send" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="event" use="required" type="{}momlEvent.datatype" />
 *                 &lt;attribute name="target" use="required" type="{}momlTarget.datatype" />
 *                 &lt;attribute name="namelist" type="{}momlNamelist.datatype" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="level" default="-6">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}nonPositiveInteger">
 *             &lt;maxInclusive value="0"/>
 *             &lt;minInclusive value="-96"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="digits" use="required" type="{}dtmfDigits.datatype" />
 *       &lt;attribute name="dur" type="{}posDuration.datatype" default="100ms" />
 *       &lt;attribute name="interval" type="{}posDuration.datatype" default="100ms" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dtmfgenexit",
    "send"
})
public class Dtmfgen
    extends PrimitiveType
{

    protected Dtmfgen.Dtmfgenexit dtmfgenexit;
    protected Dtmfgen.Send send;
    @XmlAttribute(name = "level")
    protected Integer level;
    @XmlAttribute(name = "digits", required = true)
    protected String digits;
    @XmlAttribute(name = "dur")
    protected String dur;
    @XmlAttribute(name = "interval")
    protected String interval;

    /**
     * Gets the value of the dtmfgenexit property.
     * 
     * @return
     *     possible object is
     *     {@link Dtmfgen.Dtmfgenexit }
     *     
     */
    public Dtmfgen.Dtmfgenexit getDtmfgenexit() {
        return dtmfgenexit;
    }

    /**
     * Sets the value of the dtmfgenexit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dtmfgen.Dtmfgenexit }
     *     
     */
    public void setDtmfgenexit(Dtmfgen.Dtmfgenexit value) {
        this.dtmfgenexit = value;
    }

    /**
     * Gets the value of the send property.
     * 
     * @return
     *     possible object is
     *     {@link Dtmfgen.Send }
     *     
     */
    public Dtmfgen.Send getSend() {
        return send;
    }

    /**
     * Sets the value of the send property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dtmfgen.Send }
     *     
     */
    public void setSend(Dtmfgen.Send value) {
        this.send = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getLevel() {
        if (level == null) {
            return -6;
        } else {
            return level;
        }
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLevel(Integer value) {
        this.level = value;
    }

    /**
     * Gets the value of the digits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDigits() {
        return digits;
    }

    /**
     * Sets the value of the digits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDigits(String value) {
        this.digits = value;
    }

    /**
     * Gets the value of the dur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDur() {
        if (dur == null) {
            return "100ms";
        } else {
            return dur;
        }
    }

    /**
     * Sets the value of the dur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDur(String value) {
        this.dur = value;
    }

    /**
     * Gets the value of the interval property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterval() {
        if (interval == null) {
            return "100ms";
        } else {
            return interval;
        }
    }

    /**
     * Sets the value of the interval property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterval(String value) {
        this.interval = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;group ref="{}sendType"/>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "send",
        "exit",
        "disconnect"
    })
    public static class Dtmfgenexit {

        protected List<com.dialogic.xms.msml.Send> send;
        protected ExitType exit;
        protected ExitType disconnect;

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
         * {@link com.dialogic.xms.msml.Send }
         * 
         * 
         */
        public List<com.dialogic.xms.msml.Send> getSend() {
            if (send == null) {
                send = new ArrayList<com.dialogic.xms.msml.Send>();
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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="event" use="required" type="{}momlEvent.datatype" />
     *       &lt;attribute name="target" use="required" type="{}momlTarget.datatype" />
     *       &lt;attribute name="namelist" type="{}momlNamelist.datatype" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Send {

        @XmlAttribute(name = "event", required = true)
        protected String event;
        @XmlAttribute(name = "target", required = true)
        protected String target;
        @XmlAttribute(name = "namelist")
        protected String namelist;

        /**
         * Gets the value of the event property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEvent() {
            return event;
        }

        /**
         * Sets the value of the event property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEvent(String value) {
            this.event = value;
        }

        /**
         * Gets the value of the target property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTarget() {
            return target;
        }

        /**
         * Sets the value of the target property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTarget(String value) {
            this.target = value;
        }

        /**
         * Gets the value of the namelist property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNamelist() {
            return namelist;
        }

        /**
         * Sets the value of the namelist property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNamelist(String value) {
            this.namelist = value;
        }

    }

}
