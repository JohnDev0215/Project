package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class LongUnaryOperators {
    static /* synthetic */ long lambda$identity$2(long j) {
        return j;
    }

    public static LongUnaryOperator compose(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2) {
        Objects.requireNonNull(longUnaryOperator);
        Objects.requireNonNull(longUnaryOperator2);
        return LongUnaryOperators$$Lambda$1.lambdaFactory$(longUnaryOperator, longUnaryOperator2);
    }

    public static LongUnaryOperator andThen(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2) {
        Objects.requireNonNull(longUnaryOperator);
        Objects.requireNonNull(longUnaryOperator2);
        return LongUnaryOperators$$Lambda$2.lambdaFactory$(longUnaryOperator2, longUnaryOperator);
    }

    static /* synthetic */ long lambda$andThen$1(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2, long j) {
        return longUnaryOperator.applyAsLong(longUnaryOperator2.applyAsLong(j));
    }

    public static LongUnaryOperator identity() {
        return LongUnaryOperators$$Lambda$3.instance;
    }

    private LongUnaryOperators() {
    }
}
