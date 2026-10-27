package com.google.android.material.checkbox;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.widget.c;
import com.google.android.material.internal.l;
import d.b.a.a.b;
import d.b.a.a.k;
import d.b.a.a.o.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCheckBox extends AppCompatCheckBox {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f3866e = k.Widget_MaterialComponents_CompoundButton_CheckBox;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int[][] f3867f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ColorStateList f3868g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f3869h;

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f3868g == null) {
            int[][] iArr = f3867f;
            int[] iArr2 = new int[iArr.length];
            int iC = a.c(this, b.colorControlActivated);
            int iC2 = a.c(this, b.colorSurface);
            int iC3 = a.c(this, b.colorOnSurface);
            iArr2[0] = a.f(iC2, iC, 1.0f);
            iArr2[1] = a.f(iC2, iC3, 0.54f);
            iArr2[2] = a.f(iC2, iC3, 0.38f);
            iArr2[3] = a.f(iC2, iC3, 0.38f);
            this.f3868g = new ColorStateList(iArr, iArr2);
        }
        return this.f3868g;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3869h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f3869h = z;
        if (z) {
            c.c(this, getMaterialThemeColorsTintList());
        } else {
            c.c(this, null);
        }
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.checkboxStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f3866e;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        Context context2 = getContext();
        TypedArray typedArrayH = l.h(context2, attributeSet, d.b.a.a.l.MaterialCheckBox, i2, i3, new int[0]);
        int i4 = d.b.a.a.l.MaterialCheckBox_buttonTint;
        if (typedArrayH.hasValue(i4)) {
            c.c(this, d.b.a.a.u.c.a(context2, typedArrayH, i4));
        }
        this.f3869h = typedArrayH.getBoolean(d.b.a.a.l.MaterialCheckBox_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
