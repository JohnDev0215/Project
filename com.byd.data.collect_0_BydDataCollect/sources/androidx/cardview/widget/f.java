package androidx.cardview.widget;

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
class f extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f768a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RectF f770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f771d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f772e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ColorStateList f775h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private PorterDuffColorFilter f776i;
    private ColorStateList j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f773f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f774g = true;
    private PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f769b = new Paint(5);

    f(ColorStateList colorStateList, float f2) {
        this.f768a = f2;
        e(colorStateList);
        this.f770c = new RectF();
        this.f771d = new Rect();
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    private void e(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f775h = colorStateList;
        this.f769b.setColor(colorStateList.getColorForState(getState(), this.f775h.getDefaultColor()));
    }

    private void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f770c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f771d.set(rect);
        if (this.f773f) {
            this.f771d.inset((int) Math.ceil(g.c(this.f772e, this.f768a, this.f774g)), (int) Math.ceil(g.d(this.f772e, this.f768a, this.f774g)));
            this.f770c.set(this.f771d);
        }
    }

    public ColorStateList b() {
        return this.f775h;
    }

    float c() {
        return this.f772e;
    }

    public float d() {
        return this.f768a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.f769b;
        if (this.f776i == null || paint.getColorFilter() != null) {
            z = false;
        } else {
            paint.setColorFilter(this.f776i);
            z = true;
        }
        RectF rectF = this.f770c;
        float f2 = this.f768a;
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (z) {
            paint.setColorFilter(null);
        }
    }

    public void f(ColorStateList colorStateList) {
        e(colorStateList);
        invalidateSelf();
    }

    void g(float f2, boolean z, boolean z2) {
        if (f2 == this.f772e && this.f773f == z && this.f774g == z2) {
            return;
        }
        this.f772e = f2;
        this.f773f = z;
        this.f774g = z2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f771d, this.f768a);
    }

    void h(float f2) {
        if (f2 == this.f768a) {
            return;
        }
        this.f768a = f2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f775h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f775h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z = colorForState != this.f769b.getColor();
        if (z) {
            this.f769b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.j;
        if (colorStateList2 == null || (mode = this.k) == null) {
            return z;
        }
        this.f776i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f769b.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f769b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.f776i = a(colorStateList, this.k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.k = mode;
        this.f776i = a(this.j, mode);
        invalidateSelf();
    }
}
