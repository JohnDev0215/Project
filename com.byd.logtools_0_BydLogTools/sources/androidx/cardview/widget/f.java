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
    private float f733a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RectF f735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f737e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ColorStateList f740h;
    private PorterDuffColorFilter i;
    private ColorStateList j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f738f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f739g = true;
    private PorterDuff.Mode k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f734b = new Paint(5);

    f(ColorStateList colorStateList, float f2) {
        this.f733a = f2;
        e(colorStateList);
        this.f735c = new RectF();
        this.f736d = new Rect();
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
        this.f740h = colorStateList;
        this.f734b.setColor(colorStateList.getColorForState(getState(), this.f740h.getDefaultColor()));
    }

    private void i(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f735c.set(rect.left, rect.top, rect.right, rect.bottom);
        this.f736d.set(rect);
        if (this.f738f) {
            this.f736d.inset((int) Math.ceil(g.c(this.f737e, this.f733a, this.f739g)), (int) Math.ceil(g.d(this.f737e, this.f733a, this.f739g)));
            this.f735c.set(this.f736d);
        }
    }

    public ColorStateList b() {
        return this.f740h;
    }

    float c() {
        return this.f737e;
    }

    public float d() {
        return this.f733a;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        boolean z;
        Paint paint = this.f734b;
        if (this.i == null || paint.getColorFilter() != null) {
            z = false;
        } else {
            paint.setColorFilter(this.i);
            z = true;
        }
        RectF rectF = this.f735c;
        float f2 = this.f733a;
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
        if (f2 == this.f737e && this.f738f == z && this.f739g == z2) {
            return;
        }
        this.f737e = f2;
        this.f738f = z;
        this.f739g = z2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.f736d, this.f733a);
    }

    void h(float f2) {
        if (f2 == this.f733a) {
            return;
        }
        this.f733a = f2;
        i(null);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.j;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f740h) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        i(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f740h;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        boolean z = colorForState != this.f734b.getColor();
        if (z) {
            this.f734b.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.j;
        if (colorStateList2 == null || (mode = this.k) == null) {
            return z;
        }
        this.i = a(colorStateList2, mode);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f734b.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f734b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        this.i = a(colorStateList, this.k);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.k = mode;
        this.i = a(this.j, mode);
        invalidateSelf();
    }
}
