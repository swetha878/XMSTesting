//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.01 at 05:26:52 PM EDT 
//


package com.dialogic.xms.msml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dialogLanguage.datatype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="dialogLanguage.datatype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="application/moml+xml"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dialogLanguage.datatype")
@XmlEnum
public enum DialogLanguageDatatype {

    @XmlEnumValue("application/moml+xml")
    APPLICATION_MOML_XML("application/moml+xml");
    private final String value;

    DialogLanguageDatatype(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DialogLanguageDatatype fromValue(String v) {
        for (DialogLanguageDatatype c: DialogLanguageDatatype.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
