package p;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.ListBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class va1 implements ListIterator, g81 {
    public final ListBuilder c;
    public int e;
    public int k;

    public va1(ListBuilder listBuilder, int i) {
        v03.j(listBuilder, "list");
        this.c = listBuilder;
        this.e = i;
        this.k = -1;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        int i = this.e;
        this.e = i + 1;
        this.c.add(i, obj);
        this.k = -1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.e < this.c.length;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.e > 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i = this.e;
        ListBuilder listBuilder = this.c;
        if (i >= listBuilder.length) {
            throw new NoSuchElementException();
        }
        int i2 = this.e;
        this.e = i2 + 1;
        this.k = i2;
        return listBuilder.array[listBuilder.offset + this.k];
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.e;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        int i = this.e;
        if (i <= 0) {
            throw new NoSuchElementException();
        }
        int i2 = i - 1;
        this.e = i2;
        this.k = i2;
        ListBuilder listBuilder = this.c;
        return listBuilder.array[listBuilder.offset + this.k];
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.e - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i = this.k;
        if (i == -1) {
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.".toString());
        }
        this.c.b(i);
        this.e = this.k;
        this.k = -1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        int i = this.k;
        if (i == -1) {
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.".toString());
        }
        this.c.set(i, obj);
    }
}
