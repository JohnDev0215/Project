package io.reactivex.rxjava3.internal.subscriptions;

import spi.os;
import spi.sl;

/* JADX INFO: loaded from: classes2.dex */
public enum EmptySubscription implements sl<Object> {
    INSTANCE;

    public static void complete(os<?> osVar) {
        osVar.onSubscribe(INSTANCE);
        osVar.onComplete();
    }

    public static void error(Throwable th, os<?> osVar) {
        osVar.onSubscribe(INSTANCE);
        osVar.onError(th);
    }

    @Override // spi.ps
    public void cancel() {
    }

    @Override // spi.kp
    public void clear() {
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

    @Override // spi.ps
    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    @Override // spi.rl
    public int requestFusion(int i) {
        return i & 2;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "EmptySubscription";
    }
}
