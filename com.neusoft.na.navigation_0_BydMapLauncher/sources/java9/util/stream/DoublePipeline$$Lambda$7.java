package java9.util.stream;

import java9.util.function.DoubleBinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$7 implements DoubleBinaryOperator {
    private static final DoublePipeline$$Lambda$7 instance = new DoublePipeline$$Lambda$7();

    private DoublePipeline$$Lambda$7() {
    }

    @Override // java9.util.function.DoubleBinaryOperator
    public double applyAsDouble(double d, double d2) {
        return Math.min(d, d2);
    }
}
