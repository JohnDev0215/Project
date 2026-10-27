package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: compiled from: CalendarStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f3950a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f3951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final a f3952c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final a f3953d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final a f3954e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final a f3955f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final a f3956g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Paint f3957h;

    b(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(d.b.a.a.u.b.c(context, d.b.a.a.b.materialCalendarStyle, f.class.getCanonicalName()), d.b.a.a.l.MaterialCalendar);
        this.f3950a = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_dayStyle, 0));
        this.f3956g = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_dayInvalidStyle, 0));
        this.f3951b = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_daySelectedStyle, 0));
        this.f3952c = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateListA = d.b.a.a.u.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.MaterialCalendar_rangeFillColor);
        this.f3953d = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_yearStyle, 0));
        this.f3954e = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_yearSelectedStyle, 0));
        this.f3955f = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.f3957h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
