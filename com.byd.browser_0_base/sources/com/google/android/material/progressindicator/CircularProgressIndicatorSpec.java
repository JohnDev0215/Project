package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import defpackage.r83;
import defpackage.rc1;
import defpackage.td;

/* JADX INFO: loaded from: classes.dex */
public final class CircularProgressIndicatorSpec extends td {
    public int g;
    public int h;
    public int i;

    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CircularProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i) {
        int i2 = CircularProgressIndicator.r;
        super(context, attributeSet, i, i2);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_circular_size_medium);
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.mtrl_progress_circular_inset_medium);
        TypedArray typedArrayD = r83.d(context, attributeSet, R$styleable.CircularProgressIndicator, i, i2, new int[0]);
        this.g = Math.max(rc1.c(context, typedArrayD, R$styleable.CircularProgressIndicator_indicatorSize, dimensionPixelSize), this.a * 2);
        this.h = rc1.c(context, typedArrayD, R$styleable.CircularProgressIndicator_indicatorInset, dimensionPixelSize2);
        this.i = typedArrayD.getInt(R$styleable.CircularProgressIndicator_indicatorDirectionCircular, 0);
        typedArrayD.recycle();
    }

    @Override // defpackage.td
    public final void a() {
    }
}
