package defpackage;

import android.app.NotificationChannel;

/* JADX INFO: compiled from: DisclosureUiPicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class sa0 implements hk1 {
    public final p61<pa0> e;
    public final p61<ra0> f;
    public final p61<qa0> g;
    public final oj h;
    public final so1 i;

    public sa0(p61<pa0> p61Var, p61<ra0> p61Var2, p61<qa0> p61Var3, oj ojVar, so1 so1Var, q2 q2Var) {
        this.e = p61Var;
        this.f = p61Var2;
        this.g = p61Var3;
        this.h = ojVar;
        this.i = so1Var;
        q2Var.f(this);
    }

    @Override // defpackage.hk1
    public final void G0() {
        if (this.h.H() == 0) {
            this.e.get().c();
            return;
        }
        so1 so1Var = this.i;
        boolean z = false;
        if (so1Var.f()) {
            NotificationChannel notificationChannelG = so1Var.g("twa_disclosure_initial");
            if (notificationChannelG == null || notificationChannelG.getImportance() != 0) {
                NotificationChannel notificationChannelG2 = so1Var.g("twa_disclosure_subsequent");
                if (notificationChannelG2 == null || notificationChannelG2.getImportance() != 0) {
                    z = true;
                }
            }
        }
        if (z) {
            this.g.get().j();
        } else {
            this.f.get().c();
        }
    }
}
