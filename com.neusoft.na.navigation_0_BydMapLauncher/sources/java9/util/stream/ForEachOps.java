package java9.util.stream;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.concurrent.CountedCompleter;
import java9.util.concurrent.ForkJoinPool;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.LongConsumer;
import java9.util.stream.Node;
import java9.util.stream.Sink;
import java9.util.stream.SinkDefaults;

/* JADX INFO: loaded from: classes3.dex */
final class ForEachOps {
    private ForEachOps() {
    }

    public static <T> TerminalOp<T, Void> makeRef(Consumer<? super T> consumer, boolean z) {
        Objects.requireNonNull(consumer);
        return new ForEachOp.OfRef(consumer, z);
    }

    public static TerminalOp<Integer, Void> makeInt(IntConsumer intConsumer, boolean z) {
        Objects.requireNonNull(intConsumer);
        return new ForEachOp.OfInt(intConsumer, z);
    }

    public static TerminalOp<Long, Void> makeLong(LongConsumer longConsumer, boolean z) {
        Objects.requireNonNull(longConsumer);
        return new ForEachOp.OfLong(longConsumer, z);
    }

    public static TerminalOp<Double, Void> makeDouble(DoubleConsumer doubleConsumer, boolean z) {
        Objects.requireNonNull(doubleConsumer);
        return new ForEachOp.OfDouble(doubleConsumer, z);
    }

    static abstract class ForEachOp<T> implements TerminalOp<T, Void>, TerminalSink<T, Void> {
        private final boolean ordered;

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

        @Override // java9.util.function.Supplier
        public Void get() {
            return null;
        }

