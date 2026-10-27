package java9.util.stream;

import java9.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$3 implements ToDoubleFunction {
    private static final DoublePipeline$$Lambda$3 instance = new DoublePipeline$$Lambda$3();

    private DoublePipeline$$Lambda$3() {
    }

    @Override // java9.util.function.ToDoubleFunction
    public double applyAsDouble(Object obj) {
        return ((Double) obj).doubleValue();
    }
}
