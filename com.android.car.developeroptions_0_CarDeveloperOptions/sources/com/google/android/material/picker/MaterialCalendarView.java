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
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public abstract class MaterialCalendarView<S> extends LinearLayoutCompat {
    private final MonthInYearAdapter monthInYearAdapter;

    protected abstract void drawSelection(AdapterView<?> adapterView);

    protected abstract AdapterView.OnItemClickListener getOnItemClickListener();

    protected abstract S getSelection();

    public MaterialCalendarView(Context context) {
        this(context, null);
    }

    public MaterialCalendarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCalendarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(1);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        layoutInflaterFrom.inflate(R$layout.mtrl_date_picker_calendar_days_header, this);
        layoutInflaterFrom.inflate(R$layout.mtrl_date_picker_calendar_days, this);
        GridView gridView = (GridView) findViewById(R$id.date_picker_calendar_days_header);
        GridView gridView2 = (GridView) findViewById(R$id.date_picker_calendar_days);
        Calendar calendar = Calendar.getInstance();
        MonthInYear monthInYearCreate = MonthInYear.create(calendar.get(1), calendar.get(2));
        this.monthInYearAdapter = new MonthInYearAdapter(context, monthInYearCreate);
        gridView.setAdapter((ListAdapter) new DaysHeaderAdapter());
        gridView.setNumColumns(monthInYearCreate.daysInWeek);
        gridView2.setAdapter((ListAdapter) this.monthInYearAdapter);
        gridView2.setNumColumns(monthInYearCreate.daysInWeek);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.picker.MaterialCalendarView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                MaterialCalendarView.this.getOnItemClickListener().onItemClick(adapterView, view, i2, j);
                MaterialCalendarView.this.drawSelection(adapterView);
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
