package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("simpleType")
public interface SimpleType extends Annotated, SimpleDerivation, TypedXmlWriter {
    @XmlAttribute("final")
    SimpleType _final(String str);

    @XmlAttribute("final")
    SimpleType _final(String[] strArr);

    @XmlAttribute
    SimpleType name(String str);
}
