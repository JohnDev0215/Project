package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import defpackage.r83;
import defpackage.td;

/* JADX INFO: loaded from: classes.dex */
public final class LinearProgressIndicatorSpec extends td {
    public int g;
    public int h;
    public boolean i;

    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.linearProgressIndicatorStyle);
    }

    @Override // defpackage.td
    public final void a() {
        if (this.g == 0) {
            if (this.b > 0) {
                throw new IllegalArgumentException("Rounded corners are not supported in contiguous indeterminate animation.");
            }
            if (this.c.length < 3) {
                throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
            }
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i) {
        int i2 = LinearProgressIndicator.r;
        super(context, attributeSet, i, i2);
        TypedArray typedArrayD = r83.d(context, attributeSet, R$styleable.LinearProgressIndicator, R$attr.linearProgressIndicatorStyle, i2, new int[0]);
        this.g = typedArrayD.getInt(R$styleable.LinearProgressIndicator_indeterminateAnimationType, 1);
        this.h = typedArrayD.getInt(R$styleable.LinearProgressIndicator_indicatorDirectionLinear, 0);
        typedArrayD.recycle();
        a();
        this.i = this.h == 1;
    }
}
