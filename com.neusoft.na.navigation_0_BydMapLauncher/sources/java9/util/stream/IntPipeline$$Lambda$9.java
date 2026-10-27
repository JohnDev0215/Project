package java9.util.stream;

import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$9 implements BiConsumer {
    private static final IntPipeline$$Lambda$9 instance = new IntPipeline$$Lambda$9();

    private IntPipeline$$Lambda$9() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        IntPipeline.lambda$average$3((long[]) obj, (long[]) obj2);
    }
}
