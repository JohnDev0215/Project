package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class pr2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ yr2 f;

    public /* synthetic */ pr2(yr2 yr2Var, int i) {
        this.e = i;
        this.f = yr2Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        yr2 yr2Var = this.f;
        switch (i) {
            case 0:
                yr2Var.O();
                break;
            default:
                yr2Var.O();
                break;
        }
    }
}
