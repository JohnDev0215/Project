package io.reactivex.internal.disposables;

import d.a.b;
import d.a.e;
import d.a.i;
import d.a.l;
import d.a.p.c.a;

/* JADX INFO: loaded from: classes.dex */
public enum EmptyDisposable implements a<Object> {
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

    @Override // d.a.p.c.e
    public void clear() {
    }

    @Override // d.a.m.b
    public void dispose() {
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    @Override // d.a.p.c.e
    public boolean isEmpty() {
        return true;
    }

    @Override // d.a.p.c.e
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // d.a.p.c.e
    public Object poll() throws Exception {
        return null;
    }

    @Override // d.a.p.c.b
    public int requestFusion(int i2) {
        return i2 & 2;
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public static void complete(e<?> eVar) {
        eVar.onSubscribe(INSTANCE);
        eVar.onComplete();
    }

    public static void error(Throwable th, b bVar) {
        bVar.onSubscribe(INSTANCE);
        bVar.onError(th);
    }

    public static void complete(b bVar) {
        bVar.onSubscribe(INSTANCE);
        bVar.onComplete();
    }

    public static void error(Throwable th, l<?> lVar) {
        lVar.onSubscribe(INSTANCE);
        lVar.onError(th);
    }

    public static void error(Throwable th, e<?> eVar) {
        eVar.onSubscribe(INSTANCE);
        eVar.onError(th);
    }
}
