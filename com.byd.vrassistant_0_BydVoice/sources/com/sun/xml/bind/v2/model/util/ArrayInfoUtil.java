package com.sun.xml.bind.v2.model.util;

import com.sun.xml.bind.v2.TODO;
import com.sun.xml.bind.v2.WellKnownNamespace;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public class ArrayInfoUtil {
    private ArrayInfoUtil() {
    }

    public static QName calcArrayTypeName(QName qName) {
        String namespaceURI;
        if (qName.getNamespaceURI().equals(WellKnownNamespace.XML_SCHEMA)) {
            TODO.checkSpec("this URI");
            namespaceURI = "http://jaxb.dev.java.net/array";
        } else {
            namespaceURI = qName.getNamespaceURI();
        }
        return new QName(namespaceURI, qName.getLocalPart() + "Array");
    }
}
