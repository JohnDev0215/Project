package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: TransactionExecutor.java */
/* JADX INFO: loaded from: classes.dex */
class p implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Executor f1370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayDeque<Runnable> f1371b = new ArrayDeque<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Runnable f1372c;

    /* JADX INFO: compiled from: TransactionExecutor.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Runnable f1373b;

        a(Runnable runnable) {
            this.f1373b = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1373b.run();
            } finally {
                p.this.a();
            }
        }
    }

    p(Executor executor) {
        this.f1370a = executor;
    }

    synchronized void a() {
        Runnable runnablePoll = this.f1371b.poll();
        this.f1372c = runnablePoll;
        if (runnablePoll != null) {
            this.f1370a.execute(runnablePoll);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.f1371b.offer(new a(runnable));
        if (this.f1372c == null) {
            a();
        }
    }
}
