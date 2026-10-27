package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class ADriveRedirectionErrorException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public ADriveRedirectionErrorException() {
    }

    public ADriveRedirectionErrorException(String str) {
        super(str);
    }

    public ADriveRedirectionErrorException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public ADriveRedirectionErrorException(Throwable th) {
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
