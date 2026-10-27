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
    private final SeekBar f514d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f515e;
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
        this.f514d = seekBar;
    }

    private void f() {
        Drawable drawable = this.f515e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableR = androidx.core.graphics.drawable.a.r(drawable.mutate());
                this.f515e = drawableR;
                if (this.h) {
                    androidx.core.graphics.drawable.a.o(drawableR, this.f);
                }
                if (this.i) {
                    androidx.core.graphics.drawable.a.p(this.f515e, this.g);
                }
                if (this.f515e.isStateful()) {
                    this.f515e.setState(this.f514d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.j
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        Context context = this.f514d.getContext();
        int[] iArr = b.a.j.X;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.f514d;
        b.g.k.t.h0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        Drawable drawableH = e0VarV.h(b.a.j.Y);
        if (drawableH != null) {
            this.f514d.setThumb(drawableH);
        }
        j(e0VarV.g(b.a.j.Z));
        int i2 = b.a.j.b0;
        if (e0VarV.s(i2)) {
            this.g = q.e(e0VarV.k(i2, -1), this.g);
            this.i = true;
        }
        int i3 = b.a.j.a0;
        if (e0VarV.s(i3)) {
            this.f = e0VarV.c(i3);
            this.h = true;
        }
        e0VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.f515e != null) {
            int max = this.f514d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f515e.getIntrinsicWidth();
                int intrinsicHeight = this.f515e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f515e.setBounds(-i, -i2, i, i2);
                float width = ((this.f514d.getWidth() - this.f514d.getPaddingLeft()) - this.f514d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f514d.getPaddingLeft(), this.f514d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f515e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void h() {
        Drawable drawable = this.f515e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f514d.getDrawableState())) {
            this.f514d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.f515e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.f515e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f515e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f514d);
            androidx.core.graphics.drawable.a.m(drawable, b.g.k.t.z(this.f514d));
            if (drawable.isStateful()) {
                drawable.setState(this.f514d.getDrawableState());
            }
            f();
        }
        this.f514d.invalidate();
    }
}
