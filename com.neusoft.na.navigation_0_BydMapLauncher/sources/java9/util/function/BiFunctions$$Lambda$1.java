package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class BiFunctions$$Lambda$1 implements BiFunction {
    private final Function arg$1;
    private final BiFunction arg$2;

    private BiFunctions$$Lambda$1(Function function, BiFunction biFunction) {
        this.arg$1 = function;
        this.arg$2 = biFunction;
    }

    public static BiFunction lambdaFactory$(Function function, BiFunction biFunction) {
        return new BiFunctions$$Lambda$1(function, biFunction);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return this.arg$1.apply(this.arg$2.apply(obj, obj2));
    }
}
