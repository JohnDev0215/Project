package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("complexType")
public interface ComplexType extends Annotated, ComplexTypeModel, TypedXmlWriter {
    @XmlAttribute("abstract")
    ComplexType _abstract(boolean z);

    @XmlAttribute("final")
    ComplexType _final(String str);

    @XmlAttribute("final")
    ComplexType _final(String[] strArr);

    @XmlAttribute
    ComplexType block(String str);

    @XmlAttribute
    ComplexType block(String[] strArr);

    @XmlAttribute
    ComplexType name(String str);
}
