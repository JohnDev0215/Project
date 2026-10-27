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
import c.g.k.t;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class g extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CalendarConstraints f3532c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final DateSelector<?> f3533d;
    private final MaterialCalendar.k e;
    private final int f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f3534b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f3534b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f3534b.getAdapter().j(i)) {
                g.this.e.a(this.f3534b.getAdapter().getItem(i).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {
        final TextView t;
        final MaterialCalendarGridView u;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(d.b.a.a.f.k);
            this.t = textView;
            t.k0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(d.b.a.a.f.g);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    g(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.k kVar) {
        Month monthK = calendarConstraints.k();
        Month monthH = calendarConstraints.h();
        Month monthJ = calendarConstraints.j();
        if (monthK.compareTo(monthJ) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthJ.compareTo(monthH) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f = (f.f3529b * MaterialCalendar.z1(context)) + (e.m1(context) ? MaterialCalendar.z1(context) : 0);
        this.f3532c = calendarConstraints;
        this.f3533d = dateSelector;
        this.e = kVar;
        t(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public b m(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(d.b.a.a.h.o, viewGroup, false);
        if (!e.m1(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3532c.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return this.f3532c.k().m(i).l();
    }

    Month w(int i) {
        return this.f3532c.k().m(i);
    }

    CharSequence x(int i) {
        return w(i).k();
    }

    int y(Month month) {
        return this.f3532c.k().n(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void k(b bVar, int i) {
        Month monthM = this.f3532c.k().m(i);
        bVar.t.setText(monthM.k());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(d.b.a.a.f.g);
        if (materialCalendarGridView.getAdapter() == null || !monthM.equals(materialCalendarGridView.getAdapter().f3530c)) {
            f fVar = new f(monthM, this.f3533d, this.f3532c);
            materialCalendarGridView.setNumColumns(monthM.f);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }
}
