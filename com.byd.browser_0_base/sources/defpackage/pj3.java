package defpackage;

import J.N;
import android.content.Context;
import defpackage.fc;
import org.chromium.components.component_updater.UpdateScheduler;

/* JADX INFO: compiled from: UpdateTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pj3 extends fk1 {
    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        UpdateScheduler.getInstance().a = duVar;
        return 0;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        UpdateScheduler updateScheduler = UpdateScheduler.getInstance();
        N.MILubAN7(updateScheduler.b, updateScheduler);
    }

    @Override // defpackage.fk1
    public final boolean e() {
        UpdateScheduler updateScheduler = UpdateScheduler.getInstance();
        long j = updateScheduler.b;
        if (j != 0) {
            N.MfjHVxSB(j, updateScheduler);
        }
        updateScheduler.a = null;
        updateScheduler.a(updateScheduler.c);
        return false;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        UpdateScheduler updateScheduler = UpdateScheduler.getInstance();
        long j = updateScheduler.b;
        if (j != 0) {
            N.MfjHVxSB(j, updateScheduler);
        }
        updateScheduler.a = null;
        updateScheduler.a(updateScheduler.c);
        return false;
    }
}
