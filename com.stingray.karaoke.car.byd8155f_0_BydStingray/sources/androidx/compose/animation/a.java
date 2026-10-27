package androidx.compose.animation;

import A.N;
import Q.C0509h0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.S;
import T.X;
import b0.b;
import g0.o;
import w.C2572c;
import w.C2573d;
import w.C2584o;
import w.F;
import w.G;
import w.w;
import x.B0;
import x.x0;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    /* JADX WARN: Removed duplicated region for block: B:237:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0532  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(x.x0 r33, z7.InterfaceC2782k r34, g0.o r35, w.F r36, w.G r37, z7.InterfaceC2785n r38, b0.b r39, T.C0761q r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1392
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.a.a(x.x0, z7.k, g0.o, w.F, w.G, z7.n, b0.b, T.q, int):void");
    }

    public static final void b(boolean z6, o oVar, F f10, G g7, String str, b bVar, C0761q c0761q, int i7) {
        int i10;
        String str2;
        c0761q.S(-1741346906);
        if ((i7 & 48) == 0) {
            i10 = (c0761q.g(z6) ? 32 : 16) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(f10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(g7) ? 16384 : 8192;
        }
        int i11 = i10 | 196608;
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.h(bVar) ? 1048576 : 524288;
        }
        if ((599185 & i11) == 599184 && c0761q.x()) {
            c0761q.K();
            str2 = str;
        } else {
            int i12 = i11 >> 3;
            d(B0.c(Boolean.valueOf(z6), "AnimatedVisibility", c0761q, (i12 & 14) | ((i11 >> 12) & 112)), C2584o.o, oVar, f10, g7, bVar, c0761q, (i11 & 57344) | (i11 & 896) | 48 | (i11 & 7168) | (i12 & 458752));
            str2 = "AnimatedVisibility";
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0509h0(z6, oVar, f10, g7, str2, bVar, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void c(boolean r16, g0.o r17, w.F r18, w.G r19, java.lang.String r20, b0.b r21, T.C0761q r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.a.c(boolean, g0.o, w.F, w.G, java.lang.String, b0.b, T.q, int, int):void");
    }

    public static final void d(x0 x0Var, InterfaceC2782k interfaceC2782k, o oVar, F f10, G g7, b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(429978603);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(x0Var) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(oVar) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(f10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(g7) ? 16384 : 8192;
        }
        if ((i7 & 196608) == 0) {
            i10 |= c0761q.h(bVar) ? 131072 : 65536;
        }
        if ((74899 & i10) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            int i11 = i10 & 112;
            int i12 = i10 & 14;
            boolean z6 = (i11 == 32) | (i12 == 4);
            Object objG = c0761q.G();
            if (z6 || objG == C0753m.f8270a) {
                objG = new N(interfaceC2782k, x0Var);
                c0761q.a0(objG);
            }
            a(x0Var, interfaceC2782k, androidx.compose.ui.layout.a.b(oVar, (InterfaceC2786o) objG), f10, g7, C2573d.o, bVar, c0761q, i12 | 196608 | i11 | (i10 & 7168) | (57344 & i10) | ((i10 << 6) & 29360128));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C2572c(x0Var, interfaceC2782k, oVar, f10, g7, bVar, i7);
        }
    }

    public static final w e(x0 x0Var, InterfaceC2782k interfaceC2782k, Object obj, C0761q c0761q) {
        c0761q.O(-902048200, x0Var);
        boolean zG = x0Var.g();
        w wVar = w.l;
        w wVar2 = w.f23146n;
        w wVar3 = w.f23145m;
        A9.b bVar = x0Var.f23661a;
        if (zG) {
            c0761q.Q(2101296683);
            c0761q.p(false);
            if (((Boolean) interfaceC2782k.b(obj)).booleanValue()) {
                wVar = wVar3;
            } else if (((Boolean) interfaceC2782k.b(bVar.P0())).booleanValue()) {
                wVar = wVar2;
            }
        } else {
            c0761q.Q(2101530516);
            Object objG = c0761q.G();
            if (objG == C0753m.f8270a) {
                objG = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            if (((Boolean) interfaceC2782k.b(bVar.P0())).booleanValue()) {
                x8.setValue(Boolean.TRUE);
            }
            if (((Boolean) interfaceC2782k.b(obj)).booleanValue()) {
                wVar = wVar3;
            } else if (((Boolean) x8.getValue()).booleanValue()) {
                wVar = wVar2;
            }
            c0761q.p(false);
        }
        c0761q.p(false);
        return wVar;
    }
}
