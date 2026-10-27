package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: compiled from: DaysOfWeekAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class e extends BaseAdapter {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f4431i = 4;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Calendar f4432e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f4433g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f4434h;

    public e() {
        Calendar calendarL = p.l();
        this.f4432e = calendarL;
        this.f4433g = calendarL.getMaximum(7);
        this.f4434h = calendarL.getFirstDayOfWeek();
    }

    private int b(int i7) {
        int i8 = i7 + this.f4434h;
        int i9 = this.f4433g;
        return i8 > i9 ? i8 - i9 : i8;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i7) {
        if (i7 >= this.f4433g) {
            return null;
        }
        return Integer.valueOf(b(i7));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4433g;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i7) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i7, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(o2.h.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f4432e.set(7, b(i7));
        textView.setText(this.f4432e.getDisplayName(7, f4431i, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(o2.j.mtrl_picker_day_of_week_column_header), this.f4432e.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
