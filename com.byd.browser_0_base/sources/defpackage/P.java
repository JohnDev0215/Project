package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import org.chromium.base.ThreadUtils;
import org.chromium.components.signin.a;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class P extends Q implements I {
    private static final /* synthetic */ AtomicReferenceFieldUpdater e = AtomicReferenceFieldUpdater.newUpdater(P.class, Object.class, "_queue");
    private volatile /* synthetic */ Object _queue = null;
    private volatile /* synthetic */ Object _delayed = null;
    private volatile /* synthetic */ int _isCompleted = 0;

    static {
        AtomicReferenceFieldUpdater.newUpdater(P.class, Object.class, "_delayed");
    }

    public final void c(AbstractC0208s abstractC0208s, Runnable runnable) {
        f(runnable);
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object, s0] */
    public void f(Runnable runnable) {
        boolean z;
        while (true) {
            Object obj = this._queue;
            z = false;
            if (this._isCompleted != 0) {
                break;
            }
            if (obj == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, null, runnable)) {
                        z = true;
                        break;
                    } else if (atomicReferenceFieldUpdater.get(this) != null) {
                        break;
                    }
                }
                if (z) {
                    break;
                }
            } else if (obj instanceof C0209s0) {
                C0209s0 c0209s0 = (C0209s0) obj;
                int iA = c0209s0.a(runnable);
                if (iA == 0) {
                    break;
                }
                if (iA == 1) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = e;
                    C0209s0 c0209s0E = c0209s0.e();
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, obj, c0209s0E) && atomicReferenceFieldUpdater2.get(this) == obj) {
                    }
                } else if (iA == 2) {
                    break;
                }
            } else {
                if (obj == S.a) {
                    break;
                }
                ?? c0209s02 = new z1(8, true) { // from class: s0
                    @Override // defpackage.z1
                    public final void F() {
                        a aVar = this.e;
                        aVar.getClass();
                        Object obj2 = ThreadUtils.a;
                        new C0213u0(aVar).c(i9.f);
                    }
                };
                c0209s02.a((Runnable) obj);
                c0209s02.a(runnable);
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = e;
                while (true) {
                    if (atomicReferenceFieldUpdater3.compareAndSet(this, obj, c0209s02)) {
                        z = true;
                        break;
                    } else if (atomicReferenceFieldUpdater3.get(this) != obj) {
                        break;
                    }
                }
                if (z) {
                    break;
                }
            }
        }
        z = true;
        if (!z) {
            E.f.f(runnable);
            return;
        }
        Thread threadE = e();
        if (Thread.currentThread() != threadE) {
            LockSupport.unpark(threadE);
        }
    }

    protected final boolean g() {
        O.a(this._delayed);
        Object obj = this._queue;
        if (obj == null) {
            return true;
        }
        return obj instanceof C0209s0 ? ((C0209s0) obj).d() : obj == S.a;
    }

    public final long h() {
        Runnable runnable;
        boolean z;
        O.a(this._delayed);
        while (true) {
            Object obj = this._queue;
            runnable = null;
            if (obj == null) {
                break;
            }
            if (obj instanceof C0209s0) {
                C0209s0 c0209s0 = (C0209s0) obj;
                Object objF = c0209s0.f();
                if (objF != C0209s0.g) {
                    runnable = (Runnable) objF;
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e;
                C0209s0 c0209s0E = c0209s0.e();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, c0209s0E) && atomicReferenceFieldUpdater.get(this) == obj) {
                }
            } else {
                if (obj == S.a) {
                    break;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = e;
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, null)) {
                        z = true;
                        break;
                    }
                    if (atomicReferenceFieldUpdater2.get(this) != obj) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    runnable = (Runnable) obj;
                    break;
                }
            }
        }
        if (runnable != null) {
            runnable.run();
            return 0L;
        }
        Object obj2 = this._queue;
        if (obj2 == null) {
            O.a(this._delayed);
        } else if (obj2 instanceof C0209s0) {
            if (!((C0209s0) obj2).d()) {
                return 0L;
            }
            O.a(this._delayed);
        } else if (obj2 != S.a) {
            return 0L;
        }
        return Long.MAX_VALUE;
    }

    protected final void i() {
        this._queue = null;
        this._delayed = null;
    }
}
