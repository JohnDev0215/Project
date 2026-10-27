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
    private final Rect f3789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f3790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f3791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f3792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3793e;
    private final c.b.a.a.w.m f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, c.b.a.a.w.m mVar, Rect rect) {
        b.g.j.h.b(rect.left);
        b.g.j.h.b(rect.top);
        b.g.j.h.b(rect.right);
        b.g.j.h.b(rect.bottom);
        this.f3789a = rect;
        this.f3790b = colorStateList2;
        this.f3791c = colorStateList;
        this.f3792d = colorStateList3;
        this.f3793e = i;
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
        return this.f3789a.bottom;
    }

    int c() {
        return this.f3789a.top;
    }

    void d(TextView textView) {
        c.b.a.a.w.h hVar = new c.b.a.a.w.h();
        c.b.a.a.w.h hVar2 = new c.b.a.a.w.h();
        hVar.setShapeAppearanceModel(this.f);
        hVar2.setShapeAppearanceModel(this.f);
        hVar.X(this.f3791c);
        hVar.h0(this.f3793e, this.f3792d);
        textView.setTextColor(this.f3790b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f3790b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f3789a;
        t.n0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
