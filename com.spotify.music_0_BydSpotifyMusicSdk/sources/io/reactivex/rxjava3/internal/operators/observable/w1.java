package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class w1 extends io.reactivex.rxjava3.internal.observers.b {
    public final Iterator c;
    public volatile boolean e;
    public boolean k;
    public boolean l;
    public boolean m;

    public w1(Observer observer, Iterator it) {
        this.c = it;
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        this.k = true;
        return 1;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
        this.l = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e = true;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return this.l;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        if (this.l) {
            return null;
        }
        boolean z = this.m;
        Iterator it = this.c;
        if (!z) {
            this.m = true;
        } else if (!it.hasNext()) {
            this.l = true;
            return null;
        }
        Object next = it.next();
        Objects.requireNonNull(next, "The iterator returned a null value");
        return next;
    }
}
