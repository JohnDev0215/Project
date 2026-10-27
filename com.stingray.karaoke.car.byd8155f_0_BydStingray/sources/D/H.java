package D;

/* JADX INFO: loaded from: classes.dex */
public final class H extends G {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1027y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f1028z;

    @Override // D.G
    public final long H0(C0.J j3, long j10) {
        int iP = this.f1027y == 1 ? j3.P(a1.a.g(j10)) : j3.V(a1.a.g(j10));
        if (iP < 0) {
            iP = 0;
        }
        if (iP >= 0) {
            return io.sentry.config.a.U(iP, iP, 0, Integer.MAX_VALUE);
        }
        w0.c.e0("width(" + iP + ") must be >= 0");
        throw null;
    }

    @Override // D.G
    public final boolean I0() {
        return this.f1028z;
    }

    @Override // D.G, E0.InterfaceC0174y
    public final int c(E0.Q q4, C0.J j3, int i7) {
        return this.f1027y == 1 ? j3.P(i7) : j3.V(i7);
    }

    @Override // D.G, E0.InterfaceC0174y
    public final int d(E0.Q q4, C0.J j3, int i7) {
        return this.f1027y == 1 ? j3.P(i7) : j3.V(i7);
    }
}
