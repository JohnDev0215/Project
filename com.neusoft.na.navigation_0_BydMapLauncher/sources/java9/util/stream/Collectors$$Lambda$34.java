package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$34 implements BiConsumer {
    private final ToLongFunction arg$1;

    private Collectors$$Lambda$34(ToLongFunction toLongFunction) {
        this.arg$1 = toLongFunction;
    }

    public static BiConsumer lambdaFactory$(ToLongFunction toLongFunction) {
        return new Collectors$$Lambda$34(toLongFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$summingLong$24(this.arg$1, (long[]) obj, obj2);
    }
}
