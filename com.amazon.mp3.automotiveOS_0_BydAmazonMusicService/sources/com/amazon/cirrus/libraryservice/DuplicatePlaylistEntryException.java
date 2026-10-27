package com.amazon.cirrus.libraryservice;

/* JADX INFO: loaded from: classes2.dex */
public class DuplicatePlaylistEntryException extends RuntimeException {
    private static final long serialVersionUID = -1;

    public static class Builder {
    }

    public DuplicatePlaylistEntryException() {
    }

    public DuplicatePlaylistEntryException(String str) {
        super(str);
    }

    public DuplicatePlaylistEntryException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public DuplicatePlaylistEntryException(Throwable th) {
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
