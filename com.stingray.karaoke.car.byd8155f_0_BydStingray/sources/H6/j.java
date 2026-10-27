package h6;

import A.C0004b;
import A.L;
import A7.A;
import B8.t;
import C0.C0114v;
import C0.K;
import D.AbstractC0120b;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.C0167q;
import E0.InterfaceC0161k;
import F0.W;
import H7.InterfaceC0318c;
import Q.C0509h0;
import T.C0735d;
import T.C0738e0;
import T.C0752l0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.S;
import T.X;
import a2.AbstractC0911e;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.l0;
import b2.AbstractC0992b;
import b2.C0991a;
import e0.C1178r;
import h2.AbstractC1347d;
import h3.y;
import io.sentry.C1458d0;
import io.sentry.E;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.J;
import io.sentry.U;
import io.sentry.U0;
import io.sentry.V1;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import k8.C1708a;
import n3.C1945j;
import n3.N;
import n3.P;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o3.C2028a;
import u.AbstractC2456D;
import u.C2484x;
import w.C2572c;
import w.C2575f;
import w.C2578i;
import w.C2579j;
import w.M;
import w.u;
import x.B0;
import x.E0;
import x.r0;
import x.x0;
import x3.AbstractC2673a;
import y0.v;
import z.C2750a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15580a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Boolean f15581b;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r19v3 */
    /* JADX WARN: Type inference failed for: r19v4 */
    /* JADX WARN: Type inference failed for: r19v7 */
    /* JADX WARN: Type inference failed for: r23v0, types: [java.lang.Object, z7.k] */
    /* JADX WARN: Type inference failed for: r27v0, types: [T.q, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v13, types: [e0.r] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7, types: [int] */
    /* JADX WARN: Type inference failed for: r6v9 */
    public static final void a(x0 x0Var, g0.o oVar, InterfaceC2782k interfaceC2782k, g0.c cVar, InterfaceC2782k interfaceC2782k2, b0.b bVar, C0761q c0761q, int i7) {
        C2579j c2579j;
        C2484x c2484x;
        C1178r c1178r;
        Object obj;
        C2579j c2579j2;
        ?? r13;
        int i10;
        c0761q.S(-114689412);
        int i11 = (i7 & 6) == 0 ? (c0761q.f(x0Var) ? 4 : 2) | i7 : i7;
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.f(cVar) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(interfaceC2782k2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.h(bVar) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            int i12 = i11 & 14;
            boolean z6 = i12 == 4;
            Object objG = c0761q.G();
            Object obj2 = C0753m.f8270a;
            if (z6 || objG == obj2) {
                objG = new C2579j(x0Var, cVar);
                c0761q.a0(objG);
            }
            C2579j c2579j3 = (C2579j) objG;
            boolean z10 = i12 == 4;
            Object objG2 = c0761q.G();
            Object obj3 = objG2;
            if (z10 || objG2 == obj2) {
                Object[] objArr = {x0Var.f23661a.P0()};
                C1178r c1178r2 = new C1178r();
                c1178r2.addAll(AbstractC1996k.b0(objArr));
                c0761q.a0(c1178r2);
                obj3 = c1178r2;
            }
            C1178r c1178r3 = (C1178r) obj3;
            boolean z11 = i12 == 4;
            Object objG3 = c0761q.G();
            if (z11 || objG3 == obj2) {
                long[] jArr = AbstractC2456D.f22584a;
                objG3 = new C2484x();
                c0761q.a0(objG3);
            }
            C2484x c2484x2 = (C2484x) objG3;
            boolean zContains = c1178r3.contains(x0Var.f23661a.P0());
            A9.b bVar2 = x0Var.f23661a;
            if (!zContains) {
                c1178r3.clear();
                c1178r3.add(bVar2.P0());
            }
            Object objP0 = bVar2.P0();
            C0738e0 c0738e0 = x0Var.d;
            if (A7.m.a(objP0, c0738e0.getValue())) {
                if (c1178r3.size() != 1 || !A7.m.a(c1178r3.get(0), bVar2.P0())) {
                    c1178r3.clear();
                    c1178r3.add(bVar2.P0());
                }
                if (c2484x2.f22645e != 1 || c2484x2.b(bVar2.P0())) {
                    c2484x2.a();
                }
                c2579j3.f23106b = cVar;
            }
            if (A7.m.a(bVar2.P0(), c0738e0.getValue()) || c1178r3.contains(c0738e0.getValue())) {
                c2579j = c2579j3;
            } else {
                ListIterator listIterator = c1178r3.listIterator();
                int i13 = 0;
                while (true) {
                    C0167q c0167q = (C0167q) listIterator;
                    ListIterator listIterator2 = listIterator;
                    if (!c0167q.hasNext()) {
                        c2579j = c2579j3;
                        i10 = -1;
                        i13 = -1;
                        break;
                    }
                    Object objB = interfaceC2782k2.b(c0167q.next());
                    c2579j = c2579j3;
                    if (A7.m.a(objB, interfaceC2782k2.b(c0738e0.getValue()))) {
                        i10 = -1;
                        break;
                    } else {
                        i13++;
                        c2579j3 = c2579j;
                        listIterator = listIterator2;
                    }
                }
                if (i13 == i10) {
                    c1178r3.add(c0738e0.getValue());
                } else {
                    c1178r3.set(i13, c0738e0.getValue());
                }
            }
            if (c2484x2.b(c0738e0.getValue()) && c2484x2.b(bVar2.P0())) {
                c0761q.Q(915535767);
                c0761q.p(false);
                c2484x = c2484x2;
                c1178r = c1178r3;
                obj = obj2;
                r13 = 0;
                c2579j2 = c2579j;
            } else {
                boolean z12 = false;
                c0761q.Q(912931457);
                c2484x2.a();
                int size = c1178r3.size();
                int i14 = 0;
                while (i14 < size) {
                    Object obj4 = c1178r3.get(i14);
                    C2484x c2484x3 = c2484x2;
                    C1178r c1178r4 = c1178r3;
                    C2579j c2579j4 = c2579j;
                    c2484x3.i(obj4, b0.g.b(885640742, new W5.o(x0Var, obj4, (InterfaceC2782k) interfaceC2782k, c2579j4, c1178r4, bVar), c0761q));
                    i14++;
                    c2484x2 = c2484x3;
                    obj2 = obj2;
                    z12 = z12;
                    c1178r3 = c1178r4;
                    c2579j = c2579j4;
                }
                c2484x = c2484x2;
                c1178r = c1178r3;
                obj = obj2;
                boolean z13 = z12;
                c2579j2 = c2579j;
                c0761q.p(z13);
                r13 = z13;
            }
            C2579j c2579j5 = c2579j2;
            boolean zF = c0761q.f(x0Var.f()) | c0761q.f(c2579j5);
            Object objG4 = c0761q.G();
            if (zF || objG4 == obj) {
                objG4 = (u) interfaceC2782k.b(c2579j5);
                c0761q.a0(objG4);
            }
            u uVar = (u) objG4;
            c2579j5.getClass();
            boolean zF2 = c0761q.f(c2579j5);
            Object objG5 = c0761q.G();
            if (zF2 || objG5 == obj) {
                objG5 = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG5);
            }
            X x8 = (X) objG5;
            X xR = C0735d.R(uVar.d, c0761q);
            x0 x0Var2 = c2579j5.f23105a;
            if (A7.m.a(x0Var2.f23661a.P0(), x0Var2.d.getValue())) {
                x8.setValue(Boolean.FALSE);
            } else if (xR.getValue() != null) {
                x8.setValue(Boolean.TRUE);
            }
            boolean zBooleanValue = ((Boolean) x8.getValue()).booleanValue();
            g0.o oVarN = g0.l.f14684a;
            if (zBooleanValue) {
                c0761q.Q(249037309);
                r0 r0VarA = B0.a(c2579j5.f23105a, E0.f23401h, null, c0761q, 0, 2);
                boolean zF3 = c0761q.f(r0VarA);
                Object objG6 = c0761q.G();
                if (zF3 || objG6 == obj) {
                    M m10 = (M) xR.getValue();
                    if (m10 == null || m10.f23078a) {
                        oVarN = q.n(oVarN);
                    }
                    objG6 = oVarN.k(new C2578i(c2579j5, r0VarA, xR));
                    c0761q.a0(objG6);
                }
                oVarN = (g0.o) objG6;
                c0761q.p(r13);
            } else {
                c0761q.Q(249353726);
                c0761q.p(r13);
            }
            g0.o oVarK = oVar.k(oVarN);
            Object objG7 = c0761q.G();
            if (objG7 == obj) {
                objG7 = new C2575f(c2579j5);
                c0761q.a0(objG7);
            }
            C2575f c2575f = (C2575f) objG7;
            int i15 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarK);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c2575f, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i15))) {
                AbstractC1347d.l(i15, c0761q, i15, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            c0761q.Q(-1491001814);
            int size2 = c1178r.size();
            ?? r19 = c1178r;
            for (?? r62 = r13; r62 < size2; r62++) {
                ?? r32 = r19;
                Object obj5 = r32.get(r62);
                c0761q.O(1908315325, interfaceC2782k2.b(obj5));
                InterfaceC2785n interfaceC2785n = (InterfaceC2785n) c2484x.e(obj5);
                if (interfaceC2785n == null) {
                    c0761q.Q(-971711888);
                } else {
                    c0761q.Q(1908317105);
                    interfaceC2785n.invoke(c0761q, Integer.valueOf((int) r13));
                }
                c0761q.p(r13);
                c0761q.p(r13);
                r19 = r32;
            }
            c0761q.p(r13);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C2572c(x0Var, oVar, (InterfaceC2782k) interfaceC2782k, cVar, interfaceC2782k2, bVar, i7);
        }
    }

    public static final void b(int i7, C0761q c0761q, g0.o oVar, InterfaceC2782k interfaceC2782k) {
        int i10;
        c0761q.S(-932836462);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0120b.b(c0761q, androidx.compose.ui.draw.a.a(oVar, interfaceC2782k));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W(oVar, interfaceC2782k, i7, 11);
        }
    }

    public static final void c(z.m mVar, InterfaceC2772a interfaceC2772a, g0.o oVar, C0004b c0004b, C0761q c0761q, int i7) {
        int i10;
        g0.o oVar2;
        c0761q.S(645832757);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(mVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        int i11 = i10 | 384;
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(c0004b) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            z.l lVar2 = (z.l) mVar.f24251a.getValue();
            if (!(lVar2 instanceof z.k)) {
                C0754m0 c0754m0R = c0761q.r();
                if (c0754m0R != null) {
                    c0754m0R.d = new C2750a(mVar, interfaceC2772a, lVar, c0004b, i7, 0);
                    return;
                }
                return;
            }
            boolean zF = c0761q.f(lVar2);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                objG = new z.g(AbstractC2673a.T(((z.k) lVar2).f24250a));
                c0761q.a0(objG);
            }
            z.n.c((z.g) objG, interfaceC2772a, lVar, c0004b, c0761q, i11 & 8176);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R2 = c0761q.r();
        if (c0754m0R2 != null) {
            c0754m0R2.d = new C2750a(mVar, interfaceC2772a, oVar2, c0004b, i7, 1);
        }
    }

    public static final void d(z.m mVar, InterfaceC2772a interfaceC2772a, C0004b c0004b, g0.o oVar, boolean z6, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        g0.o oVar2;
        c0761q.S(-84584070);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(mVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(c0004b) ? 256 : 128;
        }
        int i11 = i10 | 3072;
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.h(bVar) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarA = z6 ? v.a(lVar, z.f.f24235a, new z.d(mVar, null)) : lVar;
            K kE = AbstractC0133o.e(g0.b.l, true);
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
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            bVar.invoke(c0761q, Integer.valueOf((i11 >> 15) & 14));
            c(mVar, interfaceC2772a, null, c0004b, c0761q, (i11 & 126) | ((i11 << 3) & 7168));
            c0761q.p(true);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0509h0(mVar, interfaceC2772a, c0004b, oVar2, z6, bVar, i7);
        }
    }

    public static final void e(C1945j c1945j, d0.h hVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1579360880);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1945j) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(hVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(bVar) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            C0735d.b(new C0752l0[]{c2.b.f12350a.a(c1945j), AbstractC0911e.f11054a.a(c1945j), AndroidCompositionLocals_androidKt.f11689e.a(c1945j)}, b0.g.b(-52928304, new L(hVar, 16, bVar), c0761q), c0761q, 56);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(c1945j, hVar, bVar, i7, 10);
        }
    }

    public static final long f(float f10, float f11) {
        return (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
    }

    public static final void g(d0.h hVar, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1211832233);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(hVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.R(1729797275);
            l0 l0VarA = c2.b.a(c0761q);
            if (l0VarA == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
            }
            AbstractC0992b abstractC0992bE = l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b;
            InterfaceC0318c interfaceC0318cB = A.f443a.b(C2028a.class);
            c0761q.R(1673618944);
            f0 f0VarI = C7.a.I(l0VarA, interfaceC0318cB, null, null, abstractC0992bE);
            c0761q.p(false);
            c0761q.p(false);
            C2028a c2028a = (C2028a) f0VarI;
            c2028a.d = new WeakReference(hVar);
            hVar.e(c2028a.c, bVar, c0761q, ((i10 << 6) & 896) | (i10 & 112));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W(hVar, bVar, i7, 9);
        }
    }

    public static io.sentry.instrumentation.file.e h(FileInputStream fileInputStream, File file) {
        E e10 = E.f15834a;
        U uI = io.sentry.util.f.f16921a ? e10.i() : e10.b();
        return new io.sentry.instrumentation.file.e(new t(file, uI != null ? uI.j("file.read") : null, fileInputStream, e10.q(), 21));
    }

    public static io.sentry.instrumentation.file.e i(FileInputStream fileInputStream, FileDescriptor fileDescriptor) {
        U uI = io.sentry.util.f.f16921a ? U0.b().i() : U0.b().b();
        return new io.sentry.instrumentation.file.e(new t((Object) null, uI != null ? uI.j("file.read") : null, fileInputStream, U0.b().q(), 21), fileDescriptor);
    }

    public static final boolean j(int i7, int i10) {
        return i7 == i10;
    }

    public static final long k(long j3) {
        if (j3 != 9205357640488583168L) {
            return e.a(Float.intBitsToFloat((int) (j3 >> 32)) / 2.0f, Float.intBitsToFloat((int) (j3 & 4294967295L)) / 2.0f);
        }
        throw new IllegalStateException("Size is unspecified");
    }

    public static String l(Class cls) {
        LinkedHashMap linkedHashMap = P.f19843b;
        String strValue = (String) linkedHashMap.get(cls);
        if (strValue == null) {
            N n10 = (N) cls.getAnnotation(N.class);
            strValue = n10 != null ? n10.value() : null;
            if (strValue == null || strValue.length() <= 0) {
                throw new IllegalArgumentException("No @Navigator.Name annotation found for ".concat(cls.getSimpleName()).toString());
            }
            linkedHashMap.put(cls, strValue);
        }
        A7.m.c(strValue);
        return strValue;
    }

    public static boolean m(F1 f12, String str) {
        return n(str, f12 != null ? f12.getLogger() : null) != null;
    }

    public static Class n(String str, io.sentry.K k10) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e10) {
            if (k10 == null) {
                return null;
            }
            k10.s(EnumC1495p1.DEBUG, "Class not available:".concat(str), e10);
            return null;
        } catch (UnsatisfiedLinkError e11) {
            if (k10 == null) {
                return null;
            }
            k10.s(EnumC1495p1.ERROR, "Failed to load (UnsatisfiedLinkError) ".concat(str), e11);
            return null;
        } catch (Throwable th) {
            if (k10 == null) {
                return null;
            }
            k10.s(EnumC1495p1.ERROR, "Failed to initialize ".concat(str), th);
            return null;
        }
    }

    public static final boolean o(String str) {
        A7.m.f(str, "method");
        return (str.equals("GET") || str.equals("HEAD")) ? false : true;
    }

    public static C1708a p(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        G7.d dVar = new G7.d(1, dataInputStream.readInt(), 1);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(dVar, 10));
        G7.c it = dVar.iterator();
        while (it.f2715n) {
            it.b();
            arrayList.add(Integer.valueOf(dataInputStream.readInt()));
        }
        int[] iArrY0 = AbstractC1998m.y0(arrayList);
        return new C1708a(Arrays.copyOf(iArrY0, iArrY0.length));
    }

    public static M.t q(J j3, String str, List list, U u4) {
        F1 f1Q = j3.q();
        Object obj = null;
        if (!f1Q.isTraceSampling() || !f.e(str, f1Q.getTracePropagationTargets())) {
            return null;
        }
        F1 f1Q2 = j3.q();
        if (u4 != null && !u4.r()) {
            return new M.t(u4.f(), u4.w(list));
        }
        e7.f fVar = new e7.f((char) 0, 19);
        fVar.f14480m = null;
        j3.l(new C1458d0(fVar, 7, f1Q2));
        y yVar = (y) fVar.f14480m;
        if (yVar == null) {
            return null;
        }
        A2.c cVar = (A2.c) yVar.o;
        return new M.t(new y((io.sentry.protocol.t) yVar.f15499m, (V1) yVar.f15500n, obj, 3), cVar != null ? B2.q.d(cVar, list) : null);
    }
}
