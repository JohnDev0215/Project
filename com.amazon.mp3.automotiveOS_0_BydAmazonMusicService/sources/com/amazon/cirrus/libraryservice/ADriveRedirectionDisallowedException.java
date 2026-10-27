package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class ADriveRedirectionDisallowedException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public ADriveRedirectionDisallowedException() {
    }

    public ADriveRedirectionDisallowedException(String str) {
        super(str);
    }

    public ADriveRedirectionDisallowedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public ADriveRedirectionDisallowedException(Throwable th) {
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
