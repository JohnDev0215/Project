package p;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class hf1 extends w12 {
    public final /* synthetic */ MaterialCalendar a;

    public hf1(MaterialCalendar materialCalendar) {
        this.a = materialCalendar;
        bs2.c(null);
        bs2.c(null);
    }

    @Override // p.w12
    public final void a(RecyclerView recyclerView) {
        if ((recyclerView.getAdapter() instanceof ez2) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            int i = MaterialCalendar.l0;
            this.a.getClass();
            throw null;
        }
    }
}
