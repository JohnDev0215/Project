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
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class g extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CalendarConstraints f3810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final DateSelector<?> f3811d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final MaterialCalendar.k f3812e;
    private final int f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f3813b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f3813b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f3813b.getAdapter().j(i)) {
                g.this.f3812e.a(this.f3813b.getAdapter().getItem(i).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {
        final TextView t;
        final MaterialCalendarGridView u;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(c.b.a.a.f.k);
            this.t = textView;
            t.l0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(c.b.a.a.f.g);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    g(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.k kVar) {
        Month monthK = calendarConstraints.k();
        Month monthF = calendarConstraints.f();
        Month monthJ = calendarConstraints.j();
        if (monthK.compareTo(monthJ) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthJ.compareTo(monthF) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f = (f.f3806b * MaterialCalendar.n(context)) + (e.a(context) ? MaterialCalendar.n(context) : 0);
        this.f3810c = calendarConstraints;
        this.f3811d = dateSelector;
        this.f3812e = kVar;
        s(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3810c.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return this.f3810c.k().p(i).o();
    }

    Month v(int i) {
        return this.f3810c.k().p(i);
    }

    CharSequence w(int i) {
        return v(i).n();
    }

    int x(Month month) {
        return this.f3810c.k().q(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i) {
        Month monthP = this.f3810c.k().p(i);
        bVar.t.setText(monthP.n());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(c.b.a.a.f.g);
        if (materialCalendarGridView.getAdapter() == null || !monthP.equals(materialCalendarGridView.getAdapter().f3807c)) {
            f fVar = new f(monthP, this.f3811d, this.f3810c);
            materialCalendarGridView.setNumColumns(monthP.f);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(c.b.a.a.h.o, viewGroup, false);
        if (!e.a(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f));
        return new b(linearLayout, true);
    }
}
