package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class NamespaceDecl {
    boolean declared;
    final String dummyPrefix;
    NamespaceDecl next;
    String prefix;
    boolean requirePrefix;
    final char uniqueId;
    final String uri;

    NamespaceDecl(char c, String str, String str2, boolean z) {
        this.dummyPrefix = new StringBuilder(2).append((char) 0).append(c).toString();
        this.uri = str;
        this.prefix = str2;
        this.requirePrefix = z;
        this.uniqueId = c;
    }
}