        protected ForEachOp(boolean z) {
            this.ordered = z;
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

        @Override // java9.util.stream.TerminalOp
        public StreamShape inputShape() {
            return StreamShape.REFERENCE;
        }

        @Override // java9.util.stream.TerminalOp
        public int getOpFlags() {
            if (this.ordered) {
                return 0;
            }
            return StreamOpFlag.NOT_ORDERED;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.TerminalOp
        public <S> Void evaluateSequential(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator) {
            return ((ForEachOp) pipelineHelper.wrapAndCopyInto(this, (Spliterator) spliterator)).get();
        }

        @Override // java9.util.stream.TerminalOp
        public <S> Void evaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator) {
            if (this.ordered) {
                new ForEachOrderedTask(pipelineHelper, spliterator, this).invoke();
                return null;
            }
            new ForEachTask(pipelineHelper, spliterator, pipelineHelper.wrapSink((Sink<T>) this)).invoke();
            return null;
        }

        static final class OfRef<T> extends ForEachOp<T> {
            final Consumer<? super T> consumer;

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            OfRef(Consumer<? super T> consumer, boolean z) {
                super(z);
                this.consumer = consumer;
            }

            @Override // java9.util.function.Consumer
            public void accept(T t) {
                this.consumer.accept(t);
            }
        }

        static final class OfInt extends ForEachOp<Integer> implements Sink.OfInt {
            final IntConsumer consumer;

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            OfInt(IntConsumer intConsumer, boolean z) {
                super(z);
                this.consumer = intConsumer;
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public StreamShape inputShape() {
                return StreamShape.INT_VALUE;
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.Sink
            public void accept(int i) {
                this.consumer.accept(i);
            }

            @Override // java9.util.function.Consumer
            public void accept(Integer num) {
                SinkDefaults.OfInt.accept(this, num);
            }
        }

        static final class OfLong extends ForEachOp<Long> implements Sink.OfLong {
            final LongConsumer consumer;

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            OfLong(LongConsumer longConsumer, boolean z) {
                super(z);
                this.consumer = longConsumer;
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public StreamShape inputShape() {
                return StreamShape.LONG_VALUE;
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.Sink
            public void accept(long j) {
                this.consumer.accept(j);
            }

            @Override // java9.util.function.Consumer
            public void accept(Long l) {
                SinkDefaults.OfLong.accept(this, l);
            }
        }

        static final class OfDouble extends ForEachOp<Double> implements Sink.OfDouble {
            final DoubleConsumer consumer;

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateParallel(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Void evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return super.evaluateSequential(pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            OfDouble(DoubleConsumer doubleConsumer, boolean z) {
                super(z);
                this.consumer = doubleConsumer;
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.TerminalOp
            public StreamShape inputShape() {
                return StreamShape.DOUBLE_VALUE;
            }

            @Override // java9.util.stream.ForEachOps.ForEachOp, java9.util.stream.Sink
            public void accept(double d) {
                this.consumer.accept(d);
            }

            @Override // java9.util.function.Consumer
            public void accept(Double d) {
                SinkDefaults.OfDouble.accept(this, d);
            }
        }
    }

    static final class ForEachTask<S, T> extends CountedCompleter<Void> {
        private final PipelineHelper<T> helper;
        private final Sink<S> sink;
        private Spliterator<S> spliterator;
        private long targetSize;

        ForEachTask(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator, Sink<S> sink) {
            super(null);
            this.sink = sink;
            this.helper = pipelineHelper;
            this.spliterator = spliterator;
            this.targetSize = 0L;
        }

        ForEachTask(ForEachTask<S, T> forEachTask, Spliterator<S> spliterator) {
            super(forEachTask);
            this.spliterator = spliterator;
            this.sink = forEachTask.sink;
            this.targetSize = forEachTask.targetSize;
            this.helper = forEachTask.helper;
        }

        @Override // java9.util.concurrent.CountedCompleter
        public void compute() {
            Spliterator<S> spliteratorTrySplit;
            Spliterator<S> spliterator = this.spliterator;
            long jEstimateSize = spliterator.estimateSize();
            long jSuggestTargetSize = this.targetSize;
            if (jSuggestTargetSize == 0) {
                jSuggestTargetSize = AbstractTask.suggestTargetSize(jEstimateSize);
                this.targetSize = jSuggestTargetSize;
            }
            boolean zIsKnown = StreamOpFlag.SHORT_CIRCUIT.isKnown(this.helper.getStreamAndOpFlags());
            Sink<S> sink = this.sink;
            boolean z = false;
            ForEachTask<S, T> forEachTask = this;
            while (true) {
                if (zIsKnown && sink.cancellationRequested()) {
                    break;
                }
                if (jEstimateSize <= jSuggestTargetSize || (spliteratorTrySplit = spliterator.trySplit()) == null) {
                    break;
                }
                ForEachTask<S, T> forEachTask2 = new ForEachTask<>(forEachTask, spliteratorTrySplit);
                forEachTask.addToPendingCount(1);
                if (z) {
                    spliterator = spliteratorTrySplit;
                } else {
                    ForEachTask<S, T> forEachTask3 = forEachTask;
                    forEachTask = forEachTask2;
                    forEachTask2 = forEachTask3;
                }
                z = !z;
                forEachTask.fork();
                forEachTask = forEachTask2;
                jEstimateSize = spliterator.estimateSize();
            }
            forEachTask.helper.copyInto(sink, spliterator);
            forEachTask.spliterator = null;
            forEachTask.propagateCompletion();
        }
    }

    static final class ForEachOrderedTask<S, T> extends CountedCompleter<Void> {
        private final Sink<T> action;
        private final ConcurrentMap<ForEachOrderedTask<S, T>, ForEachOrderedTask<S, T>> completionMap;
        private final PipelineHelper<T> helper;
        private final ForEachOrderedTask<S, T> leftPredecessor;
        private Node<T> node;
        private Spliterator<S> spliterator;
        private final long targetSize;

        protected ForEachOrderedTask(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator, Sink<T> sink) {
            super(null);
            this.helper = pipelineHelper;
            this.spliterator = spliterator;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator.estimateSize());
            this.completionMap = new ConcurrentHashMap(Math.max(16, AbstractTask.getLeafTarget() << 1), 0.75f, ForkJoinPool.getCommonPoolParallelism() + 1);
            this.action = sink;
            this.leftPredecessor = null;
        }

        ForEachOrderedTask(ForEachOrderedTask<S, T> forEachOrderedTask, Spliterator<S> spliterator, ForEachOrderedTask<S, T> forEachOrderedTask2) {
            super(forEachOrderedTask);
            this.helper = forEachOrderedTask.helper;
            this.spliterator = spliterator;
            this.targetSize = forEachOrderedTask.targetSize;
            this.completionMap = forEachOrderedTask.completionMap;
            this.action = forEachOrderedTask.action;
            this.leftPredecessor = forEachOrderedTask2;
        }

        @Override // java9.util.concurrent.CountedCompleter
        public final void compute() {
            doCompute(this);
        }

        private static <S, T> void doCompute(ForEachOrderedTask<S, T> forEachOrderedTask) {
            Spliterator<S> spliteratorTrySplit;
            Spliterator<S> spliterator = ((ForEachOrderedTask) forEachOrderedTask).spliterator;
            long j = ((ForEachOrderedTask) forEachOrderedTask).targetSize;
            boolean z = false;
            while (spliterator.estimateSize() > j && (spliteratorTrySplit = spliterator.trySplit()) != null) {
                ForEachOrderedTask<S, T> forEachOrderedTask2 = new ForEachOrderedTask<>(forEachOrderedTask, spliteratorTrySplit, ((ForEachOrderedTask) forEachOrderedTask).leftPredecessor);
                ForEachOrderedTask<S, T> forEachOrderedTask3 = new ForEachOrderedTask<>(forEachOrderedTask, spliterator, forEachOrderedTask2);
                forEachOrderedTask.addToPendingCount(1);
                forEachOrderedTask3.addToPendingCount(1);
                ((ForEachOrderedTask) forEachOrderedTask).completionMap.put(forEachOrderedTask2, forEachOrderedTask3);
                if (((ForEachOrderedTask) forEachOrderedTask).leftPredecessor != null) {
                    forEachOrderedTask2.addToPendingCount(1);
                    if (((ForEachOrderedTask) forEachOrderedTask).completionMap.replace(((ForEachOrderedTask) forEachOrderedTask).leftPredecessor, forEachOrderedTask, forEachOrderedTask2)) {
                        forEachOrderedTask.addToPendingCount(-1);
                    } else {
                        forEachOrderedTask2.addToPendingCount(-1);
                    }
                }
                if (z) {
                    spliterator = spliteratorTrySplit;
                    forEachOrderedTask = forEachOrderedTask2;
                    forEachOrderedTask2 = forEachOrderedTask3;
                } else {
                    forEachOrderedTask = forEachOrderedTask3;
                }
                z = !z;
                forEachOrderedTask2.fork();
            }
            if (forEachOrderedTask.getPendingCount() > 0) {
                IntFunction<T[]> intFunction = ForEachOps$ForEachOrderedTask$$Lambda$1.instance;
                PipelineHelper<T> pipelineHelper = ((ForEachOrderedTask) forEachOrderedTask).helper;
                ((ForEachOrderedTask) forEachOrderedTask).node = ((Node.Builder) ((ForEachOrderedTask) forEachOrderedTask).helper.wrapAndCopyInto(pipelineHelper.makeNodeBuilder(pipelineHelper.exactOutputSizeIfKnown(spliterator), intFunction), (Spliterator) spliterator)).build2();
                ((ForEachOrderedTask) forEachOrderedTask).spliterator = null;
            }
            forEachOrderedTask.tryComplete();
        }

        static /* synthetic */ Object[] lambda$doCompute$0(int i) {
            return new Object[i];
        }

        @Override // java9.util.concurrent.CountedCompleter
        public void onCompletion(CountedCompleter<?> countedCompleter) {
            Node<T> node = this.node;
            if (node != null) {
                node.forEach(this.action);
                this.node = null;
            } else {
                Spliterator<S> spliterator = this.spliterator;
                if (spliterator != null) {
                    this.helper.wrapAndCopyInto(this.action, (Spliterator) spliterator);
                    this.spliterator = null;
                }
            }
            ForEachOrderedTask<S, T> forEachOrderedTaskRemove = this.completionMap.remove(this);
            if (forEachOrderedTaskRemove != null) {
                forEachOrderedTaskRemove.tryComplete();
            }
        }
    }
}
