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
class t extends p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SeekBar f1186d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f1187e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f1188f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private PorterDuff.Mode f1189g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1190h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1191i;

    t(SeekBar seekBar) {
        super(seekBar);
        this.f1188f = null;
        this.f1189g = null;
        this.f1190h = false;
        this.f1191i = false;
        this.f1186d = seekBar;
    }

    private void f() {
        Drawable drawable = this.f1187e;
        if (drawable != null) {
            if (this.f1190h || this.f1191i) {
                Drawable drawableL = a0.a.l(drawable.mutate());
                this.f1187e = drawableL;
                if (this.f1190h) {
                    a0.a.i(drawableL, this.f1188f);
                }
                if (this.f1191i) {
                    a0.a.j(this.f1187e, this.f1189g);
                }
                if (this.f1187e.isStateful()) {
                    this.f1187e.setState(this.f1186d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.p
    void c(AttributeSet attributeSet, int i7) {
        super.c(attributeSet, i7);
        Context context = this.f1186d.getContext();
        int[] iArr = e.j.AppCompatSeekBar;
        s0 s0VarV = s0.v(context, attributeSet, iArr, i7, 0);
        SeekBar seekBar = this.f1186d;
        i0.u.i0(seekBar, seekBar.getContext(), iArr, attributeSet, s0VarV.r(), i7, 0);
        Drawable drawableH = s0VarV.h(e.j.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f1186d.setThumb(drawableH);
        }
        j(s0VarV.g(e.j.AppCompatSeekBar_tickMark));
        int i8 = e.j.AppCompatSeekBar_tickMarkTintMode;
        if (s0VarV.s(i8)) {
            this.f1189g = b0.c(s0VarV.k(i8, -1), this.f1189g);
            this.f1191i = true;
        }
        int i9 = e.j.AppCompatSeekBar_tickMarkTint;
        if (s0VarV.s(i9)) {
            this.f1188f = s0VarV.c(i9);
            this.f1190h = true;
        }
        s0VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.f1187e != null) {
            int max = this.f1186d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1187e.getIntrinsicWidth();
                int intrinsicHeight = this.f1187e.getIntrinsicHeight();
                int i7 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i8 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1187e.setBounds(-i7, -i8, i7, i8);
                float width = ((this.f1186d.getWidth() - this.f1186d.getPaddingLeft()) - this.f1186d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f1186d.getPaddingLeft(), this.f1186d.getHeight() / 2);
                for (int i9 = 0; i9 <= max; i9++) {
                    this.f1187e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void h() {
        Drawable drawable = this.f1187e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1186d.getDrawableState())) {
            this.f1186d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.f1187e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.f1187e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1187e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1186d);
            a0.a.g(drawable, i0.u.y(this.f1186d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1186d.getDrawableState());
            }
            f();
        }
        this.f1186d.invalidate();
    }
}
