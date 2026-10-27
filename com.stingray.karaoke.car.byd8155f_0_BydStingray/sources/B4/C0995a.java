package b4;

import W3.c;
import android.graphics.PointF;
import android.view.animation.BaseInterpolator;

/* JADX INFO: renamed from: b4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0995a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final O3.a f12211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f12212b;
    public Object c;
    public final BaseInterpolator d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final BaseInterpolator f12213e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final BaseInterpolator f12214f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f12215g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Float f12216h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f12217i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f12218j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f12219k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f12220m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f12221n;
    public PointF o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public PointF f12222p;

    public C0995a(O3.a aVar, Object obj, Object obj2, BaseInterpolator baseInterpolator, float f10, Float f11) {
        this.f12217i = -3987645.8f;
        this.f12218j = -3987645.8f;
        this.f12219k = 784923401;
        this.l = 784923401;
        this.f12220m = Float.MIN_VALUE;
        this.f12221n = Float.MIN_VALUE;
        this.o = null;
        this.f12222p = null;
        this.f12211a = aVar;
        this.f12212b = obj;
        this.c = obj2;
        this.d = baseInterpolator;
        this.f12213e = null;
        this.f12214f = null;
        this.f12215g = f10;
        this.f12216h = f11;
    }

    public final float a() {
        O3.a aVar = this.f12211a;
        if (aVar == null) {
            return 1.0f;
        }
        if (this.f12221n == Float.MIN_VALUE) {
            if (this.f12216h == null) {
                this.f12221n = 1.0f;
            } else {
                this.f12221n = (float) (((double) b()) + (((double) (this.f12216h.floatValue() - this.f12215g)) / ((double) (aVar.f5078m - aVar.l))));
            }
        }
        return this.f12221n;
    }

    public final float b() {
        O3.a aVar = this.f12211a;
        if (aVar == null) {
            return 0.0f;
        }
        if (this.f12220m == Float.MIN_VALUE) {
            float f10 = aVar.l;
            this.f12220m = (this.f12215g - f10) / (aVar.f5078m - f10);
        }
        return this.f12220m;
    }

    public final boolean c() {
        return this.d == null && this.f12213e == null && this.f12214f == null;
    }

    public final String toString() {
        return "Keyframe{startValue=" + this.f12212b + ", endValue=" + this.c + ", startFrame=" + this.f12215g + ", endFrame=" + this.f12216h + ", interpolator=" + this.d + '}';
    }

    public C0995a(O3.a aVar, Object obj, Object obj2, BaseInterpolator baseInterpolator, BaseInterpolator baseInterpolator2, float f10) {
        this.f12217i = -3987645.8f;
        this.f12218j = -3987645.8f;
        this.f12219k = 784923401;
        this.l = 784923401;
        this.f12220m = Float.MIN_VALUE;
        this.f12221n = Float.MIN_VALUE;
        this.o = null;
        this.f12222p = null;
        this.f12211a = aVar;
        this.f12212b = obj;
        this.c = obj2;
        this.d = null;
        this.f12213e = baseInterpolator;
        this.f12214f = baseInterpolator2;
        this.f12215g = f10;
        this.f12216h = null;
    }

    public C0995a(O3.a aVar, Object obj, Object obj2, BaseInterpolator baseInterpolator, BaseInterpolator baseInterpolator2, BaseInterpolator baseInterpolator3, float f10, Float f11) {
        this.f12217i = -3987645.8f;
        this.f12218j = -3987645.8f;
        this.f12219k = 784923401;
        this.l = 784923401;
        this.f12220m = Float.MIN_VALUE;
        this.f12221n = Float.MIN_VALUE;
        this.o = null;
        this.f12222p = null;
        this.f12211a = aVar;
        this.f12212b = obj;
        this.c = obj2;
        this.d = baseInterpolator;
        this.f12213e = baseInterpolator2;
        this.f12214f = baseInterpolator3;
        this.f12215g = f10;
        this.f12216h = f11;
    }

    public C0995a(Object obj) {
        this.f12217i = -3987645.8f;
        this.f12218j = -3987645.8f;
        this.f12219k = 784923401;
        this.l = 784923401;
        this.f12220m = Float.MIN_VALUE;
        this.f12221n = Float.MIN_VALUE;
        this.o = null;
        this.f12222p = null;
        this.f12211a = null;
        this.f12212b = obj;
        this.c = obj;
        this.d = null;
        this.f12213e = null;
        this.f12214f = null;
        this.f12215g = Float.MIN_VALUE;
        this.f12216h = Float.valueOf(Float.MAX_VALUE);
    }

    public C0995a(c cVar, c cVar2) {
        this.f12217i = -3987645.8f;
        this.f12218j = -3987645.8f;
        this.f12219k = 784923401;
        this.l = 784923401;
        this.f12220m = Float.MIN_VALUE;
        this.f12221n = Float.MIN_VALUE;
        this.o = null;
        this.f12222p = null;
        this.f12211a = null;
        this.f12212b = cVar;
        this.c = cVar2;
        this.d = null;
        this.f12213e = null;
        this.f12214f = null;
        this.f12215g = Float.MIN_VALUE;
        this.f12216h = Float.valueOf(Float.MAX_VALUE);
    }
}
