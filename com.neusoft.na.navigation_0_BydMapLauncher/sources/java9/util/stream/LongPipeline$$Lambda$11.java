package java9.util.stream;

import java9.util.LongSummaryStatistics;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$11 implements BiConsumer {
    private static final LongPipeline$$Lambda$11 instance = new LongPipeline$$Lambda$11();

    private LongPipeline$$Lambda$11() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((LongSummaryStatistics) obj).combine((LongSummaryStatistics) obj2);
    }
}
