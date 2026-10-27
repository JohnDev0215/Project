package com.google.android.material.picker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.material.resources.MaterialAttributes;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public class MonthInYearAdapter extends BaseAdapter {
    private static final int MAXIMUM_WEEKS = Calendar.getInstance().getMaximum(4);
    private final MonthInYear monthInYear;
    private final int textViewSize;

    MonthInYearAdapter(Context context, MonthInYear monthInYear) {
        this.monthInYear = monthInYear;
        this.textViewSize = MaterialAttributes.resolveMinimumAccessibleTouchTarget(context);
    }

    @Override // android.widget.Adapter
    public Calendar getItem(int position) {
        if (position < this.monthInYear.daysFromStartOfWeekToFirstOfMonth() || position > lastPositionInMonth()) {
            return null;
        }
        return this.monthInYear.getDay(positionToDay(position));
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position / this.monthInYear.daysInWeek;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.monthInYear.daysInWeek * MAXIMUM_WEEKS;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView day = (TextView) convertView;
        if (convertView == null) {
            day = new TextView(parent.getContext());
            day.setHeight(this.textViewSize);
        }
        int offsetPosition = position - firstPositionInMonth();
        if (offsetPosition < 0 || offsetPosition >= this.monthInYear.daysInMonth) {
            day.setVisibility(4);
        } else {
            day.setText(String.valueOf(offsetPosition + 1));
            day.setVisibility(0);
        }
        return day;
    }

    public int firstPositionInMonth() {
        return this.monthInYear.daysFromStartOfWeekToFirstOfMonth();
    }

    public int lastPositionInMonth() {
        return (this.monthInYear.daysFromStartOfWeekToFirstOfMonth() + this.monthInYear.daysInMonth) - 1;
    }

    public int positionToDay(int position) {
        return (position - this.monthInYear.daysFromStartOfWeekToFirstOfMonth()) + 1;
    }

    public boolean withinMonth(int position) {
        return position >= firstPositionInMonth() && position <= lastPositionInMonth();
    }
}
