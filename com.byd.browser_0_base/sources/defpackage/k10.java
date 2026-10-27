package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.creator.CreatorCoordinator;

/* JADX INFO: compiled from: CreatorCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class k10 extends RecyclerView.q {
    public final /* synthetic */ CreatorCoordinator e;

    public k10(CreatorCoordinator creatorCoordinator) {
        this.e = creatorCoordinator;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        this.e.j.p(n10.h, Boolean.valueOf(recyclerView.canScrollVertically(-1)));
    }
}
