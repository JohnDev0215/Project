package com.google.android.material.picker;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public abstract class MaterialCalendarView<S> extends LinearLayoutCompat {
    private final MonthInYearAdapter monthInYearAdapter;

    protected abstract void drawSelection(AdapterView<?> adapterView);

    protected abstract AdapterView.OnItemClickListener getOnItemClickListener();

    protected abstract S getSelection();

    public MaterialCalendarView(Context context) {
        this(context, null);
    }

    public MaterialCalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(1);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.mtrl_date_picker_calendar_days_header, this);
        layoutInflater.inflate(R.layout.mtrl_date_picker_calendar_days, this);
        GridView daysHeader = (GridView) findViewById(R.id.date_picker_calendar_days_header);
        GridView daysGrid = (GridView) findViewById(R.id.date_picker_calendar_days);
        Calendar calendar = Calendar.getInstance();
        MonthInYear monthInYear = MonthInYear.create(calendar.get(1), calendar.get(2));
        this.monthInYearAdapter = new MonthInYearAdapter(context, monthInYear);
        daysHeader.setAdapter((ListAdapter) new DaysHeaderAdapter());
        daysHeader.setNumColumns(monthInYear.daysInWeek);
        daysGrid.setAdapter((ListAdapter) this.monthInYearAdapter);
        daysGrid.setNumColumns(monthInYear.daysInWeek);
        daysGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.picker.MaterialCalendarView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaterialCalendarView.this.getOnItemClickListener().onItemClick(parent, view, position, id);
                MaterialCalendarView.this.drawSelection(parent);
                if (Build.VERSION.SDK_INT >= 16) {
                    MaterialCalendarView.this.callOnClick();
                } else {
                    MaterialCalendarView.this.performClick();
                }
            }
        });
    }

    protected final MonthInYearAdapter getMonthInYearAdapter() {
        return this.monthInYearAdapter;
    }
}
