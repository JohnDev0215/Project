package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$7 implements Supplier {
    private static final LongPipeline$$Lambda$7 instance = new LongPipeline$$Lambda$7();

    private LongPipeline$$Lambda$7() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return LongPipeline.lambda$average$1();
    }
}
