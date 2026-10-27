package p;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class tw0 extends bk0 {
    public final /* synthetic */ ww0 q;

    public tw0(ww0 ww0Var) {
        this.q = ww0Var;
    }

    @Override // p.bk0
    public final View O(int i) {
        ww0 ww0Var = this.q;
        View view = ww0Var.M;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment " + ww0Var + " does not have a view");
    }

    @Override // p.bk0
    public final boolean P() {
        return this.q.M != null;
    }
}
