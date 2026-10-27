package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import i0.u;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Rect f4407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ColorStateList f4408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ColorStateList f4409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ColorStateList f4410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final e3.k f4412f;

    private a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i7, e3.k kVar, Rect rect) {
        h0.h.d(rect.left);
        h0.h.d(rect.top);
        h0.h.d(rect.right);
        h0.h.d(rect.bottom);
        this.f4407a = rect;
        this.f4408b = colorStateList2;
        this.f4409c = colorStateList;
        this.f4410d = colorStateList3;
        this.f4411e = i7;
        this.f4412f = kVar;
    }

    static a a(Context context, int i7) {
        h0.h.b(i7 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i7, o2.l.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(o2.l.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(o2.l.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(o2.l.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(o2.l.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = b3.c.a(context, typedArrayObtainStyledAttributes, o2.l.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = b3.c.a(context, typedArrayObtainStyledAttributes, o2.l.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = b3.c.a(context, typedArrayObtainStyledAttributes, o2.l.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(o2.l.MaterialCalendarItem_itemStrokeWidth, 0);
        e3.k kVarM = e3.k.b(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, kVarM, rect);
    }

    int b() {
        return this.f4407a.bottom;
    }

    int c() {
        return this.f4407a.top;
    }

    void d(TextView textView) {
        e3.g gVar = new e3.g();
        e3.g gVar2 = new e3.g();
        gVar.setShapeAppearanceModel(this.f4412f);
        gVar2.setShapeAppearanceModel(this.f4412f);
        gVar.X(this.f4409c);
        gVar.c0(this.f4411e, this.f4410d);
        textView.setTextColor(this.f4408b);
        RippleDrawable rippleDrawable = new RippleDrawable(this.f4408b.withAlpha(30), gVar, gVar2);
        Rect rect = this.f4407a;
        u.n0(textView, new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
