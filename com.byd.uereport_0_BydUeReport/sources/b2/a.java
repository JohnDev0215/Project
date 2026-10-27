package b2;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import o1.e;

/* JADX INFO: compiled from: Keyframe.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f2044a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final T f2045b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public T f2046c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Interpolator f2047d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f2048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Float f2049f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f2050g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f2051h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2052i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2053j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f2054k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f2055l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PointF f2056m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public PointF f2057n;

    public a(e eVar, T t7, T t8, Interpolator interpolator, float f8, Float f9) {
        this.f2050g = -3987645.8f;
        this.f2051h = -3987645.8f;
        this.f2052i = 784923401;
        this.f2053j = 784923401;
        this.f2054k = Float.MIN_VALUE;
        this.f2055l = Float.MIN_VALUE;
        this.f2056m = null;
        this.f2057n = null;
        this.f2044a = eVar;
        this.f2045b = t7;
        this.f2046c = t8;
        this.f2047d = interpolator;
        this.f2048e = f8;
        this.f2049f = f9;
    }

    public boolean a(float f8) {
        return f8 >= e() && f8 < b();
    }

    public float b() {
        if (this.f2044a == null) {
            return 1.0f;
        }
        if (this.f2055l == Float.MIN_VALUE) {
            if (this.f2049f == null) {
                this.f2055l = 1.0f;
            } else {
                this.f2055l = e() + ((this.f2049f.floatValue() - this.f2048e) / this.f2044a.e());
            }
        }
        return this.f2055l;
    }

    public float c() {
        if (this.f2051h == -3987645.8f) {
            this.f2051h = ((Float) this.f2046c).floatValue();
        }
        return this.f2051h;
    }

    public int d() {
        if (this.f2053j == 784923401) {
            this.f2053j = ((Integer) this.f2046c).intValue();
        }
        return this.f2053j;
    }

    public float e() {
        e eVar = this.f2044a;
        if (eVar == null) {
            return 0.0f;
        }
        if (this.f2054k == Float.MIN_VALUE) {
            this.f2054k = (this.f2048e - eVar.o()) / this.f2044a.e();
        }
        return this.f2054k;
    }

    public float f() {
        if (this.f2050g == -3987645.8f) {
            this.f2050g = ((Float) this.f2045b).floatValue();
        }
        return this.f2050g;
    }

    public int g() {
        if (this.f2052i == 784923401) {
            this.f2052i = ((Integer) this.f2045b).intValue();
        }
        return this.f2052i;
    }

    public boolean h() {
        return this.f2047d == null;
    }

    public String toString() {
        return "Keyframe{startValue=" + this.f2045b + ", endValue=" + this.f2046c + ", startFrame=" + this.f2048e + ", endFrame=" + this.f2049f + ", interpolator=" + this.f2047d + '}';
    }

    public a(T t7) {
        this.f2050g = -3987645.8f;
        this.f2051h = -3987645.8f;
        this.f2052i = 784923401;
        this.f2053j = 784923401;
        this.f2054k = Float.MIN_VALUE;
        this.f2055l = Float.MIN_VALUE;
        this.f2056m = null;
        this.f2057n = null;
        this.f2044a = null;
        this.f2045b = t7;
        this.f2046c = t7;
        this.f2047d = null;
        this.f2048e = Float.MIN_VALUE;
        this.f2049f = Float.valueOf(Float.MAX_VALUE);
    }
}
