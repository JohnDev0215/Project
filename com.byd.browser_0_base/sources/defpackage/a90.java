package defpackage;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DetailScreenScrollListener.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a90 extends RecyclerView.q {
    public final ji e;
    public int f;

    public a90(ji jiVar) {
        this.e = jiVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        this.f = iComputeVerticalScrollOffset;
        if ((iComputeVerticalScrollOffset == 0) && this.e.j() == 2) {
            recyclerView.suppressLayout(true);
        }
    }
}
