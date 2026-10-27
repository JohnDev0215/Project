package S8;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class K implements Runnable, Comparable, F {
    private volatile Object _heap;
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8089m = -1;

    public K(long j3) {
        this.l = j3;
    }

    @Override // S8.F
    public final void a() {
        synchronized (this) {
            try {
                Object obj = this._heap;
                B2.q qVar = AbstractC0727y.f8145b;
                if (obj == qVar) {
                    return;
                }
                L l = obj instanceof L ? (L) obj : null;
                if (l != null) {
                    synchronized (l) {
                        Object obj2 = this._heap;
                        if ((obj2 instanceof X8.u ? (X8.u) obj2 : null) != null) {
                            l.b(this.f8089m);
                        }
                    }
                }
                this._heap = qVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int b(long j3, L l, M m10) {
        synchronized (this) {
            if (this._heap == AbstractC0727y.f8145b) {
                return 2;
            }
            synchronized (l) {
                try {
                    K[] kArr = l.f10581a;
                    K k10 = kArr != null ? kArr[0] : null;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = M.f8090r;
                    m10.getClass();
                    if (M.f8092t.get(m10) != 0) {
                        return 1;
                    }
                    if (k10 == null) {
                        l.c = j3;
                    } else {
                        long j10 = k10.l;
                        if (j10 - j3 < 0) {
                            j3 = j10;
                        }
                        if (j3 - l.c > 0) {
                            l.c = j3;
                        }
                    }
                    long j11 = this.l;
                    long j12 = l.c;
                    if (j11 - j12 < 0) {
                        this.l = j12;
                    }
                    l.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j3 = this.l - ((K) obj).l;
        if (j3 > 0) {
            return 1;
        }
        return j3 < 0 ? -1 : 0;
    }

    public final void d(L l) {
        if (this._heap == AbstractC0727y.f8145b) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this._heap = l;
    }

    public String toString() {
        return "Delayed[nanos=" + this.l + ']';
    }
}
