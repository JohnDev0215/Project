package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
abstract class Text extends Content {
    protected final StringBuilder buffer;

    @Override // com.sun.xml.txw2.Content
    boolean concludesPendingStartTag() {
        return false;
    }

    protected Text(Document document, NamespaceResolver namespaceResolver, Object obj) {
        StringBuilder sb = new StringBuilder();
        this.buffer = sb;
        document.writeValue(obj, namespaceResolver, sb);
    }
}
