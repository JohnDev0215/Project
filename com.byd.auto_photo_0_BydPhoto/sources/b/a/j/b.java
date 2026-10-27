package b.a.j;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.MotionEvent;
import com.byd.imageedit.CropImageView;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f520a = 140;
    private String A;
    private boolean C;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Paint f521b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected Path f522c;
    protected Rect d;
    private Rect e;
    private int f;
    private int g;
    private boolean h;
    private double i;
    private Point j;
    private double k;
    private int m;
    private float o;
    private CropImageView q;
    private c r;
    private boolean u;
    private boolean v;
    private int w;
    private int[] z;
    private boolean l = true;
    private Point n = new Point();
    private float p = 0.0f;
    private int s = 60;
    private int t = 5;
    private boolean x = true;
    protected boolean y = true;
    private boolean B = true;
    private Path D = new Path();

    public b(CropImageView cropImageView, boolean z) {
        this.q = cropImageView;
        this.h = z;
        Paint paint = new Paint();
        this.f521b = paint;
        paint.setAntiAlias(true);
        this.f521b.setStrokeWidth(1.0f);
        this.f521b.setColor(-1);
        this.f522c = new Path();
        this.r = new c();
        this.z = new int[2];
        this.f521b.setTextSize((int) ((cropImageView.getContext().getResources().getDisplayMetrics().scaledDensity * 16.0f) + 0.5f));
    }

    private void a(Rect rect, int i, int i2) {
        int i3 = this.f;
        int[] iArr = this.z;
        int i4 = (iArr[1] * i3) / iArr[0];
        int i5 = this.g;
        if (i4 <= i5) {
            int i6 = i4 >> 1;
            rect.top = i2 - i6;
            rect.bottom = i2 + i6;
        } else {
            int i7 = (i5 * iArr[0]) / iArr[1];
            if (i7 <= i3) {
                int i8 = i7 >> 1;
                rect.left = i - i8;
                rect.right = i + i8;
            }
        }
    }

    private void b(boolean z) {
        int[] iArr = this.z;
        if (iArr[0] <= 0 || iArr[1] <= 0) {
            return;
        }
        int iWidth = this.d.width();
        int[] iArr2 = this.z;
        int i = (iWidth * iArr2[1]) / iArr2[0];
        int i2 = this.g;
        if (i <= i2) {
            if (z) {
                Rect rect = this.d;
                rect.bottom = rect.top + i;
            } else {
                Rect rect2 = this.d;
                rect2.top = rect2.bottom - i;
            }
            j(0, i);
            return;
        }
        Rect rect3 = this.d;
        Rect rect4 = this.e;
        rect3.top = rect4.top;
        rect3.bottom = rect4.bottom;
        int i3 = (i2 * iArr2[0]) / iArr2[1];
        int iWidth2 = rect3.width() - i3;
        Rect rect5 = this.d;
        int i4 = iWidth2 / 2;
        rect5.left += i4;
        rect5.right -= i4;
        j(i3, 0);
    }

    private void c(int i) {
        if (Math.abs(i) > 45) {
            this.f521b.setColor(-7829368);
        } else {
            l();
        }
        if (this.v) {
            int iAbs = (int) (255.0f - (Math.abs(i - this.p) * 5.0f));
            if (iAbs < 0) {
                iAbs = 0;
            }
            this.f521b.setAlpha(iAbs);
        }
    }

    private void d(boolean z) {
        int[] iArr = this.z;
        if (iArr[0] <= 0 || iArr[1] <= 0) {
            return;
        }
        int iHeight = this.d.height();
        int[] iArr2 = this.z;
        int i = (iHeight * iArr2[0]) / iArr2[1];
        int i2 = this.f;
        if (i <= i2) {
            if (z) {
                Rect rect = this.d;
                rect.right = rect.left + i;
            } else {
                Rect rect2 = this.d;
                rect2.left = rect2.right - i;
            }
            j(i, 0);
            return;
        }
        Rect rect3 = this.d;
        Rect rect4 = this.e;
        rect3.left = rect4.left;
        rect3.right = rect4.right;
        int i3 = (i2 * iArr2[1]) / iArr2[0];
        int iHeight2 = rect3.height() - i3;
        Rect rect5 = this.d;
        int i4 = iHeight2 / 2;
        rect5.top += i4;
        rect5.bottom -= i4;
        j(0, i3);
    }

    private void e(int i) {
        Rect rect = this.d;
        int i2 = rect.bottom + i;
        int i3 = this.e.bottom;
        if (i2 > i3) {
            i2 = i3;
        } else {
            int i4 = rect.top;
            int i5 = f520a;
            if (i2 < i4 + i5) {
                i2 = i4 + i5;
            }
        }
        rect.bottom = i2;
    }

    private void f(int i, int i2) {
        this.d.offset(i, i2);
        Rect rect = this.d;
        int i3 = rect.left;
        Rect rect2 = this.e;
        int i4 = rect2.left;
        if (i3 < i4 || rect.right > rect2.right) {
            int i5 = i3 < i4 ? i4 - i3 : rect2.right - rect.right;
            rect.left = i3 + i5;
            rect.right += i5;
        }
        int i6 = rect.top;
        int i7 = rect2.top;
        if (i6 < i7 || rect.bottom > rect2.bottom) {
            int i8 = i6 < i7 ? i7 - i6 : rect2.bottom - rect.bottom;
            rect.top = i6 + i8;
            rect.bottom += i8;
        }
    }

    private void g(int i) {
        Rect rect = this.d;
        int i2 = rect.left + i;
        int i3 = this.e.left;
        if (i2 < i3) {
            i2 = i3;
        } else {
            int i4 = rect.right;
            int i5 = f520a;
            if (i2 > i4 - i5) {
                i2 = i4 - i5;
            }
        }
        rect.left = i2;
    }

    private void h(int i) {
        Rect rect = this.d;
        int i2 = rect.right + i;
        int i3 = this.e.right;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = rect.left;
        int i5 = f520a;
        if (i2 < i4 + i5) {
            i2 = i4 + i5;
        }
        rect.right = i2;
    }

    private void i(int i) {
        Rect rect = this.d;
        int i2 = rect.top + i;
        int i3 = this.e.top;
        if (i2 < i3) {
            i2 = i3;
        } else {
            int i4 = rect.bottom;
            int i5 = f520a;
            if (i2 > i4 - i5) {
                i2 = i4 - i5;
            }
        }
        rect.top = i2;
    }

    private void j(int i, int i2) {
        if (i2 == 0) {
            i2 = this.d.height();
        }
        Rect rect = this.d;
        int i3 = rect.top;
        Rect rect2 = this.e;
        int i4 = rect2.top;
        if (i3 < i4) {
            rect.top = i4;
            rect.bottom = i4 + i2;
        } else {
            int i5 = rect.bottom;
            int i6 = rect2.bottom;
            if (i5 > i6) {
                rect.bottom = i6;
                rect.top = rect2.bottom - i2;
            }
        }
        if (i == 0) {
            i = rect.width();
        }
        Rect rect3 = this.d;
        int i7 = rect3.left;
        Rect rect4 = this.e;
        int i8 = rect4.left;
        if (i7 < i8) {
            rect3.left = i8;
            rect3.right = i8 + i;
            return;
        }
        int i9 = rect3.right;
        int i10 = rect4.right;
        if (i9 > i10) {
            rect3.right = i10;
            rect3.left = rect4.right - i;
        }
    }

    private void l() {
        Paint paint;
        int i;
        if (!this.x || this.p == 0.0f) {
            paint = this.f521b;
            i = this.w;
        } else {
            paint = this.f521b;
            i = -1;
        }
        paint.setColor(i);
    }

    private void m(Canvas canvas) {
        this.f521b.setStyle(Paint.Style.STROKE);
        this.f521b.setStrokeWidth(3.0f);
        canvas.drawRect(this.d, this.f521b);
        if (this.B) {
            int iHeight = this.d.height() / 3;
            Rect rect = this.d;
            int i = rect.top;
            int iWidth = rect.width() / 3;
            int i2 = this.d.left;
            this.f521b.setStrokeWidth(1.0f);
            for (int i3 = 0; i3 < 2; i3++) {
                i += iHeight;
                Rect rect2 = this.d;
                float f = i;
                canvas.drawLine(rect2.left, f, rect2.right, f, this.f521b);
                i2 += iWidth;
                float f2 = i2;
                Rect rect3 = this.d;
                canvas.drawLine(f2, rect3.top, f2, rect3.bottom, this.f521b);
            }
        }
    }

    private void n(Canvas canvas) {
        this.f521b.setStyle(Paint.Style.FILL);
        if (!this.C) {
            Rect rect = this.d;
            canvas.drawRect(rect.left, rect.top, r1 + this.t, r0 + this.s, this.f521b);
            Rect rect2 = this.d;
            canvas.drawRect(rect2.left, rect2.top, r1 + this.s, r0 + this.t, this.f521b);
            Rect rect3 = this.d;
            int i = rect3.right;
            canvas.drawRect(i - this.s, rect3.top, i, r0 + this.t, this.f521b);
            Rect rect4 = this.d;
            int i2 = rect4.right;
            canvas.drawRect(i2 - this.t, rect4.top, i2, r0 + this.s, this.f521b);
            Rect rect5 = this.d;
            canvas.drawRect(rect5.left, r0 - this.s, r1 + this.t, rect5.bottom, this.f521b);
            Rect rect6 = this.d;
            canvas.drawRect(rect6.left, r0 - this.t, r1 + this.s, rect6.bottom, this.f521b);
            Rect rect7 = this.d;
            int i3 = rect7.right;
            canvas.drawRect(i3 - this.s, r0 - this.t, i3, rect7.bottom, this.f521b);
            Rect rect8 = this.d;
            int i4 = rect8.right;
            canvas.drawRect(i4 - this.t, r0 - this.s, i4, rect8.bottom, this.f521b);
            return;
        }
        Rect rect9 = this.d;
        int i5 = rect9.left;
        int i6 = this.t;
        int i7 = rect9.top;
        canvas.drawRect(i5 - i6, i7 - i6, i5, (i7 - i6) + this.s, this.f521b);
        Rect rect10 = this.d;
        int i8 = rect10.left;
        int i9 = this.t;
        canvas.drawRect(i8 - i9, r0 - i9, (i8 - i9) + this.s, rect10.top, this.f521b);
        Rect rect11 = this.d;
        canvas.drawRect((rect11.right + this.t) - this.s, r0 - r2, r1 + r2, rect11.top, this.f521b);
        Rect rect12 = this.d;
        int i10 = rect12.right;
        int i11 = rect12.top;
        int i12 = this.t;
        canvas.drawRect(i10, i11 - i12, i10 + i12, (i11 - i12) + this.s, this.f521b);
        Rect rect13 = this.d;
        int i13 = rect13.left;
        int i14 = this.t;
        int i15 = rect13.bottom;
        canvas.drawRect(i13 - i14, (i15 + i14) - this.s, i13, i15 + i14, this.f521b);
        Rect rect14 = this.d;
        int i16 = rect14.left;
        int i17 = this.t;
        canvas.drawRect(i16 - i17, rect14.bottom, (i16 - i17) + this.s, r0 + i17, this.f521b);
        Rect rect15 = this.d;
        canvas.drawRect((rect15.right + this.t) - this.s, rect15.bottom, r1 + r2, r0 + r2, this.f521b);
        Rect rect16 = this.d;
        int i18 = rect16.right;
        int i19 = rect16.bottom;
        int i20 = this.t;
        canvas.drawRect(i18, (i19 + i20) - this.s, i18 + i20, i19 + i20, this.f521b);
    }

    private void o(Canvas canvas) {
        canvas.save();
        canvas.clipRect(0, this.d.bottom + 5, this.q.getRight(), this.q.getHeight());
        this.f521b.setPathEffect(null);
        this.f521b.setStyle(Paint.Style.FILL);
        l();
        if (!this.u) {
            float f = this.j.x;
            float f2 = this.d.bottom - 240;
            canvas.rotate(this.p, f, f2);
            Rect rect = new Rect();
            for (int i = 0; i < 180; i++) {
                int i2 = i * 2;
                float[] fArrT = t(f, f2, 320.0f, i2);
                if (i2 % 10 == 0) {
                    canvas.drawCircle(fArrT[0], fArrT[1], 4.0f, this.f521b);
                    float[] fArrT2 = t(f, f2, 295.0f, i2);
                    String strValueOf = String.valueOf(180 - i2);
                    this.f521b.getTextBounds(strValueOf, 0, strValueOf.length(), rect);
                    canvas.drawText(strValueOf, fArrT2[0] - rect.centerX(), fArrT2[1] - rect.centerY(), this.f521b);
                } else {
                    canvas.drawPoint(fArrT[0], fArrT[1], this.f521b);
                }
            }
            canvas.restore();
            this.D.reset();
            float f3 = f2 + 320.0f;
            this.D.moveTo(f, 25.0f + f3);
            float f4 = f3 + 50.0f;
            this.D.lineTo(f - 15.0f, f4);
            this.D.lineTo(f + 15.0f, f4);
            this.D.close();
            canvas.drawPath(this.D, this.f521b);
            return;
        }
        canvas.translate((-this.p) * 10.0f, 0.0f);
        float height = this.q != null ? r1.getHeight() - 100 : 0.0f;
        float f5 = height < ((float) (this.d.bottom + 60)) ? r1 + 60 : height;
        float f6 = f5 + 30.0f;
        Rect rect2 = new Rect();
        for (int i3 = -100; i3 <= 100; i3 += 10) {
            float f7 = this.j.x + (i3 * 10);
            c(i3);
            canvas.drawLine(f7, f5, f7, f6, this.f521b);
            String strValueOf2 = String.valueOf(i3);
            this.f521b.getTextBounds(strValueOf2, 0, strValueOf2.length(), rect2);
            canvas.drawText(strValueOf2, f7 - rect2.centerX(), (f6 - rect2.centerY()) + rect2.height() + 10.0f, this.f521b);
            if (i3 < 100) {
                int i4 = 0;
                float f8 = f7;
                while (i4 < 9) {
                    float f9 = f8 + 10.0f;
                    c(i3 + i4 + 1);
                    canvas.drawLine(f9, f5, f9, this.v ? f6 : f5 + 20.0f, this.f521b);
                    i4++;
                    f8 = f9;
                }
            }
        }
        canvas.restore();
        this.f521b.setAlpha(255);
        l();
        this.D.reset();
        this.D.moveTo(this.j.x, f5 - 10.0f);
        float f10 = f5 - 20.0f;
        this.D.lineTo(this.j.x + 8, f10);
        this.D.lineTo(this.j.x - 8, f10);
        this.D.close();
        canvas.drawPath(this.D, this.f521b);
        if (this.v) {
            int i5 = this.j.x;
            canvas.drawRect(i5 - 3, f5, i5 + 3, f6 + 10.0f, this.f521b);
        }
    }

    private int r(int i, int i2) {
        if (!this.d.contains(i, i2)) {
            return 0;
        }
        Rect rect = this.d;
        int i3 = rect.left;
        if (i >= i3) {
            int i4 = this.s;
            if (i <= i3 + i4) {
                int i5 = rect.top;
                if (i2 >= i5 && i2 <= i5 + i4) {
                    return 1;
                }
                int i6 = rect.bottom;
                return (i2 < i6 - i4 || i2 > i6) ? 5 : 3;
            }
        }
        int i7 = rect.top;
        if (i2 >= i7) {
            int i8 = this.s;
            if (i2 <= i7 + i8) {
                if (i >= i3 && i <= i3 + i8) {
                    return 1;
                }
                int i9 = rect.right;
                return (i < i9 - i8 || i > i9) ? 6 : 2;
            }
        }
        int i10 = rect.right;
        int i11 = this.s;
        if (i >= i10 - i11 && i < i10) {
            if (i2 >= i7 && i2 <= i7 + i11) {
                return 2;
            }
            int i12 = rect.bottom;
            return (i2 < i12 - i11 || i2 >= i12) ? 7 : 4;
        }
        int i13 = rect.bottom;
        if (i2 < i13 - i11 || i2 > i13) {
            return 9;
        }
        if (i > i3 || i > i3 + i11) {
            return (i < i10 - i11 || i > i10) ? 8 : 4;
        }
        return 3;
    }

    private float[] t(float f, float f2, float f3, int i) {
        float[] fArr = new float[2];
        if (i == 360) {
            i = 0;
        }
        if (i < 90) {
            double radians = Math.toRadians(i);
            double d = f3;
            fArr[0] = (float) (((double) f) + (Math.sin(radians) * d));
            fArr[1] = (float) (((double) f2) - (d * Math.cos(radians)));
        } else if (i == 90) {
            fArr[0] = f + f3;
            fArr[1] = f2;
        } else if (i < 180) {
            double radians2 = Math.toRadians(180 - i);
            double d2 = f3;
            fArr[0] = (float) (((double) f) + (Math.sin(radians2) * d2));
            fArr[1] = (float) (((double) f2) + (d2 * Math.cos(radians2)));
        } else if (i == 180) {
            fArr[0] = f;
            fArr[1] = f2 + f3;
        } else if (i < 270) {
            double radians3 = Math.toRadians(270 - i);
            double d3 = f3;
            fArr[0] = (float) (((double) f) - (Math.cos(radians3) * d3));
            fArr[1] = (float) (((double) f2) + (d3 * Math.sin(radians3)));
        } else if (i == 270) {
            fArr[0] = f - f3;
            fArr[1] = f2;
        } else if (i < 360) {
            double radians4 = Math.toRadians(360 - i);
            double d4 = f3;
            fArr[0] = (float) (((double) f) - (Math.sin(radians4) * d4));
            fArr[1] = (float) (((double) f2) - (d4 * Math.cos(radians4)));
        }
        return fArr;
    }

    public void A(boolean z) {
        this.C = z;
    }

    public void B(boolean z) {
        this.v = z;
    }

    public void C(boolean z) {
        this.x = z;
    }

    public void D(boolean z) {
        this.B = z;
    }

    public void E(boolean z) {
        this.u = z;
    }

    public void F(int i) {
        this.w = i;
    }

    public void G(float f) {
        this.p = f;
    }

    public void H(int i, int i2, int i3, int i4) {
        float f = i;
        float width = this.q.getWidth() / f;
        float f2 = i2;
        float height = (this.q.getHeight() - this.q.getPaddingBottom()) / f2;
        if (width < 1.0f || height < 1.0f) {
            if (width >= height) {
                width = height;
            }
            i = (int) (f * width);
            i2 = (int) (f2 * width);
        }
        int i5 = i >> 1;
        int i6 = i2 >> 1;
        int i7 = i3 - i5;
        if (i7 < 0) {
            i7 = 0;
        }
        int i8 = i4 - i6;
        int i9 = i8 >= 0 ? i8 : 0;
        int width2 = i5 + i3;
        if (width2 > this.q.getWidth()) {
            width2 = this.q.getWidth();
        }
        int height2 = i6 + i4;
        if (height2 > this.q.getHeight()) {
            height2 = this.q.getHeight();
        }
        this.e.set(i7, i9, width2, height2);
        this.f = i;
        this.g = i2;
        if (this.h) {
            this.i = Math.toDegrees(Math.atan(((double) i) / ((double) i2)));
            this.j = new Point(i3, i4);
            int i10 = this.f;
            int i11 = this.g;
            this.k = Math.sqrt((i10 * i10) + (i11 * i11));
        }
        Rect rect = this.e;
        int i12 = rect.left;
        int i13 = rect.top;
        int i14 = rect.right;
        int i15 = rect.bottom;
        Rect rect2 = this.d;
        if (rect2 == null) {
            this.d = new Rect(i12, i13, i14, i15);
        } else {
            rect2.set(i12, i13, i14, i15);
        }
        y();
    }

    public void k(Rect rect, int i, int i2, int i3, int i4) {
        this.e = rect;
        this.f = rect.width();
        int iHeight = rect.height();
        this.g = iHeight;
        if (this.h) {
            this.i = Math.toDegrees(Math.atan(((double) this.f) / ((double) iHeight)));
            this.j = new Point(i3, i4);
            int i5 = this.f;
            int i6 = this.g;
            this.k = Math.sqrt((i5 * i5) + (i6 * i6));
        } else {
            int i7 = this.s * 2;
            int i8 = this.f;
            if (i7 > i8) {
                this.s = i8 / 2;
            } else if (i7 > iHeight) {
                this.s = iHeight / 2;
            }
        }
        int i9 = f520a;
        int i10 = this.f;
        if (i9 > i10 || i9 > (i10 = this.g)) {
            f520a = i10;
        }
        int i11 = rect.left;
        int i12 = rect.top;
        int i13 = rect.right;
        int i14 = rect.bottom;
        Rect rect2 = this.d;
        if (rect2 == null) {
            this.d = new Rect(i11, i12, i13, i14);
        } else {
            rect2.set(i11, i12, i13, i14);
        }
        int[] iArr = this.z;
        iArr[0] = i;
        iArr[1] = i2;
        this.A = i + ":" + i2;
        int[] iArr2 = this.z;
        if (iArr2[0] > 0 && iArr2[1] > 0) {
            if (iArr2[0] == iArr2[1]) {
                int i15 = this.f;
                int i16 = this.g;
                int i17 = i15 > i16 ? i16 / 2 : i15 / 2;
                this.d.set(i3 - i17, i4 - i17, i3 + i17, i4 + i17);
            } else {
                a(this.d, i3, i4);
            }
        }
        y();
    }

    public void p(Canvas canvas) {
        if (!this.l) {
            canvas.clipPath(this.f522c, Region.Op.DIFFERENCE);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            return;
        }
        if (this.h) {
            o(canvas);
            if (this.p == 0.0f) {
                this.f521b.setColor(-1);
                this.f521b.setStrokeWidth(3.0f);
                this.f521b.setStyle(Paint.Style.STROKE);
                this.f521b.setPathEffect(null);
                canvas.drawPath(this.f522c, this.f521b);
                return;
            }
        }
        if (this.x) {
            canvas.save();
            canvas.clipPath(this.f522c, Region.Op.DIFFERENCE);
            canvas.drawColor(-1442840576);
            canvas.restore();
        }
        this.f521b.setColor(-1);
        m(canvas);
        this.f521b.setPathEffect(null);
        canvas.drawPath(this.f522c, this.f521b);
        if (this.h) {
            o(canvas);
            return;
        }
        n(canvas);
        int[] iArr = this.z;
        if (iArr[0] <= 0 || iArr[1] <= 0) {
            return;
        }
        Rect rect = new Rect();
        Paint paint = this.f521b;
        String str = this.A;
        paint.getTextBounds(str, 0, str.length(), rect);
        canvas.drawText(this.A, this.d.centerX() - rect.centerX(), this.d.centerY() - rect.centerY(), this.f521b);
    }

    public Rect q() {
        return this.d;
    }

    public Path s() {
        return this.f522c;
    }

    public void u(double d) {
        float fW = w(d);
        if (fW != 1.0f) {
            this.q.f();
            Matrix stateMatrix = this.q.getStateMatrix();
            Point point = this.j;
            stateMatrix.postScale(fW, fW, point.x, point.y);
        }
    }

    public float v() {
        return this.p;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020 A[PHI: r0
  0x0020: PHI (r0v4 double) = (r0v2 double), (r0v3 double) binds: [B:9:0x001e, B:15:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float w(double r8) {
        /*
            r7 = this;
            r0 = 0
            int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r0 >= 0) goto L7
            double r8 = -r8
        L7:
            r0 = 4645040803167600640(0x4076800000000000, double:360.0)
            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r3 = 4636033603912859648(0x4056800000000000, double:90.0)
            if (r2 <= 0) goto L17
            double r8 = r8 - r0
            goto L34
        L17:
            r5 = 4643457506423603200(0x4070e00000000000, double:270.0)
            int r2 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r2 <= 0) goto L23
        L20:
            double r8 = r0 - r8
            goto L34
        L23:
            r0 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r2 <= 0) goto L2f
            double r8 = r5 - r8
            goto L34
        L2f:
            int r2 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r2 <= 0) goto L34
            goto L20
        L34:
            int r0 = r7.f
            int r1 = r7.g
            if (r0 > r1) goto L43
            double r0 = r7.i
            double r3 = r3 - r0
            double r3 = r3 - r8
            double r8 = java.lang.Math.abs(r3)
            goto L4a
        L43:
            double r0 = r7.i
            double r0 = r0 - r8
            double r8 = java.lang.Math.abs(r0)
        L4a:
            double r8 = java.lang.Math.toRadians(r8)
            int r0 = r7.f
            int r1 = r7.g
            if (r0 > r1) goto L6a
            double r0 = r7.k
            double r8 = java.lang.Math.cos(r8)
            double r8 = java.lang.Math.abs(r8)
            double r0 = r0 * r8
            double r8 = r7.i
            double r8 = java.lang.Math.toRadians(r8)
            double r8 = java.lang.Math.sin(r8)
            goto L7f
        L6a:
            double r0 = r7.k
            double r8 = java.lang.Math.cos(r8)
            double r8 = java.lang.Math.abs(r8)
            double r0 = r0 * r8
            double r8 = r7.i
            double r8 = java.lang.Math.toRadians(r8)
            double r8 = java.lang.Math.cos(r8)
        L7f:
            double r8 = java.lang.Math.abs(r8)
            double r0 = r0 / r8
            double r8 = r7.k
            double r0 = r0 / r8
            float r8 = (float) r0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.j.b.w(double):float");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void x(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            if (this.h) {
                c cVar = this.r;
                float f = x;
                float f2 = y;
                this.o = cVar.a(f, f2, cVar.b(f, f2, this.j), this.j);
                return;
            }
            int iR = r(x, y);
            this.m = iR;
            if (iR > 0) {
                this.n.set(x, y);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        if (this.h) {
            c cVar2 = this.r;
            float f3 = x;
            float f4 = y;
            float fA = cVar2.a(f3, f4, cVar2.b(f3, f4, this.j), this.j);
            float fC = this.p + this.r.c(fA, this.o);
            if (fC > 45.0f) {
                fC = 45.0f;
            } else if (fC < -45.0f) {
                fC = -45.0f;
            }
            if (Math.abs(fC) > 45.0f) {
                return;
            }
            this.p = fC;
            u(fC);
            Matrix stateMatrix = this.q.getStateMatrix();
            float f5 = this.p;
            Point point = this.j;
            stateMatrix.postRotate(f5, point.x, point.y);
            this.o = fA;
        } else {
            int i = this.m;
            if (i <= 0) {
                return;
            }
            Point point2 = this.n;
            int i2 = x - point2.x;
            int i3 = y - point2.y;
            switch (i) {
                case 1:
                    g(i2);
                    i(i3);
                    d(false);
                    break;
                case 2:
                    h(i2);
                    i(i3);
                    b(false);
                    break;
                case 3:
                    g(i2);
                    e(i3);
                    b(true);
                    break;
                case 4:
                    h(i2);
                    e(i3);
                    d(true);
                    break;
                case 5:
                    g(i2);
                    b(false);
                    break;
                case 6:
                    i(i3);
                    d(false);
                    break;
                case 7:
                    h(i2);
                    b(true);
                    break;
                case 8:
                    e(i3);
                    d(true);
                    break;
                case 9:
                    f(i2, i3);
                    break;
            }
            y();
            this.n.set(x, y);
        }
        this.q.invalidate();
    }

    protected void y() {
        this.f522c.reset();
        Path path = this.f522c;
        Rect rect = this.d;
        path.addRect(rect.left, rect.top, rect.right, rect.bottom, Path.Direction.CW);
    }

    public void z(boolean z) {
        this.l = z;
    }
}
