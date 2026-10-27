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
    Drawable f3689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Rect f3690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Rect f3691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f3693f;

    class a implements b.g.k.p {
        a() {
        }

        @Override // b.g.k.p
        public b0 a(View view, b0 b0Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f3690c == null) {
                scrimInsetsFrameLayout.f3690c = new Rect();
            }
            ScrimInsetsFrameLayout.this.f3690c.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
            ScrimInsetsFrameLayout.this.a(b0Var);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!b0Var.j() || ScrimInsetsFrameLayout.this.f3689b == null);
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
        if (this.f3690c == null || this.f3689b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.f3692e) {
            this.f3691d.set(0, 0, width, this.f3690c.top);
            this.f3689b.setBounds(this.f3691d);
            this.f3689b.draw(canvas);
        }
        if (this.f3693f) {
            this.f3691d.set(0, height - this.f3690c.bottom, width, height);
            this.f3689b.setBounds(this.f3691d);
            this.f3689b.draw(canvas);
        }
        Rect rect = this.f3691d;
        Rect rect2 = this.f3690c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f3689b.setBounds(this.f3691d);
        this.f3689b.draw(canvas);
        Rect rect3 = this.f3691d;
        Rect rect4 = this.f3690c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f3689b.setBounds(this.f3691d);
        this.f3689b.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f3689b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f3689b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.f3693f = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.f3692e = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f3689b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3691d = new Rect();
        this.f3692e = true;
        this.f3693f = true;
        TypedArray typedArrayH = k.h(context, attributeSet, d.b.a.a.l.M4, i, d.b.a.a.k.m, new int[0]);
        this.f3689b = typedArrayH.getDrawable(d.b.a.a.l.N4);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t.v0(this, new a());
    }
}
