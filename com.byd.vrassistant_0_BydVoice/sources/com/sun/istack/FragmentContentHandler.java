package com.sun.istack;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentContentHandler extends XMLFilterImpl {
    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
    }

    @Override // org.xml.sax.helpers.XMLFilterImpl, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
    }

    public FragmentContentHandler() {
    }

    public FragmentContentHandler(XMLReader xMLReader) {
        super(xMLReader);
    }

    public FragmentContentHandler(ContentHandler contentHandler) {
        setContentHandler(contentHandler);
    }
}
