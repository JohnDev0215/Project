package com.sun.istack;

import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public class SAXException2 extends SAXException {
    public SAXException2(String str) {
        super(str);
    }

    public SAXException2(Exception exc) {
        super(exc);
    }

    public SAXException2(String str, Exception exc) {
        super(str, exc);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return getException();
    }
}
