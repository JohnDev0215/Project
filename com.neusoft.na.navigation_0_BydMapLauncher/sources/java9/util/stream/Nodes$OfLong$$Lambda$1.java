package java9.util.stream;

import java9.util.function.LongConsumer;
import java9.util.stream.Nodes;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$OfLong$$Lambda$1 implements LongConsumer {
    private static final Nodes$OfLong$$Lambda$1 instance = new Nodes$OfLong$$Lambda$1();

    private Nodes$OfLong$$Lambda$1() {
    }

    @Override // java9.util.function.LongConsumer
    public void accept(long j) {
        Nodes.OfLong.lambda$truncate$0(j);
    }
}
