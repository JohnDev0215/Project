package Q;

import D.AbstractC0127i;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5501a = 48;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f5502b = 12;
    public static final float c = 112;
    public static final float d = 280;

    public static final void a(b0.b bVar, InterfaceC2772a interfaceC2772a, g0.o oVar, InterfaceC2785n interfaceC2785n, InterfaceC2785n interfaceC2785n2, boolean z6, C0543z c0543z, D.L l, C.k kVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1564716777);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(bVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2785n2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.g(z6) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.f(c0543z) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.f(l) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.f(kVar) ? 67108864 : 33554432;
        }
        if ((i10 & 38347923) == 38347922 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarD = androidx.compose.foundation.layout.c.d(androidx.compose.foundation.a.d(oVar, kVar, M.c(true, 0.0f, c0761q, 6, 6), z6, null, interfaceC2772a, 24), 1.0f);
            float f10 = f5501a;
            float f11 = d;
            float f12 = c;
            if ((8 & 4) != 0) {
                f11 = Float.NaN;
            }
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.n(oVarD, f12, f10, f11, Float.NaN), l);
            D.S sB = D.P.b(AbstractC0127i.f1076a, g0.b.f14670v, c0761q, 48);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarJ);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD2, C0160j.c);
            c1.a(((h1) c0761q.k(i1.f5927a)).f5919m, b0.g.b(1065051884, new B(interfaceC2785n, c0543z, z6, interfaceC2785n2, bVar), c0761q), c0761q, 48);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0494a(bVar, interfaceC2772a, oVar, interfaceC2785n, interfaceC2785n2, z6, c0543z, l, kVar, i7, 1);
        }
    }
}
