package java9.util.stream;

import java9.util.IntSummaryStatistics;
import java9.util.function.ObjIntConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$10 implements ObjIntConsumer {
    private static final IntPipeline$$Lambda$10 instance = new IntPipeline$$Lambda$10();

    private IntPipeline$$Lambda$10() {
    }

    @Override // java9.util.function.ObjIntConsumer
    public void accept(Object obj, int i) {
        ((IntSummaryStatistics) obj).accept(i);
    }
}
