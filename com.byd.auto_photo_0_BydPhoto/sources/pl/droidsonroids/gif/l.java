package pl.droidsonroids.gif;

import java.lang.Thread;

/* JADX INFO: loaded from: classes2.dex */
abstract class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c f3203a;

    l(c cVar) {
        this.f3203a = cVar;
    }

    abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            if (this.f3203a.e()) {
                return;
            }
            a();
        } catch (Throwable th) {
            Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            if (defaultUncaughtExceptionHandler != null) {
                defaultUncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
            }
            throw th;
        }
    }
}
