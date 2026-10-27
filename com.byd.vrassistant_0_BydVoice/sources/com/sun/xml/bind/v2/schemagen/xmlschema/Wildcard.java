package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

/* JADX INFO: loaded from: classes3.dex */
public interface Wildcard extends Annotated, TypedXmlWriter {
    @XmlAttribute
    Wildcard namespace(String str);

    @XmlAttribute
    Wildcard namespace(String[] strArr);

    @XmlAttribute
    Wildcard processContents(String str);
}
