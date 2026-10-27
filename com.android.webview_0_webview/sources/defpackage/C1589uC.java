package defpackage;

import android.R;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: uC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1589uC {
    public final Context a;
    public boolean b;
    public AlertDialog c;
    public final InterfaceC1424rC d;

    public C1589uC(Context context, InterfaceC1424rC interfaceC1424rC) {
        this.a = context;
        this.d = interfaceC1424rC;
    }

    public final void a() {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
    }

    public void b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.b) {
            return;
        }
        this.b = true;
        if (i == 11) {
            ((QA) this.d).a(((i2 - 1970) * 12) + i3);
            return;
        }
        if (i == 13) {
            ((QA) this.d).a(FC.l(i2, i9).getTimeInMillis());
            return;
        }
        if (i == 12) {
            ((QA) this.d).a(TimeUnit.SECONDS.toMillis(i7) + TimeUnit.MINUTES.toMillis(i6) + TimeUnit.HOURS.toMillis(i5) + ((long) i8));
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
        ((QA) this.d).a(calendar.getTimeInMillis());
    }

    public void c(int i, double d, double d2, double d3, double d4) {
        Calendar calendarK;
        if (Double.isNaN(d)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(14, 0);
            calendarK = calendar;
        } else if (i == 11) {
            calendarK = C1644vC.k(d);
        } else if (i == 13) {
            calendarK = FC.k(d);
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar.setTimeInMillis((long) d);
            calendarK = gregorianCalendar;
        }
        if (i == 8) {
            d(i, calendarK.get(1), calendarK.get(2), calendarK.get(5), 0, 0, 0, 0, 0, d2, d3, d4);
            return;
        }
        if (i == 12) {
            d(i, 0, 0, 0, calendarK.get(11), calendarK.get(12), 0, 0, 0, d2, d3, d4);
            return;
        }
        if (i == 9 || i == 10) {
            d(i, calendarK.get(1), calendarK.get(2), calendarK.get(5), calendarK.get(11), calendarK.get(12), calendarK.get(13), calendarK.get(14), 0, d2, d3, d4);
        } else if (i == 11) {
            d(i, calendarK.get(1), calendarK.get(2), 0, 0, 0, 0, 0, 0, d2, d3, d4);
        } else if (i == 13) {
            d(i, FC.m(calendarK), 0, 0, 0, 0, 0, 0, calendarK.get(3), d2, d3, d4);
        }
    }

    public void d(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, double d, double d2, double d3) {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
        int i10 = (int) d3;
        if (i == 8) {
            DatePickerDialogC0536bC datePickerDialogC0536bC = new DatePickerDialogC0536bC(this.a, new C1260oC(this, i), i2, i3, i4);
            AbstractC0480aC.a(datePickerDialogC0536bC.getDatePicker(), datePickerDialogC0536bC, i2, i3, i4, (long) d, (long) d2);
            datePickerDialogC0536bC.setTitle(this.a.getText(AbstractC0566bn.n1));
            this.c = datePickerDialogC0536bC;
        } else if (i == 12) {
            if (i10 < 0 || i10 >= 60000) {
                this.c = new TimePickerDialog(this.a, new C1534tC(this, i), i5, i6, DateFormat.is24HourFormat(this.a));
            } else {
                Context context = this.a;
                this.c = new AlertDialogC1864zC(context, 0, i5, i6, i7, i8, (int) d, (int) d2, i10, DateFormat.is24HourFormat(context), new C1370qC(this, i));
            }
        } else if (i == 9 || i == 10) {
            this.c = new AlertDialogC0871hC(this.a, new C1315pC(this, i), i2, i3, i4, i5, i6, DateFormat.is24HourFormat(this.a), d, d2);
        } else if (i == 11) {
            this.c = new AlertDialogC1699wC(this.a, new C1479sC(this, i), i2, i3, d, d2);
        } else if (i == 13) {
            this.c = new GC(this.a, new C1479sC(this, i), i2, i9, d, d2);
        }
        this.c.setButton(-1, this.a.getText(AbstractC0566bn.m1), (DialogInterface.OnClickListener) this.c);
        this.c.setButton(-2, this.a.getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        this.c.setButton(-3, this.a.getText(AbstractC0566bn.k1), new DialogInterfaceOnClickListenerC1150mC(this));
        this.c.setOnDismissListener(new DialogInterfaceOnDismissListenerC1205nC(this));
        this.b = false;
        this.c.show();
    }
}
