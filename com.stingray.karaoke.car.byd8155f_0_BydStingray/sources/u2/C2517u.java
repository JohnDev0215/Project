package u2;

import h2.AbstractC1343A;

/* JADX INFO: renamed from: u2.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2517u implements InterfaceC2490A, InterfaceC2522z {
    public final C2492C l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f22901m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final x2.e f22902n;
    public AbstractC2498a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC2490A f22903p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public InterfaceC2522z f22904q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f22905r = -9223372036854775807L;

    public C2517u(C2492C c2492c, x2.e eVar, long j3) {
        this.l = c2492c;
        this.f22902n = eVar;
        this.f22901m = j3;
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.a(j3, b0Var);
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        this.f22904q = interfaceC2522z;
        InterfaceC2490A interfaceC2490A = this.f22903p;
        if (interfaceC2490A != null) {
            long j10 = this.f22905r;
            if (j10 == -9223372036854775807L) {
                j10 = this.f22901m;
            }
            interfaceC2490A.b(this, j10);
        }
    }

    @Override // u2.InterfaceC2522z
    public final void c(InterfaceC2490A interfaceC2490A) {
        InterfaceC2522z interfaceC2522z = this.f22904q;
        int i7 = AbstractC1343A.f15195a;
        interfaceC2522z.c(this);
    }

    @Override // u2.b0
    public final boolean d() {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        return interfaceC2490A != null && interfaceC2490A.d();
    }

    @Override // u2.InterfaceC2522z
    public final void e(b0 b0Var) {
        InterfaceC2522z interfaceC2522z = this.f22904q;
        int i7 = AbstractC1343A.f15195a;
        interfaceC2522z.e(this);
    }

    public final void f(C2492C c2492c) {
        long j3 = this.f22905r;
        if (j3 == -9223372036854775807L) {
            j3 = this.f22901m;
        }
        AbstractC2498a abstractC2498a = this.o;
        abstractC2498a.getClass();
        InterfaceC2490A interfaceC2490AB = abstractC2498a.b(c2492c, this.f22902n, j3);
        this.f22903p = interfaceC2490AB;
        if (this.f22904q != null) {
            interfaceC2490AB.b(this, j3);
        }
    }

    @Override // u2.b0
    public final long g() {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.g();
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.h();
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        return interfaceC2490A != null && interfaceC2490A.i(g7);
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.j();
    }

    @Override // u2.InterfaceC2490A
    public final long k(w2.q[] qVarArr, boolean[] zArr, a0[] a0VarArr, boolean[] zArr2, long j3) {
        long j10 = this.f22905r;
        long j11 = (j10 == -9223372036854775807L || j3 != this.f22901m) ? j3 : j10;
        this.f22905r = -9223372036854775807L;
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.k(qVarArr, zArr, a0VarArr, zArr2, j11);
    }

    @Override // u2.b0
    public final long l() {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.l();
    }

    @Override // u2.InterfaceC2490A
    public final void m() {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        if (interfaceC2490A != null) {
            interfaceC2490A.m();
            return;
        }
        AbstractC2498a abstractC2498a = this.o;
        if (abstractC2498a != null) {
            abstractC2498a.j();
        }
    }

    @Override // u2.InterfaceC2490A
    public final long o(long j3) {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        return interfaceC2490A.o(j3);
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        interfaceC2490A.p(j3);
    }

    @Override // u2.b0
    public final void q(long j3) {
        InterfaceC2490A interfaceC2490A = this.f22903p;
        int i7 = AbstractC1343A.f15195a;
        interfaceC2490A.q(j3);
    }
}
