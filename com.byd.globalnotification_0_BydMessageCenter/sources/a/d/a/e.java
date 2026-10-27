package a.d.a;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class e extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f442b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f443c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f444d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ColorStateList f445e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PorterDuffColorFilter f446f;
    public ColorStateList g;
    public PorterDuff.Mode h;

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public final void b(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        int i = rect.left;
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f446f != null) {
            throw null;
        }
        float f2 = this.f441a;
        canvas.drawRoundRect(null, f2, f2, null);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        outline.setRoundRect(null, this.f441a);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.g;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f445e) != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        b(rect);
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.f445e;
        colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        throw null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.g = colorStateList;
        this.f446f = a(colorStateList, this.h);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.h = mode;
        this.f446f = a(this.g, mode);
        invalidateSelf();
    }
}
