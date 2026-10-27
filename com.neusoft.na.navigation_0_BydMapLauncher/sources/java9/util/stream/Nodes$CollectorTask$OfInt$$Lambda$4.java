package java9.util.stream;

import java9.util.function.BinaryOperator;
import java9.util.stream.Node;
import java9.util.stream.Nodes;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Nodes$CollectorTask$OfInt$$Lambda$4 implements BinaryOperator {
    private static final Nodes$CollectorTask$OfInt$$Lambda$4 instance = new Nodes$CollectorTask$OfInt$$Lambda$4();

    private Nodes$CollectorTask$OfInt$$Lambda$4() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return new Nodes.ConcNode.OfInt((Node.OfInt) obj, (Node.OfInt) obj2);
    }
}
