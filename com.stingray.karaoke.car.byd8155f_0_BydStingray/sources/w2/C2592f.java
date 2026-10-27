package w2;

import e2.g0;
import n2.AbstractC1905e;

/* JADX INFO: renamed from: w2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2592f extends m implements Comparable {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f23197p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f23198q;

    public C2592f(int i7, g0 g0Var, int i10, C2595i c2595i, int i11) {
        super(i7, g0Var, i10);
        this.f23197p = AbstractC1905e.o(i11, c2595i.f23231n0) ? 1 : 0;
        this.f23198q = this.o.b();
    }

    @Override // w2.m
    public final int a() {
        return this.f23197p;
    }

    @Override // w2.m
    public final /* bridge */ /* synthetic */ boolean b(m mVar) {
        return false;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Integer.compare(this.f23198q, ((C2592f) obj).f23198q);
    }
}
