package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.u0;

/* JADX INFO: compiled from: TabListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f13 extends RecyclerView.q {
    public final /* synthetic */ j0 e;

    public f13(j0 j0Var) {
        this.e = j0Var;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        j0 j0Var = this.e;
        if (j0Var.h.r()) {
            return;
        }
        for (int i3 = 0; i3 < j0Var.x.getChildCount(); i3++) {
            RecyclerView.m layoutManager = j0Var.x.getLayoutManager();
            View childAt = j0Var.x.getChildAt(i3);
            if (!(layoutManager.c.b(childAt) && layoutManager.d.b(childAt))) {
                j0.K.add(Integer.valueOf(j0Var.f.get(i3).b.h(u0.a)));
            }
        }
    }
}
