package com.google.android.material.datepicker;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import defpackage.qk3;
import java.util.Calendar;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends RecyclerView.q {
    public final /* synthetic */ i e;
    public final /* synthetic */ MaterialButton f;
    public final /* synthetic */ a g;

    public c(a aVar, i iVar, MaterialButton materialButton) {
        this.g = aVar;
        this.e = iVar;
        this.f = materialButton;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void a(int i, RecyclerView recyclerView) {
        if (i == 0) {
            recyclerView.announceForAccessibility(this.f.getText());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        a aVar = this.g;
        int iB = i < 0 ? ((LinearLayoutManager) aVar.h0.getLayoutManager()).b() : ((LinearLayoutManager) aVar.h0.getLayoutManager()).d();
        i iVar = this.e;
        Calendar calendarB = qk3.b(iVar.h.e.e);
        calendarB.add(2, iB);
        aVar.d0 = new Month(calendarB);
        Calendar calendarB2 = qk3.b(iVar.h.e.e);
        calendarB2.add(2, iB);
        this.f.setText(new Month(calendarB2).L());
    }
}
