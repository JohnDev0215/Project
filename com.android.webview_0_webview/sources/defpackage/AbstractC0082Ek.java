package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

/* JADX INFO: renamed from: Ek, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0082Ek extends AbstractC1358q0 {
    public Drawable H;
    public final Rect I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final Rect f1J;
    public int K;
    public boolean L;
    public boolean M;

    public AbstractC0082Ek(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.I = new Rect();
        this.f1J = new Rect();
        this.K = 119;
        this.L = true;
        this.M = false;
        TypedArray typedArrayB = AbstractC0118Gk.b(context, attributeSet, AbstractC0678dn.S, i, 0, new int[0]);
        this.K = typedArrayB.getInt(2, this.K);
        Drawable drawable = typedArrayB.getDrawable(1);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.L = typedArrayB.getBoolean(0, true);
        typedArrayB.recycle();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.H;
        if (drawable != null) {
            if (this.M) {
                this.M = false;
                Rect rect = this.I;
                Rect rect2 = this.f1J;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                if (this.L) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                Gravity.apply(this.K, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.H;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.H;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.H.setState(getDrawableState());
    }

    @Override // android.view.View
    public Drawable getForeground() {
        return this.H;
    }

    @Override // android.view.View
    public int getForegroundGravity() {
        return this.K;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.H;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // defpackage.AbstractC1358q0, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.M = z | this.M;
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.M = true;
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.H;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.H);
            }
            this.H = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.K == 119) {
                    drawable.getPadding(new Rect());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    public void setForegroundGravity(int i) {
        if (this.K != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.K = i;
            if (i == 119 && this.H != null) {
                this.H.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.H;
    }
}
