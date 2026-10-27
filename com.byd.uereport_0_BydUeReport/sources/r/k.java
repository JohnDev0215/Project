package r;

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
    public final SeekBar f7576d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f7577e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f7578f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f7579g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7580h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f7581i;

    public k(SeekBar seekBar) {
        super(seekBar);
        this.f7578f = null;
        this.f7579g = null;
        this.f7580h = false;
        this.f7581i = false;
        this.f7576d = seekBar;
    }

    @Override // r.j
    public void c(AttributeSet attributeSet, int i8) {
        super.c(attributeSet, i8);
        Context context = this.f7576d.getContext();
        int[] iArr = R$styleable.AppCompatSeekBar;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i8, 0);
        SeekBar seekBar = this.f7576d;
        o0.t.j0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i8, 0);
        Drawable drawableH = e0VarV.h(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f7576d.setThumb(drawableH);
        }
        j(e0VarV.g(R$styleable.AppCompatSeekBar_tickMark));
        int i9 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        if (e0VarV.s(i9)) {
            this.f7579g = q.e(e0VarV.k(i9, -1), this.f7579g);
            this.f7581i = true;
        }
        int i10 = R$styleable.AppCompatSeekBar_tickMarkTint;
        if (e0VarV.s(i10)) {
            this.f7578f = e0VarV.c(i10);
            this.f7580h = true;
        }
        e0VarV.w();
        f();
    }

    public final void f() {
        Drawable drawable = this.f7577e;
        if (drawable != null) {
            if (this.f7580h || this.f7581i) {
                Drawable drawableR = h0.a.r(drawable.mutate());
                this.f7577e = drawableR;
                if (this.f7580h) {
                    h0.a.o(drawableR, this.f7578f);
                }
                if (this.f7581i) {
                    h0.a.p(this.f7577e, this.f7579g);
                }
                if (this.f7577e.isStateful()) {
                    this.f7577e.setState(this.f7576d.getDrawableState());
                }
            }
        }
    }

    public void g(Canvas canvas) {
        if (this.f7577e != null) {
            int max = this.f7576d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f7577e.getIntrinsicWidth();
                int intrinsicHeight = this.f7577e.getIntrinsicHeight();
                int i8 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i9 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f7577e.setBounds(-i8, -i9, i8, i9);
                float width = ((this.f7576d.getWidth() - this.f7576d.getPaddingLeft()) - this.f7576d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f7576d.getPaddingLeft(), this.f7576d.getHeight() / 2);
                for (int i10 = 0; i10 <= max; i10++) {
                    this.f7577e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void h() {
        Drawable drawable = this.f7577e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f7576d.getDrawableState())) {
            this.f7576d.invalidateDrawable(drawable);
        }
    }

    public void i() {
        Drawable drawable = this.f7577e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void j(Drawable drawable) {
        Drawable drawable2 = this.f7577e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f7577e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f7576d);
            h0.a.m(drawable, o0.t.A(this.f7576d));
            if (drawable.isStateful()) {
                drawable.setState(this.f7576d.getDrawableState());
            }
            f();
        }
        this.f7576d.invalidate();
    }
}
