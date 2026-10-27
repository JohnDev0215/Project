package D;

/* JADX INFO: loaded from: classes.dex */
public final class F extends G {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1025y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1026z;

    @Override // D.G
    public final long H0(C0.J j3, long j10) {
        int iA0 = this.f1025y == 1 ? j3.a0(a1.a.h(j10)) : j3.c(a1.a.h(j10));
        if (iA0 < 0) {
            iA0 = 0;
        }
        if (iA0 >= 0) {
            return io.sentry.config.a.U(0, Integer.MAX_VALUE, iA0, iA0);
        }
        w0.c.e0("height(" + iA0 + ") must be >= 0");
        throw null;
    }

    @Override // D.G
    public final boolean I0() {
        return this.f1026z;
    }

    @Override // D.G, E0.InterfaceC0174y
    public final int b(E0.Q q4, C0.J j3, int i7) {
        return this.f1025y == 1 ? j3.a0(i7) : j3.c(i7);
    }

    @Override // D.G, E0.InterfaceC0174y
    public final int f(E0.Q q4, C0.J j3, int i7) {
        return this.f1025y == 1 ? j3.a0(i7) : j3.c(i7);
    }
}
