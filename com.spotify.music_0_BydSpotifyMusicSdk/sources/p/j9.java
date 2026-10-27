package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class j9 extends e9 {
    public final SeekBar d;
    public Drawable e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public boolean h;
    public boolean i;

    public j9(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.d = seekBar;
    }

    @Override // p.e9
    public final void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, R.attr.seekBarStyle);
        SeekBar seekBar = this.d;
        Context context = seekBar.getContext();
        int[] iArr = yz1.g;
        v4 v4VarJ = v4.J(context, attributeSet, iArr, R.attr.seekBarStyle);
        zt2.j(seekBar, seekBar.getContext(), iArr, attributeSet, (TypedArray) v4VarJ.k, R.attr.seekBarStyle);
        Drawable drawableU = v4VarJ.u(0);
        if (drawableU != null) {
            seekBar.setThumb(drawableU);
        }
        Drawable drawableT = v4VarJ.t(1);
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.e = drawableT;
        if (drawableT != null) {
            drawableT.setCallback(seekBar);
            za0.b(drawableT, jt2.d(seekBar));
            if (drawableT.isStateful()) {
                drawableT.setState(seekBar.getDrawableState());
            }
            c();
        }
        seekBar.invalidate();
        if (v4VarJ.G(3)) {
            this.g = cb0.b(v4VarJ.w(3, -1), this.g);
            this.i = true;
        }
        if (v4VarJ.G(2)) {
            this.f = v4VarJ.q(2);
            this.h = true;
        }
        v4VarJ.L();
        c();
    }

    public final void c() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableMutate = drawable.mutate();
                this.e = drawableMutate;
                if (this.h) {
                    ya0.h(drawableMutate, this.f);
                }
                if (this.i) {
                    ya0.i(this.e, this.g);
                }
                if (this.e.isStateful()) {
                    this.e.setState(this.d.getDrawableState());
                }
            }
        }
    }

    public final void d(Canvas canvas) {
        if (this.e != null) {
            int max = this.d.getMax();
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.e.setBounds(-i, -i2, i, i2);
                float width = ((r0.getWidth() - r0.getPaddingLeft()) - r0.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(r0.getPaddingLeft(), r0.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    this.e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
