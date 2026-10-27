package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPredicates$$Lambda$3 implements IntPredicate {
    private final IntPredicate arg$1;
    private final IntPredicate arg$2;

    private IntPredicates$$Lambda$3(IntPredicate intPredicate, IntPredicate intPredicate2) {
        this.arg$1 = intPredicate;
        this.arg$2 = intPredicate2;
    }

    public static IntPredicate lambdaFactory$(IntPredicate intPredicate, IntPredicate intPredicate2) {
        return new IntPredicates$$Lambda$3(intPredicate, intPredicate2);
    }

    @Override // java9.util.function.IntPredicate
    public boolean test(int i) {
        return IntPredicates.lambda$or$2(this.arg$1, this.arg$2, i);
    }
}
