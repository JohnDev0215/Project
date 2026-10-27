package androidx.compose.material3;

import A7.m;
import C.k;
import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import Q.AbstractC0524p;
import Q.C0544z0;
import Q.M;
import Q.T;
import S.n;
import T.C0735d;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import g0.l;
import g0.o;
import h2.AbstractC1347d;
import h6.i;
import n0.C1891t;
import n0.InterfaceC1866P;
import x.C0;
import x.C2636g0;
import x.InterfaceC2612A;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f11541a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float f11542b;
    public static final float c;
    public static final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final float f11543e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2636g0 f11544f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0 f11545g;

    static {
        float f10 = n.f7707b;
        f11541a = f10;
        f11542b = n.f7710g;
        c = n.f7709f;
        float f11 = n.d;
        d = f11;
        f11543e = (f11 - f10) / 2;
        f11544f = new C2636g0(0);
        f11545g = new C0(100, (InterfaceC2612A) null, 6);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(boolean r20, z7.InterfaceC2782k r21, g0.o r22, z7.InterfaceC2785n r23, boolean r24, Q.C0544z0 r25, C.k r26, T.C0761q r27, int r28, int r29) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.d.a(boolean, z7.k, g0.o, z7.n, boolean, Q.z0, C.k, T.q, int, int):void");
    }

    public static final void b(o oVar, boolean z6, boolean z10, C0544z0 c0544z0, InterfaceC2785n interfaceC2785n, k kVar, InterfaceC1866P interfaceC1866P, C0761q c0761q, int i7) {
        int i10;
        int i11;
        long j3;
        c0761q.S(-1594099146);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z10) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(c0544z0) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.f(kVar) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.f(interfaceC1866P) ? 1048576 : 524288;
        }
        if ((599187 & i10) == 599186 && c0761q.x()) {
            c0761q.K();
        } else {
            long j10 = z10 ? z6 ? c0544z0.f6078b : c0544z0.f6080f : z6 ? c0544z0.f6084j : c0544z0.f6087n;
            long j11 = z10 ? z6 ? c0544z0.f6077a : c0544z0.f6079e : z6 ? c0544z0.f6083i : c0544z0.f6086m;
            float f10 = n.f7706a;
            InterfaceC1866P interfaceC1866PA = T.a(5, c0761q);
            float f11 = n.f7708e;
            if (z10) {
                i11 = i10;
                j3 = z6 ? c0544z0.c : c0544z0.f6081g;
            } else {
                i11 = i10;
                j3 = z6 ? c0544z0.f6085k : c0544z0.o;
            }
            o oVarB = androidx.compose.foundation.a.b(i.c(oVar, f11, j3, interfaceC1866PA), j10, interfaceC1866PA);
            K kE = AbstractC0133o.e(g0.b.l, false);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVarB);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            o oVarB2 = androidx.compose.foundation.a.b(androidx.compose.foundation.d.a(androidx.compose.foundation.layout.b.f11474a.a(l.f14684a, g0.b.o).k(new ThumbElement(kVar, z6)), kVar, M.c(false, n.c / 2, c0761q, 54, 4)), j11, interfaceC1866P);
            K kE2 = AbstractC0133o.e(g0.b.f14664p, false);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            o oVarD2 = g0.a.d(c0761q, oVarB2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            c0761q.Q(1163457794);
            if (interfaceC2785n != null) {
                C0735d.a(AbstractC0524p.f6016a.a(new C1891t(z10 ? z6 ? c0544z0.d : c0544z0.f6082h : z6 ? c0544z0.l : c0544z0.f6088p)), interfaceC2785n, c0761q, ((i11 >> 9) & 112) | 8);
            }
            M5.b.n(c0761q, false, true, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q.C0(oVar, z6, z10, c0544z0, interfaceC2785n, kVar, interfaceC1866P, i7);
        }
    }
}
