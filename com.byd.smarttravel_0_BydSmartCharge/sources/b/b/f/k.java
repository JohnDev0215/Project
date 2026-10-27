package b.b.f;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatSeekBarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SeekBar f800d;
    public Drawable e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public boolean h;
    public boolean i;

    public k(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.f800d = seekBar;
    }

    @Override // b.b.f.j
    public void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        Context context = this.f800d.getContext();
        int[] iArr = R$styleable.AppCompatSeekBar;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.f800d;
        b.g.j.t.h0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        Drawable drawableH = e0VarV.h(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f800d.setThumb(drawableH);
        }
        j(e0VarV.g(R$styleable.AppCompatSeekBar_tickMark));
        int i2 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        if (e0VarV.s(i2)) {
            this.g = q.e(e0VarV.k(i2, -1), this.g);
            this.i = true;
        }
        int i3 = R$styleable.AppCompatSeekBar_tickMarkTint;
        if (e0VarV.s(i3)) {
            this.f = e0VarV.c(i3);
            this.h = true;
        }
        e0VarV.w();
        f();
    }

    public final void f() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableR = b.g.c.l.a.r(drawable.mutate());
                this.e = drawableR;
                if (this.h) {
                    b.g.c.l.a.o(drawableR, this.f);
                }
                if (this.i) {
                    b.g.c.l.a.p(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.f800d.getDrawableState());
                }
            }
        }
    }

    public void g(Canvas canvas) {
        if (this.e != null) {
            int max = this.f800d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i, -i2, i, i2);
                float width = ((this.f800d.getWidth() - this.f800d.getPaddingLeft()) - this.f800d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f800d.getPaddingLeft(), this.f800d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void h() {
        Drawable drawable = this.e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f800d.getDrawableState())) {
            this.f800d.invalidateDrawable(drawable);
        }
    }

    public void i() {
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void j(Drawable drawable) {
        Drawable drawable2 = this.e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f800d);
            b.g.c.l.a.m(drawable, b.g.j.t.z(this.f800d));
            if (drawable.isStateful()) {
                drawable.setState(this.f800d.getDrawableState());
            }
            f();
        }
        this.f800d.invalidate();
    }
}
