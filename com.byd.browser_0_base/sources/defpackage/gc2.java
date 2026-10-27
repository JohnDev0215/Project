package defpackage;

import org.chromium.base.Callback;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class gc2 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ nc2 f;

    public /* synthetic */ gc2(nc2 nc2Var, int i) {
        this.e = i;
        this.f = nc2Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        nc2 nc2Var = this.f;
        switch (i) {
            case 0:
                Boolean bool = (Boolean) obj;
                Callback<Boolean> callback = nc2Var.w;
                if (callback != null) {
                    callback.onResult(bool);
                }
                jq jqVar = nc2Var.Z;
                if (jqVar != null) {
                    jqVar.k(bool.booleanValue());
                }
                nc2Var.f47J.r(bool);
                break;
            default:
                nc2Var.o((l61) obj);
                break;
        }
    }
}
