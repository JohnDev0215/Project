package org.chromium.ui.picker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.chromium.ui.R;
import org.chromium.ui.picker.DateTimePickerDialog;
import org.chromium.ui.picker.MultiFieldTimePickerDialog;
import org.chromium.ui.picker.TwoFieldDatePickerDialog;

/* JADX INFO: loaded from: classes4.dex */
public class InputDialogContainer {
    private final Context mContext;
    private AlertDialog mDialog;
    private boolean mDialogAlreadyDismissed;
    private final InputActionDelegate mInputActionDelegate;

    public interface InputActionDelegate {
        void cancelDateTimeDialog();

        void replaceDateTime(double d);
    }

    public static boolean isDialogInputType(int i) {
        return i == 8 || i == 12 || i == 9 || i == 10 || i == 11 || i == 13;
    }

    public InputDialogContainer(Context context, InputActionDelegate inputActionDelegate) {
        this.mContext = context;
        this.mInputActionDelegate = inputActionDelegate;
    }

    public void showPickerDialog(int i, double d, double d2, double d3, double d4) {
        Calendar calendarCreateDateFromValue;
        if (Double.isNaN(d)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(14, 0);
            calendarCreateDateFromValue = calendar;
        } else if (i == 11) {
            calendarCreateDateFromValue = MonthPicker.createDateFromValue(d);
        } else if (i == 13) {
            calendarCreateDateFromValue = WeekPicker.createDateFromValue(d);
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar.setTimeInMillis((long) d);
            calendarCreateDateFromValue = gregorianCalendar;
        }
        if (i == 8) {
            showPickerDialog(i, calendarCreateDateFromValue.get(1), calendarCreateDateFromValue.get(2), calendarCreateDateFromValue.get(5), 0, 0, 0, 0, 0, d2, d3, d4);
            return;
        }
        if (i == 12) {
            showPickerDialog(i, 0, 0, 0, calendarCreateDateFromValue.get(11), calendarCreateDateFromValue.get(12), 0, 0, 0, d2, d3, d4);
            return;
        }
        if (i == 9 || i == 10) {
            showPickerDialog(i, calendarCreateDateFromValue.get(1), calendarCreateDateFromValue.get(2), calendarCreateDateFromValue.get(5), calendarCreateDateFromValue.get(11), calendarCreateDateFromValue.get(12), calendarCreateDateFromValue.get(13), calendarCreateDateFromValue.get(14), 0, d2, d3, d4);
        } else if (i == 11) {
            showPickerDialog(i, calendarCreateDateFromValue.get(1), calendarCreateDateFromValue.get(2), 0, 0, 0, 0, 0, 0, d2, d3, d4);
        } else if (i == 13) {
            showPickerDialog(i, WeekPicker.getISOWeekYearForDate(calendarCreateDateFromValue), 0, 0, 0, 0, 0, 0, WeekPicker.getWeekForDate(calendarCreateDateFromValue), d2, d3, d4);
        }
    }

