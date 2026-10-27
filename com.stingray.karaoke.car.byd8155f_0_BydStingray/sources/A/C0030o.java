package A;

import E0.AbstractC0156f;
import E0.InterfaceC0162l;
import E0.InterfaceC0173x;
import S8.AbstractC0727y;
import S8.EnumC0726x;

/* JADX INFO: renamed from: A.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0030o extends g0.n implements InterfaceC0173x, InterfaceC0162l {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f222A;
    public InterfaceC0012f B;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public C0.r f224D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public m0.d f225E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f226F;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f228H;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public EnumC0017h0 f229y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final S0 f230z;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0006c f223C = new C0006c(0);

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public long f227G = 0;

    public C0030o(EnumC0017h0 enumC0017h0, S0 s02, boolean z6, InterfaceC0012f interfaceC0012f) {
        this.f229y = enumC0017h0;
        this.f230z = s02;
        this.f222A = z6;
        this.B = interfaceC0012f;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0072 A[EDGE_INSN: B:42:0x0072->B:25:0x0072 BREAK  A[LOOP:0: B:8:0x001b->B:44:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[LOOP:0: B:8:0x001b->B:44:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final float H0(A.C0030o r12, A.InterfaceC0012f r13) {
        /*
            long r0 = r12.f227G
            r2 = 0
            boolean r0 = a1.j.a(r0, r2)
            r1 = 0
            if (r0 == 0) goto Ld
            goto Lb3
        Ld:
            A.c r0 = r12.f223C
            V.d r0 = r0.f158a
            int r2 = r0.f9279n
            r3 = 1
            r4 = 0
            if (r2 <= 0) goto L71
            int r2 = r2 - r3
            java.lang.Object[] r0 = r0.l
            r5 = r4
        L1b:
            r6 = r0[r2]
            A.j r6 = (A.C0020j) r6
            H.e r6 = r6.f187a
            java.lang.Object r6 = r6.a()
            m0.d r6 = (m0.d) r6
            if (r6 == 0) goto L6c
            float r7 = r6.c()
            float r8 = r6.b()
            long r7 = h6.j.f(r7, r8)
            long r9 = r12.f227G
            long r9 = H7.H.S(r9)
            A.h0 r11 = r12.f229y
            int r11 = r11.ordinal()
            if (r11 == 0) goto L58
            if (r11 != r3) goto L52
            float r7 = m0.f.d(r7)
            float r8 = m0.f.d(r9)
            int r7 = java.lang.Float.compare(r7, r8)
            goto L64
        L52:
            A9.k r12 = new A9.k
            r12.<init>()
            throw r12
        L58:
            float r7 = m0.f.b(r7)
            float r8 = m0.f.b(r9)
            int r7 = java.lang.Float.compare(r7, r8)
        L64:
            if (r7 > 0) goto L68
            r5 = r6
            goto L6c
        L68:
            if (r5 != 0) goto L72
            r5 = r6
            goto L72
        L6c:
            int r2 = r2 + (-1)
            if (r2 >= 0) goto L1b
            goto L72
        L71:
            r5 = r4
        L72:
            if (r5 != 0) goto L80
            boolean r0 = r12.f226F
            if (r0 == 0) goto L7c
            m0.d r4 = r12.I0()
        L7c:
            if (r4 != 0) goto L7f
            goto Lb3
        L7f:
            r5 = r4
        L80:
            long r0 = r12.f227G
            long r0 = H7.H.S(r0)
            A.h0 r12 = r12.f229y
            int r12 = r12.ordinal()
            if (r12 == 0) goto La5
            if (r12 != r3) goto L9f
            float r12 = r5.c
            float r2 = r5.f18882a
            float r12 = r12 - r2
            float r0 = m0.f.d(r0)
            float r12 = r13.a(r2, r12, r0)
        L9d:
            r1 = r12
            goto Lb3
        L9f:
            A9.k r12 = new A9.k
            r12.<init>()
            throw r12
        La5:
            float r12 = r5.d
            float r2 = r5.f18883b
            float r12 = r12 - r2
            float r0 = m0.f.b(r0)
            float r12 = r13.a(r2, r12, r0)
            goto L9d
        Lb3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: A.C0030o.H0(A.o, A.f):float");
    }

    public final m0.d I0() {
        if (!this.f14695x) {
            return null;
        }
        E0.d0 d0VarS = AbstractC0156f.s(this);
        C0.r rVar = this.f224D;
        if (rVar != null) {
            if (!rVar.D()) {
                rVar = null;
            }
            if (rVar != null) {
                return d0VarS.o(rVar, false);
            }
        }
        return null;
    }

    public final boolean J0(m0.d dVar, long j3) {
        long jL0 = L0(dVar, j3);
        return Math.abs(m0.c.d(jL0)) <= 0.5f && Math.abs(m0.c.e(jL0)) <= 0.5f;
    }

    public final void K0() {
        InterfaceC0012f interfaceC0012f = this.B;
        if (interfaceC0012f == null) {
            interfaceC0012f = (InterfaceC0012f) AbstractC0156f.i(this, AbstractC0018i.f183a);
        }
        if (this.f228H) {
            throw new IllegalStateException("launchAnimation called when previous animation was running");
        }
        AbstractC0727y.w(v0(), null, EnumC0726x.o, new C0028n(this, new v1(interfaceC0012f.b()), interfaceC0012f, null), 1);
    }

    public final long L0(m0.d dVar, long j3) {
        long jS = H7.H.S(j3);
        int iOrdinal = this.f229y.ordinal();
        if (iOrdinal == 0) {
            InterfaceC0012f interfaceC0012f = this.B;
            if (interfaceC0012f == null) {
                interfaceC0012f = (InterfaceC0012f) AbstractC0156f.i(this, AbstractC0018i.f183a);
            }
            float f10 = dVar.d;
            float f11 = dVar.f18883b;
            return h6.e.a(0.0f, interfaceC0012f.a(f11, f10 - f11, m0.f.b(jS)));
        }
        if (iOrdinal != 1) {
            throw new A9.k();
        }
        InterfaceC0012f interfaceC0012f2 = this.B;
        if (interfaceC0012f2 == null) {
            interfaceC0012f2 = (InterfaceC0012f) AbstractC0156f.i(this, AbstractC0018i.f183a);
        }
        float f12 = dVar.c;
        float f13 = dVar.f18882a;
        return h6.e.a(interfaceC0012f2.a(f13, f12 - f13, m0.f.d(jS)), 0.0f);
    }

    @Override // E0.InterfaceC0173x
    public final void u(long j3) {
        int iG;
        m0.d dVarI0;
        long j10 = this.f227G;
        this.f227G = j3;
        int iOrdinal = this.f229y.ordinal();
        if (iOrdinal == 0) {
            iG = A7.m.g((int) (j3 & 4294967295L), (int) (4294967295L & j10));
        } else {
            if (iOrdinal != 1) {
                throw new A9.k();
            }
            iG = A7.m.g((int) (j3 >> 32), (int) (j10 >> 32));
        }
        if (iG < 0 && (dVarI0 = I0()) != null) {
            m0.d dVar = this.f225E;
            if (dVar == null) {
                dVar = dVarI0;
            }
            if (!this.f228H && !this.f226F && J0(dVar, j10) && !J0(dVarI0, j3)) {
                this.f226F = true;
                K0();
            }
            this.f225E = dVarI0;
        }
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
