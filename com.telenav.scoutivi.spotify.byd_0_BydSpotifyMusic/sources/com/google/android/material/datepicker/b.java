package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: compiled from: CalendarStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f4413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f4414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final a f4415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final a f4416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final a f4417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final a f4418f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final a f4419g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Paint f4420h;

    b(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(b3.b.c(context, o2.b.materialCalendarStyle, f.class.getCanonicalName()), o2.l.MaterialCalendar);
        this.f4413a = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_dayStyle, 0));
        this.f4419g = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_dayInvalidStyle, 0));
        this.f4414b = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_daySelectedStyle, 0));
        this.f4415c = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateListA = b3.c.a(context, typedArrayObtainStyledAttributes, o2.l.MaterialCalendar_rangeFillColor);
        this.f4416d = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_yearStyle, 0));
        this.f4417e = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_yearSelectedStyle, 0));
        this.f4418f = a.a(context, typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.f4420h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
