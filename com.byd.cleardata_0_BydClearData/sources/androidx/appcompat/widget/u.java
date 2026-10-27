package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
class u extends q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SeekBar f1026d;
    private Drawable e;
    private ColorStateList f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    u(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.f1026d = seekBar;
    }

    private void f() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableR = androidx.core.graphics.drawable.a.r(drawable.mutate());
                this.e = drawableR;
                if (this.h) {
                    androidx.core.graphics.drawable.a.o(drawableR, this.f);
                }
                if (this.i) {
                    androidx.core.graphics.drawable.a.p(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.f1026d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.q
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        Context context = this.f1026d.getContext();
        int[] iArr = a.a.j.V;
        v0 v0VarV = v0.v(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.f1026d;
        a.g.k.t.f0(seekBar, seekBar.getContext(), iArr, attributeSet, v0VarV.r(), i, 0);
        Drawable drawableH = v0VarV.h(a.a.j.W);
        if (drawableH != null) {
            this.f1026d.setThumb(drawableH);
        }
        j(v0VarV.g(a.a.j.X));
        int i2 = a.a.j.Z;
        if (v0VarV.s(i2)) {
            this.g = d0.d(v0VarV.k(i2, -1), this.g);
            this.i = true;
        }
        int i3 = a.a.j.Y;
        if (v0VarV.s(i3)) {
            this.f = v0VarV.c(i3);
            this.h = true;
        }
        v0VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.e != null) {
            int max = this.f1026d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i, -i2, i, i2);
                float width = ((this.f1026d.getWidth() - this.f1026d.getPaddingLeft()) - this.f1026d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f1026d.getPaddingLeft(), this.f1026d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void h() {
        Drawable drawable = this.e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1026d.getDrawableState())) {
            this.f1026d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1026d);
            androidx.core.graphics.drawable.a.m(drawable, a.g.k.t.y(this.f1026d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1026d.getDrawableState());
            }
            f();
        }
        this.f1026d.invalidate();
    }
}
