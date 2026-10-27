package com.google.android.material.elevation;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import com.google.android.material.R$attr;
import defpackage.cc1;
import defpackage.gv;
import defpackage.oj2;

/* JADX INFO: loaded from: classes.dex */
public class ElevationOverlayProvider {
    public static final int f = (int) Math.round(5.1000000000000005d);
    public final boolean a;
    public final int b;
    public final int c;
    public final int d;
    public final float e;

    /* JADX WARN: Illegal instructions before constructor call */
    public ElevationOverlayProvider(Context context) {
        TypedValue typedValueA = cc1.a(context, R$attr.elevationOverlayEnabled);
        this((typedValueA == null || typedValueA.type != 18 || typedValueA.data == 0) ? false : true, oj2.o(context, R$attr.elevationOverlayColor, 0), oj2.o(context, R$attr.elevationOverlayAccentColor, 0), oj2.o(context, R$attr.colorSurface, 0), context.getResources().getDisplayMetrics().density);
    }

    public final int a(float f2, int i) {
        int i2;
        float fMin = (this.e <= 0.0f || f2 <= 0.0f) ? 0.0f : Math.min(((((float) Math.log1p(f2 / r0)) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        int iAlpha = Color.alpha(i);
        int iJ = oj2.J(gv.g(i, 255), this.b, fMin);
        if (fMin > 0.0f && (i2 = this.c) != 0) {
            iJ = gv.f(gv.g(i2, f), iJ);
        }
        return gv.g(iJ, iAlpha);
    }

    public final int b(float f2, int i) {
        if (this.a) {
            if (gv.g(i, 255) == this.d) {
                return a(f2, i);
            }
        }
        return i;
    }

    public ElevationOverlayProvider(boolean z, int i, int i2, int i3, float f2) {
        this.a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = f2;
    }
}
