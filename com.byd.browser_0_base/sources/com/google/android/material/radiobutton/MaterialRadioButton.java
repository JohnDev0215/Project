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
import defpackage.oj2;
import defpackage.r83;
import defpackage.rc1;
import defpackage.uc1;

/* JADX INFO: loaded from: classes.dex */
public class MaterialRadioButton extends AppCompatRadioButton {
    public static final int k = R$style.Widget_MaterialComponents_CompoundButton_RadioButton;
    public static final int[][] l = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public ColorStateList i;
    public boolean j;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.i == null) {
            int iP = oj2.p(this, R$attr.colorControlActivated);
            int iP2 = oj2.p(this, R$attr.colorOnSurface);
            int iP3 = oj2.p(this, R$attr.colorSurface);
            this.i = new ColorStateList(l, new int[]{oj2.J(iP3, iP, 1.0f), oj2.J(iP3, iP2, 0.54f), oj2.J(iP3, iP2, 0.38f), oj2.J(iP3, iP2, 0.38f)});
        }
        return this.i;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.j && getButtonTintList() == null) {
            setUseMaterialThemeColors(true);
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.j = z;
        if (z) {
            setButtonTintList(getMaterialThemeColorsTintList());
        } else {
            setButtonTintList(null);
        }
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = k;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.MaterialRadioButton, i, i2, new int[0]);
        int i3 = R$styleable.MaterialRadioButton_buttonTint;
        if (typedArrayD.hasValue(i3)) {
            setButtonTintList(rc1.a(context2, typedArrayD, i3));
        }
        this.j = typedArrayD.getBoolean(R$styleable.MaterialRadioButton_useMaterialThemeColors, false);
        typedArrayD.recycle();
    }
}
