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
import g6.b;
import g6.c;
import o0.t;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final View.OnTouchListener f4018d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f4019e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b f4020f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4021g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f4022h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f4023i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f4024j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PorterDuff.Mode f4025k;

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
        gradientDrawable.setColor(t5.a.g(this, R$attr.colorSurface, R$attr.colorOnSurface, getBackgroundOverlayColorAlpha()));
        if (this.f4024j == null) {
            return h0.a.r(gradientDrawable);
        }
        Drawable drawableR = h0.a.r(gradientDrawable);
        h0.a.o(drawableR, this.f4024j);
        return drawableR;
    }

    public float getActionTextColorAlpha() {
        return this.f4023i;
    }

    public int getAnimationMode() {
        return this.f4021g;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.f4022h;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f4020f;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.i0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f4020f;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        c cVar = this.f4019e;
        if (cVar != null) {
            cVar.a(this, i8, i9, i10, i11);
        }
    }

    public void setAnimationMode(int i8) {
        this.f4021g = i8;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f4024j != null) {
            drawable = h0.a.r(drawable.mutate());
            h0.a.o(drawable, this.f4024j);
            h0.a.p(drawable, this.f4025k);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f4024j = colorStateList;
        if (getBackground() != null) {
            Drawable drawableR = h0.a.r(getBackground().mutate());
            h0.a.o(drawableR, colorStateList);
            h0.a.p(drawableR, this.f4025k);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f4025k = mode;
        if (getBackground() != null) {
            Drawable drawableR = h0.a.r(getBackground().mutate());
            h0.a.p(drawableR, mode);
            if (drawableR != getBackground()) {
                super.setBackgroundDrawable(drawableR);
            }
        }
    }

    public void setOnAttachStateChangeListener(b bVar) {
        this.f4020f = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f4018d);
        super.setOnClickListener(onClickListener);
    }

    public void setOnLayoutChangeListener(c cVar) {
        this.f4019e = cVar;
    }

    public BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(i6.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
            t.s0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f4021g = typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_animationMode, 0);
        this.f4022h = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(b6.c.a(context2, typedArrayObtainStyledAttributes, R$styleable.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(p.i(typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f4023i = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f4018d);
        setFocusable(true);
        if (getBackground() == null) {
            t.o0(this, a());
        }
    }
}
