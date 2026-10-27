package com.sun.istack;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: classes3.dex */
public class XMLStreamException2 extends XMLStreamException {
    public XMLStreamException2(String str) {
        super(str);
    }

    public XMLStreamException2(Throwable th) {
        super(th);
    }

    public XMLStreamException2(String str, Throwable th) {
        super(str, th);
    }

    public XMLStreamException2(String str, Location location) {
        super(str, location);
    }

    public XMLStreamException2(String str, Location location, Throwable th) {
        super(str, location, th);
    }

    public Throwable getCause() {
        return getNestedException();
    }
}
