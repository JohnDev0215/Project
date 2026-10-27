package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import b.g.k.b0;
import b.g.k.t;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable f4106b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Rect f4107c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Rect f4108d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4110f;

    class a implements b.g.k.p {
        a() {
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f4107c == null) {
                scrimInsetsFrameLayout.f4107c = new Rect();
            }
            ScrimInsetsFrameLayout.this.f4107c.set(b0Var.g(), b0Var.i(), b0Var.h(), b0Var.f());
            ScrimInsetsFrameLayout.this.a(b0Var);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!b0Var.k() || ScrimInsetsFrameLayout.this.f4106b == null);
            t.a0(ScrimInsetsFrameLayout.this);
            return b0Var.c();
        }
    }

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    protected void a(b0 b0Var) {
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f4107c == null || this.f4106b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.f4109e) {
            this.f4108d.set(0, 0, width, this.f4107c.top);
            this.f4106b.setBounds(this.f4108d);
            this.f4106b.draw(canvas);
        }
        if (this.f4110f) {
            this.f4108d.set(0, height - this.f4107c.bottom, width, height);
            this.f4106b.setBounds(this.f4108d);
            this.f4106b.draw(canvas);
        }
        Rect rect = this.f4108d;
        Rect rect2 = this.f4107c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f4106b.setBounds(this.f4108d);
        this.f4106b.draw(canvas);
        Rect rect3 = this.f4108d;
        Rect rect4 = this.f4107c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f4106b.setBounds(this.f4108d);
        this.f4106b.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f4106b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f4106b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.f4110f = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.f4109e = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f4106b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4108d = new Rect();
        this.f4109e = true;
        this.f4110f = true;
        TypedArray typedArrayH = l.h(context, attributeSet, d.b.a.a.l.ScrimInsetsFrameLayout, i2, d.b.a.a.k.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.f4106b = typedArrayH.getDrawable(d.b.a.a.l.ScrimInsetsFrameLayout_insetForeground);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t.v0(this, new a());
    }
}
