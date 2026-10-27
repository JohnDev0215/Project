package b.f.g.l;

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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final PorterDuff.Mode f1665c = PorterDuff.Mode.SRC_IN;
    public boolean E3;
    public Drawable F3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1666d;
    public PorterDuff.Mode s;
    public boolean x;
    public f y;

    public d(f fVar, Resources resources) {
        this.y = fVar;
        e(resources);
    }

    @Override // b.f.g.l.c
    public final Drawable a() {
        return this.F3;
    }

    @Override // b.f.g.l.c
    public final void b(Drawable drawable) {
        Drawable drawable2 = this.F3;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.F3 = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            f fVar = this.y;
            if (fVar != null) {
                fVar.f1668b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public boolean c() {
        return true;
    }

    public final f d() {
        return new f(this.y);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.F3.draw(canvas);
    }

    public final void e(Resources resources) {
        Drawable.ConstantState constantState;
        f fVar = this.y;
        if (fVar == null || (constantState = fVar.f1668b) == null) {
            return;
        }
        b(constantState.newDrawable(resources));
    }

    public final boolean f(int[] iArr) {
        if (!c()) {
            return false;
        }
        f fVar = this.y;
        ColorStateList colorStateList = fVar.f1669c;
        PorterDuff.Mode mode = fVar.f1670d;
        if (colorStateList == null || mode == null) {
            this.x = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.x || colorForState != this.f1666d || mode != this.s) {
                setColorFilter(colorForState, mode);
                this.f1666d = colorForState;
                this.s = mode;
                this.x = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        f fVar = this.y;
        return changingConfigurations | (fVar != null ? fVar.getChangingConfigurations() : 0) | this.F3.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        f fVar = this.y;
        if (fVar == null || !fVar.a()) {
            return null;
        }
        this.y.f1667a = getChangingConfigurations();
        return this.y;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.F3.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.F3.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.F3.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.F3.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.F3.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.F3.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.F3.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.F3.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.F3.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.F3.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        f fVar;
        ColorStateList colorStateList = (!c() || (fVar = this.y) == null) ? null : fVar.f1669c;
        return (colorStateList != null && colorStateList.isStateful()) || this.F3.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.F3.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.E3 && super.mutate() == this) {
            this.y = d();
            Drawable drawable = this.F3;
            if (drawable != null) {
                drawable.mutate();
            }
            f fVar = this.y;
            if (fVar != null) {
                Drawable drawable2 = this.F3;
                fVar.f1668b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.E3 = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.F3;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        return this.F3.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.F3.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        this.F3.setAutoMirrored(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i2) {
        this.F3.setChangingConfigurations(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.F3.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.F3.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.F3.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return f(iArr) || this.F3.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintList(ColorStateList colorStateList) {
        this.y.f1669c = colorStateList;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable, b.f.g.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        this.y.f1670d = mode;
        f(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.F3.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public d(Drawable drawable) {
        this.y = d();
        b(drawable);
    }
}
