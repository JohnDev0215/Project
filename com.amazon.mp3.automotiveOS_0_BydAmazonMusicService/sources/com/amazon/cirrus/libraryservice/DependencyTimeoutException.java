package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DependencyTimeoutException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public DependencyTimeoutException() {
    }

    public DependencyTimeoutException(String str) {
        super(str);
    }

    public DependencyTimeoutException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DependencyTimeoutException(Throwable th) {
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
