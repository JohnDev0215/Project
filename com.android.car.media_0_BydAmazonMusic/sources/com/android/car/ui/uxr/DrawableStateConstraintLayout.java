package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Objects;
import spi.gb;
import spi.m3;
import spi.n3;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableStateConstraintLayout extends ConstraintLayout implements n3 {
    public m3 a;

    public DrawableStateConstraintLayout(Context context) {
        super(context);
    }

    public static /* synthetic */ int[] e(DrawableStateConstraintLayout drawableStateConstraintLayout, Integer num) {
        Objects.requireNonNull(drawableStateConstraintLayout);
        return super.onCreateDrawableState(num.intValue());
    }

    @Override // spi.n3
    public final void d(@Nullable int[] iArr, @Nullable int[] iArr2) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        this.a.d(iArr, iArr2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.a == null) {
            this.a = new m3(this);
        }
        return this.a.a(i, new gb(this, 2));
    }

    public DrawableStateConstraintLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DrawableStateConstraintLayout(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
