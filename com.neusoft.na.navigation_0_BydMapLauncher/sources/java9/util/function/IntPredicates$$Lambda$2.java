package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPredicates$$Lambda$2 implements IntPredicate {
    private final IntPredicate arg$1;

    private IntPredicates$$Lambda$2(IntPredicate intPredicate) {
        this.arg$1 = intPredicate;
    }

    public static IntPredicate lambdaFactory$(IntPredicate intPredicate) {
        return new IntPredicates$$Lambda$2(intPredicate);
    }

    @Override // java9.util.function.IntPredicate
    public boolean test(int i) {
        return IntPredicates.lambda$negate$1(this.arg$1, i);
    }
}
