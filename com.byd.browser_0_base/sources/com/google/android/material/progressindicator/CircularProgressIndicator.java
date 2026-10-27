package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.c90;
import defpackage.ft;
import defpackage.jt;
import defpackage.oz0;
import defpackage.td;

/* JADX INFO: loaded from: classes.dex */
public final class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int r = R$style.Widget_MaterialComponents_CircularProgressIndicator;

    public CircularProgressIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final td a(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.e).i;
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.e).h;
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.e).g;
    }

    public void setIndicatorDirection(int i) {
        ((CircularProgressIndicatorSpec) this.e).i = i;
        invalidate();
    }

    public void setIndicatorInset(int i) {
        Object obj = this.e;
        if (((CircularProgressIndicatorSpec) obj).h != i) {
            ((CircularProgressIndicatorSpec) obj).h = i;
            invalidate();
        }
    }

    public void setIndicatorSize(int i) {
        int iMax = Math.max(i, getTrackThickness() * 2);
        Object obj = this.e;
        if (((CircularProgressIndicatorSpec) obj).g != iMax) {
            ((CircularProgressIndicatorSpec) obj).g = iMax;
            ((CircularProgressIndicatorSpec) obj).getClass();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i) {
        super.setTrackThickness(i);
        ((CircularProgressIndicatorSpec) this.e).getClass();
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, r);
        Context context2 = getContext();
        CircularProgressIndicatorSpec circularProgressIndicatorSpec = (CircularProgressIndicatorSpec) this.e;
        setIndeterminateDrawable(new oz0(context2, circularProgressIndicatorSpec, new ft(circularProgressIndicatorSpec), new jt(circularProgressIndicatorSpec)));
        setProgressDrawable(new c90(getContext(), circularProgressIndicatorSpec, new ft(circularProgressIndicatorSpec)));
    }
}
