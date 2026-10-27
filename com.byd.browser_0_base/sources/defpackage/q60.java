package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import defpackage.g60;

/* JADX INFO: compiled from: DateOrderedListView.java */
/* JADX INFO: loaded from: classes.dex */
public final class q60 extends RecyclerView.q {
    public final /* synthetic */ g60.a e;
    public final /* synthetic */ r60 f;

    public q60(r60 r60Var, tc0 tc0Var) {
        this.f = r60Var;
        this.e = tc0Var;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        boolean zCanScrollVertically = this.f.i.canScrollVertically(-1);
        ac3 ac3Var = ((tc0) this.e).a.d;
        if (ac3Var == null || ac3Var.k == zCanScrollVertically) {
            return;
        }
        ac3Var.k = zCanScrollVertically;
        ac3Var.d();
    }
}
