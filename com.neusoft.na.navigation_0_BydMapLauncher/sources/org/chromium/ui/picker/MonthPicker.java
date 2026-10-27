package org.chromium.ui.picker;

import android.content.Context;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
public class MonthPicker extends TwoFieldDatePicker {
    private static final int MONTHS_NUMBER = 12;
    private final String[] mShortMonths;

    public MonthPicker(Context context, double d, double d2) {
        super(context, d, d2);
        getPositionInYearSpinner().setContentDescription(getResources().getString(R.string.accessibility_date_picker_month));
        this.mShortMonths = DateFormatSymbols.getInstance(Locale.getDefault()).getShortMonths();
        if (usingNumericMonths()) {
            int i = 0;
            while (true) {
                String[] strArr = this.mShortMonths;
                if (i >= strArr.length) {
                    break;
                }
                int i2 = i + 1;
                strArr[i] = String.format("%d", Integer.valueOf(i2));
                i = i2;
            }
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        init(calendar.get(1), calendar.get(2), null);
    }

    protected boolean usingNumericMonths() {
        return Character.isDigit(this.mShortMonths[0].charAt(0));
    }

    public static Calendar createDateFromValue(double d) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set((int) Math.min((d / 12.0d) + 1970.0d, 2.147483647E9d), (int) (d % 12.0d), 1);
        return calendar;
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected Calendar getDateForValue(double d) {
        return createDateFromValue(d);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected void setCurrentDate(int i, int i2) {
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

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected void updateSpinners() {
        super.updateSpinners();
        getPositionInYearSpinner().setDisplayedValues((String[]) Arrays.copyOfRange(this.mShortMonths, getPositionInYearSpinner().getMinValue(), getPositionInYearSpinner().getMaxValue() + 1));
    }

    public int getMonth() {
        return getCurrentDate().get(2);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    public int getPositionInYear() {
        return getMonth();
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMaxYear() {
        return getMaxDate().get(1);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMinYear() {
        return getMinDate().get(1);
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMaxPositionInYear(int i) {
        if (i == getMaxDate().get(1)) {
            return getMaxDate().get(2);
        }
        return 11;
    }

    @Override // org.chromium.ui.picker.TwoFieldDatePicker
    protected int getMinPositionInYear(int i) {
        if (i == getMinDate().get(1)) {
            return getMinDate().get(2);
        }
        return 0;
    }
}
