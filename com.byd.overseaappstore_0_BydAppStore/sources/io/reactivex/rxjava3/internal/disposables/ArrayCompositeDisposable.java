package io.reactivex.rxjava3.internal.disposables;

import java.util.concurrent.atomic.AtomicReferenceArray;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class ArrayCompositeDisposable extends AtomicReferenceArray<q6> implements q6 {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int i) {
        super(i);
    }

    @Override // spi.q6
    public void dispose() {
        q6 andSet;
        if (get(0) != DisposableHelper.DISPOSED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                q6 q6Var = get(i);
                DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
                if (q6Var != disposableHelper && (andSet = getAndSet(i, disposableHelper)) != disposableHelper && andSet != null) {
                    andSet.dispose();
                }
            }
        }
    }

    public boolean isDisposed() {
        return get(0) == DisposableHelper.DISPOSED;
    }

    public q6 replaceResource(int i, q6 q6Var) {
        q6 q6Var2;
        do {
            q6Var2 = get(i);
            if (q6Var2 == DisposableHelper.DISPOSED) {
                q6Var.dispose();
                return null;
            }
        } while (!compareAndSet(i, q6Var2, q6Var));
        return q6Var2;
    }

    public boolean setResource(int i, q6 q6Var) {
        q6 q6Var2;
        do {
            q6Var2 = get(i);
            if (q6Var2 == DisposableHelper.DISPOSED) {
                q6Var.dispose();
                return false;
            }
        } while (!compareAndSet(i, q6Var2, q6Var));
        if (q6Var2 == null) {
            return true;
        }
        q6Var2.dispose();
        return true;
    }
}
