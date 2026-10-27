package spi;

/* JADX INFO: loaded from: classes2.dex */
public interface os<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(ps psVar);
}
