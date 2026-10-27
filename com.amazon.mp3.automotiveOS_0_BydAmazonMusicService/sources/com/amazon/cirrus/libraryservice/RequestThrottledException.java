package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class RequestThrottledException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public RequestThrottledException() {
    }

    public RequestThrottledException(String str) {
        super(str);
    }

    public RequestThrottledException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public RequestThrottledException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }
}
