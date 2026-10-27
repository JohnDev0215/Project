package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class Attribute {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    final String localName;
    Attribute next;
    final String nsUri;
    final StringBuilder value = new StringBuilder();

    Attribute(String str, String str2) {
        this.nsUri = str;
        this.localName = str2;
    }

    boolean hasName(String str, String str2) {
        return this.localName.equals(str2) && this.nsUri.equals(str);
    }
}
