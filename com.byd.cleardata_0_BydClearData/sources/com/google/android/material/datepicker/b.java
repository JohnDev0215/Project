package com.google.android.material.datepicker;

import a.g.k.t;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Rect f3038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f3039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f3040c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f3041d;
    private final int e;
    private final b.a.a.a.b0.k f;

    private b(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, b.a.a.a.b0.k kVar, Rect rect) {
        a.g.j.h.b(rect.left);
        a.g.j.h.b(rect.top);
        a.g.j.h.b(rect.right);
        a.g.j.h.b(rect.bottom);
        this.f3038a = rect;
        this.f3039b = colorStateList2;
        this.f3040c = colorStateList;
        this.f3041d = colorStateList3;
        this.e = i;
        this.f = kVar;
    }

    static b a(Context context, int i) {
        a.g.j.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, b.a.a.a.l.b2);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.a.a.a.l.c2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.a.a.a.l.e2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.a.a.a.l.d2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.a.a.a.l.f2, 0));
        ColorStateList colorStateListA = b.a.a.a.y.c.a(context, typedArrayObtainStyledAttributes, b.a.a.a.l.g2);
        ColorStateList colorStateListA2 = b.a.a.a.y.c.a(context, typedArrayObtainStyledAttributes, b.a.a.a.l.l2);
        ColorStateList colorStateListA3 = b.a.a.a.y.c.a(context, typedArrayObtainStyledAttributes, b.a.a.a.l.j2);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.a.a.a.l.k2, 0);
        b.a.a.a.b0.k kVarM = b.a.a.a.b0.k.b(context, typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.h2, 0), typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.i2, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new b(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, kVarM, rect);
    }

    int b() {
        return this.f3038a.bottom;
    }

    int c() {
        return this.f3038a.top;
    }

    void d(TextView textView) {
        b.a.a.a.b0.g gVar = new b.a.a.a.b0.g();
        b.a.a.a.b0.g gVar2 = new b.a.a.a.b0.g();
        gVar.setShapeAppearanceModel(this.f);
        gVar2.setShapeAppearanceModel(this.f);
        gVar.W(this.f3040c);
        gVar.c0(this.e, this.f3041d);
        textView.setTextColor(this.f3039b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f3039b.withAlpha(30), gVar, gVar2) : gVar;
        Rect rect = this.f3038a;
        t.k0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
