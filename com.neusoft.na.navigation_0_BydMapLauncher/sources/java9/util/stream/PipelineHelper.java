package java9.util.stream;

import java9.util.Spliterator;
import java9.util.function.Consumer;
import java9.util.function.IntFunction;
import java9.util.stream.Node;

/* JADX INFO: loaded from: classes3.dex */
abstract class PipelineHelper<P_OUT> {
    abstract <P_IN> void copyInto(Sink<P_IN> sink, Spliterator<P_IN> spliterator);

    abstract <P_IN> boolean copyIntoWithCancel(Sink<P_IN> sink, Spliterator<P_IN> spliterator);

    abstract <P_IN> Node<P_OUT> evaluate(Spliterator<P_IN> spliterator, boolean z, IntFunction<P_OUT[]> intFunction);

    abstract <P_IN> long exactOutputSizeIfKnown(Spliterator<P_IN> spliterator);

    abstract StreamShape getSourceShape();

    abstract int getStreamAndOpFlags();

    abstract Node.Builder<P_OUT> makeNodeBuilder(long j, IntFunction<P_OUT[]> intFunction);

    abstract <P_IN, S extends Consumer<P_OUT>> S wrapAndCopyInto(S s, Spliterator<P_IN> spliterator);

    abstract <P_IN, S extends Sink<P_OUT>> S wrapAndCopyInto(S s, Spliterator<P_IN> spliterator);

    abstract <P_IN> Sink<P_IN> wrapSink(Consumer<P_OUT> consumer);

    abstract <P_IN> Sink<P_IN> wrapSink(Sink<P_OUT> sink);

    abstract <P_IN> Spliterator<P_OUT> wrapSpliterator(Spliterator<P_IN> spliterator);

    PipelineHelper() {
    }
}
