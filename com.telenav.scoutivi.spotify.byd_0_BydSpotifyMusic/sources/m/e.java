package m;

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
class e extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f8013a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f8014b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RectF f8015c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f8016d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f8017e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f8018f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f8019g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ColorStateList f8020h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private PorterDuffColorFilter f8021i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ColorStateList f8022j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private PorterDuff.Mode f8023k;

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
        this.f8020h = colorStateList;
        this.f8014b.setColor(colorStateList.getColorForState(getState(), this.f8020h.getDefaultColor()));
    }

    private void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f8015c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f8016d.set(rect);
        if (this.f8018f) {
            this.f8016d.inset((int) Math.ceil(f.a(this.f8017e, this.f8013a, this.f8019g)), (int) Math.ceil(f.b(this.f8017e, this.f8013a, this.f8019g)));
            this.f8015c.set(this.f8016d);
        }
    }

    public ColorStateList b() {
        return this.f8020h;
    }

    float c() {
        return this.f8017e;
    }

    public float d() {
        return this.f8013a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z6;
        Paint paint = this.f8014b;
        if (this.f8021i == null || paint.getColorFilter() != null) {
            z6 = false;
        } else {
            paint.setColorFilter(this.f8021i);
            z6 = true;
        }
        RectF rectF = this.f8015c;
        float f7 = this.f8013a;
        canvas.drawRoundRect(rectF, f7, f7, paint);
        if (z6) {
            paint.setColorFilter(null);
        }
    }

    public void f(ColorStateList colorStateList) {
        e(colorStateList);
        invalidateSelf();
    }

    void g(float f7, boolean z6, boolean z7) {
        if (f7 == this.f8017e && this.f8018f == z6 && this.f8019g == z7) {
            return;
        }
        this.f8017e = f7;
        this.f8018f = z6;
        this.f8019g = z7;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f8016d, this.f8013a);
    }

    void h(float f7) {
        if (f7 == this.f8013a) {
            return;
        }
        this.f8013a = f7;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f8022j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f8020h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f8020h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z6 = colorForState != this.f8014b.getColor();
        if (z6) {
            this.f8014b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f8022j;
        if (colorStateList2 == null || (mode = this.f8023k) == null) {
            return z6;
        }
        this.f8021i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        this.f8014b.setAlpha(i7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f8014b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f8022j = colorStateList;
        this.f8021i = a(colorStateList, this.f8023k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f8023k = mode;
        this.f8021i = a(this.f8022j, mode);
        invalidateSelf();
    }
}
