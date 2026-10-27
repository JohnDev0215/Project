package b.b.a.j.e.c;

import android.graphics.PointF;

/* JADX INFO: compiled from: BydLineInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PointF f575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PointF f577c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PointF f578d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public PointF f579e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PointF f580f = new PointF();

    @Override // b.b.a.j.e.c.c
    public void a(int i) {
    }

    @Override // b.b.a.j.e.c.c
    public void b(PointF pointF, PointF pointF2, PointF pointF3) {
    }

    @Override // b.b.a.j.e.c.c
    public PointF c(int i, float f2) {
        b.b.a.h.a.a(this, "getCurrentPoint  scrollDirection : " + i + " , input : " + f2);
        if (f2 < 0.0f || f2 > 1.0f) {
            return this.f580f;
        }
        if (i == 0) {
            if (i()) {
                e(f2);
            } else {
                g(f2);
            }
        } else if (j()) {
            f(f2);
        } else {
            h(f2);
        }
        return this.f580f;
    }

    @Override // b.b.a.j.e.c.c
    public void d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
        this.f575a = pointF;
        this.f577c = pointF2;
        this.f576b = pointF4;
        this.f578d = pointF5;
        this.f579e = pointF3;
    }

    public PointF e(float f2) {
        PointF pointF = this.f580f;
        float f3 = this.f577c.x;
        PointF pointF2 = this.f575a;
        float f4 = pointF2.x;
        pointF.x = ((f3 - f4) * f2) + f4;
        pointF.y = pointF2.y;
        b.b.a.h.a.a(this, "getHorizontalPosition  currentPoint.x : " + this.f580f.x + " currentPoint.y " + this.f580f.y);
        return this.f580f;
    }

    public PointF f(float f2) {
        PointF pointF = this.f580f;
        float f3 = this.f578d.y;
        PointF pointF2 = this.f576b;
        float f4 = pointF2.y;
        pointF.y = ((f3 - f4) * f2) + f4;
        pointF.x = pointF2.x;
        b.b.a.h.a.a(this, "getVerticalPosition  currentPoint.x : " + this.f580f.x + " currentPoint.y " + this.f580f.y);
        return this.f580f;
    }

    public PointF g(float f2) {
        PointF pointF = this.f577c;
        float f3 = pointF.y;
        PointF pointF2 = this.f575a;
        float f4 = f3 - pointF2.y;
        float f5 = pointF.x;
        float f6 = pointF2.x;
        float f7 = f4 / (f5 - f6);
        float f8 = f3 - (f7 * f5);
        PointF pointF3 = this.f580f;
        float f9 = ((f5 - f6) * f2) + f6;
        pointF3.x = f9;
        pointF3.y = (f7 * f9) + f8;
        b.b.a.h.a.a(this, "getXPosition  currentPoint.x : " + this.f580f.x + " currentPoint.y " + this.f580f.y);
        return this.f580f;
    }

    public PointF h(float f2) {
        PointF pointF = this.f578d;
        float f3 = pointF.y;
        PointF pointF2 = this.f576b;
        float f4 = pointF2.y;
        float f5 = pointF.x;
        float f6 = (f3 - f4) / (f5 - pointF2.x);
        float f7 = f3 - (f5 * f6);
        PointF pointF3 = this.f580f;
        float f8 = ((f3 - f4) * f2) + f4;
        pointF3.y = f8;
        pointF3.x = (f8 - f7) / f6;
        b.b.a.h.a.a(this, "getYPosition  currentPoint.x : " + this.f580f.x + " currentPoint.y " + this.f580f.y);
        return this.f580f;
    }

    public boolean i() {
        return this.f575a.y == this.f577c.y;
    }

    public boolean j() {
        return this.f576b.x == this.f578d.x;
    }
}
