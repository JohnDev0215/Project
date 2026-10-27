package java9.util.stream;

import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.function.Consumer;
import java9.util.function.IntFunction;
import java9.util.function.Supplier;
import java9.util.stream.BaseStream;
import java9.util.stream.Node;

/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractPipeline<E_IN, E_OUT, S extends BaseStream<E_OUT, S>> extends PipelineHelper<E_OUT> implements BaseStream<E_OUT, S> {
    private static final String MSG_CONSUMED = "source already consumed or closed";
    private static final String MSG_STREAM_LINKED = "stream has already been operated upon or closed";
    private int combinedFlags;
    private int depth;
    private boolean linkedOrConsumed;
    private AbstractPipeline nextStage;
    private boolean parallel;
    private final AbstractPipeline previousStage;
    private boolean sourceAnyStateful;
    private Runnable sourceCloseAction;
    protected final int sourceOrOpFlags;
    private Spliterator<?> sourceSpliterator;
    private final AbstractPipeline sourceStage;
    private Supplier<? extends Spliterator<?>> sourceSupplier;

    static /* synthetic */ Spliterator lambda$wrapSpliterator$1(Spliterator spliterator) {
        return spliterator;
    }

    abstract <P_IN> Node<E_OUT> evaluateToNode(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator, boolean z, IntFunction<E_OUT[]> intFunction);

    abstract boolean forEachWithCancel(Spliterator<E_OUT> spliterator, Sink<E_OUT> sink);

    abstract StreamShape getOutputShape();

    /* JADX INFO: renamed from: lazySpliterator */
    abstract Spliterator<E_OUT> lazySpliterator2(Supplier<? extends Spliterator<E_OUT>> supplier);

    @Override // java9.util.stream.PipelineHelper
    abstract Node.Builder<E_OUT> makeNodeBuilder(long j, IntFunction<E_OUT[]> intFunction);

    abstract boolean opIsStateful();

    abstract Sink<E_IN> opWrapSink(int i, Sink<E_OUT> sink);

    abstract <P_IN> Spliterator<E_OUT> wrap(PipelineHelper<E_OUT> pipelineHelper, Supplier<Spliterator<P_IN>> supplier, boolean z);

    AbstractPipeline(Supplier<? extends Spliterator<?>> supplier, int i, boolean z) {
        this.previousStage = null;
        this.sourceSupplier = supplier;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = (~(i2 << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    AbstractPipeline(Spliterator<?> spliterator, int i, boolean z) {
        this.previousStage = null;
        this.sourceSpliterator = spliterator;
        this.sourceStage = this;
        int i2 = StreamOpFlag.STREAM_MASK & i;
        this.sourceOrOpFlags = i2;
        this.combinedFlags = (~(i2 << 1)) & StreamOpFlag.INITIAL_OPS_VALUE;
        this.depth = 0;
        this.parallel = z;
    }

    AbstractPipeline(AbstractPipeline<?, E_IN, ?> abstractPipeline, int i) {
        if (abstractPipeline.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        abstractPipeline.linkedOrConsumed = true;
        abstractPipeline.nextStage = this;
        this.previousStage = abstractPipeline;
        this.sourceOrOpFlags = StreamOpFlag.OP_MASK & i;
        this.combinedFlags = StreamOpFlag.combineOpFlags(i, abstractPipeline.combinedFlags);
        AbstractPipeline abstractPipeline2 = abstractPipeline.sourceStage;
        this.sourceStage = abstractPipeline2;
        if (opIsStateful()) {
            abstractPipeline2.sourceAnyStateful = true;
        }
        this.depth = abstractPipeline.depth + 1;
    }

    final <R> R evaluate(TerminalOp<E_OUT, R> terminalOp) {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        this.linkedOrConsumed = true;
        if (isParallel()) {
            return terminalOp.evaluateParallel(this, sourceSpliterator(terminalOp.getOpFlags()));
        }
        return terminalOp.evaluateSequential(this, sourceSpliterator(terminalOp.getOpFlags()));
    }

    final Node<E_OUT> evaluateToArrayNode(IntFunction<E_OUT[]> intFunction) {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        this.linkedOrConsumed = true;
        if (isParallel() && this.previousStage != null && opIsStateful()) {
            this.depth = 0;
            AbstractPipeline abstractPipeline = this.previousStage;
            return opEvaluateParallel(abstractPipeline, abstractPipeline.sourceSpliterator(0), intFunction);
        }
        return evaluate(sourceSpliterator(0), true, intFunction);
    }

    final Spliterator<E_OUT> sourceStageSpliterator() {
        AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this.sourceStage;
        if (this != abstractPipeline) {
            throw new IllegalStateException();
        }
        if (this.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        this.linkedOrConsumed = true;
        Spliterator<E_OUT> spliterator = (Spliterator<E_OUT>) abstractPipeline.sourceSpliterator;
        if (spliterator != null) {
            abstractPipeline.sourceSpliterator = null;
            return spliterator;
        }
        Supplier<? extends Spliterator<?>> supplier = abstractPipeline.sourceSupplier;
        if (supplier != null) {
            Spliterator<E_OUT> spliterator2 = (Spliterator) supplier.get();
            this.sourceStage.sourceSupplier = null;
            return spliterator2;
        }
        throw new IllegalStateException(MSG_CONSUMED);
    }

    @Override // java9.util.stream.BaseStream
    public final S sequential() {
        this.sourceStage.parallel = false;
        return this;
    }

    @Override // java9.util.stream.BaseStream
    public final S parallel() {
        this.sourceStage.parallel = true;
        return this;
    }

    @Override // java9.util.stream.BaseStream
    public void close() {
        this.linkedOrConsumed = true;
        this.sourceSupplier = null;
        this.sourceSpliterator = null;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Runnable runnable = abstractPipeline.sourceCloseAction;
        if (runnable != null) {
            abstractPipeline.sourceCloseAction = null;
            runnable.run();
        }
    }

    @Override // java9.util.stream.BaseStream
    public S onClose(Runnable runnable) {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        Objects.requireNonNull(runnable);
        AbstractPipeline abstractPipeline = this.sourceStage;
        Runnable runnable2 = abstractPipeline.sourceCloseAction;
        if (runnable2 != null) {
            runnable = Streams.composeWithExceptions(runnable2, runnable);
        }
        abstractPipeline.sourceCloseAction = runnable;
        return this;
    }

    @Override // java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator */
    public Spliterator<E_OUT> spliterator2() {
        if (this.linkedOrConsumed) {
            throw new IllegalStateException(MSG_STREAM_LINKED);
        }
        this.linkedOrConsumed = true;
        AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this.sourceStage;
        if (this == abstractPipeline) {
            Spliterator<E_OUT> spliterator = (Spliterator<E_OUT>) abstractPipeline.sourceSpliterator;
            if (spliterator != null) {
                abstractPipeline.sourceSpliterator = null;
                return spliterator;
            }
            Supplier<? extends Spliterator<?>> supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                abstractPipeline.sourceSupplier = null;
                return lazySpliterator2(supplier);
            }
            throw new IllegalStateException(MSG_CONSUMED);
        }
        return wrap(this, AbstractPipeline$$Lambda$1.lambdaFactory$(this), isParallel());
    }

    static /* synthetic */ Spliterator lambda$spliterator$0(AbstractPipeline abstractPipeline) {
        return abstractPipeline.sourceSpliterator(0);
    }

    @Override // java9.util.stream.BaseStream
    public final boolean isParallel() {
        return this.sourceStage.parallel;
    }

    final int getStreamFlags() {
        return StreamOpFlag.toStreamFlags(this.combinedFlags);
    }

    private Spliterator<?> sourceSpliterator(int i) {
        int i2;
        int i3;
        AbstractPipeline abstractPipeline = this.sourceStage;
        Spliterator<?> spliteratorOpEvaluateParallelLazy = abstractPipeline.sourceSpliterator;
        if (spliteratorOpEvaluateParallelLazy != null) {
            abstractPipeline.sourceSpliterator = null;
        } else {
            Supplier<? extends Spliterator<?>> supplier = abstractPipeline.sourceSupplier;
            if (supplier != null) {
                spliteratorOpEvaluateParallelLazy = supplier.get();
                this.sourceStage.sourceSupplier = null;
            } else {
                throw new IllegalStateException(MSG_CONSUMED);
            }
        }
        if (isParallel()) {
            AbstractPipeline abstractPipeline2 = this.sourceStage;
            if (abstractPipeline2.sourceAnyStateful) {
                AbstractPipeline abstractPipeline3 = abstractPipeline2.nextStage;
                int i4 = 1;
                while (abstractPipeline2 != this) {
                    int i5 = abstractPipeline3.sourceOrOpFlags;
                    if (abstractPipeline3.opIsStateful()) {
                        if (StreamOpFlag.SHORT_CIRCUIT.isKnown(i5)) {
                            i5 &= ~StreamOpFlag.IS_SHORT_CIRCUIT;
                        }
                        spliteratorOpEvaluateParallelLazy = abstractPipeline3.opEvaluateParallelLazy(abstractPipeline2, spliteratorOpEvaluateParallelLazy);
                        if (spliteratorOpEvaluateParallelLazy.hasCharacteristics(64)) {
                            i2 = (~StreamOpFlag.NOT_SIZED) & i5;
                            i3 = StreamOpFlag.IS_SIZED;
                        } else {
                            i2 = (~StreamOpFlag.IS_SIZED) & i5;
                            i3 = StreamOpFlag.NOT_SIZED;
                        }
                        i5 = i2 | i3;
                        i4 = 0;
                    }
                    abstractPipeline3.depth = i4;
                    abstractPipeline3.combinedFlags = StreamOpFlag.combineOpFlags(i5, abstractPipeline2.combinedFlags);
                    i4++;
                    AbstractPipeline abstractPipeline4 = abstractPipeline3;
                    abstractPipeline3 = abstractPipeline3.nextStage;
                    abstractPipeline2 = abstractPipeline4;
                }
            }
        }
        if (i != 0) {
            this.combinedFlags = StreamOpFlag.combineOpFlags(i, this.combinedFlags);
        }
        return spliteratorOpEvaluateParallelLazy;
    }

    @Override // java9.util.stream.PipelineHelper
    final StreamShape getSourceShape() {
        AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        return abstractPipeline.getOutputShape();
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN> long exactOutputSizeIfKnown(Spliterator<P_IN> spliterator) {
        if (StreamOpFlag.SIZED.isKnown(getStreamAndOpFlags())) {
            return spliterator.getExactSizeIfKnown();
        }
        return -1L;
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN, S_ extends Sink<E_OUT>> S_ wrapAndCopyInto(S_ s_, Spliterator<P_IN> spliterator) {
        copyInto(wrapSink((Sink) Objects.requireNonNull(s_)), spliterator);
        return s_;
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN, S_ extends Consumer<E_OUT>> S_ wrapAndCopyInto(S_ s_, Spliterator<P_IN> spliterator) {
        copyInto(wrapSink((Consumer) Objects.requireNonNull(s_)), spliterator);
        return s_;
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN> void copyInto(Sink<P_IN> sink, Spliterator<P_IN> spliterator) {
        Objects.requireNonNull(sink);
        if (!StreamOpFlag.SHORT_CIRCUIT.isKnown(getStreamAndOpFlags())) {
            sink.begin(spliterator.getExactSizeIfKnown());
            spliterator.forEachRemaining(sink);
            sink.end();
            return;
        }
        copyIntoWithCancel(sink, spliterator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java9.util.stream.PipelineHelper
    final <P_IN> boolean copyIntoWithCancel(Sink<P_IN> sink, Spliterator<P_IN> spliterator) {
        AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this;
        while (abstractPipeline.depth > 0) {
            abstractPipeline = abstractPipeline.previousStage;
        }
        sink.begin(spliterator.getExactSizeIfKnown());
        boolean zForEachWithCancel = abstractPipeline.forEachWithCancel(spliterator, sink);
        sink.end();
        return zForEachWithCancel;
    }

    @Override // java9.util.stream.PipelineHelper
    final int getStreamAndOpFlags() {
        return this.combinedFlags;
    }

    final boolean isOrdered() {
        return StreamOpFlag.ORDERED.isKnown(this.combinedFlags);
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN> Sink<P_IN> wrapSink(Sink<E_OUT> sink) {
        Objects.requireNonNull(sink);
        for (AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this; abstractPipeline.depth > 0; abstractPipeline = abstractPipeline.previousStage) {
            sink = (Sink<P_IN>) abstractPipeline.opWrapSink(abstractPipeline.previousStage.combinedFlags, sink);
        }
        return (Sink<P_IN>) sink;
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN> Sink<P_IN> wrapSink(final Consumer<E_OUT> consumer) {
        Objects.requireNonNull(consumer);
        Sink<E_OUT> sink = new Sink<E_OUT>() { // from class: java9.util.stream.AbstractPipeline.1
            @Override // java9.util.stream.Sink
            public void begin(long j) {
            }

            @Override // java9.util.stream.Sink
            public boolean cancellationRequested() {
                return false;
            }

            @Override // java9.util.stream.Sink
            public void end() {
            }

            @Override // java9.util.function.Consumer
            public void accept(E_OUT e_out) {
                consumer.accept(e_out);
            }

            @Override // java9.util.stream.Sink
            public void accept(int i) {
                SinkDefaults.reject();
            }

            @Override // java9.util.stream.Sink
            public void accept(long j) {
                SinkDefaults.reject();
            }

            @Override // java9.util.stream.Sink
            public void accept(double d) {
                SinkDefaults.reject();
            }
        };
        for (AbstractPipeline<E_IN, E_OUT, S> abstractPipeline = this; abstractPipeline.depth > 0; abstractPipeline = abstractPipeline.previousStage) {
            sink = (Sink<P_IN>) abstractPipeline.opWrapSink(abstractPipeline.previousStage.combinedFlags, sink);
        }
        return (Sink<P_IN>) sink;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java9.util.stream.PipelineHelper
    final <P_IN> Spliterator<E_OUT> wrapSpliterator(Spliterator<P_IN> spliterator) {
        return this.depth == 0 ? spliterator : wrap(this, AbstractPipeline$$Lambda$2.lambdaFactory$(spliterator), isParallel());
    }

    @Override // java9.util.stream.PipelineHelper
    final <P_IN> Node<E_OUT> evaluate(Spliterator<P_IN> spliterator, boolean z, IntFunction<E_OUT[]> intFunction) {
        if (isParallel()) {
            return evaluateToNode(this, spliterator, z, intFunction);
        }
        return ((Node.Builder) wrapAndCopyInto(makeNodeBuilder(exactOutputSizeIfKnown(spliterator), intFunction), (Spliterator) spliterator)).build2();
    }

    <P_IN> Node<E_OUT> opEvaluateParallel(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<E_OUT[]> intFunction) {
        throw new UnsupportedOperationException("Parallel evaluation is not supported");
    }

    static /* synthetic */ Object[] lambda$opEvaluateParallelLazy$2(int i) {
        return new Object[i];
    }

    <P_IN> Spliterator<E_OUT> opEvaluateParallelLazy(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator) {
        return opEvaluateParallel(pipelineHelper, spliterator, AbstractPipeline$$Lambda$3.instance).spliterator();
    }
}
