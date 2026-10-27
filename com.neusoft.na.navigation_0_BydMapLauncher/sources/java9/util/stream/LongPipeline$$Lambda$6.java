package java9.util.stream;

import java9.util.function.LongBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$6 implements LongBinaryOperator {
    private static final LongPipeline$$Lambda$6 instance = new LongPipeline$$Lambda$6();

    private LongPipeline$$Lambda$6() {
    }

    @Override // java9.util.function.LongBinaryOperator
    public long applyAsLong(long j, long j2) {
        return Math.max(j, j2);
    }
}
