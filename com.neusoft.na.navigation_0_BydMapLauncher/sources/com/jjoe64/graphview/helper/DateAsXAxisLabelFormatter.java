package com.jjoe64.graphview.helper;

import android.content.Context;
import com.jjoe64.graphview.DefaultLabelFormatter;
import java.text.DateFormat;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
public class DateAsXAxisLabelFormatter extends DefaultLabelFormatter {
    protected final Calendar mCalendar = Calendar.getInstance();
    protected final DateFormat mDateFormat;

    public DateAsXAxisLabelFormatter(Context context) {
        this.mDateFormat = android.text.format.DateFormat.getDateFormat(context);
    }

    public DateAsXAxisLabelFormatter(Context context, DateFormat dateFormat) {
        this.mDateFormat = dateFormat;
    }

    @Override // com.jjoe64.graphview.DefaultLabelFormatter, com.jjoe64.graphview.LabelFormatter
    public String formatLabel(double d, boolean z) {
        if (z) {
            this.mCalendar.setTimeInMillis((long) d);
            return this.mDateFormat.format(Long.valueOf(this.mCalendar.getTimeInMillis()));
        }
        return super.formatLabel(d, z);
    }
}
