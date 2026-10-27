package defpackage;

import java.lang.Thread;
import org.chromium.base.JavaHandlerThread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Rs implements Thread.UncaughtExceptionHandler {
    public final /* synthetic */ JavaHandlerThread a;

    public Rs(JavaHandlerThread javaHandlerThread) {
        this.a = javaHandlerThread;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.a.b = th;
    }
}
