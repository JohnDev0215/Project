package S8;

import F0.C0223q;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class V extends Z {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f8098q = AtomicIntegerFieldUpdater.newUpdater(V.class, "_invoked$volatile");
    private volatile /* synthetic */ int _invoked$volatile = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C0223q f8099p;

    public V(C0223q c0223q) {
        this.f8099p = c0223q;
    }

    @Override // S8.Z
    public final boolean k() {
        return true;
    }

    @Override // S8.Z
    public final void l(Throwable th) {
        if (f8098q.compareAndSet(this, 0, 1)) {
            this.f8099p.b(th);
        }
    }
}
