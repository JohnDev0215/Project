package java9.util.stream;

import java9.util.IntSummaryStatistics;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$11 implements BiConsumer {
    private static final IntPipeline$$Lambda$11 instance = new IntPipeline$$Lambda$11();

    private IntPipeline$$Lambda$11() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((IntSummaryStatistics) obj).combine((IntSummaryStatistics) obj2);
    }
}
