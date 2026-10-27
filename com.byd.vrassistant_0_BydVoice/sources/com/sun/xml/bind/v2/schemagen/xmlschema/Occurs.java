package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

/* JADX INFO: loaded from: classes3.dex */
public interface Occurs extends TypedXmlWriter {
    @XmlAttribute
    Occurs maxOccurs(int i);

    @XmlAttribute
    Occurs maxOccurs(String str);

    @XmlAttribute
    Occurs minOccurs(int i);
}
