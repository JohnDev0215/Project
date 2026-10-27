package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import w5.a;
import z5.k;
import z5.p;

/* JADX INFO: loaded from: classes2.dex */
public class SwitchMaterial extends SwitchCompat {
    public static final int S = R$style.Widget_MaterialComponents_CompoundButton_Switch;
    public static final int[][] T = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public final a U;
    public ColorStateList V;
    public ColorStateList W;
    public boolean X;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.V == null) {
            int iC = t5.a.c(this, R$attr.colorSurface);
            int iC2 = t5.a.c(this, R$attr.colorControlActivated);
            float dimension = getResources().getDimension(R$dimen.mtrl_switch_thumb_elevation);
            if (this.U.d()) {
                dimension += p.g(this);
            }
            int iC3 = this.U.c(iC, dimension);
            int[][] iArr = T;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = t5.a.f(iC, iC2, 1.0f);
            iArr2[1] = iC3;
            iArr2[2] = t5.a.f(iC, iC2, 0.38f);
            iArr2[3] = iC3;
            this.V = new ColorStateList(iArr, iArr2);
        }
        return this.V;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.W == null) {
            int[][] iArr = T;
            int[] iArr2 = new int[iArr.length];
            int iC = t5.a.c(this, R$attr.colorSurface);
            int iC2 = t5.a.c(this, R$attr.colorControlActivated);
            int iC3 = t5.a.c(this, R$attr.colorOnSurface);
            iArr2[0] = t5.a.f(iC, iC2, 0.54f);
            iArr2[1] = t5.a.f(iC, iC3, 0.32f);
            iArr2[2] = t5.a.f(iC, iC2, 0.12f);
            iArr2[3] = t5.a.f(iC, iC3, 0.12f);
            this.W = new ColorStateList(iArr, iArr2);
        }
        return this.W;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.X && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.X && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z7) {
        this.X = z7;
        if (z7) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        } else {
            setThumbTintList(null);
            setTrackTintList(null);
        }
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SwitchMaterial(Context context, AttributeSet attributeSet, int i8) {
        int i9 = S;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        Context context2 = getContext();
        this.U = new a(context2);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.SwitchMaterial, i8, i9, new int[0]);
        this.X = typedArrayH.getBoolean(R$styleable.SwitchMaterial_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
