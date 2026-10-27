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
import com.google.android.material.datepicker.f;
import i0.u;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class k extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f4484c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final CalendarConstraints f4485d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final DateSelector<?> f4486e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final f.l f4487f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f4488g;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f4489e;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f4489e = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j7) {
            if (this.f4489e.getAdapter().n(i7)) {
                k.this.f4487f.a(this.f4489e.getAdapter().getItem(i7).longValue());
            }
        }
    }

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        final TextView f4491t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        final MaterialCalendarGridView f4492u;

        b(LinearLayout linearLayout, boolean z6) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(o2.f.month_title);
            this.f4491t = textView;
            u.l0(textView, true);
            this.f4492u = (MaterialCalendarGridView) linearLayout.findViewById(o2.f.month_grid);
            if (z6) {
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
        int iC2 = j.f4478k * f.c2(context);
        int iC22 = g.q2(context) ? f.c2(context) : 0;
        this.f4484c = context;
        this.f4488g = iC2 + iC22;
        this.f4485d = calendarConstraints;
        this.f4486e = dateSelector;
        this.f4487f = lVar;
        z(true);
    }

    Month C(int i7) {
        return this.f4485d.s().s(i7);
    }

    CharSequence D(int i7) {
        return C(i7).q(this.f4484c);
    }

    int E(Month month) {
        return this.f4485d.s().t(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public void q(b bVar, int i7) {
        Month monthS = this.f4485d.s().s(i7);
        bVar.f4491t.setText(monthS.q(bVar.f2857a.getContext()));
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.f4492u.findViewById(o2.f.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthS.equals(materialCalendarGridView.getAdapter().f4479e)) {
            j jVar = new j(monthS, this.f4486e, this.f4485d);
            materialCalendarGridView.setNumColumns(monthS.f4386i);
            materialCalendarGridView.setAdapter((ListAdapter) jVar);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter().m(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public b s(ViewGroup viewGroup, int i7) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(o2.h.mtrl_calendar_month_labeled, viewGroup, false);
        if (!g.q2(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f4488g));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int e() {
        return this.f4485d.q();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long f(int i7) {
        return this.f4485d.s().s(i7).r();
    }
}
