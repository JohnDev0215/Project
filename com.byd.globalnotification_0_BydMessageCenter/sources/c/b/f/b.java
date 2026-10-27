package c.b.f;

/* JADX INFO: loaded from: classes.dex */
public final class b extends RuntimeException {
    public b(Throwable th) {
        super("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | " + th, th == null ? new NullPointerException() : th);
    }
}
