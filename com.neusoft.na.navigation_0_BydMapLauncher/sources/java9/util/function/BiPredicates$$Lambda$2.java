package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class BiPredicates$$Lambda$2 implements BiPredicate {
    private final BiPredicate arg$1;

    private BiPredicates$$Lambda$2(BiPredicate biPredicate) {
        this.arg$1 = biPredicate;
    }

    public static BiPredicate lambdaFactory$(BiPredicate biPredicate) {
        return new BiPredicates$$Lambda$2(biPredicate);
    }

    @Override // java9.util.function.BiPredicate
    public boolean test(Object obj, Object obj2) {
        return BiPredicates.lambda$negate$1(this.arg$1, obj, obj2);
    }
}
