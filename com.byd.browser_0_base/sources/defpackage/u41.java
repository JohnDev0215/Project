package defpackage;

import android.os.Process;
import java.lang.Thread;

/* JADX INFO: compiled from: KillChildUncaughtExceptionHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class u41 implements Thread.UncaughtExceptionHandler {
    public boolean a;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        if (this.a) {
            return;
        }
        this.a = true;
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
