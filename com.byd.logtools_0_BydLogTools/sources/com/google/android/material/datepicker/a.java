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
    private final Rect f3557a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f3558b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f3559c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f3560d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3561e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final d.b.a.a.w.m f3562f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, d.b.a.a.w.m mVar, Rect rect) {
        b.g.j.h.b(rect.left);
        b.g.j.h.b(rect.top);
        b.g.j.h.b(rect.right);
        b.g.j.h.b(rect.bottom);
        this.f3557a = rect;
        this.f3558b = colorStateList2;
        this.f3559c = colorStateList;
        this.f3560d = colorStateList3;
        this.f3561e = i;
        this.f3562f = mVar;
    }

    static a a(Context context, int i) {
        b.g.j.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
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
        return this.f3557a.bottom;
    }

    int c() {
        return this.f3557a.top;
    }

    void d(TextView textView) {
        d.b.a.a.w.h hVar = new d.b.a.a.w.h();
        d.b.a.a.w.h hVar2 = new d.b.a.a.w.h();
        hVar.setShapeAppearanceModel(this.f3562f);
        hVar2.setShapeAppearanceModel(this.f3562f);
        hVar.X(this.f3559c);
        hVar.h0(this.f3561e, this.f3560d);
        textView.setTextColor(this.f3558b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f3558b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f3557a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
