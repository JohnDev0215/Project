package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class PurchaseException extends RuntimeException {
    private static final long serialVersionUID = -1;
    private String errorCode;

    public static class Builder {
    }

    public PurchaseException() {
    }

    public PurchaseException(String str) {
        super(str);
    }

    public PurchaseException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public PurchaseException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }
}
