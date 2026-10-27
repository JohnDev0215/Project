package io.sentry;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public enum W1 implements InterfaceC1485m0 {
    OK(200, 299),
    CANCELLED(499),
    INTERNAL_ERROR(500),
    UNKNOWN(500),
    UNKNOWN_ERROR(500),
    INVALID_ARGUMENT(400),
    DEADLINE_EXCEEDED(504),
    NOT_FOUND(404),
    ALREADY_EXISTS(409),
    PERMISSION_DENIED(403),
    RESOURCE_EXHAUSTED(429),
    FAILED_PRECONDITION(400),
    ABORTED(409),
    OUT_OF_RANGE(400),
    UNIMPLEMENTED(501),
    UNAVAILABLE(503),
    DATA_LOSS(500),
    UNAUTHENTICATED(401);

    private final int maxHttpStatusCode;
    private final int minHttpStatusCode;

    W1(int i7) {
        this.minHttpStatusCode = i7;
        this.maxHttpStatusCode = i7;
    }

    public static W1 fromHttpStatusCode(int i7) {
        for (W1 w1 : values()) {
            if (w1.matches(i7)) {
                return w1;
            }
        }
        return null;
    }

    private boolean matches(int i7) {
        return i7 >= this.minHttpStatusCode && i7 <= this.maxHttpStatusCode;
    }

    @Override // io.sentry.InterfaceC1485m0
    public void serialize(C0 c02, K k10) {
        ((C1489n1) c02).L(name().toLowerCase(Locale.ROOT));
    }

    public static W1 fromHttpStatusCode(Integer num, W1 w1) {
        W1 w1FromHttpStatusCode = num != null ? fromHttpStatusCode(num.intValue()) : w1;
        return w1FromHttpStatusCode != null ? w1FromHttpStatusCode : w1;
    }

    W1(int i7, int i10) {
        this.minHttpStatusCode = i7;
        this.maxHttpStatusCode = i10;
    }
}
