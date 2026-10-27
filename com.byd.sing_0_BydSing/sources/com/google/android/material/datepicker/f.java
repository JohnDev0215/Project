package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class f extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f3529b = l.l().getMaximum(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Month f3530c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final DateSelector<?> f3531d;
    b e;
    final CalendarConstraints f;

    f(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f3530c = month;
        this.f3531d = dateSelector;
        this.f = calendarConstraints;
    }

    private void e(Context context) {
        if (this.e == null) {
            this.e = new b(context);
        }
    }

    int a(int i) {
        return b() + (i - 1);
    }

    int b() {
        return this.f3530c.i();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i) {
        if (i < this.f3530c.i() || i > h()) {
            return null;
        }
        return Long.valueOf(this.f3530c.j(i(i)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0061  */
    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.f.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    boolean f(int i) {
        return i % this.f3530c.f == 0;
    }

    boolean g(int i) {
        return (i + 1) % this.f3530c.f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3530c.g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.f3530c.f;
    }

    int h() {
        return (this.f3530c.i() + this.f3530c.g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    int i(int i) {
        return (i - this.f3530c.i()) + 1;
    }

    boolean j(int i) {
        return i >= b() && i <= h();
    }
}
