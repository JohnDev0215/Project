package com.airbnb.lottie.v;

import android.graphics.PointF;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: Keyframe.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.airbnb.lottie.d f3197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final T f3198b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public T f3199c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Interpolator f3200d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f3201e;
    public Float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private float k;
    private float l;
    public PointF m;
    public PointF n;

    public a(com.airbnb.lottie.d dVar, T t, T t2, Interpolator interpolator, float f, Float f2) {
        this.g = -3987645.8f;
        this.h = -3987645.8f;
        this.i = 784923401;
        this.j = 784923401;
        this.k = Float.MIN_VALUE;
        this.l = Float.MIN_VALUE;
        this.m = null;
        this.n = null;
        this.f3197a = dVar;
        this.f3198b = t;
        this.f3199c = t2;
        this.f3200d = interpolator;
        this.f3201e = f;
        this.f = f2;
    }

    public boolean a(float f) {
        return f >= e() && f < b();
    }

    public float b() {
        if (this.f3197a == null) {
            return 1.0f;
        }
        if (this.l == Float.MIN_VALUE) {
            if (this.f == null) {
                this.l = 1.0f;
            } else {
                this.l = e() + ((this.f.floatValue() - this.f3201e) / this.f3197a.e());
            }
        }
        return this.l;
    }

    public float c() {
        if (this.h == -3987645.8f) {
            this.h = ((Float) this.f3199c).floatValue();
        }
        return this.h;
    }

    public int d() {
        if (this.j == 784923401) {
            this.j = ((Integer) this.f3199c).intValue();
        }
        return this.j;
    }

    public float e() {
        com.airbnb.lottie.d dVar = this.f3197a;
        if (dVar == null) {
            return 0.0f;
        }
        if (this.k == Float.MIN_VALUE) {
            this.k = (this.f3201e - dVar.o()) / this.f3197a.e();
        }
        return this.k;
    }

    public float f() {
        if (this.g == -3987645.8f) {
            this.g = ((Float) this.f3198b).floatValue();
        }
        return this.g;
    }

    public int g() {
        if (this.i == 784923401) {
            this.i = ((Integer) this.f3198b).intValue();
        }
        return this.i;
    }

    public boolean h() {
        return this.f3200d == null;
    }

    public String toString() {
        return "Keyframe{startValue=" + this.f3198b + ", endValue=" + this.f3199c + ", startFrame=" + this.f3201e + ", endFrame=" + this.f + ", interpolator=" + this.f3200d + '}';
    }

    public a(T t) {
        this.g = -3987645.8f;
        this.h = -3987645.8f;
        this.i = 784923401;
        this.j = 784923401;
        this.k = Float.MIN_VALUE;
        this.l = Float.MIN_VALUE;
        this.m = null;
        this.n = null;
        this.f3197a = null;
        this.f3198b = t;
        this.f3199c = t;
        this.f3200d = null;
        this.f3201e = Float.MIN_VALUE;
        this.f = Float.valueOf(Float.MAX_VALUE);
    }
}
