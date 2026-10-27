package b.a.b.e.e.c;

import android.graphics.PointF;

/* JADX INFO: compiled from: BydCircleInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PointF f201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f202b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PointF f204d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PointF f203c = new PointF();
    public final PointF e = new PointF();
    public PointF f = new PointF();
    public int g = 1;

    @Override // b.a.b.e.e.c.c
    public void a(int i) {
        this.g = i;
    }

    @Override // b.a.b.e.e.c.c
    public void b(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f201a = pointF;
        this.f204d = pointF2;
        this.f202b = pointF3;
        b.a.b.c.a.a(this, " findCenterPoint :   mStartPoint.x " + pointF.x + " , mStartPoint.y " + pointF.y);
        b.a.b.c.a.a(this, " findCenterPoint :   mHalfPoint.x " + pointF2.x + " , mHalfPoint.y " + pointF2.y);
        b.a.b.c.a.a(this, " findCenterPoint :   mEndPoint.x " + pointF3.x + " , mEndPoint.y " + pointF3.y);
        e();
        f();
    }

    @Override // b.a.b.e.e.c.c
    public PointF c(int i, float f) {
        b.a.b.c.a.a(this, " input : " + f);
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f >= 0.0f && f <= 1.0f) {
            if (this.g == 1) {
                this.f = h(f);
            } else {
                this.f = g(f);
            }
        }
        b.a.b.c.a.a(this, " input : " + f + ", mCurrentPoint.x " + this.f.x + " , mCurrentPoint.y" + this.f.y);
        return this.f;
    }

    @Override // b.a.b.e.e.c.c
    public void d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
    }

    public final PointF e() {
        PointF pointF = this.f201a;
        float f = pointF.y;
        PointF pointF2 = this.f204d;
        float f2 = pointF2.y;
        float f3 = (-f) + f2;
        float f4 = pointF2.x;
        float f5 = pointF.x;
        float f6 = f4 - f5;
        PointF pointF3 = this.f202b;
        float f7 = pointF3.y;
        float f8 = f - f7;
        float f9 = pointF3.x;
        float f10 = f9 - f5;
        float f11 = (((f9 * f9) - (f5 * f5)) + (f7 * f7)) - (f * f);
        float f12 = (((f4 * f4) - (f5 * f5)) + (f2 * f2)) - (f * f);
        if (f10 != 0.0f || f8 == 0.0f) {
            if (f10 == 0.0f || f8 != 0.0f) {
                if (f6 == 0.0f && f3 != 0.0f) {
                    PointF pointF4 = this.f203c;
                    float f13 = (f11 * f3) + (f8 * f12);
                    float f14 = f3 * 2.0f;
                    pointF4.x = f13 / (f10 * f14);
                    pointF4.y = f12 / f14;
                } else if (f6 != 0.0f && f3 == 0.0f) {
                    PointF pointF5 = this.f203c;
                    float f15 = 2.0f * f6;
                    pointF5.x = f12 / f15;
                    pointF5.y = ((f12 * f10) - (f11 * f6)) / (f15 * f8);
                } else {
                    if (f6 == 0.0f || f3 == 0.0f) {
                        return null;
                    }
                    PointF pointF6 = this.f203c;
                    float f16 = (f12 * f8) + (f11 * f3);
                    float f17 = ((f8 * f6) + (f3 * f10)) * 2.0f;
                    pointF6.x = f16 / f17;
                    pointF6.y = ((f12 * f10) - (f11 * f6)) / f17;
                }
            } else if (f6 == 0.0f && f3 != 0.0f) {
                PointF pointF7 = this.f203c;
                pointF7.x = f11 / (f10 * 2.0f);
                pointF7.y = f12 / (f3 * 2.0f);
            } else {
                if ((f6 != 0.0f && f3 == 0.0f) || f6 == 0.0f || f3 == 0.0f) {
                    return null;
                }
                PointF pointF8 = this.f203c;
                float f18 = 2.0f * f10;
                pointF8.x = f11 / f18;
                pointF8.y = ((f12 * f10) - (f11 * f6)) / (f18 * f3);
            }
        } else {
            if (f6 == 0.0f && f3 != 0.0f) {
                return null;
            }
            if (f6 != 0.0f && f3 == 0.0f) {
                PointF pointF9 = this.f203c;
                pointF9.x = f12 / (f6 * 2.0f);
                pointF9.y = (-f11) / (f8 * 2.0f);
            } else {
                if (f6 == 0.0f || f3 == 0.0f) {
                    return null;
                }
                PointF pointF10 = this.f203c;
                pointF10.y = (-f11) / (f8 * 2.0f);
                pointF10.x = ((f12 * f8) + (f3 * f11)) / ((f6 * 2.0f) * f8);
            }
        }
        b.a.b.c.a.a(this, " findCenterPoint :  mCenterPoint.x : " + this.f203c.x + " mCenterPoint.y : " + this.f203c.y);
        return this.f203c;
    }

    public final PointF f() {
        PointF pointF = this.f203c;
        float f = pointF.y;
        float f2 = pointF.x;
        PointF pointF2 = this.e;
        PointF pointF3 = this.f204d;
        pointF2.x = (pointF3.x * 2.0f) - f2;
        pointF2.y = (pointF3.y * 2.0f) - f;
        b.a.b.c.a.a(this, " findmControlPoint :   mControlPoint.x " + this.e.x + " , mControlPoint.y " + this.e.y + " mAreaPoint.x : " + this.f204d.x + " , mAreaPoint.y " + this.f204d.y + " , mCenterPoint.x " + this.f203c.x + " ,mCenterPoint.y " + this.f203c.y + " mStartPoint.x : " + this.f201a.x + " mStartPoint.y : " + this.f201a.y + " mEndPoint.x " + this.f202b.x + " mEndPoint.y " + this.f202b.y + " mAreaPoint.x  ： " + this.f204d.x + " mAreaPoint.y " + this.f204d.y);
        return this.e;
    }

    public PointF g(float f) {
        PointF pointF = this.f203c;
        float f2 = pointF.x;
        PointF pointF2 = this.f202b;
        float f3 = pointF2.x;
        float f4 = (f2 - f3) * (f2 - f3);
        float f5 = pointF.y;
        float f6 = pointF2.y;
        float fSqrt = (float) Math.sqrt(f4 + ((f5 - f6) * (f5 - f6)));
        float f7 = this.f202b.x;
        float f8 = this.f201a.x;
        float f9 = ((f7 - f8) * f) + f8;
        float f10 = fSqrt * fSqrt;
        float f11 = this.f203c.x;
        double dSqrt = Math.sqrt(f10 - ((f9 - f11) * (f9 - f11)));
        PointF pointF3 = this.f203c;
        int i = (int) (dSqrt + ((double) pointF3.y));
        float f12 = pointF3.x;
        int iSqrt = (int) (Math.sqrt(f10 - ((f9 - f12) * (f9 - f12))) - ((double) this.f203c.y));
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(iSqrt);
        float fMax = Math.max(this.f201a.y, this.f202b.y);
        float fMin = Math.min(this.f201a.y, this.f202b.y);
        float f13 = iAbs;
        if (f13 >= fMin && f13 <= fMax) {
            this.f.y = f13;
        }
        float f14 = iAbs2;
        if (f14 >= fMin && f14 <= fMax) {
            this.f.y = f14;
        }
        this.f.x = f9;
        b.a.b.c.a.a(this, " cubicCurves  :  t : " + f + " x ： " + f9 + " y1 : " + iAbs + " y2 " + iAbs2 + " r " + fSqrt + " mCurrentPoint.x " + this.f.x + " mCurrentPoint.y " + this.f.y + " mStartPoint.x " + this.f201a.x + "  mStartPoint.y " + this.f201a.y + " mEndPoint.x " + this.f202b.x + " mEndPoint.y " + this.f202b.y + " mCenterPoint.x " + this.f203c.x + " mCenterPoint.y " + this.f203c.y + " mAreaPoint.x : " + this.f204d.x + " mAreaPoint.y : " + this.f204d.y);
        return this.f;
    }

    public PointF h(float f) {
        PointF pointF = this.f203c;
        float f2 = pointF.x;
        PointF pointF2 = this.f202b;
        float f3 = pointF2.x;
        float f4 = (f2 - f3) * (f2 - f3);
        float f5 = pointF.y;
        float f6 = pointF2.y;
        float fSqrt = (float) Math.sqrt(f4 + ((f5 - f6) * (f5 - f6)));
        float f7 = this.f202b.y;
        float f8 = this.f201a.y;
        float f9 = ((f7 - f8) * f) + f8;
        float f10 = fSqrt * fSqrt;
        float f11 = this.f203c.y;
        double dSqrt = Math.sqrt(f10 - ((f9 - f11) * (f9 - f11)));
        PointF pointF3 = this.f203c;
        int i = (int) (dSqrt + ((double) pointF3.x));
        float f12 = pointF3.y;
        int iSqrt = (int) (Math.sqrt(f10 - ((f9 - f12) * (f9 - f12))) - ((double) this.f203c.x));
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(iSqrt);
        float fMin = Math.min(this.f201a.x, this.f202b.x);
        float fMax = Math.max(this.f201a.x, this.f202b.x);
        float f13 = iAbs;
        float f14 = fMin - 5.0f;
        if (f13 >= f14 && f13 <= fMax + 5.0f) {
            PointF pointF4 = this.f;
            pointF4.x = f13;
            pointF4.y = f9;
        }
        float f15 = iAbs2;
        if (f15 >= f14 && f15 <= fMax + 5.0f) {
            PointF pointF5 = this.f;
            pointF5.x = f15;
            pointF5.y = f9;
        }
        b.a.b.c.a.a(this, " cubicCurves  :  t : " + f + " y ： " + f9 + " x1 : " + iAbs + " x2 " + iAbs2 + " r " + fSqrt + " mCurrentPoint.x " + this.f.x + " mCurrentPoint.y " + this.f.y + " mStartPoint.x " + this.f201a.x + "  mStartPoint.y " + this.f201a.y + " mEndPoint.x " + this.f202b.x + " mEndPoint.y " + this.f202b.y + " mCenterPoint.x " + this.f203c.x + " mCenterPoint.y " + this.f203c.y + " mAreaPoint.x : " + this.f204d.x + " mAreaPoint.y : " + this.f204d.y);
        return this.f;
    }
}
