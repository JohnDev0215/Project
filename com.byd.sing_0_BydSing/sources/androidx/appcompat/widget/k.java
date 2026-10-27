package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: compiled from: AppCompatSeekBarHelper.java */
/* JADX INFO: loaded from: classes.dex */
class k extends j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SeekBar f987d;
    private Drawable e;
    private ColorStateList f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    k(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.f987d = seekBar;
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
                    this.e.setState(this.f987d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.j
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        Context context = this.f987d.getContext();
        int[] iArr = c.a.j.X;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.f987d;
        c.g.k.t.h0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        Drawable drawableH = e0VarV.h(c.a.j.Y);
        if (drawableH != null) {
            this.f987d.setThumb(drawableH);
        }
        j(e0VarV.g(c.a.j.Z));
        int i2 = c.a.j.b0;
        if (e0VarV.s(i2)) {
            this.g = q.e(e0VarV.k(i2, -1), this.g);
            this.i = true;
        }
        int i3 = c.a.j.a0;
        if (e0VarV.s(i3)) {
            this.f = e0VarV.c(i3);
            this.h = true;
        }
        e0VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.e != null) {
            int max = this.f987d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i, -i2, i, i2);
                float width = ((this.f987d.getWidth() - this.f987d.getPaddingLeft()) - this.f987d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f987d.getPaddingLeft(), this.f987d.getHeight() / 2);
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
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f987d.getDrawableState())) {
            this.f987d.invalidateDrawable(drawable);
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
            drawable.setCallback(this.f987d);
            androidx.core.graphics.drawable.a.m(drawable, c.g.k.t.z(this.f987d));
            if (drawable.isStateful()) {
                drawable.setState(this.f987d.getDrawableState());
            }
            f();
        }
        this.f987d.invalidate();
    }
}
