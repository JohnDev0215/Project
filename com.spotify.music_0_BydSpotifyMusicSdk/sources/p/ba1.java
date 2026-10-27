package p;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class ba1 extends kotlinx.coroutines.b implements o80 {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f105p = AtomicIntegerFieldUpdater.newUpdater(ba1.class, "runningWorkers");
    public final kotlinx.coroutines.b k;
    public final int l;
    public final /* synthetic */ o80 m;
    public final ec1 n;
    public final Object o;
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public ba1(kotlinx.coroutines.b bVar, int i) {
        this.k = bVar;
        this.l = i;
        o80 o80Var = bVar instanceof o80 ? (o80) bVar : null;
        this.m = o80Var == null ? v60.a : o80Var;
        this.n = new ec1();
        this.o = new Object();
    }

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        Runnable runnableK0;
        this.n.a(runnable);
        if (f105p.get(this) >= this.l || !L0() || (runnableK0 = K0()) == null) {
            return;
        }
        this.k.G0(this, new z13(this, 7, runnableK0));
    }

    @Override // kotlinx.coroutines.b
    public final void H0(y10 y10Var, Runnable runnable) {
        Runnable runnableK0;
        this.n.a(runnable);
        if (f105p.get(this) >= this.l || !L0() || (runnableK0 = K0()) == null) {
            return;
        }
        this.k.H0(this, new z13(this, 7, runnableK0));
    }

    @Override // p.o80
    public final oa0 J(long j, ul2 ul2Var, y10 y10Var) {
        return this.m.J(j, ul2Var, y10Var);
    }

    public final Runnable K0() {
        while (true) {
            Runnable runnable = (Runnable) this.n.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.o) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f105p;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.n.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean L0() {
        synchronized (this.o) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f105p;
            if (atomicIntegerFieldUpdater.get(this) >= this.l) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // p.o80
    public final void X(long j, zp zpVar) {
        this.m.X(j, zpVar);
    }
}
