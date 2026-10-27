package E0;

import C0.C0107n;
import n0.AbstractC1861K;
import n0.C1878g;
import n0.C1891t;
import n0.InterfaceC1889r;
import q0.C2164c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class A extends d0 {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final C1878g f1455X;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public InterfaceC0174y f1456V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public C0175z f1457W;

    static {
        C1878g c1878gH = AbstractC1861K.h();
        int i7 = C1891t.l;
        c1878gH.e(C1891t.f19449h);
        c1878gH.k(1.0f);
        c1878gH.l(1);
        f1455X = c1878gH;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public A(F f10, InterfaceC0174y interfaceC0174y) {
        super(f10);
        this.f1456V = interfaceC0174y;
        this.f1457W = f10.f1482n != null ? new C0175z(this) : null;
        if ((((g0.n) interfaceC0174y).l.f14686n & 512) != 0) {
            throw new ClassCastException();
        }
    }

    @Override // E0.d0
    public final void L0() {
        if (this.f1457W == null) {
            this.f1457W = new C0175z(this);
        }
    }

    @Override // E0.d0
    public final S O0() {
        return this.f1457W;
    }

    @Override // C0.J
    public final int P(int i7) {
        InterfaceC0174y interfaceC0174y = this.f1456V;
        d0 d0Var = this.f1649x;
        A7.m.c(d0Var);
        return interfaceC0174y.c(this, d0Var, i7);
    }

    @Override // E0.d0
    public final g0.n Q0() {
        return ((g0.n) this.f1456V).l;
    }

    @Override // C0.J
    public final int V(int i7) {
        InterfaceC0174y interfaceC0174y = this.f1456V;
        d0 d0Var = this.f1649x;
        A7.m.c(d0Var);
        return interfaceC0174y.d(this, d0Var, i7);
    }

    @Override // C0.J
    public final int a0(int i7) {
        InterfaceC0174y interfaceC0174y = this.f1456V;
        d0 d0Var = this.f1649x;
        A7.m.c(d0Var);
        return interfaceC0174y.f(this, d0Var, i7);
    }

    @Override // C0.J
    public final C0.V b(long j3) {
        o0(j3);
        InterfaceC0174y interfaceC0174y = this.f1456V;
        d0 d0Var = this.f1649x;
        A7.m.c(d0Var);
        f1(interfaceC0174y.g(this, d0Var, j3));
        a1();
        return this;
    }

    @Override // C0.J
    public final int c(int i7) {
        InterfaceC0174y interfaceC0174y = this.f1456V;
        d0 d0Var = this.f1649x;
        A7.m.c(d0Var);
        return interfaceC0174y.b(this, d0Var, i7);
    }

    @Override // E0.d0
    public final void c1(InterfaceC1889r interfaceC1889r, C2164c c2164c) {
        d0 d0Var = this.f1649x;
        A7.m.c(d0Var);
        d0Var.I0(interfaceC1889r, c2164c);
        if (I.a(this.f1648w).getShowLayoutBounds()) {
            J0(interfaceC1889r, f1455X);
        }
    }

    @Override // E0.d0, C0.V
    public final void k0(long j3, float f10, C2164c c2164c) {
        d1(j3, f10, null, c2164c);
        n1();
    }

    @Override // C0.V
    public final void l0(long j3, float f10, InterfaceC2782k interfaceC2782k) {
        d1(j3, f10, interfaceC2782k, null);
        n1();
    }

    public final void n1() {
        if (this.f1576r) {
            return;
        }
        b1();
        z0().b();
        A7.m.c(this.f1649x);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o1(InterfaceC0174y interfaceC0174y) {
        if (!interfaceC0174y.equals(this.f1456V) && (((g0.n) interfaceC0174y).l.f14686n & 512) != 0) {
            throw new ClassCastException();
        }
        this.f1456V = interfaceC0174y;
    }

    @Override // E0.Q
    public final int q0(C0107n c0107n) {
        C0175z c0175z = this.f1457W;
        if (c0175z == null) {
            return AbstractC0156f.c(this, c0107n);
        }
        Integer num = (Integer) c0175z.B.get(c0107n);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }
}
