package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongUnaryOperators$$Lambda$3 implements LongUnaryOperator {
    private static final LongUnaryOperators$$Lambda$3 instance = new LongUnaryOperators$$Lambda$3();

    private LongUnaryOperators$$Lambda$3() {
    }

    @Override // java9.util.function.LongUnaryOperator
    public long applyAsLong(long j) {
        return LongUnaryOperators.lambda$identity$2(j);
    }
}
