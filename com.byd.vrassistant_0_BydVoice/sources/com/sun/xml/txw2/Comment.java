package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
final class Comment extends Content {
    private final StringBuilder buffer;

    @Override // com.sun.xml.txw2.Content
    boolean concludesPendingStartTag() {
        return false;
    }

    public Comment(Document document, NamespaceResolver namespaceResolver, Object obj) {
        StringBuilder sb = new StringBuilder();
        this.buffer = sb;
        document.writeValue(obj, namespaceResolver, sb);
    }

    @Override // com.sun.xml.txw2.Content
    void accept(ContentVisitor contentVisitor) {
        contentVisitor.onComment(this.buffer);
    }
}
