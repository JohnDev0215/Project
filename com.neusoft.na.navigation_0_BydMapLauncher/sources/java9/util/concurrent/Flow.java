package java9.util.concurrent;

/* JADX INFO: loaded from: classes3.dex */
public final class Flow {
    static final int DEFAULT_BUFFER_SIZE = 256;

    public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
    }

    public interface Publisher<T> {
        void subscribe(Subscriber<? super T> subscriber);
    }

    public interface Subscriber<T> {
        void onComplete();

        void onError(Throwable th);

        void onNext(T t);

        void onSubscribe(Subscription subscription);
    }

    public interface Subscription {
        void cancel();

        void request(long j);
    }

    public static int defaultBufferSize() {
        return 256;
    }

    private Flow() {
    }
}
