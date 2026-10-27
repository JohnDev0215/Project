package defpackage;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: SerialExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class si2 implements Executor {
    public final ArrayDeque<Runnable> e = new ArrayDeque<>();
    public Runnable f;

    /* JADX INFO: compiled from: SerialExecutor.java */
    public class a implements Runnable {
        public final /* synthetic */ Runnable e;

        public a(Runnable runnable) {
            this.e = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            si2 si2Var = si2.this;
            try {
                this.e.run();
            } finally {
                si2Var.a();
            }
        }
    }

    public final synchronized void a() {
        Runnable runnablePoll = this.e.poll();
        this.f = runnablePoll;
        if (runnablePoll != null) {
            i9.e.execute(runnablePoll);
        }
    }

    @Override // java.util.concurrent.Executor
    public final synchronized void execute(Runnable runnable) {
        this.e.offer(new a(runnable));
        if (this.f == null) {
            a();
        }
    }
}
