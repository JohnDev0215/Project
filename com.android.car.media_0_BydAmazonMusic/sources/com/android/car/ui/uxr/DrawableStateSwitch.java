package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;
import androidx.annotation.Nullable;
import java.util.Objects;
import spi.gb;
import spi.m3;
import spi.n3;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableStateSwitch extends Switch implements n3 {
    public m3 a;

    public DrawableStateSwitch(Context context) {
        super(context);
    }

    public static /* synthetic */ int[] a(DrawableStateSwitch drawableStateSwitch, Integer num) {
        Objects.requireNonNull(drawableStateSwitch);
        return super.onCreateDrawableState(num.intValue());
    }

    @Override // spi.n3
    public final void d(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        this.a.d(iArr, iArr2);
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        return this.a.a(i, new gb(this, 8));
    }

    public DrawableStateSwitch(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableStateSwitch(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
