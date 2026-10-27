package C0;

/* JADX INFO: renamed from: C0.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0115w extends g0.m {
    default int b(E0.Q q4, J j3, int i7) {
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, 2, 2, 1), io.sentry.config.a.f(i7, 0, 13)).c();
    }

    default int c(E0.Q q4, J j3, int i7) {
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, 1, 1, 1), io.sentry.config.a.f(0, i7, 7)).e();
    }

    default int d(E0.Q q4, J j3, int i7) {
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, 2, 1, 1), io.sentry.config.a.f(0, i7, 7)).e();
    }

    default int f(E0.Q q4, J j3, int i7) {
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, 1, 2, 1), io.sentry.config.a.f(i7, 0, 13)).c();
    }

    L g(M m10, J j3, long j10);
}
