package io.reactivex.rxjava3.exceptions;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueOverflowException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Queue overflow due to illegal concurrent onNext calls or a bug in an operator";
    private static final long serialVersionUID = 8517344746016032542L;

    public QueueOverflowException() {
        this(DEFAULT_MESSAGE);
    }

    public QueueOverflowException(String message) {
        super(message);
    }
}
