package O;

import K.C0331b0;
import K.G0;
import K.InterfaceC0355n0;
import T.C0738e0;
import a.AbstractC0904a;
import u0.C2488b;
import u0.InterfaceC2487a;

/* JADX INFO: loaded from: classes.dex */
public final class I implements InterfaceC0355n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4908a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ K f4909b;

    public /* synthetic */ I(K k10, int i7) {
        this.f4908a = i7;
        this.f4909b = k10;
    }

    private final void f() {
    }

    private final void g() {
    }

    private final void h() {
    }

    private final void j() {
    }

    @Override // K.InterfaceC0355n0
    public final void a() {
        switch (this.f4908a) {
            case 0:
                K k10 = this.f4909b;
                k10.o.setValue(null);
                k10.f4923p.setValue(null);
                break;
            default:
                i();
                break;
        }
    }

    @Override // K.InterfaceC0355n0
    public final void b() {
        switch (this.f4908a) {
            case 0:
                K k10 = this.f4909b;
                k10.o.setValue(null);
                k10.f4923p.setValue(null);
                break;
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [A7.o, z7.k] */
    @Override // K.InterfaceC0355n0
    public final void c(long j3) {
        G0 g0D;
        G0 g0D2;
        G0 g0D3;
        switch (this.f4908a) {
            case 0:
                K k10 = this.f4909b;
                long jI = k10.i(true);
                float f10 = x.f5002a;
                long jA = h6.e.a(m0.c.d(jI), m0.c.e(jI) - 1.0f);
                C0331b0 c0331b0 = k10.d;
                if (c0331b0 != null && (g0D = c0331b0.d()) != null) {
                    long jE = g0D.e(jA);
                    k10.l = jE;
                    k10.f4923p.setValue(new m0.c(jE));
                    k10.f4922n = 0L;
                    k10.o.setValue(K.O.l);
                    k10.p(false);
                    break;
                }
                break;
            default:
                K k11 = this.f4909b;
                if (k11.h()) {
                    C0738e0 c0738e0 = k11.o;
                    if (((K.O) c0738e0.getValue()) == null) {
                        c0738e0.setValue(K.O.f3191n);
                        k11.f4924q = -1;
                        k11.k();
                        C0331b0 c0331b02 = k11.d;
                        if (c0331b02 == null || (g0D3 = c0331b02.d()) == null || !g0D3.c(j3)) {
                            C0331b0 c0331b03 = k11.d;
                            if (c0331b03 != null && (g0D2 = c0331b03.d()) != null) {
                                int iA = k11.f4913b.a(g0D2.b(j3, true));
                                T0.y yVarC = K.c(k11.j().f8481a, AbstractC0904a.q(iA, iA));
                                k11.f(false);
                                InterfaceC2487a interfaceC2487a = k11.f4917h;
                                if (interfaceC2487a != null) {
                                    ((C2488b) interfaceC2487a).a();
                                }
                                k11.c.b(yVarC);
                            }
                        } else if (k11.j().f8481a.l.length() != 0) {
                            k11.f(false);
                            k11.f4921m = Integer.valueOf((int) (K.a(k11, T0.y.a(k11.j(), null, N0.K.f4356b, 5), j3, true, false, C0483n.f4975e, true) >> 32));
                        }
                        k11.n(K.P.l);
                        k11.l = j3;
                        k11.f4923p.setValue(new m0.c(j3));
                        k11.f4922n = 0L;
                        break;
                    }
                }
                break;
        }
    }

    @Override // K.InterfaceC0355n0
    public final void d() {
        int i7 = this.f4908a;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0091  */
    /* JADX WARN: Type inference failed for: r0v7, types: [A7.o, z7.k] */
    @Override // K.InterfaceC0355n0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(long r11) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O.I.e(long):void");
    }

    public void i() {
        K k10 = this.f4909b;
        k10.o.setValue(null);
        k10.f4923p.setValue(null);
        k10.p(true);
        k10.f4921m = null;
        boolean zB = N0.K.b(k10.j().f8482b);
        k10.n(zB ? K.P.f3195n : K.P.f3194m);
        C0331b0 c0331b0 = k10.d;
        if (c0331b0 != null) {
            c0331b0.f3229m.setValue(Boolean.valueOf(!zB && l9.l.U(k10, true)));
        }
        C0331b0 c0331b02 = k10.d;
        if (c0331b02 != null) {
            c0331b02.f3230n.setValue(Boolean.valueOf(!zB && l9.l.U(k10, false)));
        }
        C0331b0 c0331b03 = k10.d;
        if (c0331b03 == null) {
            return;
        }
        c0331b03.o.setValue(Boolean.valueOf(zB && l9.l.U(k10, true)));
    }

    @Override // K.InterfaceC0355n0
    public final void onCancel() {
        switch (this.f4908a) {
            case 0:
                break;
            default:
                i();
                break;
        }
    }
}
