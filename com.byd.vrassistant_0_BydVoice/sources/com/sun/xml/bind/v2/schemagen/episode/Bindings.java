package com.sun.xml.bind.v2.schemagen.episode;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("bindings")
public interface Bindings extends TypedXmlWriter {
    @XmlElement
    Bindings bindings();

    @XmlElement("class")
    Klass klass();

    @XmlAttribute
    void scd(String str);

    @XmlElement
    SchemaBindings schemaBindings();

    Klass typesafeEnumClass();

    @XmlAttribute
    void version(String str);
}
