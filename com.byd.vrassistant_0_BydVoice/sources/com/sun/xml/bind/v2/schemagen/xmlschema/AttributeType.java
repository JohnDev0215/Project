package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public interface AttributeType extends SimpleTypeHost, TypedXmlWriter {
    @XmlAttribute
    AttributeType type(QName qName);
}
