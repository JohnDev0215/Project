package java9.util.stream;

import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$6 implements BiConsumer {
    private static final DoublePipeline$$Lambda$6 instance = new DoublePipeline$$Lambda$6();

    private DoublePipeline$$Lambda$6() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        DoublePipeline.lambda$sum$3((double[]) obj, (double[]) obj2);
    }
}
