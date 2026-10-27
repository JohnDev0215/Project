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
    private final MaterialCalendar<?> f4212c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4213b;

        a(int i) {
            this.f4213b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.f4212c.P1(m.this.f4212c.I1().u(Month.v(this.f4213b, m.this.f4212c.K1().f4178d)));
            m.this.f4212c.Q1(MaterialCalendar.CalendarSelector.DAY);
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
        this.f4212c = materialCalendar;
    }

    private View.OnClickListener x(int i) {
        return new a(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public void l(b bVar, int i) {
        int iZ = z(i);
        String string = bVar.t.getContext().getString(c.a.a.a.j.o);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iZ)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iZ)));
        com.google.android.material.datepicker.b bVarJ1 = this.f4212c.J1();
        Calendar calendarJ = l.j();
        com.google.android.material.datepicker.a aVar = calendarJ.get(1) == iZ ? bVarJ1.f : bVarJ1.f4193d;
        Iterator<Long> it = this.f4212c.L1().s().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iZ) {
                aVar = bVarJ1.f4194e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(x(iZ));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public b n(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.q, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f4212c.I1().A();
    }

    int y(int i) {
        return i - this.f4212c.I1().z().f4179e;
    }

    int z(int i) {
        return this.f4212c.I1().z().f4179e + i;
    }
}
