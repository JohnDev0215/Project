package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceNotAuthorizedException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public DeviceNotAuthorizedException() {
    }

    public DeviceNotAuthorizedException(String str) {
        super(str);
    }

    public DeviceNotAuthorizedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DeviceNotAuthorizedException(Throwable th) {
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
