package com.byd.imageedit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    boolean f2873a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2874b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    float f2875c;
    Path d = new Path();
    int e;
    private float f;
    private float g;
    Bitmap h;

    public a(boolean z, int i, float f, int i2) {
        this.f2873a = z;
        this.f2874b = i;
        this.f2875c = f;
        this.e = i2;
    }

    private void b(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float degrees = (float) Math.toDegrees((float) Math.atan(r5 / Math.abs(f5)));
        float fSqrt = ((float) Math.sqrt(Math.pow(f5, 2.0d) + Math.pow(f4 - f2, 2.0d))) - this.f2875c;
        float degrees2 = (float) Math.toDegrees(Math.atan((r6 / 3.0f) / fSqrt));
        if (f3 < f) {
            degrees = 180.0f - degrees;
        }
        double d = fSqrt;
        double d2 = degrees;
        float fAbs = (float) Math.abs(Math.cos(Math.toRadians(d2)) * d);
        float fAbs2 = (float) Math.abs(Math.sin(Math.toRadians(d2)) * d);
        if (f3 > f) {
            fAbs *= -1.0f;
        }
        if (f4 > f2) {
            fAbs2 *= -1.0f;
        }
        double radians = Math.toRadians(degrees - degrees2);
        float fCos = ((float) (d * Math.cos(radians))) + f;
        float fSin = ((float) (Math.sin(radians) * d)) + f2;
        float f6 = fCos + fAbs;
        float f7 = fSin + fAbs2;
        double radians2 = Math.toRadians(degrees2 + degrees);
        float fCos2 = ((float) (d * Math.cos(radians2))) + f;
        float fSin2 = ((float) (Math.sin(radians2) * d)) + f2;
        float degrees3 = (float) Math.toDegrees(Math.atan(this.f2875c / fSqrt));
        double radians3 = Math.toRadians(degrees - degrees3);
        double dCos = (float) (d * Math.cos(Math.toRadians(degrees3)));
        float fCos3 = ((float) (Math.cos(radians3) * dCos)) + f;
        float fSin3 = ((float) (Math.sin(radians3) * dCos)) + f2;
        double radians4 = Math.toRadians(degrees + degrees3);
        float fCos4 = ((float) (dCos * Math.cos(radians4))) + f;
        float fSin4 = ((float) (dCos * Math.sin(radians4))) + f2;
        this.d.moveTo(f, f2);
        this.d.lineTo(f6, f7);
        this.d.lineTo(fCos, fSin);
        this.d.lineTo(fCos3, fSin3);
        this.d.lineTo(f3, f4);
        this.d.lineTo(fCos4, fSin4);
        this.d.lineTo(fCos2, fSin2);
        this.d.lineTo(fAbs + fCos2, fAbs2 + fSin2);
        this.d.close();
    }

    void a(Canvas canvas, Paint paint) {
        if (!this.f2873a) {
            paint.setColor(this.f2874b);
        }
        if (this.e == 2) {
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(this.f2875c);
        }
        canvas.drawPath(this.d, paint);
    }

    void c(float f, float f2) {
        int i = this.e;
        if (i == 1 || i == 10) {
            this.d.lineTo(f, f2 + 0.1f);
        }
    }

    void d(float f, float f2) {
        float f3;
        Path path;
        Path.Direction direction;
        float f4;
        float f5;
        float f6;
        Path path2;
        RectF rectF;
        int i = this.e;
        if (i != 1) {
            if (i == 2) {
                this.d.reset();
                b(this.f, this.g, f, f2);
                return;
            }
            if (i == 3) {
                this.d.reset();
                this.d.moveTo(this.f, this.g);
                this.d.lineTo(f, f2);
                return;
            }
            if (i == 4) {
                this.d.reset();
                float f7 = this.f;
                if (f7 < f) {
                    f3 = this.g;
                    if (f3 > f2) {
                        path = this.d;
                        direction = Path.Direction.CCW;
                        f4 = f7;
                        f5 = f2;
                        f7 = f;
                        f6 = f3;
                    } else {
                        path = this.d;
                        direction = Path.Direction.CCW;
                        f4 = f7;
                        f5 = f3;
                        f7 = f;
                        f6 = f2;
                    }
                } else {
                    f3 = this.g;
                    if (f3 > f2) {
                        path = this.d;
                        direction = Path.Direction.CCW;
                        f4 = f;
                        f5 = f2;
                        f6 = f3;
                    } else {
                        path = this.d;
                        direction = Path.Direction.CCW;
                        f4 = f;
                        f5 = f3;
                        f6 = f2;
                    }
                }
                path.addRect(f4, f5, f7, f6, direction);
                return;
            }
            if (i == 5) {
                this.d.reset();
                float f8 = this.f;
                if (f8 < f) {
                    float f9 = this.g;
                    if (f9 > f2) {
                        path2 = this.d;
                        rectF = new RectF(f8, f2, f, f9);
                    } else {
                        path2 = this.d;
                        rectF = new RectF(f8, f9, f, f2);
                    }
                } else {
                    float f10 = this.g;
                    if (f10 > f2) {
                        path2 = this.d;
                        rectF = new RectF(f, f2, f8, f10);
                    } else {
                        path2 = this.d;
                        rectF = new RectF(f, f10, f8, f2);
                    }
                }
                path2.addArc(rectF, 0.0f, 360.0f);
                return;
            }
            if (i != 10) {
                return;
            }
        }
        Path path3 = this.d;
        float f11 = this.f;
        float f12 = this.g;
        path3.quadTo(f11, f12, (f + f11) / 2.0f, (f2 + f12) / 2.0f);
        this.f = f;
        this.g = f2;
    }

    void e(float f, float f2) {
        this.d.moveTo(f, f2);
        this.f = f;
        this.g = f2;
    }
}
