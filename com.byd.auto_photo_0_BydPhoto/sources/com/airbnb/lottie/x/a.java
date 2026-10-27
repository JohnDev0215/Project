package com.airbnb.lottie.x;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final com.airbnb.lottie.d f975a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    public final T f976b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public T f977c;

    @Nullable
    public final Interpolator d;
    public final float e;

    @Nullable
    public Float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private float k;
    private float l;
    public PointF m;
    public PointF n;

    public a(com.airbnb.lottie.d dVar, @Nullable T t, @Nullable T t2, @Nullable Interpolator interpolator, float f, @Nullable Float f2) {
        this.g = -3987645.8f;
        this.h = -3987645.8f;
        this.i = 784923401;
        this.j = 784923401;
        this.k = Float.MIN_VALUE;
        this.l = Float.MIN_VALUE;
        this.m = null;
        this.n = null;
        this.f975a = dVar;
        this.f976b = t;
        this.f977c = t2;
        this.d = interpolator;
        this.e = f;
        this.f = f2;
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
        this.f975a = null;
        this.f976b = t;
        this.f977c = t;
        this.d = null;
        this.e = Float.MIN_VALUE;
        this.f = Float.valueOf(Float.MAX_VALUE);
    }

    public boolean a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return f >= e() && f < b();
    }

    public float b() {
        if (this.f975a == null) {
            return 1.0f;
        }
        if (this.l == Float.MIN_VALUE) {
            if (this.f == null) {
                this.l = 1.0f;
            } else {
                this.l = e() + ((this.f.floatValue() - this.e) / this.f975a.e());
            }
        }
        return this.l;
    }

    public float c() {
        if (this.h == -3987645.8f) {
            this.h = ((Float) this.f977c).floatValue();
        }
        return this.h;
    }

    public int d() {
        if (this.j == 784923401) {
            this.j = ((Integer) this.f977c).intValue();
        }
        return this.j;
    }

    public float e() {
        com.airbnb.lottie.d dVar = this.f975a;
        if (dVar == null) {
            return 0.0f;
        }
        if (this.k == Float.MIN_VALUE) {
            this.k = (this.e - dVar.o()) / this.f975a.e();
        }
        return this.k;
    }

    public float f() {
        if (this.g == -3987645.8f) {
            this.g = ((Float) this.f976b).floatValue();
        }
        return this.g;
    }

    public int g() {
        if (this.i == 784923401) {
            this.i = ((Integer) this.f976b).intValue();
        }
        return this.i;
    }

    public boolean h() {
        return this.d == null;
    }

    public String toString() {
        return "Keyframe{startValue=" + this.f976b + ", endValue=" + this.f977c + ", startFrame=" + this.e + ", endFrame=" + this.f + ", interpolator=" + this.d + '}';
    }
}
