package A;

import E0.AbstractC0164n;
import E0.InterfaceC0162l;
import y0.C2722B;
import y0.C2730h;
import y0.EnumC2731i;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class V extends AbstractC0164n implements E0.n0, InterfaceC0162l {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EnumC0017h0 f120A;
    public A7.o B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f121C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public C.k f122D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public U8.l f123E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public C.b f124F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f125G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public C2722B f126H;

    /* JADX WARN: Multi-variable type inference failed */
    public V(InterfaceC2782k interfaceC2782k, boolean z6, C.k kVar, EnumC0017h0 enumC0017h0) {
        this.f120A = enumC0017h0;
        this.B = (A7.o) interfaceC2782k;
        this.f121C = z6;
        this.f122D = kVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object K0(A.V r5, s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            r5.getClass()
            boolean r0 = r6 instanceof A.P
            if (r0 == 0) goto L16
            r0 = r6
            A.P r0 = (A.P) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.o = r1
            goto L1b
        L16:
            A.P r0 = new A.P
            r0.<init>(r5, r6)
        L1b:
            java.lang.Object r6 = r0.f86m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            A.V r5 = r0.l
            h8.AbstractC1386c.u(r6)
            goto L4f
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            h8.AbstractC1386c.u(r6)
            C.b r6 = r5.f124F
            if (r6 == 0) goto L52
            C.k r2 = r5.f122D
            if (r2 == 0) goto L4f
            C.a r4 = new C.a
            r4.<init>(r6)
            r0.l = r5
            r0.o = r3
            java.lang.Object r6 = r2.a(r4, r0)
            if (r6 != r1) goto L4f
            goto L59
        L4f:
            r6 = 0
            r5.f124F = r6
        L52:
            r0 = 0
            r5.Q0(r0)
            m7.q r1 = m7.q.f19267a
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: A.V.K0(A.V, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object L0(A.V r6, A.C0049y r7, s7.AbstractC2410c r8) throws java.lang.Throwable {
        /*
            r6.getClass()
            boolean r0 = r8 instanceof A.Q
            if (r0 == 0) goto L16
            r0 = r8
            A.Q r0 = (A.Q) r0
            int r1 = r0.f92q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f92q = r1
            goto L1b
        L16:
            A.Q r0 = new A.Q
            r0.<init>(r6, r8)
        L1b:
            java.lang.Object r8 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f92q
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 == r4) goto L3b
            if (r2 != r3) goto L33
            C.b r6 = r0.f90n
            A.y r7 = r0.f89m
            A.V r0 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L7a
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            A.y r7 = r0.f89m
            A.V r6 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L60
        L43:
            h8.AbstractC1386c.u(r8)
            C.b r8 = r6.f124F
            if (r8 == 0) goto L60
            C.k r2 = r6.f122D
            if (r2 == 0) goto L60
            C.a r5 = new C.a
            r5.<init>(r8)
            r0.l = r6
            r0.f89m = r7
            r0.f92q = r4
            java.lang.Object r8 = r2.a(r5, r0)
            if (r8 != r1) goto L60
            goto L85
        L60:
            C.b r8 = new C.b
            r8.<init>()
            C.k r2 = r6.f122D
            if (r2 == 0) goto L7c
            r0.l = r6
            r0.f89m = r7
            r0.f90n = r8
            r0.f92q = r3
            java.lang.Object r0 = r2.a(r8, r0)
            if (r0 != r1) goto L78
            goto L85
        L78:
            r0 = r6
            r6 = r8
        L7a:
            r8 = r6
            r6 = r0
        L7c:
            r6.f124F = r8
            long r7 = r7.f299a
            r6.P0(r7)
            m7.q r1 = m7.q.f19267a
        L85:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: A.V.L0(A.V, A.y, s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object M0(A.V r5, A.C0051z r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            r5.getClass()
            boolean r0 = r7 instanceof A.S
            if (r0 == 0) goto L16
            r0 = r7
            A.S r0 = (A.S) r0
            int r1 = r0.f99p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f99p = r1
            goto L1b
        L16:
            A.S r0 = new A.S
            r0.<init>(r5, r7)
        L1b:
            java.lang.Object r7 = r0.f98n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f99p
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            A.z r6 = r0.f97m
            A.V r5 = r0.l
            h8.AbstractC1386c.u(r7)
            goto L53
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            h8.AbstractC1386c.u(r7)
            C.b r7 = r5.f124F
            if (r7 == 0) goto L56
            C.k r2 = r5.f122D
            if (r2 == 0) goto L53
            C.c r4 = new C.c
            r4.<init>(r7)
            r0.l = r5
            r0.f97m = r6
            r0.f99p = r3
            java.lang.Object r7 = r2.a(r4, r0)
            if (r7 != r1) goto L53
            goto L5d
        L53:
            r7 = 0
            r5.f124F = r7
        L56:
            long r6 = r6.f302a
            r5.Q0(r6)
            m7.q r1 = m7.q.f19267a
        L5d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: A.V.M0(A.V, A.z, s7.c):java.lang.Object");
    }

    @Override // g0.n
    public final void A0() {
        this.f125G = false;
        N0();
    }

    public final void N0() {
        C.b bVar = this.f124F;
        if (bVar != null) {
            C.k kVar = this.f122D;
            if (kVar != null) {
                kVar.b(new C.a(bVar));
            }
            this.f124F = null;
        }
    }

    public abstract Object O0(T t10, U u4);

    public abstract void P0(long j3);

    public abstract void Q0(long j3);

    public void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        if (this.f121C && this.f126H == null) {
            O o = new O(this, null);
            C2730h c2730h2 = y0.v.f24101a;
            C2722B c2722b = new C2722B(null, null, null, o);
            H0(c2722b);
            this.f126H = c2722b;
        }
        C2722B c2722b2 = this.f126H;
        if (c2722b2 != null) {
            c2722b2.R(c2730h, enumC2731i, j3);
        }
    }

    public abstract boolean R0();

    /* JADX WARN: Multi-variable type inference failed */
    public final void S0(InterfaceC2782k interfaceC2782k, boolean z6, C.k kVar, EnumC0017h0 enumC0017h0, boolean z10) {
        C2722B c2722b;
        this.B = (A7.o) interfaceC2782k;
        boolean z11 = true;
        if (this.f121C != z6) {
            this.f121C = z6;
            if (!z6) {
                N0();
                C2722B c2722b2 = this.f126H;
                if (c2722b2 != null) {
                    I0(c2722b2);
                }
                this.f126H = null;
            }
            z10 = true;
        }
        if (!A7.m.a(this.f122D, kVar)) {
            N0();
            this.f122D = kVar;
        }
        if (this.f120A != enumC0017h0) {
            this.f120A = enumC0017h0;
        } else {
            z11 = z10;
        }
        if (!z11 || (c2722b = this.f126H) == null) {
            return;
        }
        c2722b.J0();
    }

    @Override // E0.n0
    public final void e0() {
        C2722B c2722b = this.f126H;
        if (c2722b != null) {
            c2722b.e0();
        }
    }
}
