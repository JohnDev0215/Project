package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import c.b.a.a.n.a;
import c.b.a.a.q.k;
import c.b.a.a.q.p;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {
    public static final int Q = R$style.Widget_MaterialComponents_CompoundButton_Switch;
    public static final int[][] R = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public final a S;
    public ColorStateList T;
    public ColorStateList U;
    public boolean V;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.T == null) {
            int iC = c.b.a.a.k.a.c(this, R$attr.colorSurface);
            int iC2 = c.b.a.a.k.a.c(this, R$attr.colorControlActivated);
            float dimension = getResources().getDimension(R$dimen.mtrl_switch_thumb_elevation);
            if (this.S.d()) {
                dimension += p.g(this);
            }
            int iC3 = this.S.c(iC, dimension);
            int[][] iArr = R;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = c.b.a.a.k.a.f(iC, iC2, 1.0f);
            iArr2[1] = iC3;
            iArr2[2] = c.b.a.a.k.a.f(iC, iC2, 0.38f);
            iArr2[3] = iC3;
            this.T = new ColorStateList(iArr, iArr2);
        }
        return this.T;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.U == null) {
            int[][] iArr = R;
            int[] iArr2 = new int[iArr.length];
            int iC = c.b.a.a.k.a.c(this, R$attr.colorSurface);
            int iC2 = c.b.a.a.k.a.c(this, R$attr.colorControlActivated);
            int iC3 = c.b.a.a.k.a.c(this, R$attr.colorOnSurface);
            iArr2[0] = c.b.a.a.k.a.f(iC, iC2, 0.54f);
            iArr2[1] = c.b.a.a.k.a.f(iC, iC3, 0.32f);
            iArr2[2] = c.b.a.a.k.a.f(iC, iC2, 0.12f);
            iArr2[3] = c.b.a.a.k.a.f(iC, iC3, 0.12f);
            this.U = new ColorStateList(iArr, iArr2);
        }
        return this.U;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.V && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.V && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.V = z;
        if (z) {
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
    public SwitchMaterial(Context context, AttributeSet attributeSet, int i) {
        int i2 = Q;
        super(c.b.a.a.z.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.S = new a(context2);
        TypedArray typedArrayH = k.h(context2, attributeSet, R$styleable.SwitchMaterial, i, i2, new int[0]);
        this.V = typedArrayH.getBoolean(R$styleable.SwitchMaterial_useMaterialThemeColors, false);
        typedArrayH.recycle();
    }
}
