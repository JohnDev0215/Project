package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoubleUnaryOperators$$Lambda$2 implements DoubleUnaryOperator {
    private final DoubleUnaryOperator arg$1;
    private final DoubleUnaryOperator arg$2;

    private DoubleUnaryOperators$$Lambda$2(DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2) {
        this.arg$1 = doubleUnaryOperator;
        this.arg$2 = doubleUnaryOperator2;
    }

    public static DoubleUnaryOperator lambdaFactory$(DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2) {
        return new DoubleUnaryOperators$$Lambda$2(doubleUnaryOperator, doubleUnaryOperator2);
    }

    @Override // java9.util.function.DoubleUnaryOperator
    public double applyAsDouble(double d) {
        return DoubleUnaryOperators.lambda$andThen$1(this.arg$1, this.arg$2, d);
    }
}
