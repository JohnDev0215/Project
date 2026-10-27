package defpackage;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Yu implements ThreadFactory {
    public final AtomicInteger a = new AtomicInteger(1);

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        Runnable runnable2 = new Runnable(runnable) { // from class: Xu
            public final Runnable s;

            {
                this.s = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Runnable runnable3 = this.s;
                Process.setThreadPriority(10);
                runnable3.run();
            }
        };
        StringBuilder sbF = Z7.f("CrAsyncTask #");
        sbF.append(this.a.getAndIncrement());
        return new Thread(runnable2, sbF.toString());
    }
}
