package rx;

/* JADX INFO: loaded from: classes4.dex */
public interface Observer<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
