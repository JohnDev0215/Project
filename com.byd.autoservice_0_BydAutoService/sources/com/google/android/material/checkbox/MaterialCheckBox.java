package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import c2.a;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.k;
import n0.c;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f2179e = R$style.Widget_MaterialComponents_CompoundButton_CheckBox;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[][] f2180f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f2181g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2182h;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f2181g == null) {
            int[][] iArr = f2180f;
            int[] iArr2 = new int[iArr.length];
            int iC = a.c(this, R$attr.colorControlActivated);
            int iC2 = a.c(this, R$attr.colorSurface);
            int iC3 = a.c(this, R$attr.colorOnSurface);
            iArr2[0] = a.f(iC2, iC, 1.0f);
            iArr2[1] = a.f(iC2, iC3, 0.54f);
            iArr2[2] = a.f(iC2, iC3, 0.38f);
            iArr2[3] = a.f(iC2, iC3, 0.38f);
            this.f2181g = new ColorStateList(iArr, iArr2);
        }
        return this.f2181g;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f2182h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z3) {
        this.f2182h = z3;
        if (z3) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2179e;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        Context context2 = getContext();
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.MaterialCheckBox, i3, i4, new int[0]);
        int i5 = R$styleable.MaterialCheckBox_buttonTint;
        if (typedArrayH.hasValue(i5)) {
            c.c(this, k2.c.a(context2, typedArrayH, i5));
        }
        this.f2182h = typedArrayH.getBoolean(R$styleable.MaterialCheckBox_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
