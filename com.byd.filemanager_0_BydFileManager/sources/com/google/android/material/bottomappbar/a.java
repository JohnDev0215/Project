package com.google.android.material.bottomappbar;

import c.a.a.a.w.f;
import c.a.a.a.w.o;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f4053b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f4054c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f4055d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f4056e;
    private float f;

    public a(float f, float f2, float f3) {
        this.f4054c = f;
        this.f4053b = f2;
        h(f3);
        this.f = 0.0f;
    }

    @Override // c.a.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        float f4 = this.f4055d;
        if (f4 == 0.0f) {
            oVar.m(f, 0.0f);
            return;
        }
        float f5 = ((this.f4054c * 2.0f) + f4) / 2.0f;
        float f6 = f3 * this.f4053b;
        float f7 = f2 + this.f;
        float f8 = (this.f4056e * f3) + ((1.0f - f3) * f5);
        if (f8 / f5 >= 1.0f) {
            oVar.m(f, 0.0f);
            return;
        }
        float f9 = f5 + f6;
        float f10 = f8 + f6;
        float fSqrt = (float) Math.sqrt((f9 * f9) - (f10 * f10));
        float f11 = f7 - fSqrt;
        float f12 = f7 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f10));
        float f13 = 90.0f - degrees;
        oVar.m(f11, 0.0f);
        float f14 = f6 * 2.0f;
        oVar.a(f11 - f6, 0.0f, f11 + f6, f14, 270.0f, degrees);
        oVar.a(f7 - f5, (-f5) - f8, f7 + f5, f5 - f8, 180.0f - f13, (f13 * 2.0f) - 180.0f);
        oVar.a(f12 - f6, 0.0f, f12 + f6, f14, 270.0f - degrees, degrees);
        oVar.m(f, 0.0f);
    }

    float c() {
        return this.f4056e;
    }

    float d() {
        return this.f4054c;
    }

    float e() {
        return this.f4053b;
    }

    public float f() {
        return this.f4055d;
    }

    public float g() {
        return this.f;
    }

    void h(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f4056e = f;
    }

    void i(float f) {
        this.f4054c = f;
    }

    void j(float f) {
        this.f4053b = f;
    }

    public void k(float f) {
        this.f4055d = f;
    }

    void l(float f) {
        this.f = f;
    }
}
