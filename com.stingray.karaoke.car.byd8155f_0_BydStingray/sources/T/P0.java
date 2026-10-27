package T;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class P0 implements Iterable, B7.a {
    public final z0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f8219m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0735d f8220n;

    public P0(z0 z0Var, int i7, K k10, C0735d c0735d) {
        this.l = z0Var;
        this.f8219m = i7;
        this.f8220n = c0735d;
        k10.getClass();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new J(this.l, this.f8219m, null, this.f8220n);
    }
}
