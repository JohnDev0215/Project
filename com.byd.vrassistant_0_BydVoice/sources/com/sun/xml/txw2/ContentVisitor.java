package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
interface ContentVisitor {
    void onCdata(StringBuilder sb);

    void onComment(StringBuilder sb);

    void onEndDocument();

    void onEndTag();

    void onPcdata(StringBuilder sb);

    void onStartDocument();

    void onStartTag(String str, String str2, Attribute attribute, NamespaceDecl namespaceDecl);
}
