package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidTermsOfServiceException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public InvalidTermsOfServiceException() {
    }

    public InvalidTermsOfServiceException(String str) {
        super(str);
    }

    public InvalidTermsOfServiceException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public InvalidTermsOfServiceException(Throwable th) {
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
