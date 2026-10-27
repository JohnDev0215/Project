package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class ClearDeviceAuthorizationsLimitException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public ClearDeviceAuthorizationsLimitException() {
    }

    public ClearDeviceAuthorizationsLimitException(String str) {
        super(str);
    }

    public ClearDeviceAuthorizationsLimitException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public ClearDeviceAuthorizationsLimitException(Throwable th) {
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
