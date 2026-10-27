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

/* JADX INFO: loaded from: classes.dex */
class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3046b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Calendar f3047c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3048d;
    private final int e;

    static {
        f3046b = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public f() {
        Calendar calendarL = p.l();
        this.f3047c = calendarL;
        this.f3048d = calendarL.getMaximum(7);
        this.e = calendarL.getFirstDayOfWeek();
    }

    private int b(int i) {
        int i2 = i + this.e;
        int i3 = this.f3048d;
        return i2 > i3 ? i2 - i3 : i2;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i) {
        if (i >= this.f3048d) {
            return null;
        }
        return Integer.valueOf(b(i));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3048d;
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
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(b.a.a.a.h.g, viewGroup, false);
        }
        this.f3047c.set(7, b(i));
        textView.setText(this.f3047c.getDisplayName(7, f3046b, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(b.a.a.a.j.k), this.f3047c.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
