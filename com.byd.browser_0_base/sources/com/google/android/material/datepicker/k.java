package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import defpackage.hl;
import defpackage.il;
import defpackage.qk3;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: YearGridAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends RecyclerView.e<a> {
    public final com.google.android.material.datepicker.a<?> h;

    /* JADX INFO: compiled from: YearGridAdapter.java */
    public static class a extends RecyclerView.a0 {
        public final TextView y;

        public a(TextView textView) {
            super(textView);
            this.y = textView;
        }
    }

    public k(com.google.android.material.datepicker.a<?> aVar) {
        this.h = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int k() {
        return this.h.c0.j;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        com.google.android.material.datepicker.a<?> aVar = this.h;
        int i2 = aVar.c0.e.g + i;
        TextView textView = ((a) a0Var).y;
        String string = textView.getContext().getString(R$string.mtrl_picker_navigate_to_year_description);
        textView.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(i2)));
        textView.setContentDescription(String.format(string, Integer.valueOf(i2)));
        il ilVar = aVar.f0;
        Calendar calendarC = qk3.c();
        hl hlVar = (hl) (calendarC.get(1) == i2 ? ilVar.f : ilVar.d);
        Iterator<Long> it = aVar.b0.X().iterator();
        while (it.hasNext()) {
            calendarC.setTimeInMillis(it.next().longValue());
            if (calendarC.get(1) == i2) {
                hlVar = (hl) ilVar.e;
            }
        }
        hlVar.b(textView);
        textView.setOnClickListener(new j(this, i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        return new a((TextView) LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.mtrl_calendar_year, (ViewGroup) recyclerView, false));
    }
}
