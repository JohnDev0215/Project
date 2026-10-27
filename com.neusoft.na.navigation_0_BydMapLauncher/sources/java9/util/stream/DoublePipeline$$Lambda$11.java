package java9.util.stream;

import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$11 implements BiConsumer {
    private static final DoublePipeline$$Lambda$11 instance = new DoublePipeline$$Lambda$11();

    private DoublePipeline$$Lambda$11() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        DoublePipeline.lambda$average$6((double[]) obj, (double[]) obj2);
    }
}
