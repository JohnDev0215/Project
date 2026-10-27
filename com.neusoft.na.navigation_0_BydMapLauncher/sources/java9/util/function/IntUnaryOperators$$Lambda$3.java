package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntUnaryOperators$$Lambda$3 implements IntUnaryOperator {
    private static final IntUnaryOperators$$Lambda$3 instance = new IntUnaryOperators$$Lambda$3();

    private IntUnaryOperators$$Lambda$3() {
    }

    @Override // java9.util.function.IntUnaryOperator
    public int applyAsInt(int i) {
        return IntUnaryOperators.lambda$identity$2(i);
    }
}
