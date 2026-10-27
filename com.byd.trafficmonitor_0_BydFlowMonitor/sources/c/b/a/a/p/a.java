package c.b.a.a.p;

import c.b.a.a.i0.f;
import c.b.a.a.i0.o;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f4059b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4060c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4061d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4062e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4063f;

    public a(float f2, float f3, float f4) {
        this.f4060c = f2;
        this.f4059b = f3;
        h(f4);
        this.f4063f = 0.0f;
    }

    @Override // c.b.a.a.i0.f
    public void b(float f2, float f3, float f4, o oVar) {
        float f5 = this.f4061d;
        if (f5 == 0.0f) {
            oVar.m(f2, 0.0f);
            return;
        }
        float f6 = ((this.f4060c * 2.0f) + f5) / 2.0f;
        float f7 = f4 * this.f4059b;
        float f8 = f3 + this.f4063f;
        float f9 = (this.f4062e * f4) + ((1.0f - f4) * f6);
        if (f9 / f6 >= 1.0f) {
            oVar.m(f2, 0.0f);
            return;
        }
        float f10 = f6 + f7;
        float f11 = f9 + f7;
        float fSqrt = (float) Math.sqrt((f10 * f10) - (f11 * f11));
        float f12 = f8 - fSqrt;
        float f13 = f8 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f11));
        float f14 = 90.0f - degrees;
        oVar.m(f12, 0.0f);
        float f15 = f7 * 2.0f;
        oVar.a(f12 - f7, 0.0f, f12 + f7, f15, 270.0f, degrees);
        oVar.a(f8 - f6, (-f6) - f9, f8 + f6, f6 - f9, 180.0f - f14, (f14 * 2.0f) - 180.0f);
        oVar.a(f13 - f7, 0.0f, f13 + f7, f15, 270.0f - degrees, degrees);
        oVar.m(f2, 0.0f);
    }

    public float c() {
        return this.f4062e;
    }

    public float d() {
        return this.f4060c;
    }

    public float e() {
        return this.f4059b;
    }

    public float f() {
        return this.f4061d;
    }

    public float g() {
        return this.f4063f;
    }

    public void h(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f4062e = f2;
    }

    public void i(float f2) {
        this.f4060c = f2;
    }

    public void j(float f2) {
        this.f4059b = f2;
    }

    public void k(float f2) {
        this.f4061d = f2;
    }

    public void l(float f2) {
        this.f4063f = f2;
    }
}
