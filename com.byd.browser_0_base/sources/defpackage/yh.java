package defpackage;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class yh extends if0 implements Cloneable {
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j = -1.0f;

    public yh(float f, float f2, float f3) {
        this.f = f;
        this.e = f2;
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.h = f3;
        this.i = 0.0f;
    }

    @Override // defpackage.if0
    public final void e(float f, float f2, float f3, xj2 xj2Var) {
        float f4;
        float f5;
        float f6 = this.g;
        if (f6 == 0.0f) {
            xj2Var.d(f, 0.0f);
            return;
        }
        float f7 = ((this.f * 2.0f) + f6) / 2.0f;
        float f8 = f3 * this.e;
        float f9 = f2 + this.i;
        float f10 = C0173a.f(1.0f, f3, f7, this.h * f3);
        if (f10 / f7 >= 1.0f) {
            xj2Var.d(f, 0.0f);
            return;
        }
        float f11 = this.j;
        float f12 = f11 * f3;
        boolean z = f11 == -1.0f || Math.abs((f11 * 2.0f) - f6) < 0.1f;
        if (z) {
            f4 = f10;
            f5 = 0.0f;
        } else {
            f5 = 1.75f;
            f4 = 0.0f;
        }
        float f13 = f7 + f8;
        float f14 = f4 + f8;
        float fSqrt = (float) Math.sqrt((f13 * f13) - (f14 * f14));
        float f15 = f9 - fSqrt;
        float f16 = f9 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f14));
        float f17 = (90.0f - degrees) + f5;
        xj2Var.d(f15, 0.0f);
        float f18 = f8 * 2.0f;
        xj2Var.a(f15 - f8, 0.0f, f15 + f8, f18, 270.0f, degrees);
        if (z) {
            xj2Var.a(f9 - f7, (-f7) - f4, f9 + f7, f7 - f4, 180.0f - f17, (f17 * 2.0f) - 180.0f);
        } else {
            float f19 = this.f;
            float f20 = f12 * 2.0f;
            float f21 = f9 - f7;
            float f22 = f12 + f19;
            xj2Var.a(f21, -f22, f21 + f19 + f20, f22, 180.0f - f17, ((f17 * 2.0f) - 180.0f) / 2.0f);
            float f23 = f9 + f7;
            float f24 = this.f;
            xj2Var.d(f23 - ((f24 / 2.0f) + f12), f24 + f12);
            float f25 = this.f;
            float f26 = f12 + f25;
            xj2Var.a(f23 - (f20 + f25), -f26, f23, f26, 90.0f, f17 - 90.0f);
        }
        xj2Var.a(f16 - f8, 0.0f, f16 + f8, f18, 270.0f - degrees, degrees);
        xj2Var.d(f, 0.0f);
    }
}
