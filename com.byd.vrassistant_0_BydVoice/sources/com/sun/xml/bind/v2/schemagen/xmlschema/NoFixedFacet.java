package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

/* JADX INFO: loaded from: classes3.dex */
public interface NoFixedFacet extends Annotated, TypedXmlWriter {
    @XmlAttribute
    NoFixedFacet value(String str);
}
