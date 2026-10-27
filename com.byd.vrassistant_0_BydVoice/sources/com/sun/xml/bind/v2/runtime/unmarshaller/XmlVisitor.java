package com.sun.xml.bind.v2.runtime.unmarshaller;

import javax.xml.namespace.NamespaceContext;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public interface XmlVisitor {

    public interface TextPredictor {
        boolean expectText();
    }

    void endDocument() throws SAXException;

    void endElement(TagName tagName) throws SAXException;

    void endPrefixMapping(String str) throws SAXException;

    UnmarshallingContext getContext();

    TextPredictor getPredictor();

    void startDocument(LocatorEx locatorEx, NamespaceContext namespaceContext) throws SAXException;

    void startElement(TagName tagName) throws SAXException;

    void startPrefixMapping(String str, String str2) throws SAXException;

    void text(CharSequence charSequence) throws SAXException;
}
