package a.b.p;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public class u extends q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SeekBar f362d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f363e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f364f;
    public PorterDuff.Mode g;
    public boolean h;
    public boolean i;

    public u(SeekBar seekBar) {
        super(seekBar);
        this.f364f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.f362d = seekBar;
    }

    @Override // a.b.p.q
    public void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        x0 x0VarQ = x0.q(this.f362d.getContext(), attributeSet, a.b.j.AppCompatSeekBar, i, 0);
        SeekBar seekBar = this.f362d;
        a.h.k.q.T(seekBar, seekBar.getContext(), a.b.j.AppCompatSeekBar, attributeSet, x0VarQ.f390b, i, 0);
        Drawable drawableH = x0VarQ.h(a.b.j.AppCompatSeekBar_android_thumb);
        if (drawableH != null) {
            this.f362d.setThumb(drawableH);
        }
        Drawable drawableG = x0VarQ.g(a.b.j.AppCompatSeekBar_tickMark);
        Drawable drawable = this.f363e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f363e = drawableG;
        if (drawableG != null) {
            drawableG.setCallback(this.f362d);
            drawableG.setLayoutDirection(a.h.k.q.p(this.f362d));
            if (drawableG.isStateful()) {
                drawableG.setState(this.f362d.getDrawableState());
            }
            c();
        }
        this.f362d.invalidate();
        if (x0VarQ.o(a.b.j.AppCompatSeekBar_tickMarkTintMode)) {
            this.g = e0.c(x0VarQ.j(a.b.j.AppCompatSeekBar_tickMarkTintMode, -1), this.g);
            this.i = true;
        }
        if (x0VarQ.o(a.b.j.AppCompatSeekBar_tickMarkTint)) {
            this.f364f = x0VarQ.c(a.b.j.AppCompatSeekBar_tickMarkTint);
            this.h = true;
        }
        x0VarQ.f390b.recycle();
        c();
    }

    public final void c() {
        if (this.f363e != null) {
            if (this.h || this.i) {
                Drawable drawableMutate = this.f363e.mutate();
                this.f363e = drawableMutate;
                if (this.h) {
                    drawableMutate.setTintList(this.f364f);
                }
                if (this.i) {
                    this.f363e.setTintMode(this.g);
                }
                if (this.f363e.isStateful()) {
                    this.f363e.setState(this.f362d.getDrawableState());
                }
            }
        }
    }

    public void d(Canvas canvas) {
        if (this.f363e != null) {
            int max = this.f362d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f363e.getIntrinsicWidth();
                int intrinsicHeight = this.f363e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f363e.setBounds(-i, -i2, i, i2);
                float width = ((this.f362d.getWidth() - this.f362d.getPaddingLeft()) - this.f362d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f362d.getPaddingLeft(), this.f362d.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f363e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
