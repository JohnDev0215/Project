package java9.util.stream;

import java9.util.function.BiFunction;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$88 implements BiFunction {
    private final Function arg$1;

    private Collectors$$Lambda$88(Function function) {
        this.arg$1 = function;
    }

    public static BiFunction lambdaFactory$(Function function) {
        return new Collectors$$Lambda$88(function);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$null$55(this.arg$1, obj, obj2);
    }
}
