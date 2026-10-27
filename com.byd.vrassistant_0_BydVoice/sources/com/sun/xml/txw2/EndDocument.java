package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class EndDocument extends Content {
    @Override // com.sun.xml.txw2.Content
    boolean concludesPendingStartTag() {
        return true;
    }

    EndDocument() {
    }

    @Override // com.sun.xml.txw2.Content
    void accept(ContentVisitor contentVisitor) {
        contentVisitor.onEndDocument();
    }
}
