package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class StorageLimitException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public StorageLimitException() {
    }

    public StorageLimitException(String str) {
        super(str);
    }

    public StorageLimitException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public StorageLimitException(Throwable th) {
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
