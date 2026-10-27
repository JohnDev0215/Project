package rx.exceptions;

/* JADX INFO: loaded from: classes6.dex */
public final class OnCompletedFailedException extends RuntimeException {
    public OnCompletedFailedException(String str, Throwable th) {
        super(str, th == null ? new NullPointerException() : th);
    }
}
