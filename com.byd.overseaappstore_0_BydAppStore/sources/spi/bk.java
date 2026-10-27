package spi;

/* JADX INFO: loaded from: classes2.dex */
public interface bk<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(q6 q6Var);
}
