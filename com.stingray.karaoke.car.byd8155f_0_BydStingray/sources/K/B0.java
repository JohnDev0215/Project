package K;

import T.C0735d;
import s7.AbstractC2410c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B0 implements A.K0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ A.K0 f3125a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final T.D f3126b;
    public final T.D c;

    public B0(A.K0 k02, E0 e02) {
        this.f3125a = k02;
        this.f3126b = C0735d.G(new A0(e02, 1));
        this.c = C0735d.G(new A0(e02, 0));
    }

    @Override // A.K0
    public final boolean a() {
        return ((Boolean) this.c.getValue()).booleanValue();
    }

    @Override // A.K0
    public final boolean b() {
        return this.f3125a.b();
    }

    @Override // A.K0
    public final boolean c() {
        return ((Boolean) this.f3126b.getValue()).booleanValue();
    }

    @Override // A.K0
    public final float d(float f10) {
        return this.f3125a.d(f10);
    }

    @Override // A.K0
    public final Object e(y.j0 j0Var, InterfaceC2785n interfaceC2785n, AbstractC2410c abstractC2410c) {
        return this.f3125a.e(j0Var, interfaceC2785n, abstractC2410c);
    }
}
