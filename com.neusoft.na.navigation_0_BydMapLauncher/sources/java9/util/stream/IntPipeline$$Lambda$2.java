package java9.util.stream;

import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$2 implements IntFunction {
    private static final IntPipeline$$Lambda$2 instance = new IntPipeline$$Lambda$2();

    private IntPipeline$$Lambda$2() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return Integer.valueOf(i);
    }
}
