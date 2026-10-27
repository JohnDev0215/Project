package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceAlreadyAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public DeviceAlreadyAuthorizedException() {
    }

    public DeviceAlreadyAuthorizedException(String str) {
        super(str);
    }

    public DeviceAlreadyAuthorizedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DeviceAlreadyAuthorizedException(Throwable th) {
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
