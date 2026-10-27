package java9.util.stream;

import java9.util.function.LongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$CollectorTask$OfInt$$Lambda$1 implements LongFunction {
    private static final Nodes$CollectorTask$OfInt$$Lambda$1 instance = new Nodes$CollectorTask$OfInt$$Lambda$1();

    private Nodes$CollectorTask$OfInt$$Lambda$1() {
    }

    @Override // java9.util.function.LongFunction
    public Object apply(long j) {
        return Nodes.intBuilder(j);
    }
}
