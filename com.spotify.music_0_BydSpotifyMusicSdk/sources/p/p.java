package p;

import android.os.Bundle;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ vg1 e;

    public /* synthetic */ p(gg1 gg1Var, int i) {
        this.c = i;
        this.e = gg1Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        vg1 vg1Var = this.e;
        switch (i) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putBoolean("result", ((Boolean) obj).booleanValue());
                vg1Var.g(bundle);
                break;
            case 1:
                Logger.c((Throwable) obj, "Exception getting collection state.", new Object[0]);
                vg1Var.f();
                break;
            default:
                Logger.c((Throwable) obj, "Exception adding/removing from collection.", new Object[0]);
                vg1Var.f();
                break;
        }
    }
}
