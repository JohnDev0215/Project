package x0;

import E0.AbstractC0156f;
import E0.s0;
import S8.InterfaceC0725w;
import e0.C1183w;
import g0.n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends n implements s0, InterfaceC2660a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final String f23687A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2660a f23688y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public d f23689z;

    public g(InterfaceC2660a interfaceC2660a, d dVar) {
        this.f23688y = interfaceC2660a;
        this.f23689z = dVar == null ? new d() : dVar;
        this.f23687A = "androidx.compose.ui.input.nestedscroll.NestedScrollNode";
    }

    @Override // g0.n
    public final void A0() {
        d dVar = this.f23689z;
        if (dVar.f23678a == this) {
            dVar.f23678a = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // x0.InterfaceC2660a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object B(long r10, q7.InterfaceC2189c r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof x0.f
            if (r0 == 0) goto L13
            r0 = r12
            x0.f r0 = (x0.f) r0
            int r1 = r0.f23686p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f23686p = r1
            goto L1a
        L13:
            x0.f r0 = new x0.f
            s7.c r12 = (s7.AbstractC2410c) r12
            r0.<init>(r9, r12)
        L1a:
            java.lang.Object r12 = r0.f23685n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f23686p
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L3f
            if (r2 == r5) goto L37
            if (r2 != r4) goto L2f
            long r9 = r0.f23684m
            h8.AbstractC1386c.u(r12)
            goto L7e
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L37:
            long r10 = r0.f23684m
            x0.g r9 = r0.l
            h8.AbstractC1386c.u(r12)
            goto L5f
        L3f:
            h8.AbstractC1386c.u(r12)
            boolean r12 = r9.f14695x
            if (r12 == 0) goto L4f
            if (r12 == 0) goto L4f
            E0.s0 r12 = E0.AbstractC0156f.k(r9)
            x0.g r12 = (x0.g) r12
            goto L50
        L4f:
            r12 = r3
        L50:
            if (r12 == 0) goto L68
            r0.l = r9
            r0.f23684m = r10
            r0.f23686p = r5
            java.lang.Object r12 = r12.B(r10, r0)
            if (r12 != r1) goto L5f
            return r1
        L5f:
            a1.o r12 = (a1.o) r12
            long r5 = r12.f11044a
        L63:
            r7 = r10
            r11 = r9
            r9 = r5
            r5 = r7
            goto L6b
        L68:
            r5 = 0
            goto L63
        L6b:
            x0.a r11 = r11.f23688y
            long r5 = a1.o.d(r5, r9)
            r0.l = r3
            r0.f23684m = r9
            r0.f23686p = r4
            java.lang.Object r12 = r11.B(r5, r0)
            if (r12 != r1) goto L7e
            return r1
        L7e:
            a1.o r12 = (a1.o) r12
            long r11 = r12.f11044a
            long r9 = a1.o.e(r9, r11)
            a1.o r11 = new a1.o
            r11.<init>(r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.g.B(long, q7.c):java.lang.Object");
    }

    public final InterfaceC0725w H0() {
        g gVar = this.f14695x ? (g) AbstractC0156f.k(this) : null;
        if (gVar != null) {
            return gVar.H0();
        }
        InterfaceC0725w interfaceC0725w = this.f23689z.c;
        if (interfaceC0725w != null) {
            return interfaceC0725w;
        }
        throw new IllegalStateException("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
    }

    @Override // x0.InterfaceC2660a
    public final long N(int i7, long j3, long j10) {
        long jN = this.f23688y.N(i7, j3, j10);
        boolean z6 = this.f14695x;
        g gVar = null;
        if (z6 && z6) {
            gVar = (g) AbstractC0156f.k(this);
        }
        g gVar2 = gVar;
        return m0.c.h(jN, gVar2 != null ? gVar2.N(i7, m0.c.h(j3, jN), m0.c.g(j10, jN)) : 0L);
    }

    @Override // x0.InterfaceC2660a
    public final long n(long j3, int i7) {
        boolean z6 = this.f14695x;
        g gVar = null;
        if (z6 && z6) {
            gVar = (g) AbstractC0156f.k(this);
        }
        long jN = gVar != null ? gVar.n(j3, i7) : 0L;
        return m0.c.h(jN, this.f23688y.n(m0.c.g(j3, jN), i7));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @Override // x0.InterfaceC2660a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object q0(long r16, long r18, q7.InterfaceC2189c r20) {
        /*
            r15 = this;
            r0 = r15
            r1 = r20
            boolean r2 = r1 instanceof x0.e
            if (r2 == 0) goto L16
            r2 = r1
            x0.e r2 = (x0.e) r2
            int r3 = r2.f23683q
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L16
            int r3 = r3 - r4
            r2.f23683q = r3
            goto L1d
        L16:
            x0.e r2 = new x0.e
            s7.c r1 = (s7.AbstractC2410c) r1
            r2.<init>(r15, r1)
        L1d:
            java.lang.Object r1 = r2.o
            r7.a r9 = r7.EnumC2303a.l
            int r3 = r2.f23683q
            r10 = 2
            r4 = 1
            if (r3 == 0) goto L46
            if (r3 == r4) goto L3a
            if (r3 != r10) goto L32
            long r2 = r2.f23680m
            h8.AbstractC1386c.u(r1)
            goto L95
        L32:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3a:
            long r3 = r2.f23681n
            long r5 = r2.f23680m
            x0.g r0 = r2.l
            h8.AbstractC1386c.u(r1)
            r13 = r3
            r11 = r5
            goto L63
        L46:
            h8.AbstractC1386c.u(r1)
            x0.a r3 = r0.f23688y
            r2.l = r0
            r11 = r16
            r2.f23680m = r11
            r13 = r18
            r2.f23681n = r13
            r2.f23683q = r4
            r4 = r16
            r6 = r18
            r8 = r2
            java.lang.Object r1 = r3.q0(r4, r6, r8)
            if (r1 != r9) goto L63
            return r9
        L63:
            a1.o r1 = (a1.o) r1
            long r3 = r1.f11044a
            boolean r1 = r0.f14695x
            r5 = 0
            if (r1 == 0) goto L75
            if (r1 == 0) goto L75
            E0.s0 r0 = E0.AbstractC0156f.k(r0)
            x0.g r0 = (x0.g) r0
            goto L76
        L75:
            r0 = r5
        L76:
            if (r0 == 0) goto L9b
            long r6 = a1.o.e(r11, r3)
            long r11 = a1.o.d(r13, r3)
            r2.l = r5
            r2.f23680m = r3
            r2.f23683q = r10
            r15 = r0
            r16 = r6
            r18 = r11
            r20 = r2
            java.lang.Object r1 = r15.q0(r16, r18, r20)
            if (r1 != r9) goto L94
            return r9
        L94:
            r2 = r3
        L95:
            a1.o r1 = (a1.o) r1
            long r0 = r1.f11044a
            r3 = r2
            goto L9d
        L9b:
            r0 = 0
        L9d:
            long r0 = a1.o.e(r3, r0)
            a1.o r2 = new a1.o
            r2.<init>(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: x0.g.q0(long, long, q7.c):java.lang.Object");
    }

    @Override // E0.s0
    public final Object r() {
        return this.f23687A;
    }

    @Override // g0.n
    public final void z0() {
        d dVar = this.f23689z;
        dVar.f23678a = this;
        dVar.f23679b = new C1183w(this, 15);
        dVar.c = v0();
    }
}
