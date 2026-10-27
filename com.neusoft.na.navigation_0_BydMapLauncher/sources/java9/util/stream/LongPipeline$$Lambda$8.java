package java9.util.stream;

import java9.util.function.ObjLongConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$8 implements ObjLongConsumer {
    private static final LongPipeline$$Lambda$8 instance = new LongPipeline$$Lambda$8();

    private LongPipeline$$Lambda$8() {
    }

    @Override // java9.util.function.ObjLongConsumer
    public void accept(Object obj, long j) {
        LongPipeline.lambda$average$2((long[]) obj, j);
    }
}
