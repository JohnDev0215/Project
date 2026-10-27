package java9.util;

import java9.util.function.IntConsumer;
import java9.util.function.IntToDoubleFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class J8Arrays$$Lambda$4 implements IntConsumer {
    private final double[] arg$1;
    private final IntToDoubleFunction arg$2;

    private J8Arrays$$Lambda$4(double[] dArr, IntToDoubleFunction intToDoubleFunction) {
        this.arg$1 = dArr;
        this.arg$2 = intToDoubleFunction;
    }

    public static IntConsumer lambdaFactory$(double[] dArr, IntToDoubleFunction intToDoubleFunction) {
        return new J8Arrays$$Lambda$4(dArr, intToDoubleFunction);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        J8Arrays.lambda$parallelSetAll$3(this.arg$1, this.arg$2, i);
    }
}
