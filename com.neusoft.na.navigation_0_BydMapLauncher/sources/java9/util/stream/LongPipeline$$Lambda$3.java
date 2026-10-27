package java9.util.stream;

import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$3 implements ToLongFunction {
    private static final LongPipeline$$Lambda$3 instance = new LongPipeline$$Lambda$3();

    private LongPipeline$$Lambda$3() {
    }

    @Override // java9.util.function.ToLongFunction
    public long applyAsLong(Object obj) {
        return ((Long) obj).longValue();
    }
}
