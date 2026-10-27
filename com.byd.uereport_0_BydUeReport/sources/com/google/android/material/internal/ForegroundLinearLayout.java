package com.google.android.material.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.R$styleable;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class ForegroundLinearLayout extends LinearLayoutCompat {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Drawable f3936s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f3937t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Rect f3938u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3939v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f3940w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f3941x;

    public ForegroundLinearLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.f3936s;
        if (drawable != null) {
            if (this.f3941x) {
                this.f3941x = false;
                Rect rect = this.f3937t;
                Rect rect2 = this.f3938u;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                if (this.f3940w) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                Gravity.apply(this.f3939v, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    @Override // android.view.View
    @TargetApi(21)
    public void drawableHotspotChanged(float f8, float f9) {
        super.drawableHotspotChanged(f8, f9);
        Drawable drawable = this.f3936s;
        if (drawable != null) {
            drawable.setHotspot(f8, f9);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f3936s;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.f3936s.setState(getDrawableState());
    }

    @Override // android.view.View
    public Drawable getForeground() {
        return this.f3936s;
    }

    @Override // android.view.View
    public int getForegroundGravity() {
        return this.f3939v;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f3936s;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        this.f3941x = z7 | this.f3941x;
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        this.f3941x = true;
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.f3936s;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.f3936s);
            }
            this.f3936s = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f3939v == 119) {
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
    public void setForegroundGravity(int i8) {
        if (this.f3939v != i8) {
            if ((8388615 & i8) == 0) {
                i8 |= 8388611;
            }
            if ((i8 & 112) == 0) {
                i8 |= 48;
            }
            this.f3939v = i8;
            if (i8 == 119 && this.f3936s != null) {
                this.f3936s.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f3936s;
    }

    public ForegroundLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ForegroundLinearLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3937t = new Rect();
        this.f3938u = new Rect();
        this.f3939v = 119;
        this.f3940w = true;
        this.f3941x = false;
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.ForegroundLinearLayout, i8, 0, new int[0]);
        this.f3939v = typedArrayH.getInt(R$styleable.ForegroundLinearLayout_android_foregroundGravity, this.f3939v);
        Drawable drawable = typedArrayH.getDrawable(R$styleable.ForegroundLinearLayout_android_foreground);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.f3940w = typedArrayH.getBoolean(R$styleable.ForegroundLinearLayout_foregroundInsidePadding, true);
        typedArrayH.recycle();
    }
}
