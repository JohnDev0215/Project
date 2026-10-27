package io.reactivex.rxjava3.internal.functions;

import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.q;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Callable, q, o {
    public final Object c;

    public e(Object obj) {
        this.c = obj;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        return this.c;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return this.c;
    }

    @Override // io.reactivex.rxjava3.functions.q
    public final Object get() {
        return this.c;
    }
}
