package java9.util.stream;

import java.util.Map;
import java9.util.function.BiConsumer;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$6 implements BiConsumer {
    private final Function arg$1;
    private final Function arg$2;

    private Collectors$$Lambda$6(Function function, Function function2) {
        this.arg$1 = function;
        this.arg$2 = function2;
    }

    public static BiConsumer lambdaFactory$(Function function, Function function2) {
        return new Collectors$$Lambda$6(function, function2);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$uniqKeysMapAccumulator$2(this.arg$1, this.arg$2, (Map) obj, obj2);
    }
}
