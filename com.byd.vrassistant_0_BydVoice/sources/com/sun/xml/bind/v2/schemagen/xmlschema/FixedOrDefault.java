package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;

/* JADX INFO: loaded from: classes3.dex */
public interface FixedOrDefault extends TypedXmlWriter {
    @XmlAttribute("default")
    FixedOrDefault _default(String str);

    @XmlAttribute
    FixedOrDefault fixed(String str);
}
