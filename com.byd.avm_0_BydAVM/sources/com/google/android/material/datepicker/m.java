package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: YearGridAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class m extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final MaterialCalendar<?> f2347c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2348b;

        a(int i) {
            this.f2348b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f2347c.q(m.this.f2347c.j().f(Month.g(this.f2348b, m.this.f2347c.l().f2316d)));
            m.this.f2347c.r(MaterialCalendar.CalendarSelector.DAY);
        }
    }

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public static class b extends RecyclerView.c0 {
        final TextView t;

        b(TextView textView) {
            super(textView);
            this.t = textView;
        }
    }

    m(MaterialCalendar<?> materialCalendar) {
        this.f2347c = materialCalendar;
    }

    private View.OnClickListener v(int i) {
        return new a(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f2347c.j().l();
    }

    int w(int i) {
        return i - this.f2347c.j().k().e;
    }

    int x(int i) {
        return this.f2347c.j().k().e + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i) {
        int iX = x(i);
        String string = bVar.t.getContext().getString(c.a.a.a.j.o);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iX)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iX)));
        com.google.android.material.datepicker.b bVarK = this.f2347c.k();
        Calendar calendarJ = l.j();
        com.google.android.material.datepicker.a aVar = calendarJ.get(1) == iX ? bVarK.f : bVarK.f2328d;
        Iterator<Long> it = this.f2347c.m().e().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iX) {
                aVar = bVarK.e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(v(iX));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.q, viewGroup, false));
    }
}
