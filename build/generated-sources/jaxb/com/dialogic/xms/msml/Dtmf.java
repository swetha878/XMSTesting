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
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="pattern" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{}sendType"/>
 *                 &lt;attribute name="digits" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="format">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="moml+digits"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="iterate" type="{}iterate.datatype" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="detect" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{}sendType"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="noinput" type="{}iterateSendType" minOccurs="0"/>
 *         &lt;element name="nomatch" type="{}iterateSendType" minOccurs="0"/>
 *         &lt;element name="dtmfexit" minOccurs="0">
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
 *         &lt;element ref="{}play" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cleardb" type="{}boolean.datatype" default="true" />
 *       &lt;attribute name="fdt" type="{}posDuration.datatype" default="0s" />
 *       &lt;attribute name="idt" type="{}posDuration.datatype" default="0s" />
 *       &lt;attribute name="edt" type="{}posDuration.datatype" default="0s" />
 *       &lt;attribute name="starttimer" type="{}boolean.datatype" default="false" />
 *       &lt;attribute name="iterate" type="{}iterate.datatype" default="1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pattern",
    "detect",
    "noinput",
    "nomatch",
    "dtmfexit",
    "send",
    "play"
})
public class Dtmf
    extends PrimitiveType
{

    @XmlElement(required = true)
    protected List<Dtmf.Pattern> pattern;
    protected Dtmf.Detect detect;
    protected IterateSendType noinput;
    protected IterateSendType nomatch;
    protected Dtmf.Dtmfexit dtmfexit;
    protected Dtmf.Send send;
    protected Play play;
    @XmlAttribute(name = "cleardb")
    protected BooleanDatatype cleardb;
    @XmlAttribute(name = "fdt")
    protected String fdt;
    @XmlAttribute(name = "idt")
    protected String idt;
    @XmlAttribute(name = "edt")
    protected String edt;
    @XmlAttribute(name = "starttimer")
    protected BooleanDatatype starttimer;
    @XmlAttribute(name = "iterate")
    protected String iterate;

    /**
     * Gets the value of the pattern property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pattern property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPattern().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dtmf.Pattern }
     * 
     * 
     */
    public List<Dtmf.Pattern> getPattern() {
        if (pattern == null) {
            pattern = new ArrayList<Dtmf.Pattern>();
        }
        return this.pattern;
    }

    /**
     * Gets the value of the detect property.
     * 
     * @return
     *     possible object is
     *     {@link Dtmf.Detect }
     *     
     */
    public Dtmf.Detect getDetect() {
        return detect;
    }

    /**
     * Sets the value of the detect property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dtmf.Detect }
     *     
     */
    public void setDetect(Dtmf.Detect value) {
        this.detect = value;
    }

    /**
     * Gets the value of the noinput property.
     * 
     * @return
     *     possible object is
     *     {@link IterateSendType }
     *     
     */
    public IterateSendType getNoinput() {
        return noinput;
    }

    /**
     * Sets the value of the noinput property.
     * 
     * @param value
     *     allowed object is
     *     {@link IterateSendType }
     *     
     */
    public void setNoinput(IterateSendType value) {
        this.noinput = value;
    }

    /**
     * Gets the value of the nomatch property.
     * 
     * @return
     *     possible object is
     *     {@link IterateSendType }
     *     
     */
    public IterateSendType getNomatch() {
        return nomatch;
    }

    /**
     * Sets the value of the nomatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link IterateSendType }
     *     
     */
    public void setNomatch(IterateSendType value) {
        this.nomatch = value;
    }

    /**
     * Gets the value of the dtmfexit property.
     * 
     * @return
     *     possible object is
     *     {@link Dtmf.Dtmfexit }
     *     
     */
    public Dtmf.Dtmfexit getDtmfexit() {
        return dtmfexit;
    }

    /**
     * Sets the value of the dtmfexit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dtmf.Dtmfexit }
     *     
     */
    public void setDtmfexit(Dtmf.Dtmfexit value) {
        this.dtmfexit = value;
    }

    /**
     * Gets the value of the send property.
     * 
     * @return
     *     possible object is
     *     {@link Dtmf.Send }
     *     
     */
    public Dtmf.Send getSend() {
        return send;
    }

    /**
     * Sets the value of the send property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dtmf.Send }
     *     
     */
    public void setSend(Dtmf.Send value) {
        this.send = value;
    }

    /**
     * Gets the value of the play property.
     * 
     * @return
     *     possible object is
     *     {@link Play }
     *     
     */
    public Play getPlay() {
        return play;
    }

    /**
     * Sets the value of the play property.
     * 
     * @param value
     *     allowed object is
     *     {@link Play }
     *     
     */
    public void setPlay(Play value) {
        this.play = value;
    }

    /**
     * Gets the value of the cleardb property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanDatatype }
     *     
     */
    public BooleanDatatype getCleardb() {
        if (cleardb == null) {
            return BooleanDatatype.TRUE;
        } else {
            return cleardb;
        }
    }

    /**
     * Sets the value of the cleardb property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanDatatype }
     *     
     */
    public void setCleardb(BooleanDatatype value) {
        this.cleardb = value;
    }

    /**
     * Gets the value of the fdt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdt() {
        if (fdt == null) {
            return "0s";
        } else {
            return fdt;
        }
    }

    /**
     * Sets the value of the fdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdt(String value) {
        this.fdt = value;
    }

    /**
     * Gets the value of the idt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdt() {
        if (idt == null) {
            return "0s";
        } else {
            return idt;
        }
    }

    /**
     * Sets the value of the idt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdt(String value) {
        this.idt = value;
    }

    /**
     * Gets the value of the edt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdt() {
        if (edt == null) {
            return "0s";
        } else {
            return edt;
        }
    }

    /**
     * Sets the value of the edt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdt(String value) {
        this.edt = value;
    }

    /**
     * Gets the value of the starttimer property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanDatatype }
     *     
     */
    public BooleanDatatype getStarttimer() {
        if (starttimer == null) {
            return BooleanDatatype.FALSE;
        } else {
            return starttimer;
        }
    }

    /**
     * Sets the value of the starttimer property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanDatatype }
     *     
     */
    public void setStarttimer(BooleanDatatype value) {
        this.starttimer = value;
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
    public static class Detect {

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
    public static class Dtmfexit {

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
     *       &lt;group ref="{}sendType"/>
     *       &lt;attribute name="digits" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="format">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="moml+digits"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="iterate" type="{}iterate.datatype" />
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
    public static class Pattern {

        protected List<com.dialogic.xms.msml.Send> send;
        protected ExitType exit;
        protected ExitType disconnect;
        @XmlAttribute(name = "digits", required = true)
        protected String digits;
        @XmlAttribute(name = "format")
        protected String format;
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
         * Gets the value of the format property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFormat() {
            return format;
        }

        /**
         * Sets the value of the format property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFormat(String value) {
            this.format = value;
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
            return iterate;
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
