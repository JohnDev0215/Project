package w4;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DisposableHelper.java */
/* JADX INFO: loaded from: classes.dex */
public enum b implements t4.b {
    DISPOSED;

    public static boolean dispose(AtomicReference<t4.b> atomicReference) {
        t4.b andSet;
        t4.b bVar = atomicReference.get();
        b bVar2 = DISPOSED;
        if (bVar == bVar2 || (andSet = atomicReference.getAndSet(bVar2)) == bVar2) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean isDisposed(t4.b bVar) {
        return bVar == DISPOSED;
    }

    public static boolean replace(AtomicReference<t4.b> atomicReference, t4.b bVar) {
        t4.b bVar2;
        do {
            bVar2 = atomicReference.get();
            if (bVar2 == DISPOSED) {
                if (bVar == null) {
                    return false;
                }
                bVar.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(bVar2, bVar));
        return true;
    }

    public static void reportDisposableSet() {
        d5.a.k(new u4.d("Disposable already set!"));
    }

    public static boolean set(AtomicReference<t4.b> atomicReference, t4.b bVar) {
        t4.b bVar2;
        do {
            bVar2 = atomicReference.get();
            if (bVar2 == DISPOSED) {
                if (bVar == null) {
                    return false;
                }
                bVar.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(bVar2, bVar));
        if (bVar2 == null) {
            return true;
        }
        bVar2.dispose();
        return true;
    }

    public static boolean setOnce(AtomicReference<t4.b> atomicReference, t4.b bVar) {
        x4.b.c(bVar, "d is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        reportDisposableSet();
        return false;
    }

    public static boolean trySet(AtomicReference<t4.b> atomicReference, t4.b bVar) {
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        bVar.dispose();
        return false;
    }

    public static boolean validate(t4.b bVar, t4.b bVar2) {
        if (bVar2 == null) {
            d5.a.k(new NullPointerException("next is null"));
            return false;
        }
        if (bVar == null) {
            return true;
        }
        bVar2.dispose();
        reportDisposableSet();
        return false;
    }

    @Override // t4.b
    public void dispose() {
    }

    @Override // t4.b
    public boolean isDisposed() {
        return true;
    }
}
