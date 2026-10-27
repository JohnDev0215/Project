package java9.util.stream;

import java9.util.Spliterator;

/* JADX INFO: loaded from: classes3.dex */
interface TerminalOp<E_IN, R> {
    <P_IN> R evaluateParallel(PipelineHelper<E_IN> pipelineHelper, Spliterator<P_IN> spliterator);

    <P_IN> R evaluateSequential(PipelineHelper<E_IN> pipelineHelper, Spliterator<P_IN> spliterator);

    int getOpFlags();

    StreamShape inputShape();
}
