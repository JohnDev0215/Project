package C1;

import g4.RunnableC1297a;
import java.util.concurrent.ThreadFactory;
import k7.C1707a;

/* JADX INFO: loaded from: classes.dex */
public final class m implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f948a;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.f948a) {
            case 0:
                return new l(runnable);
            case 1:
                return new D5.e(runnable, "Segment-" + D5.e.l.getAndIncrement());
            case 2:
                Thread thread = new Thread(runnable, "engine.io-client.heartbeat-" + d7.l.f13511N.getAndIncrement());
                thread.setDaemon(true);
                return thread;
            case 3:
                return new Thread(new RunnableC1297a(runnable, 0), "glide-active-resources");
            default:
                C1707a c1707a = new C1707a(runnable);
                C1707a.f18310n = c1707a;
                c1707a.setName("EventThread");
                C1707a.f18310n.setDaemon(Thread.currentThread().isDaemon());
                return C1707a.f18310n;
        }
    }
}
