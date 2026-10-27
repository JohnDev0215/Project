package E;

import A.C0006c;
import A.C0043v;
import A.K0;
import G.AbstractC0301k;
import G.C0293c;
import G.C0296f;
import G.C0300j;
import G.U;
import G.V;
import G.h0;
import S8.AbstractC0727y;
import T.C0735d;
import T.C0738e0;
import T.S;
import T.X;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import l3.P;
import n7.AbstractC1998m;
import r7.EnumC2303a;
import x.AbstractC2631e;
import x.C2648n;
import x.C2649o;
import x.E0;
import y.j0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class D implements K0 {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f1301x = l9.l.V(w.f1446m, r.o);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1303b;
    public t c;
    public final v d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1307h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public E0.F f1310k;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final V f1313p;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0738e0 f1317t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f1318u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final X f1319v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C2648n f1320w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0144a f1302a = new C0144a(2, 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0149f f1304e = new C0149f(this);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f1305f = C0735d.O(G.f1325b, S.f8222n);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C.k f1306g = new C.k();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0043v f1308i = new C0043v(new A.F(this, 6));

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f1309j = true;
    public final y l = new y(this, 0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0293c f1311m = new C0293c();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final androidx.compose.foundation.lazy.layout.a f1312n = new androidx.compose.foundation.lazy.layout.a();
    public final C0006c o = new C0006c(1);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0149f f1314q = new C0149f(this);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final G.S f1315r = new G.S();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final X f1316s = G.E.g();

    public D(int i7, int i10) {
        this.d = new v(i7, i10, 0);
        this.f1313p = new V(new x(i7, 0, this));
        Boolean bool = Boolean.FALSE;
        S s6 = S.f8224q;
        this.f1317t = C0735d.O(bool, s6);
        this.f1318u = C0735d.O(bool, s6);
        this.f1319v = G.E.g();
        this.f1320w = new C2648n(E0.f23396a, Float.valueOf(0.0f), new C2649o(0.0f), Long.MIN_VALUE, Long.MIN_VALUE, false);
    }

    @Override // A.K0
    public final boolean a() {
        return ((Boolean) this.f1318u.getValue()).booleanValue();
    }

    @Override // A.K0
    public final boolean b() {
        return this.f1308i.b();
    }

    @Override // A.K0
    public final boolean c() {
        return ((Boolean) this.f1317t.getValue()).booleanValue();
    }

    @Override // A.K0
    public final float d(float f10) {
        return this.f1308i.d(f10);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // A.K0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(y.j0 r6, z7.InterfaceC2785n r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof E.z
            if (r0 == 0) goto L13
            r0 = r8
            E.z r0 = (E.z) r0
            int r1 = r0.f1454q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1454q = r1
            goto L18
        L13:
            E.z r0 = new E.z
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f1454q
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L32
            if (r2 != r3) goto L2a
            h8.AbstractC1386c.u(r8)
            goto L62
        L2a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L32:
            z7.n r7 = r0.f1452n
            y.j0 r6 = r0.f1451m
            E.D r5 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L50
        L3c:
            h8.AbstractC1386c.u(r8)
            r0.l = r5
            r0.f1451m = r6
            r0.f1452n = r7
            r0.f1454q = r4
            G.c r8 = r5.f1311m
            java.lang.Object r8 = r8.i(r0)
            if (r8 != r1) goto L50
            return r1
        L50:
            A.v r5 = r5.f1308i
            r8 = 0
            r0.l = r8
            r0.f1451m = r8
            r0.f1452n = r8
            r0.f1454q = r3
            java.lang.Object r5 = r5.e(r6, r7, r0)
            if (r5 != r1) goto L62
            return r1
        L62:
            m7.q r5 = m7.q.f19267a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: E.D.e(y.j0, z7.n, s7.c):java.lang.Object");
    }

    public final Object f(int i7, int i10, s7.i iVar) throws Throwable {
        a1.b bVar = ((t) this.f1305f.getValue()).f1413h;
        float f10 = AbstractC0301k.f2596a;
        C0149f c0149f = this.f1304e;
        Object objE = c0149f.f1351a.e(j0.l, new C0300j(i7, bVar, c0149f, i10, null), iVar);
        EnumC2303a enumC2303a = EnumC2303a.l;
        m7.q qVar = m7.q.f19267a;
        if (objE != enumC2303a) {
            objE = qVar;
        }
        if (objE != enumC2303a) {
            objE = qVar;
        }
        return objE == enumC2303a ? objE : qVar;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.util.Collection, java.util.List] */
    public final void g(t tVar, boolean z6, boolean z10) {
        if (!z6 && this.f1303b) {
            this.c = tVar;
            return;
        }
        if (z6) {
            this.f1303b = true;
        }
        u uVar = tVar.f1408a;
        this.f1318u.setValue(Boolean.valueOf(((uVar != null ? uVar.f1422a : 0) == 0 && tVar.f1409b == 0) ? false : true));
        this.f1317t.setValue(Boolean.valueOf(tVar.c));
        this.f1307h -= tVar.d;
        this.f1305f.setValue(tVar);
        v vVar = this.d;
        if (z10) {
            int i7 = tVar.f1409b;
            if (i7 < 0.0f) {
                vVar.getClass();
                throw new IllegalStateException(("scrollOffset should be non-negative (" + i7 + ')').toString());
            }
            vVar.c.j(i7);
        } else {
            vVar.getClass();
            vVar.f1444e = uVar != null ? uVar.l : null;
            if (vVar.d || tVar.f1417m > 0) {
                vVar.d = true;
                int i10 = tVar.f1409b;
                if (i10 < 0.0f) {
                    throw new IllegalStateException(("scrollOffset should be non-negative (" + i10 + ')').toString());
                }
                vVar.a(uVar != null ? uVar.f1422a : 0, i10);
            }
            if (this.f1309j) {
                C0144a c0144a = this.f1302a;
                if (c0144a.f1327b != -1) {
                    ?? r12 = tVar.f1415j;
                    if (!r12.isEmpty()) {
                        if (c0144a.f1327b != (c0144a.c ? ((u) AbstractC1998m.h0(r12)).f1422a + 1 : ((u) AbstractC1998m.Y(r12)).f1422a - 1)) {
                            c0144a.f1327b = -1;
                            U u4 = (U) c0144a.d;
                            if (u4 != null) {
                                u4.cancel();
                            }
                            c0144a.d = null;
                        }
                    }
                }
            }
        }
        if (z6) {
            float fY = tVar.f1413h.y(G.f1324a);
            float f10 = tVar.f1410e;
            if (f10 <= fY) {
                return;
            }
            AbstractC1167g abstractC1167gC = AbstractC1179s.c();
            InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
            AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
            try {
                float fFloatValue = ((Number) this.f1320w.f23569m.getValue()).floatValue();
                C2648n c2648n = this.f1320w;
                boolean z11 = c2648n.f23572q;
                X8.c cVar = tVar.f1412g;
                if (z11) {
                    this.f1320w = AbstractC2631e.j(c2648n, fFloatValue - f10);
                    AbstractC0727y.w(cVar, null, null, new B(this, null), 3);
                } else {
                    this.f1320w = new C2648n(E0.f23396a, Float.valueOf(-f10), null, 60);
                    AbstractC0727y.w(cVar, null, null, new C(this, null), 3);
                }
                AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
            } catch (Throwable th) {
                AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                throw th;
            }
        }
    }

    public final t h() {
        return (t) this.f1305f.getValue();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, java.util.List] */
    public final void i(float f10, t tVar) {
        U u4;
        U u10;
        Object obj;
        U u11;
        if (this.f1309j) {
            C0144a c0144a = this.f1302a;
            if (tVar.f1415j.isEmpty()) {
                return;
            }
            boolean z6 = f10 < 0.0f;
            ?? r32 = tVar.f1415j;
            int i7 = z6 ? ((u) AbstractC1998m.h0(r32)).f1422a + 1 : ((u) AbstractC1998m.Y(r32)).f1422a - 1;
            if (i7 < 0 || i7 >= tVar.f1417m) {
                return;
            }
            if (i7 != c0144a.f1327b) {
                if (c0144a.c != z6 && (u11 = (U) c0144a.d) != null) {
                    u11.cancel();
                }
                c0144a.c = z6;
                c0144a.f1327b = i7;
                D d = this.f1314q.f1351a;
                AbstractC1167g abstractC1167gC = AbstractC1179s.c();
                InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
                AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
                try {
                    long j3 = ((t) d.f1305f.getValue()).f1414i;
                    AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                    V v10 = d.f1313p;
                    P p10 = v10.c;
                    if (p10 != null) {
                        h0 h0Var = new h0(p10, i7, j3, v10.f2526b);
                        ((G.j0) p10.o).b(h0Var);
                        obj = h0Var;
                    } else {
                        obj = C0296f.f2556a;
                    }
                    c0144a.d = obj;
                } catch (Throwable th) {
                    AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                    throw th;
                }
            }
            if (!z6) {
                if (tVar.f1416k - ((u) AbstractC1998m.Y(r32)).f1433p >= f10 || (u4 = (U) c0144a.d) == null) {
                    return;
                }
                u4.a();
                return;
            }
            u uVar = (u) AbstractC1998m.h0(r32);
            if (((uVar.f1433p + uVar.f1434q) + tVar.f1420q) - tVar.l >= (-f10) || (u10 = (U) c0144a.d) == null) {
                return;
            }
            u10.a();
        }
    }

    public final Object j(int i7, int i10, s7.i iVar) throws Throwable {
        Object objE = e(j0.l, new A(this, i7, i10, null), iVar);
        return objE == EnumC2303a.l ? objE : m7.q.f19267a;
    }

    public final void k(int i7, int i10) {
        v vVar = this.d;
        if (vVar.f1443b.i() != i7 || vVar.c.i() != i10) {
            this.f1312n.f();
        }
        vVar.a(i7, i10);
        vVar.f1444e = null;
        E0.F f10 = this.f1310k;
        if (f10 != null) {
            f10.l();
        }
    }
}
