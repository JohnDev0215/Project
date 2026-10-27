package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.f;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: YearGridAdapter.java */
/* JADX INFO: loaded from: classes.dex */
class q extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f<?> f4006c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4007b;

        a(int i2) {
            this.f4007b = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            q.this.f4006c.I1(q.this.f4006c.A1().j(Month.c(this.f4007b, q.this.f4006c.C1().f3924d)));
            q.this.f4006c.J1(f.k.DAY);
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

    q(f<?> fVar) {
        this.f4006c = fVar;
    }

    private View.OnClickListener v(int i2) {
        return new a(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f4006c.A1().t();
    }

    int w(int i2) {
        return i2 - this.f4006c.A1().s().f3925e;
    }

    int x(int i2) {
        return this.f4006c.A1().s().f3925e + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
    public void j(b bVar, int i2) {
        int iX = x(i2);
        String string = bVar.t.getContext().getString(d.b.a.a.j.mtrl_picker_navigate_to_year_description);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iX)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iX)));
        com.google.android.material.datepicker.b bVarB1 = this.f4006c.B1();
        Calendar calendarP = p.p();
        com.google.android.material.datepicker.a aVar = calendarP.get(1) == iX ? bVarB1.f3955f : bVarB1.f3953d;
        Iterator<Long> it = this.f4006c.D1().o().iterator();
        while (it.hasNext()) {
            calendarP.setTimeInMillis(it.next().longValue());
            if (calendarP.get(1) == iX) {
                aVar = bVarB1.f3954e;
            }
        }
        aVar.d(bVar.t);
        bVar.t.setOnClickListener(v(iX));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public b l(ViewGroup viewGroup, int i2) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(d.b.a.a.h.mtrl_calendar_year, viewGroup, false));
    }
}
