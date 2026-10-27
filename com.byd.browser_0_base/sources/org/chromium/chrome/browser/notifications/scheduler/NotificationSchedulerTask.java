package org.chromium.chrome.browser.notifications.scheduler;

import J.N;
import android.content.Context;
import defpackage.a73;
import defpackage.a8;
import defpackage.du;
import defpackage.fc;
import defpackage.fk1;
import defpackage.hc;
import defpackage.jc;
import defpackage.xz;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.background_task_scheduler.TaskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationSchedulerTask extends fk1 {

    public class a implements Callback<Boolean> {
        public final /* synthetic */ fc.a e;

        public a(fc.a aVar) {
            this.e = aVar;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Boolean bool) {
            this.e.b(bool.booleanValue());
        }
    }

    @CalledByNative
    public static void cancel() {
        ((jc) a8.E()).a(xz.a, 103);
    }

    @CalledByNative
    public static void schedule(long j, long j2) {
        hc hcVarE = a8.E();
        TaskInfo.a aVarA = TaskInfo.a(103, j, j2);
        aVarA.f = true;
        aVarA.e = true;
        jc jcVar = (jc) hcVarE;
        jcVar.b(xz.a, aVarA.a());
    }

    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        return 0;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        N.Mgeg_Rc9(this, new a(aVar));
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        return N.M91xgL_Z(this);
    }
}
