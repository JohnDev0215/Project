package com.google.zxing;

/* JADX INFO: loaded from: classes.dex */
public final class ChecksumException extends ReaderException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ChecksumException f4172b;

    static {
        ChecksumException checksumException = new ChecksumException();
        f4172b = checksumException;
        checksumException.setStackTrace(ReaderException.NO_TRACE);
    }

    private ChecksumException() {
    }

    public static ChecksumException getChecksumInstance() {
        return ReaderException.isStackTrace ? new ChecksumException() : f4172b;
    }

    private ChecksumException(Throwable th) {
        super(th);
    }

    public static ChecksumException getChecksumInstance(Throwable th) {
        return ReaderException.isStackTrace ? new ChecksumException(th) : f4172b;
    }
}
