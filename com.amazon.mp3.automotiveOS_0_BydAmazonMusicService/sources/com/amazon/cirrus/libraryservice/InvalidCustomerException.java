package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidCustomerException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public InvalidCustomerException() {
    }

    public InvalidCustomerException(String str) {
        super(str);
    }

    public InvalidCustomerException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public InvalidCustomerException(Throwable th) {
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
