package com.google.android.material.snackbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import b.g.k.t;
import com.google.android.material.internal.p;
import d.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final View.OnTouchListener f3795b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f3796c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f3797d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3798e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final float f3799f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final float f3800g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ColorStateList f3801h;
    private PorterDuff.Mode i;

    static class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    protected BaseTransientBottomBar$SnackbarBaseLayout(Context context) {
        this(context, null);
    }

    private Drawable a() {
        float dimension = getResources().getDimension(d.b.a.a.d.N);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(d.b.a.a.o.a.g(this, d.b.a.a.b.p, d.b.a.a.b.m, getBackgroundOverlayColorAlpha()));
        if (this.f3801h == null) {
            return androidx.core.graphics.drawable.a.r(gradientDrawable);
        }
        Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable);
        androidx.core.graphics.drawable.a.o(drawableR, this.f3801h);
        return drawableR;
    }

    float getActionTextColorAlpha() {
        return this.f3800g;
    }

    int getAnimationMode() {
        return this.f3798e;
    }

    float getBackgroundOverlayColorAlpha() {
        return this.f3799f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f3797d;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.g0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f3797d;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c cVar = this.f3796c;
        if (cVar != null) {
            cVar.a(this, i, i2, i3, i4);
        }
    }

    void setAnimationMode(int i) {
        this.f3798e = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f3801h != null) {
            drawable = androidx.core.graphics.drawable.a.r(drawable.mutate());
            androidx.core.graphics.drawable.a.o(drawable, this.f3801h);
            androidx.core.graphics.drawable.a.p(drawable, this.i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f3801h = colorStateList;
        if (getBackground() != null) {
            Drawable drawableR = androidx.core.graphics.drawable.a.r(getBackground().mutate());
            androidx.core.graphics.drawable.a.o(drawableR, colorStateList);
            androidx.core.graphics.drawable.a.p(drawableR, this.i);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.i = mode;
        if (getBackground() != null) {
            Drawable drawableR = androidx.core.graphics.drawable.a.r(getBackground().mutate());
            androidx.core.graphics.drawable.a.p(drawableR, mode);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    void setOnAttachStateChangeListener(b bVar) {
        this.f3797d = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f3795b);
        super.setOnClickListener(onClickListener);
    }

    void setOnLayoutChangeListener(c cVar) {
        this.f3796c = cVar;
    }

    protected BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, l.z5);
        if (typedArrayObtainStyledAttributes.hasValue(l.G5)) {
            t.q0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f3798e = typedArrayObtainStyledAttributes.getInt(l.C5, 0);
        this.f3799f = typedArrayObtainStyledAttributes.getFloat(l.D5, 1.0f);
        setBackgroundTintList(d.b.a.a.t.c.a(context2, typedArrayObtainStyledAttributes, l.E5));
        setBackgroundTintMode(p.i(typedArrayObtainStyledAttributes.getInt(l.F5, -1), PorterDuff.Mode.SRC_IN));
        this.f3800g = typedArrayObtainStyledAttributes.getFloat(l.B5, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f3795b);
        setFocusable(true);
        if (getBackground() == null) {
            t.m0(this, a());
        }
    }
}
