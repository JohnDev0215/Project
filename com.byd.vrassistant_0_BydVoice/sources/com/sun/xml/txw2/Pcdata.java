package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class Pcdata extends Text {
    Pcdata(Document document, NamespaceResolver namespaceResolver, Object obj) {
        super(document, namespaceResolver, obj);
    }

    @Override // com.sun.xml.txw2.Content
    void accept(ContentVisitor contentVisitor) {
        contentVisitor.onPcdata(this.buffer);
    }
}
