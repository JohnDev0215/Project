package com.sun.xml.bind.api;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: classes3.dex */
public interface ErrorListener extends ErrorHandler {
    @Override // org.xml.sax.ErrorHandler
    void error(SAXParseException sAXParseException);

    @Override // org.xml.sax.ErrorHandler
    void fatalError(SAXParseException sAXParseException);

    void info(SAXParseException sAXParseException);

    @Override // org.xml.sax.ErrorHandler
    void warning(SAXParseException sAXParseException);
}
