package x;

import x3.AbstractC2673a;

/* JADX INFO: renamed from: x.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2618G implements InterfaceC2616E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23405a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23406b;
    public final InterfaceC2612A c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f23407e;

    public C2618G(int i7, int i10, InterfaceC2612A interfaceC2612A) {
        this.f23405a = i7;
        this.f23406b = i10;
        this.c = interfaceC2612A;
        this.d = ((long) i7) * 1000000;
        this.f23407e = ((long) i10) * 1000000;
    }

    @Override // x.InterfaceC2616E
    public final float b(long j3, float f10, float f11, float f12) {
        float fW = this.f23405a == 0 ? 1.0f : AbstractC2673a.w(j3 - this.f23407e, 0L, this.d) / this.d;
        if (fW < 0.0f) {
            fW = 0.0f;
        }
        float fA = this.c.a(fW <= 1.0f ? fW : 1.0f);
        D0 d02 = E0.f23396a;
        return (f11 * fA) + ((1 - fA) * f10);
    }

    @Override // x.InterfaceC2616E
    public final float c(long j3, float f10, float f11, float f12) {
        long jW = AbstractC2673a.w(j3 - this.f23407e, 0L, this.d);
        if (jW < 0) {
            return 0.0f;
        }
        if (jW == 0) {
            return f12;
        }
        return (b(jW, f10, f11, f12) - b(jW - 1000000, f10, f11, f12)) * 1000.0f;
    }

    @Override // x.InterfaceC2616E
    public final long d(float f10, float f11, float f12) {
        return ((long) (this.f23406b + this.f23405a)) * 1000000;
    }
}
