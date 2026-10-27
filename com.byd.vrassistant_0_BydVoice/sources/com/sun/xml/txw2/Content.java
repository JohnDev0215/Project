package com.sun.xml.txw2;

/* JADX INFO: loaded from: classes3.dex */
abstract class Content {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Content next;

    abstract void accept(ContentVisitor contentVisitor);

    abstract boolean concludesPendingStartTag();

    boolean isReadyToCommit() {
        return true;
    }

    public void written() {
    }

    Content() {
    }

    final Content getNext() {
        return this.next;
    }

    final void setNext(Document document, Content content) {
        this.next = content;
        document.run();
    }
}
