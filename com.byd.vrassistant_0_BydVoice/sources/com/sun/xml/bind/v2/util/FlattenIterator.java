package com.sun.xml.bind.v2.util;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class FlattenIterator<T> implements Iterator<T> {
    private Iterator<? extends T> child = null;
    private T next;
    private final Iterator<? extends Map<?, ? extends T>> parent;

    public FlattenIterator(Iterable<? extends Map<?, ? extends T>> iterable) {
        this.parent = iterable.iterator();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        getNext();
        return this.next != null;
    }

    @Override // java.util.Iterator
    public T next() {
        T t = this.next;
        this.next = null;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException();
    }

    private void getNext() {
        if (this.next != null) {
            return;
        }
        Iterator<? extends T> it = this.child;
        if (it != null && it.hasNext()) {
            this.next = this.child.next();
        } else if (this.parent.hasNext()) {
            this.child = this.parent.next().values().iterator();
            getNext();
        }
    }
}
