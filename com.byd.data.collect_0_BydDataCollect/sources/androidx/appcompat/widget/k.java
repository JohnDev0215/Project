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
    private final SeekBar f660d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f661e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f662f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private PorterDuff.Mode f663g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f664h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f665i;

    k(SeekBar seekBar) {
        super(seekBar);
        this.f662f = null;
        this.f663g = null;
        this.f664h = false;
        this.f665i = false;
        this.f660d = seekBar;
    }

    private void f() {
        Drawable drawable = this.f661e;
        if (drawable != null) {
            if (this.f664h || this.f665i) {
                Drawable drawableR = androidx.core.graphics.drawable.a.r(drawable.mutate());
                this.f661e = drawableR;
                if (this.f664h) {
                    androidx.core.graphics.drawable.a.o(drawableR, this.f662f);
                }
                if (this.f665i) {
                    androidx.core.graphics.drawable.a.p(this.f661e, this.f663g);
                }
                if (this.f661e.isStateful()) {
                    this.f661e.setState(this.f660d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.j
    void c(AttributeSet attributeSet, int i2) {
        super.c(attributeSet, i2);
        Context context = this.f660d.getContext();
        int[] iArr = b.a.j.AppCompatSeekBar;
        f0 f0VarV = f0.v(context, attributeSet, iArr, i2, 0);
        SeekBar seekBar = this.f660d;
        b.g.k.t.h0(seekBar, seekBar.getContext(), iArr, attributeSet, f0VarV.r(), i2, 0);
        Drawable drawableH = f0VarV.h(b.a.j.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f660d.setThumb(drawableH);
        }
        j(f0VarV.g(b.a.j.AppCompatSeekBar_tickMark));
        int i3 = b.a.j.AppCompatSeekBar_tickMarkTintMode;
        if (f0VarV.s(i3)) {
            this.f663g = q.e(f0VarV.k(i3, -1), this.f663g);
            this.f665i = true;
        }
        int i4 = b.a.j.AppCompatSeekBar_tickMarkTint;
        if (f0VarV.s(i4)) {
            this.f662f = f0VarV.c(i4);
            this.f664h = true;
        }
        f0VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.f661e != null) {
            int max = this.f660d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f661e.getIntrinsicWidth();
                int intrinsicHeight = this.f661e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f661e.setBounds(-i2, -i3, i2, i3);
                float width = ((this.f660d.getWidth() - this.f660d.getPaddingLeft()) - this.f660d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f660d.getPaddingLeft(), this.f660d.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f661e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void h() {
        Drawable drawable = this.f661e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f660d.getDrawableState())) {
            this.f660d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.f661e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.f661e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f661e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f660d);
            androidx.core.graphics.drawable.a.m(drawable, b.g.k.t.z(this.f660d));
            if (drawable.isStateful()) {
                drawable.setState(this.f660d.getDrawableState());
            }
            f();
        }
        this.f660d.invalidate();
    }
}
