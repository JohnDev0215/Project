package java9.util.stream;

import java9.lang.Longs;
import java9.util.function.LongBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongPipeline$$Lambda$4 implements LongBinaryOperator {
    private static final LongPipeline$$Lambda$4 instance = new LongPipeline$$Lambda$4();

    private LongPipeline$$Lambda$4() {
    }

    @Override // java9.util.function.LongBinaryOperator
    public long applyAsLong(long j, long j2) {
        return Longs.sum(j, j2);
    }
}
