package androidx.datastore.preferences.protobuf;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class k0 implements ListIterator {
    public ListIterator l;

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.l.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.l.hasPrevious();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return (String) this.l.next();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.l.nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return (String) this.l.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.l.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
