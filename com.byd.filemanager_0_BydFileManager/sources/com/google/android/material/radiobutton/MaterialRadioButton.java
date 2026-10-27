package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.c;
import c.a.a.a.b;
import c.a.a.a.k;
import c.a.a.a.l;
import c.a.a.a.o.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f4355e = k.x;
    private static final int[][] f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    private ColorStateList g;
    private boolean h;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.g == null) {
            int iC = a.c(this, b.j);
            int iC2 = a.c(this, b.m);
            int iC3 = a.c(this, b.p);
            int[][] iArr = f;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = a.f(iC3, iC, 1.0f);
            iArr2[1] = a.f(iC3, iC2, 0.54f);
            iArr2[2] = a.f(iC3, iC2, 0.38f);
            iArr2[3] = a.f(iC3, iC2, 0.38f);
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

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.C);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = f4355e;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context2, attributeSet, l.V3, i, i2, new int[0]);
        int i3 = l.W3;
        if (typedArrayH.hasValue(i3)) {
            c.c(this, c.a.a.a.t.c.a(context2, typedArrayH, i3));
        }
        this.h = typedArrayH.getBoolean(l.X3, false);
        typedArrayH.recycle();
    }
}
