package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongUnaryOperators$$Lambda$1 implements LongUnaryOperator {
    private final LongUnaryOperator arg$1;
    private final LongUnaryOperator arg$2;

    private LongUnaryOperators$$Lambda$1(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2) {
        this.arg$1 = longUnaryOperator;
        this.arg$2 = longUnaryOperator2;
    }

    public static LongUnaryOperator lambdaFactory$(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2) {
        return new LongUnaryOperators$$Lambda$1(longUnaryOperator, longUnaryOperator2);
    }

    @Override // java9.util.function.LongUnaryOperator
    public long applyAsLong(long j) {
        return this.arg$1.applyAsLong(this.arg$2.applyAsLong(j));
    }
}
