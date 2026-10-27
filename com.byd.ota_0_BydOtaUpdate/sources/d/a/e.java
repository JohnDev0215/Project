package d.a;

/* JADX INFO: compiled from: MaybeObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface e<T> {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(d.a.m.b bVar);
}
