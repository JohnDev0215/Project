package e.a;

/* JADX INFO: compiled from: Subscriber.java */
/* JADX INFO: loaded from: classes.dex */
public interface a<T> {
    void a(b bVar);

    void onComplete();

    void onError(Throwable th);

    void onNext(T t);
}
