package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import c.b.a.a.b;
import c.b.a.a.d;
import c.b.a.a.k;
import c.b.a.a.l;
import c.b.a.a.q.a;
import com.google.android.material.internal.p;

/* JADX INFO: loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {
    private static final int Q = k.y;
    private static final int[][] R = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    private final a S;
    private ColorStateList T;
    private ColorStateList U;
    private boolean V;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.T == null) {
            int iC = c.b.a.a.o.a.c(this, b.p);
            int iC2 = c.b.a.a.o.a.c(this, b.j);
            float dimension = getResources().getDimension(d.O);
            if (this.S.d()) {
                dimension += p.g(this);
            }
            int iC3 = this.S.c(iC, dimension);
            int[][] iArr = R;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = c.b.a.a.o.a.f(iC, iC2, 1.0f);
            iArr2[1] = iC3;
            iArr2[2] = c.b.a.a.o.a.f(iC, iC2, 0.38f);
            iArr2[3] = iC3;
            this.T = new ColorStateList(iArr, iArr2);
        }
        return this.T;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.U == null) {
            int[][] iArr = R;
            int[] iArr2 = new int[iArr.length];
            int iC = c.b.a.a.o.a.c(this, b.p);
            int iC2 = c.b.a.a.o.a.c(this, b.j);
            int iC3 = c.b.a.a.o.a.c(this, b.m);
            iArr2[0] = c.b.a.a.o.a.f(iC, iC2, 0.54f);
            iArr2[1] = c.b.a.a.o.a.f(iC, iC3, 0.32f);
            iArr2[2] = c.b.a.a.o.a.f(iC, iC2, 0.12f);
            iArr2[3] = c.b.a.a.o.a.f(iC, iC3, 0.12f);
            this.U = new ColorStateList(iArr, iArr2);
        }
        return this.U;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
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
        this(context, attributeSet, b.J);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SwitchMaterial(Context context, AttributeSet attributeSet, int i) {
        int i2 = Q;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.S = new a(context2);
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context2, attributeSet, l.M5, i, i2, new int[0]);
        this.V = typedArrayH.getBoolean(l.N5, false);
        typedArrayH.recycle();
    }
}
