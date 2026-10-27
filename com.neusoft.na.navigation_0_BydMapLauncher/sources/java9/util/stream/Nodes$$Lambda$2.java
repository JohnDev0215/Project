package java9.util.stream;

import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$$Lambda$2 implements IntFunction {
    private static final Nodes$$Lambda$2 instance = new Nodes$$Lambda$2();

    private Nodes$$Lambda$2() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return Nodes.lambda$castingArray$1(i);
    }
}
