package com.sun.istack;

import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/* JADX INFO: loaded from: classes3.dex */
public class SAXParseException2 extends SAXParseException {
    public SAXParseException2(String str, Locator locator) {
        super(str, locator);
    }

    public SAXParseException2(String str, Locator locator, Exception exc) {
        super(str, locator, exc);
    }

    public SAXParseException2(String str, String str2, String str3, int i, int i2) {
        super(str, str2, str3, i, i2);
    }

    public SAXParseException2(String str, String str2, String str3, int i, int i2, Exception exc) {
        super(str, str2, str3, i, i2, exc);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return getException();
    }
}
