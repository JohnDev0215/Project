package java9.util.stream;

import java9.util.DoubleSummaryStatistics;
import java9.util.function.ObjDoubleConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$12 implements ObjDoubleConsumer {
    private static final DoublePipeline$$Lambda$12 instance = new DoublePipeline$$Lambda$12();

    private DoublePipeline$$Lambda$12() {
    }

    @Override // java9.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        ((DoubleSummaryStatistics) obj).accept(d);
    }
}
