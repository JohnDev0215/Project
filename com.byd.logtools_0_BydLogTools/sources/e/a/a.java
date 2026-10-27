package e.a;

/* JADX INFO: compiled from: CompletableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(io.reactivex.disposables.b bVar);
}
