package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.ui.modelutil.m;

/* JADX INFO: compiled from: ActionChipsAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b2 extends m {
    public int j;
    public RecyclerView.m k;

    public b2(ya1 ya1Var) {
        super(ya1Var);
        this.j = -1;
    }

    public final void I(int i) {
        View viewU = this.k.u(this.j);
        if (viewU != null) {
            viewU.setSelected(false);
        }
        this.j = i;
        this.k.v0(i);
        View viewU2 = this.k.u(this.j);
        if (viewU2 != null) {
            viewU2.setSelected(true);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void s(RecyclerView recyclerView) {
        this.k = recyclerView.getLayoutManager();
        this.j = -1;
    }
}
