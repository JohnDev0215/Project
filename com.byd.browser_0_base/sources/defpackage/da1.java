package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: LockFreeTaskQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public class da1<E> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(da1.class, Object.class, "_cur");
    private volatile /* synthetic */ Object _cur = new ea1(8, false);

    public final boolean a(E e) {
        while (true) {
            ea1 ea1Var = (ea1) this._cur;
            int iA = ea1Var.a(e);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
                ea1<E> ea1VarE = ea1Var.e();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, ea1Var, ea1VarE) && atomicReferenceFieldUpdater.get(this) == ea1Var) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            ea1 ea1Var = (ea1) this._cur;
            if (ea1Var.b()) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            ea1<E> ea1VarE = ea1Var.e();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, ea1Var, ea1VarE) && atomicReferenceFieldUpdater.get(this) == ea1Var) {
            }
        }
    }

    public final int c() {
        return ((ea1) this._cur).c();
    }

    public final E d() {
        while (true) {
            ea1 ea1Var = (ea1) this._cur;
            E e = (E) ea1Var.f();
            if (e != ea1.g) {
                return e;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            ea1<E> ea1VarE = ea1Var.e();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, ea1Var, ea1VarE) && atomicReferenceFieldUpdater.get(this) == ea1Var) {
            }
        }
    }
}
