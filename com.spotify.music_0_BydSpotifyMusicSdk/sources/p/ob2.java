package p;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class ob2 implements Executor {
    public final Executor e;
    public Runnable k;
    public final ArrayDeque c = new ArrayDeque();
    public final Object l = new Object();

    public ob2(ExecutorService executorService) {
        this.e = executorService;
    }

    public final void a() {
        Runnable runnable = (Runnable) this.c.poll();
        this.k = runnable;
        if (runnable != null) {
            this.e.execute(runnable);
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        synchronized (this.l) {
            try {
                this.c.add(new yf1(this, runnable, 11));
                if (this.k == null) {
                    a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
