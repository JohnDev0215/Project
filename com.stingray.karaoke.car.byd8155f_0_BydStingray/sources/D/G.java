package D;

import E0.InterfaceC0174y;

/* JADX INFO: loaded from: classes.dex */
public abstract class G extends g0.n implements InterfaceC0174y {
    public abstract long H0(C0.J j3, long j10);

    public abstract boolean I0();

    public int b(E0.Q q4, C0.J j3, int i7) {
        return j3.c(i7);
    }

    @Override // E0.InterfaceC0174y
    public int c(E0.Q q4, C0.J j3, int i7) {
        return j3.P(i7);
    }

    @Override // E0.InterfaceC0174y
    public int d(E0.Q q4, C0.J j3, int i7) {
        return j3.V(i7);
    }

    public int f(E0.Q q4, C0.J j3, int i7) {
        return j3.a0(i7);
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        long jH0 = H0(j3, j10);
        if (I0()) {
            jH0 = io.sentry.config.a.Q(j10, jH0);
        }
        C0.V vB = j3.b(jH0);
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0.X(vB, 3));
    }
}
