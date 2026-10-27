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
import defpackage.in3;
import defpackage.n01;
import defpackage.om3;
import defpackage.pt3;
import defpackage.r83;
import defpackage.wr1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {
    public Drawable e;
    public Rect f;
    public final Rect g;
    public boolean h;
    public boolean i;

    public class a implements wr1 {
        public a() {
        }

        @Override // defpackage.wr1
        public final pt3 k(View view, pt3 pt3Var) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.f == null) {
                scrimInsetsFrameLayout.f = new Rect();
            }
            scrimInsetsFrameLayout.f.set(pt3Var.c(), pt3Var.e(), pt3Var.d(), pt3Var.b());
            scrimInsetsFrameLayout.a(pt3Var);
            pt3.i iVar = pt3Var.a;
            boolean z = true;
            if ((!iVar.j().equals(n01.e)) && scrimInsetsFrameLayout.e != null) {
                z = false;
            }
            scrimInsetsFrameLayout.setWillNotDraw(z);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.d.j(scrimInsetsFrameLayout);
            return iVar.c();
        }
    }

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f == null || this.e == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        boolean z = this.h;
        Rect rect = this.g;
        if (z) {
            rect.set(0, 0, width, this.f.top);
            this.e.setBounds(rect);
            this.e.draw(canvas);
        }
        if (this.i) {
            rect.set(0, height - this.f.bottom, width, height);
            this.e.setBounds(rect);
            this.e.draw(canvas);
        }
        Rect rect2 = this.f;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.e.setBounds(rect);
        this.e.draw(canvas);
        Rect rect3 = this.f;
        rect.set(width - rect3.right, rect3.top, width, height - rect3.bottom);
        this.e.setBounds(rect);
        this.e.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.i = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.h = z;
    }

    public void setScrimInsetForeground(Drawable drawable) {
        this.e = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new Rect();
        this.h = true;
        this.i = true;
        TypedArray typedArrayD = r83.d(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.e = typedArrayD.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayD.recycle();
        setWillNotDraw(true);
        a aVar = new a();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.m(this, aVar);
    }

    public void a(pt3 pt3Var) {
    }
}
