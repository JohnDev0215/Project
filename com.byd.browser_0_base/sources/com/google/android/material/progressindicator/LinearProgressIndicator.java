package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import defpackage.c90;
import defpackage.i71;
import defpackage.in3;
import defpackage.l71;
import defpackage.nz0;
import defpackage.o71;
import defpackage.om3;
import defpackage.oz0;
import defpackage.td;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class LinearProgressIndicator extends BaseProgressIndicator<LinearProgressIndicatorSpec> {
    public static final int r = R$style.Widget_MaterialComponents_LinearProgressIndicator;

    public LinearProgressIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final td a(Context context, AttributeSet attributeSet) {
        return new LinearProgressIndicatorSpec(context, attributeSet);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public final void b(int i, boolean z) {
        Object obj = this.e;
        if (obj != null && ((LinearProgressIndicatorSpec) obj).g == 0 && isIndeterminate()) {
            return;
        }
        super.b(i, z);
    }

    public int getIndeterminateAnimationType() {
        return ((LinearProgressIndicatorSpec) this.e).g;
    }

    public int getIndicatorDirection() {
        return ((LinearProgressIndicatorSpec) this.e).h;
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Object obj = this.e;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) obj;
        boolean z2 = true;
        if (((LinearProgressIndicatorSpec) obj).h != 1) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if ((om3.e.c(this) != 1 || ((LinearProgressIndicatorSpec) obj).h != 2) && (om3.e.c(this) != 0 || ((LinearProgressIndicatorSpec) obj).h != 3)) {
                z2 = false;
            }
        }
        linearProgressIndicatorSpec.i = z2;
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingRight = i - (getPaddingRight() + getPaddingLeft());
        int paddingBottom = i2 - (getPaddingBottom() + getPaddingTop());
        oz0<LinearProgressIndicatorSpec> indeterminateDrawable = getIndeterminateDrawable();
        if (indeterminateDrawable != null) {
            indeterminateDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
        c90<LinearProgressIndicatorSpec> progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            progressDrawable.setBounds(0, 0, paddingRight, paddingBottom);
        }
    }

    public void setIndeterminateAnimationType(int i) {
        Object obj = this.e;
        if (((LinearProgressIndicatorSpec) obj).g == i) {
            return;
        }
        if (c() && isIndeterminate()) {
            throw new IllegalStateException("Cannot change indeterminate animation type while the progress indicator is show in indeterminate mode.");
        }
        ((LinearProgressIndicatorSpec) obj).g = i;
        ((LinearProgressIndicatorSpec) obj).a();
        if (i == 0) {
            oz0<LinearProgressIndicatorSpec> indeterminateDrawable = getIndeterminateDrawable();
            l71 l71Var = new l71((LinearProgressIndicatorSpec) obj);
            indeterminateDrawable.q = l71Var;
            l71Var.a = indeterminateDrawable;
        } else {
            oz0<LinearProgressIndicatorSpec> indeterminateDrawable2 = getIndeterminateDrawable();
            o71 o71Var = new o71(getContext(), (LinearProgressIndicatorSpec) obj);
            indeterminateDrawable2.q = o71Var;
            o71Var.a = indeterminateDrawable2;
        }
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setIndicatorColor(int... iArr) {
        super.setIndicatorColor(iArr);
        ((LinearProgressIndicatorSpec) this.e).a();
    }

    public void setIndicatorDirection(int i) {
        Object obj = this.e;
        ((LinearProgressIndicatorSpec) obj).h = i;
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) obj;
        boolean z = true;
        if (i != 1) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if ((om3.e.c(this) != 1 || ((LinearProgressIndicatorSpec) obj).h != 2) && (om3.e.c(this) != 0 || i != 3)) {
                z = false;
            }
        }
        linearProgressIndicatorSpec.i = z;
        invalidate();
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackCornerRadius(int i) {
        super.setTrackCornerRadius(i);
        ((LinearProgressIndicatorSpec) this.e).a();
        invalidate();
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.linearProgressIndicatorStyle);
    }

    public LinearProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        nz0 o71Var;
        super(context, attributeSet, i, r);
        Context context2 = getContext();
        LinearProgressIndicatorSpec linearProgressIndicatorSpec = (LinearProgressIndicatorSpec) this.e;
        i71 i71Var = new i71(linearProgressIndicatorSpec);
        if (linearProgressIndicatorSpec.g == 0) {
            o71Var = new l71(linearProgressIndicatorSpec);
        } else {
            o71Var = new o71(context2, linearProgressIndicatorSpec);
        }
        setIndeterminateDrawable(new oz0(context2, linearProgressIndicatorSpec, i71Var, o71Var));
        setProgressDrawable(new c90(getContext(), linearProgressIndicatorSpec, new i71(linearProgressIndicatorSpec)));
    }
}
