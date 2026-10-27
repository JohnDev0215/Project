package d5;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: d5.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1078G extends s0 implements ListIterator {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f13380m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final I f13381n;

    public C1078G(I i7, int i10) {
        int size = i7.size();
        P8.n.r(i10, size);
        this.l = size;
        this.f13380m = i10;
        this.f13381n = i7;
    }

    public final Object a(int i7) {
        return this.f13381n.get(i7);
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.f13380m < this.l;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f13380m > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i7 = this.f13380m;
        this.f13380m = i7 + 1;
        return a(i7);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f13380m;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i7 = this.f13380m - 1;
        this.f13380m = i7;
        return a(i7);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f13380m - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
