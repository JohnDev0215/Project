package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.g;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class q extends RecyclerView.g<b> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g<?> f3082c;

    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3083b;

        a(int i) {
            this.f3083b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            q.this.f3082c.P1(q.this.f3082c.I1().u(i.v(this.f3083b, q.this.f3082c.K1().f3070d)));
            q.this.f3082c.Q1(g.k.DAY);
        }
    }

    public static class b extends RecyclerView.d0 {
        final TextView t;

        b(TextView textView) {
            super(textView);
            this.t = textView;
        }
    }

    q(g<?> gVar) {
        this.f3082c = gVar;
    }

    private View.OnClickListener y(int i) {
        return new a(i);
    }

    int A(int i) {
        return this.f3082c.I1().z().e + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public void m(b bVar, int i) {
        int iA = A(i);
        String string = bVar.t.getContext().getString(b.a.a.a.j.l);
        bVar.t.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(iA)));
        bVar.t.setContentDescription(String.format(string, Integer.valueOf(iA)));
        c cVarJ1 = this.f3082c.J1();
        Calendar calendarJ = p.j();
        com.google.android.material.datepicker.b bVar2 = calendarJ.get(1) == iA ? cVarJ1.f : cVarJ1.f3045d;
        Iterator<Long> it = this.f3082c.L1().s().iterator();
        while (it.hasNext()) {
            calendarJ.setTimeInMillis(it.next().longValue());
            if (calendarJ.get(1) == iA) {
                bVar2 = cVarJ1.e;
            }
        }
        bVar2.d(bVar.t);
        bVar.t.setOnClickListener(y(iA));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public b o(ViewGroup viewGroup, int i) {
        return new b((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(b.a.a.a.h.k, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int c() {
        return this.f3082c.I1().A();
    }

    int z(int i) {
        return i - this.f3082c.I1().z().e;
    }
}
