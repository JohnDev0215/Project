package io.reactivex.rxjava3.exceptions;

/* JADX INFO: loaded from: classes7.dex */
public final class OnErrorNotImplementedException extends RuntimeException {
    public OnErrorNotImplementedException(String message, Throwable e) {
        super(message, e == null ? new NullPointerException() : e);
    }

    public OnErrorNotImplementedException(Throwable e) {
        this("The exception was not handled due to missing onError handler in the subscribe() method call. Further reading: https://github.com/ReactiveX/RxJava/wiki/Error-Handling | " + e, e);
    }
}
