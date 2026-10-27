package X;

import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements ListIterator, B7.a {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9947m;

    public a(int i7, int i10) {
        this.l = i7;
        this.f9947m = i10;
    }

    @Override // java.util.ListIterator
    public void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.l < this.f9947m;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.l > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.l;
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.l - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
