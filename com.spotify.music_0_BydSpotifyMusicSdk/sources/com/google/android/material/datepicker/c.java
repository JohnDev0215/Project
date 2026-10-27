package com.google.android.material.datepicker;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.spotify.music.R;
import java.util.Calendar;
import p.bl1;
import p.bs2;
import p.cl1;
import p.ip;
import p.mf1;
import p.n22;
import p.p01;
import p.q12;
import p.z12;

/* JADX INFO: loaded from: classes.dex */
public final class c extends q12 {
    public final Context c;
    public final ip d;
    public final p01 e;
    public final int f;

    public c(ContextThemeWrapper contextThemeWrapper, ip ipVar, p01 p01Var) {
        bl1 bl1Var = ipVar.c;
        bl1 bl1Var2 = ipVar.l;
        if (bl1Var.c.compareTo(bl1Var2.c) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (bl1Var2.c.compareTo(ipVar.e.c) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int i = cl1.l;
        int i2 = MaterialCalendar.l0;
        int dimensionPixelSize = contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * i;
        int dimensionPixelSize2 = mf1.J(contextThemeWrapper, android.R.attr.windowFullscreen) ? contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) : 0;
        this.c = contextThemeWrapper;
        this.f = dimensionPixelSize + dimensionPixelSize2;
        this.d = ipVar;
        this.e = p01Var;
        if (this.a.a()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.b = true;
    }

    @Override // p.q12
    public final int a() {
        return this.d.n;
    }

    @Override // p.q12
    public final long b(int i) {
        Calendar calendarA = bs2.a(this.d.c.c);
        calendarA.add(2, i);
        return new bl1(calendarA).c.getTimeInMillis();
    }

    @Override // p.q12
    public final void c(n22 n22Var, int i) {
        b bVar = (b) n22Var;
        ip ipVar = this.d;
        Calendar calendarA = bs2.a(ipVar.c.c);
        calendarA.add(2, i);
        bl1 bl1Var = new bl1(calendarA);
        bVar.t.setText(bl1Var.d(bVar.a.getContext()));
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) bVar.u.findViewById(R.id.month_grid);
        if (materialCalendarGridView.a() == null || !bl1Var.equals(materialCalendarGridView.a().c)) {
            new cl1(bl1Var, ipVar);
            throw null;
        }
        materialCalendarGridView.invalidate();
        materialCalendarGridView.a().getClass();
        throw null;
    }

    @Override // p.q12
    public final n22 d(RecyclerView recyclerView) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, (ViewGroup) recyclerView, false);
        if (!mf1.J(recyclerView.getContext(), android.R.attr.windowFullscreen)) {
            return new b(linearLayout, false);
        }
        linearLayout.setLayoutParams(new z12(-1, this.f));
        return new b(linearLayout, true);
    }
}
