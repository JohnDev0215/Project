package spi;

/* JADX INFO: loaded from: classes2.dex */
public interface di<T> {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(q6 q6Var);
}
