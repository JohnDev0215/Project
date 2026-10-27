package u;

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
    public float f8090a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RectF f8092c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f8093d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f8094e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f8097h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuffColorFilter f8098i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f8099j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8095f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8096g = true;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PorterDuff.Mode f8100k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f8091b = new Paint(5);

    public f(ColorStateList colorStateList, float f8) {
        this.f8090a = f8;
        e(colorStateList);
        this.f8092c = new RectF();
        this.f8093d = new Rect();
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public ColorStateList b() {
        return this.f8097h;
    }

    public float c() {
        return this.f8094e;
    }

    public float d() {
        return this.f8090a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z7;
        Paint paint = this.f8091b;
        if (this.f8098i == null || paint.getColorFilter() != null) {
            z7 = false;
        } else {
            paint.setColorFilter(this.f8098i);
            z7 = true;
        }
        RectF rectF = this.f8092c;
        float f8 = this.f8090a;
        canvas.drawRoundRect(rectF, f8, f8, paint);
        if (z7) {
            paint.setColorFilter(null);
        }
    }

    public final void e(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f8097h = colorStateList;
        this.f8091b.setColor(colorStateList.getColorForState(getState(), this.f8097h.getDefaultColor()));
    }

    public void f(ColorStateList colorStateList) {
        e(colorStateList);
        invalidateSelf();
    }

    public void g(float f8, boolean z7, boolean z8) {
        if (f8 == this.f8094e && this.f8095f == z7 && this.f8096g == z8) {
            return;
        }
        this.f8094e = f8;
        this.f8095f = z7;
        this.f8096g = z8;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f8093d, this.f8090a);
    }

    public void h(float f8) {
        if (f8 == this.f8090a) {
            return;
        }
        this.f8090a = f8;
        i(null);
        invalidateSelf();
    }

    public final void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f8092c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f8093d.set(rect);
        if (this.f8095f) {
            this.f8093d.inset((int) Math.ceil(g.c(this.f8094e, this.f8090a, this.f8096g)), (int) Math.ceil(g.d(this.f8094e, this.f8090a, this.f8096g)));
            this.f8092c.set(this.f8093d);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f8099j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f8097h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f8097h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z7 = colorForState != this.f8091b.getColor();
        if (z7) {
            this.f8091b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f8099j;
        if (colorStateList2 == null || (mode = this.f8100k) == null) {
            return z7;
        }
        this.f8098i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f8091b.setAlpha(i8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f8091b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f8099j = colorStateList;
        this.f8098i = a(colorStateList, this.f8100k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f8100k = mode;
        this.f8098i = a(this.f8099j, mode);
        invalidateSelf();
    }
}
