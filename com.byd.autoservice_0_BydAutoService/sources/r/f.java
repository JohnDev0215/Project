package r;

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
    public float f4645a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RectF f4647c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f4648d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f4649e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f4652h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuffColorFilter f4653i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f4654j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4650f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4651g = true;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PorterDuff.Mode f4655k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f4646b = new Paint(5);

    public f(ColorStateList colorStateList, float f3) {
        this.f4645a = f3;
        e(colorStateList);
        this.f4647c = new RectF();
        this.f4648d = new Rect();
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public ColorStateList b() {
        return this.f4652h;
    }

    public float c() {
        return this.f4649e;
    }

    public float d() {
        return this.f4645a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z3;
        Paint paint = this.f4646b;
        if (this.f4653i == null || paint.getColorFilter() != null) {
            z3 = false;
        } else {
            paint.setColorFilter(this.f4653i);
            z3 = true;
        }
        RectF rectF = this.f4647c;
        float f3 = this.f4645a;
        canvas.drawRoundRect(rectF, f3, f3, paint);
        if (z3) {
            paint.setColorFilter(null);
        }
    }

    public final void e(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f4652h = colorStateList;
        this.f4646b.setColor(colorStateList.getColorForState(getState(), this.f4652h.getDefaultColor()));
    }

    public void f(ColorStateList colorStateList) {
        e(colorStateList);
        invalidateSelf();
    }

    public void g(float f3, boolean z3, boolean z4) {
        if (f3 == this.f4649e && this.f4650f == z3 && this.f4651g == z4) {
            return;
        }
        this.f4649e = f3;
        this.f4650f = z3;
        this.f4651g = z4;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f4648d, this.f4645a);
    }

    public void h(float f3) {
        if (f3 == this.f4645a) {
            return;
        }
        this.f4645a = f3;
        i(null);
        invalidateSelf();
    }

    public final void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f4647c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f4648d.set(rect);
        if (this.f4650f) {
            this.f4648d.inset((int) Math.ceil(g.c(this.f4649e, this.f4645a, this.f4651g)), (int) Math.ceil(g.d(this.f4649e, this.f4645a, this.f4651g)));
            this.f4647c.set(this.f4648d);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f4654j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f4652h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f4652h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z3 = colorForState != this.f4646b.getColor();
        if (z3) {
            this.f4646b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f4654j;
        if (colorStateList2 == null || (mode = this.f4655k) == null) {
            return z3;
        }
        this.f4653i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        this.f4646b.setAlpha(i3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4646b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f4654j = colorStateList;
        this.f4653i = a(colorStateList, this.f4655k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f4655k = mode;
        this.f4653i = a(this.f4654j, mode);
        invalidateSelf();
    }
}
