package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class ni0 implements Runnable, Comparable, oa0 {
    private volatile Object _heap;
    public long c;
    public int e = -1;

    public ni0(long j) {
        this.c = j;
    }

    public final lk2 b() {
        Object obj = this._heap;
        if (obj instanceof lk2) {
            return (lk2) obj;
        }
        return null;
    }

    public final int c(long j, oi0 oi0Var, kotlinx.coroutines.e eVar) {
        synchronized (this) {
            if (this._heap == qi0.a) {
                return 2;
            }
            synchronized (oi0Var) {
                try {
                    ni0[] ni0VarArr = oi0Var.a;
                    ni0 ni0Var = ni0VarArr != null ? ni0VarArr[0] : null;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = kotlinx.coroutines.e.o;
                    eVar.getClass();
                    if (kotlinx.coroutines.e.q.get(eVar) != 0) {
                        return 1;
                    }
                    if (ni0Var == null) {
                        oi0Var.c = j;
                    } else {
                        long j2 = ni0Var.c;
                        if (j2 - j < 0) {
                            j = j2;
                        }
                        if (j - oi0Var.c > 0) {
                            oi0Var.c = j;
                        }
                    }
                    long j3 = this.c;
                    long j4 = oi0Var.c;
                    if (j3 - j4 < 0) {
                        this.c = j4;
                    }
                    oi0Var.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j = this.c - ((ni0) obj).c;
        if (j > 0) {
            return 1;
        }
        return j < 0 ? -1 : 0;
    }

    public final void d(oi0 oi0Var) {
        if (this._heap == qi0.a) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        this._heap = oi0Var;
    }

    @Override // p.oa0
    public final void dispose() {
        synchronized (this) {
            try {
                Object obj = this._heap;
                tz2 tz2Var = qi0.a;
                if (obj == tz2Var) {
                    return;
                }
                oi0 oi0Var = obj instanceof oi0 ? (oi0) obj : null;
                if (oi0Var != null) {
                    synchronized (oi0Var) {
                        if (b() != null) {
                            oi0Var.b(this.e);
                        }
                    }
                }
                this._heap = tz2Var;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String toString() {
        return "Delayed[nanos=" + this.c + ']';
    }
}
