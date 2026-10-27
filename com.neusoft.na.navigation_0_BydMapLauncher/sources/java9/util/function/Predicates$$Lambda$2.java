package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Predicates$$Lambda$2 implements Predicate {
    private final Predicate arg$1;

    private Predicates$$Lambda$2(Predicate predicate) {
        this.arg$1 = predicate;
    }

    public static Predicate lambdaFactory$(Predicate predicate) {
        return new Predicates$$Lambda$2(predicate);
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return Predicates.lambda$negate$1(this.arg$1, obj);
    }
}
