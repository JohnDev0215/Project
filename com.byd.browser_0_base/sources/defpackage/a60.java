package defpackage;

import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* JADX INFO: compiled from: DateDialogNormalizer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a60 {

    /* JADX INFO: compiled from: DateDialogNormalizer.java */
    public static class a {
        public final long a;
        public final int b;
        public final int c;
        public final int d;

        public a(long j, int i, int i2, int i3) {
            this.a = j;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }

        public static a a(long j) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar.setTimeInMillis(j);
            int i = gregorianCalendar.get(1);
            int i2 = gregorianCalendar.get(2);
            int i3 = gregorianCalendar.get(5);
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            calendar.clear();
            calendar.set(i, i2, i3);
            return new a(calendar.getTimeInMillis(), i, i2, i3);
        }
    }

    public static void a(DatePicker datePicker, DatePicker.OnDateChangedListener onDateChangedListener, int i, int i2, int i3, long j, long j2) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.clear();
        calendar.set(i, i2, i3);
        long timeInMillis = calendar.getTimeInMillis();
        a aVar = new a(timeInMillis, i, i2, i3);
        a aVarA = a.a(j);
        a aVarA2 = a.a(j2);
        long j3 = aVarA2.a;
        long j4 = aVarA.a;
        if (j3 < j4) {
            aVarA2 = aVarA;
        }
        long j5 = aVarA2.a;
        if (timeInMillis < j4) {
            aVar = aVarA;
        } else if (timeInMillis > j5) {
            aVar = aVarA2;
        }
        if (j4 > datePicker.getMaxDate()) {
            datePicker.setMaxDate(j5);
            datePicker.setMinDate(j4);
        } else {
            datePicker.setMinDate(j4);
            datePicker.setMaxDate(j5);
        }
        datePicker.init(aVar.b, aVar.c, aVar.d, onDateChangedListener);
    }
}
