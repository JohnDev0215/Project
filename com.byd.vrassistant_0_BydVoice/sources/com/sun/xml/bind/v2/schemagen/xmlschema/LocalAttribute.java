package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("attribute")
public interface LocalAttribute extends Annotated, AttributeType, FixedOrDefault, TypedXmlWriter {
    @XmlAttribute
    LocalAttribute form(String str);

    @XmlAttribute
    LocalAttribute name(String str);

    @XmlAttribute
    LocalAttribute ref(QName qName);

    @XmlAttribute
    LocalAttribute use(String str);
}
