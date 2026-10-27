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
    private final SeekBar f641d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f642e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ColorStateList f643f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private PorterDuff.Mode f644g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f645h;
    private boolean i;

    k(SeekBar seekBar) {
        super(seekBar);
        this.f643f = null;
        this.f644g = null;
        this.f645h = false;
        this.i = false;
        this.f641d = seekBar;
    }

    private void f() {
        Drawable drawable = this.f642e;
        if (drawable != null) {
            if (this.f645h || this.i) {
                Drawable drawableR = androidx.core.graphics.drawable.a.r(drawable.mutate());
                this.f642e = drawableR;
                if (this.f645h) {
                    androidx.core.graphics.drawable.a.o(drawableR, this.f643f);
                }
                if (this.i) {
                    androidx.core.graphics.drawable.a.p(this.f642e, this.f644g);
                }
                if (this.f642e.isStateful()) {
                    this.f642e.setState(this.f641d.getDrawableState());
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.j
    void c(AttributeSet attributeSet, int i) {
        super.c(attributeSet, i);
        Context context = this.f641d.getContext();
        int[] iArr = b.a.j.X;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        SeekBar seekBar = this.f641d;
        b.g.k.t.h0(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        Drawable drawableH = e0VarV.h(b.a.j.Y);
        if (drawableH != null) {
            this.f641d.setThumb(drawableH);
        }
        j(e0VarV.g(b.a.j.Z));
        int i2 = b.a.j.b0;
        if (e0VarV.s(i2)) {
            this.f644g = q.e(e0VarV.k(i2, -1), this.f644g);
            this.i = true;
        }
        int i3 = b.a.j.a0;
        if (e0VarV.s(i3)) {
            this.f643f = e0VarV.c(i3);
            this.f645h = true;
        }
        e0VarV.w();
        f();
    }

    void g(Canvas canvas) {
        if (this.f642e != null) {
            int max = this.f641d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f642e.getIntrinsicWidth();
                int intrinsicHeight = this.f642e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f642e.setBounds(-i, -i2, i, i2);
                float width = ((this.f641d.getWidth() - this.f641d.getPaddingLeft()) - this.f641d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f641d.getPaddingLeft(), this.f641d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f642e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    void h() {
        Drawable drawable = this.f642e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f641d.getDrawableState())) {
            this.f641d.invalidateDrawable(drawable);
        }
    }

    void i() {
        Drawable drawable = this.f642e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    void j(Drawable drawable) {
        Drawable drawable2 = this.f642e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f642e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f641d);
            androidx.core.graphics.drawable.a.m(drawable, b.g.k.t.z(this.f641d));
            if (drawable.isStateful()) {
                drawable.setState(this.f641d.getDrawableState());
            }
            f();
        }
        this.f641d.invalidate();
    }
}
