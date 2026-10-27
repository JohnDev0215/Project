package io.reactivex.rxjava3.internal.disposables;

import spi.bk;
import spi.di;
import spi.mp;
import spi.ql;
import spi.s3;

/* JADX INFO: loaded from: classes2.dex */
public enum EmptyDisposable implements ql<Object> {
    INSTANCE,
    NEVER;

    public static void complete(bk<?> bkVar) {
        bkVar.onSubscribe(INSTANCE);
        bkVar.onComplete();
    }

    public static void complete(di<?> diVar) {
        diVar.onSubscribe(INSTANCE);
        diVar.onComplete();
    }

    public static void complete(s3 s3Var) {
        s3Var.onSubscribe(INSTANCE);
        s3Var.onComplete();
    }

    public static void error(Throwable th, bk<?> bkVar) {
        bkVar.onSubscribe(INSTANCE);
        bkVar.onError(th);
    }

    public static void error(Throwable th, di<?> diVar) {
        diVar.onSubscribe(INSTANCE);
        diVar.onError(th);
    }

    public static void error(Throwable th, mp<?> mpVar) {
        mpVar.onSubscribe(INSTANCE);
        mpVar.onError(th);
    }

    public static void error(Throwable th, s3 s3Var) {
        s3Var.onSubscribe(INSTANCE);
        s3Var.onError(th);
    }

    @Override // spi.kp
    public void clear() {
    }

    @Override // spi.q6
    public void dispose() {
    }

    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public boolean isEmpty() {
        return true;
    }

    @Override // spi.kp
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public Object poll() {
        return null;
    }

    @Override // spi.rl
    public int requestFusion(int i) {
        return i & 2;
    }
}
