package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Functions$$Lambda$2 implements Function {
    private final Function arg$1;
    private final Function arg$2;

    private Functions$$Lambda$2(Function function, Function function2) {
        this.arg$1 = function;
        this.arg$2 = function2;
    }

    public static Function lambdaFactory$(Function function, Function function2) {
        return new Functions$$Lambda$2(function, function2);
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Functions.lambda$andThen$1(this.arg$1, this.arg$2, obj);
    }
}
