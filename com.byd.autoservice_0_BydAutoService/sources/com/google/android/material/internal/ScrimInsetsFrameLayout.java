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
import i2.k;
import l0.b0;
import l0.p;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f2364b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f2365c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Rect f2366d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2367e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2368f;

    public class a implements p {
        public a() {
        }

        @Override // l0.p
        public b0 a(View view, b0 b0Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f2365c == null) {
                scrimInsetsFrameLayout.f2365c = new Rect();
            }
            ScrimInsetsFrameLayout.this.f2365c.set(b0Var.f(), b0Var.h(), b0Var.g(), b0Var.e());
            ScrimInsetsFrameLayout.this.a(b0Var);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!b0Var.j() || ScrimInsetsFrameLayout.this.f2364b == null);
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
        if (this.f2365c == null || this.f2364b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.f2367e) {
            this.f2366d.set(0, 0, width, this.f2365c.top);
            this.f2364b.setBounds(this.f2366d);
            this.f2364b.draw(canvas);
        }
        if (this.f2368f) {
            this.f2366d.set(0, height - this.f2365c.bottom, width, height);
            this.f2364b.setBounds(this.f2366d);
            this.f2364b.draw(canvas);
        }
        Rect rect = this.f2366d;
        Rect rect2 = this.f2365c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.f2364b.setBounds(this.f2366d);
        this.f2364b.draw(canvas);
        Rect rect3 = this.f2366d;
        Rect rect4 = this.f2365c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.f2364b.setBounds(this.f2366d);
        this.f2364b.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.f2364b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.f2364b;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z3) {
        this.f2368f = z3;
    }

    public void setDrawTopInsetForeground(boolean z3) {
        this.f2367e = z3;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.f2364b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f2366d = new Rect();
        this.f2367e = true;
        this.f2368f = true;
        TypedArray typedArrayH = k.h(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i3, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.f2364b = typedArrayH.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t.v0(this, new a());
    }
}
