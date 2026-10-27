package u2;

/* JADX INFO: loaded from: classes.dex */
public abstract class o0 extends AbstractC2507j {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC2498a f22886k;

    public o0(AbstractC2498a abstractC2498a) {
        this.f22886k = abstractC2498a;
    }

    public void A() {
        x(null, this.f22886k);
    }

    @Override // u2.AbstractC2498a
    public final e2.f0 g() {
        return this.f22886k.g();
    }

    @Override // u2.AbstractC2498a
    public final e2.F h() {
        return this.f22886k.h();
    }

    @Override // u2.AbstractC2498a
    public final boolean i() {
        return this.f22886k.i();
    }

    @Override // u2.AbstractC2507j, u2.AbstractC2498a
    public final void l(k2.E e10) {
        super.l(e10);
        A();
    }

    @Override // u2.AbstractC2498a
    public void s(e2.F f10) {
        this.f22886k.s(f10);
    }

    @Override // u2.AbstractC2507j
    public final C2492C t(Object obj, C2492C c2492c) {
        return y(c2492c);
    }

    @Override // u2.AbstractC2507j
    public final long u(Object obj, long j3, C2492C c2492c) {
        return j3;
    }

    @Override // u2.AbstractC2507j
    public final int v(int i7, Object obj) {
        return i7;
    }

    @Override // u2.AbstractC2507j
    public final void w(Object obj, AbstractC2498a abstractC2498a, e2.f0 f0Var) {
        z(f0Var);
    }

    public C2492C y(C2492C c2492c) {
        return c2492c;
    }

    public abstract void z(e2.f0 f0Var);
}
