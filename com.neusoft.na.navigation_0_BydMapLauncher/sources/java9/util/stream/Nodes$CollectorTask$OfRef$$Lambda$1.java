package java9.util.stream;

import java9.util.function.IntFunction;
import java9.util.function.LongFunction;
import java9.util.stream.Nodes;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$CollectorTask$OfRef$$Lambda$1 implements LongFunction {
    private final IntFunction arg$1;

    private Nodes$CollectorTask$OfRef$$Lambda$1(IntFunction intFunction) {
        this.arg$1 = intFunction;
    }

    public static LongFunction lambdaFactory$(IntFunction intFunction) {
        return new Nodes$CollectorTask$OfRef$$Lambda$1(intFunction);
    }

    @Override // java9.util.function.LongFunction
    public Object apply(long j) {
        return Nodes.CollectorTask.OfRef.lambda$new$0(this.arg$1, j);
    }
}
