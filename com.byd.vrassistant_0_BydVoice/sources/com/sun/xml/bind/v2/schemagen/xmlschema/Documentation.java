package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("documentation")
public interface Documentation extends TypedXmlWriter {
    @XmlAttribute(ns = "http://www.w3.org/XML/1998/namespace")
    Documentation lang(String str);

    @XmlAttribute
    Documentation source(String str);
}
