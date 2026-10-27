package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidAddressException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public InvalidAddressException() {
    }

    public InvalidAddressException(String str) {
        super(str);
    }

    public InvalidAddressException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public InvalidAddressException(Throwable th) {
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
