package defpackage;

/* JADX INFO: renamed from: Hd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractRunnableC0129Hd implements Runnable {
    public final /* synthetic */ C1773xd s;

    public AbstractRunnableC0129Hd(C1773xd c1773xd, RunnableC0003Ad runnableC0003Ad) {
        this.s = c1773xd;
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public void run() {
        this.s.b.lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            a();
        } catch (RuntimeException e) {
            C0272Pd c0272Pd = this.s.a;
            c0272Pd.e.sendMessage(c0272Pd.e.obtainMessage(2, e));
        } finally {
            this.s.b.unlock();
        }
    }
}
