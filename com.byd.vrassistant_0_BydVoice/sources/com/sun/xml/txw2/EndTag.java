package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class EndTag extends Content {
    @Override // com.sun.xml.txw2.Content
    boolean concludesPendingStartTag() {
        return true;
    }

    EndTag() {
    }

    @Override // com.sun.xml.txw2.Content
    void accept(ContentVisitor contentVisitor) {
        contentVisitor.onEndTag();
    }
}
