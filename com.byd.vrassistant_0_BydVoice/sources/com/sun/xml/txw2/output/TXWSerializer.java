package com.sun.xml.txw2.output;

import com.sun.xml.txw2.TypedXmlWriter;

/* JADX INFO: loaded from: classes3.dex */
public final class TXWSerializer implements XmlSerializer {
    public final TypedXmlWriter txw;

    public TXWSerializer(TypedXmlWriter typedXmlWriter) {
        this.txw = typedXmlWriter;
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void startDocument() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endDocument() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void beginStartTag(String str, String str2, String str3) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeAttribute(String str, String str2, String str3, StringBuilder sb) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void writeXmlns(String str, String str2) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endStartTag(String str, String str2, String str3) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void endTag() {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void text(StringBuilder sb) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void cdata(StringBuilder sb) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void comment(StringBuilder sb) {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.xml.txw2.output.XmlSerializer
    public void flush() {
        throw new UnsupportedOperationException();
    }
}
