package java9.util.stream;

import java9.util.Optional;
import java9.util.OptionalDouble;
import java9.util.OptionalInt;
import java9.util.OptionalLong;
import java9.util.Spliterator;
import java9.util.concurrent.CountedCompleter;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.stream.Sink;

/* JADX INFO: loaded from: classes3.dex */
final class FindOps {
    private static final TerminalOp<Double, OptionalDouble> ANY_DOUBLE;
    private static final TerminalOp<Integer, OptionalInt> ANY_INT;
    private static final TerminalOp<Long, OptionalLong> ANY_LONG;
    private static final TerminalOp ANY_REF;
    private static final Predicate<OptionalDouble> DOUBLE_IS_PRESENT;
    private static final Supplier<TerminalSink<Double, OptionalDouble>> DOUBLE_SINK_SUPP;
    private static final TerminalOp<Double, OptionalDouble> FIRST_DOUBLE;
    private static final TerminalOp<Integer, OptionalInt> FIRST_INT;
    private static final TerminalOp<Long, OptionalLong> FIRST_LONG;
    private static final TerminalOp FIRST_REF;
    private static final Predicate<OptionalInt> INT_IS_PRESENT;
    private static final Supplier<TerminalSink<Integer, OptionalInt>> INT_SINK_SUPP;
    private static final Predicate<OptionalLong> LONG_IS_PRESENT;
    private static final Supplier<TerminalSink<Long, OptionalLong>> LONG_SINK_SUPP;
    private static final Predicate<Optional<Object>> REF_IS_PRESENT;
    private static final Supplier<TerminalSink<Object, Optional<Object>>> REF_SINK_SUPP;

    private FindOps() {
    }

    static {
        Predicate<Optional<Object>> predicate = FindOps$$Lambda$1.instance;
        REF_IS_PRESENT = predicate;
        Predicate<OptionalInt> predicate2 = FindOps$$Lambda$2.instance;
        INT_IS_PRESENT = predicate2;
        Predicate<OptionalLong> predicate3 = FindOps$$Lambda$3.instance;
        LONG_IS_PRESENT = predicate3;
        Predicate<OptionalDouble> predicate4 = FindOps$$Lambda$4.instance;
        DOUBLE_IS_PRESENT = predicate4;
        Supplier<TerminalSink<Object, Optional<Object>>> supplier = FindOps$$Lambda$5.instance;
        REF_SINK_SUPP = supplier;
        Supplier<TerminalSink<Integer, OptionalInt>> supplier2 = FindOps$$Lambda$6.instance;
        INT_SINK_SUPP = supplier2;
        Supplier<TerminalSink<Long, OptionalLong>> supplier3 = FindOps$$Lambda$7.instance;
        LONG_SINK_SUPP = supplier3;
        Supplier<TerminalSink<Double, OptionalDouble>> supplier4 = FindOps$$Lambda$8.instance;
        DOUBLE_SINK_SUPP = supplier4;
        FIRST_REF = new FindOp(true, StreamShape.REFERENCE, Optional.empty(), predicate, supplier);
        ANY_REF = new FindOp(false, StreamShape.REFERENCE, Optional.empty(), predicate, supplier);
        FIRST_INT = new FindOp(true, StreamShape.INT_VALUE, OptionalInt.empty(), predicate2, supplier2);
        ANY_INT = new FindOp(false, StreamShape.INT_VALUE, OptionalInt.empty(), predicate2, supplier2);
        FIRST_LONG = new FindOp(true, StreamShape.LONG_VALUE, OptionalLong.empty(), predicate3, supplier3);
        ANY_LONG = new FindOp(false, StreamShape.LONG_VALUE, OptionalLong.empty(), predicate3, supplier3);
        FIRST_DOUBLE = new FindOp(true, StreamShape.DOUBLE_VALUE, OptionalDouble.empty(), predicate4, supplier4);
        ANY_DOUBLE = new FindOp(false, StreamShape.DOUBLE_VALUE, OptionalDouble.empty(), predicate4, supplier4);
    }

    public static <T> TerminalOp<T, Optional<T>> makeRef(boolean z) {
        return z ? FIRST_REF : ANY_REF;
    }

    public static TerminalOp<Integer, OptionalInt> makeInt(boolean z) {
        return z ? FIRST_INT : ANY_INT;
    }

    public static TerminalOp<Long, OptionalLong> makeLong(boolean z) {
        return z ? FIRST_LONG : ANY_LONG;
    }

    public static TerminalOp<Double, OptionalDouble> makeDouble(boolean z) {
        return z ? FIRST_DOUBLE : ANY_DOUBLE;
    }

    private static final class FindOp<T, O> implements TerminalOp<T, O> {
        final O emptyValue;
        final int opFlags;
        final Predicate<O> presentPredicate;
        private final StreamShape shape;
        final Supplier<TerminalSink<T, O>> sinkSupplier;

        FindOp(boolean z, StreamShape streamShape, O o, Predicate<O> predicate, Supplier<TerminalSink<T, O>> supplier) {
            this.opFlags = (z ? 0 : StreamOpFlag.NOT_ORDERED) | StreamOpFlag.IS_SHORT_CIRCUIT;
            this.shape = streamShape;
            this.emptyValue = o;
            this.presentPredicate = predicate;
            this.sinkSupplier = supplier;
        }

        @Override // java9.util.stream.TerminalOp
        public int getOpFlags() {
            return this.opFlags;
        }

