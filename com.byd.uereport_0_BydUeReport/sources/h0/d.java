package h0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: WrappedDrawableApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends Drawable implements Drawable.Callback, c, b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final PorterDuff.Mode f5108d = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PorterDuff.Mode f5110f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5111g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f f5112h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5113i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f5114j;

    public d(f fVar, Resources resources) {
        this.f5112h = fVar;
        e(resources);
    }

    @Override // h0.c
    public final void a(Drawable drawable) {
        Drawable drawable2 = this.f5114j;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f5114j = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            f fVar = this.f5112h;
            if (fVar != null) {
                fVar.f5117b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    @Override // h0.c
    public final Drawable b() {
        return this.f5114j;
    }

    public boolean c() {
        return true;
    }

    public final f d() {
        return new f(this.f5112h);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f5114j.draw(canvas);
    }

    public final void e(Resources resources) {
        Drawable.ConstantState constantState;
        f fVar = this.f5112h;
        if (fVar == null || (constantState = fVar.f5117b) == null) {
            return;
        }
        a(constantState.newDrawable(resources));
    }

    public final boolean f(int[] iArr) {
        if (!c()) {
            return false;
        }
        f fVar = this.f5112h;
        ColorStateList colorStateList = fVar.f5118c;
        PorterDuff.Mode mode = fVar.f5119d;
        if (colorStateList == null || mode == null) {
            this.f5111g = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.f5111g || colorForState != this.f5109e || mode != this.f5110f) {
                setColorFilter(colorForState, mode);
                this.f5109e = colorForState;
                this.f5110f = mode;
                this.f5111g = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        f fVar = this.f5112h;
        return changingConfigurations | (fVar != null ? fVar.getChangingConfigurations() : 0) | this.f5114j.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        f fVar = this.f5112h;
        if (fVar == null || !fVar.a()) {
            return null;
        }
        this.f5112h.f5116a = getChangingConfigurations();
        return this.f5112h;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f5114j.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f5114j.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f5114j.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.f5114j.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.f5114j.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f5114j.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f5114j.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.f5114j.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.f5114j.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f5114j.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        f fVar;
        ColorStateList colorStateList = (!c() || (fVar = this.f5112h) == null) ? null : fVar.f5118c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f5114j.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.f5114j.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f5113i && super.mutate() == this) {
            this.f5112h = d();
            Drawable drawable = this.f5114j;
            if (drawable != null) {
                drawable.mutate();
            }
            f fVar = this.f5112h;
            if (fVar != null) {
                Drawable drawable2 = this.f5114j;
                fVar.f5117b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f5113i = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f5114j;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i8) {
        return this.f5114j.setLevel(i8);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
        scheduleSelf(runnable, j8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f5114j.setAlpha(i8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z7) {
        this.f5114j.setAutoMirrored(z7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i8) {
        this.f5114j.setChangingConfigurations(i8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f5114j.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z7) {
        this.f5114j.setDither(z7);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z7) {
        this.f5114j.setFilterBitmap(z7);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return f(iArr) || this.f5114j.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTint(int i8) {
        setTintList(ColorStateList.valueOf(i8));
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        this.f5112h.f5118c = colorStateList;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f5112h.f5119d = mode;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z7, boolean z8) {
        return super.setVisible(z7, z8) || this.f5114j.setVisible(z7, z8);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public d(Drawable drawable) {
        this.f5112h = d();
        a(drawable);
    }
}
