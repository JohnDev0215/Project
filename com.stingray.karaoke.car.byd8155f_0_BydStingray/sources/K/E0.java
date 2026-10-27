package K;

import A.EnumC0017h0;
import T.C0730a0;
import T.C0735d;
import T.C0738e0;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class E0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final io.sentry.internal.debugmeta.c f3141f = l9.l.V(D0.f3135m, C0340g.f3264y);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0730a0 f3142a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0730a0 f3143b = C0735d.L(0.0f);
    public m0.d c = m0.d.f18881e;
    public long d = N0.K.f4356b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f3144e;

    public E0(EnumC0017h0 enumC0017h0, float f10) {
        this.f3142a = C0735d.L(f10);
        this.f3144e = C0735d.O(enumC0017h0, T.S.f8224q);
    }

    public final void a(EnumC0017h0 enumC0017h0, m0.d dVar, int i7, int i10) {
        float f10 = i10 - i7;
        this.f3143b.j(f10);
        m0.d dVar2 = this.c;
        float f11 = dVar2.f18882a;
        float f12 = dVar.f18882a;
        C0730a0 c0730a0 = this.f3142a;
        float f13 = dVar.f18883b;
        if (f12 != f11 || f13 != dVar2.f18883b) {
            boolean z6 = enumC0017h0 == EnumC0017h0.l;
            if (z6) {
                f12 = f13;
            }
            float f14 = z6 ? dVar.d : dVar.c;
            float fI = c0730a0.i();
            float f15 = i7;
            float f16 = fI + f15;
            c0730a0.j(c0730a0.i() + ((f14 <= f16 && (f12 >= fI || f14 - f12 <= f15)) ? (f12 >= fI || f14 - f12 > f15) ? 0.0f : f12 - fI : f14 - f16));
            this.c = dVar;
        }
        c0730a0.j(AbstractC2673a.u(c0730a0.i(), 0.0f, f10));
    }
}
