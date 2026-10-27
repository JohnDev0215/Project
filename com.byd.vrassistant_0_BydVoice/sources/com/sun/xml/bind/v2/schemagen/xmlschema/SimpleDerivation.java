package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
public interface SimpleDerivation extends TypedXmlWriter {
    @XmlElement
    List list();

    @XmlElement
    SimpleRestriction restriction();

    @XmlElement
    Union union();
}
