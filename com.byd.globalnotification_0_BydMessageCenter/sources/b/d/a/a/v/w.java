package b.d.a.a.v;

import android.annotation.TargetApi;
import android.icu.text.DateFormat;
import android.icu.util.TimeZone;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicReference<v> f2125a = new AtomicReference<>();

    @TargetApi(24)
    public static DateFormat a(Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton("MMMEd", locale);
        instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
        return instanceForSkeleton;
    }

    public static Calendar b(Calendar calendar) {
        Calendar calendarF = f(calendar);
        Calendar calendarE = e();
        calendarE.set(calendarF.get(1), calendarF.get(2), calendarF.get(5));
        return calendarE;
    }

    public static java.util.TimeZone c() {
        return java.util.TimeZone.getTimeZone("UTC");
    }

    public static Calendar d() {
        v vVar = f2125a.get();
        if (vVar == null) {
            vVar = v.f2122c;
        }
        java.util.TimeZone timeZone = vVar.f2124b;
        Calendar calendar = timeZone == null ? Calendar.getInstance() : Calendar.getInstance(timeZone);
        Long l = vVar.f2123a;
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.setTimeZone(c());
        return calendar;
    }

    public static Calendar e() {
        return f(null);
    }

    public static Calendar f(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(c());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    @TargetApi(24)
    public static DateFormat g(Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton("yMMMEd", locale);
        instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
        return instanceForSkeleton;
    }
}
