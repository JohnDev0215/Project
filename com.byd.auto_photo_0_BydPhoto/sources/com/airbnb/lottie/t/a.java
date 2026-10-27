package com.airbnb.lottie.t;

import android.graphics.PointF;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final PointF f779a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PointF f780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final PointF f781c;

    public a() {
        this.f779a = new PointF();
        this.f780b = new PointF();
        this.f781c = new PointF();
    }

    public a(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f779a = pointF;
        this.f780b = pointF2;
        this.f781c = pointF3;
    }

    public PointF a() {
        return this.f779a;
    }

    public PointF b() {
        return this.f780b;
    }

    public PointF c() {
        return this.f781c;
    }

    public void d(float f, float f2) {
        this.f779a.set(f, f2);
    }

    public void e(float f, float f2) {
        this.f780b.set(f, f2);
    }

    public void f(float f, float f2) {
        this.f781c.set(f, f2);
    }
}
