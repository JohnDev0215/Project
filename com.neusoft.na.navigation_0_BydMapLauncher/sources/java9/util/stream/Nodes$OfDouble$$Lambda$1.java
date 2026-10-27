package java9.util.stream;

import java9.util.function.DoubleConsumer;
import java9.util.stream.Nodes;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$OfDouble$$Lambda$1 implements DoubleConsumer {
    private static final Nodes$OfDouble$$Lambda$1 instance = new Nodes$OfDouble$$Lambda$1();

    private Nodes$OfDouble$$Lambda$1() {
    }

    @Override // java9.util.function.DoubleConsumer
    public void accept(double d) {
        Nodes.OfDouble.lambda$truncate$0(d);
    }
}
