package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class ec1 {
    public static final AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(ec1.class, Object.class, "_cur");
    private volatile Object _cur = new gc1(8, false);

    public final boolean a(Object obj) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            gc1 gc1Var = (gc1) atomicReferenceFieldUpdater.get(this);
            int iA = gc1Var.a(obj);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                gc1 gc1VarC = gc1Var.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, gc1Var, gc1VarC) && atomicReferenceFieldUpdater.get(this) == gc1Var) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            gc1 gc1Var = (gc1) atomicReferenceFieldUpdater.get(this);
            if (gc1Var.b()) {
                return;
            }
            gc1 gc1VarC = gc1Var.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, gc1Var, gc1VarC) && atomicReferenceFieldUpdater.get(this) == gc1Var) {
            }
        }
    }

    public final int c() {
        gc1 gc1Var = (gc1) a.get(this);
        gc1Var.getClass();
        long j = gc1.f.get(gc1Var);
        return (((int) ((j & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j))) & 1073741823;
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            gc1 gc1Var = (gc1) atomicReferenceFieldUpdater.get(this);
            Object objD = gc1Var.d();
            if (objD != gc1.g) {
                return objD;
            }
            gc1 gc1VarC = gc1Var.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, gc1Var, gc1VarC) && atomicReferenceFieldUpdater.get(this) == gc1Var) {
            }
        }
    }
}
