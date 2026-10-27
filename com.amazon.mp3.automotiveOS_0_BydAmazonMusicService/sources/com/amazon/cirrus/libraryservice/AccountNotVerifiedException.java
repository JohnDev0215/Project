package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class AccountNotVerifiedException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public AccountNotVerifiedException() {
    }

    public AccountNotVerifiedException(String str) {
        super(str);
    }

    public AccountNotVerifiedException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public AccountNotVerifiedException(Throwable th) {
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
