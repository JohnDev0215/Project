package O;

import B2.C0074n;
import K.C0331b0;
import K.G0;
import K.InterfaceC0355n0;

/* JADX INFO: loaded from: classes.dex */
public final class J implements InterfaceC0355n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ K f4910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f4911b;

    public J(K k10, boolean z6) {
        this.f4910a = k10;
        this.f4911b = z6;
    }

    @Override // K.InterfaceC0355n0
    public final void a() {
        K k10 = this.f4910a;
        k10.o.setValue(null);
        k10.f4923p.setValue(null);
        k10.p(true);
    }

    @Override // K.InterfaceC0355n0
    public final void b() {
        K k10 = this.f4910a;
        k10.o.setValue(null);
        k10.f4923p.setValue(null);
        k10.p(true);
    }

    @Override // K.InterfaceC0355n0
    public final void c(long j3) {
    }

    @Override // K.InterfaceC0355n0
    public final void d() {
        G0 g0D;
        boolean z6 = this.f4911b;
        K.O o = z6 ? K.O.f3190m : K.O.f3191n;
        K k10 = this.f4910a;
        k10.o.setValue(o);
        long jI = k10.i(z6);
        float f10 = x.f5002a;
        long jA = h6.e.a(m0.c.d(jI), m0.c.e(jI) - 1.0f);
        C0331b0 c0331b0 = k10.d;
        if (c0331b0 == null || (g0D = c0331b0.d()) == null) {
            return;
        }
        long jE = g0D.e(jA);
        k10.l = jE;
        k10.f4923p.setValue(new m0.c(jE));
        k10.f4922n = 0L;
        k10.f4924q = -1;
        C0331b0 c0331b02 = k10.d;
        if (c0331b02 != null) {
            c0331b02.f3232q.setValue(Boolean.TRUE);
        }
        k10.p(false);
    }

    @Override // K.InterfaceC0355n0
    public final void e(long j3) {
        K k10 = this.f4910a;
        long jH = m0.c.h(k10.f4922n, j3);
        k10.f4922n = jH;
        k10.f4923p.setValue(new m0.c(m0.c.h(k10.l, jH)));
        T0.y yVarJ = k10.j();
        m0.c cVarG = k10.g();
        A7.m.c(cVarG);
        C0074n c0074n = C0483n.f4977g;
        K.a(k10, yVarJ, cVarG.f18880a, false, this.f4911b, c0074n, true);
        k10.p(false);
    }

    @Override // K.InterfaceC0355n0
    public final void onCancel() {
    }
}
