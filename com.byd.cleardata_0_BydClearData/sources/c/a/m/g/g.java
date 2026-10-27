package c.a.m.g;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class g extends a implements Callable<Void> {
    public g(Runnable runnable) {
        super(runnable);
    }

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Void call() {
        this.e = Thread.currentThread();
        try {
            this.f1855d.run();
            return null;
        } finally {
            lazySet(a.f1853b);
            this.e = null;
        }
    }
}
