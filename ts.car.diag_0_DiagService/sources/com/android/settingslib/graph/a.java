package com.android.settingslib.graph;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class a extends Drawable {
    private final float[] A;
    private final Path B;
    private final Rect C;
    private final RectF D;
    private final RectF E;
    private final RectF F;
    private final RectF G;
    private final Path H;
    private final Path I;
    private final Path J;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final Paint f1359a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final Paint f1360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final Paint f1361c;
    protected final Paint d;
    protected final Paint e;
    protected final Paint f;
    protected final Paint g;
    protected float h;
    private int i;
    private boolean j;
    private boolean k;
    protected boolean l;
    private boolean m;
    private final int[] n;
    private final int o;
    private final int p;
    private float q;
    private float r;
    private int s;
    private int t;
    private int u;
    private String v;
    private final int w;
    private int x;
    private final float[] y;
    private final Path z;

    private int c(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.n;
            if (i2 >= iArr.length) {
                return i3;
            }
            int i4 = iArr[i2];
            int i5 = iArr[i2 + 1];
            if (i <= i4) {
                return i2 == iArr.length + (-2) ? this.s : i5;
            }
            i2 += 2;
            i3 = i5;
        }
    }

    private void e() {
        Rect bounds = getBounds();
        int i = bounds.bottom;
        Rect rect = this.C;
        int i2 = (i - rect.bottom) - (bounds.top + rect.top);
        this.t = i2;
        this.u = (bounds.right - rect.right) - (bounds.left + rect.left);
        this.f1361c.setTextSize(i2 * 0.75f);
        this.r = -this.f1361c.getFontMetrics().ascent;
    }

    protected int a(int i) {
        return (this.j || (this.k && this.l)) ? this.x : c(i);
    }

    protected float b() {
        return 0.58f;
    }

    protected float d() {
        return 0.05882353f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float fHeight;
        float[] fArr;
        float f;
        float f2;
        float[] fArr2;
        int i = this.i;
        Rect bounds = getBounds();
        if (i == -1) {
            return;
        }
        float f3 = i / 100.0f;
        int i2 = this.t;
        int iB = (int) (b() * this.t);
        int i3 = (this.u - iB) / 2;
        float f4 = i2;
        int iRound = Math.round(this.h * f4);
        Rect rect = this.C;
        int i4 = rect.left + bounds.left;
        float f5 = i4;
        float f6 = (bounds.bottom - rect.bottom) - i2;
        this.D.set(f5, f6, i4 + iB, i2 + r3);
        this.D.offset(i3, 0.0f);
        RectF rectF = this.E;
        float f7 = iB * 0.28f;
        float fRound = this.D.left + Math.round(f7);
        RectF rectF2 = this.D;
        float f8 = iRound;
        rectF.set(fRound, rectF2.top, rectF2.right - Math.round(f7), this.D.top + f8);
        this.D.top += f8;
        this.f1360b.setColor(a(i));
        if (i >= 96) {
            f3 = 1.0f;
        } else if (i <= this.w) {
            f3 = 0.0f;
        }
        if (f3 == 1.0f) {
            fHeight = this.E.top;
        } else {
            RectF rectF3 = this.D;
            fHeight = (rectF3.height() * (1.0f - f3)) + rectF3.top;
        }
        this.H.reset();
        this.I.reset();
        float fD = d() * (this.D.height() + f8);
        this.H.setFillType(Path.FillType.WINDING);
        this.H.addRoundRect(this.D, fD, fD, Path.Direction.CW);
        this.H.addRect(this.E, Path.Direction.CW);
        this.I.addRoundRect(this.D, fD, fD, Path.Direction.CW);
        Path path = new Path();
        path.addRect(this.E, Path.Direction.CW);
        this.I.op(path, Path.Op.XOR);
        if (this.j) {
            RectF rectF4 = this.D;
            float fWidth = rectF4.left + (rectF4.width() / 4.0f) + 1.0f;
            RectF rectF5 = this.D;
            float fHeight2 = rectF5.top + (rectF5.height() / 6.0f);
            RectF rectF6 = this.D;
            float fWidth2 = (rectF6.right - (rectF6.width() / 4.0f)) + 1.0f;
            RectF rectF7 = this.D;
            float fHeight3 = rectF7.bottom - (rectF7.height() / 10.0f);
            RectF rectF8 = this.F;
            if (rectF8.left != fWidth || rectF8.top != fHeight2 || rectF8.right != fWidth2 || rectF8.bottom != fHeight3) {
                this.F.set(fWidth, fHeight2, fWidth2, fHeight3);
                this.z.reset();
                Path path2 = this.z;
                RectF rectF9 = this.F;
                float fWidth3 = rectF9.left + (this.y[0] * rectF9.width());
                RectF rectF10 = this.F;
                path2.moveTo(fWidth3, rectF10.top + (this.y[1] * rectF10.height()));
                int i5 = 2;
                while (true) {
                    fArr2 = this.y;
                    if (i5 >= fArr2.length) {
                        break;
                    }
                    Path path3 = this.z;
                    RectF rectF11 = this.F;
                    float fWidth4 = rectF11.left + (fArr2[i5] * rectF11.width());
                    RectF rectF12 = this.F;
                    path3.lineTo(fWidth4, rectF12.top + (this.y[i5 + 1] * rectF12.height()));
                    i5 += 2;
                }
                Path path4 = this.z;
                RectF rectF13 = this.F;
                float fWidth5 = rectF13.left + (fArr2[0] * rectF13.width());
                RectF rectF14 = this.F;
                path4.lineTo(fWidth5, rectF14.top + (this.y[1] * rectF14.height()));
            }
            RectF rectF15 = this.F;
            float f9 = rectF15.bottom;
            if (Math.min(Math.max((f9 - fHeight) / (f9 - rectF15.top), 0.0f), 1.0f) <= 0.3f) {
                canvas.drawPath(this.z, this.e);
            } else {
                this.H.op(this.z, Path.Op.DIFFERENCE);
            }
        } else if (this.k) {
            float fWidth6 = (this.D.width() * 2.0f) / 3.0f;
            RectF rectF16 = this.D;
            float fWidth7 = rectF16.left + ((rectF16.width() - fWidth6) / 2.0f);
            RectF rectF17 = this.D;
            float fHeight4 = rectF17.top + ((rectF17.height() - fWidth6) / 2.0f);
            RectF rectF18 = this.D;
            float fWidth8 = rectF18.right - ((rectF18.width() - fWidth6) / 2.0f);
            RectF rectF19 = this.D;
            float fHeight5 = rectF19.bottom - ((rectF19.height() - fWidth6) / 2.0f);
            RectF rectF20 = this.G;
            if (rectF20.left != fWidth7 || rectF20.top != fHeight4 || rectF20.right != fWidth8 || rectF20.bottom != fHeight5) {
                this.G.set(fWidth7, fHeight4, fWidth8, fHeight5);
                this.B.reset();
                Path path5 = this.B;
                RectF rectF21 = this.G;
                float fWidth9 = rectF21.left + (this.A[0] * rectF21.width());
                RectF rectF22 = this.G;
                path5.moveTo(fWidth9, rectF22.top + (this.A[1] * rectF22.height()));
                int i6 = 2;
                while (true) {
                    fArr = this.A;
                    if (i6 >= fArr.length) {
                        break;
                    }
                    Path path6 = this.B;
                    RectF rectF23 = this.G;
                    float fWidth10 = rectF23.left + (fArr[i6] * rectF23.width());
                    RectF rectF24 = this.G;
                    path6.lineTo(fWidth10, rectF24.top + (this.A[i6 + 1] * rectF24.height()));
                    i6 += 2;
                }
                Path path7 = this.B;
                RectF rectF25 = this.G;
                float fWidth11 = rectF25.left + (fArr[0] * rectF25.width());
                RectF rectF26 = this.G;
                path7.lineTo(fWidth11, rectF26.top + (this.A[1] * rectF26.height()));
            }
            this.H.op(this.B, Path.Op.DIFFERENCE);
            if (this.l) {
                canvas.drawPath(this.B, this.f);
            }
        }
        String strValueOf = null;
        if (this.j || this.k || i <= this.w || !this.m) {
            f = 0.0f;
            f2 = 0.0f;
        } else {
            this.d.setColor(c(i));
            this.d.setTextSize(f4 * (this.i == 100 ? 0.38f : 0.5f));
            this.q = -this.d.getFontMetrics().ascent;
            strValueOf = String.valueOf(i);
            f = (this.u * 0.5f) + f5;
            f2 = ((this.t + this.q) * 0.47f) + f6;
            z = fHeight > f2;
            if (!z) {
                this.J.reset();
                this.d.getTextPath(strValueOf, 0, strValueOf.length(), f, f2, this.J);
                this.H.op(this.J, Path.Op.DIFFERENCE);
            }
        }
        canvas.drawPath(this.H, this.f1359a);
        this.D.top = fHeight;
        canvas.save();
        canvas.clipRect(this.D);
        canvas.drawPath(this.H, this.f1360b);
        canvas.restore();
        if (!this.j && !this.k) {
            if (i <= this.w) {
                canvas.drawText(this.v, (this.u * 0.5f) + f5, ((this.t + this.r) * 0.48f) + f6, this.f1361c);
            } else if (z) {
                canvas.drawText(strValueOf, f, f2, this.d);
            }
        }
        if (!this.j && this.k && this.l) {
            canvas.drawPath(this.I, this.g);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.p;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.o;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.C;
        if (rect2.left == 0 && rect2.top == 0 && rect2.right == 0 && rect2.bottom == 0) {
            return super.getPadding(rect);
        }
        rect.set(this.C);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        e();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1359a.setColorFilter(colorFilter);
        this.f1360b.setColorFilter(colorFilter);
        this.f1361c.setColorFilter(colorFilter);
        this.e.setColorFilter(colorFilter);
        this.f.setColorFilter(colorFilter);
    }
}
