package com.sun.xml.bind.v2.util;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: classes3.dex */
public class FatalAdapter implements ErrorHandler {
    private final ErrorHandler core;

    public FatalAdapter(ErrorHandler errorHandler) {
        this.core = errorHandler;
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException sAXParseException) throws SAXException {
        this.core.warning(sAXParseException);
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException sAXParseException) throws SAXException {
        this.core.fatalError(sAXParseException);
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        this.core.fatalError(sAXParseException);
    }
}
