package e.a.n.a;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DisposableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public enum b implements e.a.k.b {
    DISPOSED;

    public static boolean a(AtomicReference<e.a.k.b> atomicReference) {
        e.a.k.b andSet;
        e.a.k.b bVar = atomicReference.get();
        b bVar2 = DISPOSED;
        if (bVar == bVar2 || (andSet = atomicReference.getAndSet(bVar2)) == bVar2) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.d();
        return true;
    }

    public static void b() {
        e.a.p.a.o(new e.a.l.d("Disposable already set!"));
    }

    public static boolean e(AtomicReference<e.a.k.b> atomicReference, e.a.k.b bVar) {
        e.a.n.b.b.c(bVar, "d is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.d();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        b();
        return false;
    }

    public static boolean g(e.a.k.b bVar, e.a.k.b bVar2) {
        if (bVar2 == null) {
            e.a.p.a.o(new NullPointerException("next is null"));
            return false;
        }
        if (bVar == null) {
            return true;
        }
        bVar2.d();
        b();
        return false;
    }

    @Override // e.a.k.b
    public void d() {
    }
}
