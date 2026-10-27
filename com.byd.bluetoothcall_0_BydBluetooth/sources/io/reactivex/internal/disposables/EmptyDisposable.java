package io.reactivex.internal.disposables;

import d.a.d;
import d.a.i;
import d.a.k;

/* JADX INFO: loaded from: classes.dex */
public enum EmptyDisposable implements d.a.n.b.a<Object> {
    INSTANCE,
    NEVER;

    public static void complete(i<?> iVar) {
        iVar.onSubscribe(INSTANCE);
        iVar.onComplete();
    }

    public static void error(Throwable th, i<?> iVar) {
        iVar.onSubscribe(INSTANCE);
        iVar.onError(th);
    }

    @Override // d.a.n.b.e
    public void clear() {
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    @Override // d.a.n.b.e
    public boolean isEmpty() {
        return true;
    }

    @Override // d.a.n.b.e
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // d.a.n.b.e
    public Object poll() throws Exception {
        return null;
    }

    @Override // d.a.n.b.b
    public int requestFusion(int i) {
        return i & 2;
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public static void complete(d<?> dVar) {
        dVar.onSubscribe(INSTANCE);
        dVar.onComplete();
    }

    public static void error(Throwable th, d.a.a aVar) {
        aVar.onSubscribe(INSTANCE);
        aVar.onError(th);
    }

    public static void complete(d.a.a aVar) {
        aVar.onSubscribe(INSTANCE);
        aVar.onComplete();
    }

    public static void error(Throwable th, k<?> kVar) {
        kVar.onSubscribe(INSTANCE);
        kVar.onError(th);
    }

    public static void error(Throwable th, d<?> dVar) {
        dVar.onSubscribe(INSTANCE);
        dVar.onError(th);
    }
}
