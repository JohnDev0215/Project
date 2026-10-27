package com.google.android.material.snackbar;

import a.g.k.t;
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
import b.a.a.a.l;
import com.google.android.material.internal.j;

/* JADX INFO: loaded from: classes.dex */
public class d extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final View.OnTouchListener f3166b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c f3167c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f3168d;
    private int e;
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

    protected d(Context context, AttributeSet attributeSet) {
        super(com.google.android.material.theme.a.a.c(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, l.e3);
        if (typedArrayObtainStyledAttributes.hasValue(l.l3)) {
            t.o0(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(r1, 0));
        }
        this.e = typedArrayObtainStyledAttributes.getInt(l.h3, 0);
        this.f = typedArrayObtainStyledAttributes.getFloat(l.i3, 1.0f);
        setBackgroundTintList(b.a.a.a.y.c.a(context2, typedArrayObtainStyledAttributes, l.j3));
        setBackgroundTintMode(j.e(typedArrayObtainStyledAttributes.getInt(l.k3, -1), PorterDuff.Mode.SRC_IN));
        this.g = typedArrayObtainStyledAttributes.getFloat(l.g3, 1.0f);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(f3166b);
        setFocusable(true);
        if (getBackground() == null) {
            t.k0(this, a());
        }
    }

    private Drawable a() {
        float dimension = getResources().getDimension(b.a.a.a.d.w);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(b.a.a.a.s.a.g(this, b.a.a.a.b.l, b.a.a.a.b.i, getBackgroundOverlayColorAlpha()));
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
        return this.e;
    }

    float getBackgroundOverlayColorAlpha() {
        return this.f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b bVar = this.f3168d;
        if (bVar != null) {
            bVar.onViewAttachedToWindow(this);
        }
        t.e0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.f3168d;
        if (bVar != null) {
            bVar.onViewDetachedFromWindow(this);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        c cVar = this.f3167c;
        if (cVar != null) {
            cVar.a(this, i, i2, i3, i4);
        }
    }

    void setAnimationMode(int i) {
        this.e = i;
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
        this.f3168d = bVar;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : f3166b);
        super.setOnClickListener(onClickListener);
    }

    void setOnLayoutChangeListener(c cVar) {
        this.f3167c = cVar;
    }
}
