package com.google.android.material.datepicker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.hl;
import defpackage.qk3;
import defpackage.qv1;
import java.util.Calendar;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends RecyclerView.l {
    public final Calendar a = qk3.d(null);
    public final Calendar b = qk3.d(null);
    public final /* synthetic */ a c;

    public b(a aVar) {
        this.c = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void h(Canvas canvas, RecyclerView recyclerView) {
        Long l;
        if ((recyclerView.getAdapter() instanceof k) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            k kVar = (k) recyclerView.getAdapter();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            a aVar = this.c;
            for (qv1<Long, Long> qv1Var : aVar.b0.q()) {
                Long l2 = qv1Var.a;
                if (l2 != null && (l = qv1Var.b) != null) {
                    long jLongValue = l2.longValue();
                    Calendar calendar = this.a;
                    calendar.setTimeInMillis(jLongValue);
                    long jLongValue2 = l.longValue();
                    Calendar calendar2 = this.b;
                    calendar2.setTimeInMillis(jLongValue2);
                    int i = calendar.get(1) - kVar.h.c0.e.g;
                    int i2 = calendar2.get(1) - kVar.h.c0.e.g;
                    View viewU = gridLayoutManager.u(i);
                    View viewU2 = gridLayoutManager.u(i2);
                    int i3 = gridLayoutManager.F;
                    int i4 = i / i3;
                    int i5 = i2 / i3;
                    int i6 = i4;
                    while (i6 <= i5) {
                        if (gridLayoutManager.u(gridLayoutManager.F * i6) != null) {
                            canvas.drawRect(i6 == i4 ? (viewU.getWidth() / 2) + viewU.getLeft() : 0, r10.getTop() + ((hl) aVar.f0.d).a.top, i6 == i5 ? (viewU2.getWidth() / 2) + viewU2.getLeft() : recyclerView.getWidth(), r10.getBottom() - ((hl) aVar.f0.d).a.bottom, (Paint) aVar.f0.h);
                        }
                        i6++;
                    }
                }
            }
        }
    }
}
