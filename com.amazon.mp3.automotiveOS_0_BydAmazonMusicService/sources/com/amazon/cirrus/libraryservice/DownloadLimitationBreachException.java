package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadLimitationBreachException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public DownloadLimitationBreachException() {
    }

    public DownloadLimitationBreachException(String str) {
        super(str);
    }

    public DownloadLimitationBreachException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DownloadLimitationBreachException(Throwable th) {
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
