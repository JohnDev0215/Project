package R;

import Q.C0521n0;
import Q.EnumC0542y0;
import T.C0730a0;
import T.C0735d;
import T.C0738e0;
import T.S;
import x.C2640i0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f7326a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final K7.K f7327b;
    public final InterfaceC2782k c;
    public final D d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final M.t f7328e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0738e0 f7329f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final T.D f7330g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final T.D f7331h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0730a0 f7332i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0730a0 f7333j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0738e0 f7334k;
    public final C0738e0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final u f7335m;

    public w(EnumC0542y0 enumC0542y0, InterfaceC2782k interfaceC2782k, K7.K k10, InterfaceC2782k interfaceC2782k2) {
        C2640i0 c2640i0 = AbstractC0677e.f7290a;
        this.f7326a = interfaceC2782k;
        this.f7327b = k10;
        this.c = interfaceC2782k2;
        this.d = new D();
        M.t tVar = new M.t();
        tVar.f4151m = this;
        tVar.l = new C0521n0(this, 1);
        this.f7328e = tVar;
        S s6 = S.f8224q;
        this.f7329f = C0735d.O(enumC0542y0, s6);
        this.f7330g = C0735d.G(new C0687o(this, 4));
        this.f7331h = C0735d.G(new C0687o(this, 2));
        this.f7332i = C0735d.L(Float.NaN);
        C0735d.F(s6, new C0687o(this, 3));
        this.f7333j = C0735d.L(0.0f);
        this.f7334k = C0735d.O(null, s6);
        this.l = C0735d.O(new E(n7.v.l), s6);
        this.f7335m = new u(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.lang.Object, java.util.Map] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(java.lang.Object r7, y.j0 r8, R.C0680h r9, s7.AbstractC2410c r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: R.w.a(java.lang.Object, y.j0, R.h, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(y.j0 r7, R.v r8, s7.AbstractC2410c r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof R.C0686n
            if (r0 == 0) goto L13
            r0 = r9
            R.n r0 = (R.C0686n) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            R.n r0 = new R.n
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.f7309m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1056964608(0x3f000000, float:0.5)
            r4 = 1
            if (r2 == 0) goto L35
            if (r2 != r4) goto L2d
            R.w r6 = r0.l
            h8.AbstractC1386c.u(r9)     // Catch: java.lang.Throwable -> L2b
            goto L53
        L2b:
            r7 = move-exception
            goto L8c
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            h8.AbstractC1386c.u(r9)
            R.D r9 = r6.d     // Catch: java.lang.Throwable -> L2b
            R.q r2 = new R.q     // Catch: java.lang.Throwable -> L2b
            r5 = 0
            r2.<init>(r8, r6, r5)     // Catch: java.lang.Throwable -> L2b
            r0.l = r6     // Catch: java.lang.Throwable -> L2b
            r0.o = r4     // Catch: java.lang.Throwable -> L2b
            r9.getClass()     // Catch: java.lang.Throwable -> L2b
            R.C r8 = new R.C     // Catch: java.lang.Throwable -> L2b
            r8.<init>(r7, r9, r2, r5)     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r7 = S8.AbstractC0727y.h(r8, r0)     // Catch: java.lang.Throwable -> L2b
            if (r7 != r1) goto L53
            return r1
        L53:
            R.E r7 = r6.d()
            T.a0 r8 = r6.f7332i
            float r9 = r8.i()
            java.lang.Object r7 = r7.a(r9)
            if (r7 == 0) goto L89
            float r8 = r8.i()
            R.E r9 = r6.d()
            float r9 = r9.c(r7)
            float r8 = r8 - r9
            float r8 = java.lang.Math.abs(r8)
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 > 0) goto L89
            z7.k r8 = r6.c
            java.lang.Object r8 = r8.b(r7)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L89
            r6.f(r7)
        L89:
            m7.q r6 = m7.q.f19267a
            return r6
        L8c:
            R.E r8 = r6.d()
            T.a0 r9 = r6.f7332i
            float r0 = r9.i()
            java.lang.Object r8 = r8.a(r0)
            if (r8 == 0) goto Lc2
            float r9 = r9.i()
            R.E r0 = r6.d()
            float r0 = r0.c(r8)
            float r9 = r9 - r0
            float r9 = java.lang.Math.abs(r9)
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 > 0) goto Lc2
            z7.k r9 = r6.c
            java.lang.Object r9 = r9.b(r8)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto Lc2
            r6.f(r8)
        Lc2:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: R.w.b(y.j0, R.v, s7.c):java.lang.Object");
    }

    public final Object c(float f10, float f11, Object obj) {
        Object objB;
        E eD = d();
        float fC = eD.c(obj);
        float fFloatValue = ((Number) this.f7327b.a()).floatValue();
        if (fC == f10 || Float.isNaN(fC)) {
            return obj;
        }
        InterfaceC2782k interfaceC2782k = this.f7326a;
        if (fC < f10) {
            if (f11 >= fFloatValue) {
                Object objB2 = eD.b(f10, true);
                A7.m.c(objB2);
                return objB2;
            }
            objB = eD.b(f10, true);
            A7.m.c(objB);
            if (f10 < Math.abs(Math.abs(((Number) interfaceC2782k.b(Float.valueOf(Math.abs(eD.c(objB) - fC)))).floatValue()) + fC)) {
                return obj;
            }
        } else {
            if (f11 <= (-fFloatValue)) {
                Object objB3 = eD.b(f10, false);
                A7.m.c(objB3);
                return objB3;
            }
            objB = eD.b(f10, false);
            A7.m.c(objB);
            float fAbs = Math.abs(fC - Math.abs(((Number) interfaceC2782k.b(Float.valueOf(Math.abs(fC - eD.c(objB))))).floatValue()));
            if (f10 < 0.0f) {
                if (Math.abs(f10) < fAbs) {
                    return obj;
                }
            } else if (f10 > fAbs) {
                return obj;
            }
        }
        return objB;
    }

    public final E d() {
        return (E) this.l.getValue();
    }

    public final float e() {
        C0730a0 c0730a0 = this.f7332i;
        if (Float.isNaN(c0730a0.i())) {
            throw new IllegalStateException("The offset was read before being initialized. Did you access the offset in a phase before layout, like effects or composition?");
        }
        return c0730a0.i();
    }

    public final void f(Object obj) {
        this.f7329f.setValue(obj);
    }

    public final void g(Object obj) {
        this.f7334k.setValue(obj);
    }
}
