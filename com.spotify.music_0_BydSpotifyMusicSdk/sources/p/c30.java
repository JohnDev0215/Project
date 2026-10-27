package p;

import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class c30 implements Callable {
    public final /* synthetic */ long c;
    public final /* synthetic */ Throwable e;
    public final /* synthetic */ Thread k;
    public final /* synthetic */ com.google.firebase.crashlytics.internal.settings.a l;
    public final /* synthetic */ boolean m = false;
    public final /* synthetic */ com.google.firebase.crashlytics.internal.common.a n;

    public c30(com.google.firebase.crashlytics.internal.common.a aVar, long j, Throwable th, Thread thread, com.google.firebase.crashlytics.internal.settings.a aVar2) {
        this.n = aVar;
        this.c = j;
        this.e = th;
        this.k = thread;
        this.l = aVar2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws Throwable {
        long j = this.c;
        long j2 = j / 1000;
        com.google.firebase.crashlytics.internal.common.a aVar = this.n;
        String strF = aVar.f();
        if (strF == null) {
            Log.e("FirebaseCrashlytics", "Tried to write a fatal exception while no session was open.", null);
            return ci1.t(null);
        }
        aVar.c.d();
        Throwable th = this.e;
        Thread thread = this.k;
        xb2 xb2Var = aVar.l;
        xb2Var.getClass();
        Log.isLoggable("FirebaseCrashlytics", 2);
        xb2Var.d(th, thread, strF, "crash", j2, true);
        aVar.d(j);
        com.google.firebase.crashlytics.internal.settings.a aVar2 = this.l;
        aVar.c(false, aVar2);
        new cp(aVar.f);
        com.google.firebase.crashlytics.internal.common.a.a(aVar, cp.b);
        if (!aVar.b.b()) {
            return ci1.t(null);
        }
        Executor executor = (Executor) aVar.e.e;
        return ((bj2) aVar2.i.get()).a.e(executor, new kk0(this, executor, strF, 14));
    }
}
