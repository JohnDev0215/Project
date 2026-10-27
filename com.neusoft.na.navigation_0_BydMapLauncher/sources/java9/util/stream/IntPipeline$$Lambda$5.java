package java9.util.stream;

import java9.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$5 implements IntBinaryOperator {
    private static final IntPipeline$$Lambda$5 instance = new IntPipeline$$Lambda$5();

    private IntPipeline$$Lambda$5() {
    }

    @Override // java9.util.function.IntBinaryOperator
    public int applyAsInt(int i, int i2) {
        return Math.min(i, i2);
    }
}
