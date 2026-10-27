package defpackage;

import android.content.Context;
import org.chromium.components.background_task_scheduler.TaskInfo;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService;

/* JADX INFO: compiled from: OmahaService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class or1 extends kr1 implements fc {
    public static final Object c = new Object();
    public static or1 d;
    public static boolean e;
    public mr1 b;

    /* JADX INFO: compiled from: OmahaService.java */
    public static class a extends lr1 {
    }

    public or1() {
        super(new a());
    }

    public static boolean c(long j) {
        long jMax = Math.max(0L, j);
        TaskInfo taskInfoA = TaskInfo.a(71300, jMax, jMax).a();
        boolean zB = ((jc) a8.E()).b(xz.a, taskInfoA);
        e = zB;
        return zB;
    }

    @Override // defpackage.fc
    public final boolean a(Context context, a73 a73Var, BackgroundTaskJobService.a aVar) {
        e = false;
        mr1 mr1Var = new mr1(this, aVar);
        mr1Var.c(i9.f);
        this.b = mr1Var;
        return false;
    }

    @Override // defpackage.fc
    public final boolean b(Context context, a73 a73Var) {
        e = false;
        mr1 mr1Var = this.b;
        if (mr1Var != null) {
            mr1Var.a(false);
            this.b = null;
        }
        return false;
    }
}
