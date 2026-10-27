package o;

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
    public final SeekBar f4317d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f4318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f4319f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f4320g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4321h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4322i;

    public k(SeekBar seekBar) {
        super(seekBar);
        this.f4319f = null;
        this.f4320g = null;
        this.f4321h = false;
        this.f4322i = false;
        this.f4317d = seekBar;
    }

    @Override // o.j
    public void c(AttributeSet attributeSet, int i3) {
        super.c(attributeSet, i3);
        Context context = this.f4317d.getContext();
        int[] iArr = R$styleable.AppCompatSeekBar;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i3, 0);
        SeekBar seekBar = this.f4317d;
        l0.t.h0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i3, 0);
        Drawable drawableH = e0VarV.h(R$styleable.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f4317d.setThumb(drawableH);
        }
        j(e0VarV.g(R$styleable.AppCompatSeekBar_tickMark));
        int i4 = R$styleable.AppCompatSeekBar_tickMarkTintMode;
        if (e0VarV.s(i4)) {
            this.f4320g = q.e(e0VarV.k(i4, -1), this.f4320g);
            this.f4322i = true;
        }
        int i5 = R$styleable.AppCompatSeekBar_tickMarkTint;
        if (e0VarV.s(i5)) {
            this.f4319f = e0VarV.c(i5);
            this.f4321h = true;
        }
        e0VarV.w();
        f();
    }

    public final void f() {
        Drawable drawable = this.f4318e;
        if (drawable != null) {
            if (this.f4321h || this.f4322i) {
                Drawable drawableR = e0.a.r(drawable.mutate());
                this.f4318e = drawableR;
                if (this.f4321h) {
                    e0.a.o(drawableR, this.f4319f);
                }
                if (this.f4322i) {
                    e0.a.p(this.f4318e, this.f4320g);
                }
                if (this.f4318e.isStateful()) {
                    this.f4318e.setState(this.f4317d.getDrawableState());
                }
            }
        }
    }

    public void g(Canvas canvas) {
        if (this.f4318e != null) {
            int max = this.f4317d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f4318e.getIntrinsicWidth();
                int intrinsicHeight = this.f4318e.getIntrinsicHeight();
                int i3 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i4 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f4318e.setBounds(-i3, -i4, i3, i4);
                float width = ((this.f4317d.getWidth() - this.f4317d.getPaddingLeft()) - this.f4317d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f4317d.getPaddingLeft(), this.f4317d.getHeight() / 2);
                for (int i5 = 0; i5 <= max; i5++) {
                    this.f4318e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void h() {
        Drawable drawable = this.f4318e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f4317d.getDrawableState())) {
            this.f4317d.invalidateDrawable(drawable);
        }
    }

    public void i() {
        Drawable drawable = this.f4318e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void j(Drawable drawable) {
        Drawable drawable2 = this.f4318e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f4318e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f4317d);
            e0.a.m(drawable, l0.t.z(this.f4317d));
            if (drawable.isStateful()) {
                drawable.setState(this.f4317d.getDrawableState());
            }
            f();
        }
        this.f4317d.invalidate();
    }
}
