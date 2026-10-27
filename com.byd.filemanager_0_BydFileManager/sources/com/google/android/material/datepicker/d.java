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
class d extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Calendar f4196c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4198e;

    static {
        f4195b = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public d() {
        Calendar calendarL = l.l();
        this.f4196c = calendarL;
        this.f4197d = calendarL.getMaximum(7);
        this.f4198e = calendarL.getFirstDayOfWeek();
    }

    private int b(int i) {
        int i2 = i + this.f4198e;
        int i3 = this.f4197d;
        return i2 > i3 ? i2 - i3 : i2;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i) {
        if (i >= this.f4197d) {
            return null;
        }
        return Integer.valueOf(b(i));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4197d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.m, viewGroup, false);
        }
        this.f4196c.set(7, b(i));
        textView.setText(this.f4196c.getDisplayName(7, f4195b, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(c.a.a.a.j.n), this.f4196c.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
