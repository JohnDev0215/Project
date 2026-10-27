package java9.util.stream;

import java9.util.function.IntFunction;
import java9.util.stream.ForEachOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class ForEachOps$ForEachOrderedTask$$Lambda$1 implements IntFunction {
    private static final ForEachOps$ForEachOrderedTask$$Lambda$1 instance = new ForEachOps$ForEachOrderedTask$$Lambda$1();

    private ForEachOps$ForEachOrderedTask$$Lambda$1() {
    }

    @Override // java9.util.function.IntFunction
    public Object apply(int i) {
        return ForEachOps.ForEachOrderedTask.lambda$doCompute$0(i);
    }
}
