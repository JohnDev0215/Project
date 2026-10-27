package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntUnaryOperators$$Lambda$2 implements IntUnaryOperator {
    private final IntUnaryOperator arg$1;
    private final IntUnaryOperator arg$2;

    private IntUnaryOperators$$Lambda$2(IntUnaryOperator intUnaryOperator, IntUnaryOperator intUnaryOperator2) {
        this.arg$1 = intUnaryOperator;
        this.arg$2 = intUnaryOperator2;
    }

    public static IntUnaryOperator lambdaFactory$(IntUnaryOperator intUnaryOperator, IntUnaryOperator intUnaryOperator2) {
        return new IntUnaryOperators$$Lambda$2(intUnaryOperator, intUnaryOperator2);
    }

    @Override // java9.util.function.IntUnaryOperator
    public int applyAsInt(int i) {
        return IntUnaryOperators.lambda$andThen$1(this.arg$1, this.arg$2, i);
    }
}
