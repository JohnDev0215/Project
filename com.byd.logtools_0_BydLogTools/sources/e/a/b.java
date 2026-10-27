package e.a;

/* JADX INFO: compiled from: Emitter.java */
/* JADX INFO: loaded from: classes.dex */
public interface b<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);
}
