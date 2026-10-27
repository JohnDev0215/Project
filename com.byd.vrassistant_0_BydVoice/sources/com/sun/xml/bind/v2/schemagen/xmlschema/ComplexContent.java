package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("complexContent")
public interface ComplexContent extends Annotated, TypedXmlWriter {
    @XmlElement
    ComplexExtension extension();

    @XmlAttribute
    ComplexContent mixed(boolean z);

    @XmlElement
    ComplexRestriction restriction();
}
