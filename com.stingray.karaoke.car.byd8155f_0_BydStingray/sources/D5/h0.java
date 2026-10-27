package d5;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends I {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object[] f13424n;
    public final transient int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f13425p;

    public h0(Object[] objArr, int i7, int i10) {
        this.f13424n = objArr;
        this.o = i7;
        this.f13425p = i10;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        P8.n.p(i7, this.f13425p);
        Object obj = this.f13424n[(i7 * 2) + this.o];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f13425p;
    }
}
