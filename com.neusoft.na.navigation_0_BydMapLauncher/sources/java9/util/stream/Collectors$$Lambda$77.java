package java9.util.stream;

import java9.util.LongSummaryStatistics;
import java9.util.function.BiConsumer;
import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$77 implements BiConsumer {
    private final ToLongFunction arg$1;

    private Collectors$$Lambda$77(ToLongFunction toLongFunction) {
        this.arg$1 = toLongFunction;
    }

    public static BiConsumer lambdaFactory$(ToLongFunction toLongFunction) {
        return new Collectors$$Lambda$77(toLongFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$summarizingLong$71(this.arg$1, (LongSummaryStatistics) obj, obj2);
    }
}
