package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
public interface TypeDefParticle extends TypedXmlWriter {
    @XmlElement
    ExplicitGroup all();

    @XmlElement
    ExplicitGroup choice();

    @XmlElement
    ExplicitGroup sequence();
}
