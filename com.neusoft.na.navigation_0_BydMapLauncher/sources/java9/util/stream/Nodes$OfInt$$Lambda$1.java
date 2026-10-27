package java9.util.stream;

import java9.util.function.IntConsumer;
import java9.util.stream.Nodes;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$OfInt$$Lambda$1 implements IntConsumer {
    private static final Nodes$OfInt$$Lambda$1 instance = new Nodes$OfInt$$Lambda$1();

    private Nodes$OfInt$$Lambda$1() {
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        Nodes.OfInt.lambda$truncate$0(i);
    }
}
