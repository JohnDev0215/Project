package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePredicates$$Lambda$2 implements DoublePredicate {
    private final DoublePredicate arg$1;

    private DoublePredicates$$Lambda$2(DoublePredicate doublePredicate) {
        this.arg$1 = doublePredicate;
    }

    public static DoublePredicate lambdaFactory$(DoublePredicate doublePredicate) {
        return new DoublePredicates$$Lambda$2(doublePredicate);
    }

    @Override // java9.util.function.DoublePredicate
    public boolean test(double d) {
        return DoublePredicates.lambda$negate$1(this.arg$1, d);
    }
}
