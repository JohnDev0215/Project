package u2;

/* JADX INFO: loaded from: classes.dex */
public final class m0 implements InterfaceC2490A, InterfaceC2522z {
    public final InterfaceC2490A l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public InterfaceC2522z f22876n;

    public m0(InterfaceC2490A interfaceC2490A, long j3) {
        this.l = interfaceC2490A;
        this.f22875m = j3;
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        long j10 = this.f22875m;
        return this.l.a(j3 - j10, b0Var) + j10;
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        this.f22876n = interfaceC2522z;
        this.l.b(this, j3 - this.f22875m);
    }

    @Override // u2.InterfaceC2522z
    public final void c(InterfaceC2490A interfaceC2490A) {
        InterfaceC2522z interfaceC2522z = this.f22876n;
        interfaceC2522z.getClass();
        interfaceC2522z.c(this);
    }

    @Override // u2.b0
    public final boolean d() {
        return this.l.d();
    }

    @Override // u2.InterfaceC2522z
    public final void e(b0 b0Var) {
        InterfaceC2522z interfaceC2522z = this.f22876n;
        interfaceC2522z.getClass();
        interfaceC2522z.e(this);
    }

    @Override // u2.b0
    public final long g() {
        long jG = this.l.g();
        if (jG == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return this.f22875m + jG;
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        long jH = this.l.h();
        if (jH == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.f22875m + jH;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        n2.F f10 = new n2.F();
        f10.f19535b = g7.f19537b;
        f10.c = g7.c;
        f10.f19534a = g7.f19536a - this.f22875m;
        return this.l.i(new n2.G(f10));
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        return this.l.j();
    }

    @Override // u2.InterfaceC2490A
    public final long k(w2.q[] qVarArr, boolean[] zArr, a0[] a0VarArr, boolean[] zArr2, long j3) {
        a0[] a0VarArr2 = new a0[a0VarArr.length];
        int i7 = 0;
        while (true) {
            a0 a0Var = null;
            if (i7 >= a0VarArr.length) {
                break;
            }
            l0 l0Var = (l0) a0VarArr[i7];
            if (l0Var != null) {
                a0Var = l0Var.l;
            }
            a0VarArr2[i7] = a0Var;
            i7++;
        }
        long j10 = this.f22875m;
        long jK = this.l.k(qVarArr, zArr, a0VarArr2, zArr2, j3 - j10);
        for (int i10 = 0; i10 < a0VarArr.length; i10++) {
            a0 a0Var2 = a0VarArr2[i10];
            if (a0Var2 == null) {
                a0VarArr[i10] = null;
            } else {
                a0 a0Var3 = a0VarArr[i10];
                if (a0Var3 == null || ((l0) a0Var3).l != a0Var2) {
                    a0VarArr[i10] = new l0(a0Var2, j10);
                }
            }
        }
        return jK + j10;
    }

    @Override // u2.b0
    public final long l() {
        long jL = this.l.l();
        if (jL == Long.MIN_VALUE) {
            return Long.MIN_VALUE;
        }
        return this.f22875m + jL;
    }

    @Override // u2.InterfaceC2490A
    public final void m() {
        this.l.m();
    }

    @Override // u2.InterfaceC2490A
    public final long o(long j3) {
        long j10 = this.f22875m;
        return this.l.o(j3 - j10) + j10;
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
        this.l.p(j3 - this.f22875m);
    }

    @Override // u2.b0
    public final void q(long j3) {
        this.l.q(j3 - this.f22875m);
    }
}
