package java9.util.concurrent;

import java.util.concurrent.ConcurrentMap;
import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class ConcurrentMaps$$Lambda$1 implements BiConsumer {
    private final ConcurrentMap arg$1;
    private final BiFunction arg$2;

    private ConcurrentMaps$$Lambda$1(ConcurrentMap concurrentMap, BiFunction biFunction) {
        this.arg$1 = concurrentMap;
        this.arg$2 = biFunction;
    }

    public static BiConsumer lambdaFactory$(ConcurrentMap concurrentMap, BiFunction biFunction) {
        return new ConcurrentMaps$$Lambda$1(concurrentMap, biFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ConcurrentMaps.lambda$replaceAll$0(this.arg$1, this.arg$2, obj, obj2);
    }
}
