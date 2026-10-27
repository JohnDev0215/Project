package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class MetadataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public MetadataNotFoundException() {
    }

    public MetadataNotFoundException(String str) {
        super(str);
    }

    public MetadataNotFoundException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public MetadataNotFoundException(Throwable th) {
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
