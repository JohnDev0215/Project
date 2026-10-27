package e.a;

/* JADX INFO: compiled from: MaybeObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface d<T> {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(io.reactivex.disposables.b bVar);
}
