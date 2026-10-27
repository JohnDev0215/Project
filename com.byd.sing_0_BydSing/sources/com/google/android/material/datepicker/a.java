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
import c.g.k.t;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Rect f3518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f3519b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f3520c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f3521d;
    private final int e;
    private final d.b.a.a.w.m f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, d.b.a.a.w.m mVar, Rect rect) {
        c.g.j.h.b(rect.left);
        c.g.j.h.b(rect.top);
        c.g.j.h.b(rect.right);
        c.g.j.h.b(rect.bottom);
        this.f3518a = rect;
        this.f3519b = colorStateList2;
        this.f3520c = colorStateList;
        this.f3521d = colorStateList3;
        this.e = i;
        this.f = mVar;
    }

    static a a(Context context, int i) {
        c.g.j.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, d.b.a.a.l.z3);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.A3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.C3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.B3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.D3, 0));
        ColorStateList colorStateListA = d.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.E3);
        ColorStateList colorStateListA2 = d.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.J3);
        ColorStateList colorStateListA3 = d.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.H3);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(d.b.a.a.l.I3, 0);
        d.b.a.a.w.m mVarM = d.b.a.a.w.m.b(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.F3, 0), typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.G3, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    int b() {
        return this.f3518a.bottom;
    }

    int c() {
        return this.f3518a.top;
    }

    void d(TextView textView) {
        d.b.a.a.w.h hVar = new d.b.a.a.w.h();
        d.b.a.a.w.h hVar2 = new d.b.a.a.w.h();
        hVar.setShapeAppearanceModel(this.f);
        hVar2.setShapeAppearanceModel(this.f);
        hVar.X(this.f3520c);
        hVar.h0(this.e, this.f3521d);
        textView.setTextColor(this.f3519b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f3519b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f3518a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
