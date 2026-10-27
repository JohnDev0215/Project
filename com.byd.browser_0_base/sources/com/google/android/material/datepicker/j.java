package com.google.android.material.datepicker;

import android.view.View;
import java.util.Calendar;

/* JADX INFO: compiled from: YearGridAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ k f;

    public j(k kVar, int i) {
        this.f = kVar;
        this.e = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        k kVar = this.f;
        Month monthA = Month.a(this.e, kVar.h.d0.f);
        a<?> aVar = kVar.h;
        CalendarConstraints calendarConstraints = aVar.c0;
        Month month = calendarConstraints.e;
        Calendar calendar = month.e;
        Calendar calendar2 = monthA.e;
        if (calendar2.compareTo(calendar) < 0) {
            monthA = month;
        } else {
            Month month2 = calendarConstraints.f;
            if (calendar2.compareTo(month2.e) > 0) {
                monthA = month2;
            }
        }
        aVar.I0(monthA);
        aVar.J0(1);
    }
}
