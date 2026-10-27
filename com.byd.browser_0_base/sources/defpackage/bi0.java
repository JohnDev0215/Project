package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: compiled from: EventLoop.common.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class bi0 extends ci0 implements h80 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater i = AtomicReferenceFieldUpdater.newUpdater(bi0.class, Object.class, "_queue");
    public static final /* synthetic */ AtomicReferenceFieldUpdater j = AtomicReferenceFieldUpdater.newUpdater(bi0.class, Object.class, "_delayed");
    private volatile /* synthetic */ Object _queue = null;
    private volatile /* synthetic */ Object _delayed = null;
    private volatile /* synthetic */ int _isCompleted = 0;

    /* JADX INFO: compiled from: EventLoop.common.kt */
    public static abstract class a implements Runnable, Comparable<a>, gb0, y83 {
        public long e;
        public Object f;
        public int g;

        @Override // java.lang.Comparable
        public final int compareTo(a aVar) {
            long j = this.e - aVar.e;
            if (j > 0) {
                return 1;
            }
            return j < 0 ? -1 : 0;
        }

        @Override // defpackage.y83
        public final void e(b bVar) {
            if (!(this.f != z7.j)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            this.f = bVar;
        }

        @Override // defpackage.gb0
        public final synchronized void f() {
            Object obj = this.f;
            a41 a41Var = z7.j;
            if (obj == a41Var) {
                return;
            }
            b bVar = obj instanceof b ? (b) obj : null;
            if (bVar != null) {
                synchronized (bVar) {
                    try {
                        Object obj2 = this.f;
                        if ((obj2 instanceof x83 ? (x83) obj2 : null) != null) {
                            bVar.c(this.g);
                        }
                    } finally {
                    }
                }
            }
            this.f = a41Var;
        }

        @Override // defpackage.y83
        public final void setIndex(int i) {
            this.g = i;
        }

        public final String toString() {
            return "Delayed[nanos=" + this.e + ']';
        }
    }

    /* JADX INFO: compiled from: EventLoop.common.kt */
    public static final class b extends x83<a> {
        public long b;

        public b(long j) {
            this.b = j;
        }
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        o1(runnable);
    }

    public void o1(Runnable runnable) {
        if (!p1(runnable)) {
            q70.k.o1(runnable);
            return;
        }
        Thread threadM1 = m1();
        if (Thread.currentThread() != threadM1) {
            LockSupport.unpark(threadM1);
        }
    }

    public final boolean p1(Runnable runnable) {
        while (true) {
            Object obj = this._queue;
            boolean z = false;
            if (this._isCompleted != 0) {
                return false;
            }
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                        z = true;
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                if (z) {
                    return true;
                }
            } else if (obj instanceof ea1) {
                ea1 ea1Var = (ea1) obj;
                int iA = ea1Var.a(runnable);
                if (iA == 0) {
                    return true;
                }
                if (iA == 1) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i;
                    ea1 ea1VarE = ea1Var.e();
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj, ea1VarE) && atomicReferenceFieldUpdater2.get(this) == obj) {
                    }
                } else if (iA == 2) {
                    return false;
                }
            } else {
                if (obj == z7.k) {
                    return false;
                }
                ea1 ea1Var2 = new ea1(8, true);
                ea1Var2.a((Runnable) obj);
                ea1Var2.a(runnable);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = i;
                while (true) {
                    if (atomicReferenceFieldUpdater3.compareAndSet(this, obj, ea1Var2)) {
                        z = true;
                        break;
                    }
                    if (atomicReferenceFieldUpdater3.get(this) != obj) {
                        break;
                    }
                }
                if (z) {
                    return true;
                }
            }
        }
    }

    public final boolean q1() {
        o8 o8Var = this.h;
        if (!(o8Var == null || o8Var.a == o8Var.b)) {
            return false;
        }
        b bVar = (b) this._delayed;
        if (bVar != null && !bVar.b()) {
            return false;
        }
        Object obj = this._queue;
        if (obj != null) {
            if (obj instanceof ea1) {
                return ((ea1) obj).d();
            }
            if (obj != z7.k) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x007f, code lost:
    
        r7 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long r1() {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bi0.r1():long");
    }

    public final void s1() {
        this._queue = null;
        this._delayed = null;
    }

    @Override // defpackage.ai0
    public void shutdown() {
        ThreadLocal<ai0> threadLocal = w83.a;
        w83.a.set(null);
        this._isCompleted = 1;
        while (true) {
            Object obj = this._queue;
            a41 a41Var = z7.k;
            boolean z = false;
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, null, a41Var)) {
                        z = true;
                        break;
                    } else if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                if (z) {
                    break;
                }
            } else {
                if (obj instanceof ea1) {
                    ((ea1) obj).b();
                    break;
                }
                if (obj == a41Var) {
                    break;
                }
                ea1 ea1Var = new ea1(8, true);
                ea1Var.a((Runnable) obj);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i;
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, ea1Var)) {
                        z = true;
                        break;
                    } else if (atomicReferenceFieldUpdater2.get(this) != obj) {
                        break;
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        while (r1() <= 0) {
        }
        long jNanoTime = System.nanoTime();
        while (true) {
            b bVar = (b) this._delayed;
            a aVarD = bVar == null ? null : bVar.d();
            if (aVarD == null) {
                return;
            } else {
                n1(jNanoTime, aVarD);
            }
        }
    }

    public final void t1(long j2, a aVar) {
        char c;
        Thread threadM1;
        y83 y83Var = null;
        if (this._isCompleted == 0) {
            b bVar = (b) this._delayed;
            if (bVar == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = j;
                b bVar2 = new b(j2);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, null, bVar2) && atomicReferenceFieldUpdater.get(this) == null) {
                }
                Object obj = this._delayed;
                i21.b(obj);
                bVar = (b) obj;
            }
            synchronized (aVar) {
                if (aVar.f == z7.j) {
                    c = 2;
                } else {
                    synchronized (bVar) {
                        Object[] objArr = bVar.a;
                        a aVar2 = (a) (objArr == null ? null : objArr[0]);
                        if (((q70) this)._isCompleted != 0) {
                            c = 1;
                        } else {
                            if (aVar2 == null) {
                                bVar.b = j2;
                            } else {
                                long j3 = aVar2.e;
                                if (j3 - j2 >= 0) {
                                    j3 = j2;
                                }
                                if (j3 - bVar.b > 0) {
                                    bVar.b = j3;
                                }
                            }
                            long j4 = aVar.e;
                            long j5 = bVar.b;
                            if (j4 - j5 < 0) {
                                aVar.e = j5;
                            }
                            bVar.a(aVar);
                            c = 0;
                        }
                    }
                }
            }
        } else {
            c = 1;
        }
        if (c != 0) {
            if (c == 1) {
                n1(j2, aVar);
                return;
            } else {
                if (c != 2) {
                    throw new IllegalStateException("unexpected result".toString());
                }
                return;
            }
        }
        b bVar3 = (b) this._delayed;
        if (bVar3 != null) {
            synchronized (bVar3) {
                y83[] y83VarArr = bVar3.a;
                if (y83VarArr != null) {
                    y83Var = y83VarArr[0];
                }
            }
            y83Var = (a) y83Var;
        }
        if (!(y83Var == aVar) || Thread.currentThread() == (threadM1 = m1())) {
            return;
        }
        LockSupport.unpark(threadM1);
    }
}
