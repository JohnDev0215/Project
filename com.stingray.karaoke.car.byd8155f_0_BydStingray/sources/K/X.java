package K;

import A.C0004b;
import A.C0022k;
import B2.C0074n;
import C0.C0114v;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F1.C0254m;
import N0.AbstractC0412g;
import N0.C0409d;
import N0.C0411f;
import S0.InterfaceC0702n;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import android.view.KeyEvent;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import k3.T0;
import n0.InterfaceC1892u;
import y0.C2723a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final U f3202a = new U(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2723a f3203b = new C2723a(1008);
    public static final C0254m c = new C0254m(0, 0, 1);

    public static final void a(C0411f c0411f, g0.o oVar, N0.L l, InterfaceC2782k interfaceC2782k, int i7, boolean z6, int i10, int i11, Map map, InterfaceC1892u interfaceC1892u, C0761q c0761q, int i12) {
        int i13;
        boolean z10;
        boolean z11;
        boolean z12;
        InterfaceC1892u interfaceC1892u2;
        c0761q.S(-1064305212);
        if ((i12 & 6) == 0) {
            i13 = (c0761q.f(c0411f) ? 4 : 2) | i12;
        } else {
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            i13 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i12 & 384) == 0) {
            i13 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= c0761q.h(interfaceC2782k) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i13 |= c0761q.d(i7) ? 16384 : 8192;
        }
        if ((196608 & i12) == 0) {
            i13 |= c0761q.g(z6) ? 131072 : 65536;
        }
        if ((1572864 & i12) == 0) {
            i13 |= c0761q.d(i10) ? 1048576 : 524288;
        }
        if ((12582912 & i12) == 0) {
            i13 |= c0761q.d(i11) ? 8388608 : 4194304;
        }
        if ((100663296 & i12) == 0) {
            i13 |= c0761q.h(map) ? 67108864 : 33554432;
        }
        int i14 = i13 | 805306368;
        if ((306783379 & i14) == 306783378 && c0761q.x()) {
            c0761q.K();
            interfaceC1892u2 = interfaceC1892u;
        } else {
            w(i11, i10);
            if (c0761q.k(O.E.f4896a) != null) {
                throw new ClassCastException();
            }
            c0761q.Q(-1584467526);
            int i15 = 0;
            c0761q.p(false);
            m7.i iVar = AbstractC0338f.f3251a;
            int length = c0411f.l.length();
            List list = c0411f.o;
            if (list != null) {
                int size = list.size();
                while (i15 < size) {
                    List list2 = list;
                    C0409d c0409d = (C0409d) list.get(i15);
                    if ((c0409d.f4371a instanceof String) && "androidx.compose.foundation.text.inlineContent".equals(c0409d.d)) {
                        z10 = false;
                        if (AbstractC0412g.c(0, length, c0409d.f4372b, c0409d.c)) {
                            z12 = true;
                            z11 = true;
                            break;
                        }
                    }
                    i15++;
                    list = list2;
                }
                z10 = false;
            } else {
                z10 = false;
            }
            z11 = true;
            z12 = z10;
            boolean zU = w0.c.U(c0411f);
            if (z12 || zU) {
                boolean z13 = z10;
                boolean z14 = z11;
                c0761q.Q(-1583391888);
                boolean z15 = (i14 & 14) == 4 ? z14 : z13;
                Object objG = c0761q.G();
                Object obj = C0753m.f8270a;
                if (z15 || objG == obj) {
                    objG = C0735d.O(c0411f, T.S.f8224q);
                    c0761q.a0(objG);
                }
                T.X x8 = (T.X) objG;
                C0411f c0411f2 = (C0411f) x8.getValue();
                InterfaceC0702n interfaceC0702n = (InterfaceC0702n) c0761q.k(AbstractC0227s0.f2262i);
                boolean zF = c0761q.f(x8);
                Object objG2 = c0761q.G();
                if (zF || objG2 == obj) {
                    objG2 = new A.L0(x8, 2);
                    c0761q.a0(objG2);
                }
                int i16 = i14 << 6;
                f(oVar, c0411f2, interfaceC2782k, z12, map, l, i7, z6, i10, i11, interfaceC0702n, null, (InterfaceC2782k) objG2, c0761q, ((i14 >> 3) & 910) | ((i14 >> 12) & 57344) | ((i14 << 9) & 458752) | (3670016 & i16) | (29360128 & i16) | (234881024 & i16) | (i16 & 1879048192), (i14 >> 21) & 896);
                c0761q.p(z13);
            } else {
                c0761q.Q(-1584294453);
                g0.o oVarV = v(androidx.compose.ui.graphics.a.b(oVar, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, 131071), c0411f, l, interfaceC2782k, i7, z6, i10, i11, (InterfaceC0702n) c0761q.k(AbstractC0227s0.f2262i), null, null, null, null);
                C0336e c0336e = C0336e.c;
                int i17 = c0761q.f8304P;
                g0.o oVarD = g0.a.d(c0761q, oVarV);
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                InterfaceC0161k.f1681b.getClass();
                InterfaceC2772a interfaceC2772a = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(interfaceC2772a);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, c0336e, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0735d.V(c0761q, oVarD, C0160j.c);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i17))) {
                    AbstractC1347d.l(i17, c0761q, i17, c0158h);
                }
                c0761q.p(true);
                c0761q.p(false);
            }
            interfaceC1892u2 = null;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0348k(c0411f, oVar, l, interfaceC2782k, i7, z6, i10, i11, map, interfaceC1892u2, i12);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x010c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(java.lang.String r27, g0.o r28, N0.L r29, z7.InterfaceC2782k r30, int r31, boolean r32, int r33, int r34, n0.InterfaceC1892u r35, T.C0761q r36, int r37, int r38) {
        /*
            Method dump skipped, instruction units count: 567
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K.X.b(java.lang.String, g0.o, N0.L, z7.k, int, boolean, int, int, n0.u, T.q, int, int):void");
    }

    public static final void c(O.K k10, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1985516685);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(k10) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new z.m();
                c0761q.a0(objG);
            }
            z.m mVar = (z.m) objG;
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = new A.I0(mVar, 24);
                c0761q.a0(objG2);
            }
            h6.j.d(mVar, (InterfaceC2772a) objG2, new C0004b(k10, 24, mVar), null, k10.h(), bVar, c0761q, ((i10 << 12) & 458752) | 54);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new F0.W(k10, bVar, i7, 3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:201:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0475  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x049e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x04e7  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0583  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0592  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0595  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x05ab  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x05eb A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0604  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x068e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:293:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x06d0  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x06fa  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0704  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x072c  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0755  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x075f  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0782  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0784  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x079b  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x07b8  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x07cd  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x07e6  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0810  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0812  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0818  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x081a  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0828  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x082a  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x0859 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x08bd  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x08c2  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x08d8  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x08ea  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0908  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x090a  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0928 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:405:0x092e  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x094e  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0951  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x09b0  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x09b4  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0a3f  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0a43  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void d(T0.y r92, z7.InterfaceC2782k r93, g0.o r94, N0.L r95, B2.C0074n r96, z7.InterfaceC2782k r97, C.k r98, n0.AbstractC1887p r99, boolean r100, int r101, int r102, T0.m r103, K.Z r104, boolean r105, boolean r106, z7.InterfaceC2786o r107, T.C0761q r108, int r109, int r110) {
        /*
            Method dump skipped, instruction units count: 2778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K.X.d(T0.y, z7.k, g0.o, N0.L, B2.n, z7.k, C.k, n0.p, boolean, int, int, T0.m, K.Z, boolean, boolean, z7.o, T.q, int, int):void");
    }

    public static final void e(g0.o oVar, O.K k10, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-20551815);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(k10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(bVar) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            C0.K kE = AbstractC0133o.e(g0.b.l, true);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            c(k10, bVar, c0761q, (i10 >> 3) & 126);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(oVar, k10, bVar, i7, 4);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0266  */
    /* JADX WARN: Type inference failed for: r10v31, types: [n7.u] */
    /* JADX WARN: Type inference failed for: r10v32, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r10v34, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r49v0, types: [T.q] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void f(g0.o r36, N0.C0411f r37, z7.InterfaceC2782k r38, boolean r39, java.util.Map r40, N0.L r41, int r42, boolean r43, int r44, int r45, S0.InterfaceC0702n r46, n0.InterfaceC1892u r47, z7.InterfaceC2782k r48, T.C0761q r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 1097
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K.X.f(g0.o, N0.f, z7.k, boolean, java.util.Map, N0.L, int, boolean, int, int, S0.n, n0.u, z7.k, T.q, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void g(O.K r14, T.C0761q r15, int r16) {
        /*
            Method dump skipped, instruction units count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K.X.g(O.K, T.q, int):void");
    }

    public static final void h(O.K k10, boolean z6, C0761q c0761q, int i7) {
        int i10;
        G0 g0D;
        c0761q.S(626339208);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(k10) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else if (z6) {
            c0761q.Q(-1286242594);
            C0331b0 c0331b0 = k10.d;
            N0.H h10 = null;
            if (c0331b0 != null && (g0D = c0331b0.d()) != null) {
                N0.H h11 = g0D.f3161a;
                C0331b0 c0331b02 = k10.d;
                if (!(c0331b02 != null ? c0331b02.f3231p : true)) {
                    h10 = h11;
                }
            }
            if (h10 == null) {
                c0761q.Q(-1285984396);
            } else {
                c0761q.Q(-1285984395);
                if (N0.K.b(k10.j().f8482b)) {
                    c0761q.Q(-1679637798);
                    c0761q.p(false);
                } else {
                    c0761q.Q(-1680616096);
                    int iB = k10.f4913b.b((int) (k10.j().f8482b >> 32));
                    int iB2 = k10.f4913b.b((int) (k10.j().f8482b & 4294967295L));
                    Y0.h hVarA = h10.a(iB);
                    Y0.h hVarA2 = h10.a(Math.max(iB2 - 1, 0));
                    C0331b0 c0331b03 = k10.d;
                    if (c0331b03 == null || !((Boolean) c0331b03.f3229m.getValue()).booleanValue()) {
                        c0761q.Q(-1679975078);
                        c0761q.p(false);
                    } else {
                        c0761q.Q(-1680216289);
                        l9.l.y(true, hVarA, k10, c0761q, ((i10 << 6) & 896) | 6);
                        c0761q.p(false);
                    }
                    C0331b0 c0331b04 = k10.d;
                    if (c0331b04 == null || !((Boolean) c0331b04.f3230n.getValue()).booleanValue()) {
                        c0761q.Q(-1679655654);
                        c0761q.p(false);
                    } else {
                        c0761q.Q(-1679895904);
                        l9.l.y(false, hVarA2, k10, c0761q, ((i10 << 6) & 896) | 6);
                        c0761q.p(false);
                    }
                    c0761q.p(false);
                }
                C0331b0 c0331b05 = k10.d;
                if (c0331b05 != null) {
                    boolean zA = A7.m.a(k10.f4925r.f8481a.l, k10.j().f8481a.l);
                    C0738e0 c0738e0 = c0331b05.l;
                    if (!zA) {
                        c0738e0.setValue(Boolean.FALSE);
                    }
                    if (c0331b05.b()) {
                        if (((Boolean) c0738e0.getValue()).booleanValue()) {
                            k10.o();
                        } else {
                            k10.k();
                        }
                    }
                }
            }
            c0761q.p(false);
            c0761q.p(false);
        } else {
            c0761q.Q(651305535);
            c0761q.p(false);
            k10.k();
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new H(k10, z6, i7);
        }
    }

    public static final void i(C0331b0 c0331b0) {
        T0.E e10 = c0331b0.f3222e;
        if (e10 != null) {
            c0331b0.f3235t.b(T0.y.a((T0.y) c0331b0.d.l, null, 0L, 3));
            T0.z zVar = e10.f8423a;
            AtomicReference atomicReference = zVar.f8484b;
            while (true) {
                if (atomicReference.compareAndSet(e10, null)) {
                    zVar.f8483a.f();
                    break;
                } else if (atomicReference.get() != e10) {
                    break;
                }
            }
        }
        c0331b0.f3222e = null;
    }

    public static final m0.d j(a1.b bVar, int i7, T0.F f10, N0.H h10, boolean z6, int i10) {
        m0.d dVarC = h10 != null ? h10.c(f10.f8426b.b(i7)) : m0.d.f18881e;
        int iO = bVar.O(r0.f3381a);
        float f11 = dVarC.f18882a;
        return new m0.d(z6 ? (i10 - f11) - iO : f11, dVarC.f18883b, z6 ? i10 - f11 : iO + f11, dVarC.d);
    }

    public static final boolean k(int i7, KeyEvent keyEvent) {
        return ((int) (w0.c.R(keyEvent) >> 32)) == i7;
    }

    public static final ArrayList l(List list, InterfaceC2772a interfaceC2772a) {
        L0 l02;
        if (!((Boolean) interfaceC2772a.a()).booleanValue()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0.J j3 = (C0.J) list.get(i7);
            Object objI = j3.i();
            A7.m.d(objI, "null cannot be cast to non-null type androidx.compose.foundation.text.TextRangeLayoutModifier");
            T0 t02 = ((M0) objI).f3184a;
            N0.H h10 = (N0.H) ((J0) t02.l).f3173b.getValue();
            if (h10 == null) {
                l02 = new L0(0, 0, I0.f3169m);
            } else {
                a1.i iVarX = C7.a.X(h10.k(t02.f17859m, t02.f17860n).c());
                l02 = new L0(iVarX.c - iVarX.f11035a, iVarX.d - iVarX.f11036b, new A.I0(iVarX, 27));
            }
            int i10 = l02.f3180m;
            int iMin = Math.min(i10, 262142);
            int iMin2 = Integer.MAX_VALUE;
            int iMin3 = i10 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i10, 262142);
            int iJ = io.sentry.config.a.J(iMin3 == Integer.MAX_VALUE ? iMin : iMin3);
            int i11 = l02.f3181n;
            if (i11 != Integer.MAX_VALUE) {
                iMin2 = Math.min(iJ, i11);
            }
            arrayList.add(new m7.i(j3.b(io.sentry.config.a.e(iMin, iMin3, Math.min(iJ, i11), iMin2)), (A7.o) l02.o));
        }
        return arrayList;
    }

    public static final void m(T0.z zVar, C0331b0 c0331b0, T0.y yVar, T0.m mVar, T0.s sVar) {
        A7.z zVar2 = new A7.z();
        C0022k c0022k = new C0022k(c0331b0.d, c0331b0.f3235t, zVar2, 6);
        T0.t tVar = zVar.f8483a;
        tVar.h(yVar, mVar, c0022k, c0331b0.f3236u);
        T0.E e10 = new T0.E(zVar, tVar);
        zVar.f8484b.set(e10);
        zVar2.l = e10;
        c0331b0.f3222e = e10;
        t(c0331b0, yVar, sVar);
    }

    public static final int n(float f10) {
        return Math.round((float) Math.ceil(f10));
    }

    public static final T0.F o(C0074n c0074n, C0411f c0411f) {
        c0074n.getClass();
        int length = c0411f.l.length();
        int length2 = c0411f.l.length();
        int iMin = Math.min(length, 100);
        for (int i7 = 0; i7 < iMin; i7++) {
            x(i7, length2, i7);
        }
        x(length, length2, length);
        int iMin2 = Math.min(length2, 100);
        for (int i10 = 0; i10 < iMin2; i10++) {
            y(i10, length, i10);
        }
        y(length2, length, length2);
        return new T0.F(c0411f, new C0254m(c0411f.l.length(), c0411f.l.length(), 1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int p(int r9, java.lang.String r10) {
        /*
            boolean r0 = T1.i.c()
            r1 = 0
            if (r0 == 0) goto L13
            T1.i r0 = T1.i.a()
            int r2 = r0.b()
            r3 = 1
            if (r2 != r3) goto L13
            goto L14
        L13:
            r0 = r1
        L14:
            if (r0 == 0) goto L6f
            T1.e r0 = r0.f8498e
            l3.P r2 = r0.f8491b
            r2.getClass()
            r0 = -1
            if (r9 < 0) goto L66
            int r3 = r10.length()
            if (r9 < r3) goto L27
            goto L66
        L27:
            boolean r3 = r10 instanceof android.text.Spanned
            r4 = 0
            if (r3 == 0) goto L43
            r3 = r10
            android.text.Spanned r3 = (android.text.Spanned) r3
            int r5 = r9 + 1
            java.lang.Class<T1.x> r6 = T1.x.class
            java.lang.Object[] r5 = r3.getSpans(r9, r5, r6)
            T1.x[] r5 = (T1.x[]) r5
            int r6 = r5.length
            if (r6 <= 0) goto L43
            r2 = r5[r4]
            int r2 = r3.getSpanEnd(r2)
            goto L67
        L43:
            int r3 = r9 + (-16)
            int r4 = java.lang.Math.max(r4, r3)
            int r3 = r10.length()
            int r5 = r9 + 16
            int r5 = java.lang.Math.min(r3, r5)
            T1.p r8 = new T1.p
            r8.<init>(r9)
            r6 = 2147483647(0x7fffffff, float:NaN)
            r7 = 1
            r3 = r10
            java.lang.Object r2 = r2.z(r3, r4, r5, r6, r7, r8)
            T1.p r2 = (T1.p) r2
            int r2 = r2.f8509n
            goto L67
        L66:
            r2 = r0
        L67:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            if (r2 != r0) goto L6e
            goto L6f
        L6e:
            r1 = r3
        L6f:
            if (r1 == 0) goto L76
            int r9 = r1.intValue()
            return r9
        L76:
            java.text.BreakIterator r0 = java.text.BreakIterator.getCharacterInstance()
            r0.setText(r10)
            int r9 = r0.following(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: K.X.p(int, java.lang.String):int");
    }

    public static final int q(int i7, CharSequence charSequence) {
        int length = charSequence.length();
        while (i7 < length) {
            if (charSequence.charAt(i7) == '\n') {
                return i7;
            }
            i7++;
        }
        return charSequence.length();
    }

    public static final int r(int i7, CharSequence charSequence) {
        while (i7 > 0) {
            if (charSequence.charAt(i7 - 1) == '\n') {
                return i7;
            }
            i7--;
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int s(int r11, java.lang.String r12) {
        /*
            boolean r0 = T1.i.c()
            r1 = 0
            if (r0 == 0) goto L13
            T1.i r0 = T1.i.a()
            int r2 = r0.b()
            r3 = 1
            if (r2 != r3) goto L13
            goto L14
        L13:
            r0 = r1
        L14:
            if (r0 == 0) goto L75
            int r2 = r11 + (-1)
            r3 = 0
            int r2 = java.lang.Math.max(r3, r2)
            T1.e r0 = r0.f8498e
            l3.P r4 = r0.f8491b
            r4.getClass()
            r0 = -1
            if (r2 < 0) goto L6c
            int r5 = r12.length()
            if (r2 < r5) goto L2e
            goto L6c
        L2e:
            boolean r5 = r12 instanceof android.text.Spanned
            if (r5 == 0) goto L49
            r5 = r12
            android.text.Spanned r5 = (android.text.Spanned) r5
            int r6 = r2 + 1
            java.lang.Class<T1.x> r7 = T1.x.class
            java.lang.Object[] r6 = r5.getSpans(r2, r6, r7)
            T1.x[] r6 = (T1.x[]) r6
            int r7 = r6.length
            if (r7 <= 0) goto L49
            r2 = r6[r3]
            int r2 = r5.getSpanStart(r2)
            goto L6d
        L49:
            int r5 = r2 + (-16)
            int r6 = java.lang.Math.max(r3, r5)
            int r3 = r12.length()
            int r5 = r2 + 16
            int r7 = java.lang.Math.min(r3, r5)
            T1.p r10 = new T1.p
            r10.<init>(r2)
            r8 = 2147483647(0x7fffffff, float:NaN)
            r9 = 1
            r5 = r12
            java.lang.Object r2 = r4.z(r5, r6, r7, r8, r9, r10)
            T1.p r2 = (T1.p) r2
            int r2 = r2.f8508m
            goto L6d
        L6c:
            r2 = r0
        L6d:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            if (r2 != r0) goto L74
            goto L75
        L74:
            r1 = r3
        L75:
            if (r1 == 0) goto L7c
            int r11 = r1.intValue()
            return r11
        L7c:
            java.text.BreakIterator r0 = java.text.BreakIterator.getCharacterInstance()
            r0.setText(r12)
            int r11 = r0.preceding(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: K.X.s(int, java.lang.String):int");
    }

    public static final void t(C0331b0 c0331b0, T0.y yVar, T0.s sVar) {
        AbstractC1167g abstractC1167gC = AbstractC1179s.c();
        InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
        AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
        try {
            G0 g0D = c0331b0.d();
            if (g0D == null) {
                return;
            }
            T0.E e10 = c0331b0.f3222e;
            if (e10 == null) {
                return;
            }
            C0.r rVarC = c0331b0.c();
            if (rVarC == null) {
                return;
            }
            u(yVar, c0331b0.f3220a, g0D.f3161a, rVarC, e10, c0331b0.b(), sVar);
        } finally {
            AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
        }
    }

    public static void u(T0.y yVar, C0353m0 c0353m0, N0.H h10, C0.r rVar, T0.E e10, boolean z6, T0.s sVar) {
        m0.d dVar;
        if (z6) {
            int iB = sVar.b(N0.K.d(yVar.f8482b));
            if (iB < h10.f4348a.f4340a.l.length()) {
                dVar = h10.b(iB);
            } else if (iB != 0) {
                dVar = h10.b(iB - 1);
            } else {
                dVar = new m0.d(0.0f, 0.0f, 1.0f, (int) (s0.a(c0353m0.f3339b, c0353m0.f3342g, c0353m0.f3343h, s0.f3382a, 1) & 4294967295L));
            }
            long jN = rVar.N(h6.e.a(dVar.f18882a, dVar.f18883b));
            m0.d dVarA = h6.f.a(h6.e.a(m0.c.d(jN), m0.c.e(jN)), h6.j.f(dVar.c(), dVar.b()));
            if (A7.m.a((T0.E) e10.f8423a.f8484b.get(), e10)) {
                e10.f8424b.a(dVarA);
            }
        }
    }

    public static final g0.o v(g0.o oVar, C0411f c0411f, N0.L l, InterfaceC2782k interfaceC2782k, int i7, boolean z6, int i10, int i11, InterfaceC0702n interfaceC0702n, List list, InterfaceC2782k interfaceC2782k2, InterfaceC1892u interfaceC1892u, InterfaceC2782k interfaceC2782k3) {
        return oVar.k(g0.l.f14684a).k(new TextAnnotatedStringElement(c0411f, l, interfaceC0702n, interfaceC2782k, i7, z6, i10, i11, list, interfaceC2782k2, interfaceC1892u, interfaceC2782k3));
    }

    public static final void w(int i7, int i10) {
        if (i7 > 0 && i10 > 0) {
            if (i7 > i10) {
                throw new IllegalArgumentException(A7.k.i("minLines ", " must be less than or equal to maxLines ", i7, i10).toString());
            }
            return;
        }
        throw new IllegalArgumentException(("both minLines " + i7 + " and maxLines " + i10 + " must be greater than zero").toString());
    }

    public static final void x(int i7, int i10, int i11) {
        if (i7 < 0 || i7 > i10) {
            throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.j(M5.b.l("OffsetMapping.originalToTransformed returned invalid mapping: ", " -> ", i11, i7, " is not in range of transformed text [0, "), i10, ']').toString());
        }
    }

    public static final void y(int i7, int i10, int i11) {
        if (i7 < 0 || i7 > i10) {
            throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.j(M5.b.l("OffsetMapping.transformedToOriginal returned invalid mapping: ", " -> ", i11, i7, " is not in range of original text [0, "), i10, ']').toString());
        }
    }
}
