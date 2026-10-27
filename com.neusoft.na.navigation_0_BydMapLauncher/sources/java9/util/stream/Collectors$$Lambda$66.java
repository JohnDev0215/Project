package java9.util.stream;

import java.util.concurrent.ConcurrentMap;
import java9.util.function.BiConsumer;
import java9.util.function.Function;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$66 implements BiConsumer {
    private final Function arg$1;
    private final Supplier arg$2;
    private final BiConsumer arg$3;

    private Collectors$$Lambda$66(Function function, Supplier supplier, BiConsumer biConsumer) {
        this.arg$1 = function;
        this.arg$2 = supplier;
        this.arg$3 = biConsumer;
    }

    public static BiConsumer lambdaFactory$(Function function, Supplier supplier, BiConsumer biConsumer) {
        return new Collectors$$Lambda$66(function, supplier, biConsumer);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$groupingByConcurrent$58(this.arg$1, this.arg$2, this.arg$3, (ConcurrentMap) obj, obj2);
    }
}
