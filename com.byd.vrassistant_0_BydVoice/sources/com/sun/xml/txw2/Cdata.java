package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class Cdata extends Text {
    Cdata(Document document, NamespaceResolver namespaceResolver, Object obj) {
        super(document, namespaceResolver, obj);
    }

    @Override // com.sun.xml.txw2.Content
    void accept(ContentVisitor contentVisitor) {
        contentVisitor.onCdata(this.buffer);
    }
}
