package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: CompletionState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n extends y {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7708c = AtomicIntegerFieldUpdater.newUpdater(n.class, "_resumed");
    private volatile int _resumed;

    public n(k5.d<?> dVar, Throwable th, boolean z6) {
        if (th == null) {
            th = new CancellationException("Continuation " + dVar + " was cancelled normally");
        }
        super(th, z6);
        this._resumed = 0;
    }

    public final boolean c() {
        return f7708c.compareAndSet(this, 0, 1);
    }
}
