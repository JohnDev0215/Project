package com.google.zxing;

/* JADX INFO: loaded from: classes.dex */
public final class FormatException extends ReaderException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final FormatException f4175b;

    static {
        FormatException formatException = new FormatException();
        f4175b = formatException;
        formatException.setStackTrace(ReaderException.NO_TRACE);
    }

    private FormatException() {
    }

    public static FormatException getFormatInstance() {
        return ReaderException.isStackTrace ? new FormatException() : f4175b;
    }

    private FormatException(Throwable th) {
        super(th);
    }

    public static FormatException getFormatInstance(Throwable th) {
        return ReaderException.isStackTrace ? new FormatException(th) : f4175b;
    }
}
