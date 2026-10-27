package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class F2 extends Drawable implements Drawable.Callback, D2, C2 {
    public static final PorterDuff.Mode y = PorterDuff.Mode.SRC_IN;
    public int s;
    public PorterDuff.Mode t;
    public boolean u;
    public E2 v;
    public boolean w;
    public Drawable x;

    public F2(E2 e2, Resources resources) {
        Drawable.ConstantState constantState;
        this.v = e2;
        if (e2 == null || (constantState = e2.b) == null) {
            return;
        }
        c(constantState.newDrawable(resources));
    }

    public abstract boolean a();

    public abstract E2 b();

    public final void c(Drawable drawable) {
        Drawable drawable2 = this.x;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.x = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            E2 e2 = this.v;
            if (e2 != null) {
                e2.b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public final boolean d(int[] iArr) {
        if (!a()) {
            return false;
        }
        E2 e2 = this.v;
        ColorStateList colorStateList = e2.c;
        PorterDuff.Mode mode = e2.d;
        if (colorStateList == null || mode == null) {
            this.u = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.u || colorForState != this.s || mode != this.t) {
                setColorFilter(colorForState, mode);
                this.s = colorForState;
                this.t = mode;
                this.u = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.x.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        E2 e2 = this.v;
        return changingConfigurations | (e2 != null ? e2.getChangingConfigurations() : 0) | this.x.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        E2 e2 = this.v;
        if (e2 == null) {
            return null;
        }
        if (!(e2.b != null)) {
            return null;
        }
        this.v.a = getChangingConfigurations();
        return this.v;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.x.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.x.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.x.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.x.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.x.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.x.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        return this.x.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        return this.x.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        return this.x.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.x.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        E2 e2;
        ColorStateList colorStateList = (!a() || (e2 = this.v) == null) ? null : e2.c;
        return (colorStateList != null && colorStateList.isStateful()) || this.x.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        this.x.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.w && super.mutate() == this) {
            this.v = b();
            Drawable drawable = this.x;
            if (drawable != null) {
                drawable.mutate();
            }
            E2 e2 = this.v;
            if (e2 != null) {
                Drawable drawable2 = this.x;
                e2.b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.w = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.x;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
        return this.x.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.x.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        this.x.setAutoMirrored(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i) {
        this.x.setChangingConfigurations(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.x.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.x.setDither(z);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.x.setFilterBitmap(z);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return d(iArr) || this.x.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    @Override // android.graphics.drawable.Drawable
    public abstract void setTintList(ColorStateList colorStateList);

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.x.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public F2(Drawable drawable) {
        this.v = b();
        c(drawable);
    }
}
