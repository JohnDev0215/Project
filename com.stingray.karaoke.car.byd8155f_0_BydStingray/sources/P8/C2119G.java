package p8;

import java.util.AbstractList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: renamed from: p8.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2119G extends AbstractList implements RandomAccess, s {
    public final C2143r l;

    public C2119G(C2143r c2143r) {
        this.l = c2143r;
    }

    @Override // p8.s
    public final C2119G b() {
        return this;
    }

    @Override // p8.s
    public final List c() {
        return Collections.unmodifiableList(this.l.l);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i7) {
        return (String) this.l.get(i7);
    }

    @Override // p8.s
    public final AbstractC2129d i(int i7) {
        return this.l.i(i7);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        C2118F c2118f = new C2118F();
        c2118f.l = this.l.iterator();
        return c2118f;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i7) {
        C2117E c2117e = new C2117E();
        c2117e.l = this.l.listIterator(i7);
        return c2117e;
    }

    @Override // p8.s
    public final void o(t tVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.l.size();
    }
}
