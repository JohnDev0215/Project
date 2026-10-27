package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
public interface SimpleTypeHost extends TypeHost, TypedXmlWriter {
    @XmlElement
    SimpleType simpleType();
}
