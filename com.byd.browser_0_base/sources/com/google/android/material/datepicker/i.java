package com.google.android.material.datepicker;

import android.content.res.Resources;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.datepicker.a;
import defpackage.in3;
import defpackage.nm3;
import defpackage.om3;
import defpackage.qk3;
import java.util.Calendar;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends RecyclerView.e<a> {
    public final CalendarConstraints h;
    public final DateSelector<?> i;
    public final a.d j;
    public final int k;

    /* JADX INFO: compiled from: MonthsPagerAdapter.java */
    public static class a extends RecyclerView.a0 {
        public final TextView y;
        public final MaterialCalendarGridView z;

        public a(LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R$id.month_title);
            this.y = textView;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            new nm3(androidx.core.R$id.tag_accessibility_heading).e(textView, Boolean.TRUE);
            this.z = (MaterialCalendarGridView) linearLayout.findViewById(R$id.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    public i(ContextThemeWrapper contextThemeWrapper, DateSelector dateSelector, CalendarConstraints calendarConstraints, a.c cVar) {
        Calendar calendar = calendarConstraints.e.e;
        Month month = calendarConstraints.h;
        if (calendar.compareTo(month.e) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (month.e.compareTo(calendarConstraints.f.e) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int i = g.j;
        int i2 = com.google.android.material.datepicker.a.k0;
        Resources resources = contextThemeWrapper.getResources();
        int i3 = R$dimen.mtrl_calendar_day_height;
        this.k = (resources.getDimensionPixelSize(i3) * i) + (f.P0(contextThemeWrapper) ? contextThemeWrapper.getResources().getDimensionPixelSize(i3) : 0);
        this.h = calendarConstraints;
        this.i = dateSelector;
        this.j = cVar;
        B(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h.k;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final long l(int i) {
        Calendar calendarB = qk3.b(this.h.e.e);
        calendarB.add(2, i);
        return new Month(calendarB).e.getTimeInMillis();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        a aVar = (a) a0Var;
        CalendarConstraints calendarConstraints = this.h;
        Calendar calendarB = qk3.b(calendarConstraints.e.e);
        calendarB.add(2, i);
        Month month = new Month(calendarB);
        aVar.y.setText(month.L());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) aVar.z.findViewById(R$id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !month.equals(materialCalendarGridView.getAdapter().e)) {
            g gVar = new g(month, this.i, calendarConstraints);
            materialCalendarGridView.setNumColumns(month.h);
            materialCalendarGridView.setAdapter((ListAdapter) gVar);
        } else {
            materialCalendarGridView.invalidate();
            g gVarA = materialCalendarGridView.getAdapter();
            Iterator<Long> it = gVarA.g.iterator();
            while (it.hasNext()) {
                gVarA.e(materialCalendarGridView, it.next().longValue());
            }
            DateSelector<?> dateSelector = gVarA.f;
            if (dateSelector != null) {
                Iterator<Long> it2 = dateSelector.X().iterator();
                while (it2.hasNext()) {
                    gVarA.e(materialCalendarGridView, it2.next().longValue());
                }
                gVarA.g = dateSelector.X();
            }
        }
        materialCalendarGridView.setOnItemClickListener(new h(this, materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.mtrl_calendar_month_labeled, (ViewGroup) recyclerView, false);
        if (!f.P0(recyclerView.getContext())) {
            return new a(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.k));
        return new a(linearLayout, true);
    }
}
