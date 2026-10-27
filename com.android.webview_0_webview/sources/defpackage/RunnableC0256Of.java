package defpackage;

import android.os.Process;

/* JADX INFO: renamed from: Of, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0256Of implements Runnable {
    public final Runnable s;
    public final int t;

    public RunnableC0256Of(Runnable runnable, int i) {
        this.s = runnable;
        this.t = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.t);
        this.s.run();
    }
}
