package java9.util.stream;

import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class WhileOps$$Lambda$3 implements IntFunction {
    private static final WhileOps$$Lambda$3 instance = new WhileOps$$Lambda$3();

    private WhileOps$$Lambda$3() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return WhileOps.lambda$static$2(i);
    }
}
