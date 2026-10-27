package defpackage;

import android.content.Context;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class FC extends CC {
    public FC(Context context, double d, double d2) {
        super(context, d, d2);
        this.s.setContentDescription(getResources().getString(AbstractC0566bn.Q));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis(System.currentTimeMillis());
        h(m(calendar), calendar.get(3), null);
    }

    public static Calendar k(double d) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis((long) d);
        return calendar;
    }

    public static Calendar l(int i, int i2) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.set(7, 2);
        calendar.set(1, i);
        calendar.set(3, i2);
        return calendar;
    }

    public static int m(Calendar calendar) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(3);
        return (i2 != 0 || i3 <= 51) ? (i2 == 11 && i3 == 1) ? i + 1 : i : i - 1;
    }

    public static int n(Calendar calendar) {
        return calendar.get(3);
    }

    @Override // defpackage.CC
    public Calendar a(double d) {
        return k(d);
    }

    @Override // defpackage.CC
    public int b(int i) {
        return i == m(this.w) ? n(this.w) : l(i, 20).getActualMaximum(3);
    }

    @Override // defpackage.CC
    public int c() {
        return m(this.w);
    }

    @Override // defpackage.CC
    public int d(int i) {
        if (i == m(this.v)) {
            return n(this.v);
        }
        return 1;
    }

    @Override // defpackage.CC
    public int e() {
        return m(this.v);
    }

    @Override // defpackage.CC
    public int f() {
        return n(this.x);
    }

    @Override // defpackage.CC
    public int g() {
        return m(this.x);
    }

    @Override // defpackage.CC
    public void i(int i, int i2) {
        Calendar calendarL = l(i, i2);
        if (calendarL.before(this.v)) {
            this.x = this.v;
        } else if (calendarL.after(this.w)) {
            this.x = this.w;
        } else {
            this.x = calendarL;
        }
    }
}
