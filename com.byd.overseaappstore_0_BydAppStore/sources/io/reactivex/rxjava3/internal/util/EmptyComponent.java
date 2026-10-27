package io.reactivex.rxjava3.internal.util;

import spi.bk;
import spi.di;
import spi.mp;
import spi.os;
import spi.ps;
import spi.q6;
import spi.s3;
import spi.vn;

/* JADX INFO: loaded from: classes2.dex */
public enum EmptyComponent implements os, bk<Object>, di<Object>, mp<Object>, s3, ps, q6 {
    INSTANCE;

    public static <T> bk<T> asObserver() {
        return INSTANCE;
    }

    public static <T> os<T> asSubscriber() {
        return INSTANCE;
    }

    @Override // spi.ps
    public void cancel() {
    }

    @Override // spi.q6
    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }

    @Override // spi.os
    public void onComplete() {
    }

    @Override // spi.os
    public void onError(Throwable th) {
        vn.a(th);
    }

    @Override // spi.os
    public void onNext(Object obj) {
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        psVar.cancel();
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        q6Var.dispose();
    }

    public void onSuccess(Object obj) {
    }

    @Override // spi.ps
    public void request(long j) {
    }
}
