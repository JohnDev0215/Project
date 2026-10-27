package X8;

import S8.i0;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import q7.InterfaceC2194h;

/* JADX INFO: loaded from: classes.dex */
public abstract class r extends b implements i0 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater o = AtomicIntegerFieldUpdater.newUpdater(r.class, "cleanedAndPointers$volatile");
    private volatile /* synthetic */ int cleanedAndPointers$volatile;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f10577n;

    public r(long j3, r rVar, int i7) {
        super(rVar);
        this.f10577n = j3;
        this.cleanedAndPointers$volatile = i7 << 16;
    }

    @Override // X8.b
    public final boolean d() {
        return o.get(this) == g() && c() != null;
    }

    public final boolean f() {
        return o.addAndGet(this, -65536) == g() && c() != null;
    }

    public abstract int g();

    public abstract void h(int i7, InterfaceC2194h interfaceC2194h);

    public final void i() {
        if (o.incrementAndGet(this) == g()) {
            e();
        }
    }

    public final boolean j() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i7;
        do {
            atomicIntegerFieldUpdater = o;
            i7 = atomicIntegerFieldUpdater.get(this);
            if (i7 == g() && c() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i7, 65536 + i7));
        return true;
    }
}
