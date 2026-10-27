package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPredicates$$Lambda$1 implements LongPredicate {
    private final LongPredicate arg$1;
    private final LongPredicate arg$2;

    private LongPredicates$$Lambda$1(LongPredicate longPredicate, LongPredicate longPredicate2) {
        this.arg$1 = longPredicate;
        this.arg$2 = longPredicate2;
    }

    public static LongPredicate lambdaFactory$(LongPredicate longPredicate, LongPredicate longPredicate2) {
        return new LongPredicates$$Lambda$1(longPredicate, longPredicate2);
    }

    @Override // java9.util.function.LongPredicate
    public boolean test(long j) {
        return LongPredicates.lambda$and$0(this.arg$1, this.arg$2, j);
    }
}
