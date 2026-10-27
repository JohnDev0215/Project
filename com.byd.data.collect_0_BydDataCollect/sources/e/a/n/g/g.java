package e.a.n.g;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ScheduledDirectTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends a implements Callable<Void> {
    private static final long serialVersionUID = 1811839108042568751L;

    public g(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Void call() {
        this.runner = Thread.currentThread();
        try {
            this.runnable.run();
            return null;
        } finally {
            lazySet(a.f4905b);
            this.runner = null;
        }
    }
}
