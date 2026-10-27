package java9.util.stream;

import java9.util.DoubleSummaryStatistics;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$13 implements BiConsumer {
    private static final DoublePipeline$$Lambda$13 instance = new DoublePipeline$$Lambda$13();

    private DoublePipeline$$Lambda$13() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((DoubleSummaryStatistics) obj).combine((DoubleSummaryStatistics) obj2);
    }
}
