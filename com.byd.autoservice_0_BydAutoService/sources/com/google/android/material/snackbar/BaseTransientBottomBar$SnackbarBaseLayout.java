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
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import i2.p;
import l0.t;
import p2.b;
import p2.c;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final View.OnTouchListener f2439b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f2440c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f2441d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2442e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f2443f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f2444g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f2445h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f2446i;

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
        float dimension = getResources().getDimension(R$dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(c2.a.g(this, R$attr.colorSurface, R$attr.colorOnSurface, getBackgroundOverlayColorAlpha()));
        if (this.f2445h == null) {
            return e0.a.r(gradientDrawable);
        }
        Drawable drawableR = e0.a.r(gradientDrawable);
        e0.a.o(drawableR, this.f2445h);
        return drawableR;
    }

    public float getActionTextColorAlpha() {
        return this.f2444g;
    }

    public int getAnimationMode() {
        return this.f2442e;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.f2443f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f2441d;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.g0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f2441d;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        c cVar = this.f2440c;
        if (cVar != null) {
            cVar.a(this, i3, i4, i5, i6);
        }
    }

    public void setAnimationMode(int i3) {
        this.f2442e = i3;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f2445h != null) {
            drawable = e0.a.r(drawable.mutate());
            e0.a.o(drawable, this.f2445h);
            e0.a.p(drawable, this.f2446i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f2445h = colorStateList;
        if (getBackground() != null) {
            Drawable drawableR = e0.a.r(getBackground().mutate());
            e0.a.o(drawableR, colorStateList);
            e0.a.p(drawableR, this.f2446i);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f2446i = mode;
        if (getBackground() != null) {
            Drawable drawableR = e0.a.r(getBackground().mutate());
            e0.a.p(drawableR, mode);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    public void setOnAttachStateChangeListener(b bVar) {
        this.f2441d = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f2439b);
        super.setOnClickListener(onClickListener);
    }

    public void setOnLayoutChangeListener(c cVar) {
        this.f2440c = cVar;
    }

    public BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(r2.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
            t.q0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f2442e = typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_animationMode, 0);
        this.f2443f = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(k2.c.a(context2, typedArrayObtainStyledAttributes, R$styleable.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(p.i(typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f2444g = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f2439b);
        setFocusable(true);
        if (getBackground() == null) {
            t.m0(this, a());
        }
    }
}
