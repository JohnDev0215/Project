package java9.util.stream;

import java9.util.function.LongConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$1 implements LongConsumer {
    private final Sink arg$1;

    private LongPipeline$$Lambda$1(Sink sink) {
        this.arg$1 = sink;
    }

    public static LongConsumer lambdaFactory$(Sink sink) {
        return new LongPipeline$$Lambda$1(sink);
    }

    @Override // java9.util.function.LongConsumer
    public void accept(long j) {
        this.arg$1.accept(j);
    }
}
