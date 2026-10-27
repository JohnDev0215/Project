package com.amazon.cirrus.shared.model.exception;

/* JADX INFO: loaded from: classes2.dex */
public class CirrusBaseException extends RuntimeException {
    private static final long serialVersionUID = -1;
    private Boolean retriable;

    public static class Builder {
    }

    public CirrusBaseException() {
    }

    public CirrusBaseException(String str) {
        super(str);
    }

    public CirrusBaseException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public CirrusBaseException(Throwable th) {
        initCause(th);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }

    public Boolean isRetriable() {
        return this.retriable;
    }

    public void setRetriable(Boolean bool) {
        this.retriable = bool;
    }
}
