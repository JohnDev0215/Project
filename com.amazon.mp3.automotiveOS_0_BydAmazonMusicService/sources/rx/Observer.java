package rx;

/* JADX INFO: loaded from: classes6.dex */
public interface Observer<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
