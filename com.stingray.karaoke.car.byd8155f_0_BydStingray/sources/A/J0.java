package A;

import E0.AbstractC0156f;
import S8.AbstractC0727y;
import android.view.KeyEvent;
import l0.C1727r;
import l0.InterfaceC1718i;
import l0.InterfaceC1721l;
import l3.C1764u;
import r7.EnumC2303a;
import w0.C2586a;
import x.C2658y;
import y.C2695P;
import y0.AbstractC2737o;
import y0.C2730h;
import y0.EnumC2731i;

/* JADX INFO: loaded from: classes.dex */
public final class J0 extends V implements E0.f0, InterfaceC1721l, w0.d, E0.p0 {

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public y.r0 f48I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public r f49J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final x0.d f50K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final C0035q0 f51L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final r f52M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final S0 f53N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final C0050y0 f54O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final C0030o f55P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public C0002a f56Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public G0 f57R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public H0 f58S;

    public J0(InterfaceC0012f interfaceC0012f, r rVar, EnumC0017h0 enumC0017h0, K0 k02, C.k kVar, y.r0 r0Var, boolean z6, boolean z10) {
        super(C0014g.f175p, z6, kVar, enumC0017h0);
        this.f48I = r0Var;
        this.f49J = rVar;
        x0.d dVar = new x0.d();
        this.f50K = dVar;
        C0035q0 c0035q0 = new C0035q0();
        c0035q0.f258y = z6;
        H0(c0035q0);
        this.f51L = c0035q0;
        r rVar2 = new r(new C2658y(new C1764u(androidx.compose.foundation.gestures.a.c)));
        this.f52M = rVar2;
        y.r0 r0Var2 = this.f48I;
        r rVar3 = this.f49J;
        S0 s02 = new S0(k02, r0Var2, rVar3 == null ? rVar2 : rVar3, enumC0017h0, z10, dVar);
        this.f53N = s02;
        C0050y0 c0050y0 = new C0050y0(0, s02, z6);
        this.f54O = c0050y0;
        C0030o c0030o = new C0030o(enumC0017h0, s02, z10, interfaceC0012f);
        H0(c0030o);
        this.f55P = c0030o;
        H0(new x0.g(c0050y0, dVar));
        H0(new C1727r());
        H.i iVar = new H.i();
        iVar.f2762y = c0030o;
        H0(iVar);
        F f10 = new F(this, 1);
        C2695P c2695p = new C2695P();
        c2695p.f23861y = f10;
        H0(c2695p);
    }

    @Override // E0.p0
    public final void H(L0.j jVar) {
        if (this.f121C && (this.f57R == null || this.f58S == null)) {
            this.f57R = new G0(this, 0);
            this.f58S = new H0(this, null);
        }
        G0 g02 = this.f57R;
        if (g02 != null) {
            H7.w[] wVarArr = L0.t.f3964a;
            jVar.l(L0.i.d, new L0.a(null, g02));
        }
        H0 h02 = this.f58S;
        if (h02 != null) {
            H7.w[] wVarArr2 = L0.t.f3964a;
            jVar.l(L0.i.f3889e, h02);
        }
    }

    @Override // E0.f0
    public final void L() {
        AbstractC0156f.q(this, new I0(this, 0));
    }

    @Override // A.V
    public final Object O0(T t10, U u4) {
        y.j0 j0Var = y.j0.f23942m;
        S0 s02 = this.f53N;
        Object objE = s02.e(j0Var, new C0052z0(t10, s02, null), u4);
        return objE == EnumC2303a.l ? objE : m7.q.f19267a;
    }

    @Override // w0.d
    public final boolean P(KeyEvent keyEvent) {
        long jA;
        if (!this.f121C) {
            return false;
        }
        if ((!C2586a.a(w0.c.R(keyEvent), C2586a.l) && !C2586a.a(h6.r.a(keyEvent.getKeyCode()), C2586a.f23161k)) || !h6.q.p(w0.c.S(keyEvent), 2) || keyEvent.isCtrlPressed()) {
            return false;
        }
        boolean z6 = this.f53N.d == EnumC0017h0.l;
        C0030o c0030o = this.f55P;
        if (z6) {
            int i7 = (int) (c0030o.f227G & 4294967295L);
            jA = h6.e.a(0.0f, C2586a.a(h6.r.a(keyEvent.getKeyCode()), C2586a.f23161k) ? i7 : -i7);
        } else {
            int i10 = (int) (c0030o.f227G >> 32);
            jA = h6.e.a(C2586a.a(h6.r.a(keyEvent.getKeyCode()), C2586a.f23161k) ? i10 : -i10, 0.0f);
        }
        AbstractC0727y.w(v0(), null, null, new C0(this, jA, null), 3);
        return true;
    }

    @Override // A.V
    public final void P0(long j3) {
    }

    @Override // A.V
    public final void Q0(long j3) {
        AbstractC0727y.w(this.f50K.c(), null, null, new A0(this, j3, null), 3);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v1, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object, java.util.List] */
    @Override // A.V, E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        long j10;
        ?? r02 = c2730h.f24065a;
        int size = r02.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                break;
            }
            if (((Boolean) this.B.b((y0.q) r02.get(i7))).booleanValue()) {
                super.R(c2730h, enumC2731i, j3);
                break;
            }
            i7++;
        }
        if (enumC2731i == EnumC2731i.f24067m && AbstractC2737o.d(c2730h.d, 6)) {
            ?? r82 = c2730h.f24065a;
            int size2 = r82.size();
            for (int i10 = 0; i10 < size2; i10++) {
                if (((y0.q) r82.get(i10)).b()) {
                    return;
                }
            }
            A7.m.c(this.f56Q);
            a1.b bVar = AbstractC0156f.t(this).f1464C;
            m0.c cVar = new m0.c(0L);
            int size3 = r82.size();
            int i11 = 0;
            while (true) {
                j10 = cVar.f18880a;
                if (i11 >= size3) {
                    break;
                }
                cVar = new m0.c(m0.c.h(j10, ((y0.q) r82.get(i11)).f24084j));
                i11++;
            }
            AbstractC0727y.w(v0(), null, null, new E0(this, m0.c.i(-bVar.y(64), j10), null), 3);
            int size4 = r82.size();
            for (int i12 = 0; i12 < size4; i12++) {
                ((y0.q) r82.get(i12)).a();
            }
        }
    }

    @Override // A.V
    public final boolean R0() {
        S0 s02 = this.f53N;
        if (!s02.f100a.b()) {
            y.r0 r0Var = s02.f101b;
            if (!(r0Var != null ? r0Var.c() : false)) {
                return false;
            }
        }
        return true;
    }

    @Override // w0.d
    public final boolean l(KeyEvent keyEvent) {
        return false;
    }

    @Override // l0.InterfaceC1721l
    public final void o(InterfaceC1718i interfaceC1718i) {
        interfaceC1718i.b(false);
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }

    @Override // g0.n
    public final void z0() {
        AbstractC0156f.q(this, new I0(this, 0));
        this.f56Q = C0002a.f138a;
    }
}
