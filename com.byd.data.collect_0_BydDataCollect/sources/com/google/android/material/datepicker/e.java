package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.os.Build;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3963b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Calendar f3964c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3965d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f3966e;

    static {
        f3963b = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public e() {
        Calendar calendarR = p.r();
        this.f3964c = calendarR;
        this.f3965d = calendarR.getMaximum(7);
        this.f3966e = calendarR.getFirstDayOfWeek();
    }

    private int b(int i2) {
        int i3 = i2 + this.f3966e;
        int i4 = this.f3965d;
        return i3 > i4 ? i3 - i4 : i3;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i2) {
        if (i2 >= this.f3965d) {
            return null;
        }
        return Integer.valueOf(b(i2));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3965d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i2, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(d.b.a.a.h.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f3964c.set(7, b(i2));
        textView.setText(this.f3964c.getDisplayName(7, f3963b, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(d.b.a.a.j.mtrl_picker_day_of_week_column_header), this.f3964c.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
