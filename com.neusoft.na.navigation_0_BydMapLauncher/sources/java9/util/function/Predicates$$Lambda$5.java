package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Predicates$$Lambda$5 implements Predicate {
    private final Object arg$1;

    private Predicates$$Lambda$5(Object obj) {
        this.arg$1 = obj;
    }

    public static Predicate lambdaFactory$(Object obj) {
        return new Predicates$$Lambda$5(obj);
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return this.arg$1.equals(obj);
    }
}
