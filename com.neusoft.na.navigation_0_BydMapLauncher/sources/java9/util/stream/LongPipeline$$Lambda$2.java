package java9.util.stream;

import java9.util.function.LongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$2 implements LongFunction {
    private static final LongPipeline$$Lambda$2 instance = new LongPipeline$$Lambda$2();

    private LongPipeline$$Lambda$2() {
    }

    @Override // java9.util.function.LongFunction
    public Object apply(long j) {
        return Long.valueOf(j);
    }
}
