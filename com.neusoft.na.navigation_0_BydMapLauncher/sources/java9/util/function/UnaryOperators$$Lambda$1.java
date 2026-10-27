package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class UnaryOperators$$Lambda$1 implements UnaryOperator {
    private static final UnaryOperators$$Lambda$1 instance = new UnaryOperators$$Lambda$1();

    private UnaryOperators$$Lambda$1() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return UnaryOperators.lambda$identity$0(obj);
    }
}
