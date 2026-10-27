package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* JADX INFO: compiled from: Select.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicLongFieldUpdater f7808a = AtomicLongFieldUpdater.newUpdater(h.class, "number");
    private volatile long number = 1;

    public final long a() {
        return f7808a.incrementAndGet(this);
    }
}
