package b.h.f.l;

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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final PorterDuff.Mode f2461b = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2462c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f2463d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2464e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f f2465f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2466g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f2467h;

    public d(f fVar, Resources resources) {
        this.f2465f = fVar;
        e(resources);
    }

    @Override // b.h.f.l.c
    public final Drawable a() {
        return this.f2467h;
    }

    @Override // b.h.f.l.c
    public final void b(Drawable drawable) {
        Drawable drawable2 = this.f2467h;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f2467h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            f fVar = this.f2465f;
            if (fVar != null) {
                fVar.f2470b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public boolean c() {
        return true;
    }

    public final f d() {
        return new f(this.f2465f);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f2467h.draw(canvas);
    }

    public final void e(Resources resources) {
        Drawable.ConstantState constantState;
        f fVar = this.f2465f;
        if (fVar == null || (constantState = fVar.f2470b) == null) {
            return;
        }
        b(constantState.newDrawable(resources));
    }

    public final boolean f(int[] iArr) {
        if (!c()) {
            return false;
        }
        f fVar = this.f2465f;
        ColorStateList colorStateList = fVar.f2471c;
        PorterDuff.Mode mode = fVar.f2472d;
        if (colorStateList == null || mode == null) {
            this.f2464e = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.f2464e || colorForState != this.f2462c || mode != this.f2463d) {
                setColorFilter(colorForState, mode);
                this.f2462c = colorForState;
                this.f2463d = mode;
                this.f2464e = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        f fVar = this.f2465f;
        return changingConfigurations | (fVar != null ? fVar.getChangingConfigurations() : 0) | this.f2467h.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        f fVar = this.f2465f;
        if (fVar == null || !fVar.a()) {
            return null;
        }
        this.f2465f.f2469a = getChangingConfigurations();
        return this.f2465f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f2467h.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f2467h.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f2467h.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.f2467h.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.f2467h.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f2467h.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.f2467h.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.f2467h.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.f2467h.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f2467h.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        f fVar;
        ColorStateList colorStateList = (!c() || (fVar = this.f2465f) == null) ? null : fVar.f2471c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f2467h.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.f2467h.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f2466g && super.mutate() == this) {
            this.f2465f = d();
            Drawable drawable = this.f2467h;
            if (drawable != null) {
                drawable.mutate();
            }
            f fVar = this.f2465f;
            if (fVar != null) {
                Drawable drawable2 = this.f2467h;
                fVar.f2470b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f2466g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f2467h;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        return this.f2467h.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f2467h.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        this.f2467h.setAutoMirrored(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i2) {
        this.f2467h.setChangingConfigurations(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f2467h.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.f2467h.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.f2467h.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return f(iArr) || this.f2467h.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintList(ColorStateList colorStateList) {
        this.f2465f.f2471c = colorStateList;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable, b.h.f.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.f2465f.f2472d = mode;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f2467h.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public d(Drawable drawable) {
        this.f2465f = d();
        b(drawable);
    }
}
