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

/* JADX INFO: compiled from: ForegroundLinearLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends LinearLayoutCompat {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private Drawable f4630u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private final Rect f4631v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final Rect f4632w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f4633x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    protected boolean f4634y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    boolean f4635z;

    public d(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.f4630u;
        if (drawable != null) {
            if (this.f4635z) {
                this.f4635z = false;
                Rect rect = this.f4631v;
                Rect rect2 = this.f4632w;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                if (this.f4634y) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                Gravity.apply(this.f4633x, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    @Override // android.view.View
    @TargetApi(21)
    public void drawableHotspotChanged(float f7, float f8) {
        super.drawableHotspotChanged(f7, f8);
        Drawable drawable = this.f4630u;
        if (drawable != null) {
            drawable.setHotspot(f7, f8);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f4630u;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.f4630u.setState(getDrawableState());
    }

    @Override // android.view.View
    public Drawable getForeground() {
        return this.f4630u;
    }

    @Override // android.view.View
    public int getForegroundGravity() {
        return this.f4633x;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f4630u;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        this.f4635z = z6 | this.f4635z;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        this.f4635z = true;
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.f4630u;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.f4630u);
            }
            this.f4630u = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f4633x == 119) {
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
        if (this.f4633x != i7) {
            if ((8388615 & i7) == 0) {
                i7 |= 8388611;
            }
            if ((i7 & 112) == 0) {
                i7 |= 48;
            }
            this.f4633x = i7;
            if (i7 == 119 && this.f4630u != null) {
                this.f4630u.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f4630u;
    }

    public d(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f4631v = new Rect();
        this.f4632w = new Rect();
        this.f4633x = 119;
        this.f4634y = true;
        this.f4635z = false;
        TypedArray typedArrayH = j.h(context, attributeSet, o2.l.ForegroundLinearLayout, i7, 0, new int[0]);
        this.f4633x = typedArrayH.getInt(o2.l.ForegroundLinearLayout_android_foregroundGravity, this.f4633x);
        Drawable drawable = typedArrayH.getDrawable(o2.l.ForegroundLinearLayout_android_foreground);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.f4634y = typedArrayH.getBoolean(o2.l.ForegroundLinearLayout_foregroundInsidePadding, true);
        typedArrayH.recycle();
    }
}
