package org.chromium.content.browser.picker;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import defpackage.a60;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.chromium.content.R$id;
import org.chromium.content.R$layout;
import org.chromium.content.R$string;
import org.chromium.content.browser.picker.d;

/* JADX INFO: compiled from: DateTimePickerDialog.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends AlertDialog implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    public final DatePicker e;
    public final TimePicker f;
    public final InterfaceC0151a g;
    public final long h;
    public final long i;

    /* JADX INFO: renamed from: org.chromium.content.browser.picker.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DateTimePickerDialog.java */
    public interface InterfaceC0151a {
    }

    public a(Context context, d.C0152d c0152d, int i, int i2, int i3, int i4, int i5, boolean z, double d, double d2) {
        super(context, 0);
        long j = (long) d;
        this.h = j;
        long j2 = (long) d2;
        this.i = j2;
        this.g = c0152d;
        setButton(-1, context.getText(R$string.date_picker_dialog_set), this);
        setButton(-2, context.getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        setTitle(context.getText(R$string.date_time_picker_dialog_title));
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.date_time_picker_dialog, (ViewGroup) null);
        setView(viewInflate);
        DatePicker datePicker = (DatePicker) viewInflate.findViewById(R$id.date_picker);
        this.e = datePicker;
        a60.a(datePicker, this, i, i2, i3, j, j2);
        TimePicker timePicker = (TimePicker) viewInflate.findViewById(R$id.time_picker);
        this.f = timePicker;
        timePicker.setIs24HourView(Boolean.valueOf(z));
        timePicker.setCurrentHour(Integer.valueOf(i4));
        timePicker.setCurrentMinute(Integer.valueOf(i5));
        timePicker.setOnTimeChangedListener(this);
        onTimeChanged(timePicker, timePicker.getCurrentHour().intValue(), timePicker.getCurrentMinute().intValue());
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        InterfaceC0151a interfaceC0151a = this.g;
        if (interfaceC0151a != null) {
            DatePicker datePicker = this.e;
            datePicker.clearFocus();
            TimePicker timePicker = this.f;
            timePicker.clearFocus();
            d.C0152d c0152d = (d.C0152d) interfaceC0151a;
            d.this.b(c0152d.a, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour().intValue(), timePicker.getCurrentMinute().intValue(), 0, 0, 0);
        }
    }

    @Override // android.widget.DatePicker.OnDateChangedListener
    public final void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        TimePicker timePicker = this.f;
        if (timePicker != null) {
            onTimeChanged(timePicker, timePicker.getCurrentHour().intValue(), timePicker.getCurrentMinute().intValue());
        }
    }

    @Override // android.widget.TimePicker.OnTimeChangedListener
    public final void onTimeChanged(TimePicker timePicker, int i, int i2) {
        DatePicker datePicker = this.e;
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int dayOfMonth = datePicker.getDayOfMonth();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.clear();
        TimePicker timePicker2 = this.f;
        gregorianCalendar.set(year, month, dayOfMonth, timePicker2.getCurrentHour().intValue(), timePicker2.getCurrentMinute().intValue(), 0);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        long j = this.h;
        if (timeInMillis < j) {
            gregorianCalendar.setTimeInMillis(j);
        } else {
            long timeInMillis2 = gregorianCalendar.getTimeInMillis();
            long j2 = this.i;
            if (timeInMillis2 > j2) {
                gregorianCalendar.setTimeInMillis(j2);
            }
        }
        timePicker2.setCurrentHour(Integer.valueOf(gregorianCalendar.get(11)));
        timePicker2.setCurrentMinute(Integer.valueOf(gregorianCalendar.get(12)));
    }
}
