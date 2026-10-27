package w;

import C0.InterfaceC0115w;
import C0.V;
import E0.Q;
import T.C0741g;
import T.X;
import q0.C2162a;
import x.r0;

/* JADX INFO: renamed from: w.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2578i implements InterfaceC0115w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r0 f23103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X f23104b;
    public final /* synthetic */ C2579j c;

    public C2578i(C2579j c2579j, r0 r0Var, X x8) {
        this.c = c2579j;
        this.f23103a = r0Var;
        this.f23104b = x8;
    }

    @Override // C0.InterfaceC0115w
    public final int b(Q q4, C0.J j3, int i7) {
        return j3.c(i7);
    }

    @Override // C0.InterfaceC0115w
    public final int c(Q q4, C0.J j3, int i7) {
        return j3.P(i7);
    }

    @Override // C0.InterfaceC0115w
    public final int d(Q q4, C0.J j3, int i7) {
        return j3.V(i7);
    }

    @Override // C0.InterfaceC0115w
    public final int f(Q q4, C0.J j3, int i7) {
        return j3.a0(i7);
    }

    @Override // C0.InterfaceC0115w
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        V vB = j3.b(j10);
        C2579j c2579j = this.c;
        long jC = m10.v() ? H7.H.c(vB.l, vB.f871m) : ((a1.j) this.f23103a.a(new C0741g(c2579j, 24, this), new C2162a(c2579j, 5)).getValue()).f11037a;
        return m10.m0((int) (jC >> 32), (int) (4294967295L & jC), n7.v.l, new io.sentry.android.replay.h(c2579j, vB, jC));
    }
}
