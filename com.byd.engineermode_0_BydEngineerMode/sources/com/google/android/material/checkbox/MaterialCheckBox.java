package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.widget.c;
import c.b.a.a.b;
import c.b.a.a.k;
import c.b.a.a.l;
import c.b.a.a.o.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    private static final int e = k.w;
    private static final int[][] f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    private ColorStateList g;
    private boolean h;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.g == null) {
            int[][] iArr = f;
            int[] iArr2 = new int[iArr.length];
            int iC = a.c(this, b.j);
            int iC2 = a.c(this, b.p);
            int iC3 = a.c(this, b.m);
            iArr2[0] = a.f(iC2, iC, 1.0f);
            iArr2[1] = a.f(iC2, iC3, 0.54f);
            iArr2[2] = a.f(iC2, iC3, 0.38f);
            iArr2[3] = a.f(iC2, iC3, 0.38f);
            this.g = new ColorStateList(iArr, iArr2);
        }
        return this.g;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.h = z;
        if (z) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.g);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i) {
        int i2 = e;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context2, attributeSet, l.S3, i, i2, new int[0]);
        int i3 = l.T3;
        if (typedArrayH.hasValue(i3)) {
            c.c(this, c.b.a.a.t.c.a(context2, typedArrayH, i3));
        }
        this.h = typedArrayH.getBoolean(l.U3, false);
        typedArrayH.recycle();
    }
}
