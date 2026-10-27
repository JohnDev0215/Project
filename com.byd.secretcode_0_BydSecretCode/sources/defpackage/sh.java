package defpackage;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class sh extends fk implements Cloneable {
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;

    public sh(float f, float f2, float f3) {
        this.c = f;
        this.b = f2;
        h(f3);
        this.f = 0.0f;
    }

    @Override // defpackage.fk
    public void b(float f, float f2, float f3, ok okVar) {
        float f4 = this.d;
        if (f4 == 0.0f) {
            okVar.m(f, 0.0f);
            return;
        }
        float f5 = ((this.c * 2.0f) + f4) / 2.0f;
        float f6 = f3 * this.b;
        float f7 = f2 + this.f;
        float f8 = (this.e * f3) + ((1.0f - f3) * f5);
        if (f8 / f5 >= 1.0f) {
            okVar.m(f, 0.0f);
            return;
        }
        float f9 = f5 + f6;
        float f10 = f8 + f6;
        float fSqrt = (float) Math.sqrt((f9 * f9) - (f10 * f10));
        float f11 = f7 - fSqrt;
        float f12 = f7 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f10));
        float f13 = 90.0f - degrees;
        okVar.m(f11, 0.0f);
        float f14 = f6 * 2.0f;
        okVar.a(f11 - f6, 0.0f, f11 + f6, f14, 270.0f, degrees);
        okVar.a(f7 - f5, (-f5) - f8, f7 + f5, f5 - f8, 180.0f - f13, (f13 * 2.0f) - 180.0f);
        okVar.a(f12 - f6, 0.0f, f12 + f6, f14, 270.0f - degrees, degrees);
        okVar.m(f, 0.0f);
    }

    public float c() {
        return this.e;
    }

    public float d() {
        return this.c;
    }

    public float e() {
        return this.b;
    }

    public float f() {
        return this.d;
    }

    public float g() {
        return this.f;
    }

    public void h(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.e = f;
    }

    public void i(float f) {
        this.c = f;
    }

    public void j(float f) {
        this.b = f;
    }

    public void k(float f) {
        this.d = f;
    }

    public void l(float f) {
        this.f = f;
    }
}
