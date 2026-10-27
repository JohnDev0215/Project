package b.a.b.e.e.c;

import android.graphics.PointF;

/* JADX INFO: compiled from: BydArcInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PointF f197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f198b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PointF f200d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PointF f199c = new PointF();
    public final PointF e = new PointF();
    public final PointF f = new PointF();

    @Override // b.a.b.e.e.c.c
    public void a(int i) {
    }

    @Override // b.a.b.e.e.c.c
    public void b(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f197a = pointF;
        this.f200d = pointF2;
        this.f198b = pointF3;
        b.a.b.c.a.a(this, " findCenterPoint :   mStartPoint.x " + pointF.x + " , mStartPoint.y " + pointF.y);
        b.a.b.c.a.a(this, " findCenterPoint :   mHalfPoint.x " + pointF2.x + " , mHalfPoint.y " + pointF2.y);
        b.a.b.c.a.a(this, " findCenterPoint :   mEndPoint.x " + pointF3.x + " , mEndPoint.y " + pointF3.y);
        f();
        g();
    }

    @Override // b.a.b.e.e.c.c
    public PointF c(int i, float f) {
        b.a.b.c.a.a(this, " getCurrentPoint  input : " + f + " direction : " + i);
        if (f >= 0.0f && f <= 1.0f) {
            this.f.x = e(f, this.f197a.x, this.e.x, this.f198b.x);
            this.f.y = e(f, this.f197a.y, this.e.y, this.f198b.y);
        }
        b.a.b.c.a.a(this, "getCurrentPoint  input : " + f + ", mCurrentPoint.x " + this.f.x + " , mCurrentPoint.y" + this.f.y);
        return this.f;
    }

    @Override // b.a.b.e.e.c.c
    public void d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
    }

    public float e(float f, float f2, float f3, float f4) {
        float f5 = 1.0f - f;
        float f6 = (f5 * f5 * f2) + (2.0f * f * f5 * f3) + (f * f * f4);
        b.a.b.c.a.a(this, " cubicCurves  :  t : " + f + " start ： " + f2 + " control : " + f3 + " end " + f4 + " value " + f6);
        return f6;
    }

    public final PointF f() {
        PointF pointF = this.f197a;
        float f = pointF.y;
        PointF pointF2 = this.f200d;
        float f2 = pointF2.y;
        float f3 = (-f) + f2;
        float f4 = pointF2.x;
        float f5 = pointF.x;
        float f6 = f4 - f5;
        PointF pointF3 = this.f198b;
        float f7 = pointF3.y;
        float f8 = f - f7;
        float f9 = pointF3.x;
        float f10 = f9 - f5;
        float f11 = (((f9 * f9) - (f5 * f5)) + (f7 * f7)) - (f * f);
        float f12 = (((f4 * f4) - (f5 * f5)) + (f2 * f2)) - (f * f);
        if (f10 != 0.0f || f8 == 0.0f) {
            if (f10 == 0.0f || f8 != 0.0f) {
                if (f6 == 0.0f && f3 != 0.0f) {
                    PointF pointF4 = this.f199c;
                    float f13 = (f11 * f3) + (f8 * f12);
                    float f14 = f3 * 2.0f;
                    pointF4.x = f13 / (f10 * f14);
                    pointF4.y = f12 / f14;
                } else if (f6 != 0.0f && f3 == 0.0f) {
                    PointF pointF5 = this.f199c;
                    float f15 = 2.0f * f6;
                    pointF5.x = f12 / f15;
                    pointF5.y = ((f12 * f10) - (f11 * f6)) / (f15 * f8);
                } else {
                    if (f6 == 0.0f || f3 == 0.0f) {
                        return null;
                    }
                    PointF pointF6 = this.f199c;
                    float f16 = (f12 * f8) + (f11 * f3);
                    float f17 = ((f8 * f6) + (f3 * f10)) * 2.0f;
                    pointF6.x = f16 / f17;
                    pointF6.y = ((f12 * f10) - (f11 * f6)) / f17;
                }
            } else if (f6 == 0.0f && f3 != 0.0f) {
                PointF pointF7 = this.f199c;
                pointF7.x = f11 / (f10 * 2.0f);
                pointF7.y = f12 / (f3 * 2.0f);
            } else {
                if ((f6 != 0.0f && f3 == 0.0f) || f6 == 0.0f || f3 == 0.0f) {
                    return null;
                }
                PointF pointF8 = this.f199c;
                float f18 = 2.0f * f10;
                pointF8.x = f11 / f18;
                pointF8.y = ((f12 * f10) - (f11 * f6)) / (f18 * f3);
            }
        } else {
            if (f6 == 0.0f && f3 != 0.0f) {
                return null;
            }
            if (f6 != 0.0f && f3 == 0.0f) {
                PointF pointF9 = this.f199c;
                pointF9.x = f12 / (f6 * 2.0f);
                pointF9.y = (-f11) / (f8 * 2.0f);
            } else {
                if (f6 == 0.0f || f3 == 0.0f) {
                    return null;
                }
                PointF pointF10 = this.f199c;
                pointF10.y = (-f11) / (f8 * 2.0f);
                pointF10.x = ((f12 * f8) + (f3 * f11)) / ((f6 * 2.0f) * f8);
            }
        }
        b.a.b.c.a.a(this, " findCenterPoint :  mCenterPoint.x : " + this.f199c.x + " mCenterPoint.y : " + this.f199c.y);
        return this.f199c;
    }

    public final PointF g() {
        PointF pointF = this.f199c;
        float f = pointF.y;
        float f2 = pointF.x;
        PointF pointF2 = this.e;
        PointF pointF3 = this.f200d;
        pointF2.x = (pointF3.x * 2.0f) - f2;
        pointF2.y = (pointF3.y * 2.0f) - f;
        b.a.b.c.a.a(this, " findmControlPoint :   mControlPoint.x " + this.e.x + " , mControlPoint.y " + this.e.y + " mAreaPoint.x : " + this.f200d.x + " , mAreaPoint.y " + this.f200d.y + " , mCenterPoint.x " + this.f199c.x + " ,mCenterPoint.y " + this.f199c.y + " mStartPoint.x : " + this.f197a.x + " mStartPoint.y : " + this.f197a.y + " mEndPoint.x " + this.f198b.x + " mEndPoint.y " + this.f198b.y + " mAreaPoint.x  ： " + this.f200d.x + " mAreaPoint.y " + this.f200d.y);
        return this.e;
    }
}
