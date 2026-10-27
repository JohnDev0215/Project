package C0;

/* JADX INFO: renamed from: C0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0104k implements J {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final J f905m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f906n;
    public final int o;

    public /* synthetic */ C0104k(J j3, int i7, int i10, int i11) {
        this.l = i11;
        this.f905m = j3;
        this.f906n = i7;
        this.o = i10;
    }

    @Override // C0.J
    public final int P(int i7) {
        switch (this.l) {
        }
        return this.f905m.P(i7);
    }

    @Override // C0.J
    public final int V(int i7) {
        switch (this.l) {
        }
        return this.f905m.V(i7);
    }

    @Override // C0.J
    public final int a0(int i7) {
        switch (this.l) {
        }
        return this.f905m.a0(i7);
    }

    @Override // C0.J
    public final V b(long j3) {
        switch (this.l) {
            case 0:
                int i7 = this.o;
                int i10 = this.f906n;
                J j10 = this.f905m;
                if (i7 == 1) {
                    return new C0106m(i10 == 2 ? j10.V(a1.a.g(j3)) : j10.P(a1.a.g(j3)), a1.a.c(j3) ? a1.a.g(j3) : 32767, 0);
                }
                return new C0106m(a1.a.d(j3) ? a1.a.h(j3) : 32767, i10 == 2 ? j10.c(a1.a.h(j3)) : j10.a0(a1.a.h(j3)), 0);
            case 1:
                int i11 = this.o;
                int i12 = this.f906n;
                J j11 = this.f905m;
                if (i11 == 1) {
                    return new C0106m(i12 == 2 ? j11.V(a1.a.g(j3)) : j11.P(a1.a.g(j3)), a1.a.c(j3) ? a1.a.g(j3) : 32767, 1);
                }
                return new C0106m(a1.a.d(j3) ? a1.a.h(j3) : 32767, i12 == 2 ? j11.c(a1.a.h(j3)) : j11.a0(a1.a.h(j3)), 1);
            default:
                int i13 = this.o;
                int i14 = this.f906n;
                J j12 = this.f905m;
                if (i13 == 1) {
                    return new C0106m(i14 == 2 ? j12.V(a1.a.g(j3)) : j12.P(a1.a.g(j3)), a1.a.c(j3) ? a1.a.g(j3) : 32767, 2);
                }
                return new C0106m(a1.a.d(j3) ? a1.a.h(j3) : 32767, i14 == 2 ? j12.c(a1.a.h(j3)) : j12.a0(a1.a.h(j3)), 2);
        }
    }

    @Override // C0.J
    public final int c(int i7) {
        switch (this.l) {
        }
        return this.f905m.c(i7);
    }

    @Override // C0.J
    public final Object i() {
        switch (this.l) {
        }
        return this.f905m.i();
    }
}
