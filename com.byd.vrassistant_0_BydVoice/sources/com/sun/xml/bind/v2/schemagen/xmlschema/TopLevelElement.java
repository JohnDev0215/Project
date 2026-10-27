package com.sun.xml.bind.v2.schemagen.xmlschema;

import com.sun.xml.txw2.TypedXmlWriter;
import com.sun.xml.txw2.annotation.XmlAttribute;
import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
@XmlElement("element")
public interface TopLevelElement extends Element, TypedXmlWriter {
    @XmlAttribute("abstract")
    TopLevelElement _abstract(boolean z);

    @XmlAttribute("final")
    TopLevelElement _final(String str);

    @XmlAttribute("final")
    TopLevelElement _final(String[] strArr);

    @XmlAttribute
    TopLevelElement name(String str);

    @XmlAttribute
    TopLevelElement substitutionGroup(QName qName);
}
