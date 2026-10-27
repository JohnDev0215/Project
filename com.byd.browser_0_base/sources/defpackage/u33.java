package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.g1;

/* JADX INFO: compiled from: TabStripSnapshotter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u33 extends RecyclerView.q {
    public final /* synthetic */ g1 e;

    public u33(g1 g1Var) {
        this.e = g1Var;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void a(int i, RecyclerView recyclerView) {
        if (i == 0) {
            g1 g1Var = this.e;
            g1Var.a.onResult(new g1.b(g1Var.b, g1Var.c.computeHorizontalScrollOffset()));
        }
    }
}
