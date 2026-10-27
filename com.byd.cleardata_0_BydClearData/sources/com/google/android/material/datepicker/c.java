package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final b f3042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f3043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final b f3044c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final b f3045d;
    final b e;
    final b f;
    final b g;
    final Paint h;

    c(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(b.a.a.a.y.b.c(context, b.a.a.a.b.r, g.class.getCanonicalName()), b.a.a.a.l.S1);
        this.f3042a = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.V1, 0));
        this.g = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.T1, 0));
        this.f3043b = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.U1, 0));
        this.f3044c = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.W1, 0));
        ColorStateList colorStateListA = b.a.a.a.y.c.a(context, typedArrayObtainStyledAttributes, b.a.a.a.l.X1);
        this.f3045d = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.Z1, 0));
        this.e = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.Y1, 0));
        this.f = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.a2, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
