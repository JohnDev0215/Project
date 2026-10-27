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
    private final Rect f4185a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f4186b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f4187c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f4188d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4189e;
    private final c.a.a.a.w.m f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, c.a.a.a.w.m mVar, Rect rect) {
        b.g.j.h.b(rect.left);
        b.g.j.h.b(rect.top);
        b.g.j.h.b(rect.right);
        b.g.j.h.b(rect.bottom);
        this.f4185a = rect;
        this.f4186b = colorStateList2;
        this.f4187c = colorStateList;
        this.f4188d = colorStateList3;
        this.f4189e = i;
        this.f = mVar;
    }

    static a a(Context context, int i) {
        b.g.j.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, c.a.a.a.l.z3);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.A3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.C3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.B3, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.D3, 0));
        ColorStateList colorStateListA = c.a.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.a.a.a.l.E3);
        ColorStateList colorStateListA2 = c.a.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.a.a.a.l.J3);
        ColorStateList colorStateListA3 = c.a.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.a.a.a.l.H3);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(c.a.a.a.l.I3, 0);
        c.a.a.a.w.m mVarM = c.a.a.a.w.m.b(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.F3, 0), typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.G3, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    int b() {
        return this.f4185a.bottom;
    }

    int c() {
        return this.f4185a.top;
    }

    void d(TextView textView) {
        c.a.a.a.w.h hVar = new c.a.a.a.w.h();
        c.a.a.a.w.h hVar2 = new c.a.a.a.w.h();
        hVar.setShapeAppearanceModel(this.f);
        hVar2.setShapeAppearanceModel(this.f);
        hVar.X(this.f4187c);
        hVar.h0(this.f4189e, this.f4188d);
        textView.setTextColor(this.f4186b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f4186b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f4185a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
