package org.chromium.content.browser.picker;

import android.content.Context;
import java.util.Calendar;
import java.util.TimeZone;
import org.chromium.content.R$string;

/* JADX INFO: loaded from: classes3.dex */
public class WeekPicker extends TwoFieldDatePicker {
    public static final /* synthetic */ int k = 0;

    public WeekPicker(Context context, double d, double d2) {
        super(context, d, d2);
        getPositionInYearSpinner().setContentDescription(getResources().getString(R$string.accessibility_date_picker_week));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis(System.currentTimeMillis());
        d(g(calendar), calendar.get(3));
        e();
        this.g = null;
    }

    public static Calendar f(int i, int i2) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.set(7, 2);
        calendar.set(1, i);
        calendar.set(3, i2);
        return calendar;
    }

    public static int g(Calendar calendar) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(3);
        return (i2 != 0 || i3 <= 51) ? (i2 == 11 && i3 == 1) ? i + 1 : i : i - 1;
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final Calendar a(double d) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis((long) d);
        return calendar;
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final int b(int i) {
        return i == g(getMaxDate()) ? getMaxDate().get(3) : f(i, 20).getActualMaximum(3);
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final int c(int i) {
        if (i == g(getMinDate())) {
            return getMinDate().get(3);
        }
        return 1;
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final void d(int i, int i2) {
        Calendar calendarF = f(i, i2);
        if (calendarF.before(getMinDate())) {
            setCurrentDate(getMinDate());
        } else if (calendarF.after(getMaxDate())) {
            setCurrentDate(getMaxDate());
        } else {
            setCurrentDate(calendarF);
        }
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getMaxYear() {
        return g(getMaxDate());
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getMinYear() {
        return g(getMinDate());
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getPositionInYear() {
        return getWeek();
    }

    public int getWeek() {
        return getCurrentDate().get(3);
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getYear() {
        return g(getCurrentDate());
    }
}
