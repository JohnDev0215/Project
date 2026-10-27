package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LockFreeTaskQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public class q<E> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final AtomicReferenceFieldUpdater f7668a = AtomicReferenceFieldUpdater.newUpdater(q.class, Object.class, "_cur");
    private volatile Object _cur;

    public q(boolean z6) {
        this._cur = new r(8, z6);
    }

    public final boolean a(E e7) {
        while (true) {
            r rVar = (r) this._cur;
            int iA = rVar.a(e7);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                kotlinx.coroutines.j.a(f7668a, this, rVar, rVar.i());
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            r rVar = (r) this._cur;
            if (rVar.d()) {
                return;
            } else {
                kotlinx.coroutines.j.a(f7668a, this, rVar, rVar.i());
            }
        }
    }

    public final int c() {
        return ((r) this._cur).f();
    }

    public final E d() {
        while (true) {
            r rVar = (r) this._cur;
            E e7 = (E) rVar.j();
            if (e7 != r.f7671g) {
                return e7;
            }
            kotlinx.coroutines.j.a(f7668a, this, rVar, rVar.i());
        }
    }
}
