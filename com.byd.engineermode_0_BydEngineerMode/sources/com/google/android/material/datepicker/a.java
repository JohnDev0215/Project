package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.widget.TextView;
import b.g.k.t;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Rect f2709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f2710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f2711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f2712d;
    private final int e;
    private final c.b.a.a.w.m f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, c.b.a.a.w.m mVar, Rect rect) {
        b.g.j.h.b(rect.left);
        b.g.j.h.b(rect.top);
        b.g.j.h.b(rect.right);
        b.g.j.h.b(rect.bottom);
        this.f2709a = rect;
        this.f2710b = colorStateList2;
        this.f2711c = colorStateList;
        this.f2712d = colorStateList3;
        this.e = i;
        this.f = mVar;
    }

    static a a(Context context, int i) {
        b.g.j.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, c.b.a.a.l.z3);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.A3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.C3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.B3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.D3, 0));
        ColorStateList colorStateListA = c.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.E3);
        ColorStateList colorStateListA2 = c.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.J3);
        ColorStateList colorStateListA3 = c.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.H3);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(c.b.a.a.l.I3, 0);
        c.b.a.a.w.m mVarM = c.b.a.a.w.m.b(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.F3, 0), typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.G3, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    int b() {
        return this.f2709a.bottom;
    }

    int c() {
        return this.f2709a.top;
    }

    void d(TextView textView) {
        c.b.a.a.w.h hVar = new c.b.a.a.w.h();
        c.b.a.a.w.h hVar2 = new c.b.a.a.w.h();
        hVar.setShapeAppearanceModel(this.f);
        hVar2.setShapeAppearanceModel(this.f);
        hVar.X(this.f2711c);
        hVar.h0(this.e, this.f2712d);
        textView.setTextColor(this.f2710b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f2710b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f2709a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
