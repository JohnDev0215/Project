package m5;

import e6.f;
import e6.o;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f6380d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f6381e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f6382f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f6383g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6384h;

    public a(float f8, float f9, float f10) {
        this.f6381e = f8;
        this.f6380d = f9;
        h(f10);
        this.f6384h = 0.0f;
    }

    @Override // e6.f
    public void b(float f8, float f9, float f10, o oVar) {
        float f11 = this.f6382f;
        if (f11 == 0.0f) {
            oVar.m(f8, 0.0f);
            return;
        }
        float f12 = ((this.f6381e * 2.0f) + f11) / 2.0f;
        float f13 = f10 * this.f6380d;
        float f14 = f9 + this.f6384h;
        float f15 = (this.f6383g * f10) + ((1.0f - f10) * f12);
        if (f15 / f12 >= 1.0f) {
            oVar.m(f8, 0.0f);
            return;
        }
        float f16 = f12 + f13;
        float f17 = f15 + f13;
        float fSqrt = (float) Math.sqrt((f16 * f16) - (f17 * f17));
        float f18 = f14 - fSqrt;
        float f19 = f14 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f17));
        float f20 = 90.0f - degrees;
        oVar.m(f18, 0.0f);
        float f21 = f13 * 2.0f;
        oVar.a(f18 - f13, 0.0f, f18 + f13, f21, 270.0f, degrees);
        oVar.a(f14 - f12, (-f12) - f15, f14 + f12, f12 - f15, 180.0f - f20, (f20 * 2.0f) - 180.0f);
        oVar.a(f19 - f13, 0.0f, f19 + f13, f21, 270.0f - degrees, degrees);
        oVar.m(f8, 0.0f);
    }

    public float c() {
        return this.f6383g;
    }

    public float d() {
        return this.f6381e;
    }

    public float e() {
        return this.f6380d;
    }

    public float f() {
        return this.f6382f;
    }

    public float g() {
        return this.f6384h;
    }

    public void h(float f8) {
        if (f8 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f6383g = f8;
    }

    public void i(float f8) {
        this.f6381e = f8;
    }

    public void j(float f8) {
        this.f6380d = f8;
    }

    public void k(float f8) {
        this.f6382f = f8;
    }

    public void l(float f8) {
        this.f6384h = f8;
    }
}
