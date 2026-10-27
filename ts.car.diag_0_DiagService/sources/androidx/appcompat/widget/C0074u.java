package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: androidx.appcompat.widget.u, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class C0074u extends C0071q {
    private final SeekBar d;
    private Drawable e;
    private ColorStateList f;
    private PorterDuff.Mode g;
    private boolean h;
    private boolean i;

    C0074u(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.d = seekBar;
    }

    private void f() {
        if (this.e != null) {
            if (this.h || this.i) {
                Drawable drawableQ = androidx.core.graphics.drawable.a.q(this.e.mutate());
                this.e = drawableQ;
                if (this.h) {
                    androidx.core.graphics.drawable.a.n(drawableQ, this.f);
                }
                if (this.i) {
                    androidx.core.graphics.drawable.a.o(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.C0071q
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        U u = U.u(this.d.getContext(), attributeSet, R$styleable.AppCompatSeekBar, i, 0);
        SeekBar seekBar = this.d;
        ViewCompat.K(seekBar, seekBar.getContext(), R$styleable.AppCompatSeekBar, attributeSet, u.q(), i, 0);
        Drawable drawableG = u.g(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableG != null) {
            this.d.setThumb(drawableG);
        }
        j(u.f(R$styleable.AppCompatSeekBar_tickMark));
        if (u.r(R$styleable.AppCompatSeekBar_tickMarkTintMode)) {
            this.g = C.e(u.j(R$styleable.AppCompatSeekBar_tickMarkTintMode, -1), this.g);
            this.i = true;
        }
        if (u.r(R$styleable.AppCompatSeekBar_tickMarkTint)) {
            this.f = u.c(R$styleable.AppCompatSeekBar_tickMarkTint);
            this.h = true;
        }
        u.v();
        f();
    }

    void g(Canvas canvas) {
        if (this.e != null) {
            int max = this.d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i, -i2, i, i2);
                float width = ((this.d.getWidth() - this.d.getPaddingLeft()) - this.d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.d.getPaddingLeft(), this.d.getHeight() / 2);
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
        if (drawable != null && drawable.isStateful() && drawable.setState(this.d.getDrawableState())) {
            this.d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(@Nullable Drawable drawable) {
        Drawable drawable2 = this.e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.d);
            androidx.core.graphics.drawable.a.l(drawable, ViewCompat.q(this.d));
            if (drawable.isStateful()) {
                drawable.setState(this.d.getDrawableState());
            }
            f();
        }
        this.d.invalidate();
    }
}
