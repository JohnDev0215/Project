package java9.util.stream;

import java9.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$3 implements ToIntFunction {
    private static final IntPipeline$$Lambda$3 instance = new IntPipeline$$Lambda$3();

    private IntPipeline$$Lambda$3() {
    }

    @Override // java9.util.function.ToIntFunction
    public int applyAsInt(Object obj) {
        return ((Integer) obj).intValue();
    }
}
