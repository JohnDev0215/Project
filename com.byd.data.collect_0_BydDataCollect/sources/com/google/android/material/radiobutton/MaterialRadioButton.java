package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.core.widget.c;
import com.google.android.material.internal.l;
import d.b.a.a.b;
import d.b.a.a.k;
import d.b.a.a.o.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f4196e = k.Widget_MaterialComponents_CompoundButton_RadioButton;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int[][] f4197f = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ColorStateList f4198g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f4199h;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f4198g == null) {
            int iC = a.c(this, b.colorControlActivated);
            int iC2 = a.c(this, b.colorOnSurface);
            int iC3 = a.c(this, b.colorSurface);
            int[][] iArr = f4197f;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = a.f(iC3, iC, 1.0f);
            iArr2[1] = a.f(iC3, iC2, 0.54f);
            iArr2[2] = a.f(iC3, iC2, 0.38f);
            iArr2[3] = a.f(iC3, iC2, 0.38f);
            this.f4198g = new ColorStateList(iArr, iArr2);
        }
        return this.f4198g;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f4199h && c.b(this) == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f4199h = z;
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
        int i3 = f4196e;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        Context context2 = getContext();
        TypedArray typedArrayH = l.h(context2, attributeSet, d.b.a.a.l.MaterialRadioButton, i2, i3, new int[0]);
        int i4 = d.b.a.a.l.MaterialRadioButton_buttonTint;
        if (typedArrayH.hasValue(i4)) {
            c.c(this, d.b.a.a.u.c.a(context2, typedArrayH, i4));
        }
        this.f4199h = typedArrayH.getBoolean(d.b.a.a.l.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
