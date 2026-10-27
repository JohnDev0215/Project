package c.b.a.a.x;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: compiled from: CalendarStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f4106a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f4107b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f4108c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f4109d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f4110e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f4111f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f4112g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f4113h;

    public b(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(c.b.a.a.f0.b.c(context, c.b.a.a.b.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), c.b.a.a.l.MaterialCalendar);
        this.f4106a = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_dayStyle, 0));
        this.f4112g = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_dayInvalidStyle, 0));
        this.f4107b = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_daySelectedStyle, 0));
        this.f4108c = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateListA = c.b.a.a.f0.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.MaterialCalendar_rangeFillColor);
        this.f4109d = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_yearStyle, 0));
        this.f4110e = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_yearSelectedStyle, 0));
        this.f4111f = a.a(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.f4113h = paint;
        paint.setColor(colorStateListA.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
