package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import r0.c;
import t5.a;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialCheckBox extends AppCompatCheckBox {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int f3762g = R$style.Widget_MaterialComponents_CompoundButton_CheckBox;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[][] f3763h = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ColorStateList f3764i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3765j;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f3764i == null) {
            int[][] iArr = f3763h;
            int[] iArr2 = new int[iArr.length];
            int iC = a.c(this, R$attr.colorControlActivated);
            int iC2 = a.c(this, R$attr.colorSurface);
            int iC3 = a.c(this, R$attr.colorOnSurface);
            iArr2[0] = a.f(iC2, iC, 1.0f);
            iArr2[1] = a.f(iC2, iC3, 0.54f);
            iArr2[2] = a.f(iC2, iC3, 0.38f);
            iArr2[3] = a.f(iC2, iC3, 0.38f);
            this.f3764i = new ColorStateList(iArr, iArr2);
        }
        return this.f3764i;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3765j && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z7) {
        this.f3765j = z7;
        if (z7) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3762g;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        Context context2 = getContext();
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.MaterialCheckBox, i8, i9, new int[0]);
        int i10 = R$styleable.MaterialCheckBox_buttonTint;
        if (typedArrayH.hasValue(i10)) {
            c.c(this, b6.c.a(context2, typedArrayH, i10));
        }
        this.f3765j = typedArrayH.getBoolean(R$styleable.MaterialCheckBox_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
