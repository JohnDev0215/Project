package b.d.a.a.v;

import a.b.k.l;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f2078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f2079c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f2080d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f2081e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f2082f;
    public final b g;
    public final Paint h;

    public c(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(l.i.j0(context, b.d.a.a.b.materialCalendarStyle, f.class.getCanonicalName()), b.d.a.a.k.MaterialCalendar);
        this.f2077a = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_dayStyle, 0));
        this.g = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_dayInvalidStyle, 0));
        this.f2078b = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_daySelectedStyle, 0));
        this.f2079c = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateListY = l.i.y(context, typedArrayObtainStyledAttributes, b.d.a.a.k.MaterialCalendar_rangeFillColor);
        this.f2080d = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_yearStyle, 0));
        this.f2081e = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_yearSelectedStyle, 0));
        this.f2082f = b.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListY.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
