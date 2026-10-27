package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("list")
public interface List extends Annotated, SimpleTypeHost, TypedXmlWriter {
    @XmlAttribute
    List itemType(QName qName);
}
