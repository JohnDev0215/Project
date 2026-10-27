package u2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: u2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2500c implements InterfaceC2490A, InterfaceC2522z {
    public final InterfaceC2490A l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public InterfaceC2522z f22798m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C2499b[] f22799n = new C2499b[0];
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f22800p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f22801q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C2502e f22802r;

    public C2500c(InterfaceC2490A interfaceC2490A, boolean z6, long j3, long j10) {
        this.l = interfaceC2490A;
        this.o = z6 ? j3 : -9223372036854775807L;
        this.f22800p = j3;
        this.f22801q = j10;
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        long j10 = this.f22800p;
        if (j3 == j10) {
            return j10;
        }
        long j11 = AbstractC1343A.j(b0Var.f19625a, 0L, j3 - j10);
        long j12 = this.f22801q;
        long j13 = AbstractC1343A.j(b0Var.f19626b, 0L, j12 == Long.MIN_VALUE ? Long.MAX_VALUE : j12 - j3);
        if (j11 != b0Var.f19625a || j13 != b0Var.f19626b) {
            b0Var = new n2.b0(j11, j13);
        }
        return this.l.a(j3, b0Var);
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        this.f22798m = interfaceC2522z;
        this.l.b(this, j3);
    }

    @Override // u2.InterfaceC2522z
    public final void c(InterfaceC2490A interfaceC2490A) {
        if (this.f22802r != null) {
            return;
        }
        InterfaceC2522z interfaceC2522z = this.f22798m;
        interfaceC2522z.getClass();
        interfaceC2522z.c(this);
    }

    @Override // u2.b0
    public final boolean d() {
        return this.l.d();
    }

    @Override // u2.InterfaceC2522z
    public final void e(b0 b0Var) {
        InterfaceC2522z interfaceC2522z = this.f22798m;
        interfaceC2522z.getClass();
        interfaceC2522z.e(this);
    }

    public final boolean f() {
        return this.o != -9223372036854775807L;
    }

    @Override // u2.b0
    public final long g() {
        long jG = this.l.g();
        if (jG != Long.MIN_VALUE) {
            long j3 = this.f22801q;
            if (j3 == Long.MIN_VALUE || jG < j3) {
                return jG;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        if (f()) {
            long j3 = this.o;
            this.o = -9223372036854775807L;
            long jH = h();
            return jH != -9223372036854775807L ? jH : j3;
        }
        long jH2 = this.l.h();
        if (jH2 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        AbstractC1346c.j(jH2 >= this.f22800p);
        long j10 = this.f22801q;
        AbstractC1346c.j(j10 == Long.MIN_VALUE || jH2 <= j10);
        return jH2;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        return this.l.i(g7);
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        return this.l.j();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:69:0x00e4. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0124  */
    @Override // u2.InterfaceC2490A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long k(w2.q[] r16, boolean[] r17, u2.a0[] r18, boolean[] r19, long r20) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.C2500c.k(w2.q[], boolean[], u2.a0[], boolean[], long):long");
    }

    @Override // u2.b0
    public final long l() {
        long jL = this.l.l();
        if (jL != Long.MIN_VALUE) {
            long j3 = this.f22801q;
            if (j3 == Long.MIN_VALUE || jL < j3) {
                return jL;
            }
        }
        return Long.MIN_VALUE;
    }

    @Override // u2.InterfaceC2490A
    public final void m() throws C2502e {
        C2502e c2502e = this.f22802r;
        if (c2502e != null) {
            throw c2502e;
        }
        this.l.m();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    @Override // u2.InterfaceC2490A
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long o(long r6) {
        /*
            r5 = this;
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5.o = r0
            u2.b[] r0 = r5.f22799n
            int r1 = r0.length
            r2 = 0
            r3 = r2
        Lc:
            if (r3 >= r1) goto L17
            r4 = r0[r3]
            if (r4 == 0) goto L14
            r4.f22796m = r2
        L14:
            int r3 = r3 + 1
            goto Lc
        L17:
            u2.A r0 = r5.l
            long r0 = r0.o(r6)
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 == 0) goto L33
            long r6 = r5.f22800p
            int r6 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r6 < 0) goto L34
            long r5 = r5.f22801q
            r3 = -9223372036854775808
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 == 0) goto L33
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 > 0) goto L34
        L33:
            r2 = 1
        L34:
            h2.AbstractC1346c.j(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u2.C2500c.o(long):long");
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
        this.l.p(j3);
    }

    @Override // u2.b0
    public final void q(long j3) {
        this.l.q(j3);
    }
}
