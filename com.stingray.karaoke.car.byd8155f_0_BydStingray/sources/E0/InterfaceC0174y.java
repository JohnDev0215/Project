package E0;

import C0.C0104k;
import C0.C0110q;

/* JADX INFO: renamed from: E0.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0174y extends InterfaceC0163m {
    default int b(Q q4, C0.J j3, int i7) {
        int i10 = 2;
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, i10, i10, 2), io.sentry.config.a.f(i7, 0, 13)).c();
    }

    default int c(Q q4, C0.J j3, int i7) {
        int i10 = 1;
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, i10, i10, 2), io.sentry.config.a.f(0, i7, 7)).e();
    }

    default int d(Q q4, C0.J j3, int i7) {
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, 2, 1, 2), io.sentry.config.a.f(0, i7, 7)).e();
    }

    default int f(Q q4, C0.J j3, int i7) {
        return g(new C0110q(q4, q4.getLayoutDirection()), new C0104k(j3, 1, 2, 2), io.sentry.config.a.f(i7, 0, 13)).c();
    }

    C0.L g(C0.M m10, C0.J j3, long j10);
}
