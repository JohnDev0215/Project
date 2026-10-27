package com.google.android.material.datepicker;

import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.a;
import defpackage.ks1;
import java.util.Iterator;

/* JADX INFO: compiled from: MonthsPagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MaterialCalendarGridView e;
    public final /* synthetic */ i f;

    public h(i iVar, MaterialCalendarGridView materialCalendarGridView) {
        this.f = iVar;
        this.e = materialCalendarGridView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MaterialCalendarGridView materialCalendarGridView = this.e;
        g adapter = materialCalendarGridView.getAdapter();
        if (i >= adapter.a() && i <= (adapter.a() + adapter.e.i) + (-1)) {
            a.d dVar = this.f.j;
            long jLongValue = materialCalendarGridView.getAdapter().getItem(i).longValue();
            a aVar = a.this;
            if (aVar.c0.g.P(jLongValue)) {
                aVar.b0.h();
                Iterator it = aVar.Z.iterator();
                while (it.hasNext()) {
                    ((ks1) it.next()).a(aVar.b0.a0());
                }
                aVar.h0.getAdapter().o();
                RecyclerView recyclerView = aVar.g0;
                if (recyclerView != null) {
                    recyclerView.getAdapter().o();
                }
            }
        }
    }
}
