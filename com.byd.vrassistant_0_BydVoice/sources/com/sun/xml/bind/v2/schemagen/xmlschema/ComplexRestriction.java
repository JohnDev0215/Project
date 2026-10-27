package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("restriction")
public interface ComplexRestriction extends Annotated, AttrDecls, TypeDefParticle, TypedXmlWriter {
    @XmlAttribute
    ComplexRestriction base(QName qName);
}
