package p;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class gf1 extends LinearLayoutManager {
    public final /* synthetic */ int E;
    public final /* synthetic */ MaterialCalendar F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public gf1(MaterialCalendar materialCalendar, int i, int i2) {
        super(i);
        this.F = materialCalendar;
        this.E = i2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, p.y12
    public final void s0(RecyclerView recyclerView, int i) {
        lr1 lr1Var = new lr1(this, recyclerView.getContext(), 1);
        lr1Var.a = i;
        t0(lr1Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void v0(k22 k22Var, int[] iArr) {
        int i = this.E;
        MaterialCalendar materialCalendar = this.F;
        if (i == 0) {
            iArr[0] = materialCalendar.i0.getWidth();
            iArr[1] = materialCalendar.i0.getWidth();
        } else {
            iArr[0] = materialCalendar.i0.getHeight();
            iArr[1] = materialCalendar.i0.getHeight();
        }
    }
}
