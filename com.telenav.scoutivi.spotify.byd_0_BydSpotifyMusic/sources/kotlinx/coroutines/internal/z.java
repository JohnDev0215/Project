package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.coroutines.internal.z;

/* JADX INFO: compiled from: ConcurrentLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class z<S extends z<S>> extends g<S> {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final AtomicIntegerFieldUpdater f7688d = AtomicIntegerFieldUpdater.newUpdater(z.class, "cleanedAndPointers");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f7689c;
    private volatile int cleanedAndPointers;

    public z(long j7, S s6, int i7) {
        super(s6);
        this.f7689c = j7;
        this.cleanedAndPointers = i7 << 16;
    }

    @Override // kotlinx.coroutines.internal.g
    public boolean g() {
        return this.cleanedAndPointers == n() && !i();
    }

    public final boolean l() {
        return f7688d.addAndGet(this, -65536) == n() && !i();
    }

    public final long m() {
        return this.f7689c;
    }

    public abstract int n();

    public final void o() {
        if (f7688d.incrementAndGet(this) != n() || i()) {
            return;
        }
        j();
    }

    public final boolean p() {
        int i7;
        do {
            i7 = this.cleanedAndPointers;
            if (!(i7 != n() || i())) {
                return false;
            }
        } while (!f7688d.compareAndSet(this, i7, 65536 + i7));
        return true;
    }
}
