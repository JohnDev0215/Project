package io.reactivex.rxjava3.internal.disposables;

import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import spi.q6;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public enum DisposableHelper implements q6 {
    DISPOSED;

    public static boolean dispose(AtomicReference<q6> atomicReference) {
        q6 andSet;
        q6 q6Var = atomicReference.get();
        DisposableHelper disposableHelper = DISPOSED;
        if (q6Var == disposableHelper || (andSet = atomicReference.getAndSet(disposableHelper)) == disposableHelper) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean isDisposed(q6 q6Var) {
        return q6Var == DISPOSED;
    }

    public static boolean replace(AtomicReference<q6> atomicReference, q6 q6Var) {
        q6 q6Var2;
        do {
            q6Var2 = atomicReference.get();
            if (q6Var2 == DISPOSED) {
                if (q6Var == null) {
                    return false;
                }
                q6Var.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(q6Var2, q6Var));
        return true;
    }

    public static void reportDisposableSet() {
        vn.a(new ProtocolViolationException("Disposable already set!"));
    }

    public static boolean set(AtomicReference<q6> atomicReference, q6 q6Var) {
        q6 q6Var2;
        do {
            q6Var2 = atomicReference.get();
            if (q6Var2 == DISPOSED) {
                if (q6Var == null) {
                    return false;
                }
                q6Var.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(q6Var2, q6Var));
        if (q6Var2 == null) {
            return true;
        }
        q6Var2.dispose();
        return true;
    }

    public static boolean setOnce(AtomicReference<q6> atomicReference, q6 q6Var) {
        Objects.requireNonNull(q6Var, "d is null");
        if (atomicReference.compareAndSet(null, q6Var)) {
            return true;
        }
        q6Var.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        reportDisposableSet();
        return false;
    }

    public static boolean trySet(AtomicReference<q6> atomicReference, q6 q6Var) {
        if (atomicReference.compareAndSet(null, q6Var)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        q6Var.dispose();
        return false;
    }

    public static boolean validate(q6 q6Var, q6 q6Var2) {
        if (q6Var2 == null) {
            vn.a(new NullPointerException("next is null"));
            return false;
        }
        if (q6Var == null) {
            return true;
        }
        q6Var2.dispose();
        reportDisposableSet();
        return false;
    }

    @Override // spi.q6
    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }
}
