package d.a;

/* JADX INFO: compiled from: CompletableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(d.a.m.b bVar);
}
