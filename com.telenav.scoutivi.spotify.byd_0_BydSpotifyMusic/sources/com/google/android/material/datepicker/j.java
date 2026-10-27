package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: MonthAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class j extends BaseAdapter {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final int f4478k = p.l().getMaximum(4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Month f4479e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final DateSelector<?> f4480g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Collection<Long> f4481h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    b f4482i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final CalendarConstraints f4483j;

    j(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.f4479e = month;
        this.f4480g = dateSelector;
        this.f4483j = calendarConstraints;
        this.f4481h = dateSelector.o();
    }

    private void e(Context context) {
        if (this.f4482i == null) {
            this.f4482i = new b(context);
        }
    }

    private boolean h(long j7) {
        Iterator<Long> it = this.f4480g.o().iterator();
        while (it.hasNext()) {
            if (p.a(j7) == p.a(it.next().longValue())) {
                return true;
            }
        }
        return false;
    }

    private void k(TextView textView, long j7) {
        a aVar;
        if (textView == null) {
            return;
        }
        if (this.f4483j.m().g(j7)) {
            textView.setEnabled(true);
            aVar = h(j7) ? this.f4482i.f4414b : p.j().getTimeInMillis() == j7 ? this.f4482i.f4415c : this.f4482i.f4413a;
        } else {
            textView.setEnabled(false);
            aVar = this.f4482i.f4419g;
        }
        aVar.d(textView);
    }

    private void l(MaterialCalendarGridView materialCalendarGridView, long j7) {
        if (Month.h(j7).equals(this.f4479e)) {
            k((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().a(this.f4479e.p(j7)) - materialCalendarGridView.getFirstVisiblePosition()), j7);
        }
    }

    int a(int i7) {
        return b() + (i7 - 1);
    }

    int b() {
        return this.f4479e.k();
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Long getItem(int i7) {
        if (i7 < this.f4479e.k() || i7 > i()) {
            return null;
        }
        return Long.valueOf(this.f4479e.m(j(i7)));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0075  */
    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.e(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = o2.h.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.b()
            int r7 = r6 - r7
            if (r7 < 0) goto L75
            com.google.android.material.datepicker.Month r8 = r5.f4479e
            int r2 = r8.f4387j
            if (r7 < r2) goto L2d
            goto L75
        L2d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)
            r3[r1] = r4
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            com.google.android.material.datepicker.Month r8 = r5.f4479e
            long r7 = r8.m(r7)
            com.google.android.material.datepicker.Month r3 = r5.f4479e
            int r3 = r3.f4385h
            com.google.android.material.datepicker.Month r4 = com.google.android.material.datepicker.Month.j()
            int r4 = r4.f4385h
            if (r3 != r4) goto L67
            java.lang.String r7 = com.google.android.material.datepicker.d.g(r7)
            r0.setContentDescription(r7)
            goto L6e
        L67:
            java.lang.String r7 = com.google.android.material.datepicker.d.l(r7)
            r0.setContentDescription(r7)
        L6e:
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L7d
        L75:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
        L7d:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L84
            return r0
        L84:
            long r6 = r6.longValue()
            r5.k(r0, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.j.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    boolean f(int i7) {
        return i7 % this.f4479e.f4386i == 0;
    }

    boolean g(int i7) {
        return (i7 + 1) % this.f4479e.f4386i == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f4479e.f4387j + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i7) {
        return i7 / this.f4479e.f4386i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    int i() {
        return (this.f4479e.k() + this.f4479e.f4387j) - 1;
    }

    int j(int i7) {
        return (i7 - this.f4479e.k()) + 1;
    }

    public void m(MaterialCalendarGridView materialCalendarGridView) {
        Iterator<Long> it = this.f4481h.iterator();
        while (it.hasNext()) {
            l(materialCalendarGridView, it.next().longValue());
        }
        DateSelector<?> dateSelector = this.f4480g;
        if (dateSelector != null) {
            Iterator<Long> it2 = dateSelector.o().iterator();
            while (it2.hasNext()) {
                l(materialCalendarGridView, it2.next().longValue());
            }
            this.f4481h = this.f4480g.o();
        }
    }

    boolean n(int i7) {
        return i7 >= b() && i7 <= i();
    }
}
