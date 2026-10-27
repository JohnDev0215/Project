package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("schema")
public interface Schema extends SchemaTop, TypedXmlWriter {
    @XmlElement("import")
    Import _import();

    @XmlElement
    Annotation annotation();

    @XmlAttribute
    Schema attributeFormDefault(String str);

    @XmlAttribute
    Schema blockDefault(String str);

    @XmlAttribute
    Schema blockDefault(String[] strArr);

    @XmlAttribute
    Schema elementFormDefault(String str);

    @XmlAttribute
    Schema finalDefault(String str);

    @XmlAttribute
    Schema finalDefault(String[] strArr);

    @XmlAttribute
    Schema id(String str);

    @XmlAttribute(ns = "http://www.w3.org/XML/1998/namespace")
    Schema lang(String str);

    @XmlAttribute
    Schema targetNamespace(String str);

    @XmlAttribute
    Schema version(String str);
}
