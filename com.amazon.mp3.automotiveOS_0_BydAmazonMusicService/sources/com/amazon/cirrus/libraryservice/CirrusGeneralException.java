package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class CirrusGeneralException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public CirrusGeneralException() {
    }

    public CirrusGeneralException(String str) {
        super(str);
    }

    public CirrusGeneralException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public CirrusGeneralException(Throwable th) {
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
