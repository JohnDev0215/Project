package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.history.a;

/* JADX INFO: compiled from: HistoryContentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class bv0 extends RecyclerView.q {
    public final /* synthetic */ dv0 e;

    public bv0(dv0 dv0Var) {
        this.e = dv0Var;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        dv0 dv0Var = this.e;
        a aVar = dv0Var.o;
        boolean z = true;
        if (!(!aVar.s && aVar.u) || dv0Var.l) {
            return;
        }
        int iD = linearLayoutManager.d();
        a aVar2 = dv0Var.o;
        if (iD > aVar2.h - 25) {
            aVar2.P();
        } else {
            z = false;
        }
        dv0Var.i.h(z);
    }
}
