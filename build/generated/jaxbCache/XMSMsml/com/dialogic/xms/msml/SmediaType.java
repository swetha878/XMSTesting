//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.01 at 05:26:52 PM EDT 
//


package com.dialogic.xms.msml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for smediaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="smediaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="iterate" type="{}iterate.datatype" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smediaType")
@XmlSeeAlso({
    Var.class
})
public class SmediaType {

    @XmlAttribute(name = "iterate")
    protected String iterate;

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
