package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import b.h.n.c;
import c.b.a.a.b;
import c.b.a.a.k;
import c.b.a.a.l;
import c.b.a.a.w.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f4791e = k.Widget_MaterialComponents_CompoundButton_RadioButton;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[][] f4792f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f4793g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4794h;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f4793g == null) {
            int iC = a.c(this, b.colorControlActivated);
            int iC2 = a.c(this, b.colorOnSurface);
            int iC3 = a.c(this, b.colorSurface);
            int[][] iArr = f4792f;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = a.f(iC3, iC, 1.0f);
            iArr2[1] = a.f(iC3, iC2, 0.54f);
            iArr2[2] = a.f(iC3, iC2, 0.38f);
            iArr2[3] = a.f(iC3, iC2, 0.38f);
            this.f4793g = new ColorStateList(iArr, iArr2);
        }
        return this.f4793g;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f4794h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f4794h = z;
        if (z) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f4791e;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        Context context2 = getContext();
        TypedArray typedArrayH = c.b.a.a.d0.k.h(context2, attributeSet, l.MaterialRadioButton, i2, i3, new int[0]);
        int i4 = l.MaterialRadioButton_buttonTint;
        if (typedArrayH.hasValue(i4)) {
            c.c(this, c.b.a.a.f0.c.a(context2, typedArrayH, i4));
        }
        this.f4794h = typedArrayH.getBoolean(l.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
