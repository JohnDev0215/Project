package p;

import android.app.Dialog;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class r90 extends bk0 {
    public final /* synthetic */ bk0 q;
    public final /* synthetic */ s90 r;

    public r90(s90 s90Var, tw0 tw0Var) {
        this.r = s90Var;
        this.q = tw0Var;
    }

    @Override // p.bk0
    public final View O(int i) {
        bk0 bk0Var = this.q;
        if (bk0Var.P()) {
            return bk0Var.O(i);
        }
        Dialog dialog = this.r.m0;
        if (dialog != null) {
            return dialog.findViewById(i);
        }
        return null;
    }

    @Override // p.bk0
    public final boolean P() {
        return this.q.P() || this.r.p0;
    }
}
