package java9.util.stream;

import java9.util.LongSummaryStatistics;
import java9.util.function.ObjLongConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$10 implements ObjLongConsumer {
    private static final LongPipeline$$Lambda$10 instance = new LongPipeline$$Lambda$10();

    private LongPipeline$$Lambda$10() {
    }

    @Override // java9.util.function.ObjLongConsumer
    public void accept(Object obj, long j) {
        ((LongSummaryStatistics) obj).accept(j);
    }
}
