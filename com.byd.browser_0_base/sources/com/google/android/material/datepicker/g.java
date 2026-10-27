package com.google.android.material.datepicker;

import android.widget.BaseAdapter;
import android.widget.TextView;
import defpackage.hl;
import defpackage.il;
import defpackage.qk3;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends BaseAdapter {
    public static final int j = qk3.d(null).getMaximum(4);
    public static final int k = (qk3.d(null).getMaximum(7) + qk3.d(null).getMaximum(5)) - 1;
    public final Month e;
    public final DateSelector<?> f;
    public Collection<Long> g;
    public il h;
    public final CalendarConstraints i;

    public g(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.e = month;
        this.f = dateSelector;
        this.i = calendarConstraints;
        this.g = dateSelector.X();
    }

    public final int a() {
        int firstDayOfWeek = this.i.i;
        Month month = this.e;
        Calendar calendar = month.e;
        int i = calendar.get(7);
        if (firstDayOfWeek <= 0) {
            firstDayOfWeek = calendar.getFirstDayOfWeek();
        }
        int i2 = i - firstDayOfWeek;
        return i2 < 0 ? i2 + month.h : i2;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Long getItem(int i) {
        if (i < a()) {
            return null;
        }
        int iA = a();
        Month month = this.e;
        if (i > (iA + month.i) - 1) {
            return null;
        }
        int iA2 = (i - a()) + 1;
        Calendar calendarB = qk3.b(month.e);
        calendarB.set(5, iA2);
        return Long.valueOf(calendarB.getTimeInMillis());
    }

    public final void d(TextView textView, long j2) {
        hl hlVar;
        if (textView == null) {
            return;
        }
        boolean z = false;
        if (this.i.g.P(j2)) {
            textView.setEnabled(true);
            Iterator<Long> it = this.f.X().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (qk3.a(j2) == qk3.a(it.next().longValue())) {
                    z = true;
                    break;
                }
            }
            hlVar = z ? (hl) this.h.b : qk3.c().getTimeInMillis() == j2 ? (hl) this.h.c : (hl) this.h.a;
        } else {
            textView.setEnabled(false);
            hlVar = (hl) this.h.g;
        }
        hlVar.b(textView);
    }

    public final void e(MaterialCalendarGridView materialCalendarGridView, long j2) {
        Month monthF = Month.f(j2);
        Month month = this.e;
        if (monthF.equals(month)) {
            Calendar calendarB = qk3.b(month.e);
            calendarB.setTimeInMillis(j2);
            d((TextView) materialCalendarGridView.getChildAt((materialCalendarGridView.getAdapter().a() + (calendarB.get(5) - 1)) - materialCalendarGridView.getFirstVisiblePosition()), j2);
        }
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return k;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i / this.e.h;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00b8  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.g.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }
}
