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
    private final f<?> f4499c;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f4500e;

        a(int i7) {
            this.f4500e = i7;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            q.this.f4499c.g2(q.this.f4499c.Y1().k(Month.c(this.f4500e, q.this.f4499c.a2().f4384g)));
            q.this.f4499c.h2(f.k.DAY);
        }
    }

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public static class b extends RecyclerView.c0 {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        final TextView f4502t;

        b(TextView textView) {
            super(textView);
            this.f4502t = textView;
        }
    }

    q(f<?> fVar) {
        this.f4499c = fVar;
    }

    private View.OnClickListener C(int i7) {
        return new a(i7);
    }

    int D(int i7) {
        return i7 - this.f4499c.Y1().s().f4385h;
    }

    int E(int i7) {
        return this.f4499c.Y1().s().f4385h + i7;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public void q(b bVar, int i7) {
        int iE = E(i7);
        String string = bVar.f4502t.getContext().getString(o2.j.mtrl_picker_navigate_to_year_description);
        bVar.f4502t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iE)));
        bVar.f4502t.setContentDescription(String.format(string, Integer.valueOf(iE)));
        com.google.android.material.datepicker.b bVarZ1 = this.f4499c.Z1();
        Calendar calendarJ = p.j();
        com.google.android.material.datepicker.a aVar = calendarJ.get(1) == iE ? bVarZ1.f4418f : bVarZ1.f4416d;
        Iterator<Long> it = this.f4499c.b2().o().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iE) {
                aVar = bVarZ1.f4417e;
            }
        }
        aVar.d(bVar.f4502t);
        bVar.f4502t.setOnClickListener(C(iE));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public b s(ViewGroup viewGroup, int i7) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(o2.h.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int e() {
        return this.f4499c.Y1().t();
    }
}
