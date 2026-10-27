package java9.util.stream;

import java9.util.function.DoubleFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$2 implements DoubleFunction {
    private static final DoublePipeline$$Lambda$2 instance = new DoublePipeline$$Lambda$2();

    private DoublePipeline$$Lambda$2() {
    }

    @Override // java9.util.function.DoubleFunction
    public Object apply(double d) {
        return Double.valueOf(d);
    }
}
