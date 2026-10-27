package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.base.Callback;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes3.dex
 */
/* JADX INFO: compiled from: AccountRestrictionPatternReceiver.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i1 extends BroadcastReceiver {
    public final /* synthetic */ Callback a;
    public final /* synthetic */ k1 b;

    public i1(k1 k1Var, aq3 aq3Var) {
        this.b = k1Var;
        this.a = aq3Var;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        k1 k1Var = this.b;
        k1Var.getClass();
        s62 s62Var = new s62();
        new j1(k1Var, s62Var).c(i9.f);
        s62Var.g(this.a);
    }
}
