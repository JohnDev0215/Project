package java9.util.stream;

import java9.util.function.LongBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$5 implements LongBinaryOperator {
    private static final LongPipeline$$Lambda$5 instance = new LongPipeline$$Lambda$5();

    private LongPipeline$$Lambda$5() {
    }

    @Override // java9.util.function.LongBinaryOperator
    public long applyAsLong(long j, long j2) {
        return Math.min(j, j2);
    }
}
