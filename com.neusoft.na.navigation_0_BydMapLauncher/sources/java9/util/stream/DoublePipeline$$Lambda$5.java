package java9.util.stream;

import java9.util.function.ObjDoubleConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$5 implements ObjDoubleConsumer {
    private static final DoublePipeline$$Lambda$5 instance = new DoublePipeline$$Lambda$5();

    private DoublePipeline$$Lambda$5() {
    }

    @Override // java9.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        DoublePipeline.lambda$sum$2((double[]) obj, d);
    }
}
