package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import java.util.Objects;
import spi.gb;
import spi.m3;
import spi.n3;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableStateSeekBar extends SeekBar implements n3 {
    public m3 a;

    public DrawableStateSeekBar(Context context) {
        super(context);
    }

    public static /* synthetic */ int[] a(DrawableStateSeekBar drawableStateSeekBar, Integer num) {
        Objects.requireNonNull(drawableStateSeekBar);
        return super.onCreateDrawableState(num.intValue());
    }

    @Override // spi.n3
    public final void d(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        this.a.d(iArr, iArr2);
    }

    @Override // android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        return this.a.a(i, new gb(this, 7));
    }

    public DrawableStateSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableStateSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public DrawableStateSeekBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
