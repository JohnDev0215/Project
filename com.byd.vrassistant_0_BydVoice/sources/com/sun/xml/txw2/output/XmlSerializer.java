package com.sun.xml.txw2.output;

/* JADX INFO: loaded from: classes3.dex */
public interface XmlSerializer {
    void beginStartTag(String str, String str2, String str3);

    void cdata(StringBuilder sb);

    void comment(StringBuilder sb);

    void endDocument();

    void endStartTag(String str, String str2, String str3);

    void endTag();

    void flush();

    void startDocument();

    void text(StringBuilder sb);

    void writeAttribute(String str, String str2, String str3, StringBuilder sb);

    void writeXmlns(String str, String str2);
}
