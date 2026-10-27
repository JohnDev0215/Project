package io.reactivex.rxjava3.exceptions;

/* JADX INFO: loaded from: classes2.dex */
public final class MissingBackpressureException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Could not emit value due to lack of requests";
    private static final long serialVersionUID = 8517344746016032542L;

    public MissingBackpressureException() {
    }

    public MissingBackpressureException(String str) {
        super(str);
    }

    public static MissingBackpressureException createDefault() {
        return new MissingBackpressureException(DEFAULT_MESSAGE);
    }
}
