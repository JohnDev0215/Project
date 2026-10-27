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
import defpackage.bi;
import defpackage.fl;
import defpackage.jj;
import defpackage.ka;
import defpackage.sj;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {
    public static final int e = R$style.Widget_MaterialComponents_CompoundButton_CheckBox;
    public static final int[][] f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList g;
    public boolean h;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.g == null) {
            int[][] iArr = f;
            int[] iArr2 = new int[iArr.length];
            int iC = bi.c(this, R$attr.colorControlActivated);
            int iC2 = bi.c(this, R$attr.colorSurface);
            int iC3 = bi.c(this, R$attr.colorOnSurface);
            iArr2[0] = bi.f(iC2, iC, 1.0f);
            iArr2[1] = bi.f(iC2, iC3, 0.54f);
            iArr2[2] = bi.f(iC2, iC3, 0.38f);
            iArr2[3] = bi.f(iC2, iC3, 0.38f);
            this.g = new ColorStateList(iArr, iArr2);
        }
        return this.g;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.h && ka.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.h = z;
        if (z) {
            ka.c(this, getMaterialThemeColorsTintList());
        } else {
            ka.c(this, null);
        }
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i) {
        int i2 = e;
        super(fl.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayH = jj.h(context2, attributeSet, R$styleable.MaterialCheckBox, i, i2, new int[0]);
        int i3 = R$styleable.MaterialCheckBox_buttonTint;
        if (typedArrayH.hasValue(i3)) {
            ka.c(this, sj.a(context2, typedArrayH, i3));
        }
        this.h = typedArrayH.getBoolean(R$styleable.MaterialCheckBox_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
