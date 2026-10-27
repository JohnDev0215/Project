package defpackage;

import J.N;
import android.content.Context;
import defpackage.fc;

/* JADX INFO: compiled from: PeriodicBackgroundSyncChromeWakeUpTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class b02 extends fk1 {
    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        return h90.b(context) == 6 ? 1 : 0;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        oj2.X(System.currentTimeMillis() - a73Var.b.getLong("SoonestWakeupTime"), "BackgroundSync.Periodic.Wakeup.DelayTime");
        N.M3y91C0s(new wq0(12, aVar));
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        return true;
    }
}
