package org.chromium.ui.picker;

import android.R;
import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import org.threeten.bp.chrono.HijrahDate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class TwoFieldDatePicker extends FrameLayout {
    private Calendar mCurrentDate;
    private Calendar mMaxDate;
    private Calendar mMinDate;
    private OnMonthOrWeekChangedListener mMonthOrWeekChangedListener;
    private final NumberPicker mPositionInYearSpinner;
    private final NumberPicker mYearSpinner;

    public interface OnMonthOrWeekChangedListener {
        void onMonthOrWeekChanged(TwoFieldDatePicker twoFieldDatePicker, int i, int i2);
    }

    protected abstract Calendar getDateForValue(double d);

    protected abstract int getMaxPositionInYear(int i);

    protected abstract int getMaxYear();

    protected abstract int getMinPositionInYear(int i);

    protected abstract int getMinYear();

    public abstract int getPositionInYear();

    protected abstract void setCurrentDate(int i, int i2);

    public TwoFieldDatePicker(Context context, double d, double d2) {
        super(context, null, R.attr.datePickerStyle);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(org.chromium.ui.R.layout.two_field_date_picker, (ViewGroup) this, true);
        NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener() { // from class: org.chromium.ui.picker.TwoFieldDatePicker.1
            @Override // android.widget.NumberPicker.OnValueChangeListener
            public void onValueChange(NumberPicker numberPicker, int i, int i2) {
                int maxPositionInYear;
                int year = TwoFieldDatePicker.this.getYear();
                int positionInYear = TwoFieldDatePicker.this.getPositionInYear();
                if (numberPicker != TwoFieldDatePicker.this.mPositionInYearSpinner) {
                    if (numberPicker != TwoFieldDatePicker.this.mYearSpinner) {
                        throw new IllegalArgumentException();
                    }
                    maxPositionInYear = positionInYear;
                } else if (i == numberPicker.getMaxValue() && i2 == numberPicker.getMinValue()) {
                    i2 = year + 1;
                    maxPositionInYear = TwoFieldDatePicker.this.getMinPositionInYear(i2);
                } else if (i == numberPicker.getMinValue() && i2 == numberPicker.getMaxValue()) {
                    i2 = year - 1;
                    maxPositionInYear = TwoFieldDatePicker.this.getMaxPositionInYear(i2);
                } else {
                    maxPositionInYear = i2;
                    i2 = year;
                }
                TwoFieldDatePicker.this.setCurrentDate(i2, maxPositionInYear);
                TwoFieldDatePicker.this.updateSpinners();
                TwoFieldDatePicker.this.notifyDateChanged();
            }
        };
        this.mCurrentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        if (d >= d2) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.mMinDate = calendar;
            calendar.set(0, 0, 1);
            Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.mMaxDate = calendar2;
            calendar2.set(HijrahDate.MAX_VALUE_OF_ERA, 0, 1);
        } else {
            this.mMinDate = getDateForValue(d);
            this.mMaxDate = getDateForValue(d2);
        }
        NumberPicker numberPicker = (NumberPicker) findViewById(org.chromium.ui.R.id.position_in_year);
        this.mPositionInYearSpinner = numberPicker;
        numberPicker.setOnLongPressUpdateInterval(200L);
        numberPicker.setOnValueChangedListener(onValueChangeListener);
        NumberPicker numberPicker2 = (NumberPicker) findViewById(org.chromium.ui.R.id.year);
        this.mYearSpinner = numberPicker2;
        numberPicker2.setOnLongPressUpdateInterval(100L);
        numberPicker2.setOnValueChangedListener(onValueChangeListener);
        reorderSpinners();
    }

    private void reorderSpinners() {
        LinearLayout linearLayout = (LinearLayout) findViewById(org.chromium.ui.R.id.pickers);
        linearLayout.removeView(this.mPositionInYearSpinner);
        linearLayout.removeView(this.mYearSpinner);
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd");
        int iIndexOf = 0;
        boolean z = false;
        boolean z2 = false;
        while (iIndexOf < bestDateTimePattern.length()) {
            char cCharAt = bestDateTimePattern.charAt(iIndexOf);
            if (cCharAt == '\'') {
                iIndexOf = bestDateTimePattern.indexOf(39, iIndexOf + 1);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("Bad quoting in " + bestDateTimePattern);
                }
            } else if ((cCharAt == 'M' || cCharAt == 'L') && !z) {
                linearLayout.addView(this.mPositionInYearSpinner);
                z = true;
            } else if (cCharAt == 'y' && !z2) {
                linearLayout.addView(this.mYearSpinner);
                z2 = true;
            }
            iIndexOf++;
        }
        if (!z) {
            linearLayout.addView(this.mPositionInYearSpinner);
        }
        if (z2) {
            return;
        }
        linearLayout.addView(this.mYearSpinner);
    }

    public void init(int i, int i2, OnMonthOrWeekChangedListener onMonthOrWeekChangedListener) {
        setCurrentDate(i, i2);
        updateSpinners();
        this.mMonthOrWeekChangedListener = onMonthOrWeekChangedListener;
    }

    public boolean isNewDate(int i, int i2) {
        return (getYear() == i && getPositionInYear() == i2) ? false : true;
    }

    public void updateDate(int i, int i2) {
        if (isNewDate(i, i2)) {
            setCurrentDate(i, i2);
            updateSpinners();
            notifyDateChanged();
        }
    }

    protected void setCurrentDate(Calendar calendar) {
        this.mCurrentDate = calendar;
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.mCurrentDate.getTimeInMillis(), 20));
    }

    public int getYear() {
        return this.mCurrentDate.get(1);
    }

    protected Calendar getMaxDate() {
        return this.mMaxDate;
    }

    protected Calendar getMinDate() {
        return this.mMinDate;
    }

    protected Calendar getCurrentDate() {
        return this.mCurrentDate;
    }

    protected NumberPicker getPositionInYearSpinner() {
        return this.mPositionInYearSpinner;
    }

    protected NumberPicker getYearSpinner() {
        return this.mYearSpinner;
    }

    protected void updateSpinners() {
        this.mPositionInYearSpinner.setDisplayedValues(null);
        this.mPositionInYearSpinner.setMinValue(getMinPositionInYear(getYear()));
        this.mPositionInYearSpinner.setMaxValue(getMaxPositionInYear(getYear()));
        this.mPositionInYearSpinner.setWrapSelectorWheel((this.mCurrentDate.equals(this.mMinDate) || this.mCurrentDate.equals(this.mMaxDate)) ? false : true);
        this.mYearSpinner.setMinValue(getMinYear());
        this.mYearSpinner.setMaxValue(getMaxYear());
        this.mYearSpinner.setWrapSelectorWheel(false);
        this.mYearSpinner.setValue(getYear());
        this.mPositionInYearSpinner.setValue(getPositionInYear());
    }

    protected void notifyDateChanged() {
        sendAccessibilityEvent(4);
        OnMonthOrWeekChangedListener onMonthOrWeekChangedListener = this.mMonthOrWeekChangedListener;
        if (onMonthOrWeekChangedListener != null) {
            onMonthOrWeekChangedListener.onMonthOrWeekChanged(this, getYear(), getPositionInYear());
        }
    }
}
