package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.elevation.ElevationOverlayProvider;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.r83;
import defpackage.uc1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {
    public static final int e0 = R$style.Widget_MaterialComponents_CompoundButton_Switch;
    public static final int[][] f0 = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public final ElevationOverlayProvider a0;
    public ColorStateList b0;
    public ColorStateList c0;
    public boolean d0;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.b0 == null) {
            int iP = oj2.p(this, R$attr.colorSurface);
            int iP2 = oj2.p(this, R$attr.colorControlActivated);
            float dimension = getResources().getDimension(R$dimen.mtrl_switch_thumb_elevation);
            ElevationOverlayProvider elevationOverlayProvider = this.a0;
            if (elevationOverlayProvider.a) {
                float fE = 0.0f;
                for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    fE += om3.i.e((View) parent);
                }
                dimension += fE;
            }
            int iB = elevationOverlayProvider.b(dimension, iP);
            this.b0 = new ColorStateList(f0, new int[]{oj2.J(iP, iP2, 1.0f), iB, oj2.J(iP, iP2, 0.38f), iB});
        }
        return this.b0;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.c0 == null) {
            int iP = oj2.p(this, R$attr.colorSurface);
            int iP2 = oj2.p(this, R$attr.colorControlActivated);
            int iP3 = oj2.p(this, R$attr.colorOnSurface);
            this.c0 = new ColorStateList(f0, new int[]{oj2.J(iP, iP2, 0.54f), oj2.J(iP, iP3, 0.32f), oj2.J(iP, iP2, 0.12f), oj2.J(iP, iP3, 0.12f)});
        }
        return this.c0;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d0 && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.d0 && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.d0 = z;
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
        int i2 = e0;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.a0 = new ElevationOverlayProvider(context2);
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.SwitchMaterial, i, i2, new int[0]);
        this.d0 = typedArrayD.getBoolean(R$styleable.SwitchMaterial_useMaterialThemeColors, false);
        typedArrayD.recycle();
    }
}
