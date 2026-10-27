package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("import")
public interface Import extends Annotated, TypedXmlWriter {
    @XmlAttribute
    Import namespace(String str);

    @XmlAttribute
    Import schemaLocation(String str);
}
