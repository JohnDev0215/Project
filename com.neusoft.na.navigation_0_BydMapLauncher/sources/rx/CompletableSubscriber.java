package rx;

/* JADX INFO: loaded from: classes4.dex */
public interface CompletableSubscriber {
    void onCompleted();

    void onError(Throwable th);

    void onSubscribe(Subscription subscription);
}
