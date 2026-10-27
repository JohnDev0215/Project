package defpackage;

import android.content.Context;
import defpackage.fc;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.components.background_task_scheduler.internal.BackgroundTaskJobService;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: NativeBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class fk1 implements fc {
    public int a;
    public boolean b;
    public boolean c;
    public boolean d;
    public gk1 e;

    @Override // defpackage.fc
    public final boolean a(Context context, a73 a73Var, BackgroundTaskJobService.a aVar) {
        Object obj = ThreadUtils.a;
        this.a = a73Var.a;
        du duVar = new du(this, aVar);
        int iC = c(context, a73Var, duVar);
        boolean z = false;
        if (iC == 2) {
            return false;
        }
        if (iC == 1) {
            PostTask.c(rh3.a, new dk1(this, aVar));
            return true;
        }
        ek1 ek1Var = new ek1(this, context, a73Var, duVar);
        dk1 dk1Var = new dk1(this, duVar);
        if (qj.a().g()) {
            this.c = false;
            ((a8) this.e).getClass();
            lc lcVarL = lc.L();
            int i = this.a;
            boolean z2 = this.c;
            lcVarL.getClass();
            int iH = vd1.H(i);
            lc.J(iH, "Android.NativeBackgroundTask.TaskStarted");
            if (z2) {
                lc.J(iH, "Android.NativeBackgroundTask.TaskStarted.ReducedMode");
            } else {
                lc.J(iH, "Android.NativeBackgroundTask.TaskStarted.FullBrowser");
            }
            PostTask.c(rh3.a, ek1Var);
        } else {
            BrowserStartupControllerImpl browserStartupControllerImplA = qj.a();
            browserStartupControllerImplA.getClass();
            if (browserStartupControllerImplA.j && !browserStartupControllerImplA.f && browserStartupControllerImplA.g) {
                z = true;
            }
            this.c = h();
            ((a8) this.e).getClass();
            lc lcVarL2 = lc.L();
            int i2 = this.a;
            boolean z3 = this.c;
            lcVarL2.getClass();
            int iH2 = vd1.H(i2);
            lc.J(iH2, "Android.NativeBackgroundTask.TaskStarted");
            if (z3) {
                lc.J(iH2, "Android.NativeBackgroundTask.TaskStarted.ReducedMode");
            } else {
                lc.J(iH2, "Android.NativeBackgroundTask.TaskStarted.FullBrowser");
            }
            PostTask.c(rh3.a, new ck1(this, z, ek1Var, dk1Var));
        }
        return true;
    }

    @Override // defpackage.fc
    public final boolean b(Context context, a73 a73Var) {
        Object obj = ThreadUtils.a;
        this.b = true;
        g();
        return qj.a().g() ? f(a73Var) : e();
    }

    public abstract int c(Context context, a73 a73Var, du duVar);

    public abstract void d(Context context, a73 a73Var, fc.a aVar);

    public abstract boolean e();

    public abstract boolean f(a73 a73Var);

    public final void g() {
        Object obj = ThreadUtils.a;
        if (this.d) {
            return;
        }
        this.d = true;
        ((a8) this.e).getClass();
        lc lcVarL = lc.L();
        int i = this.a;
        boolean z = this.c;
        int iH = vd1.H(i);
        lcVarL.getClass();
        lc.J(iH, "Android.NativeBackgroundTask.TaskFinished");
        if (z) {
            lc.J(iH, "Android.NativeBackgroundTask.TaskFinished.ReducedMode");
        } else {
            lc.J(iH, "Android.NativeBackgroundTask.TaskFinished.FullBrowser");
        }
    }

    public boolean h() {
        return this instanceof tb0;
    }
}
