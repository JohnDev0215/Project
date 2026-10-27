package b.d.f;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final double f1919a = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f1920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1921c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f1923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f1924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RectF f1925g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1926h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Path f1927i;
    public float j;
    public float k;
    public float l;
    public ColorStateList m;
    public final int o;
    public final int p;
    public boolean n = true;
    public boolean q = true;
    public boolean r = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f1922d = new Paint(5);

    /* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
    public interface a {
        void a(Canvas canvas, RectF rectF, float f2, Paint paint);
    }

    public g(Resources resources, ColorStateList colorStateList, float f2, float f3, float f4) {
        this.o = resources.getColor(b.d.b.cardview_shadow_start_color);
        this.p = resources.getColor(b.d.b.cardview_shadow_end_color);
        this.f1921c = resources.getDimensionPixelSize(b.d.c.cardview_compat_inset_shadow);
        n(colorStateList);
        Paint paint = new Paint(5);
        this.f1923e = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f1926h = (int) (f2 + 0.5f);
        this.f1925g = new RectF();
        Paint paint2 = new Paint(this.f1923e);
        this.f1924f = paint2;
        paint2.setAntiAlias(false);
        s(f3, f4);
    }

    public static float c(float f2, float f3, boolean z) {
        return z ? (float) (((double) f2) + ((1.0d - f1919a) * ((double) f3))) : f2;
    }

    public static float d(float f2, float f3, boolean z) {
        return z ? (float) (((double) (f2 * 1.5f)) + ((1.0d - f1919a) * ((double) f3))) : f2 * 1.5f;
    }

    public final void a(Rect rect) {
        float f2 = this.j;
        float f3 = 1.5f * f2;
        this.f1925g.set(rect.left + f2, rect.top + f3, rect.right - f2, rect.bottom - f3);
        b();
    }

    public final void b() {
        float f2 = this.f1926h;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.k;
        rectF2.inset(-f3, -f3);
        Path path = this.f1927i;
        if (path == null) {
            this.f1927i = new Path();
        } else {
            path.reset();
        }
        this.f1927i.setFillType(Path.FillType.EVEN_ODD);
        this.f1927i.moveTo(-this.f1926h, 0.0f);
        this.f1927i.rLineTo(-this.k, 0.0f);
        this.f1927i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f1927i.arcTo(rectF, 270.0f, -90.0f, false);
        this.f1927i.close();
        float f4 = this.f1926h;
        float f5 = this.k;
        float f6 = f4 / (f4 + f5);
        Paint paint = this.f1923e;
        float f7 = f4 + f5;
        int i2 = this.o;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f7, new int[]{i2, i2, this.p}, new float[]{0.0f, f6, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.f1924f;
        float f8 = this.f1926h;
        float f9 = this.k;
        int i3 = this.o;
        paint2.setShader(new LinearGradient(0.0f, (-f8) + f9, 0.0f, (-f8) - f9, new int[]{i3, i3, this.p}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f1924f.setAntiAlias(false);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.n) {
            a(getBounds());
            this.n = false;
        }
        canvas.translate(0.0f, this.l / 2.0f);
        e(canvas);
        canvas.translate(0.0f, (-this.l) / 2.0f);
        f1920b.a(canvas, this.f1925g, this.f1926h, this.f1922d);
    }

    public final void e(Canvas canvas) {
        float f2 = this.f1926h;
        float f3 = (-f2) - this.k;
        float f4 = f2 + this.f1921c + (this.l / 2.0f);
        float f5 = f4 * 2.0f;
        boolean z = this.f1925g.width() - f5 > 0.0f;
        boolean z2 = this.f1925g.height() - f5 > 0.0f;
        int iSave = canvas.save();
        RectF rectF = this.f1925g;
        canvas.translate(rectF.left + f4, rectF.top + f4);
        canvas.drawPath(this.f1927i, this.f1923e);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f1925g.width() - f5, -this.f1926h, this.f1924f);
        }
        canvas.restoreToCount(iSave);
        int iSave2 = canvas.save();
        RectF rectF2 = this.f1925g;
        canvas.translate(rectF2.right - f4, rectF2.bottom - f4);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f1927i, this.f1923e);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f1925g.width() - f5, (-this.f1926h) + this.k, this.f1924f);
        }
        canvas.restoreToCount(iSave2);
        int iSave3 = canvas.save();
        RectF rectF3 = this.f1925g;
        canvas.translate(rectF3.left + f4, rectF3.bottom - f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f1927i, this.f1923e);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f1925g.height() - f5, -this.f1926h, this.f1924f);
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF4 = this.f1925g;
        canvas.translate(rectF4.right - f4, rectF4.top + f4);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f1927i, this.f1923e);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f1925g.height() - f5, -this.f1926h, this.f1924f);
        }
        canvas.restoreToCount(iSave4);
    }

    public ColorStateList f() {
        return this.m;
    }

    public float g() {
        return this.f1926h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(d(this.j, this.f1926h, this.q));
        int iCeil2 = (int) Math.ceil(c(this.j, this.f1926h, this.q));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    public void h(Rect rect) {
        getPadding(rect);
    }

    public float i() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.m;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    public float j() {
        float f2 = this.j;
        return (Math.max(f2, this.f1926h + this.f1921c + ((f2 * 1.5f) / 2.0f)) * 2.0f) + (((this.j * 1.5f) + this.f1921c) * 2.0f);
    }

    public float k() {
        float f2 = this.j;
        return (Math.max(f2, this.f1926h + this.f1921c + (f2 / 2.0f)) * 2.0f) + ((this.j + this.f1921c) * 2.0f);
    }

    public float l() {
        return this.l;
    }

    public void m(boolean z) {
        this.q = z;
        invalidateSelf();
    }

    public final void n(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.m = colorStateList;
        this.f1922d.setColor(colorStateList.getColorForState(getState(), this.m.getDefaultColor()));
    }

    public void o(ColorStateList colorStateList) {
        n(colorStateList);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.m;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.f1922d.getColor() == colorForState) {
            return false;
        }
        this.f1922d.setColor(colorForState);
        this.n = true;
        invalidateSelf();
        return true;
    }

    public void p(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f2 + ". Must be >= 0");
        }
        float f3 = (int) (f2 + 0.5f);
        if (this.f1926h == f3) {
            return;
        }
        this.f1926h = f3;
        this.n = true;
        invalidateSelf();
    }

    public void q(float f2) {
        s(this.l, f2);
    }

    public void r(float f2) {
        s(f2, this.j);
    }

    public final void s(float f2, float f3) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f2 + ". Must be >= 0");
        }
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f3 + ". Must be >= 0");
        }
        float fT = t(f2);
        float fT2 = t(f3);
        if (fT > fT2) {
            if (!this.r) {
                this.r = true;
            }
            fT = fT2;
        }
        if (this.l == fT && this.j == fT2) {
            return;
        }
        this.l = fT;
        this.j = fT2;
        this.k = (int) ((fT * 1.5f) + this.f1921c + 0.5f);
        this.n = true;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f1922d.setAlpha(i2);
        this.f1923e.setAlpha(i2);
        this.f1924f.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1922d.setColorFilter(colorFilter);
    }

    public final int t(float f2) {
        int i2 = (int) (f2 + 0.5f);
        return i2 % 2 == 1 ? i2 - 1 : i2;
    }
}
