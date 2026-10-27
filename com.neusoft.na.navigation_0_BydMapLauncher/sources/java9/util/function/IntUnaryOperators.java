package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class IntUnaryOperators {
    static /* synthetic */ int lambda$identity$2(int i) {
        return i;
    }

    public static IntUnaryOperator compose(IntUnaryOperator intUnaryOperator, IntUnaryOperator intUnaryOperator2) {
        Objects.requireNonNull(intUnaryOperator);
        Objects.requireNonNull(intUnaryOperator2);
        return IntUnaryOperators$$Lambda$1.lambdaFactory$(intUnaryOperator, intUnaryOperator2);
    }

    public static IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator, IntUnaryOperator intUnaryOperator2) {
        Objects.requireNonNull(intUnaryOperator);
        Objects.requireNonNull(intUnaryOperator2);
        return IntUnaryOperators$$Lambda$2.lambdaFactory$(intUnaryOperator2, intUnaryOperator);
    }

    static /* synthetic */ int lambda$andThen$1(IntUnaryOperator intUnaryOperator, IntUnaryOperator intUnaryOperator2, int i) {
        return intUnaryOperator.applyAsInt(intUnaryOperator2.applyAsInt(i));
    }

    public static IntUnaryOperator identity() {
        return IntUnaryOperators$$Lambda$3.instance;
    }

    private IntUnaryOperators() {
    }
}
