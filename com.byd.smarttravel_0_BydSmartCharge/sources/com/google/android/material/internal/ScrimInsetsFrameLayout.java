package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import b.g.j.b0;
import b.g.j.p;
import b.g.j.t;
import c.b.a.a.q.k;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f2204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f2205c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Rect f2206d;
    public boolean e;
    public boolean f;

    public class a implements p {
        public a() {
        }

        @Override // b.g.j.p
        public b0 a(View view, b0 b0Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f2205c == null) {
                scrimInsetsFrameLayout.f2205c = new Rect();
            }
            ScrimInsetsFrameLayout.this.f2205c.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
            ScrimInsetsFrameLayout.this.a(b0Var);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!b0Var.j() || ScrimInsetsFrameLayout.this.f2204b == null);
            t.a0(ScrimInsetsFrameLayout.this);
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
        if (this.f2205c == null || this.f2204b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.e) {
            this.f2206d.set(0, 0, width, this.f2205c.top);
            this.f2204b.setBounds(this.f2206d);
            this.f2204b.draw(canvas);
        }
        if (this.f) {
            this.f2206d.set(0, height - this.f2205c.bottom, width, height);
            this.f2204b.setBounds(this.f2206d);
            this.f2204b.draw(canvas);
        }
        Rect rect = this.f2206d;
        Rect rect2 = this.f2205c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f2204b.setBounds(this.f2206d);
        this.f2204b.draw(canvas);
        Rect rect3 = this.f2206d;
        Rect rect4 = this.f2205c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f2204b.setBounds(this.f2206d);
        this.f2204b.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f2204b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f2204b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.f = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.e = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f2204b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2206d = new Rect();
        this.e = true;
        this.f = true;
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.f2204b = typedArrayH.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t.v0(this, new a());
    }
}
