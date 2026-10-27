package rx.exceptions;

/* JADX INFO: loaded from: classes6.dex */
public class OnErrorNotImplementedException extends RuntimeException {
    public OnErrorNotImplementedException(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }

    public OnErrorNotImplementedException(Throwable th) {
        super(th != null ? th.getMessage() : null, th == null ? new NullPointerException() : th);
    }
}
