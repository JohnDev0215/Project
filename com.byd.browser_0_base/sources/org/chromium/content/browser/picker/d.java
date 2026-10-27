package org.chromium.content.browser.picker;

import J.N;
import android.R;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;
import defpackage.a60;
import defpackage.t60;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.chromium.content.R$string;
import org.chromium.content.browser.input.DateTimeChooserAndroid;
import org.chromium.content.browser.picker.a;
import org.chromium.content.browser.picker.f;
import org.chromium.content.browser.picker.g;

/* JADX INFO: compiled from: InputDialogContainer.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d {
    public final Context a;
    public boolean b;
    public AlertDialog c;
    public final f d;

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            d dVar = d.this;
            dVar.b = true;
            ((DateTimeChooserAndroid.a) dVar.d).a(Double.NaN);
        }
    }

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class b implements DialogInterface.OnDismissListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            d dVar = d.this;
            if (dVar.b) {
                return;
            }
            dVar.b = true;
            DateTimeChooserAndroid dateTimeChooserAndroid = DateTimeChooserAndroid.this;
            long j = dateTimeChooserAndroid.a;
            if (j == 0) {
                return;
            }
            N.MCtaZNwj(j, dateTimeChooserAndroid);
        }
    }

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class c implements DatePickerDialog.OnDateSetListener {
        public final int a;

        public c(int i) {
            this.a = i;
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            d.this.b(this.a, i, i2, i3, 0, 0, 0, 0, 0);
        }
    }

    /* JADX INFO: renamed from: org.chromium.content.browser.picker.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class C0152d implements a.InterfaceC0151a {
        public final int a;

        public C0152d(int i) {
            this.a = i;
        }
    }

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class e implements f.b {
        public final int a;

        public e(int i) {
            this.a = i;
        }
    }

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public interface f {
    }

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class g implements g.a {
        public final int a;

        public g(int i) {
            this.a = i;
        }
    }

    /* JADX INFO: compiled from: InputDialogContainer.java */
    public class h implements TimePickerDialog.OnTimeSetListener {
        public final int a;

        public h(int i) {
            this.a = i;
        }

        @Override // android.app.TimePickerDialog.OnTimeSetListener
        public final void onTimeSet(TimePicker timePicker, int i, int i2) {
            d.this.b(this.a, 0, 0, 0, i, i2, 0, 0, 0);
        }
    }

    public d(Context context, DateTimeChooserAndroid.a aVar) {
        this.a = context;
        this.d = aVar;
    }

    public final void a() {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
    }

    public final void b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.b) {
            return;
        }
        this.b = true;
        f fVar = this.d;
        if (i == 11) {
            ((DateTimeChooserAndroid.a) fVar).a(((i2 - 1970) * 12) + i3);
            return;
        }
        if (i == 13) {
            ((DateTimeChooserAndroid.a) fVar).a(WeekPicker.f(i2, i9).getTimeInMillis());
            return;
        }
        if (i == 12) {
            ((DateTimeChooserAndroid.a) fVar).a(TimeUnit.SECONDS.toMillis(i7) + TimeUnit.MINUTES.toMillis(i6) + TimeUnit.HOURS.toMillis(i5) + ((long) i8));
            return;
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(1, i2);
        calendar.set(2, i3);
        calendar.set(5, i4);
        calendar.set(11, i5);
        calendar.set(12, i6);
        calendar.set(13, i7);
        calendar.set(14, i8);
        ((DateTimeChooserAndroid.a) fVar).a(calendar.getTimeInMillis());
    }

    public final void c(int i, double d, double d2, double d3, double d4) {
        Calendar calendar;
        if (Double.isNaN(d)) {
            calendar = Calendar.getInstance();
            calendar.set(14, 0);
        } else if (i == 11) {
            calendar = MonthPicker.f(d);
        } else if (i == 13) {
            int i2 = WeekPicker.k;
            calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.clear();
            calendar.setFirstDayOfWeek(2);
            calendar.setMinimalDaysInFirstWeek(4);
            calendar.setTimeInMillis((long) d);
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar.setTimeInMillis((long) d);
            calendar = gregorianCalendar;
        }
        if (i == 8) {
            d(i, calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0, 0, 0, d2, d3, d4);
            return;
        }
        if (i == 12) {
            d(i, 0, 0, 0, calendar.get(11), calendar.get(12), 0, 0, 0, d2, d3, d4);
            return;
        }
        if (i == 9 || i == 10) {
            d(i, calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13), calendar.get(14), 0, d2, d3, d4);
        } else if (i == 11) {
            d(i, calendar.get(1), calendar.get(2), 0, 0, 0, 0, 0, 0, d2, d3, d4);
        } else if (i == 13) {
            d(i, WeekPicker.g(calendar), 0, 0, 0, 0, 0, 0, calendar.get(3), d2, d3, d4);
        }
    }

    public final void d(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, double d, double d2, double d3) {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
        int i10 = (int) d3;
        Context context = this.a;
        if (i == 8) {
            t60 t60Var = new t60(this.a, new c(i), i2, i3, i4);
            a60.a(t60Var.getDatePicker(), t60Var, i2, i3, i4, (long) d, (long) d2);
            t60Var.setTitle(context.getText(R$string.date_picker_dialog_title));
            this.c = t60Var;
        } else if (i == 12) {
            if (i10 < 0 || i10 >= 60000) {
                Context context2 = this.a;
                this.c = new TimePickerDialog(context2, new h(i), i5, i6, DateFormat.is24HourFormat(context2));
            } else {
                Context context3 = this.a;
                this.c = new org.chromium.content.browser.picker.f(context3, i5, i6, i7, i8, (int) d, (int) d2, i10, DateFormat.is24HourFormat(context3), new e(i));
            }
        } else if (i == 9 || i == 10) {
            Context context4 = this.a;
            this.c = new org.chromium.content.browser.picker.a(context4, new C0152d(i), i2, i3, i4, i5, i6, DateFormat.is24HourFormat(context4), d, d2);
        } else if (i == 11) {
            this.c = new org.chromium.content.browser.picker.e(this.a, new g(i), i2, i3, d, d2);
        } else if (i == 13) {
            this.c = new org.chromium.content.browser.picker.h(this.a, new g(i), i2, i9, d, d2);
        }
        this.c.setButton(-1, context.getText(R$string.date_picker_dialog_set), (DialogInterface.OnClickListener) this.c);
        this.c.setButton(-2, context.getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        this.c.setButton(-3, context.getText(R$string.date_picker_dialog_clear), new a());
        this.c.setOnDismissListener(new b());
        this.b = false;
        this.c.show();
    }
}
