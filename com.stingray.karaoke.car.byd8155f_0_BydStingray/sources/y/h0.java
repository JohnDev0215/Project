package y;

import E0.AbstractC0156f;
import E0.InterfaceC0165o;
import E0.InterfaceC0166p;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import android.view.View;
import android.widget.Magnifier;
import w8.C2607b;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends g0.n implements InterfaceC0166p, InterfaceC0165o, E0.p0, E0.f0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC2782k f23905A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f23906C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public long f23907D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public float f23908E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public float f23909F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f23910G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public o0 f23911H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public View f23912I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public a1.b f23913J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public C2607b f23914K;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public T.D f23916M;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public a1.j f23918O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public U8.l f23919P;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public A.F f23920y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public InterfaceC2782k f23921z;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final C0738e0 f23915L = C0735d.O(null, T.S.f8222n);

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public long f23917N = 9205357640488583168L;

    public h0(A.F f10, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, float f11, boolean z6, long j3, float f12, float f13, boolean z10, o0 o0Var) {
        this.f23920y = f10;
        this.f23921z = interfaceC2782k;
        this.f23905A = interfaceC2782k2;
        this.B = f11;
        this.f23906C = z6;
        this.f23907D = j3;
        this.f23908E = f12;
        this.f23909F = f13;
        this.f23910G = z10;
        this.f23911H = o0Var;
    }

    @Override // g0.n
    public final void A0() {
        C2607b c2607b = this.f23914K;
        if (c2607b != null) {
            ((Magnifier) c2607b.f23345m).dismiss();
        }
        this.f23914K = null;
    }

    @Override // E0.p0
    public final void H(L0.j jVar) {
        jVar.l(i0.f23924a, new f0(this, 1));
    }

    public final long H0() {
        if (this.f23916M == null) {
            this.f23916M = C0735d.G(new f0(this, 0));
        }
        T.D d = this.f23916M;
        if (d != null) {
            return ((m0.c) d.getValue()).f18880a;
        }
        return 9205357640488583168L;
    }

    public final void I0() {
        C2607b c2607b;
        C2607b c2607b2 = this.f23914K;
        if (c2607b2 != null) {
            ((Magnifier) c2607b2.f23345m).dismiss();
        }
        View viewV = this.f23912I;
        if (viewV == null) {
            viewV = AbstractC0156f.v(this);
        }
        this.f23912I = viewV;
        a1.b bVar = this.f23913J;
        if (bVar == null) {
            bVar = AbstractC0156f.t(this).f1464C;
        }
        this.f23913J = bVar;
        o0 o0Var = this.f23911H;
        boolean z6 = this.f23906C;
        long j3 = this.f23907D;
        float f10 = this.f23908E;
        float f11 = this.f23909F;
        boolean z10 = this.f23910G;
        float f12 = this.B;
        o0Var.getClass();
        if (z6) {
            c2607b = new C2607b(new Magnifier(viewV), 5);
        } else {
            long jY = bVar.Y(j3);
            float fY = bVar.y(f10);
            float fY2 = bVar.y(f11);
            Magnifier.Builder builder = new Magnifier.Builder(viewV);
            if (jY != 9205357640488583168L) {
                builder.setSize(C7.a.W(m0.f.d(jY)), C7.a.W(m0.f.b(jY)));
            }
            if (!Float.isNaN(fY)) {
                builder.setCornerRadius(fY);
            }
            if (!Float.isNaN(fY2)) {
                builder.setElevation(fY2);
            }
            if (!Float.isNaN(f12)) {
                builder.setInitialZoom(f12);
            }
            builder.setClippingEnabled(z10);
            c2607b = new C2607b(builder.build(), 5);
        }
        this.f23914K = c2607b;
        K0();
    }

    public final void J0() {
        a1.b bVar = this.f23913J;
        if (bVar == null) {
            bVar = AbstractC0156f.t(this).f1464C;
            this.f23913J = bVar;
        }
        long j3 = ((m0.c) this.f23920y.b(bVar)).f18880a;
        long jH = 9205357640488583168L;
        if (!h6.e.m(j3) || !h6.e.m(H0())) {
            this.f23917N = 9205357640488583168L;
            C2607b c2607b = this.f23914K;
            if (c2607b != null) {
                ((Magnifier) c2607b.f23345m).dismiss();
                return;
            }
            return;
        }
        this.f23917N = m0.c.h(H0(), j3);
        InterfaceC2782k interfaceC2782k = this.f23921z;
        if (interfaceC2782k != null) {
            long j10 = ((m0.c) interfaceC2782k.b(bVar)).f18880a;
            m0.c cVar = new m0.c(j10);
            if (!h6.e.m(j10)) {
                cVar = null;
            }
            if (cVar != null) {
                jH = m0.c.h(H0(), cVar.f18880a);
            }
        }
        if (this.f23914K == null) {
            I0();
        }
        C2607b c2607b2 = this.f23914K;
        if (c2607b2 != null) {
            long j11 = this.f23917N;
            float f10 = this.B;
            c2607b2.getClass();
            boolean zIsNaN = Float.isNaN(f10);
            Magnifier magnifier = (Magnifier) c2607b2.f23345m;
            if (!zIsNaN) {
                magnifier.setZoom(f10);
            }
            if (h6.e.m(jH)) {
                magnifier.show(m0.c.d(j11), m0.c.e(j11), m0.c.d(jH), m0.c.e(jH));
            } else {
                magnifier.show(m0.c.d(j11), m0.c.e(j11));
            }
        }
        K0();
    }

    public final void K0() {
        a1.b bVar;
        C2607b c2607b = this.f23914K;
        if (c2607b == null || (bVar = this.f23913J) == null) {
            return;
        }
        Magnifier magnifier = (Magnifier) c2607b.f23345m;
        long jC = H7.H.c(magnifier.getWidth(), magnifier.getHeight());
        a1.j jVar = this.f23918O;
        if (jVar != null && jC == jVar.f11037a) {
            return;
        }
        InterfaceC2782k interfaceC2782k = this.f23905A;
        if (interfaceC2782k != null) {
            interfaceC2782k.b(new a1.g(bVar.x(H7.H.S(H7.H.c(magnifier.getWidth(), magnifier.getHeight())))));
        }
        this.f23918O = new a1.j(H7.H.c(magnifier.getWidth(), magnifier.getHeight()));
    }

    @Override // E0.f0
    public final void L() {
        AbstractC0156f.q(this, new f0(this, 2));
    }

    @Override // E0.InterfaceC0165o
    public final void a(E0.H h10) {
        h10.a();
        U8.l lVar = this.f23919P;
        if (lVar != null) {
            lVar.p(m7.q.f19267a);
        }
    }

    @Override // E0.InterfaceC0166p
    public final void l0(E0.d0 d0Var) {
        this.f23915L.setValue(d0Var);
    }

    @Override // g0.n
    public final void z0() {
        L();
        this.f23919P = AbstractC2673a.a(0, 7, null);
        AbstractC0727y.w(v0(), null, null, new g0(this, null), 3);
    }
}
