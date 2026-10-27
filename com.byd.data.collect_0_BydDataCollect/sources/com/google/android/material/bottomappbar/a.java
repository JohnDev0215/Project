package com.google.android.material.bottomappbar;

import d.b.a.a.x.f;
import d.b.a.a.x.o;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f3757b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3758c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3759d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3760e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f3761f;

    public a(float f2, float f3, float f4) {
        this.f3758c = f2;
        this.f3757b = f3;
        k(f4);
        this.f3761f = 0.0f;
    }

    @Override // d.b.a.a.x.f
    public void c(float f2, float f3, float f4, o oVar) {
        float f5 = this.f3759d;
        if (f5 == 0.0f) {
            oVar.m(f2, 0.0f);
            return;
        }
        float f6 = ((this.f3758c * 2.0f) + f5) / 2.0f;
        float f7 = f4 * this.f3757b;
        float f8 = f3 + this.f3761f;
        float f9 = (this.f3760e * f4) + ((1.0f - f4) * f6);
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

    float e() {
        return this.f3760e;
    }

    float f() {
        return this.f3758c;
    }

    float g() {
        return this.f3757b;
    }

    public float h() {
        return this.f3759d;
    }

    public float i() {
        return this.f3761f;
    }

    void k(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f3760e = f2;
    }

    void l(float f2) {
        this.f3758c = f2;
    }

    void m(float f2) {
        this.f3757b = f2;
    }

    public void n(float f2) {
        this.f3759d = f2;
    }

    void o(float f2) {
        this.f3761f = f2;
    }
}
