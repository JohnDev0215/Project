package com.google.android.material.datepicker;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.qk3;
import java.util.Calendar;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements View.OnClickListener {
    public final /* synthetic */ i e;
    public final /* synthetic */ a f;

    public e(a aVar, i iVar) {
        this.f = aVar;
        this.e = iVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        a aVar = this.f;
        int iD = ((LinearLayoutManager) aVar.h0.getLayoutManager()).d() - 1;
        if (iD >= 0) {
            Calendar calendarB = qk3.b(this.e.h.e.e);
            calendarB.add(2, iD);
            aVar.I0(new Month(calendarB));
        }
    }
}
