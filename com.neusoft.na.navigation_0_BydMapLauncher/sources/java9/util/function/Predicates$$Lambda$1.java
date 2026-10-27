package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Predicates$$Lambda$1 implements Predicate {
    private final Predicate arg$1;
    private final Predicate arg$2;

    private Predicates$$Lambda$1(Predicate predicate, Predicate predicate2) {
        this.arg$1 = predicate;
        this.arg$2 = predicate2;
    }

    public static Predicate lambdaFactory$(Predicate predicate, Predicate predicate2) {
        return new Predicates$$Lambda$1(predicate, predicate2);
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return Predicates.lambda$and$0(this.arg$1, this.arg$2, obj);
    }
}
