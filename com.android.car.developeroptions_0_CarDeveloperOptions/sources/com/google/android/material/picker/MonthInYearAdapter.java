package com.google.android.material.picker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.resources.MaterialAttributes;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class MonthInYearAdapter extends BaseAdapter {
    private static final int MAXIMUM_WEEKS = Calendar.getInstance().getMaximum(4);
    private final MonthInYear monthInYear;
    private final int textViewSize;

    MonthInYearAdapter(Context context, MonthInYear monthInYear) {
        this.monthInYear = monthInYear;
        this.textViewSize = MaterialAttributes.resolveMinimumAccessibleTouchTarget(context);
    }

    @Override // android.widget.Adapter
    public Calendar getItem(int i) {
        if (i < this.monthInYear.daysFromStartOfWeekToFirstOfMonth() || i > lastPositionInMonth()) {
            return null;
        }
        return this.monthInYear.getDay(positionToDay(i));
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.monthInYear.daysInWeek;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.monthInYear.daysInWeek * MAXIMUM_WEEKS;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = new TextView(viewGroup.getContext());
            textView.setHeight(this.textViewSize);
        }
        int iFirstPositionInMonth = i - firstPositionInMonth();
        if (iFirstPositionInMonth < 0 || iFirstPositionInMonth >= this.monthInYear.daysInMonth) {
            textView.setVisibility(4);
        } else {
            textView.setText(String.valueOf(iFirstPositionInMonth + 1));
            textView.setVisibility(0);
        }
        return textView;
    }

    public int firstPositionInMonth() {
        return this.monthInYear.daysFromStartOfWeekToFirstOfMonth();
    }

    public int lastPositionInMonth() {
        return (this.monthInYear.daysFromStartOfWeekToFirstOfMonth() + this.monthInYear.daysInMonth) - 1;
    }

    public int positionToDay(int i) {
        return (i - this.monthInYear.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    public boolean withinMonth(int i) {
        return i >= firstPositionInMonth() && i <= lastPositionInMonth();
    }
}
