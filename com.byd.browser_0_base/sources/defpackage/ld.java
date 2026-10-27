package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ld implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ aw1 f;

    public /* synthetic */ ld(aw1 aw1Var, int i) {
        this.e = i;
        this.f = aw1Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        aw1 aw1Var = this.f;
        switch (i) {
            case 0:
                aw1Var.A((Runnable) obj);
                break;
            default:
                if (!((Boolean) obj).booleanValue()) {
                    aw1Var.k.g(null);
                    aw1Var.K(1, true);
                } else {
                    aw1Var.getClass();
                }
                break;
        }
    }
}
