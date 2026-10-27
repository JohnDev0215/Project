package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
public interface ComplexTypeModel extends AttrDecls, TypeDefParticle, TypedXmlWriter {
    @XmlElement
    ComplexContent complexContent();

    @XmlAttribute
    ComplexTypeModel mixed(boolean z);

    @XmlElement
    SimpleContent simpleContent();
}
