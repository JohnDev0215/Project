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
import i0.u;
import o2.l;

/* JADX INFO: compiled from: BaseTransientBottomBar.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends FrameLayout {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static final View.OnTouchListener f4691m = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f4692e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b f4693g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4694h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final float f4695i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final float f4696j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ColorStateList f4697k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private PorterDuff.Mode f4698l;

    /* JADX INFO: compiled from: BaseTransientBottomBar.java */
    static class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    protected d(Context context, AttributeSet attributeSet) {
        super(f3.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, l.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(l.SnackbarLayout_elevation)) {
            u.r0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f4694h = typedArrayObtainStyledAttributes.getInt(l.SnackbarLayout_animationMode, 0);
        this.f4695i = typedArrayObtainStyledAttributes.getFloat(l.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(b3.c.a(context2, typedArrayObtainStyledAttributes, l.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(com.google.android.material.internal.l.e(typedArrayObtainStyledAttributes.getInt(l.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.f4696j = typedArrayObtainStyledAttributes.getFloat(l.SnackbarLayout_actionTextColorAlpha, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f4691m);
        setFocusable(true);
        if (getBackground() == null) {
            u.n0(this, a());
        }
    }

    private Drawable a() {
        float dimension = getResources().getDimension(o2.d.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(u2.a.g(this, o2.b.colorSurface, o2.b.colorOnSurface, getBackgroundOverlayColorAlpha()));
        if (this.f4697k == null) {
            return a0.a.l(gradientDrawable);
        }
        Drawable drawableL = a0.a.l(gradientDrawable);
        a0.a.i(drawableL, this.f4697k);
        return drawableL;
    }

    float getActionTextColorAlpha() {
        return this.f4696j;
    }

    int getAnimationMode() {
        return this.f4694h;
    }

    float getBackgroundOverlayColorAlpha() {
        return this.f4695i;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f4693g;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        u.h0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f4693g;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        c cVar = this.f4692e;
        if (cVar != null) {
            cVar.a(this, i7, i8, i9, i10);
        }
    }

    void setAnimationMode(int i7) {
        this.f4694h = i7;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.f4697k != null) {
            drawable = a0.a.l(drawable.mutate());
            a0.a.i(drawable, this.f4697k);
            a0.a.j(drawable, this.f4698l);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.f4697k = colorStateList;
        if (getBackground() != null) {
            Drawable drawableL = a0.a.l(getBackground().mutate());
            a0.a.i(drawableL, colorStateList);
            a0.a.j(drawableL, this.f4698l);
            if (drawableL != getBackground()) {
                super.setBackgroundDrawable(drawableL);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.f4698l = mode;
        if (getBackground() != null) {
            Drawable drawableL = a0.a.l(getBackground().mutate());
            a0.a.j(drawableL, mode);
            if (drawableL != getBackground()) {
                super.setBackgroundDrawable(drawableL);
            }
        }
    }

    void setOnAttachStateChangeListener(b bVar) {
        this.f4693g = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f4691m);
        super.setOnClickListener(onClickListener);
    }

    void setOnLayoutChangeListener(c cVar) {
        this.f4692e = cVar;
    }
}
