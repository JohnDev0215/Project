package java9.util.stream;

import java9.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$6 implements IntBinaryOperator {
    private static final IntPipeline$$Lambda$6 instance = new IntPipeline$$Lambda$6();

    private IntPipeline$$Lambda$6() {
    }

    @Override // java9.util.function.IntBinaryOperator
    public int applyAsInt(int i, int i2) {
        return Math.max(i, i2);
    }
}
