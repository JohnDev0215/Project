package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import p.gb;
import p.gc1;
import p.kk2;
import p.li0;
import p.lk2;
import p.ni0;
import p.o80;
import p.oa0;
import p.oi0;
import p.pi0;
import p.qi0;
import p.rp;
import p.tz2;
import p.ul2;
import p.v03;
import p.v60;
import p.y10;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends pi0 implements o80 {
    public static final AtomicReferenceFieldUpdater o = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "_queue");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f89p = AtomicReferenceFieldUpdater.newUpdater(e.class, Object.class, "_delayed");
    public static final AtomicIntegerFieldUpdater q = AtomicIntegerFieldUpdater.newUpdater(e.class, "_isCompleted");
    private volatile Object _delayed;
    private volatile int _isCompleted = 0;
    private volatile Object _queue;

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        S0(runnable);
    }

    public oa0 J(long j, ul2 ul2Var, y10 y10Var) {
        return v60.a.J(j, ul2Var, y10Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0054, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dc  */
    @Override // p.pi0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long P0() {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.e.P0():long");
    }

    public void S0(Runnable runnable) {
        if (!T0(runnable)) {
            c.r.S0(runnable);
            return;
        }
        Thread threadM0 = M0();
        if (Thread.currentThread() != threadM0) {
            LockSupport.unpark(threadM0);
        }
    }

    public final boolean T0(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (q.get(this) != 0) {
                return false;
            }
            if (obj == null) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                return true;
            }
            if (!(obj instanceof gc1)) {
                if (obj == qi0.b) {
                    return false;
                }
                gc1 gc1Var = new gc1(8, true);
                gc1Var.a((Runnable) obj);
                gc1Var.a(runnable);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, gc1Var)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return true;
            }
            gc1 gc1Var2 = (gc1) obj;
            int iA = gc1Var2.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                gc1 gc1VarC = gc1Var2.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, gc1VarC) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final boolean U0() {
        gb gbVar = this.m;
        if (!(gbVar != null ? gbVar.isEmpty() : true)) {
            return false;
        }
        oi0 oi0Var = (oi0) f89p.get(this);
        if (oi0Var != null && lk2.b.get(oi0Var) != 0) {
            return false;
        }
        Object obj = o.get(this);
        if (obj == null) {
            return true;
        }
        if (obj instanceof gc1) {
            long j = gc1.f.get((gc1) obj);
            if (((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30))) {
                return true;
            }
        } else if (obj == qi0.b) {
            return true;
        }
        return false;
    }

    public final void V0(long j, ni0 ni0Var) {
        int iC;
        Thread threadM0;
        boolean z = q.get(this) != 0;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f89p;
        if (z) {
            iC = 1;
        } else {
            oi0 oi0Var = (oi0) atomicReferenceFieldUpdater.get(this);
            if (oi0Var == null) {
                oi0 oi0Var2 = new oi0();
                oi0Var2.c = j;
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, oi0Var2) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                Object obj = atomicReferenceFieldUpdater.get(this);
                v03.h(obj);
                oi0Var = (oi0) obj;
            }
            iC = ni0Var.c(j, oi0Var, this);
        }
        if (iC != 0) {
            if (iC == 1) {
                R0(j, ni0Var);
                return;
            } else {
                if (iC != 2) {
                    throw new IllegalStateException("unexpected result".toString());
                }
                return;
            }
        }
        oi0 oi0Var3 = (oi0) atomicReferenceFieldUpdater.get(this);
        if (oi0Var3 != null) {
            synchronized (oi0Var3) {
                ni0[] ni0VarArr = oi0Var3.a;
                ni0Var = ni0VarArr != null ? ni0VarArr[0] : null;
            }
        }
        if (ni0Var != ni0Var || Thread.currentThread() == (threadM0 = M0())) {
            return;
        }
        LockSupport.unpark(threadM0);
    }

    @Override // p.o80
    public final void X(long j, zp zpVar) {
        tz2 tz2Var = qi0.a;
        long j2 = j > 0 ? j >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j : 0L;
        if (j2 < 4611686018427387903L) {
            long jNanoTime = System.nanoTime();
            li0 li0Var = new li0(this, j2 + jNanoTime, zpVar);
            V0(jNanoTime, li0Var);
            zpVar.t(new rp(1, li0Var));
        }
    }

    @Override // p.pi0
    public void shutdown() {
        ni0 ni0VarB;
        ThreadLocal threadLocal = kk2.a;
        kk2.a.set(null);
        q.set(this, 1);
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = o;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj != null) {
                if (!(obj instanceof gc1)) {
                    if (obj != qi0.b) {
                        gc1 gc1Var = new gc1(8, true);
                        gc1Var.a((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, gc1Var)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((gc1) obj).b();
                break;
            }
            tz2 tz2Var = qi0.b;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, null, tz2Var)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                    break;
                }
            }
            break loop0;
        }
        while (P0() <= 0) {
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            oi0 oi0Var = (oi0) f89p.get(this);
            if (oi0Var == null) {
                return;
            }
            synchronized (oi0Var) {
                ni0VarB = lk2.b.get(oi0Var) > 0 ? oi0Var.b(0) : null;
            }
            if (ni0VarB == null) {
                return;
            } else {
                R0(jNanoTime, ni0VarB);
            }
        }
    }
}
