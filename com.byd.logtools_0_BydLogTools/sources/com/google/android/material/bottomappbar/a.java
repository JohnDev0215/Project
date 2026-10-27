package com.google.android.material.bottomappbar;

import d.b.a.a.w.f;
import d.b.a.a.w.o;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f3373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3375d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3376e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f3377f;

    public a(float f2, float f3, float f4) {
        this.f3374c = f2;
        this.f3373b = f3;
        i(f4);
        this.f3377f = 0.0f;
    }

    @Override // d.b.a.a.w.f
    public void b(float f2, float f3, float f4, o oVar) {
        float f5 = this.f3375d;
        if (f5 == 0.0f) {
            oVar.m(f2, 0.0f);
            return;
        }
        float f6 = ((this.f3374c * 2.0f) + f5) / 2.0f;
        float f7 = f4 * this.f3373b;
        float f8 = f3 + this.f3377f;
        float f9 = (this.f3376e * f4) + ((1.0f - f4) * f6);
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

    float c() {
        return this.f3376e;
    }

    float d() {
        return this.f3374c;
    }

    float f() {
        return this.f3373b;
    }

    public float g() {
        return this.f3375d;
    }

    public float h() {
        return this.f3377f;
    }

    void i(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f3376e = f2;
    }

    void j(float f2) {
        this.f3374c = f2;
    }

    void k(float f2) {
        this.f3373b = f2;
    }

    public void l(float f2) {
        this.f3375d = f2;
    }

    void m(float f2) {
        this.f3377f = f2;
    }
}
