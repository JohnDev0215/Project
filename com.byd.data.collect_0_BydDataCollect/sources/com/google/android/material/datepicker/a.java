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
    private final Rect f3944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f3945b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f3946c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f3947d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3948e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final d.b.a.a.x.m f3949f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i2, d.b.a.a.x.m mVar, Rect rect) {
        b.g.j.h.b(rect.left);
        b.g.j.h.b(rect.top);
        b.g.j.h.b(rect.right);
        b.g.j.h.b(rect.bottom);
        this.f3944a = rect;
        this.f3945b = colorStateList2;
        this.f3946c = colorStateList;
        this.f3947d = colorStateList3;
        this.f3948e = i2;
        this.f3949f = mVar;
    }

    static a a(Context context, int i2) {
        b.g.j.h.a(i2 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, d.b.a.a.l.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(d.b.a.a.l.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = d.b.a.a.u.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = d.b.a.a.u.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = d.b.a.a.u.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(d.b.a.a.l.MaterialCalendarItem_itemStrokeWidth, 0);
        d.b.a.a.x.m mVarM = d.b.a.a.x.m.b(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    int b() {
        return this.f3944a.bottom;
    }

    int c() {
        return this.f3944a.top;
    }

    void d(TextView textView) {
        d.b.a.a.x.h hVar = new d.b.a.a.x.h();
        d.b.a.a.x.h hVar2 = new d.b.a.a.x.h();
        hVar.setShapeAppearanceModel(this.f3949f);
        hVar2.setShapeAppearanceModel(this.f3949f);
        hVar.X(this.f3946c);
        hVar.h0(this.f3948e, this.f3947d);
        textView.setTextColor(this.f3945b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f3945b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f3944a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
