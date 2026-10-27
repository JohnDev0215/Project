package t1;

import android.graphics.PointF;

/* JADX INFO: compiled from: CubicCurveData.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PointF f7995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PointF f7996b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PointF f7997c;

    public a() {
        this.f7995a = new PointF();
        this.f7996b = new PointF();
        this.f7997c = new PointF();
    }

    public PointF a() {
        return this.f7995a;
    }

    public PointF b() {
        return this.f7996b;
    }

    public PointF c() {
        return this.f7997c;
    }

    public void d(float f8, float f9) {
        this.f7995a.set(f8, f9);
    }

    public void e(float f8, float f9) {
        this.f7996b.set(f8, f9);
    }

    public void f(float f8, float f9) {
        this.f7997c.set(f8, f9);
    }

    public a(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f7995a = pointF;
        this.f7996b = pointF2;
        this.f7997c = pointF3;
    }
}
