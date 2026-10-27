package java9.util.stream;

import java.util.concurrent.ConcurrentMap;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$68 implements Function {
    private final Function arg$1;

    private Collectors$$Lambda$68(Function function) {
        this.arg$1 = function;
    }

    public static Function lambdaFactory$(Function function) {
        return new Collectors$$Lambda$68(function);
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$groupingByConcurrent$62(this.arg$1, (ConcurrentMap) obj);
    }
}