    void showSuggestionDialog(final int i, final double d, final double d2, final double d3, final double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        ListView listView = new ListView(this.mContext);
        final DateTimeSuggestionListAdapter dateTimeSuggestionListAdapter = new DateTimeSuggestionListAdapter(this.mContext, Arrays.asList(dateTimeSuggestionArr));
        listView.setAdapter((ListAdapter) dateTimeSuggestionListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: org.chromium.ui.picker.InputDialogContainer.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                if (i2 == dateTimeSuggestionListAdapter.getCount() - 1) {
                    InputDialogContainer.this.dismissDialog();
                    InputDialogContainer.this.showPickerDialog(i, d, d2, d3, d4);
                } else {
                    InputDialogContainer.this.mInputActionDelegate.replaceDateTime(dateTimeSuggestionListAdapter.getItem(i2).value());
                    InputDialogContainer.this.dismissDialog();
                    InputDialogContainer.this.mDialogAlreadyDismissed = true;
                }
            }
        });
        int i2 = R.string.date_picker_dialog_title;
        if (i == 12) {
            i2 = R.string.time_picker_dialog_title;
        } else if (i == 9 || i == 10) {
            i2 = R.string.date_time_picker_dialog_title;
        } else if (i == 11) {
            i2 = R.string.month_picker_dialog_title;
        } else if (i == 13) {
            i2 = R.string.week_picker_dialog_title;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mContext).setTitle(i2).setView(listView).setNegativeButton(this.mContext.getText(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: org.chromium.ui.picker.InputDialogContainer.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                InputDialogContainer.this.dismissDialog();
            }
        }).create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.chromium.ui.picker.InputDialogContainer.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (InputDialogContainer.this.mDialog != dialogInterface || InputDialogContainer.this.mDialogAlreadyDismissed) {
                    return;
                }
                InputDialogContainer.this.mDialogAlreadyDismissed = true;
                InputDialogContainer.this.mInputActionDelegate.cancelDateTimeDialog();
            }
        });
        this.mDialogAlreadyDismissed = false;
        this.mDialog.show();
    }

    public void showDialog(int i, double d, double d2, double d3, double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        dismissDialog();
        if (dateTimeSuggestionArr == null) {
            showPickerDialog(i, d, d2, d3, d4);
        } else {
            showSuggestionDialog(i, d, d2, d3, d4, dateTimeSuggestionArr);
        }
    }

    protected void showPickerDialog(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, double d, double d2, double d3) {
        if (isDialogShowing()) {
            this.mDialog.dismiss();
        }
        int i10 = (int) d3;
        if (i == 8) {
            ChromeDatePickerDialog chromeDatePickerDialog = new ChromeDatePickerDialog(this.mContext, new DateListener(i), i2, i3, i4);
            DateDialogNormalizer.normalize(chromeDatePickerDialog.getDatePicker(), chromeDatePickerDialog, i2, i3, i4, (long) d, (long) d2);
            chromeDatePickerDialog.setTitle(this.mContext.getText(R.string.date_picker_dialog_title));
            this.mDialog = chromeDatePickerDialog;
        } else if (i == 12) {
            Context context = this.mContext;
            this.mDialog = new MultiFieldTimePickerDialog(context, 0, i5, i6, i7, i8, (int) d, (int) d2, i10, DateFormat.is24HourFormat(context), new FullTimeListener(i));
        } else if (i == 9 || i == 10) {
            this.mDialog = new DateTimePickerDialog(this.mContext, new DateTimeListener(i), i2, i3, i4, i5, i6, DateFormat.is24HourFormat(this.mContext), d, d2);
        } else if (i == 11) {
            this.mDialog = new MonthPickerDialog(this.mContext, new MonthOrWeekListener(i), i2, i3, d, d2);
        } else if (i == 13) {
            this.mDialog = new WeekPickerDialog(this.mContext, new MonthOrWeekListener(i), i2, i9, d, d2);
        }
        this.mDialog.setButton(-1, this.mContext.getText(R.string.date_picker_dialog_set), (DialogInterface.OnClickListener) this.mDialog);
        this.mDialog.setButton(-2, this.mContext.getText(android.R.string.cancel), (DialogInterface.OnClickListener) null);
        this.mDialog.setButton(-3, this.mContext.getText(R.string.date_picker_dialog_clear), new DialogInterface.OnClickListener() { // from class: org.chromium.ui.picker.InputDialogContainer.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i11) {
                InputDialogContainer.this.mDialogAlreadyDismissed = true;
                InputDialogContainer.this.mInputActionDelegate.replaceDateTime(Double.NaN);
            }
        });
        this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: org.chromium.ui.picker.InputDialogContainer.5
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (InputDialogContainer.this.mDialogAlreadyDismissed) {
                    return;
                }
                InputDialogContainer.this.mDialogAlreadyDismissed = true;
                InputDialogContainer.this.mInputActionDelegate.cancelDateTimeDialog();
            }
        });
        this.mDialogAlreadyDismissed = false;
        this.mDialog.show();
    }

    boolean isDialogShowing() {
        AlertDialog alertDialog = this.mDialog;
        return alertDialog != null && alertDialog.isShowing();
    }

    void dismissDialog() {
        if (isDialogShowing()) {
            this.mDialog.dismiss();
        }
    }

    private class DateListener implements DatePickerDialog.OnDateSetListener {
        private final int mDialogType;

        DateListener(int i) {
            this.mDialogType = i;
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, i, i2, i3, 0, 0, 0, 0, 0);
        }
    }

    private class FullTimeListener implements MultiFieldTimePickerDialog.OnMultiFieldTimeSetListener {
        private final int mDialogType;

        FullTimeListener(int i) {
            this.mDialogType = i;
        }

        @Override // org.chromium.ui.picker.MultiFieldTimePickerDialog.OnMultiFieldTimeSetListener
        public void onTimeSet(int i, int i2, int i3, int i4) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, 0, 0, 0, i, i2, i3, i4, 0);
        }
    }

    private class DateTimeListener implements DateTimePickerDialog.OnDateTimeSetListener {
        private final int mDialogType;
        private final boolean mLocal;

        public DateTimeListener(int i) {
            this.mLocal = i == 10;
            this.mDialogType = i;
        }

        @Override // org.chromium.ui.picker.DateTimePickerDialog.OnDateTimeSetListener
        public void onDateTimeSet(DatePicker datePicker, TimePicker timePicker, int i, int i2, int i3, int i4, int i5) {
            InputDialogContainer.this.setFieldDateTimeValue(this.mDialogType, i, i2, i3, i4, i5, 0, 0, 0);
        }
    }

    private class MonthOrWeekListener implements TwoFieldDatePickerDialog.OnValueSetListener {
        private final int mDialogType;

        MonthOrWeekListener(int i) {
            this.mDialogType = i;
        }

        @Override // org.chromium.ui.picker.TwoFieldDatePickerDialog.OnValueSetListener
        public void onValueSet(int i, int i2) {
            int i3 = this.mDialogType;
            if (i3 == 11) {
                InputDialogContainer.this.setFieldDateTimeValue(i3, i, i2, 0, 0, 0, 0, 0, 0);
            } else {
                InputDialogContainer.this.setFieldDateTimeValue(i3, i, 0, 0, 0, 0, 0, 0, i2);
            }
        }
    }

    protected void setFieldDateTimeValue(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.mDialogAlreadyDismissed) {
            return;
        }
        this.mDialogAlreadyDismissed = true;
        if (i == 11) {
            this.mInputActionDelegate.replaceDateTime(((i2 - 1970) * 12) + i3);
            return;
        }
        if (i == 13) {
            this.mInputActionDelegate.replaceDateTime(WeekPicker.createDateFromWeek(i2, i9).getTimeInMillis());
            return;
        }
        if (i == 12) {
            this.mInputActionDelegate.replaceDateTime(TimeUnit.HOURS.toMillis(i5) + TimeUnit.MINUTES.toMillis(i6) + TimeUnit.SECONDS.toMillis(i7) + ((long) i8));
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
        this.mInputActionDelegate.replaceDateTime(calendar.getTimeInMillis());
    }
}
