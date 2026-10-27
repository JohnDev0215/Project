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
    private final CalendarConstraints f4203c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final DateSelector<?> f4204d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final MaterialCalendar.k f4205e;
    private final int f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f4206b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f4206b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f4206b.getAdapter().j(i)) {
                g.this.f4205e.a(this.f4206b.getAdapter().getItem(i).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {
        final TextView t;
        final MaterialCalendarGridView u;

        b(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(c.a.a.a.f.k);
            this.t = textView;
            t.k0(textView, true);
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(c.a.a.a.f.g);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    g(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, MaterialCalendar.k kVar) {
        Month monthZ = calendarConstraints.z();
        Month monthW = calendarConstraints.w();
        Month monthY = calendarConstraints.y();
        if (monthZ.compareTo(monthY) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (monthY.compareTo(monthW) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.f = (f.f4199b * MaterialCalendar.M1(context)) + (e.z1(context) ? MaterialCalendar.M1(context) : 0);
        this.f4203c = calendarConstraints;
        this.f4204d = dateSelector;
        this.f4205e = kVar;
        u(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void l(b bVar, int i) {
        Month monthB = this.f4203c.z().B(i);
        bVar.t.setText(monthB.z());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(c.a.a.a.f.g);
        if (materialCalendarGridView.getAdapter() == null || !monthB.equals(materialCalendarGridView.getAdapter().f4200c)) {
            f fVar = new f(monthB, this.f4204d, this.f4203c);
            materialCalendarGridView.setNumColumns(monthB.f);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public b n(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.o, viewGroup, false);
        if (!e.z1(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f4203c.x();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return this.f4203c.z().B(i).A();
    }

    Month x(int i) {
        return this.f4203c.z().B(i);
    }

    CharSequence y(int i) {
        return x(i).z();
    }

    int z(Month month) {
        return this.f4203c.z().C(month);
    }
}
