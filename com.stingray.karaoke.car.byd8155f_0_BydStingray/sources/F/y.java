package F;

import A.C0006c;
import A.C0043v;
import A.EnumC0017h0;
import A.K0;
import B2.O;
import E.C0144a;
import E0.F;
import G.C0293c;
import G.C0296f;
import G.E;
import G.U;
import G.V;
import G.h0;
import G.j0;
import T.C0735d;
import T.C0738e0;
import T.S;
import T.X;
import e0.AbstractC1167g;
import e0.AbstractC1179s;
import java.util.ArrayList;
import java.util.List;
import l3.P;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class y implements K0 {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f1972t = l9.l.V(v.f1965m, n.o);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E.v f1974b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1975e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public F f1978h;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final V f1982m;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0738e0 f1986r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0738e0 f1987s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0144a f1973a = new C0144a(2, 1);
    public final C0738e0 c = C0735d.O(z.f1988a, S.f8222n);
    public final C.k d = new C.k();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0043v f1976f = new C0043v(new A.F(this, 9));

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1977g = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final E.y f1979i = new E.y(this, 1);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0293c f1980j = new C0293c();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final androidx.compose.foundation.lazy.layout.a f1981k = new androidx.compose.foundation.lazy.layout.a();
    public final C0006c l = new C0006c(1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final O f1983n = new O(this, 6);
    public final G.S o = new G.S();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final X f1984p = E.g();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final X f1985q = E.g();

    public y(int i7, int i10) {
        this.f1974b = new E.v(i7, i10, 1);
        this.f1982m = new V(new E.x(i7, 1, this));
        Boolean bool = Boolean.FALSE;
        S s6 = S.f8224q;
        this.f1986r = C0735d.O(bool, s6);
        this.f1987s = C0735d.O(bool, s6);
    }

    @Override // A.K0
    public final boolean a() {
        return ((Boolean) this.f1987s.getValue()).booleanValue();
    }

    @Override // A.K0
    public final boolean b() {
        return this.f1976f.b();
    }

    @Override // A.K0
    public final boolean c() {
        return ((Boolean) this.f1986r.getValue()).booleanValue();
    }

    @Override // A.K0
    public final float d(float f10) {
        return this.f1976f.d(f10);
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
            boolean r0 = r8 instanceof F.w
            if (r0 == 0) goto L13
            r0 = r8
            F.w r0 = (F.w) r0
            int r1 = r0.f1969q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1969q = r1
            goto L18
        L13:
            F.w r0 = new F.w
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f1969q
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
            z7.n r7 = r0.f1967n
            y.j0 r6 = r0.f1966m
            F.y r5 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L50
        L3c:
            h8.AbstractC1386c.u(r8)
            r0.l = r5
            r0.f1966m = r6
            r0.f1967n = r7
            r0.f1969q = r4
            G.c r8 = r5.f1980j
            java.lang.Object r8 = r8.i(r0)
            if (r8 != r1) goto L50
            return r1
        L50:
            A.v r5 = r5.f1976f
            r8 = 0
            r0.l = r8
            r0.f1966m = r8
            r0.f1967n = r8
            r0.f1969q = r3
            java.lang.Object r5 = r5.e(r6, r7, r0)
            if (r5 != r1) goto L62
            return r1
        L62:
            m7.q r5 = m7.q.f19267a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: F.y.e(y.j0, z7.n, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009e  */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.lang.Object, java.util.Collection, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(F.o r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F.y.f(F.o, boolean):void");
    }

    public final o g() {
        return (o) this.c.getValue();
    }

    /* JADX WARN: Type inference failed for: r15v3, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, java.util.List] */
    public final void h(float f10, o oVar) {
        int i7;
        int i10;
        int i11;
        int i12;
        int i13;
        Object obj;
        int i14;
        if (this.f1977g) {
            C0144a c0144a = this.f1973a;
            c0144a.getClass();
            if (oVar.f1920g.isEmpty()) {
                return;
            }
            boolean z6 = f10 < 0.0f;
            EnumC0017h0 enumC0017h0 = EnumC0017h0.l;
            EnumC0017h0 enumC0017h02 = oVar.f1924k;
            ?? r92 = oVar.f1920g;
            if (z6) {
                p pVar = (p) AbstractC1998m.h0(r92);
                i7 = (enumC0017h02 == enumC0017h0 ? pVar.f1944v : pVar.f1945w) + 1;
                i10 = ((p) AbstractC1998m.h0(r92)).f1927a + 1;
            } else {
                p pVar2 = (p) AbstractC1998m.Y(r92);
                i7 = (enumC0017h02 == enumC0017h0 ? pVar2.f1944v : pVar2.f1945w) - 1;
                i10 = ((p) AbstractC1998m.Y(r92)).f1927a - 1;
            }
            if (i10 < 0 || i10 >= oVar.f1923j) {
                return;
            }
            int i15 = c0144a.f1327b;
            V.d dVar = (V.d) c0144a.d;
            if (i7 != i15 && i7 >= 0) {
                if (c0144a.c != z6 && (i14 = dVar.f9279n) > 0) {
                    Object[] objArr = dVar.l;
                    int i16 = 0;
                    do {
                        ((U) objArr[i16]).cancel();
                        i16++;
                    } while (i16 < i14);
                }
                c0144a.c = z6;
                c0144a.f1327b = i7;
                dVar.g();
                O o = this.f1983n;
                o.getClass();
                ArrayList arrayList = new ArrayList();
                y yVar = (y) o.f527m;
                AbstractC1167g abstractC1167gC = AbstractC1179s.c();
                InterfaceC2782k interfaceC2782kF = abstractC1167gC != null ? abstractC1167gC.f() : null;
                AbstractC1167g abstractC1167gD = AbstractC1179s.d(abstractC1167gC);
                try {
                    List list = (List) ((o) yVar.c.getValue()).f1919f.b(Integer.valueOf(i7));
                    int size = list.size();
                    int i17 = 0;
                    while (i17 < size) {
                        m7.i iVar = (m7.i) list.get(i17);
                        List list2 = list;
                        V v10 = yVar.f1982m;
                        y yVar2 = yVar;
                        int iIntValue = ((Number) iVar.l).intValue();
                        long j3 = ((a1.a) iVar.f19259m).f11027a;
                        P p10 = v10.c;
                        if (p10 != null) {
                            i13 = size;
                            h0 h0Var = new h0(p10, iIntValue, j3, v10.f2526b);
                            ((j0) p10.o).b(h0Var);
                            obj = h0Var;
                        } else {
                            i13 = size;
                            obj = C0296f.f2556a;
                        }
                        arrayList.add(obj);
                        i17++;
                        list = list2;
                        yVar = yVar2;
                        size = i13;
                    }
                    AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                    dVar.d(dVar.f9279n, arrayList);
                } catch (Throwable th) {
                    AbstractC1179s.f(abstractC1167gC, abstractC1167gD, interfaceC2782kF);
                    throw th;
                }
            }
            if (!z6) {
                if (oVar.f1921h - io.sentry.config.a.i0((p) AbstractC1998m.Y(r92), enumC0017h02) >= f10 || (i11 = dVar.f9279n) <= 0) {
                    return;
                }
                Object[] objArr2 = dVar.l;
                int i18 = 0;
                do {
                    ((U) objArr2[i18]).a();
                    i18++;
                } while (i18 < i11);
                return;
            }
            p pVar3 = (p) AbstractC1998m.h0(r92);
            if (((io.sentry.config.a.i0(pVar3, enumC0017h02) + ((int) (enumC0017h02 == enumC0017h0 ? pVar3.f1942t & 4294967295L : pVar3.f1942t >> 32))) + oVar.f1925m) - oVar.f1922i >= (-f10) || (i12 = dVar.f9279n) <= 0) {
                return;
            }
            Object[] objArr3 = dVar.l;
            int i19 = 0;
            do {
                ((U) objArr3[i19]).a();
                i19++;
            } while (i19 < i12);
        }
    }
}
