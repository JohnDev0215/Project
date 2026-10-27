package o7;

import A7.m;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import n7.AbstractC1992g;

/* JADX INFO: renamed from: o7.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2055g extends AbstractC1992g implements Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C2055g f20561m;
    public final C2053e l;

    static {
        C2053e c2053e = C2053e.f20548y;
        f20561m = new C2055g(C2053e.f20548y);
    }

    public C2055g(C2053e c2053e) {
        m.f(c2053e, "backing");
        this.l = c2053e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        return this.l.b(obj) >= 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        m.f(collection, "elements");
        this.l.c();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.l.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.l.containsKey(obj);
    }

    @Override // n7.AbstractC1992g
    public final int d() {
        return this.l.f20555t;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.l.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        C2053e c2053e = this.l;
        c2053e.getClass();
        return new C2051c(c2053e, 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        C2053e c2053e = this.l;
        c2053e.c();
        int i7 = c2053e.i(obj);
        if (i7 < 0) {
            return false;
        }
        c2053e.o(i7);
        return true;
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        m.f(collection, "elements");
        this.l.c();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        m.f(collection, "elements");
        this.l.c();
        return super.retainAll(collection);
    }
}
