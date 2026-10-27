package N;

import N0.L;
import P8.n;
import S0.InterfaceC0702n;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static b f4218h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1.k f4219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f4220b;
    public final a1.c c;
    public final InterfaceC0702n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L f4221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4222f = Float.NaN;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4223g = Float.NaN;

    public b(a1.k kVar, L l, a1.c cVar, InterfaceC0702n interfaceC0702n) {
        this.f4219a = kVar;
        this.f4220b = l;
        this.c = cVar;
        this.d = interfaceC0702n;
        this.f4221e = n.T(l, kVar);
    }

    public final long a(long j3, int i7) {
        int i10;
        float f10 = this.f4223g;
        float fB = this.f4222f;
        if (Float.isNaN(f10) || Float.isNaN(fB)) {
            String str = c.f4224a;
            long jF = io.sentry.config.a.f(0, 0, 15);
            a1.c cVar = this.c;
            float fB2 = C7.a.g(str, this.f4221e, jF, cVar, this.d, null, 1, 96).b();
            fB = C7.a.g(c.f4225b, this.f4221e, io.sentry.config.a.f(0, 0, 15), cVar, this.d, null, 2, 96).b() - fB2;
            this.f4223g = fB2;
            this.f4222f = fB;
            f10 = fB2;
        }
        if (i7 != 1) {
            int iRound = Math.round((fB * (i7 - 1)) + f10);
            i10 = iRound >= 0 ? iRound : 0;
            int iG = a1.a.g(j3);
            if (i10 > iG) {
                i10 = iG;
            }
        } else {
            i10 = a1.a.i(j3);
        }
        return io.sentry.config.a.e(a1.a.j(j3), a1.a.h(j3), i10, a1.a.g(j3));
    }
}
