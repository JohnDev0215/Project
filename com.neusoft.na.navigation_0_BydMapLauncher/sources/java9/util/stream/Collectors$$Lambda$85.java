package java9.util.stream;

import java9.util.function.BiFunction;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$85 implements BiFunction {
    private final Function arg$1;

    private Collectors$$Lambda$85(Function function) {
        this.arg$1 = function;
    }

    public static BiFunction lambdaFactory$(Function function) {
        return new Collectors$$Lambda$85(function);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$null$61(this.arg$1, obj, obj2);
    }
}
