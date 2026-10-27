package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("simpleContent")
public interface SimpleContent extends Annotated, TypedXmlWriter {
    @XmlElement
    SimpleExtension extension();

    @XmlElement
    SimpleRestriction restriction();
}
