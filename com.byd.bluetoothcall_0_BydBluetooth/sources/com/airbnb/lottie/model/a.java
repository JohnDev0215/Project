package com.airbnb.lottie.model;

import android.graphics.PointF;

/* JADX INFO: compiled from: CubicCurveData.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final PointF f2900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PointF f2901b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final PointF f2902c;

    public a() {
        this.f2900a = new PointF();
        this.f2901b = new PointF();
        this.f2902c = new PointF();
    }

    public PointF a() {
        return this.f2900a;
    }

    public PointF b() {
        return this.f2901b;
    }

    public PointF c() {
        return this.f2902c;
    }

    public void d(float f, float f2) {
        this.f2900a.set(f, f2);
    }

    public void e(float f, float f2) {
        this.f2901b.set(f, f2);
    }

    public void f(float f, float f2) {
        this.f2902c.set(f, f2);
    }

    public a(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f2900a = pointF;
        this.f2901b = pointF2;
        this.f2902c = pointF3;
    }
}
