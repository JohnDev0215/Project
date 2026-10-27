package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import o0.b0;
import o0.p;
import o0.t;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f3945d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Rect f3946e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Rect f3947f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3948g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3949h;

    public class a implements p {
        public a() {
        }

        @Override // o0.p
        public b0 a(View view, b0 b0Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f3946e == null) {
                scrimInsetsFrameLayout.f3946e = new Rect();
            }
            ScrimInsetsFrameLayout.this.f3946e.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
            ScrimInsetsFrameLayout.this.a(b0Var);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!b0Var.j() || ScrimInsetsFrameLayout.this.f3945d == null);
            t.b0(ScrimInsetsFrameLayout.this);
            return b0Var.c();
        }
    }

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public void a(b0 b0Var) {
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f3946e == null || this.f3945d == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.f3948g) {
            this.f3947f.set(0, 0, width, this.f3946e.top);
            this.f3945d.setBounds(this.f3947f);
            this.f3945d.draw(canvas);
        }
        if (this.f3949h) {
            this.f3947f.set(0, height - this.f3946e.bottom, width, height);
            this.f3945d.setBounds(this.f3947f);
            this.f3945d.draw(canvas);
        }
        Rect rect = this.f3947f;
        Rect rect2 = this.f3946e;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f3945d.setBounds(this.f3947f);
        this.f3945d.draw(canvas);
        Rect rect3 = this.f3947f;
        Rect rect4 = this.f3946e;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f3945d.setBounds(this.f3947f);
        this.f3945d.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f3945d;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f3945d;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z7) {
        this.f3949h = z7;
    }

    public void setDrawTopInsetForeground(boolean z7) {
        this.f3948g = z7;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f3945d = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3947f = new Rect();
        this.f3948g = true;
        this.f3949h = true;
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i8, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.f3945d = typedArrayH.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t.y0(this, new a());
    }
}
