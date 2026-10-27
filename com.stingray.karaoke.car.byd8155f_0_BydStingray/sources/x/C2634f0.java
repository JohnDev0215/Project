package x;

import S8.C0712i;
import T.C0730a0;
import T.C0735d;
import T.C0738e0;
import e0.C1183w;
import e0.C1184x;
import n3.C1945j;
import n7.AbstractC1996k;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import u.C2482v;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: x.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2634f0 extends A9.b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final C2649o f23515D = new C2649o(0.0f);

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final C2649o f23516E = new C2649o(1.0f);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final V f23517A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final V f23518C;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f23519n;
    public final C0738e0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f23520p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public x0 f23521q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f23522r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C1183w f23523s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0730a0 f23524t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C0712i f23525u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final b9.c f23526v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final T f23527w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f23528x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final C2482v f23529y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public U f23530z;

    public C2634f0(C1945j c1945j) {
        super(9);
        T.S s6 = T.S.f8224q;
        this.f23519n = C0735d.O(c1945j, s6);
        this.o = C0735d.O(c1945j, s6);
        this.f23520p = c1945j;
        this.f23523s = new C1183w(this, 13);
        this.f23524t = C0735d.L(0.0f);
        this.f23526v = b9.d.a();
        this.f23527w = new T();
        this.f23528x = Long.MIN_VALUE;
        this.f23529y = new C2482v();
        this.f23517A = new V(this, 1);
        this.f23518C = new V(this, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a1(x.C2634f0 r10) {
        /*
            x.x0 r0 = r10.f23521q
            if (r0 != 0) goto L5
            goto L6e
        L5:
            x.U r1 = r10.f23530z
            r2 = 0
            if (r1 != 0) goto L5e
            long r3 = r10.f23522r
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 <= 0) goto L5d
            T.a0 r1 = r10.f23524t
            float r3 = r1.i()
            r4 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 != 0) goto L1f
            goto L5d
        L1f:
            T.e0 r3 = r10.o
            java.lang.Object r3 = r3.getValue()
            T.e0 r4 = r10.f23519n
            java.lang.Object r4 = r4.getValue()
            boolean r3 = A7.m.a(r3, r4)
            if (r3 == 0) goto L32
            goto L5d
        L32:
            x.U r3 = new x.U
            r3.<init>()
            float r4 = r1.i()
            r3.d = r4
            long r4 = r10.f23522r
            r3.f23458g = r4
            double r4 = (double) r4
            float r6 = r1.i()
            double r6 = (double) r6
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r8 = r8 - r6
            double r8 = r8 * r4
            long r4 = C7.a.Y(r8)
            r3.f23459h = r4
            x.o r4 = r3.f23456e
            float r1 = r1.i()
            r5 = 0
            r4.e(r1, r5)
            r1 = r3
            goto L5e
        L5d:
            r1 = r2
        L5e:
            if (r1 == 0) goto L6c
            long r3 = r10.f23522r
            r1.f23458g = r3
            u.v r3 = r10.f23529y
            r3.a(r1)
            r0.n(r1)
        L6c:
            r10.f23530z = r2
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2634f0.a1(x.f0):void");
    }

    public static final void b1(C2634f0 c2634f0, U u4, long j3) {
        c2634f0.getClass();
        long j10 = u4.f23454a + j3;
        u4.f23454a = j10;
        long j11 = u4.f23459h;
        if (j10 >= j11) {
            u4.d = 1.0f;
            return;
        }
        H0 h02 = u4.f23455b;
        if (h02 == null) {
            float fA = u4.f23456e.a(0);
            float f10 = j10 / j11;
            D0 d02 = E0.f23396a;
            u4.d = (1.0f * f10) + ((1 - f10) * fA);
            return;
        }
        C2649o c2649o = f23516E;
        C2649o c2649o2 = u4.f23457f;
        if (c2649o2 == null) {
            c2649o2 = f23515D;
        }
        u4.d = AbstractC2673a.u(((C2649o) h02.o(j10, u4.f23456e, c2649o, c2649o2)).a(0), 0.0f, 1.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object c1(x.C2634f0 r10, s7.AbstractC2410c r11) throws java.lang.Throwable {
        /*
            r10.getClass()
            boolean r0 = r11 instanceof x.Y
            if (r0 == 0) goto L16
            r0 = r11
            x.Y r0 = (x.Y) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.o = r1
            goto L1b
        L16:
            x.Y r0 = new x.Y
            r0.<init>(r10, r11)
        L1b:
            java.lang.Object r11 = r0.f23470m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            m7.q r3 = m7.q.f19267a
            r4 = 2
            r5 = 1
            r6 = -9223372036854775808
            if (r2 == 0) goto L3c
            if (r2 == r5) goto L36
            if (r2 != r4) goto L2e
            goto L36
        L2e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L36:
            x.f0 r10 = r0.l
            h8.AbstractC1386c.u(r11)
            goto L79
        L3c:
            h8.AbstractC1386c.u(r11)
            u.v r11 = r10.f23529y
            int r11 = r11.f22638b
            if (r11 != 0) goto L4b
            x.U r11 = r10.f23530z
            if (r11 != 0) goto L4b
        L49:
            r1 = r3
            goto L92
        L4b:
            q7.h r11 = r0.getContext()
            float r11 = x.AbstractC2631e.l(r11)
            r2 = 0
            int r11 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r11 != 0) goto L5e
            r10.g1()
            r10.f23528x = r6
            goto L49
        L5e:
            long r8 = r10.f23528x
            int r11 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r11 != 0) goto L79
            r0.l = r10
            r0.o = r5
            q7.h r11 = r0.getContext()
            T.T r11 = T.C0735d.I(r11)
            x.V r2 = r10.f23517A
            java.lang.Object r11 = r11.o(r2, r0)
            if (r11 != r1) goto L79
            goto L92
        L79:
            u.v r11 = r10.f23529y
            int r11 = r11.f22638b
            if (r11 == 0) goto L80
            goto L84
        L80:
            x.U r11 = r10.f23530z
            if (r11 == 0) goto L8f
        L84:
            r0.l = r10
            r0.o = r4
            java.lang.Object r11 = r10.f1(r0)
            if (r11 != r1) goto L79
            goto L92
        L8f:
            r10.f23528x = r6
            goto L49
        L92:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2634f0.c1(x.f0, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d1(x.C2634f0 r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            r7.getClass()
            boolean r0 = r8 instanceof x.C2630d0
            if (r0 == 0) goto L16
            r0 = r8
            x.d0 r0 = (x.C2630d0) r0
            int r1 = r0.f23502p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f23502p = r1
            goto L1b
        L16:
            x.d0 r0 = new x.d0
            r0.<init>(r7, r8)
        L1b:
            java.lang.Object r8 = r0.f23501n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f23502p
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L44
            if (r2 == r5) goto L3a
            if (r2 != r4) goto L32
            java.lang.Object r7 = r0.f23500m
            x.f0 r0 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L80
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            java.lang.Object r7 = r0.f23500m
            x.f0 r2 = r0.l
            h8.AbstractC1386c.u(r8)
            r8 = r7
            r7 = r2
            goto L5c
        L44:
            h8.AbstractC1386c.u(r8)
            T.e0 r8 = r7.f23519n
            java.lang.Object r8 = r8.getValue()
            r0.l = r7
            r0.f23500m = r8
            r0.f23502p = r5
            b9.c r2 = r7.f23526v
            java.lang.Object r2 = r2.c(r3, r0)
            if (r2 != r1) goto L5c
            goto L88
        L5c:
            r0.l = r7
            r0.f23500m = r8
            r0.f23502p = r4
            S8.i r2 = new S8.i
            q7.c r0 = h6.e.k(r0)
            r2.<init>(r5, r0)
            r2.s()
            r7.f23525u = r2
            b9.c r0 = r7.f23526v
            r0.e(r3)
            java.lang.Object r0 = r2.q()
            if (r0 != r1) goto L7c
            goto L88
        L7c:
            r6 = r0
            r0 = r7
            r7 = r8
            r8 = r6
        L80:
            boolean r7 = A7.m.a(r8, r7)
            if (r7 == 0) goto L89
            m7.q r1 = m7.q.f19267a
        L88:
            return r1
        L89:
            r7 = -9223372036854775808
            r0.f23528x = r7
            java.util.concurrent.CancellationException r7 = new java.util.concurrent.CancellationException
            java.lang.String r8 = "targetState while waiting for composition"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2634f0.d1(x.f0, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object e1(x.C2634f0 r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            r7.getClass()
            boolean r0 = r8 instanceof x.C2632e0
            if (r0 == 0) goto L16
            r0 = r8
            x.e0 r0 = (x.C2632e0) r0
            int r1 = r0.f23511p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f23511p = r1
            goto L1b
        L16:
            x.e0 r0 = new x.e0
            r0.<init>(r7, r8)
        L1b:
            java.lang.Object r8 = r0.f23510n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f23511p
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L42
            if (r2 == r5) goto L3a
            if (r2 != r4) goto L32
            java.lang.Object r7 = r0.f23509m
            x.f0 r0 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L89
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            java.lang.Object r7 = r0.f23509m
            x.f0 r2 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L5c
        L42:
            h8.AbstractC1386c.u(r8)
            T.e0 r8 = r7.f23519n
            java.lang.Object r8 = r8.getValue()
            r0.l = r7
            r0.f23509m = r8
            r0.f23511p = r5
            b9.c r2 = r7.f23526v
            java.lang.Object r2 = r2.c(r3, r0)
            if (r2 != r1) goto L5a
            goto L91
        L5a:
            r2 = r7
            r7 = r8
        L5c:
            java.lang.Object r8 = r2.f23520p
            boolean r8 = A7.m.a(r7, r8)
            b9.c r6 = r2.f23526v
            if (r8 == 0) goto L6a
            r6.e(r3)
            goto L8f
        L6a:
            r0.l = r2
            r0.f23509m = r7
            r0.f23511p = r4
            S8.i r8 = new S8.i
            q7.c r0 = h6.e.k(r0)
            r8.<init>(r5, r0)
            r8.s()
            r2.f23525u = r8
            r6.e(r3)
            java.lang.Object r8 = r8.q()
            if (r8 != r1) goto L88
            goto L91
        L88:
            r0 = r2
        L89:
            boolean r1 = A7.m.a(r8, r7)
            if (r1 == 0) goto L92
        L8f:
            m7.q r1 = m7.q.f19267a
        L91:
            return r1
        L92:
            r1 = -9223372036854775808
            r0.f23528x = r1
            java.util.concurrent.CancellationException r0 = new java.util.concurrent.CancellationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "snapTo() was canceled because state was changed to "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r8 = " instead of "
            r1.append(r8)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: x.C2634f0.e1(x.f0, s7.c):java.lang.Object");
    }

    @Override // A9.b
    public final Object P0() {
        return this.o.getValue();
    }

    @Override // A9.b
    public final Object Q0() {
        return this.f23519n.getValue();
    }

    @Override // A9.b
    public final void W0(Object obj) {
        this.o.setValue(obj);
    }

    @Override // A9.b
    public final void X0(x0 x0Var) {
        x0 x0Var2 = this.f23521q;
        if (x0Var2 == null || A7.m.a(x0Var, x0Var2)) {
            this.f23521q = x0Var;
            return;
        }
        throw new IllegalStateException("An instance of SeekableTransitionState has been used in different Transitions. Previous instance: " + this.f23521q + ", new instance: " + x0Var);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // A9.b
    public final void Y0() {
        this.f23521q = null;
        ((C1184x) B0.f23389a.getValue()).c(this);
    }

    public final Object f1(AbstractC2410c abstractC2410c) {
        float fL = AbstractC2631e.l(abstractC2410c.getContext());
        m7.q qVar = m7.q.f19267a;
        if (fL <= 0.0f) {
            g1();
            return qVar;
        }
        this.B = fL;
        Object objO = C0735d.I(abstractC2410c.getContext()).o(this.f23518C, abstractC2410c);
        return objO == EnumC2303a.l ? objO : qVar;
    }

    public final void g1() {
        x0 x0Var = this.f23521q;
        if (x0Var != null) {
            x0Var.c();
        }
        C2482v c2482v = this.f23529y;
        AbstractC1996k.J(c2482v.f22637a, null, 0, c2482v.f22638b);
        c2482v.f22638b = 0;
        if (this.f23530z != null) {
            this.f23530z = null;
            j1(1.0f);
            i1();
        }
    }

    public final Object h1(float f10, Object obj, s7.i iVar) {
        if (0.0f > f10 || f10 > 1.0f) {
            throw new IllegalArgumentException("Expecting fraction between 0 and 1. Got " + f10);
        }
        x0 x0Var = this.f23521q;
        m7.q qVar = m7.q.f19267a;
        if (x0Var == null) {
            return qVar;
        }
        Object objA = T.a(this.f23527w, new C2626b0(obj, this.f23519n.getValue(), this, x0Var, f10, null), iVar);
        return objA == EnumC2303a.l ? objA : qVar;
    }

    public final void i1() {
        x0 x0Var = this.f23521q;
        if (x0Var == null) {
            return;
        }
        x0Var.m(C7.a.Y(((double) this.f23524t.i()) * ((Number) x0Var.l.getValue()).longValue()));
    }

    public final void j1(float f10) {
        this.f23524t.j(f10);
    }
}
