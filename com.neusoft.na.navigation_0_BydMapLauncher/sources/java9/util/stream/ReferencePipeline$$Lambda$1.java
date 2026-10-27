package java9.util.stream;

import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class ReferencePipeline$$Lambda$1 implements IntFunction {
    private static final ReferencePipeline$$Lambda$1 instance = new ReferencePipeline$$Lambda$1();

    private ReferencePipeline$$Lambda$1() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return ReferencePipeline.lambda$toArray$0(i);
    }
}