        @Override // java9.util.stream.TerminalOp
        public StreamShape inputShape() {
            return this.shape;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.TerminalOp
        public <S> O evaluateSequential(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator) {
            O o = (O) ((TerminalSink) pipelineHelper.wrapAndCopyInto(this.sinkSupplier.get(), (Spliterator) spliterator)).get();
            return o != null ? o : this.emptyValue;
        }

        @Override // java9.util.stream.TerminalOp
        public <P_IN> O evaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
            return new FindTask(this, StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags()), pipelineHelper, spliterator).invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class FindSink<T, O> implements TerminalSink<T, O> {
        boolean hasValue;
        T value;

        @Override // java9.util.stream.Sink
        public void begin(long j) {
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        FindSink() {
        }

        @Override // java9.util.function.Consumer
        public void accept(T t) {
            if (this.hasValue) {
                return;
            }
            this.hasValue = true;
            this.value = t;
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return this.hasValue;
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

        static final class OfRef<T> extends FindSink<T, Optional<T>> {
            OfRef() {
            }

            @Override // java9.util.function.Supplier
            public Optional<T> get() {
                if (this.hasValue) {
                    return Optional.of(this.value);
                }
                return null;
            }
        }

        static final class OfInt extends FindSink<Integer, OptionalInt> implements Sink.OfInt {
            OfInt() {
            }

            @Override // java9.util.stream.Sink.OfInt
            public /* bridge */ /* synthetic */ void accept(Integer num) {
                super.accept(num);
            }

            @Override // java9.util.stream.FindOps.FindSink, java9.util.stream.Sink
            public void accept(int i) {
                accept(Integer.valueOf(i));
            }

            @Override // java9.util.function.Supplier
            public OptionalInt get() {
                if (this.hasValue) {
                    return OptionalInt.of(((Integer) this.value).intValue());
                }
                return null;
            }
        }

        static final class OfLong extends FindSink<Long, OptionalLong> implements Sink.OfLong {
            OfLong() {
            }

            @Override // java9.util.stream.Sink.OfLong
            public /* bridge */ /* synthetic */ void accept(Long l) {
                super.accept(l);
            }

            @Override // java9.util.stream.FindOps.FindSink, java9.util.stream.Sink
            public void accept(long j) {
                accept(Long.valueOf(j));
            }

            @Override // java9.util.function.Supplier
            public OptionalLong get() {
                if (this.hasValue) {
                    return OptionalLong.of(((Long) this.value).longValue());
                }
                return null;
            }
        }

        static final class OfDouble extends FindSink<Double, OptionalDouble> implements Sink.OfDouble {
            OfDouble() {
            }

            @Override // java9.util.stream.Sink.OfDouble
            public /* bridge */ /* synthetic */ void accept(Double d) {
                super.accept(d);
            }

            @Override // java9.util.stream.FindOps.FindSink, java9.util.stream.Sink
            public void accept(double d) {
                accept(Double.valueOf(d));
            }

            @Override // java9.util.function.Supplier
            public OptionalDouble get() {
                if (this.hasValue) {
                    return OptionalDouble.of(((Double) this.value).doubleValue());
                }
                return null;
            }
        }
    }

    private static final class FindTask<P_IN, P_OUT, O> extends AbstractShortCircuitTask<P_IN, P_OUT, O, FindTask<P_IN, P_OUT, O>> {
        private final boolean mustFindFirst;
        private final FindOp<P_OUT, O> op;

        FindTask(FindOp<P_OUT, O> findOp, boolean z, PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator) {
            super(pipelineHelper, spliterator);
            this.mustFindFirst = z;
            this.op = findOp;
        }

        FindTask(FindTask<P_IN, P_OUT, O> findTask, Spliterator<P_IN> spliterator) {
            super(findTask, spliterator);
            this.mustFindFirst = findTask.mustFindFirst;
            this.op = findTask.op;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public FindTask<P_IN, P_OUT, O> makeChild(Spliterator<P_IN> spliterator) {
            return new FindTask<>(this, spliterator);
        }

        @Override // java9.util.stream.AbstractShortCircuitTask
        protected O getEmptyResult() {
            return this.op.emptyValue;
        }

        private void foundResult(O o) {
            if (isLeftmostNode()) {
                shortCircuit(o);
            } else {
                cancelLaterNodes();
            }
        }

        @Override // java9.util.stream.AbstractTask
        protected O doLeaf() {
            O o = (O) ((TerminalSink) this.helper.wrapAndCopyInto(this.op.sinkSupplier.get(), (Spliterator) this.spliterator)).get();
            if (!this.mustFindFirst) {
                if (o != null) {
                    shortCircuit(o);
                }
                return null;
            }
            if (o == null) {
                return null;
            }
            foundResult(o);
            return o;
        }

        @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
        public void onCompletion(CountedCompleter<?> countedCompleter) {
            if (this.mustFindFirst) {
                FindTask findTask = (FindTask) this.leftChild;
                FindTask findTask2 = null;
                while (true) {
                    if (findTask != findTask2) {
                        O localResult = findTask.getLocalResult();
                        if (localResult == null || !this.op.presentPredicate.test(localResult)) {
                            findTask2 = findTask;
                            findTask = (FindTask) this.rightChild;
                        } else {
                            setLocalResult(localResult);
                            foundResult(localResult);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            super.onCompletion(countedCompleter);
        }
    }
}
