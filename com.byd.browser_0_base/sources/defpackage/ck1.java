package defpackage;

import android.util.Log;

/* JADX INFO: compiled from: NativeBackgroundTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ck1 implements Runnable {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Runnable f;
    public final /* synthetic */ Runnable g;
    public final /* synthetic */ fk1 h;

    public ck1(fk1 fk1Var, boolean z, ek1 ek1Var, dk1 dk1Var) {
        this.h = fk1Var;
        this.e = z;
        this.f = ek1Var;
        this.g = dk1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        fk1 fk1Var = this.h;
        if (fk1Var.b) {
            return;
        }
        if (!this.e) {
            ((a8) fk1Var.e).getClass();
            lc lcVarL = lc.L();
            int i = fk1Var.a;
            boolean z = fk1Var.c;
            int iH = vd1.H(i);
            lcVarL.getClass();
            lc.J(iH, "Android.BackgroundTaskScheduler.TaskLoadedNative");
            if (z) {
                lc.J(iH, "Android.BackgroundTaskScheduler.TaskLoadedNative.ReducedMode");
            } else {
                lc.J(iH, "Android.BackgroundTaskScheduler.TaskLoadedNative.FullBrowser");
            }
        }
        gk1 gk1Var = fk1Var.e;
        boolean z2 = fk1Var.c;
        ((a8) gk1Var).getClass();
        Runnable runnable = this.f;
        Runnable runnable2 = this.g;
        nq nqVar = new nq(runnable, z2, runnable2);
        try {
            sp.a().c(nqVar);
            sp.a().b(true, nqVar);
        } catch (p52 e) {
            Log.e("cr_BTS_NativeBkgrdTask", "Background Launch Error", e);
            runnable2.run();
        }
    }
}
