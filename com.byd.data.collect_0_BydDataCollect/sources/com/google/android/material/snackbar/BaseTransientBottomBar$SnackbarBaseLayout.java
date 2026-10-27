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
import com.google.android.material.internal.q;
import d.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final View.OnTouchListener f4218b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f4219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f4220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final float f4222f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final float f4223g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ColorStateList f4224h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private PorterDuff.Mode f4225i;

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
        float dimension = getResources().getDimension(d.b.a.a.d.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(d.b.a.a.o.a.g(this, d.b.a.a.b.colorSurface, d.b.a.a.b.colorOnSurface, getBackgroundOverlayColorAlpha()));
        if (this.f4224h == null) {
            return androidx.core.graphics.drawable.a.r(gradientDrawable);
        }
        Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable);
        androidx.core.graphics.drawable.a.o(drawableR, this.f4224h);
        return drawableR;
    }

    float getActionTextColorAlpha() {
        return this.f4223g;
    }

    int getAnimationMode() {
        return this.f4221e;
    }

    float getBackgroundOverlayColorAlpha() {
        return this.f4222f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f4220d;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.g0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f4220d;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        c cVar = this.f4219c;
        if (cVar != null) {
            cVar.a(this, i2, i3, i4, i5);
        }
    }

    void setAnimationMode(int i2) {
        this.f4221e = i2;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f4224h != null) {
            drawable = androidx.core.graphics.drawable.a.r(drawable.mutate());
            androidx.core.graphics.drawable.a.o(drawable, this.f4224h);
            androidx.core.graphics.drawable.a.p(drawable, this.f4225i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f4224h = colorStateList;
        if (getBackground() != null) {
            Drawable drawableR = androidx.core.graphics.drawable.a.r(getBackground().mutate());
            androidx.core.graphics.drawable.a.o(drawableR, colorStateList);
            androidx.core.graphics.drawable.a.p(drawableR, this.f4225i);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f4225i = mode;
        if (getBackground() != null) {
            Drawable drawableR = androidx.core.graphics.drawable.a.r(getBackground().mutate());
            androidx.core.graphics.drawable.a.p(drawableR, mode);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    void setOnAttachStateChangeListener(b bVar) {
        this.f4220d = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f4218b);
        super.setOnClickListener(onClickListener);
    }

    void setOnLayoutChangeListener(c cVar) {
        this.f4219c = cVar;
    }

    protected BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, l.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(l.SnackbarLayout_elevation)) {
            t.q0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f4221e = typedArrayObtainStyledAttributes.getInt(l.SnackbarLayout_animationMode, 0);
        this.f4222f = typedArrayObtainStyledAttributes.getFloat(l.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(d.b.a.a.u.c.a(context2, typedArrayObtainStyledAttributes, l.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(q.i(typedArrayObtainStyledAttributes.getInt(l.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f4223g = typedArrayObtainStyledAttributes.getFloat(l.SnackbarLayout_actionTextColorAlpha, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f4218b);
        setFocusable(true);
        if (getBackground() == null) {
            t.m0(this, a());
        }
    }
}
