package com.google.android.material.bottomappbar;

import e3.f;
import e3.m;

/* JADX INFO: compiled from: BottomAppBarTopEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f4183e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private float f4184g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f4185h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private float f4186i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private float f4187j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private float f4188k;

    @Override // e3.f
    public void b(float f7, float f8, float f9, m mVar) {
        float f10;
        float f11;
        float f12 = this.f4185h;
        if (f12 == 0.0f) {
            mVar.m(f7, 0.0f);
            return;
        }
        float f13 = ((this.f4184g * 2.0f) + f12) / 2.0f;
        float f14 = f9 * this.f4183e;
        float f15 = f8 + this.f4187j;
        float f16 = (this.f4186i * f9) + ((1.0f - f9) * f13);
        if (f16 / f13 >= 1.0f) {
            mVar.m(f7, 0.0f);
            return;
        }
        float f17 = this.f4188k;
        float f18 = f17 * f9;
        boolean z6 = f17 == -1.0f || Math.abs((f17 * 2.0f) - f12) < 0.1f;
        if (z6) {
            f10 = f16;
            f11 = 0.0f;
        } else {
            f11 = 1.75f;
            f10 = 0.0f;
        }
        float f19 = f13 + f14;
        float f20 = f10 + f14;
        float fSqrt = (float) Math.sqrt((f19 * f19) - (f20 * f20));
        float f21 = f15 - fSqrt;
        float f22 = f15 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f20));
        float f23 = (90.0f - degrees) + f11;
        mVar.m(f21, 0.0f);
        float f24 = f14 * 2.0f;
        mVar.a(f21 - f14, 0.0f, f21 + f14, f24, 270.0f, degrees);
        if (z6) {
            mVar.a(f15 - f13, (-f13) - f10, f15 + f13, f13 - f10, 180.0f - f23, (f23 * 2.0f) - 180.0f);
        } else {
            float f25 = this.f4184g;
            float f26 = f18 * 2.0f;
            float f27 = f15 - f13;
            mVar.a(f27, -(f18 + f25), f27 + f25 + f26, f25 + f18, 180.0f - f23, ((f23 * 2.0f) - 180.0f) / 2.0f);
            float f28 = f15 + f13;
            float f29 = this.f4184g;
            mVar.m(f28 - ((f29 / 2.0f) + f18), f29 + f18);
            float f30 = this.f4184g;
            mVar.a(f28 - (f26 + f30), -(f18 + f30), f28, f30 + f18, 90.0f, f23 - 90.0f);
        }
        mVar.a(f22 - f14, 0.0f, f22 + f14, f24, 270.0f - degrees, degrees);
        mVar.m(f7, 0.0f);
    }

    float c() {
        return this.f4186i;
    }

    public float d() {
        return this.f4188k;
    }

    float e() {
        return this.f4184g;
    }

    float f() {
        return this.f4183e;
    }

    public float g() {
        return this.f4185h;
    }

    void h(float f7) {
        if (f7 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.f4186i = f7;
    }

    public void i(float f7) {
        this.f4188k = f7;
    }

    void j(float f7) {
        this.f4184g = f7;
    }

    void k(float f7) {
        this.f4183e = f7;
    }

    public void l(float f7) {
        this.f4185h = f7;
    }

    void n(float f7) {
        this.f4187j = f7;
    }
}
