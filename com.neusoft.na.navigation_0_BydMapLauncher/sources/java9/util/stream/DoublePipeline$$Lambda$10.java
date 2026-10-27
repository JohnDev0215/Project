package java9.util.stream;

import java9.util.function.ObjDoubleConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$10 implements ObjDoubleConsumer {
    private static final DoublePipeline$$Lambda$10 instance = new DoublePipeline$$Lambda$10();

    private DoublePipeline$$Lambda$10() {
    }

    @Override // java9.util.function.ObjDoubleConsumer
    public void accept(Object obj, double d) {
        DoublePipeline.lambda$average$5((double[]) obj, d);
    }
}
