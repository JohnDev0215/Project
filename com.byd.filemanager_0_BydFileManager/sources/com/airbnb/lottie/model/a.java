package com.airbnb.lottie.model;

import android.graphics.PointF;

/* JADX INFO: compiled from: CubicCurveData.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final PointF f2516a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PointF f2517b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final PointF f2518c;

    public a() {
        this.f2516a = new PointF();
        this.f2517b = new PointF();
        this.f2518c = new PointF();
    }

    public PointF a() {
        return this.f2516a;
    }

    public PointF b() {
        return this.f2517b;
    }

    public PointF c() {
        return this.f2518c;
    }

    public void d(float f, float f2) {
        this.f2516a.set(f, f2);
    }

    public void e(float f, float f2) {
        this.f2517b.set(f, f2);
    }

    public void f(float f, float f2) {
        this.f2518c.set(f, f2);
    }

    public a(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f2516a = pointF;
        this.f2517b = pointF2;
        this.f2518c = pointF3;
    }
}
