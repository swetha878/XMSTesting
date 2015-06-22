//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.22 at 04:30:21 PM EDT 
//


package com.dialogic.xms.msml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{}play" minOccurs="0"/>
 *         &lt;element name="recordexit" minOccurs="0">
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
 *       &lt;attribute name="append" type="{}boolean.datatype" default="false" />
 *       &lt;attribute name="beep" type="{}boolean.datatype" default="false" />
 *       &lt;attribute name="dest" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="audiodest" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="videodest" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="format" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="audiosamplerate" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="audiosamplesize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="profile">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="level">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="imagewidth" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="imageheight" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="maxbitrate" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="framerate" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *       &lt;attribute name="maxtime" type="{}posDuration.datatype" />
 *       &lt;attribute ref="{http://www.dialogic.com/DialogicTypes}mintime"/>
 *       &lt;attribute name="initial" default="create">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="create"/>
 *             &lt;enumeration value="suspend"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="prespeech" type="{}posDuration.datatype" default="0s" />
 *       &lt;attribute name="postspeech" type="{}posDuration.datatype" default="0s" />
 *       &lt;attribute name="termkey">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="[0-9#*ABCD]"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "play",
    "recordexit",
    "send"
})
public class Record
    extends PrimitiveType
{

    protected Play play;
    protected Record.Recordexit recordexit;
    protected Record.Send send;
    @XmlAttribute(name = "append")
    protected BooleanDatatype append;
    @XmlAttribute(name = "beep")
    protected BooleanDatatype beep;
    @XmlAttribute(name = "dest")
    @XmlSchemaType(name = "anyURI")
    protected String dest;
    @XmlAttribute(name = "audiodest")
    @XmlSchemaType(name = "anyURI")
    protected String audiodest;
    @XmlAttribute(name = "videodest")
    @XmlSchemaType(name = "anyURI")
    protected String videodest;
    @XmlAttribute(name = "format", required = true)
    protected String format;
    @XmlAttribute(name = "audiosamplerate")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger audiosamplerate;
    @XmlAttribute(name = "audiosamplesize")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger audiosamplesize;
    @XmlAttribute(name = "profile")
    protected String profile;
    @XmlAttribute(name = "level")
    protected String level;
    @XmlAttribute(name = "imagewidth")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger imagewidth;
    @XmlAttribute(name = "imageheight")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger imageheight;
    @XmlAttribute(name = "maxbitrate")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxbitrate;
    @XmlAttribute(name = "framerate")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger framerate;
    @XmlAttribute(name = "maxtime")
    protected String maxtime;
    @XmlAttribute(name = "mintime", namespace = "http://www.dialogic.com/DialogicTypes")
    protected String mintime;
    @XmlAttribute(name = "initial")
    protected String initial;
    @XmlAttribute(name = "prespeech")
    protected String prespeech;
    @XmlAttribute(name = "postspeech")
    protected String postspeech;
    @XmlAttribute(name = "termkey")
    protected String termkey;

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
     * Gets the value of the recordexit property.
     * 
     * @return
     *     possible object is
     *     {@link Record.Recordexit }
     *     
     */
    public Record.Recordexit getRecordexit() {
        return recordexit;
    }

    /**
     * Sets the value of the recordexit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Record.Recordexit }
     *     
     */
    public void setRecordexit(Record.Recordexit value) {
        this.recordexit = value;
    }

    /**
     * Gets the value of the send property.
     * 
     * @return
     *     possible object is
     *     {@link Record.Send }
     *     
     */
    public Record.Send getSend() {
        return send;
    }

    /**
     * Sets the value of the send property.
     * 
     * @param value
     *     allowed object is
     *     {@link Record.Send }
     *     
     */
    public void setSend(Record.Send value) {
        this.send = value;
    }

    /**
     * Gets the value of the append property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanDatatype }
     *     
     */
    public BooleanDatatype getAppend() {
        if (append == null) {
            return BooleanDatatype.FALSE;
        } else {
            return append;
        }
    }

    /**
     * Sets the value of the append property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanDatatype }
     *     
     */
    public void setAppend(BooleanDatatype value) {
        this.append = value;
    }

    /**
     * Gets the value of the beep property.
     * 
     * @return
     *     possible object is
     *     {@link BooleanDatatype }
     *     
     */
    public BooleanDatatype getBeep() {
        if (beep == null) {
            return BooleanDatatype.FALSE;
        } else {
            return beep;
        }
    }

    /**
     * Sets the value of the beep property.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanDatatype }
     *     
     */
    public void setBeep(BooleanDatatype value) {
        this.beep = value;
    }

    /**
     * Gets the value of the dest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDest() {
        return dest;
    }

    /**
     * Sets the value of the dest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDest(String value) {
        this.dest = value;
    }

    /**
     * Gets the value of the audiodest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAudiodest() {
        return audiodest;
    }

    /**
     * Sets the value of the audiodest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAudiodest(String value) {
        this.audiodest = value;
    }

    /**
     * Gets the value of the videodest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideodest() {
        return videodest;
    }

    /**
     * Sets the value of the videodest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideodest(String value) {
        this.videodest = value;
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
     * Gets the value of the audiosamplerate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAudiosamplerate() {
        return audiosamplerate;
    }

    /**
     * Sets the value of the audiosamplerate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAudiosamplerate(BigInteger value) {
        this.audiosamplerate = value;
    }

    /**
     * Gets the value of the audiosamplesize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAudiosamplesize() {
        return audiosamplesize;
    }

    /**
     * Sets the value of the audiosamplesize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAudiosamplesize(BigInteger value) {
        this.audiosamplesize = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfile() {
        return profile;
    }

    /**
     * Sets the value of the profile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfile(String value) {
        this.profile = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the imagewidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getImagewidth() {
        return imagewidth;
    }

    /**
     * Sets the value of the imagewidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setImagewidth(BigInteger value) {
        this.imagewidth = value;
    }

    /**
     * Gets the value of the imageheight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getImageheight() {
        return imageheight;
    }

    /**
     * Sets the value of the imageheight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setImageheight(BigInteger value) {
        this.imageheight = value;
    }

    /**
     * Gets the value of the maxbitrate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxbitrate() {
        return maxbitrate;
    }

    /**
     * Sets the value of the maxbitrate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxbitrate(BigInteger value) {
        this.maxbitrate = value;
    }

    /**
     * Gets the value of the framerate property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFramerate() {
        return framerate;
    }

    /**
     * Sets the value of the framerate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFramerate(BigInteger value) {
        this.framerate = value;
    }

    /**
     * Gets the value of the maxtime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxtime() {
        return maxtime;
    }

    /**
     * Sets the value of the maxtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxtime(String value) {
        this.maxtime = value;
    }

    /**
     * Gets the value of the mintime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMintime() {
        if (mintime == null) {
            return "0s";
        } else {
            return mintime;
        }
    }

    /**
     * Sets the value of the mintime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMintime(String value) {
        this.mintime = value;
    }

    /**
     * Gets the value of the initial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitial() {
        if (initial == null) {
            return "create";
        } else {
            return initial;
        }
    }

    /**
     * Sets the value of the initial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitial(String value) {
        this.initial = value;
    }

    /**
     * Gets the value of the prespeech property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrespeech() {
        if (prespeech == null) {
            return "0s";
        } else {
            return prespeech;
        }
    }

    /**
     * Sets the value of the prespeech property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrespeech(String value) {
        this.prespeech = value;
    }

    /**
     * Gets the value of the postspeech property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostspeech() {
        if (postspeech == null) {
            return "0s";
        } else {
            return postspeech;
        }
    }

    /**
     * Sets the value of the postspeech property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostspeech(String value) {
        this.postspeech = value;
    }

    /**
     * Gets the value of the termkey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermkey() {
        return termkey;
    }

    /**
     * Sets the value of the termkey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermkey(String value) {
        this.termkey = value;
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
    public static class Recordexit {

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
