package a5;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: ScheduledDirectTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends a implements Callable<Void> {
    private static final long serialVersionUID = 1811839108042568751L;

    public i(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Void call() {
        this.f35g = Thread.currentThread();
        try {
            this.f34e.run();
            return null;
        } finally {
            lazySet(a.f32h);
            this.f35g = null;
        }
    }
}
