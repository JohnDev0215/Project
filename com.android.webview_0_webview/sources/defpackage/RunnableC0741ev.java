package defpackage;

/* JADX INFO: renamed from: ev, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0741ev implements Runnable {
    public final /* synthetic */ Runnable s;
    public final /* synthetic */ ExecutorC0797fv t;

    public RunnableC0741ev(ExecutorC0797fv executorC0797fv, Runnable runnable) {
        this.t = executorC0797fv;
        this.s = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.s.run();
        } finally {
            this.t.a();
        }
    }
}
