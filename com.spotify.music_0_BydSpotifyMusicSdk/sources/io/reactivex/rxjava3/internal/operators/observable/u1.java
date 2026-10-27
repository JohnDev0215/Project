package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class u1 extends io.reactivex.rxjava3.internal.observers.b {
    public final Observer c;
    public final Object[] e;
    public int k;
    public boolean l;
    public volatile boolean m;

    public u1(Observer observer, Object[] objArr) {
        this.c = observer;
        this.e = objArr;
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        this.l = true;
        return 1;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.k = this.e.length;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.m = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.m;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.k == this.e.length;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        int i = this.k;
        Object[] objArr = this.e;
        if (i == objArr.length) {
            return null;
        }
        this.k = i + 1;
        Object obj = objArr[i];
        Objects.requireNonNull(obj, "The array element is null");
        return obj;
    }
}
