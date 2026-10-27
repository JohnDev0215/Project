package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class j extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int f3991b = p.r().getMaximum(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Month f3992c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final DateSelector<?> f3993d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    b f3994e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final CalendarConstraints f3995f;

    j(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f3992c = month;
        this.f3993d = dateSelector;
        this.f3995f = calendarConstraints;
    }

    private void e(Context context) {
        if (this.f3994e == null) {
            this.f3994e = new b(context);
        }
    }

    int a(int i2) {
        return b() + (i2 - 1);
    }

    int b() {
        return this.f3992c.j();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i2) {
        if (i2 < this.f3992c.j() || i2 > h()) {
            return null;
        }
        return Long.valueOf(this.f3992c.l(i(i2)));
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.j.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    boolean f(int i2) {
        return i2 % this.f3992c.f3926f == 0;
    }

    boolean g(int i2) {
        return (i2 + 1) % this.f3992c.f3926f == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f3992c.f3927g + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2 / this.f3992c.f3926f;
    }

    int h() {
        return (this.f3992c.j() + this.f3992c.f3927g) - 1;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    int i(int i2) {
        return (i2 - this.f3992c.j()) + 1;
    }

    boolean j(int i2) {
        return i2 >= b() && i2 <= h();
    }
}
