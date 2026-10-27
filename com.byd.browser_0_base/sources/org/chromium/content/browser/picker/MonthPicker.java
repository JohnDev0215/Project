package org.chromium.content.browser.picker;

import android.annotation.SuppressLint;
import android.content.Context;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.content.R$string;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"DefaultLocale"})
public class MonthPicker extends TwoFieldDatePicker {
    public final String[] k;

    public MonthPicker(Context context, double d, double d2) {
        super(context, d, d2);
        getPositionInYearSpinner().setContentDescription(getResources().getString(R$string.accessibility_date_picker_month));
        String[] shortMonths = DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();
        this.k = shortMonths;
        if (Character.isDigit(shortMonths[0].charAt(0))) {
            int i = 0;
            while (true) {
                String[] strArr = this.k;
                if (i >= strArr.length) {
                    break;
                }
                int i2 = i + 1;
                strArr[i] = String.format("%d", Integer.valueOf(i2));
                i = i2;
            }
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        d(calendar.get(1), calendar.get(2));
        e();
        this.g = null;
    }

    public static Calendar f(double d) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set((int) Math.min((d / 12.0d) + 1970.0d, 2.147483647E9d), (int) (d % 12.0d), 1);
        return calendar;
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final Calendar a(double d) {
        return f(d);
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final int b(int i) {
        if (i == getMaxDate().get(1)) {
            return getMaxDate().get(2);
        }
        return 11;
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final int c(int i) {
        if (i == getMinDate().get(1)) {
            return getMinDate().get(2);
        }
        return 0;
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final void d(int i, int i2) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(i, i2, 1);
        if (calendar.before(getMinDate())) {
            setCurrentDate(getMinDate());
        } else if (calendar.after(getMaxDate())) {
            setCurrentDate(getMaxDate());
        } else {
            setCurrentDate(calendar);
        }
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public final void e() {
        super.e();
        getPositionInYearSpinner().setDisplayedValues((String[]) Arrays.copyOfRange(this.k, getPositionInYearSpinner().getMinValue(), getPositionInYearSpinner().getMaxValue() + 1));
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getMaxYear() {
        return getMaxDate().get(1);
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getMinYear() {
        return getMinDate().get(1);
    }

    public int getMonth() {
        return getCurrentDate().get(2);
    }

    @Override // org.chromium.content.browser.picker.TwoFieldDatePicker
    public int getPositionInYear() {
        return getMonth();
    }
}
