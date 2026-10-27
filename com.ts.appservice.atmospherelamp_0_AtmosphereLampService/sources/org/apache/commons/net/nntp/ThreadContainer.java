package org.apache.commons.net.nntp;

/* JADX INFO: loaded from: classes3.dex */
class ThreadContainer {
    ThreadContainer child;
    ThreadContainer next;
    ThreadContainer parent;
    Threadable threadable;

    ThreadContainer() {
    }

    boolean findChild(ThreadContainer target) {
        ThreadContainer threadContainer = this.child;
        if (threadContainer == null) {
            return false;
        }
        if (threadContainer == target) {
            return true;
        }
        return threadContainer.findChild(target);
    }

    void flush() {
        if (this.parent != null && this.threadable == null) {
            throw new RuntimeException("no threadable in " + toString());
        }
        this.parent = null;
        Threadable threadable = this.threadable;
        if (threadable != null) {
            ThreadContainer threadContainer = this.child;
            threadable.setChild(threadContainer == null ? null : threadContainer.threadable);
        }
        ThreadContainer threadContainer2 = this.child;
        if (threadContainer2 != null) {
            threadContainer2.flush();
            this.child = null;
        }
        Threadable threadable2 = this.threadable;
        if (threadable2 != null) {
            ThreadContainer threadContainer3 = this.next;
            threadable2.setNext(threadContainer3 == null ? null : threadContainer3.threadable);
        }
        ThreadContainer threadContainer4 = this.next;
        if (threadContainer4 != null) {
            threadContainer4.flush();
            this.next = null;
        }
        this.threadable = null;
    }

    void reverseChildren() {
        if (this.child != null) {
            ThreadContainer prev = null;
            ThreadContainer kid = this.child;
            ThreadContainer rest = kid.next;
            while (kid != null) {
                kid.next = prev;
                prev = kid;
                kid = rest;
                rest = rest == null ? null : rest.next;
            }
            this.child = prev;
            for (ThreadContainer kid2 = this.child; kid2 != null; kid2 = kid2.next) {
                kid2.reverseChildren();
            }
        }
    }
}
