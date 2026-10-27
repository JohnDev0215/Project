package java9.util.stream;

import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class WhileOps$$Lambda$2 implements IntFunction {
    private static final WhileOps$$Lambda$2 instance = new WhileOps$$Lambda$2();

    private WhileOps$$Lambda$2() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return WhileOps.lambda$static$1(i);
    }
}
