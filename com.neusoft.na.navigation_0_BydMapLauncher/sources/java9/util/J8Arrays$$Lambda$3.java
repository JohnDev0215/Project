package java9.util;

import java9.util.function.IntConsumer;
import java9.util.function.IntToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class J8Arrays$$Lambda$3 implements IntConsumer {
    private final long[] arg$1;
    private final IntToLongFunction arg$2;

    private J8Arrays$$Lambda$3(long[] jArr, IntToLongFunction intToLongFunction) {
        this.arg$1 = jArr;
        this.arg$2 = intToLongFunction;
    }

    public static IntConsumer lambdaFactory$(long[] jArr, IntToLongFunction intToLongFunction) {
        return new J8Arrays$$Lambda$3(jArr, intToLongFunction);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        J8Arrays.lambda$parallelSetAll$2(this.arg$1, this.arg$2, i);
    }
}
