package d5;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends I {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final d0 f13415p = new d0(0, new Object[0]);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object[] f13416n;
    public final transient int o;

    public d0(int i7, Object[] objArr) {
        this.f13416n = objArr;
        this.o = i7;
    }

    @Override // d5.I, d5.AbstractC1075D
    public final int d(int i7, Object[] objArr) {
        Object[] objArr2 = this.f13416n;
        int i10 = this.o;
        System.arraycopy(objArr2, 0, objArr, i7, i10);
        return i7 + i10;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        P8.n.p(i7, this.o);
        Object obj = this.f13416n[i7];
        Objects.requireNonNull(obj);
        return obj;
    }

    @Override // d5.AbstractC1075D
    public final Object[] l() {
        return this.f13416n;
    }

    @Override // d5.AbstractC1075D
    public final int m() {
        return this.o;
    }

    @Override // d5.AbstractC1075D
    public final int q() {
        return 0;
    }

    @Override // d5.AbstractC1075D
    public final boolean r() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.o;
    }
}
