package java9.util.stream;

import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$9 implements BiConsumer {
    private static final LongPipeline$$Lambda$9 instance = new LongPipeline$$Lambda$9();

    private LongPipeline$$Lambda$9() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        LongPipeline.lambda$average$3((long[]) obj, (long[]) obj2);
    }
}
