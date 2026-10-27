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
import c.b.a.a.l;
import com.google.android.material.internal.p;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final View.OnTouchListener f3977b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f3978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f3979d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3980e;
    private final float f;
    private final float g;
    private ColorStateList h;
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
        float dimension = getResources().getDimension(c.b.a.a.d.N);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(c.b.a.a.o.a.g(this, c.b.a.a.b.p, c.b.a.a.b.m, getBackgroundOverlayColorAlpha()));
        if (this.h == null) {
            return androidx.core.graphics.drawable.a.r(gradientDrawable);
        }
        Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable);
        androidx.core.graphics.drawable.a.o(drawableR, this.h);
        return drawableR;
    }

    float getActionTextColorAlpha() {
        return this.g;
    }

    int getAnimationMode() {
        return this.f3980e;
    }

    float getBackgroundOverlayColorAlpha() {
        return this.f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f3979d;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.h0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f3979d;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c cVar = this.f3978c;
        if (cVar != null) {
            cVar.a(this, i, i2, i3, i4);
        }
    }

    void setAnimationMode(int i) {
        this.f3980e = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.h != null) {
            drawable = androidx.core.graphics.drawable.a.r(drawable.mutate());
            androidx.core.graphics.drawable.a.o(drawable, this.h);
            androidx.core.graphics.drawable.a.p(drawable, this.i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.h = colorStateList;
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
        this.f3979d = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f3977b);
        super.setOnClickListener(onClickListener);
    }

    void setOnLayoutChangeListener(c cVar) {
        this.f3978c = cVar;
    }

    protected BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, l.z5);
        if (typedArrayObtainStyledAttributes.hasValue(l.G5)) {
            t.r0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.f3980e = typedArrayObtainStyledAttributes.getInt(l.C5, 0);
        this.f = typedArrayObtainStyledAttributes.getFloat(l.D5, 1.0f);
        setBackgroundTintList(c.b.a.a.t.c.a(context2, typedArrayObtainStyledAttributes, l.E5));
        setBackgroundTintMode(p.i(typedArrayObtainStyledAttributes.getInt(l.F5, -1), PorterDuff.Mode.SRC_IN));
        this.g = typedArrayObtainStyledAttributes.getFloat(l.B5, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f3977b);
        setFocusable(true);
        if (getBackground() == null) {
            t.n0(this, a());
        }
    }
}
