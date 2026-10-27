package U4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import r.AbstractC2235X;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends AbstractC2235X {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Drawable f9057A;
    public final Rect B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final Rect f9058C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f9059D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final boolean f9060E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f9061F;

    public e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B = new Rect();
        this.f9058C = new Rect();
        this.f9059D = 119;
        this.f9060E = true;
        this.f9061F = false;
        int[] iArr = K4.a.f3591i;
        k.a(context, attributeSet, 0, 0);
        k.b(context, attributeSet, iArr, 0, 0, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        this.f9059D = typedArrayObtainStyledAttributes.getInt(1, this.f9059D);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.f9060E = typedArrayObtainStyledAttributes.getBoolean(2, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.f9057A;
        if (drawable != null) {
            if (this.f9061F) {
                this.f9061F = false;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                boolean z6 = this.f9060E;
                Rect rect = this.B;
                if (z6) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                int i7 = this.f9059D;
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                Rect rect2 = this.f9058C;
                Gravity.apply(i7, intrinsicWidth, intrinsicHeight, rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    @Override // android.view.View
    public final void drawableHotspotChanged(float f10, float f11) {
        super.drawableHotspotChanged(f10, f11);
        Drawable drawable = this.f9057A;
        if (drawable != null) {
            drawable.setHotspot(f10, f11);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f9057A;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.f9057A.setState(getDrawableState());
    }

    @Override // android.view.View
    public Drawable getForeground() {
        return this.f9057A;
    }

    @Override // android.view.View
    public int getForegroundGravity() {
        return this.f9059D;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f9057A;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // r.AbstractC2235X, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        this.f9061F = z6 | this.f9061F;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        this.f9061F = true;
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.f9057A;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.f9057A);
            }
            this.f9057A = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f9059D == 119) {
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
    public void setForegroundGravity(int i7) {
        if (this.f9059D != i7) {
            if ((8388615 & i7) == 0) {
                i7 |= 8388611;
            }
            if ((i7 & 112) == 0) {
                i7 |= 48;
            }
            this.f9059D = i7;
            if (i7 == 119 && this.f9057A != null) {
                this.f9057A.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f9057A;
    }
}
