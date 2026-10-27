package java9.util;

import java9.util.function.IntConsumer;
import java9.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class J8Arrays$$Lambda$2 implements IntConsumer {
    private final int[] arg$1;
    private final IntUnaryOperator arg$2;

    private J8Arrays$$Lambda$2(int[] iArr, IntUnaryOperator intUnaryOperator) {
        this.arg$1 = iArr;
        this.arg$2 = intUnaryOperator;
    }

    public static IntConsumer lambdaFactory$(int[] iArr, IntUnaryOperator intUnaryOperator) {
        return new J8Arrays$$Lambda$2(iArr, intUnaryOperator);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        J8Arrays.lambda$parallelSetAll$1(this.arg$1, this.arg$2, i);
    }
}
