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
import defpackage.ba;
import defpackage.jj;
import defpackage.p9;
import defpackage.t9;

/* JADX INFO: loaded from: classes.dex */
public class ScrimInsetsFrameLayout extends FrameLayout {
    public Drawable b;
    public Rect c;
    public Rect d;
    public boolean e;
    public boolean f;

    public class a implements p9 {
        public a() {
        }

        @Override // defpackage.p9
        public ba a(View view, ba baVar) {
            ScrimInsetsFrameLayout scrimInsetsFrameLayout = ScrimInsetsFrameLayout.this;
            if (scrimInsetsFrameLayout.c == null) {
                scrimInsetsFrameLayout.c = new Rect();
            }
            ScrimInsetsFrameLayout.this.c.set(baVar.f(), baVar.h(), baVar.g(), baVar.e());
            ScrimInsetsFrameLayout.this.a(baVar);
            ScrimInsetsFrameLayout.this.setWillNotDraw(!baVar.j() || ScrimInsetsFrameLayout.this.b == null);
            t9.a0(ScrimInsetsFrameLayout.this);
            return baVar.c();
        }
    }

    public ScrimInsetsFrameLayout(Context context) {
        this(context, null);
    }

    public void a(ba baVar) {
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.c == null || this.b == null) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        if (this.e) {
            this.d.set(0, 0, width, this.c.top);
            this.b.setBounds(this.d);
            this.b.draw(canvas);
        }
        if (this.f) {
            this.d.set(0, height - this.c.bottom, width, height);
            this.b.setBounds(this.d);
            this.b.draw(canvas);
        }
        Rect rect = this.d;
        Rect rect2 = this.c;
        rect.set(0, rect2.top, rect2.left, height - rect2.bottom);
        this.b.setBounds(this.d);
        this.b.draw(canvas);
        Rect rect3 = this.d;
        Rect rect4 = this.c;
        rect3.set(width - rect4.right, rect4.top, width, height - rect4.bottom);
        this.b.setBounds(this.d);
        this.b.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable = this.b;
        if (drawable != null) {
            drawable.setCallback(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable = this.b;
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
        this.b = drawable;
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimInsetsFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = new Rect();
        this.e = true;
        this.f = true;
        TypedArray typedArrayH = jj.h(context, attributeSet, R$styleable.ScrimInsetsFrameLayout, i, R$style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.b = typedArrayH.getDrawable(R$styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArrayH.recycle();
        setWillNotDraw(true);
        t9.v0(this, new a());
    }
}
