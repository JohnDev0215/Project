package b.b.a.j.e.c;

import android.graphics.PointF;

/* JADX INFO: compiled from: BydCircleInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public PointF f569a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f570b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PointF f572d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final PointF f571c = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PointF f573e = new PointF();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PointF f574f = new PointF();
    public int g = 1;

    @Override // b.b.a.j.e.c.c
    public void a(int i) {
        this.g = i;
    }

    @Override // b.b.a.j.e.c.c
    public void b(PointF pointF, PointF pointF2, PointF pointF3) {
        this.f569a = pointF;
        this.f572d = pointF2;
        this.f570b = pointF3;
        b.b.a.h.a.a(this, " findCenterPoint :   mStartPoint.x " + pointF.x + " , mStartPoint.y " + pointF.y);
        b.b.a.h.a.a(this, " findCenterPoint :   mHalfPoint.x " + pointF2.x + " , mHalfPoint.y " + pointF2.y);
        b.b.a.h.a.a(this, " findCenterPoint :   mEndPoint.x " + pointF3.x + " , mEndPoint.y " + pointF3.y);
        e();
        f();
    }

    @Override // b.b.a.j.e.c.c
    public PointF c(int i, float f2) {
        b.b.a.h.a.a(this, " input : " + f2);
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 >= 0.0f && f2 <= 1.0f) {
            if (this.g == 1) {
                this.f574f = h(f2);
            } else {
                this.f574f = g(f2);
            }
        }
        b.b.a.h.a.a(this, " input : " + f2 + ", mCurrentPoint.x " + this.f574f.x + " , mCurrentPoint.y" + this.f574f.y);
        return this.f574f;
    }

    @Override // b.b.a.j.e.c.c
    public void d(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF pointF5) {
    }

    public final PointF e() {
        PointF pointF = this.f569a;
        float f2 = pointF.y;
        PointF pointF2 = this.f572d;
        float f3 = pointF2.y;
        float f4 = (-f2) + f3;
        float f5 = pointF2.x;
        float f6 = pointF.x;
        float f7 = f5 - f6;
        PointF pointF3 = this.f570b;
        float f8 = pointF3.y;
        float f9 = f2 - f8;
        float f10 = pointF3.x;
        float f11 = f10 - f6;
        float f12 = (((f10 * f10) - (f6 * f6)) + (f8 * f8)) - (f2 * f2);
        float f13 = (((f5 * f5) - (f6 * f6)) + (f3 * f3)) - (f2 * f2);
        if (f11 != 0.0f || f9 == 0.0f) {
            if (f11 == 0.0f || f9 != 0.0f) {
                if (f7 == 0.0f && f4 != 0.0f) {
                    PointF pointF4 = this.f571c;
                    float f14 = (f12 * f4) + (f9 * f13);
                    float f15 = f4 * 2.0f;
                    pointF4.x = f14 / (f11 * f15);
                    pointF4.y = f13 / f15;
                } else if (f7 != 0.0f && f4 == 0.0f) {
                    PointF pointF5 = this.f571c;
                    float f16 = 2.0f * f7;
                    pointF5.x = f13 / f16;
                    pointF5.y = ((f13 * f11) - (f12 * f7)) / (f16 * f9);
                } else {
                    if (f7 == 0.0f || f4 == 0.0f) {
                        return null;
                    }
                    PointF pointF6 = this.f571c;
                    float f17 = (f13 * f9) + (f12 * f4);
                    float f18 = ((f9 * f7) + (f4 * f11)) * 2.0f;
                    pointF6.x = f17 / f18;
                    pointF6.y = ((f13 * f11) - (f12 * f7)) / f18;
                }
            } else if (f7 == 0.0f && f4 != 0.0f) {
                PointF pointF7 = this.f571c;
                pointF7.x = f12 / (f11 * 2.0f);
                pointF7.y = f13 / (f4 * 2.0f);
            } else {
                if ((f7 != 0.0f && f4 == 0.0f) || f7 == 0.0f || f4 == 0.0f) {
                    return null;
                }
                PointF pointF8 = this.f571c;
                float f19 = 2.0f * f11;
                pointF8.x = f12 / f19;
                pointF8.y = ((f13 * f11) - (f12 * f7)) / (f19 * f4);
            }
        } else {
            if (f7 == 0.0f && f4 != 0.0f) {
                return null;
            }
            if (f7 != 0.0f && f4 == 0.0f) {
                PointF pointF9 = this.f571c;
                pointF9.x = f13 / (f7 * 2.0f);
                pointF9.y = (-f12) / (f9 * 2.0f);
            } else {
                if (f7 == 0.0f || f4 == 0.0f) {
                    return null;
                }
                PointF pointF10 = this.f571c;
                pointF10.y = (-f12) / (f9 * 2.0f);
                pointF10.x = ((f13 * f9) + (f4 * f12)) / ((f7 * 2.0f) * f9);
            }
        }
        b.b.a.h.a.a(this, " findCenterPoint :  mCenterPoint.x : " + this.f571c.x + " mCenterPoint.y : " + this.f571c.y);
        return this.f571c;
    }

    public final PointF f() {
        PointF pointF = this.f571c;
        float f2 = pointF.y;
        float f3 = pointF.x;
        PointF pointF2 = this.f573e;
        PointF pointF3 = this.f572d;
        pointF2.x = (pointF3.x * 2.0f) - f3;
        pointF2.y = (pointF3.y * 2.0f) - f2;
        b.b.a.h.a.a(this, " findmControlPoint :   mControlPoint.x " + this.f573e.x + " , mControlPoint.y " + this.f573e.y + " mAreaPoint.x : " + this.f572d.x + " , mAreaPoint.y " + this.f572d.y + " , mCenterPoint.x " + this.f571c.x + " ,mCenterPoint.y " + this.f571c.y + " mStartPoint.x : " + this.f569a.x + " mStartPoint.y : " + this.f569a.y + " mEndPoint.x " + this.f570b.x + " mEndPoint.y " + this.f570b.y + " mAreaPoint.x  ： " + this.f572d.x + " mAreaPoint.y " + this.f572d.y);
        return this.f573e;
    }

    public PointF g(float f2) {
        PointF pointF = this.f571c;
        float f3 = pointF.x;
        PointF pointF2 = this.f570b;
        float f4 = pointF2.x;
        float f5 = (f3 - f4) * (f3 - f4);
        float f6 = pointF.y;
        float f7 = pointF2.y;
        float fSqrt = (float) Math.sqrt(f5 + ((f6 - f7) * (f6 - f7)));
        float f8 = this.f570b.x;
        float f9 = this.f569a.x;
        float f10 = ((f8 - f9) * f2) + f9;
        float f11 = fSqrt * fSqrt;
        float f12 = this.f571c.x;
        double dSqrt = Math.sqrt(f11 - ((f10 - f12) * (f10 - f12)));
        PointF pointF3 = this.f571c;
        int i = (int) (dSqrt + ((double) pointF3.y));
        float f13 = pointF3.x;
        int iSqrt = (int) (Math.sqrt(f11 - ((f10 - f13) * (f10 - f13))) - ((double) this.f571c.y));
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(iSqrt);
        float fMax = Math.max(this.f569a.y, this.f570b.y);
        float fMin = Math.min(this.f569a.y, this.f570b.y);
        float f14 = iAbs;
        if (f14 >= fMin && f14 <= fMax) {
            this.f574f.y = f14;
        }
        float f15 = iAbs2;
        if (f15 >= fMin && f15 <= fMax) {
            this.f574f.y = f15;
        }
        this.f574f.x = f10;
        b.b.a.h.a.a(this, " cubicCurves  :  t : " + f2 + " x ： " + f10 + " y1 : " + iAbs + " y2 " + iAbs2 + " r " + fSqrt + " mCurrentPoint.x " + this.f574f.x + " mCurrentPoint.y " + this.f574f.y + " mStartPoint.x " + this.f569a.x + "  mStartPoint.y " + this.f569a.y + " mEndPoint.x " + this.f570b.x + " mEndPoint.y " + this.f570b.y + " mCenterPoint.x " + this.f571c.x + " mCenterPoint.y " + this.f571c.y + " mAreaPoint.x : " + this.f572d.x + " mAreaPoint.y : " + this.f572d.y);
        return this.f574f;
    }

    public PointF h(float f2) {
        PointF pointF = this.f571c;
        float f3 = pointF.x;
        PointF pointF2 = this.f570b;
        float f4 = pointF2.x;
        float f5 = (f3 - f4) * (f3 - f4);
        float f6 = pointF.y;
        float f7 = pointF2.y;
        float fSqrt = (float) Math.sqrt(f5 + ((f6 - f7) * (f6 - f7)));
        float f8 = this.f570b.y;
        float f9 = this.f569a.y;
        float f10 = ((f8 - f9) * f2) + f9;
        float f11 = fSqrt * fSqrt;
        float f12 = this.f571c.y;
        double dSqrt = Math.sqrt(f11 - ((f10 - f12) * (f10 - f12)));
        PointF pointF3 = this.f571c;
        int i = (int) (dSqrt + ((double) pointF3.x));
        float f13 = pointF3.y;
        int iSqrt = (int) (Math.sqrt(f11 - ((f10 - f13) * (f10 - f13))) - ((double) this.f571c.x));
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(iSqrt);
        float fMin = Math.min(this.f569a.x, this.f570b.x);
        float fMax = Math.max(this.f569a.x, this.f570b.x);
        float f14 = iAbs;
        float f15 = fMin - 5.0f;
        if (f14 >= f15 && f14 <= fMax + 5.0f) {
            PointF pointF4 = this.f574f;
            pointF4.x = f14;
            pointF4.y = f10;
        }
        float f16 = iAbs2;
        if (f16 >= f15 && f16 <= fMax + 5.0f) {
            PointF pointF5 = this.f574f;
            pointF5.x = f16;
            pointF5.y = f10;
        }
        b.b.a.h.a.a(this, " cubicCurves  :  t : " + f2 + " y ： " + f10 + " x1 : " + iAbs + " x2 " + iAbs2 + " r " + fSqrt + " mCurrentPoint.x " + this.f574f.x + " mCurrentPoint.y " + this.f574f.y + " mStartPoint.x " + this.f569a.x + "  mStartPoint.y " + this.f569a.y + " mEndPoint.x " + this.f570b.x + " mEndPoint.y " + this.f570b.y + " mCenterPoint.x " + this.f571c.x + " mCenterPoint.y " + this.f571c.y + " mAreaPoint.x : " + this.f572d.x + " mAreaPoint.y : " + this.f572d.y);
        return this.f574f;
    }
}
