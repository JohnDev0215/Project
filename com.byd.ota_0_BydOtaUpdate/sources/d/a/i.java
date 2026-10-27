package d.a;

/* JADX INFO: compiled from: Observer.java */
/* JADX INFO: loaded from: classes.dex */
public interface i<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(d.a.m.b bVar);
}
