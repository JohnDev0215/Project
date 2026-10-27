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
    private final CalendarConstraints f3583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final DateSelector<?> f3584d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final MaterialCalendar.k f3585e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3586f;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    class a implements AdapterView.OnItemClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialCalendarGridView f3587b;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.f3587b = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f3587b.getAdapter().j(i)) {
                g.this.f3585e.a(this.f3587b.getAdapter().getItem(i).longValue());
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
            this.u = (MaterialCalendarGridView) linearLayout.findViewById(d.b.a.a.f.f4723g);
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
        this.f3586f = (f.f3578b * MaterialCalendar.n(context)) + (e.a(context) ? MaterialCalendar.n(context) : 0);
        this.f3583c = calendarConstraints;
        this.f3584d = dateSelector;
        this.f3585e = kVar;
        t(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public b m(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(d.b.a.a.h.o, viewGroup, false);
        if (!e.a(viewGroup.getContext())) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.p(-1, this.f3586f));
        return new b(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3583c.i();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long d(int i) {
        return this.f3583c.k().m(i).l();
    }

    Month w(int i) {
        return this.f3583c.k().m(i);
    }

    CharSequence x(int i) {
        return w(i).k();
    }

    int y(Month month) {
        return this.f3583c.k().n(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void k(b bVar, int i) {
        Month monthM = this.f3583c.k().m(i);
        bVar.t.setText(monthM.k());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(d.b.a.a.f.f4723g);
        if (materialCalendarGridView.getAdapter() == null || !monthM.equals(materialCalendarGridView.getAdapter().f3579c)) {
            f fVar = new f(monthM, this.f3584d, this.f3583c);
            materialCalendarGridView.setNumColumns(monthM.f3548f);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }
}
