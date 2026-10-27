package p;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class e30 implements Runnable {
    public final /* synthetic */ long c;
    public final /* synthetic */ Throwable e;
    public final /* synthetic */ Thread k;
    public final /* synthetic */ com.google.firebase.crashlytics.internal.common.a l;

    public e30(com.google.firebase.crashlytics.internal.common.a aVar, long j, Throwable th, Thread thread) {
        this.l = aVar;
        this.c = j;
        this.e = th;
        this.k = thread;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.firebase.crashlytics.internal.common.a aVar = this.l;
        k40 k40Var = aVar.m;
        if (k40Var == null || !k40Var.e.get()) {
            long j = this.c / 1000;
            String strF = aVar.f();
            if (strF == null) {
                return;
            }
            Throwable th = this.e;
            Thread thread = this.k;
            xb2 xb2Var = aVar.l;
            xb2Var.getClass();
            Log.isLoggable("FirebaseCrashlytics", 2);
            xb2Var.d(th, thread, strF, "error", j, false);
        }
    }
}
