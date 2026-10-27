package defpackage;

import android.os.Process;
import java.lang.Thread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ay implements Thread.UncaughtExceptionHandler {
    public boolean a;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (this.a) {
            return;
        }
        this.a = true;
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
