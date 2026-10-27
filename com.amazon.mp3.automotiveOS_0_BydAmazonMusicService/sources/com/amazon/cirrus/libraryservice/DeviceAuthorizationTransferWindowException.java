package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceAuthorizationTransferWindowException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public DeviceAuthorizationTransferWindowException() {
    }

    public DeviceAuthorizationTransferWindowException(String str) {
        super(str);
    }

    public DeviceAuthorizationTransferWindowException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DeviceAuthorizationTransferWindowException(Throwable th) {
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
