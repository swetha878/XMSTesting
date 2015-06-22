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
 *       &lt;choice minOccurs="0">
 *         &lt;element name="faxdetectexit">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;group ref="{}sendType"/>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "faxdetectexit"
})
public class Faxdetect
    extends PrimitiveType
{

    protected Faxdetect.Faxdetectexit faxdetectexit;

    /**
     * Gets the value of the faxdetectexit property.
     * 
     * @return
     *     possible object is
     *     {@link Faxdetect.Faxdetectexit }
     *     
     */
    public Faxdetect.Faxdetectexit getFaxdetectexit() {
        return faxdetectexit;
    }

    /**
     * Sets the value of the faxdetectexit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Faxdetect.Faxdetectexit }
     *     
     */
    public void setFaxdetectexit(Faxdetect.Faxdetectexit value) {
        this.faxdetectexit = value;
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
    public static class Faxdetectexit {

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
