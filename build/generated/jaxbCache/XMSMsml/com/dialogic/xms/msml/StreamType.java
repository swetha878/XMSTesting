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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for streamType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="streamType">
 *   &lt;complexContent>
 *     &lt;extension base="{}basicStreamType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="gain">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="amt" type="{}gainAmt.datatype" />
 *                 &lt;attribute name="agc" type="{}boolean.datatype" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="clamp">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="dtmf" type="{}boolean.datatype" />
 *                 &lt;attribute name="tone" type="{}boolean.datatype" fixed="false" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *       &lt;attribute name="preferred">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="true"/>
 *             &lt;enumeration value="false"/>
 *             &lt;enumeration value="true_enhanced"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute ref="{http://www.dialogic.com/DialogicTypes}conf_party_type"/>
 *       &lt;attribute ref="{http://www.dialogic.com/DialogicTypes}echo_cancel"/>
 *       &lt;attribute name="display" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "streamType", propOrder = {
    "gainOrClamp"
})
public class StreamType
    extends BasicStreamType
{

    @XmlElements({
        @XmlElement(name = "gain", type = StreamType.Gain.class),
        @XmlElement(name = "clamp", type = StreamType.Clamp.class)
    })
    protected List<Object> gainOrClamp;
    @XmlAttribute(name = "preferred")
    protected String preferred;
    @XmlAttribute(name = "conf_party_type", namespace = "http://www.dialogic.com/DialogicTypes")
    protected String confPartyType;
    @XmlAttribute(name = "echo_cancel", namespace = "http://www.dialogic.com/DialogicTypes")
    protected String echoCancel;
    @XmlAttribute(name = "display")
    protected String display;

    /**
     * Gets the value of the gainOrClamp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gainOrClamp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGainOrClamp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StreamType.Gain }
     * {@link StreamType.Clamp }
     * 
     * 
     */
    public List<Object> getGainOrClamp() {
        if (gainOrClamp == null) {
            gainOrClamp = new ArrayList<Object>();
        }
        return this.gainOrClamp;
    }

    /**
     * Gets the value of the preferred property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferred() {
        return preferred;
    }

    /**
     * Sets the value of the preferred property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferred(String value) {
        this.preferred = value;
    }

    /**
     * Gets the value of the confPartyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfPartyType() {
        if (confPartyType == null) {
            return "standard";
        } else {
            return confPartyType;
        }
    }

    /**
     * Sets the value of the confPartyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfPartyType(String value) {
        this.confPartyType = value;
    }

    /**
     * Gets the value of the echoCancel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEchoCancel() {
        if (echoCancel == null) {
            return "disable";
        } else {
            return echoCancel;
        }
    }

    /**
     * Sets the value of the echoCancel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEchoCancel(String value) {
        this.echoCancel = value;
    }

    /**
     * Gets the value of the display property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplay() {
        return display;
    }

    /**
     * Sets the value of the display property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplay(String value) {
        this.display = value;
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
     *       &lt;attribute name="dtmf" type="{}boolean.datatype" />
     *       &lt;attribute name="tone" type="{}boolean.datatype" fixed="false" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Clamp {

        @XmlAttribute(name = "dtmf")
        protected BooleanDatatype dtmf;
        @XmlAttribute(name = "tone")
        protected BooleanDatatype tone;

        /**
         * Gets the value of the dtmf property.
         * 
         * @return
         *     possible object is
         *     {@link BooleanDatatype }
         *     
         */
        public BooleanDatatype getDtmf() {
            return dtmf;
        }

        /**
         * Sets the value of the dtmf property.
         * 
         * @param value
         *     allowed object is
         *     {@link BooleanDatatype }
         *     
         */
        public void setDtmf(BooleanDatatype value) {
            this.dtmf = value;
        }

        /**
         * Gets the value of the tone property.
         * 
         * @return
         *     possible object is
         *     {@link BooleanDatatype }
         *     
         */
        public BooleanDatatype getTone() {
            if (tone == null) {
                return BooleanDatatype.FALSE;
            } else {
                return tone;
            }
        }

        /**
         * Sets the value of the tone property.
         * 
         * @param value
         *     allowed object is
         *     {@link BooleanDatatype }
         *     
         */
        public void setTone(BooleanDatatype value) {
            this.tone = value;
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
     *       &lt;attribute name="amt" type="{}gainAmt.datatype" />
     *       &lt;attribute name="agc" type="{}boolean.datatype" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Gain {

        @XmlAttribute(name = "amt")
        protected String amt;
        @XmlAttribute(name = "agc")
        protected BooleanDatatype agc;

        /**
         * Gets the value of the amt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmt() {
            return amt;
        }

        /**
         * Sets the value of the amt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmt(String value) {
            this.amt = value;
        }

        /**
         * Gets the value of the agc property.
         * 
         * @return
         *     possible object is
         *     {@link BooleanDatatype }
         *     
         */
        public BooleanDatatype getAgc() {
            return agc;
        }

        /**
         * Sets the value of the agc property.
         * 
         * @param value
         *     allowed object is
         *     {@link BooleanDatatype }
         *     
         */
        public void setAgc(BooleanDatatype value) {
            this.agc = value;
        }

    }

}
