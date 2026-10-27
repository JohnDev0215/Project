package org.chromium.ui.picker;

import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes4.dex */
public class DateDialogNormalizer {

    private static class DateAndMillis {
        public final int day;
        public final long millisForPicker;
        public final int month;
        public final int year;

        DateAndMillis(long j, int i, int i2, int i3) {
            this.millisForPicker = j;
            this.year = i;
            this.month = i2;
            this.day = i3;
        }

        static DateAndMillis create(long j) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar.setTimeInMillis(j);
            return create(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5));
        }

        static DateAndMillis create(int i, int i2, int i3) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.clear();
            calendar.set(i, i2, i3);
            return new DateAndMillis(calendar.getTimeInMillis(), i, i2, i3);
        }
    }

    private static void setLimits(DatePicker datePicker, long j, long j2, long j3) {
        if (j2 > datePicker.getMaxDate()) {
            datePicker.setMaxDate(j3);
            datePicker.setMinDate(j2);
        } else {
            datePicker.setMinDate(j2);
            datePicker.setMaxDate(j3);
        }
    }

    public static void normalize(DatePicker datePicker, DatePicker.OnDateChangedListener onDateChangedListener, int i, int i2, int i3, long j, long j2) {
        DateAndMillis dateAndMillisCreate = DateAndMillis.create(i, i2, i3);
        DateAndMillis dateAndMillisCreate2 = DateAndMillis.create(j);
        DateAndMillis dateAndMillisCreate3 = DateAndMillis.create(j2);
        if (dateAndMillisCreate3.millisForPicker < dateAndMillisCreate2.millisForPicker) {
            dateAndMillisCreate3 = dateAndMillisCreate2;
        }
        if (dateAndMillisCreate.millisForPicker < dateAndMillisCreate2.millisForPicker) {
            dateAndMillisCreate = dateAndMillisCreate2;
        } else if (dateAndMillisCreate.millisForPicker > dateAndMillisCreate3.millisForPicker) {
            dateAndMillisCreate = dateAndMillisCreate3;
        }
        setLimits(datePicker, dateAndMillisCreate.millisForPicker, dateAndMillisCreate2.millisForPicker, dateAndMillisCreate3.millisForPicker);
        datePicker.init(dateAndMillisCreate.year, dateAndMillisCreate.month, dateAndMillisCreate.day, onDateChangedListener);
    }
}
