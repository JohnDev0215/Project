package u4;

/* JADX INFO: compiled from: OnErrorNotImplementedException.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    public c(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }

    public c(Throwable th) {
        this("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | " + th, th);
    }
}
