package b.b.a.j.e.c;

import android.graphics.PointF;

/* JADX INFO: compiled from: BydArcInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PointF f563a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f564b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PointF f566d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PointF f565c = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PointF f567e = new PointF();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final PointF f568f = new PointF();

    @Override // b.b.a.j.e.c.c
    public void a(int i) {
    }

    @Override // b.b.a.j.e.c.c
    public void b(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f563a = pointF;
        this.f566d = pointF2;
        this.f564b = pointF3;
        b.b.a.h.a.a(this, " findCenterPoint :   mStartPoint.x " + pointF.x + " , mStartPoint.y " + pointF.y);
        b.b.a.h.a.a(this, " findCenterPoint :   mHalfPoint.x " + pointF2.x + " , mHalfPoint.y " + pointF2.y);
        b.b.a.h.a.a(this, " findCenterPoint :   mEndPoint.x " + pointF3.x + " , mEndPoint.y " + pointF3.y);
        f();
        g();
    }

    @Override // b.b.a.j.e.c.c
    public PointF c(int i, float f2) {
        b.b.a.h.a.a(this, " getCurrentPoint  input : " + f2 + " direction : " + i);
        if (f2 >= 0.0f && f2 <= 1.0f) {
            this.f568f.x = e(f2, this.f563a.x, this.f567e.x, this.f564b.x);
            this.f568f.y = e(f2, this.f563a.y, this.f567e.y, this.f564b.y);
        }
        b.b.a.h.a.a(this, "getCurrentPoint  input : " + f2 + ", mCurrentPoint.x " + this.f568f.x + " , mCurrentPoint.y" + this.f568f.y);
        return this.f568f;
    }

    @Override // b.b.a.j.e.c.c
    public void d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
    }

    public float e(float f2, float f3, float f4, float f5) {
        float f6 = 1.0f - f2;
        float f7 = (f6 * f6 * f3) + (2.0f * f2 * f6 * f4) + (f2 * f2 * f5);
        b.b.a.h.a.a(this, " cubicCurves  :  t : " + f2 + " start ： " + f3 + " control : " + f4 + " end " + f5 + " value " + f7);
        return f7;
    }

    public final PointF f() {
        PointF pointF = this.f563a;
        float f2 = pointF.y;
        PointF pointF2 = this.f566d;
        float f3 = pointF2.y;
        float f4 = (-f2) + f3;
        float f5 = pointF2.x;
        float f6 = pointF.x;
        float f7 = f5 - f6;
        PointF pointF3 = this.f564b;
        float f8 = pointF3.y;
        float f9 = f2 - f8;
        float f10 = pointF3.x;
        float f11 = f10 - f6;
        float f12 = (((f10 * f10) - (f6 * f6)) + (f8 * f8)) - (f2 * f2);
        float f13 = (((f5 * f5) - (f6 * f6)) + (f3 * f3)) - (f2 * f2);
        if (f11 != 0.0f || f9 == 0.0f) {
            if (f11 == 0.0f || f9 != 0.0f) {
                if (f7 == 0.0f && f4 != 0.0f) {
                    PointF pointF4 = this.f565c;
                    float f14 = (f12 * f4) + (f9 * f13);
                    float f15 = f4 * 2.0f;
                    pointF4.x = f14 / (f11 * f15);
                    pointF4.y = f13 / f15;
                } else if (f7 != 0.0f && f4 == 0.0f) {
                    PointF pointF5 = this.f565c;
                    float f16 = 2.0f * f7;
                    pointF5.x = f13 / f16;
                    pointF5.y = ((f13 * f11) - (f12 * f7)) / (f16 * f9);
                } else {
                    if (f7 == 0.0f || f4 == 0.0f) {
                        return null;
                    }
                    PointF pointF6 = this.f565c;
                    float f17 = (f13 * f9) + (f12 * f4);
                    float f18 = ((f9 * f7) + (f4 * f11)) * 2.0f;
                    pointF6.x = f17 / f18;
                    pointF6.y = ((f13 * f11) - (f12 * f7)) / f18;
                }
            } else if (f7 == 0.0f && f4 != 0.0f) {
                PointF pointF7 = this.f565c;
                pointF7.x = f12 / (f11 * 2.0f);
                pointF7.y = f13 / (f4 * 2.0f);
            } else {
                if ((f7 != 0.0f && f4 == 0.0f) || f7 == 0.0f || f4 == 0.0f) {
                    return null;
                }
                PointF pointF8 = this.f565c;
                float f19 = 2.0f * f11;
                pointF8.x = f12 / f19;
                pointF8.y = ((f13 * f11) - (f12 * f7)) / (f19 * f4);
            }
        } else {
            if (f7 == 0.0f && f4 != 0.0f) {
                return null;
            }
            if (f7 != 0.0f && f4 == 0.0f) {
                PointF pointF9 = this.f565c;
                pointF9.x = f13 / (f7 * 2.0f);
                pointF9.y = (-f12) / (f9 * 2.0f);
            } else {
                if (f7 == 0.0f || f4 == 0.0f) {
                    return null;
                }
                PointF pointF10 = this.f565c;
                pointF10.y = (-f12) / (f9 * 2.0f);
                pointF10.x = ((f13 * f9) + (f4 * f12)) / ((f7 * 2.0f) * f9);
            }
        }
        b.b.a.h.a.a(this, " findCenterPoint :  mCenterPoint.x : " + this.f565c.x + " mCenterPoint.y : " + this.f565c.y);
        return this.f565c;
    }

    public final PointF g() {
        PointF pointF = this.f565c;
        float f2 = pointF.y;
        float f3 = pointF.x;
        PointF pointF2 = this.f567e;
        PointF pointF3 = this.f566d;
        pointF2.x = (pointF3.x * 2.0f) - f3;
        pointF2.y = (pointF3.y * 2.0f) - f2;
        b.b.a.h.a.a(this, " findmControlPoint :   mControlPoint.x " + this.f567e.x + " , mControlPoint.y " + this.f567e.y + " mAreaPoint.x : " + this.f566d.x + " , mAreaPoint.y " + this.f566d.y + " , mCenterPoint.x " + this.f565c.x + " ,mCenterPoint.y " + this.f565c.y + " mStartPoint.x : " + this.f563a.x + " mStartPoint.y : " + this.f563a.y + " mEndPoint.x " + this.f564b.x + " mEndPoint.y " + this.f564b.y + " mAreaPoint.x  ： " + this.f566d.x + " mAreaPoint.y " + this.f566d.y);
        return this.f567e;
    }
}
