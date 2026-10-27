package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import b.h.m.b0;
import b.h.m.p;
import b.h.m.t;
import c.b.a.a.d0.k;
import c.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f4769b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f4770c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Rect f4771d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4772e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4773f;

    public class a implements p {
        public a() {
        }

        @Override // b.h.m.p
        public b0 a(View view, b0 b0Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f4770c == null) {
                scrimInsetsFrameLayout.f4770c = new Rect();
            }
            ScrimInsetsFrameLayout.this.f4770c.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
            ScrimInsetsFrameLayout.this.a(b0Var);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!b0Var.j() || ScrimInsetsFrameLayout.this.f4769b == null);
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
        if (this.f4770c == null || this.f4769b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.f4772e) {
            this.f4771d.set(0, 0, width, this.f4770c.top);
            this.f4769b.setBounds(this.f4771d);
            this.f4769b.draw(canvas);
        }
        if (this.f4773f) {
            this.f4771d.set(0, height - this.f4770c.bottom, width, height);
            this.f4769b.setBounds(this.f4771d);
            this.f4769b.draw(canvas);
        }
        Rect rect = this.f4771d;
        Rect rect2 = this.f4770c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f4769b.setBounds(this.f4771d);
        this.f4769b.draw(canvas);
        Rect rect3 = this.f4771d;
        Rect rect4 = this.f4770c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f4769b.setBounds(this.f4771d);
        this.f4769b.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f4769b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f4769b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.f4773f = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.f4772e = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f4769b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4771d = new Rect();
        this.f4772e = true;
        this.f4773f = true;
        TypedArray typedArrayH = k.h(context, attributeSet, l.ScrimInsetsFrameLayout, i2, c.b.a.a.k.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.f4769b = typedArrayH.getDrawable(l.ScrimInsetsFrameLayout_insetForeground);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t.x0(this, new a());
    }
}
