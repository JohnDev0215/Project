package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import b.g.k.t;
import com.google.android.material.datepicker.f;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class k extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CalendarConstraints f3996c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final DateSelector<?> f3997d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final f.l f3998e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3999f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f4000b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f4000b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.f4000b.getAdapter().j(i2)) {
                k.this.f3998e.a(this.f4000b.getAdapter().getItem(i2).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {
        final TextView t;
        final MaterialCalendarGridView u;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(d.b.a.a.f.month_title);
            this.t = textView;
            t.k0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(d.b.a.a.f.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    k(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, f.l lVar) {
        Month monthS = calendarConstraints.s();
        Month monthP = calendarConstraints.p();
        Month monthR = calendarConstraints.r();
        if (monthS.compareTo(monthR) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthR.compareTo(monthP) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f3999f = (j.f3991b * f.E1(context)) + (g.O1(context) ? f.E1(context) : 0);
        this.f3996c = calendarConstraints;
        this.f3997d = dateSelector;
        this.f3998e = lVar;
        s(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3996c.q();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i2) {
        return this.f3996c.s().r(i2).q();
    }

    Month v(int i2) {
        return this.f3996c.s().r(i2);
    }

    CharSequence w(int i2) {
        return v(i2).p();
    }

    int x(Month month) {
        return this.f3996c.s().s(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i2) {
        Month monthR = this.f3996c.s().r(i2);
        bVar.t.setText(monthR.p());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(d.b.a.a.f.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthR.equals(materialCalendarGridView.getAdapter().f3992c)) {
            j jVar = new j(monthR, this.f3997d, this.f3996c);
            materialCalendarGridView.setNumColumns(monthR.f3926f);
            materialCalendarGridView.setAdapter((ListAdapter) jVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(d.b.a.a.h.mtrl_calendar_month_labeled, viewGroup, false);
        if (!g.O1(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f3999f));
        return new b(linearLayout, true);
    }
}
