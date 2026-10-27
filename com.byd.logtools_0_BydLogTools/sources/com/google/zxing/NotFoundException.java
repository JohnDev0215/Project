package com.google.zxing;

/* JADX INFO: loaded from: classes.dex */
public final class NotFoundException extends ReaderException {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final NotFoundException f4176b;

    static {
        NotFoundException notFoundException = new NotFoundException();
        f4176b = notFoundException;
        notFoundException.setStackTrace(ReaderException.NO_TRACE);
    }

    private NotFoundException() {
    }

    public static NotFoundException getNotFoundInstance() {
        return f4176b;
    }
}
