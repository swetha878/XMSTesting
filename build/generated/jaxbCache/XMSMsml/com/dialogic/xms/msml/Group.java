//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.26 at 03:30:37 PM EDT 
//


package com.dialogic.xms.msml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{}executeType"/>
 *         &lt;element name="groupexit" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{}sendType"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{}momlID.datatype" />
 *       &lt;attribute name="topology" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="parallel"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "primitive",
    "control",
    "groupexit"
})
public class Group {

    @XmlElementRef(name = "primitive", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends PrimitiveType>> primitive;
    @XmlElementRef(name = "control", type = JAXBElement.class, required = false)
    protected List<JAXBElement<?>> control;
    protected Group.Groupexit groupexit;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "topology", required = true)
    protected String topology;

    /**
     * Gets the value of the primitive property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the primitive property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimitive().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Play }{@code >}
     * {@link JAXBElement }{@code <}{@link Agc }{@code >}
     * {@link JAXBElement }{@code <}{@link Record }{@code >}
     * {@link JAXBElement }{@code <}{@link PrimitiveType }{@code >}
     * {@link JAXBElement }{@code <}{@link Faxdetect }{@code >}
     * {@link JAXBElement }{@code <}{@link Transfer }{@code >}
     * {@link JAXBElement }{@code <}{@link Fileop }{@code >}
     * {@link JAXBElement }{@code <}{@link Gain }{@code >}
     * {@link JAXBElement }{@code <}{@link Collect }{@code >}
     * {@link JAXBElement }{@code <}{@link Dtmf }{@code >}
     * {@link JAXBElement }{@code <}{@link Dtmfgen }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends PrimitiveType>> getPrimitive() {
        if (primitive == null) {
            primitive = new ArrayList<JAXBElement<? extends PrimitiveType>>();
        }
        return this.primitive;
    }

    /**
     * Gets the value of the control property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the control property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getControl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Group }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getControl() {
        if (control == null) {
            control = new ArrayList<JAXBElement<?>>();
        }
        return this.control;
    }

    /**
     * Gets the value of the groupexit property.
     * 
     * @return
     *     possible object is
     *     {@link Group.Groupexit }
     *     
     */
    public Group.Groupexit getGroupexit() {
        return groupexit;
    }

    /**
     * Sets the value of the groupexit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Group.Groupexit }
     *     
     */
    public void setGroupexit(Group.Groupexit value) {
        this.groupexit = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the topology property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopology() {
        return topology;
    }

    /**
     * Sets the value of the topology property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopology(String value) {
        this.topology = value;
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
    public static class Groupexit {

        protected List<Send> send;
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

    }

}
