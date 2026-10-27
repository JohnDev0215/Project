package java9.util.stream;

import java9.util.function.BinaryOperator;
import java9.util.stream.Node;
import java9.util.stream.Nodes;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$CollectorTask$OfDouble$$Lambda$2 implements BinaryOperator {
    private static final Nodes$CollectorTask$OfDouble$$Lambda$2 instance = new Nodes$CollectorTask$OfDouble$$Lambda$2();

    private Nodes$CollectorTask$OfDouble$$Lambda$2() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return new Nodes.ConcNode.OfDouble((Node.OfDouble) obj, (Node.OfDouble) obj2);
    }
}
