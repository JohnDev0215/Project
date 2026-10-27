package S8;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends Z {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f8123r = AtomicIntegerFieldUpdater.newUpdater(o0.class, "_state$volatile");
    private volatile /* synthetic */ int _state$volatile;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Thread f8124p = Thread.currentThread();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public F f8125q;

    public static void n(int i7) {
        throw new IllegalStateException(("Illegal state " + i7).toString());
    }

    @Override // S8.Z
    public final boolean k() {
        return true;
    }

    @Override // S8.Z
    public final void l(Throwable th) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i7;
        do {
            atomicIntegerFieldUpdater = f8123r;
            i7 = atomicIntegerFieldUpdater.get(this);
            if (i7 != 0) {
                if (i7 == 1 || i7 == 2 || i7 == 3) {
                    return;
                }
                n(i7);
                throw null;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i7, 2));
        this.f8124p.interrupt();
        atomicIntegerFieldUpdater.set(this, 3);
    }

    public final void m() {
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f8123r;
            int i7 = atomicIntegerFieldUpdater.get(this);
            if (i7 != 0) {
                if (i7 != 2) {
                    if (i7 == 3) {
                        Thread.interrupted();
                        return;
                    } else {
                        n(i7);
                        throw null;
                    }
                }
            } else if (atomicIntegerFieldUpdater.compareAndSet(this, i7, 1)) {
                F f10 = this.f8125q;
                if (f10 != null) {
                    f10.a();
                    return;
                }
                return;
            }
        }
    }
}
