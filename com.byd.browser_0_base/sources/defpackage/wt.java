package defpackage;

import android.view.View;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wt implements View.OnFocusChangeListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ wt(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                zt ztVar = (zt) obj;
                ztVar.t(ztVar.u());
                break;
            default:
                gv0 gv0Var = (gv0) obj;
                if (!z) {
                    gv0Var.getClass();
                } else {
                    gv0Var.l.o.N();
                    gv0Var.n.F(true);
                    gv0Var.v();
                    gv0Var.k.q();
                    gv0.I("Search");
                    gv0Var.u = true;
                }
                break;
        }
    }
}
