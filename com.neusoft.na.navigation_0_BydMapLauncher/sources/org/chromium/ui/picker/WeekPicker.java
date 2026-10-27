package org.chromium.ui.picker;

import android.content.Context;
import java.util.Calendar;
import java.util.TimeZone;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
public class WeekPicker extends TwoFieldDatePicker {
    public WeekPicker(Context context, double d, double d2) {
        super(context, d, d2);
        getPositionInYearSpinner().setContentDescription(getResources().getString(R.string.accessibility_date_picker_week));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis(System.currentTimeMillis());
        init(getISOWeekYearForDate(calendar), getWeekForDate(calendar), null);
    }

    public static Calendar createDateFromWeek(int i, int i2) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.set(7, 2);
        calendar.set(1, i);
        calendar.set(3, i2);
        return calendar;
    }

    public static Calendar createDateFromValue(double d) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.setFirstDayOfWeek(2);
        calendar.setMinimalDaysInFirstWeek(4);
        calendar.setTimeInMillis((long) d);
        return calendar;
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected Calendar getDateForValue(double d) {
        return createDateFromValue(d);
    }

    public static int getISOWeekYearForDate(Calendar calendar) {
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(3);
        return (i2 != 0 || i3 <= 51) ? (i2 == 11 && i3 == 1) ? i + 1 : i : i - 1;
    }

    public static int getWeekForDate(Calendar calendar) {
        return calendar.get(3);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected void setCurrentDate(int i, int i2) {
        Calendar calendarCreateDateFromWeek = createDateFromWeek(i, i2);
        if (calendarCreateDateFromWeek.before(getMinDate())) {
            setCurrentDate(getMinDate());
        } else if (calendarCreateDateFromWeek.after(getMaxDate())) {
            setCurrentDate(getMaxDate());
        } else {
            setCurrentDate(calendarCreateDateFromWeek);
        }
    }

    private int getNumberOfWeeks(int i) {
        return createDateFromWeek(i, 20).getActualMaximum(3);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    public int getYear() {
        return getISOWeekYearForDate(getCurrentDate());
    }

    public int getWeek() {
        return getWeekForDate(getCurrentDate());
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    public int getPositionInYear() {
        return getWeek();
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMaxYear() {
        return getISOWeekYearForDate(getMaxDate());
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMinYear() {
        return getISOWeekYearForDate(getMinDate());
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMaxPositionInYear(int i) {
        if (i == getISOWeekYearForDate(getMaxDate())) {
            return getWeekForDate(getMaxDate());
        }
        return getNumberOfWeeks(i);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMinPositionInYear(int i) {
        if (i == getISOWeekYearForDate(getMinDate())) {
            return getWeekForDate(getMinDate());
        }
        return 1;
    }
}
