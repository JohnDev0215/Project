package Q;

import D.AbstractC0127i;
import D.AbstractC0133o;
import D.C0119a;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import R.C0679g;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import h2.AbstractC1347d;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0536v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f6061a = 125;

    public static final void a(C0540x0 c0540x0, b0.b bVar, g0.o oVar, boolean z6, boolean z10, boolean z11, b0.b bVar2, C0761q c0761q, int i7) {
        int i10;
        C0540x0 c0540x02;
        boolean z12;
        c0761q.S(-402577235);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(c0540x0) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z10) ? 16384 : 8192;
        }
        int i11 = i10 | 196608;
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.h(bVar2) ? 1048576 : 524288;
        }
        if ((599187 & i11) == 599186 && c0761q.x()) {
            c0761q.K();
            z12 = z11;
        } else {
            boolean z13 = c0761q.k(AbstractC0227s0.l) == a1.k.f11038m;
            R.w wVar = c0540x0.f6069b;
            boolean z14 = z13;
            g0.o oVarA = A.X.a(oVar, wVar.f7328e, ((EnumC0542y0) wVar.f7329f.getValue()) == EnumC0542y0.f6072n, null, wVar.f7334k.getValue() != null, new C0679g(wVar, null), false);
            C0.K kE = AbstractC0133o.e(g0.b.l, true);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarA);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, kE, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.o oVarB = androidx.compose.foundation.layout.b.f11474a.b(g0.l.f14684a);
            int i13 = (i11 << 6) & 7168;
            C0119a c0119a = AbstractC0127i.f1076a;
            g0.f fVar = g0.b.f14669u;
            D.S sB = D.P.b(c0119a, fVar, c0761q, 0);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarB);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            D.T t10 = D.T.f1050a;
            bVar.g(t10, c0761q, Integer.valueOf(((i13 >> 6) & 112) | 6));
            c0761q.p(true);
            boolean zG = ((i11 & 7168) == 2048) | c0761q.g(z14) | ((57344 & i11) == 16384) | ((i11 & 14) == 4);
            Object objG = c0761q.G();
            if (zG || objG == C0753m.f8270a) {
                c0540x02 = c0540x0;
                objG = new C0532t0(c0540x02, z6, z14, z10);
                c0761q.a0(objG);
            } else {
                c0540x02 = c0540x0;
            }
            g0.o oVarB2 = androidx.compose.material3.internal.a.b(c0540x02.f6069b, (InterfaceC2785n) objG);
            int i15 = (i11 >> 9) & 7168;
            D.S sB2 = D.P.b(c0119a, fVar, c0761q, 0);
            int i16 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarB2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i16))) {
                AbstractC1347d.l(i16, c0761q, i16, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            bVar2.g(t10, c0761q, Integer.valueOf(((i15 >> 6) & 112) | 6));
            c0761q.p(true);
            c0761q.p(true);
            z12 = true;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0534u0(c0540x0, bVar, oVar, z6, z10, z12, bVar2, i7);
        }
    }
}
