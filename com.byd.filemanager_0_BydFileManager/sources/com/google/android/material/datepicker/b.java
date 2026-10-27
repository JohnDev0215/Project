package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: compiled from: CalendarStyle.java */
/* JADX INFO: loaded from: classes.dex */
final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f4190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final a f4191b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final a f4192c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final a f4193d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final a f4194e;
    final a f;
    final a g;
    final Paint h;

    b(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.a.a.a.t.b.c(context, c.a.a.a.b.y, MaterialCalendar.class.getCanonicalName()), c.a.a.a.l.q3);
        this.f4190a = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.t3, 0));
        this.g = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.r3, 0));
        this.f4191b = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.s3, 0));
        this.f4192c = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.u3, 0));
        ColorStateList colorStateListA = c.a.a.a.t.c.a(context, typedArrayObtainStyledAttributes, c.a.a.a.l.v3);
        this.f4193d = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.x3, 0));
        this.f4194e = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.w3, 0));
        this.f = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.a.a.a.l.y3, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
