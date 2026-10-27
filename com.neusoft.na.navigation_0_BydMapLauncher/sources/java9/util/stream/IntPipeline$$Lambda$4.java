package java9.util.stream;

import java9.lang.Integers;
import java9.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$4 implements IntBinaryOperator {
    private static final IntPipeline$$Lambda$4 instance = new IntPipeline$$Lambda$4();

    private IntPipeline$$Lambda$4() {
    }

    @Override // java9.util.function.IntBinaryOperator
    public int applyAsInt(int i, int i2) {
        return Integers.sum(i, i2);
    }
}
