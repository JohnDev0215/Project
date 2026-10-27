package v1;

import n2.f;
import n2.o;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f5419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f5420c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f5421d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f5422e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f5423f;

    public a(float f3, float f4, float f5) {
        this.f5420c = f3;
        this.f5419b = f4;
        h(f5);
        this.f5423f = 0.0f;
    }

    @Override // n2.f
    public void b(float f3, float f4, float f5, o oVar) {
        float f6 = this.f5421d;
        if (f6 == 0.0f) {
            oVar.m(f3, 0.0f);
            return;
        }
        float f7 = ((this.f5420c * 2.0f) + f6) / 2.0f;
        float f8 = f5 * this.f5419b;
        float f9 = f4 + this.f5423f;
        float f10 = (this.f5422e * f5) + ((1.0f - f5) * f7);
        if (f10 / f7 >= 1.0f) {
            oVar.m(f3, 0.0f);
            return;
        }
        float f11 = f7 + f8;
        float f12 = f10 + f8;
        float fSqrt = (float) Math.sqrt((f11 * f11) - (f12 * f12));
        float f13 = f9 - fSqrt;
        float f14 = f9 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f12));
        float f15 = 90.0f - degrees;
        oVar.m(f13, 0.0f);
        float f16 = f8 * 2.0f;
        oVar.a(f13 - f8, 0.0f, f13 + f8, f16, 270.0f, degrees);
        oVar.a(f9 - f7, (-f7) - f10, f9 + f7, f7 - f10, 180.0f - f15, (f15 * 2.0f) - 180.0f);
        oVar.a(f14 - f8, 0.0f, f14 + f8, f16, 270.0f - degrees, degrees);
        oVar.m(f3, 0.0f);
    }

    public float c() {
        return this.f5422e;
    }

    public float d() {
        return this.f5420c;
    }

    public float e() {
        return this.f5419b;
    }

    public float f() {
        return this.f5421d;
    }

    public float g() {
        return this.f5423f;
    }

    public void h(float f3) {
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f5422e = f3;
    }

    public void i(float f3) {
        this.f5420c = f3;
    }

    public void j(float f3) {
        this.f5419b = f3;
    }

    public void k(float f3) {
        this.f5421d = f3;
    }

    public void l(float f3) {
        this.f5423f = f3;
    }
}
