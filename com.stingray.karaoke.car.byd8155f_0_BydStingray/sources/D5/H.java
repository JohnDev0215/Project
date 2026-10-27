package d5;

import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class H extends I {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient int f13382n;
    public final transient int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ I f13383p;

    public H(I i7, int i10, int i11) {
        this.f13383p = i7;
        this.f13382n = i10;
        this.o = i11;
    }

    @Override // d5.I, java.util.List
    /* JADX INFO: renamed from: E */
    public final I subList(int i7, int i10) {
        P8.n.s(i7, i10, this.o);
        int i11 = this.f13382n;
        return this.f13383p.subList(i7 + i11, i10 + i11);
    }

    @Override // java.util.List
    public final Object get(int i7) {
        P8.n.p(i7, this.o);
        return this.f13383p.get(i7 + this.f13382n);
    }

    @Override // d5.I, d5.AbstractC1075D, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // d5.AbstractC1075D
    public final Object[] l() {
        return this.f13383p.l();
    }

    @Override // d5.I, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // d5.AbstractC1075D
    public final int m() {
        return this.f13383p.q() + this.f13382n + this.o;
    }

    @Override // d5.AbstractC1075D
    public final int q() {
        return this.f13383p.q() + this.f13382n;
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.o;
    }

    @Override // d5.I, java.util.List
    public final /* bridge */ /* synthetic */ ListIterator listIterator(int i7) {
        return listIterator(i7);
    }
}
