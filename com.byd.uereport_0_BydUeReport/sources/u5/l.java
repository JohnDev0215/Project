package u5;

import android.annotation.TargetApi;
import android.icu.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: UtcDates.java */
/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicReference<k> f8283a = new AtomicReference<>();

    public static long a(long j8) {
        Calendar calendarL = l();
        calendarL.setTimeInMillis(j8);
        return d(calendarL).getTimeInMillis();
    }

    @TargetApi(24)
    public static DateFormat b(Locale locale) {
        return c("MMMEd", locale);
    }

    @TargetApi(24)
    public static DateFormat c(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(k());
        return instanceForSkeleton;
    }

    public static Calendar d(Calendar calendar) {
        Calendar calendarM = m(calendar);
        Calendar calendarL = l();
        calendarL.set(calendarM.get(1), calendarM.get(2), calendarM.get(5));
        return calendarL;
    }

    public static java.text.DateFormat e(int i8, Locale locale) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i8, locale);
        dateInstance.setTimeZone(i());
        return dateInstance;
    }

    public static java.text.DateFormat f(Locale locale) {
        return e(0, locale);
    }

    public static SimpleDateFormat g(String str, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, locale);
        simpleDateFormat.setTimeZone(i());
        return simpleDateFormat;
    }

    public static k h() {
        k kVar = f8283a.get();
        return kVar == null ? k.c() : kVar;
    }

    public static TimeZone i() {
        return TimeZone.getTimeZone("UTC");
    }

    public static Calendar j() {
        Calendar calendarA = h().a();
        calendarA.set(11, 0);
        calendarA.set(12, 0);
        calendarA.set(13, 0);
        calendarA.set(14, 0);
        calendarA.setTimeZone(i());
        return calendarA;
    }

    @TargetApi(24)
    public static android.icu.util.TimeZone k() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    public static Calendar l() {
        return m(null);
    }

    public static Calendar m(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(i());
        if (calendar == null) {
            calendar2.clear();
        } else {
            calendar2.setTimeInMillis(calendar.getTimeInMillis());
        }
        return calendar2;
    }

    @TargetApi(24)
    public static DateFormat n(Locale locale) {
        return c("yMMMEd", locale);
    }

    public static SimpleDateFormat o() {
        return p(Locale.getDefault());
    }

    public static SimpleDateFormat p(Locale locale) {
        return g("LLLL, yyyy", locale);
    }
}
