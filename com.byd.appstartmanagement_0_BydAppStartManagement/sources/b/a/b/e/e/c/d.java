package b.a.b.e.e.c;

import android.graphics.PointF;

/* JADX INFO: compiled from: BydLineInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PointF f205a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f206b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PointF f207c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PointF f208d;
    public PointF e;
    public PointF f = new PointF();

    @Override // b.a.b.e.e.c.c
    public void a(int i) {
    }

    @Override // b.a.b.e.e.c.c
    public void b(PointF pointF, PointF pointF2, PointF pointF3) {
    }

    @Override // b.a.b.e.e.c.c
    public PointF c(int i, float f) {
        b.a.b.c.a.a(this, "getCurrentPoint  scrollDirection : " + i + " , input : " + f);
        if (f < 0.0f || f > 1.0f) {
            return this.f;
        }
        if (i == 0) {
            if (i()) {
                e(f);
            } else {
                g(f);
            }
        } else if (j()) {
            f(f);
        } else {
            h(f);
        }
        return this.f;
    }

    @Override // b.a.b.e.e.c.c
    public void d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
        this.f205a = pointF;
        this.f207c = pointF2;
        this.f206b = pointF4;
        this.f208d = pointF5;
        this.e = pointF3;
    }

    public PointF e(float f) {
        PointF pointF = this.f;
        float f2 = this.f207c.x;
        PointF pointF2 = this.f205a;
        float f3 = pointF2.x;
        pointF.x = ((f2 - f3) * f) + f3;
        pointF.y = pointF2.y;
        b.a.b.c.a.a(this, "getHorizontalPosition  currentPoint.x : " + this.f.x + " currentPoint.y " + this.f.y);
        return this.f;
    }

    public PointF f(float f) {
        PointF pointF = this.f;
        float f2 = this.f208d.y;
        PointF pointF2 = this.f206b;
        float f3 = pointF2.y;
        pointF.y = ((f2 - f3) * f) + f3;
        pointF.x = pointF2.x;
        b.a.b.c.a.a(this, "getVerticalPosition  currentPoint.x : " + this.f.x + " currentPoint.y " + this.f.y);
        return this.f;
    }

    public PointF g(float f) {
        PointF pointF = this.f207c;
        float f2 = pointF.y;
        PointF pointF2 = this.f205a;
        float f3 = f2 - pointF2.y;
        float f4 = pointF.x;
        float f5 = pointF2.x;
        float f6 = f3 / (f4 - f5);
        float f7 = f2 - (f6 * f4);
        PointF pointF3 = this.f;
        float f8 = ((f4 - f5) * f) + f5;
        pointF3.x = f8;
        pointF3.y = (f6 * f8) + f7;
        b.a.b.c.a.a(this, "getXPosition  currentPoint.x : " + this.f.x + " currentPoint.y " + this.f.y);
        return this.f;
    }

    public PointF h(float f) {
        PointF pointF = this.f208d;
        float f2 = pointF.y;
        PointF pointF2 = this.f206b;
        float f3 = pointF2.y;
        float f4 = pointF.x;
        float f5 = (f2 - f3) / (f4 - pointF2.x);
        float f6 = f2 - (f4 * f5);
        PointF pointF3 = this.f;
        float f7 = ((f2 - f3) * f) + f3;
        pointF3.y = f7;
        pointF3.x = (f7 - f6) / f5;
        b.a.b.c.a.a(this, "getYPosition  currentPoint.x : " + this.f.x + " currentPoint.y " + this.f.y);
        return this.f;
    }

    public boolean i() {
        return this.f205a.y == this.f207c.y;
    }

    public boolean j() {
        return this.f206b.x == this.f208d.x;
    }
}
