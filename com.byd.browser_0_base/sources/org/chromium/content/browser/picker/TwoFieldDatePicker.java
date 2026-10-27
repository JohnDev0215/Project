package org.chromium.content.browser.picker;

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
import org.chromium.content.R$id;
import org.chromium.content.R$layout;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TwoFieldDatePicker extends FrameLayout {
    public final NumberPicker e;
    public final NumberPicker f;
    public b g;
    public final Calendar h;
    public final Calendar i;
    public Calendar j;

    public class a implements NumberPicker.OnValueChangeListener {
        public a() {
        }

        @Override // android.widget.NumberPicker.OnValueChangeListener
        public final void onValueChange(NumberPicker numberPicker, int i, int i2) {
            int iB;
            TwoFieldDatePicker twoFieldDatePicker = TwoFieldDatePicker.this;
            int year = twoFieldDatePicker.getYear();
            int positionInYear = twoFieldDatePicker.getPositionInYear();
            if (numberPicker == twoFieldDatePicker.e) {
                if (i == numberPicker.getMaxValue() && i2 == numberPicker.getMinValue()) {
                    i2 = year + 1;
                    iB = twoFieldDatePicker.c(i2);
                } else if (i == numberPicker.getMinValue() && i2 == numberPicker.getMaxValue()) {
                    i2 = year - 1;
                    iB = twoFieldDatePicker.b(i2);
                } else {
                    iB = i2;
                    i2 = year;
                }
            } else {
                if (numberPicker != twoFieldDatePicker.f) {
                    throw new IllegalArgumentException();
                }
                iB = positionInYear;
            }
            twoFieldDatePicker.d(i2, iB);
            twoFieldDatePicker.e();
            twoFieldDatePicker.sendAccessibilityEvent(4);
            b bVar = twoFieldDatePicker.g;
            if (bVar != null) {
                int year2 = twoFieldDatePicker.getYear();
                int positionInYear2 = twoFieldDatePicker.getPositionInYear();
                TwoFieldDatePicker twoFieldDatePicker2 = ((g) bVar).e;
                twoFieldDatePicker2.d(year2, positionInYear2);
                twoFieldDatePicker2.e();
                twoFieldDatePicker2.g = null;
            }
        }
    }

    public interface b {
    }

    public TwoFieldDatePicker(Context context, double d, double d2) {
        super(context, null, R.attr.datePickerStyle);
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.two_field_date_picker, (ViewGroup) this, true);
        a aVar = new a();
        this.j = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        int iIndexOf = 0;
        if (d >= d2) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.h = calendar;
            calendar.set(0, 0, 1);
            Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            this.i = calendar2;
            calendar2.set(9999, 0, 1);
        } else {
            this.h = a(d);
            this.i = a(d2);
        }
        NumberPicker numberPicker = (NumberPicker) findViewById(R$id.position_in_year);
        this.e = numberPicker;
        numberPicker.setOnLongPressUpdateInterval(200L);
        numberPicker.setOnValueChangedListener(aVar);
        NumberPicker numberPicker2 = (NumberPicker) findViewById(R$id.year);
        this.f = numberPicker2;
        numberPicker2.setOnLongPressUpdateInterval(100L);
        numberPicker2.setOnValueChangedListener(aVar);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.pickers);
        linearLayout.removeView(numberPicker);
        linearLayout.removeView(numberPicker2);
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd");
        boolean z = false;
        boolean z2 = false;
        while (iIndexOf < bestDateTimePattern.length()) {
            char cCharAt = bestDateTimePattern.charAt(iIndexOf);
            if (cCharAt == '\'') {
                iIndexOf = bestDateTimePattern.indexOf(39, iIndexOf + 1);
                if (iIndexOf == -1) {
                    throw new IllegalArgumentException("Bad quoting in ".concat(bestDateTimePattern));
                }
            } else if ((cCharAt == 'M' || cCharAt == 'L') && !z) {
                linearLayout.addView(numberPicker);
                z = true;
            } else if (cCharAt == 'y' && !z2) {
                linearLayout.addView(numberPicker2);
                z2 = true;
            }
            iIndexOf++;
        }
        if (!z) {
            linearLayout.addView(numberPicker);
        }
        if (z2) {
            return;
        }
        linearLayout.addView(numberPicker2);
    }

    public abstract Calendar a(double d);

    public abstract int b(int i);

    public abstract int c(int i);

    public abstract void d(int i, int i2);

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public void e() {
        NumberPicker numberPicker = this.e;
        numberPicker.setDisplayedValues(null);
        numberPicker.setMinValue(c(getYear()));
        numberPicker.setMaxValue(b(getYear()));
        numberPicker.setWrapSelectorWheel((this.j.equals(this.h) || this.j.equals(this.i)) ? false : true);
        int minYear = getMinYear();
        NumberPicker numberPicker2 = this.f;
        numberPicker2.setMinValue(minYear);
        numberPicker2.setMaxValue(getMaxYear());
        numberPicker2.setWrapSelectorWheel(false);
        numberPicker2.setValue(getYear());
        numberPicker.setValue(getPositionInYear());
    }

    public Calendar getCurrentDate() {
        return this.j;
    }

    public Calendar getMaxDate() {
        return this.i;
    }

    public abstract int getMaxYear();

    public Calendar getMinDate() {
        return this.h;
    }

    public abstract int getMinYear();

    public abstract int getPositionInYear();

    public NumberPicker getPositionInYearSpinner() {
        return this.e;
    }

    public int getYear() {
        return this.j.get(1);
    }

    public NumberPicker getYearSpinner() {
        return this.f;
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(getContext(), this.j.getTimeInMillis(), 20));
    }

    public void setCurrentDate(Calendar calendar) {
        this.j = calendar;
    }
}
