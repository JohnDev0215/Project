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
    private final MaterialCalendar<?> f3594c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3595b;

        a(int i) {
            this.f3595b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f3594c.q(m.this.f3594c.j().e(Month.g(this.f3595b, m.this.f3594c.l().f3546d)));
            m.this.f3594c.r(MaterialCalendar.CalendarSelector.DAY);
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
        this.f3594c = materialCalendar;
    }

    private View.OnClickListener w(int i) {
        return new a(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public b m(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(d.b.a.a.h.q, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3594c.j().l();
    }

    int x(int i) {
        return i - this.f3594c.j().k().f3547e;
    }

    int y(int i) {
        return this.f3594c.j().k().f3547e + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void k(b bVar, int i) {
        int iY = y(i);
        String string = bVar.t.getContext().getString(d.b.a.a.j.o);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iY)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iY)));
        com.google.android.material.datepicker.b bVarK = this.f3594c.k();
        Calendar calendarJ = l.j();
        com.google.android.material.datepicker.a aVar = calendarJ.get(1) == iY ? bVarK.f3568f : bVarK.f3566d;
        Iterator<Long> it = this.f3594c.m().f().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iY) {
                aVar = bVarK.f3567e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(w(iY));
    }
}
