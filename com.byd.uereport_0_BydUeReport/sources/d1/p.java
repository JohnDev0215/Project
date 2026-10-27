package d1;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: TransactionExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f4427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<Runnable> f4428b = new ArrayDeque<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Runnable f4429c;

    /* JADX INFO: compiled from: TransactionExecutor.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Runnable f4430d;

        public a(Runnable runnable) {
            this.f4430d = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4430d.run();
            } finally {
                p.this.a();
            }
        }
    }

    public p(Executor executor) {
        this.f4427a = executor;
    }

    public synchronized void a() {
        Runnable runnablePoll = this.f4428b.poll();
        this.f4429c = runnablePoll;
        if (runnablePoll != null) {
            this.f4427a.execute(runnablePoll);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.f4428b.offer(new a(runnable));
        if (this.f4429c == null) {
            a();
        }
    }
}
