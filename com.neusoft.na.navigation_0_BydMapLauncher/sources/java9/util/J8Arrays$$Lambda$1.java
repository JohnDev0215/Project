package java9.util;

import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class J8Arrays$$Lambda$1 implements IntConsumer {
    private final Object[] arg$1;
    private final IntFunction arg$2;

    private J8Arrays$$Lambda$1(Object[] objArr, IntFunction intFunction) {
        this.arg$1 = objArr;
        this.arg$2 = intFunction;
    }

    public static IntConsumer lambdaFactory$(Object[] objArr, IntFunction intFunction) {
        return new J8Arrays$$Lambda$1(objArr, intFunction);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        J8Arrays.lambda$parallelSetAll$0(this.arg$1, this.arg$2, i);
    }
}
