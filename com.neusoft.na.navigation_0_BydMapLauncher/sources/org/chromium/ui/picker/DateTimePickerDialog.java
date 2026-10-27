package org.chromium.ui.picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
public class DateTimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    private final OnDateTimeSetListener mCallBack;
    private final DatePicker mDatePicker;
    private final long mMaxTimeMillis;
    private final long mMinTimeMillis;
    private final TimePicker mTimePicker;

    public interface OnDateTimeSetListener {
        void onDateTimeSet(DatePicker datePicker, TimePicker timePicker, int i, int i2, int i3, int i4, int i5);
    }

    public DateTimePickerDialog(Context context, OnDateTimeSetListener onDateTimeSetListener, int i, int i2, int i3, int i4, int i5, boolean z, double d, double d2) {
        super(context, 0);
        long j = (long) d;
        this.mMinTimeMillis = j;
        long j2 = (long) d2;
        this.mMaxTimeMillis = j2;
        this.mCallBack = onDateTimeSetListener;
        setButton(-1, context.getText(R.string.date_picker_dialog_set), this);
        setButton(-2, context.getText(android.R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        setTitle(context.getText(R.string.date_time_picker_dialog_title));
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.date_time_picker_dialog, (ViewGroup) null);
        setView(viewInflate);
        DatePicker datePicker = (DatePicker) viewInflate.findViewById(R.id.date_picker);
        this.mDatePicker = datePicker;
        DateDialogNormalizer.normalize(datePicker, this, i, i2, i3, j, j2);
        TimePicker timePicker = (TimePicker) viewInflate.findViewById(R.id.time_picker);
        this.mTimePicker = timePicker;
        timePicker.setIs24HourView(Boolean.valueOf(z));
        setHour(timePicker, i4);
        setMinute(timePicker, i5);
        timePicker.setOnTimeChangedListener(this);
        onTimeChanged(timePicker, getHour(timePicker), getMinute(timePicker));
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        tryNotifyDateTimeSet();
    }

    private void tryNotifyDateTimeSet() {
        if (this.mCallBack != null) {
            this.mDatePicker.clearFocus();
            this.mTimePicker.clearFocus();
            OnDateTimeSetListener onDateTimeSetListener = this.mCallBack;
            DatePicker datePicker = this.mDatePicker;
            onDateTimeSetListener.onDateTimeSet(datePicker, this.mTimePicker, datePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth(), getHour(this.mTimePicker), getMinute(this.mTimePicker));
        }
    }

    @Override // android.widget.DatePicker.OnDateChangedListener
    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        TimePicker timePicker = this.mTimePicker;
        if (timePicker != null) {
            onTimeChanged(timePicker, getHour(timePicker), getMinute(this.mTimePicker));
        }
    }

    @Override // android.widget.TimePicker.OnTimeChangedListener
    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        onTimeChangedInternal(this.mDatePicker.getYear(), this.mDatePicker.getMonth(), this.mDatePicker.getDayOfMonth(), this.mTimePicker, this.mMinTimeMillis, this.mMaxTimeMillis);
    }

    public static void onTimeChangedInternal(int i, int i2, int i3, TimePicker timePicker, long j, long j2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.clear();
        gregorianCalendar.set(i, i2, i3, getHour(timePicker), getMinute(timePicker), 0);
        if (gregorianCalendar.getTimeInMillis() < j) {
            gregorianCalendar.setTimeInMillis(j);
        } else if (gregorianCalendar.getTimeInMillis() > j2) {
            gregorianCalendar.setTimeInMillis(j2);
        }
        setHour(timePicker, gregorianCalendar.get(11));
        setMinute(timePicker, gregorianCalendar.get(12));
    }

    public void updateDateTime(int i, int i2, int i3, int i4, int i5) {
        this.mDatePicker.updateDate(i, i2, i3);
        setHour(this.mTimePicker, i4);
        setMinute(this.mTimePicker, i5);
    }

    private static void setHour(TimePicker timePicker, int i) {
        timePicker.setCurrentHour(Integer.valueOf(i));
    }

    private static void setMinute(TimePicker timePicker, int i) {
        timePicker.setCurrentMinute(Integer.valueOf(i));
    }

    private static int getHour(TimePicker timePicker) {
        return timePicker.getCurrentHour().intValue();
    }

    private static int getMinute(TimePicker timePicker) {
        return timePicker.getCurrentMinute().intValue();
    }
}
