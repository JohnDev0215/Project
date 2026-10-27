package defpackage;

import android.content.IntentFilter;

/* JADX INFO: compiled from: AccountRestrictionPatternReceiver.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k1 {
    public k1(aq3 aq3Var) {
        xz.g(xz.a, new i1(this, aq3Var), new IntentFilter("android.intent.action.APPLICATION_RESTRICTIONS_CHANGED"));
        s62 s62Var = new s62();
        new j1(this, s62Var).c(i9.f);
        s62Var.g(aq3Var);
    }
}
