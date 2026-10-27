package defpackage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: fv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ExecutorC0797fv implements Executor {
    public final ArrayDeque a = new ArrayDeque();
    public Runnable b;

    public synchronized void a() {
        Runnable runnable = (Runnable) this.a.poll();
        this.b = runnable;
        if (runnable != null) {
            Executor executor = Su.f;
            ((Pu) Su.f).execute(runnable);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.a.offer(new RunnableC0741ev(this, runnable));
        if (this.b == null) {
            a();
        }
    }
}
