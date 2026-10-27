package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoubleUnaryOperators$$Lambda$3 implements DoubleUnaryOperator {
    private static final DoubleUnaryOperators$$Lambda$3 instance = new DoubleUnaryOperators$$Lambda$3();

    private DoubleUnaryOperators$$Lambda$3() {
    }

    @Override // java9.util.function.DoubleUnaryOperator
    public double applyAsDouble(double d) {
        return DoubleUnaryOperators.lambda$identity$2(d);
    }
}
