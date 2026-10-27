package java9.util.stream;

import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class AbstractPipeline$$Lambda$3 implements IntFunction {
    private static final AbstractPipeline$$Lambda$3 instance = new AbstractPipeline$$Lambda$3();

    private AbstractPipeline$$Lambda$3() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return AbstractPipeline.lambda$opEvaluateParallelLazy$2(i);
    }
}
