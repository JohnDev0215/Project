package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPredicates$$Lambda$2 implements LongPredicate {
    private final LongPredicate arg$1;

    private LongPredicates$$Lambda$2(LongPredicate longPredicate) {
        this.arg$1 = longPredicate;
    }

    public static LongPredicate lambdaFactory$(LongPredicate longPredicate) {
        return new LongPredicates$$Lambda$2(longPredicate);
    }

    @Override // java9.util.function.LongPredicate
    public boolean test(long j) {
        return LongPredicates.lambda$negate$1(this.arg$1, j);
    }
}
