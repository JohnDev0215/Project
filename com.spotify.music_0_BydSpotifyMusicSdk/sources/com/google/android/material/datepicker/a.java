package com.google.android.material.datepicker;

import android.view.View;
import android.widget.AdapterView;
import p.c60;
import p.cl1;
import p.p01;

/* JADX INFO: loaded from: classes.dex */
public final class a implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MaterialCalendarGridView c;
    public final /* synthetic */ c e;

    public a(c cVar, MaterialCalendarGridView materialCalendarGridView) {
        this.e = cVar;
        this.c = materialCalendarGridView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        MaterialCalendarGridView materialCalendarGridView = this.c;
        cl1 cl1VarA = materialCalendarGridView.a();
        if (i < cl1VarA.c.c() || i > cl1VarA.b()) {
            return;
        }
        p01 p01Var = this.e.e;
        long jLongValue = materialCalendarGridView.a().getItem(i).longValue();
        Object obj = p01Var.e;
        if (jLongValue < ((c60) ((MaterialCalendar) obj).d0.k).c) {
            return;
        }
        ((MaterialCalendar) obj).getClass();
        throw null;
    }
}
