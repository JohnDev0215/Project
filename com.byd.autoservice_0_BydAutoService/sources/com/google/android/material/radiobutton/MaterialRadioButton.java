package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import c2.a;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.k;
import n0.c;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f2397e = R$style.Widget_MaterialComponents_CompoundButton_RadioButton;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[][] f2398f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f2399g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2400h;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f2399g == null) {
            int iC = a.c(this, R$attr.colorControlActivated);
            int iC2 = a.c(this, R$attr.colorOnSurface);
            int iC3 = a.c(this, R$attr.colorSurface);
            int[][] iArr = f2398f;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = a.f(iC3, iC, 1.0f);
            iArr2[1] = a.f(iC3, iC2, 0.54f);
            iArr2[2] = a.f(iC3, iC2, 0.38f);
            iArr2[3] = a.f(iC3, iC2, 0.38f);
            this.f2399g = new ColorStateList(iArr, iArr2);
        }
        return this.f2399g;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f2400h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z3) {
        this.f2400h = z3;
        if (z3) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2397e;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        Context context2 = getContext();
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.MaterialRadioButton, i3, i4, new int[0]);
        int i5 = R$styleable.MaterialRadioButton_buttonTint;
        if (typedArrayH.hasValue(i5)) {
            c.c(this, k2.c.a(context2, typedArrayH, i5));
        }
        this.f2400h = typedArrayH.getBoolean(R$styleable.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
