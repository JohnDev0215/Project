package java9.util.stream;

import java.util.Map;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$65 implements Function {
    private final Function arg$1;

    private Collectors$$Lambda$65(Function function) {
        this.arg$1 = function;
    }

    public static Function lambdaFactory$(Function function) {
        return new Collectors$$Lambda$65(function);
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$groupingBy$56(this.arg$1, (Map) obj);
    }
}
