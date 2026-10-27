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
    public final SeekBar f1060d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f1062f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f1063g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1064h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1065i;

    public k(SeekBar seekBar) {
        super(seekBar);
        this.f1062f = null;
        this.f1063g = null;
        this.f1064h = false;
        this.f1065i = false;
        this.f1060d = seekBar;
    }

    @Override // b.b.q.j
    public void c(AttributeSet attributeSet, int i2) {
        super.c(attributeSet, i2);
        Context context = this.f1060d.getContext();
        int[] iArr = b.b.j.X;
        e0 e0VarU = e0.u(context, attributeSet, iArr, i2, 0);
        SeekBar seekBar = this.f1060d;
        b.f.m.s.R(seekBar, seekBar.getContext(), iArr, attributeSet, e0VarU.q(), i2, 0);
        Drawable drawableG = e0VarU.g(b.b.j.Y);
        if (drawableG != null) {
            this.f1060d.setThumb(drawableG);
        }
        j(e0VarU.f(b.b.j.Z));
        int i3 = b.b.j.b0;
        if (e0VarU.r(i3)) {
            this.f1063g = q.e(e0VarU.j(i3, -1), this.f1063g);
            this.f1065i = true;
        }
        int i4 = b.b.j.a0;
        if (e0VarU.r(i4)) {
            this.f1062f = e0VarU.c(i4);
            this.f1064h = true;
        }
        e0VarU.v();
        f();
    }

    public final void f() {
        Drawable drawable = this.f1061e;
        if (drawable != null) {
            if (this.f1064h || this.f1065i) {
                Drawable drawableQ = b.f.g.l.a.q(drawable.mutate());
                this.f1061e = drawableQ;
                if (this.f1064h) {
                    b.f.g.l.a.n(drawableQ, this.f1062f);
                }
                if (this.f1065i) {
                    b.f.g.l.a.o(this.f1061e, this.f1063g);
                }
                if (this.f1061e.isStateful()) {
                    this.f1061e.setState(this.f1060d.getDrawableState());
                }
            }
        }
    }

    public void g(Canvas canvas) {
        if (this.f1061e != null) {
            int max = this.f1060d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1061e.getIntrinsicWidth();
                int intrinsicHeight = this.f1061e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1061e.setBounds(-i2, -i3, i2, i3);
                float width = ((this.f1060d.getWidth() - this.f1060d.getPaddingLeft()) - this.f1060d.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(this.f1060d.getPaddingLeft(), this.f1060d.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f1061e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void h() {
        Drawable drawable = this.f1061e;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1060d.getDrawableState())) {
            this.f1060d.invalidateDrawable(drawable);
        }
    }

    public void i() {
        Drawable drawable = this.f1061e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void j(Drawable drawable) {
        Drawable drawable2 = this.f1061e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1061e = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1060d);
            b.f.g.l.a.l(drawable, b.f.m.s.v(this.f1060d));
            if (drawable.isStateful()) {
                drawable.setState(this.f1060d.getDrawableState());
            }
            f();
        }
        this.f1060d.invalidate();
    }
}
