package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("element")
public interface LocalElement extends Element, Occurs, TypedXmlWriter {
    @XmlAttribute
    LocalElement form(String str);

    @XmlAttribute
    LocalElement name(String str);

    @XmlAttribute
    LocalElement ref(QName qName);
}
