package V;

import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class c implements ListIterator, B7.a {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9277m;

    public c(int i7, List list) {
        this.l = list;
        this.f9277m = i7;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator
    public final void add(Object obj) {
        this.l.add(this.f9277m, obj);
        this.f9277m++;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.f9277m < this.l.size();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f9277m > 0;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        int i7 = this.f9277m;
        this.f9277m = i7 + 1;
        return this.l.get(i7);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f9277m;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator
    public final Object previous() {
        int i7 = this.f9277m - 1;
        this.f9277m = i7;
        return this.l.get(i7);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f9277m - 1;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        int i7 = this.f9277m - 1;
        this.f9277m = i7;
        this.l.remove(i7);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    @Override // java.util.ListIterator
    public final void set(Object obj) {
        this.l.set(this.f9277m, obj);
    }
}
