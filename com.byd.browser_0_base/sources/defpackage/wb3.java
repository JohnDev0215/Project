package defpackage;

import org.chromium.chrome.R$id;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class wb3 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ yb3 f;

    public /* synthetic */ wb3(yb3 yb3Var, int i) {
        this.e = i;
        this.f = yb3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        yb3 yb3Var = this.f;
        switch (i) {
            case 0:
                Integer numValueOf = Integer.valueOf(R$id.new_incognito_tab_menu_id);
                h7 h7Var = yb3Var.l;
                if (h7Var != null) {
                    ((i7) h7Var).c(numValueOf);
                }
                break;
            default:
                Integer numValueOf2 = Integer.valueOf(R$id.enable_price_tracking_menu_id);
                h7 h7Var2 = yb3Var.l;
                if (h7Var2 != null) {
                    ((i7) h7Var2).c(numValueOf2);
                }
                break;
        }
    }
}
