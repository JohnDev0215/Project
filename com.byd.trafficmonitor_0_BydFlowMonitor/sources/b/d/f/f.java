package b.d.f;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RoundRectDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f1910a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RectF f1912c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f1913d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1914e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f1917h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuffColorFilter f1918i;
    public ColorStateList j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1915f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1916g = true;
    public PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f1911b = new Paint(5);

    public f(ColorStateList colorStateList, float f2) {
        this.f1910a = f2;
        e(colorStateList);
        this.f1912c = new RectF();
        this.f1913d = new Rect();
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public ColorStateList b() {
        return this.f1917h;
    }

    public float c() {
        return this.f1914e;
    }

    public float d() {
        return this.f1910a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.f1911b;
        if (this.f1918i == null || paint.getColorFilter() != null) {
            z = false;
        } else {
            paint.setColorFilter(this.f1918i);
            z = true;
        }
        RectF rectF = this.f1912c;
        float f2 = this.f1910a;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (z) {
            paint.setColorFilter(null);
        }
    }

    public final void e(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f1917h = colorStateList;
        this.f1911b.setColor(colorStateList.getColorForState(getState(), this.f1917h.getDefaultColor()));
    }

    public void f(ColorStateList colorStateList) {
        e(colorStateList);
        invalidateSelf();
    }

    public void g(float f2, boolean z, boolean z2) {
        if (f2 == this.f1914e && this.f1915f == z && this.f1916g == z2) {
            return;
        }
        this.f1914e = f2;
        this.f1915f = z;
        this.f1916g = z2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f1913d, this.f1910a);
    }

    public void h(float f2) {
        if (f2 == this.f1910a) {
            return;
        }
        this.f1910a = f2;
        i(null);
        invalidateSelf();
    }

    public final void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f1912c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f1913d.set(rect);
        if (this.f1915f) {
            this.f1913d.inset((int) Math.ceil(g.c(this.f1914e, this.f1910a, this.f1916g)), (int) Math.ceil(g.d(this.f1914e, this.f1910a, this.f1916g)));
            this.f1912c.set(this.f1913d);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f1917h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f1917h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z = colorForState != this.f1911b.getColor();
        if (z) {
            this.f1911b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.j;
        if (colorStateList2 == null || (mode = this.k) == null) {
            return z;
        }
        this.f1918i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f1911b.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f1911b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.f1918i = a(colorStateList, this.k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.k = mode;
        this.f1918i = a(this.j, mode);
        invalidateSelf();
    }
}
