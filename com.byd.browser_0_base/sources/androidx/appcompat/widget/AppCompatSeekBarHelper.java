package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import androidx.appcompat.R;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    public final SeekBar d;
    public Drawable e;
    public ColorStateList f;
    public PorterDuff.Mode g;
    public boolean h;
    public boolean i;

    public AppCompatSeekBarHelper(SeekBar seekBar) {
        super(seekBar);
        this.f = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.d = seekBar;
    }

    @Override // androidx.appcompat.widget.AppCompatProgressBarHelper
    public final void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        SeekBar seekBar = this.d;
        Context context = seekBar.getContext();
        int[] iArr = R.styleable.AppCompatSeekBar;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(context, attributeSet, iArr, i);
        Context context2 = seekBar.getContext();
        TypedArray typedArray = tintTypedArrayM.b;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(seekBar, context2, iArr, attributeSet, typedArray, i, 0);
        Drawable drawableF = tintTypedArrayM.f(R.styleable.AppCompatSeekBar_android_thumb);
        if (drawableF != null) {
            seekBar.setThumb(drawableF);
        }
        Drawable drawableE = tintTypedArrayM.e(R.styleable.AppCompatSeekBar_tickMark);
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.e = drawableE;
        if (drawableE != null) {
            drawableE.setCallback(seekBar);
            drawableE.setLayoutDirection(om3.e.c(seekBar));
            if (drawableE.isStateful()) {
                drawableE.setState(seekBar.getDrawableState());
            }
            c();
        }
        seekBar.invalidate();
        int i2 = R.styleable.AppCompatSeekBar_tickMarkTintMode;
        if (tintTypedArrayM.l(i2)) {
            this.g = DrawableUtils.c(tintTypedArrayM.h(i2, -1), this.g);
            this.i = true;
        }
        int i3 = R.styleable.AppCompatSeekBar_tickMarkTint;
        if (tintTypedArrayM.l(i3)) {
            this.f = tintTypedArrayM.b(i3);
            this.h = true;
        }
        tintTypedArrayM.n();
        c();
    }

    public final void c() {
        Drawable drawable = this.e;
        if (drawable != null) {
            if (this.h || this.i) {
                Drawable drawableMutate = drawable.mutate();
                this.e = drawableMutate;
                if (this.h) {
                    drawableMutate.setTintList(this.f);
                }
                if (this.i) {
                    this.e.setTintMode(this.g);
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
