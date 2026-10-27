package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: compiled from: CalendarStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f3563a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f3564b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final a f3565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final a f3566d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final a f3567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final a f3568f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final a f3569g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Paint f3570h;

    b(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(d.b.a.a.t.b.c(context, d.b.a.a.b.y, MaterialCalendar.class.getCanonicalName()), d.b.a.a.l.q3);
        this.f3563a = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.t3, 0));
        this.f3569g = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.r3, 0));
        this.f3564b = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.s3, 0));
        this.f3565c = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.u3, 0));
        ColorStateList colorStateListA = d.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, d.b.a.a.l.v3);
        this.f3566d = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.x3, 0));
        this.f3567e = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.w3, 0));
        this.f3568f = a.a(context, typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.y3, 0));
        Paint paint = new Paint();
        this.f3570h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
