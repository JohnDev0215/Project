package java9.util.stream;

import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.function.DoublePredicate;
import java9.util.function.IntPredicate;
import java9.util.function.LongPredicate;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.stream.Sink;
import java9.util.stream.SinkDefaults;

/* JADX INFO: loaded from: classes3.dex */
final class MatchOps {
    private MatchOps() {
    }

    enum MatchKind {
        ANY(true, true),
        ALL(false, false),
        NONE(true, false);

        private final boolean shortCircuitResult;
        private final boolean stopOnPredicateMatches;

        MatchKind(boolean z, boolean z2) {
            this.stopOnPredicateMatches = z;
            this.shortCircuitResult = z2;
        }
    }

    public static <T> TerminalOp<T, Boolean> makeRef(Predicate<? super T> predicate, MatchKind matchKind) {
        Objects.requireNonNull(predicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.REFERENCE, matchKind, MatchOps$$Lambda$1.lambdaFactory$(matchKind, predicate));
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeRef$0(MatchKind matchKind, Predicate predicate) {
        return new BooleanTerminalSink<T>(predicate) { // from class: java9.util.stream.MatchOps.1MatchSink
            final /* synthetic */ Predicate val$predicate;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.val$matchKind);
                this.val$predicate = predicate;
            }

            @Override // java9.util.function.Consumer
            public void accept(T t) {
                if (this.stop || this.val$predicate.test(t) != this.val$matchKind.stopOnPredicateMatches) {
                    return;
                }
                this.stop = true;
                this.value = this.val$matchKind.shortCircuitResult;
            }
        };
    }

    public static TerminalOp<Integer, Boolean> makeInt(IntPredicate intPredicate, MatchKind matchKind) {
        Objects.requireNonNull(intPredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.INT_VALUE, matchKind, MatchOps$$Lambda$2.lambdaFactory$(matchKind, intPredicate));
    }

    /* JADX INFO: renamed from: java9.util.stream.MatchOps$2MatchSink, reason: invalid class name */
    class C2MatchSink extends BooleanTerminalSink<Integer> implements Sink.OfInt {
        final /* synthetic */ MatchKind val$matchKind;
        final /* synthetic */ IntPredicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C2MatchSink(MatchKind matchKind, IntPredicate intPredicate) {
            super(matchKind);
            this.val$matchKind = matchKind;
            this.val$predicate = intPredicate;
        }

        @Override // java9.util.stream.MatchOps.BooleanTerminalSink, java9.util.stream.Sink
        public void accept(int i) {
            if (this.stop || this.val$predicate.test(i) != this.val$matchKind.stopOnPredicateMatches) {
                return;
            }
            this.stop = true;
            this.value = this.val$matchKind.shortCircuitResult;
        }

        @Override // java9.util.function.Consumer
        public void accept(Integer num) {
            SinkDefaults.OfInt.accept(this, num);
        }
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeInt$1(MatchKind matchKind, IntPredicate intPredicate) {
        return new C2MatchSink(matchKind, intPredicate);
    }

    public static TerminalOp<Long, Boolean> makeLong(LongPredicate longPredicate, MatchKind matchKind) {
        Objects.requireNonNull(longPredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.LONG_VALUE, matchKind, MatchOps$$Lambda$3.lambdaFactory$(matchKind, longPredicate));
    }

    /* JADX INFO: renamed from: java9.util.stream.MatchOps$3MatchSink, reason: invalid class name */
    class C3MatchSink extends BooleanTerminalSink<Long> implements Sink.OfLong {
        final /* synthetic */ MatchKind val$matchKind;
        final /* synthetic */ LongPredicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C3MatchSink(MatchKind matchKind, LongPredicate longPredicate) {
            super(matchKind);
            this.val$matchKind = matchKind;
            this.val$predicate = longPredicate;
        }

        @Override // java9.util.stream.MatchOps.BooleanTerminalSink, java9.util.stream.Sink
        public void accept(long j) {
            if (this.stop || this.val$predicate.test(j) != this.val$matchKind.stopOnPredicateMatches) {
                return;
            }
            this.stop = true;
            this.value = this.val$matchKind.shortCircuitResult;
        }

        @Override // java9.util.function.Consumer
        public void accept(Long l) {
            SinkDefaults.OfLong.accept(this, l);
        }
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeLong$2(MatchKind matchKind, LongPredicate longPredicate) {
        return new C3MatchSink(matchKind, longPredicate);
    }

    public static TerminalOp<Double, Boolean> makeDouble(DoublePredicate doublePredicate, MatchKind matchKind) {
        Objects.requireNonNull(doublePredicate);
        Objects.requireNonNull(matchKind);
        return new MatchOp(StreamShape.DOUBLE_VALUE, matchKind, MatchOps$$Lambda$4.lambdaFactory$(matchKind, doublePredicate));
    }

    /* JADX INFO: renamed from: java9.util.stream.MatchOps$4MatchSink, reason: invalid class name */
    class C4MatchSink extends BooleanTerminalSink<Double> implements Sink.OfDouble {
        final /* synthetic */ MatchKind val$matchKind;
        final /* synthetic */ DoublePredicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C4MatchSink(MatchKind matchKind, DoublePredicate doublePredicate) {
            super(matchKind);
            this.val$matchKind = matchKind;
            this.val$predicate = doublePredicate;
        }

        @Override // java9.util.stream.MatchOps.BooleanTerminalSink, java9.util.stream.Sink
        public void accept(double d) {
            if (this.stop || this.val$predicate.test(d) != this.val$matchKind.stopOnPredicateMatches) {
                return;
            }
            this.stop = true;
            this.value = this.val$matchKind.shortCircuitResult;
        }

        @Override // java9.util.function.Consumer
        public void accept(Double d) {
            SinkDefaults.OfDouble.accept(this, d);
        }
    }

    static /* synthetic */ BooleanTerminalSink lambda$makeDouble$3(MatchKind matchKind, DoublePredicate doublePredicate) {
        return new C4MatchSink(matchKind, doublePredicate);
    }

    private static final class MatchOp<T> implements TerminalOp<T, Boolean> {
        private final StreamShape inputShape;
        final MatchKind matchKind;
        final Supplier<BooleanTerminalSink<T>> sinkSupplier;

        MatchOp(StreamShape streamShape, MatchKind matchKind, Supplier<BooleanTerminalSink<T>> supplier) {
            this.inputShape = streamShape;
            this.matchKind = matchKind;
            this.sinkSupplier = supplier;
        }

        @Override // java9.util.stream.TerminalOp
        public int getOpFlags() {
            return StreamOpFlag.IS_SHORT_CIRCUIT | StreamOpFlag.NOT_ORDERED;
        }

        @Override // java9.util.stream.TerminalOp
        public StreamShape inputShape() {
            return this.inputShape;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.TerminalOp
        public <S> Boolean evaluateSequential(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator) {
            return Boolean.valueOf(((BooleanTerminalSink) pipelineHelper.wrapAndCopyInto(this.sinkSupplier.get(), (Spliterator) spliterator)).getAndClearState());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java9.util.stream.TerminalOp
        public <S> Boolean evaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<S> spliterator) {
            return new MatchTask(this, pipelineHelper, spliterator).invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class BooleanTerminalSink<T> implements Sink<T> {
        boolean stop;
        boolean value;

        @Override // java9.util.stream.Sink
        public void begin(long j) {
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        BooleanTerminalSink(MatchKind matchKind) {
            this.value = !matchKind.shortCircuitResult;
        }

        public boolean getAndClearState() {
            return this.value;
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return this.stop;
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
    }

    private static final class MatchTask<P_IN, P_OUT> extends AbstractShortCircuitTask<P_IN, P_OUT, Boolean, MatchTask<P_IN, P_OUT>> {
        private final MatchOp<P_OUT> op;

        MatchTask(MatchOp<P_OUT> matchOp, PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator) {
            super(pipelineHelper, spliterator);
            this.op = matchOp;
        }

        MatchTask(MatchTask<P_IN, P_OUT> matchTask, Spliterator<P_IN> spliterator) {
            super(matchTask, spliterator);
            this.op = matchTask.op;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public MatchTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new MatchTask<>(this, spliterator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public Boolean doLeaf() {
            boolean andClearState = ((BooleanTerminalSink) this.helper.wrapAndCopyInto(this.op.sinkSupplier.get(), (Spliterator) this.spliterator)).getAndClearState();
            if (andClearState != this.op.matchKind.shortCircuitResult) {
                return null;
            }
            shortCircuit(Boolean.valueOf(andClearState));
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java9.util.stream.AbstractShortCircuitTask
        public Boolean getEmptyResult() {
            return Boolean.valueOf(!this.op.matchKind.shortCircuitResult);
        }
    }
}
