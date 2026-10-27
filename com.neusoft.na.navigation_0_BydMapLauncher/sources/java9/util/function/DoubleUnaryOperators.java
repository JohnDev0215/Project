package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class DoubleUnaryOperators {
    static /* synthetic */ double lambda$identity$2(double d) {
        return d;
    }

    public static DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2) {
        Objects.requireNonNull(doubleUnaryOperator);
        Objects.requireNonNull(doubleUnaryOperator2);
        return DoubleUnaryOperators$$Lambda$1.lambdaFactory$(doubleUnaryOperator, doubleUnaryOperator2);
    }

    public static DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2) {
        Objects.requireNonNull(doubleUnaryOperator);
        Objects.requireNonNull(doubleUnaryOperator2);
        return DoubleUnaryOperators$$Lambda$2.lambdaFactory$(doubleUnaryOperator2, doubleUnaryOperator);
    }

    static /* synthetic */ double lambda$andThen$1(DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2, double d) {
        return doubleUnaryOperator.applyAsDouble(doubleUnaryOperator2.applyAsDouble(d));
    }

    public static DoubleUnaryOperator identity() {
        return DoubleUnaryOperators$$Lambda$3.instance;
    }

    private DoubleUnaryOperators() {
    }
}
