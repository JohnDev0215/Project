package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: compiled from: CalendarStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f3794a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f3795b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final a f3796c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final a f3797d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final a f3798e;
    final a f;
    final a g;
    final Paint h;

    b(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.b.a.a.t.b.c(context, c.b.a.a.b.y, MaterialCalendar.class.getCanonicalName()), c.b.a.a.l.q3);
        this.f3794a = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.t3, 0));
        this.g = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.r3, 0));
        this.f3795b = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.s3, 0));
        this.f3796c = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.u3, 0));
        ColorStateList colorStateListA = c.b.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.v3);
        this.f3797d = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.x3, 0));
        this.f3798e = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.w3, 0));
        this.f = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.y3, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
