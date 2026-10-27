package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePredicates$$Lambda$3 implements DoublePredicate {
    private final DoublePredicate arg$1;
    private final DoublePredicate arg$2;

    private DoublePredicates$$Lambda$3(DoublePredicate doublePredicate, DoublePredicate doublePredicate2) {
        this.arg$1 = doublePredicate;
        this.arg$2 = doublePredicate2;
    }

    public static DoublePredicate lambdaFactory$(DoublePredicate doublePredicate, DoublePredicate doublePredicate2) {
        return new DoublePredicates$$Lambda$3(doublePredicate, doublePredicate2);
    }

    @Override // java9.util.function.DoublePredicate
    public boolean test(double d) {
        return DoublePredicates.lambda$or$2(this.arg$1, this.arg$2, d);
    }
}
