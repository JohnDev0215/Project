package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import r0.c;
import t5.a;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialRadioButton extends AppCompatRadioButton {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f3978g = R$style.Widget_MaterialComponents_CompoundButton_RadioButton;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[][] f3979h = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ColorStateList f3980i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3981j;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f3980i == null) {
            int iC = a.c(this, R$attr.colorControlActivated);
            int iC2 = a.c(this, R$attr.colorOnSurface);
            int iC3 = a.c(this, R$attr.colorSurface);
            int[][] iArr = f3979h;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = a.f(iC3, iC, 1.0f);
            iArr2[1] = a.f(iC3, iC2, 0.54f);
            iArr2[2] = a.f(iC3, iC2, 0.38f);
            iArr2[3] = a.f(iC3, iC2, 0.38f);
            this.f3980i = new ColorStateList(iArr, iArr2);
        }
        return this.f3980i;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3981j && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z7) {
        this.f3981j = z7;
        if (z7) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3978g;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        Context context2 = getContext();
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.MaterialRadioButton, i8, i9, new int[0]);
        int i10 = R$styleable.MaterialRadioButton_buttonTint;
        if (typedArrayH.hasValue(i10)) {
            c.c(this, b6.c.a(context2, typedArrayH, i10));
        }
        this.f3981j = typedArrayH.getBoolean(R$styleable.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
