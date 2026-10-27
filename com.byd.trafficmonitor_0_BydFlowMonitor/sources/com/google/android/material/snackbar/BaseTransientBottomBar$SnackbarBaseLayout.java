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
import b.h.m.t;
import c.b.a.a.d;
import c.b.a.a.d0.p;
import c.b.a.a.k0.b;
import c.b.a.a.k0.c;
import c.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final View.OnTouchListener f4813b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f4814c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f4815d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4816e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f4817f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f4818g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f4819h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f4820i;

    public static class a implements View.OnTouchListener {
        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    public BaseTransientBottomBar$SnackbarBaseLayout(Context context) {
        this(context, null);
    }

    public final Drawable a() {
        float dimension = getResources().getDimension(d.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(c.b.a.a.w.a.g(this, c.b.a.a.b.colorSurface, c.b.a.a.b.colorOnSurface, getBackgroundOverlayColorAlpha()));
        if (this.f4819h == null) {
            return b.h.f.l.a.r(gradientDrawable);
        }
        Drawable drawableR = b.h.f.l.a.r(gradientDrawable);
        b.h.f.l.a.o(drawableR, this.f4819h);
        return drawableR;
    }

    public float getActionTextColorAlpha() {
        return this.f4818g;
    }

    public int getAnimationMode() {
        return this.f4816e;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.f4817f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f4815d;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.h0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f4815d;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        c cVar = this.f4814c;
        if (cVar != null) {
            cVar.a(this, i2, i3, i4, i5);
        }
    }

    public void setAnimationMode(int i2) {
        this.f4816e = i2;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f4819h != null) {
            drawable = b.h.f.l.a.r(drawable.mutate());
            b.h.f.l.a.o(drawable, this.f4819h);
            b.h.f.l.a.p(drawable, this.f4820i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f4819h = colorStateList;
        if (getBackground() != null) {
            Drawable drawableR = b.h.f.l.a.r(getBackground().mutate());
            b.h.f.l.a.o(drawableR, colorStateList);
            b.h.f.l.a.p(drawableR, this.f4820i);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f4820i = mode;
        if (getBackground() != null) {
            Drawable drawableR = b.h.f.l.a.r(getBackground().mutate());
            b.h.f.l.a.p(drawableR, mode);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    public void setOnAttachStateChangeListener(b bVar) {
        this.f4815d = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f4813b);
        super.setOnClickListener(onClickListener);
    }

    public void setOnLayoutChangeListener(c cVar) {
        this.f4814c = cVar;
    }

    public BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(c.b.a.a.m0.a.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, l.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(l.SnackbarLayout_elevation)) {
            t.r0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f4816e = typedArrayObtainStyledAttributes.getInt(l.SnackbarLayout_animationMode, 0);
        this.f4817f = typedArrayObtainStyledAttributes.getFloat(l.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(c.b.a.a.f0.c.a(context2, typedArrayObtainStyledAttributes, l.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(p.i(typedArrayObtainStyledAttributes.getInt(l.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f4818g = typedArrayObtainStyledAttributes.getFloat(l.SnackbarLayout_actionTextColorAlpha, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f4813b);
        setFocusable(true);
        if (getBackground() == null) {
            t.n0(this, a());
        }
    }
}
