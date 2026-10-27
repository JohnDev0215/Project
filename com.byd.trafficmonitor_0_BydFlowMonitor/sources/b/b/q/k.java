package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: compiled from: AppCompatSeekBarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SeekBar f1775d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1776e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f1777f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f1778g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1779h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1780i;

    public k(SeekBar seekBar) {
        super(seekBar);
        this.f1777f = null;
        this.f1778g = null;
        this.f1779h = false;
        this.f1780i = false;
        this.f1775d = seekBar;
    }

    @Override // b.b.q.j
    public void c(AttributeSet attributeSet, int i2) {
        super.c(attributeSet, i2);
        Context context = this.f1775d.getContext();
        int[] iArr = b.b.j.AppCompatSeekBar;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i2, 0);
        SeekBar seekBar = this.f1775d;
        b.h.m.t.i0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i2, 0);
        Drawable drawableH = e0VarV.h(b.b.j.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f1775d.setThumb(drawableH);
        }
        j(e0VarV.g(b.b.j.AppCompatSeekBar_tickMark));
        int i3 = b.b.j.AppCompatSeekBar_tickMarkTintMode;
        if (e0VarV.s(i3)) {
            this.f1778g = q.e(e0VarV.k(i3, -1), this.f1778g);
            this.f1780i = true;
        }
        int i4 = b.b.j.AppCompatSeekBar_tickMarkTint;
        if (e0VarV.s(i4)) {
            this.f1777f = e0VarV.c(i4);
            this.f1779h = true;
        }
        e0VarV.w();
        f();
    }

    public final void f() {
        Drawable drawable = this.f1776e;
        if (drawable != null) {
            if (this.f1779h || this.f1780i) {
                Drawable drawableR = b.h.f.l.a.r(drawable.mutate());
                this.f1776e = drawableR;
                if (this.f1779h) {
                    b.h.f.l.a.o(drawableR, this.f1777f);
                }
                if (this.f1780i) {
                    b.h.f.l.a.p(this.f1776e, this.f1778g);
                }
                if (this.f1776e.isStateful()) {
                    this.f1776e.setState(this.f1775d.getDrawableState());
                }
            }
        }
    }

    public void g(Canvas canvas) {
        if (this.f1776e != null) {
            int max = this.f1775d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1776e.getIntrinsicWidth();
                int intrinsicHeight = this.f1776e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1776e.setBounds(-i2, -i3, i2, i3);
                float width = ((this.f1775d.getWidth() - this.f1775d.getPaddingLeft()) - this.f1775d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f1775d.getPaddingLeft(), this.f1775d.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f1776e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void h() {
        Drawable drawable = this.f1776e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1775d.getDrawableState())) {
            this.f1775d.invalidateDrawable(drawable);
        }
    }

    public void i() {
        Drawable drawable = this.f1776e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void j(Drawable drawable) {
        Drawable drawable2 = this.f1776e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1776e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1775d);
            b.h.f.l.a.m(drawable, b.h.m.t.z(this.f1775d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1775d.getDrawableState());
            }
            f();
        }
        this.f1775d.invalidate();
    }
}
