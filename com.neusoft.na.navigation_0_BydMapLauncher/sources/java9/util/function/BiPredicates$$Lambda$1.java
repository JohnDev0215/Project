package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class BiPredicates$$Lambda$1 implements BiPredicate {
    private final BiPredicate arg$1;
    private final BiPredicate arg$2;

    private BiPredicates$$Lambda$1(BiPredicate biPredicate, BiPredicate biPredicate2) {
        this.arg$1 = biPredicate;
        this.arg$2 = biPredicate2;
    }

    public static BiPredicate lambdaFactory$(BiPredicate biPredicate, BiPredicate biPredicate2) {
        return new BiPredicates$$Lambda$1(biPredicate, biPredicate2);
    }

    @Override // java9.util.function.BiPredicate
    public boolean test(Object obj, Object obj2) {
        return BiPredicates.lambda$and$0(this.arg$1, this.arg$2, obj, obj2);
    }
}
