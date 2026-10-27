package S8;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class C extends X8.q {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f8082p = AtomicIntegerFieldUpdater.newUpdater(C.class, "_decision$volatile");
    private volatile /* synthetic */ int _decision$volatile;

    @Override // X8.q, S8.d0
    public final void s(Object obj) {
        t(obj);
    }

    @Override // X8.q, S8.d0
    public final void t(Object obj) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        do {
            atomicIntegerFieldUpdater = f8082p;
            int i7 = atomicIntegerFieldUpdater.get(this);
            if (i7 != 0) {
                if (i7 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                X8.a.j(AbstractC0727y.y(obj), h6.e.k(this.o));
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, 0, 2));
    }
}
