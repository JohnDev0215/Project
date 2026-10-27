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
    private final MaterialCalendar<?> f3540c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3541b;

        a(int i) {
            this.f3541b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f3540c.C1(m.this.f3540c.v1().f(Month.g(this.f3541b, m.this.f3540c.x1().f3513d)));
            m.this.f3540c.D1(MaterialCalendar.CalendarSelector.DAY);
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
        this.f3540c = materialCalendar;
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
        return this.f3540c.v1().l();
    }

    int x(int i) {
        return i - this.f3540c.v1().k().e;
    }

    int y(int i) {
        return this.f3540c.v1().k().e + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public void k(b bVar, int i) {
        int iY = y(i);
        String string = bVar.t.getContext().getString(d.b.a.a.j.o);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iY)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iY)));
        com.google.android.material.datepicker.b bVarW1 = this.f3540c.w1();
        Calendar calendarJ = l.j();
        com.google.android.material.datepicker.a aVar = calendarJ.get(1) == iY ? bVarW1.f : bVarW1.f3525d;
        Iterator<Long> it = this.f3540c.y1().c().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iY) {
                aVar = bVarW1.e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(w(iY));
    }
}
