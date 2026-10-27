package java9.util.stream;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.concurrent.CountedCompleter;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.DoublePredicate;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.IntPredicate;
import java9.util.function.LongConsumer;
import java9.util.function.LongPredicate;
import java9.util.function.Predicate;
import java9.util.stream.DoublePipeline;
import java9.util.stream.IntPipeline;
import java9.util.stream.LongPipeline;
import java9.util.stream.Node;
import java9.util.stream.ReferencePipeline;
import java9.util.stream.Sink;

/* JADX INFO: loaded from: classes3.dex */
final class WhileOps {
    static final int TAKE_FLAGS = StreamOpFlag.NOT_SIZED | StreamOpFlag.IS_SHORT_CIRCUIT;
    static final int DROP_FLAGS = StreamOpFlag.NOT_SIZED;
    static final IntFunction<Integer[]> INT_ARR_GEN = WhileOps$$Lambda$1.instance;
    static final IntFunction<Long[]> LONG_ARR_GEN = WhileOps$$Lambda$2.instance;
    static final IntFunction<Double[]> DOUBLE_ARR_GEN = WhileOps$$Lambda$3.instance;

    interface DropWhileOp<T> {
        DropWhileSink<T> opWrapSink(Sink<T> sink, boolean z);
    }

    interface DropWhileSink<T> extends Sink<T> {
        long getDropCount();
    }

    WhileOps() {
    }

    static /* synthetic */ Integer[] lambda$static$0(int i) {
        return new Integer[i];
    }

    static /* synthetic */ Long[] lambda$static$1(int i) {
        return new Long[i];
    }

    static /* synthetic */ Double[] lambda$static$2(int i) {
        return new Double[i];
    }

    static <T> Stream<T> makeTakeWhileRef(AbstractPipeline<?, T, ?> abstractPipeline, final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new ReferencePipeline.StatefulOp<T, T>(abstractPipeline, StreamShape.REFERENCE, TAKE_FLAGS) { // from class: java9.util.stream.WhileOps.1
            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<T> opEvaluateParallelLazy(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return opEvaluateParallel(pipelineHelper, spliterator, Nodes.castingArray()).spliterator();
                }
                return new UnorderedWhileSpliterator.OfRef.Taking(pipelineHelper.wrapSpliterator(spliterator), false, predicate);
            }

            @Override // java9.util.stream.ReferencePipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<T[]> intFunction) {
                return (Node) new TakeWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<T> opWrapSink(int i, Sink<T> sink) {
                return new Sink.ChainedReference<T, T>(sink) { // from class: java9.util.stream.WhileOps.1.1
                    boolean take = true;

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.function.Consumer
                    public void accept(T t) {
                        if (this.take) {
                            boolean zTest = predicate.test(t);
                            this.take = zTest;
                            if (zTest) {
                                this.downstream.accept((Object) t);
                            }
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return !this.take || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    static IntStream makeTakeWhileInt(AbstractPipeline<?, Integer, ?> abstractPipeline, final IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new IntPipeline.StatefulOp<Integer>(abstractPipeline, StreamShape.INT_VALUE, TAKE_FLAGS) { // from class: java9.util.stream.WhileOps.2
            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<Integer> opEvaluateParallelLazy(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return opEvaluateParallel(pipelineHelper, spliterator, WhileOps.INT_ARR_GEN).spliterator();
                }
                return new UnorderedWhileSpliterator.OfInt.Taking((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), false, intPredicate);
            }

            @Override // java9.util.stream.IntPipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> intFunction) {
                return (Node) new TakeWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) { // from class: java9.util.stream.WhileOps.2.1
                    boolean take = true;

                    @Override // java9.util.stream.Sink.ChainedInt, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java9.util.stream.Sink.OfInt, java9.util.stream.Sink
                    public void accept(int i2) {
                        if (this.take) {
                            boolean zTest = intPredicate.test(i2);
                            this.take = zTest;
                            if (zTest) {
                                this.downstream.accept(i2);
                            }
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedInt, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return !this.take || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    static LongStream makeTakeWhileLong(AbstractPipeline<?, Long, ?> abstractPipeline, final LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new LongPipeline.StatefulOp<Long>(abstractPipeline, StreamShape.LONG_VALUE, TAKE_FLAGS) { // from class: java9.util.stream.WhileOps.3
            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<Long> opEvaluateParallelLazy(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return opEvaluateParallel(pipelineHelper, spliterator, WhileOps.LONG_ARR_GEN).spliterator();
                }
                return new UnorderedWhileSpliterator.OfLong.Taking((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), false, longPredicate);
            }

            @Override // java9.util.stream.LongPipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Long[]> intFunction) {
                return (Node) new TakeWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) { // from class: java9.util.stream.WhileOps.3.1
                    boolean take = true;

                    @Override // java9.util.stream.Sink.ChainedLong, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java9.util.stream.Sink.OfLong, java9.util.stream.Sink
                    public void accept(long j) {
                        if (this.take) {
                            boolean zTest = longPredicate.test(j);
                            this.take = zTest;
                            if (zTest) {
                                this.downstream.accept(j);
                            }
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedLong, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return !this.take || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    static DoubleStream makeTakeWhileDouble(AbstractPipeline<?, Double, ?> abstractPipeline, final DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return new DoublePipeline.StatefulOp<Double>(abstractPipeline, StreamShape.DOUBLE_VALUE, TAKE_FLAGS) { // from class: java9.util.stream.WhileOps.4
            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<Double> opEvaluateParallelLazy(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return opEvaluateParallel(pipelineHelper, spliterator, WhileOps.DOUBLE_ARR_GEN).spliterator();
                }
                return new UnorderedWhileSpliterator.OfDouble.Taking((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), false, doublePredicate);
            }

            @Override // java9.util.stream.DoublePipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Double[]> intFunction) {
                return (Node) new TakeWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) { // from class: java9.util.stream.WhileOps.4.1
                    boolean take = true;

                    @Override // java9.util.stream.Sink.ChainedDouble, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference fix 'apply assigned field type' failed
                    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                     */
                    @Override // java9.util.stream.Sink.OfDouble, java9.util.stream.Sink
                    public void accept(double d) {
                        if (this.take) {
                            boolean zTest = doublePredicate.test(d);
                            this.take = zTest;
                            if (zTest) {
                                this.downstream.accept(d);
                            }
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedDouble, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return !this.take || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    static <T> Stream<T> makeDropWhileRef(AbstractPipeline<?, T, ?> abstractPipeline, Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return new C1Op(abstractPipeline, StreamShape.REFERENCE, DROP_FLAGS, predicate);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: java9.util.stream.WhileOps$1Op, reason: invalid class name */
    class C1Op<T> extends ReferencePipeline.StatefulOp<T, T> implements DropWhileOp<T> {
        final /* synthetic */ Predicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C1Op(AbstractPipeline abstractPipeline, AbstractPipeline<?, T, ?> abstractPipeline2, StreamShape streamShape, int i) {
            super(abstractPipeline, abstractPipeline2, streamShape);
            this.val$predicate = i;
        }

        @Override // java9.util.stream.AbstractPipeline
        <P_IN> Spliterator<T> opEvaluateParallelLazy(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return opEvaluateParallel(pipelineHelper, spliterator, Nodes.castingArray()).spliterator();
            }
            return new UnorderedWhileSpliterator.OfRef.Dropping(pipelineHelper.wrapSpliterator(spliterator), false, this.val$predicate);
        }

        @Override // java9.util.stream.ReferencePipeline.StatefulOp, java9.util.stream.AbstractPipeline
        <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<T[]> intFunction) {
            return (Node) new DropWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
        }

        @Override // java9.util.stream.AbstractPipeline
        Sink<T> opWrapSink(int i, Sink<T> sink) {
            return opWrapSink((Sink) sink, false);
        }

        /* JADX INFO: renamed from: java9.util.stream.WhileOps$1Op$1OpSink, reason: invalid class name */
        class C1OpSink extends Sink.ChainedReference<T, T> implements DropWhileSink<T> {
            long dropCount;
            boolean take;
            final /* synthetic */ boolean val$retainAndCountDroppedElements;
            final /* synthetic */ Sink val$sink;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C1OpSink(Sink sink, boolean z) {
                super(sink);
                this.val$sink = sink;
                this.val$retainAndCountDroppedElements = z;
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // java9.util.function.Consumer
            public void accept(T t) {
                boolean z = true;
                if (!this.take) {
                    boolean z2 = !C1Op.this.val$predicate.test(t);
                    this.take = z2;
                    if (!z2) {
                        z = false;
                    }
                }
                boolean z3 = this.val$retainAndCountDroppedElements;
                if (z3 && !z) {
                    this.dropCount++;
                }
                if (z3 || z) {
                    this.downstream.accept((Object) t);
                }
            }

            @Override // java9.util.stream.WhileOps.DropWhileSink
            public long getDropCount() {
                return this.dropCount;
            }
        }

        @Override // java9.util.stream.WhileOps.DropWhileOp
        public DropWhileSink<T> opWrapSink(Sink<T> sink, boolean z) {
            return new C1OpSink(sink, z);
        }
    }

    static IntStream makeDropWhileInt(AbstractPipeline<?, Integer, ?> abstractPipeline, IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new C2Op(abstractPipeline, StreamShape.INT_VALUE, DROP_FLAGS, intPredicate);
    }

    /* JADX INFO: renamed from: java9.util.stream.WhileOps$2Op, reason: invalid class name */
    class C2Op extends IntPipeline.StatefulOp<Integer> implements DropWhileOp<Integer> {
        final /* synthetic */ IntPredicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C2Op(AbstractPipeline abstractPipeline, AbstractPipeline<?, Integer, ?> abstractPipeline2, StreamShape streamShape, int i) {
            super(abstractPipeline, abstractPipeline2, streamShape);
            this.val$predicate = i;
        }

        @Override // java9.util.stream.AbstractPipeline
        <P_IN> Spliterator<Integer> opEvaluateParallelLazy(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator) {
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return opEvaluateParallel(pipelineHelper, spliterator, WhileOps.INT_ARR_GEN).spliterator();
            }
            return new UnorderedWhileSpliterator.OfInt.Dropping((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), false, this.val$predicate);
        }

        @Override // java9.util.stream.IntPipeline.StatefulOp, java9.util.stream.AbstractPipeline
        <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> intFunction) {
            return (Node) new DropWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
        }

        @Override // java9.util.stream.AbstractPipeline
        Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
            return opWrapSink(sink, false);
        }

        /* JADX INFO: renamed from: java9.util.stream.WhileOps$2Op$1OpSink, reason: invalid class name */
        class C1OpSink extends Sink.ChainedInt<Integer> implements DropWhileSink<Integer> {
            long dropCount;
            boolean take;
            final /* synthetic */ boolean val$retainAndCountDroppedElements;
            final /* synthetic */ Sink val$sink;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C1OpSink(Sink sink, boolean z) {
                super(sink);
                this.val$sink = sink;
                this.val$retainAndCountDroppedElements = z;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java9.util.stream.Sink.OfInt, java9.util.stream.Sink
            public void accept(int i) {
                boolean z = true;
                if (!this.take) {
                    boolean z2 = !C2Op.this.val$predicate.test(i);
                    this.take = z2;
                    if (!z2) {
                        z = false;
                    }
                }
                boolean z3 = this.val$retainAndCountDroppedElements;
                if (z3 && !z) {
                    this.dropCount++;
                }
                if (z3 || z) {
                    this.downstream.accept(i);
                }
            }

            @Override // java9.util.stream.WhileOps.DropWhileSink
            public long getDropCount() {
                return this.dropCount;
            }
        }

        @Override // java9.util.stream.WhileOps.DropWhileOp
        public DropWhileSink<Integer> opWrapSink(Sink<Integer> sink, boolean z) {
            return new C1OpSink(sink, z);
        }
    }

    static LongStream makeDropWhileLong(AbstractPipeline<?, Long, ?> abstractPipeline, LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new C3Op(abstractPipeline, StreamShape.LONG_VALUE, DROP_FLAGS, longPredicate);
    }

    /* JADX INFO: renamed from: java9.util.stream.WhileOps$3Op, reason: invalid class name */
    class C3Op extends LongPipeline.StatefulOp<Long> implements DropWhileOp<Long> {
        final /* synthetic */ LongPredicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C3Op(AbstractPipeline abstractPipeline, AbstractPipeline<?, Long, ?> abstractPipeline2, StreamShape streamShape, int i) {
            super(abstractPipeline, abstractPipeline2, streamShape);
            this.val$predicate = i;
        }

        @Override // java9.util.stream.AbstractPipeline
        <P_IN> Spliterator<Long> opEvaluateParallelLazy(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator) {
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return opEvaluateParallel(pipelineHelper, spliterator, WhileOps.LONG_ARR_GEN).spliterator();
            }
            return new UnorderedWhileSpliterator.OfLong.Dropping((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), false, this.val$predicate);
        }

        @Override // java9.util.stream.LongPipeline.StatefulOp, java9.util.stream.AbstractPipeline
        <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Long[]> intFunction) {
            return (Node) new DropWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
        }

        @Override // java9.util.stream.AbstractPipeline
        Sink<Long> opWrapSink(int i, Sink<Long> sink) {
            return opWrapSink(sink, false);
        }

        /* JADX INFO: renamed from: java9.util.stream.WhileOps$3Op$1OpSink, reason: invalid class name */
        class C1OpSink extends Sink.ChainedLong<Long> implements DropWhileSink<Long> {
            long dropCount;
            boolean take;
            final /* synthetic */ boolean val$retainAndCountDroppedElements;
            final /* synthetic */ Sink val$sink;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C1OpSink(Sink sink, boolean z) {
                super(sink);
                this.val$sink = sink;
                this.val$retainAndCountDroppedElements = z;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java9.util.stream.Sink.OfLong, java9.util.stream.Sink
            public void accept(long j) {
                boolean z = true;
                if (!this.take) {
                    boolean z2 = !C3Op.this.val$predicate.test(j);
                    this.take = z2;
                    if (!z2) {
                        z = false;
                    }
                }
                boolean z3 = this.val$retainAndCountDroppedElements;
                if (z3 && !z) {
                    this.dropCount++;
                }
                if (z3 || z) {
                    this.downstream.accept(j);
                }
            }

            @Override // java9.util.stream.WhileOps.DropWhileSink
            public long getDropCount() {
                return this.dropCount;
            }
        }

        @Override // java9.util.stream.WhileOps.DropWhileOp
        public DropWhileSink<Long> opWrapSink(Sink<Long> sink, boolean z) {
            return new C1OpSink(sink, z);
        }
    }

    static DoubleStream makeDropWhileDouble(AbstractPipeline<?, Double, ?> abstractPipeline, DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return new C4Op(abstractPipeline, StreamShape.DOUBLE_VALUE, DROP_FLAGS, doublePredicate);
    }

    /* JADX INFO: renamed from: java9.util.stream.WhileOps$4Op, reason: invalid class name */
    class C4Op extends DoublePipeline.StatefulOp<Double> implements DropWhileOp<Double> {
        final /* synthetic */ DoublePredicate val$predicate;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C4Op(AbstractPipeline abstractPipeline, AbstractPipeline<?, Double, ?> abstractPipeline2, StreamShape streamShape, int i) {
            super(abstractPipeline, abstractPipeline2, streamShape);
            this.val$predicate = i;
        }

        @Override // java9.util.stream.AbstractPipeline
        <P_IN> Spliterator<Double> opEvaluateParallelLazy(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator) {
            if (StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                return opEvaluateParallel(pipelineHelper, spliterator, WhileOps.DOUBLE_ARR_GEN).spliterator();
            }
            return new UnorderedWhileSpliterator.OfDouble.Dropping((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), false, this.val$predicate);
        }

        @Override // java9.util.stream.DoublePipeline.StatefulOp, java9.util.stream.AbstractPipeline
        <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Double[]> intFunction) {
            return (Node) new DropWhileTask(this, pipelineHelper, spliterator, intFunction).invoke();
        }

        @Override // java9.util.stream.AbstractPipeline
        Sink<Double> opWrapSink(int i, Sink<Double> sink) {
            return opWrapSink(sink, false);
        }

        /* JADX INFO: renamed from: java9.util.stream.WhileOps$4Op$1OpSink, reason: invalid class name */
        class C1OpSink extends Sink.ChainedDouble<Double> implements DropWhileSink<Double> {
            long dropCount;
            boolean take;
            final /* synthetic */ boolean val$retainAndCountDroppedElements;
            final /* synthetic */ Sink val$sink;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C1OpSink(Sink sink, boolean z) {
                super(sink);
                this.val$sink = sink;
                this.val$retainAndCountDroppedElements = z;
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java9.util.stream.Sink.OfDouble, java9.util.stream.Sink
            public void accept(double d) {
                boolean z = true;
                if (!this.take) {
                    boolean z2 = !C4Op.this.val$predicate.test(d);
                    this.take = z2;
                    if (!z2) {
                        z = false;
                    }
                }
                boolean z3 = this.val$retainAndCountDroppedElements;
                if (z3 && !z) {
                    this.dropCount++;
                }
                if (z3 || z) {
                    this.downstream.accept(d);
                }
            }

            @Override // java9.util.stream.WhileOps.DropWhileSink
            public long getDropCount() {
                return this.dropCount;
            }
        }

        @Override // java9.util.stream.WhileOps.DropWhileOp
        public DropWhileSink<Double> opWrapSink(Sink<Double> sink, boolean z) {
            return new C1OpSink(sink, z);
        }
    }

    static abstract class UnorderedWhileSpliterator<T, T_SPLITR extends Spliterator<T>> implements Spliterator<T> {
        static final int CANCEL_CHECK_COUNT = 63;
        final AtomicBoolean cancel;
        int count;
        final boolean noSplitting;
        final T_SPLITR s;
        boolean takeOrDrop;

        @Override // java9.util.Spliterator
        public long getExactSizeIfKnown() {
            return -1L;
        }

        abstract T_SPLITR makeSpliterator(T_SPLITR t_splitr);

        UnorderedWhileSpliterator(T_SPLITR t_splitr, boolean z) {
            this.takeOrDrop = true;
            this.s = t_splitr;
            this.noSplitting = z;
            this.cancel = new AtomicBoolean();
        }

        UnorderedWhileSpliterator(T_SPLITR t_splitr, UnorderedWhileSpliterator<T, T_SPLITR> unorderedWhileSpliterator) {
            this.takeOrDrop = true;
            this.s = t_splitr;
            this.noSplitting = unorderedWhileSpliterator.noSplitting;
            this.cancel = unorderedWhileSpliterator.cancel;
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.s.estimateSize();
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.s.characteristics() & (-16449);
        }

        @Override // java9.util.Spliterator
        public Comparator<? super T> getComparator() {
            return this.s.getComparator();
        }

        @Override // java9.util.Spliterator
        public T_SPLITR trySplit() {
            Spliterator<T> spliteratorTrySplit = this.noSplitting ? null : this.s.trySplit();
            if (spliteratorTrySplit != null) {
                return (T_SPLITR) makeSpliterator(spliteratorTrySplit);
            }
            return null;
        }

        boolean checkCancelOnCount() {
            return (this.count == 0 && this.cancel.get()) ? false : true;
        }

        static abstract class OfRef<T> extends UnorderedWhileSpliterator<T, Spliterator<T>> implements Consumer<T> {
            final Predicate<? super T> p;
            T t;

            OfRef(Spliterator<T> spliterator, boolean z, Predicate<? super T> predicate) {
                super(spliterator, z);
                this.p = predicate;
            }

            OfRef(Spliterator<T> spliterator, OfRef<T> ofRef) {
                super(spliterator, ofRef);
                this.p = ofRef.p;
            }

            @Override // java9.util.function.Consumer
            public void accept(T t) {
                this.count = (this.count + 1) & 63;
                this.t = t;
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super T> consumer) {
                Spliterators.forEachRemaining(this, consumer);
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }

            static final class Taking<T> extends OfRef<T> {
                Taking(Spliterator<T> spliterator, boolean z, Predicate<? super T> predicate) {
                    super(spliterator, z, predicate);
                }

                Taking(Spliterator<T> spliterator, Taking<T> taking) {
                    super(spliterator, taking);
                }

                @Override // java9.util.Spliterator
                public boolean tryAdvance(Consumer<? super T> consumer) {
                    boolean zTest;
                    if (this.takeOrDrop && checkCancelOnCount() && this.s.tryAdvance(this)) {
                        zTest = this.p.test(this.t);
                        if (zTest) {
                            consumer.accept(this.t);
                            return true;
                        }
                    } else {
                        zTest = true;
                    }
                    this.takeOrDrop = false;
                    if (!zTest) {
                        this.cancel.set(true);
                    }
                    return false;
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public Spliterator<T> trySplit() {
                    if (this.cancel.get()) {
                        return null;
                    }
                    return super.trySplit();
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
                Spliterator<T> makeSpliterator(Spliterator<T> spliterator) {
                    return new Taking(spliterator, this);
                }
            }

            static final class Dropping<T> extends OfRef<T> {
                Dropping(Spliterator<T> spliterator, boolean z, Predicate<? super T> predicate) {
                    super(spliterator, z, predicate);
                }

                Dropping(Spliterator<T> spliterator, Dropping<T> dropping) {
                    super(spliterator, dropping);
                }

                @Override // java9.util.Spliterator
                public boolean tryAdvance(Consumer<? super T> consumer) {
                    boolean zTryAdvance;
                    if (this.takeOrDrop) {
                        boolean z = false;
                        this.takeOrDrop = false;
                        while (true) {
                            zTryAdvance = this.s.tryAdvance(this);
                            if (!zTryAdvance || !checkCancelOnCount() || !this.p.test(this.t)) {
                                break;
                            }
                            z = true;
                        }
                        if (zTryAdvance) {
                            if (z) {
                                this.cancel.set(true);
                            }
                            consumer.accept(this.t);
                        }
                        return zTryAdvance;
                    }
                    return this.s.tryAdvance(consumer);
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
                Spliterator<T> makeSpliterator(Spliterator<T> spliterator) {
                    return new Dropping(spliterator, this);
                }
            }
        }

        static abstract class OfInt extends UnorderedWhileSpliterator<Integer, Spliterator.OfInt> implements IntConsumer, Spliterator.OfInt {
            final IntPredicate p;
            int t;

            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfInt(Spliterator.OfInt ofInt, boolean z, IntPredicate intPredicate) {
                super(ofInt, z);
                this.p = intPredicate;
            }

            OfInt(Spliterator.OfInt ofInt, OfInt ofInt2) {
                super(ofInt, ofInt2);
                this.p = ofInt2.p;
            }

            @Override // java9.util.function.IntConsumer
            public void accept(int i) {
                this.count = (this.count + 1) & 63;
                this.t = i;
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(IntConsumer intConsumer) {
                Spliterators.OfInt.forEachRemaining(this, intConsumer);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Integer> consumer) {
                return Spliterators.OfInt.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Integer> consumer) {
                Spliterators.forEachRemaining(this, consumer);
            }

            static final class Taking extends OfInt {
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfInt, java9.util.Spliterator.OfPrimitive
                public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                    super.forEachRemaining(intConsumer);
                }

                Taking(Spliterator.OfInt ofInt, boolean z, IntPredicate intPredicate) {
                    super(ofInt, z, intPredicate);
                }

                Taking(Spliterator.OfInt ofInt, OfInt ofInt2) {
                    super(ofInt, ofInt2);
                }

                @Override // java9.util.Spliterator.OfPrimitive
                public boolean tryAdvance(IntConsumer intConsumer) {
                    boolean zTest;
                    if (this.takeOrDrop && checkCancelOnCount() && ((Spliterator.OfInt) this.s).tryAdvance((IntConsumer) this)) {
                        zTest = this.p.test(this.t);
                        if (zTest) {
                            intConsumer.accept(this.t);
                            return true;
                        }
                    } else {
                        zTest = true;
                    }
                    this.takeOrDrop = false;
                    if (!zTest) {
                        this.cancel.set(true);
                    }
                    return false;
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public Spliterator.OfInt trySplit() {
                    if (this.cancel.get()) {
                        return null;
                    }
                    return (Spliterator.OfInt) super.trySplit();
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
                public Spliterator.OfInt makeSpliterator(Spliterator.OfInt ofInt) {
                    return new Taking(ofInt, this);
                }
            }

            static final class Dropping extends OfInt {
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfInt, java9.util.Spliterator.OfPrimitive
                public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                    super.forEachRemaining(intConsumer);
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfInt, java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                    return (Spliterator.OfInt) super.trySplit();
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfInt, java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                    return (Spliterator.OfPrimitive) super.trySplit();
                }

                Dropping(Spliterator.OfInt ofInt, boolean z, IntPredicate intPredicate) {
                    super(ofInt, z, intPredicate);
                }

                Dropping(Spliterator.OfInt ofInt, OfInt ofInt2) {
                    super(ofInt, ofInt2);
                }

                @Override // java9.util.Spliterator.OfPrimitive
                public boolean tryAdvance(IntConsumer intConsumer) {
                    boolean zTryAdvance;
                    if (this.takeOrDrop) {
                        boolean z = false;
                        this.takeOrDrop = false;
                        while (true) {
                            zTryAdvance = ((Spliterator.OfInt) this.s).tryAdvance((IntConsumer) this);
                            if (!zTryAdvance || !checkCancelOnCount() || !this.p.test(this.t)) {
                                break;
                            }
                            z = true;
                        }
                        if (zTryAdvance) {
                            if (z) {
                                this.cancel.set(true);
                            }
                            intConsumer.accept(this.t);
                        }
                        return zTryAdvance;
                    }
                    return ((Spliterator.OfInt) this.s).tryAdvance(intConsumer);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
                public Spliterator.OfInt makeSpliterator(Spliterator.OfInt ofInt) {
                    return new Dropping(ofInt, this);
                }
            }
        }

        static abstract class OfLong extends UnorderedWhileSpliterator<Long, Spliterator.OfLong> implements LongConsumer, Spliterator.OfLong {
            final LongPredicate p;
            long t;

            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfLong(Spliterator.OfLong ofLong, boolean z, LongPredicate longPredicate) {
                super(ofLong, z);
                this.p = longPredicate;
            }

            OfLong(Spliterator.OfLong ofLong, OfLong ofLong2) {
                super(ofLong, ofLong2);
                this.p = ofLong2.p;
            }

            @Override // java9.util.function.LongConsumer
            public void accept(long j) {
                this.count = (this.count + 1) & 63;
                this.t = j;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
            public Spliterator.OfLong makeSpliterator(Spliterator.OfLong ofLong) {
                return new Dropping(ofLong, this);
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(LongConsumer longConsumer) {
                Spliterators.OfLong.forEachRemaining(this, longConsumer);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Long> consumer) {
                return Spliterators.OfLong.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Long> consumer) {
                Spliterators.OfLong.forEachRemaining(this, consumer);
            }

            static final class Taking extends OfLong {
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfLong, java9.util.Spliterator.OfPrimitive
                public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                    super.forEachRemaining(longConsumer);
                }

                Taking(Spliterator.OfLong ofLong, boolean z, LongPredicate longPredicate) {
                    super(ofLong, z, longPredicate);
                }

                Taking(Spliterator.OfLong ofLong, OfLong ofLong2) {
                    super(ofLong, ofLong2);
                }

                @Override // java9.util.Spliterator.OfPrimitive
                public boolean tryAdvance(LongConsumer longConsumer) {
                    boolean zTest;
                    if (this.takeOrDrop && checkCancelOnCount() && ((Spliterator.OfLong) this.s).tryAdvance((LongConsumer) this)) {
                        zTest = this.p.test(this.t);
                        if (zTest) {
                            longConsumer.accept(this.t);
                            return true;
                        }
                    } else {
                        zTest = true;
                    }
                    this.takeOrDrop = false;
                    if (!zTest) {
                        this.cancel.set(true);
                    }
                    return false;
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public Spliterator.OfLong trySplit() {
                    if (this.cancel.get()) {
                        return null;
                    }
                    return (Spliterator.OfLong) super.trySplit();
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfLong, java9.util.stream.WhileOps.UnorderedWhileSpliterator
                public Spliterator.OfLong makeSpliterator(Spliterator.OfLong ofLong) {
                    return new Taking(ofLong, this);
                }
            }

            static final class Dropping extends OfLong {
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfLong, java9.util.Spliterator.OfPrimitive
                public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                    super.forEachRemaining(longConsumer);
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfLong, java9.util.stream.WhileOps.UnorderedWhileSpliterator
                /* bridge */ /* synthetic */ Spliterator makeSpliterator(Spliterator spliterator) {
                    return super.makeSpliterator((Spliterator.OfLong) spliterator);
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfLong, java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                    return (Spliterator.OfLong) super.trySplit();
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfLong, java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                    return (Spliterator.OfPrimitive) super.trySplit();
                }

                Dropping(Spliterator.OfLong ofLong, boolean z, LongPredicate longPredicate) {
                    super(ofLong, z, longPredicate);
                }

                Dropping(Spliterator.OfLong ofLong, OfLong ofLong2) {
                    super(ofLong, ofLong2);
                }

                @Override // java9.util.Spliterator.OfPrimitive
                public boolean tryAdvance(LongConsumer longConsumer) {
                    boolean zTryAdvance;
                    if (this.takeOrDrop) {
                        boolean z = false;
                        this.takeOrDrop = false;
                        while (true) {
                            zTryAdvance = ((Spliterator.OfLong) this.s).tryAdvance((LongConsumer) this);
                            if (!zTryAdvance || !checkCancelOnCount() || !this.p.test(this.t)) {
                                break;
                            }
                            z = true;
                        }
                        if (zTryAdvance) {
                            if (z) {
                                this.cancel.set(true);
                            }
                            longConsumer.accept(this.t);
                        }
                        return zTryAdvance;
                    }
                    return ((Spliterator.OfLong) this.s).tryAdvance(longConsumer);
                }
            }
        }

        static abstract class OfDouble extends UnorderedWhileSpliterator<Double, Spliterator.OfDouble> implements DoubleConsumer, Spliterator.OfDouble {
            final DoublePredicate p;
            double t;

            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfDouble(Spliterator.OfDouble ofDouble, boolean z, DoublePredicate doublePredicate) {
                super(ofDouble, z);
                this.p = doublePredicate;
            }

            OfDouble(Spliterator.OfDouble ofDouble, OfDouble ofDouble2) {
                super(ofDouble, ofDouble2);
                this.p = ofDouble2.p;
            }

            @Override // java9.util.function.DoubleConsumer
            public void accept(double d) {
                this.count = (this.count + 1) & 63;
                this.t = d;
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(DoubleConsumer doubleConsumer) {
                Spliterators.OfDouble.forEachRemaining(this, doubleConsumer);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Double> consumer) {
                return Spliterators.OfDouble.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Double> consumer) {
                Spliterators.OfDouble.forEachRemaining(this, consumer);
            }

            static final class Taking extends OfDouble {
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfDouble, java9.util.Spliterator.OfPrimitive
                public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                    super.forEachRemaining(doubleConsumer);
                }

                Taking(Spliterator.OfDouble ofDouble, boolean z, DoublePredicate doublePredicate) {
                    super(ofDouble, z, doublePredicate);
                }

                Taking(Spliterator.OfDouble ofDouble, OfDouble ofDouble2) {
                    super(ofDouble, ofDouble2);
                }

                @Override // java9.util.Spliterator.OfPrimitive
                public boolean tryAdvance(DoubleConsumer doubleConsumer) {
                    boolean zTest;
                    if (this.takeOrDrop && checkCancelOnCount() && ((Spliterator.OfDouble) this.s).tryAdvance((DoubleConsumer) this)) {
                        zTest = this.p.test(this.t);
                        if (zTest) {
                            doubleConsumer.accept(this.t);
                            return true;
                        }
                    } else {
                        zTest = true;
                    }
                    this.takeOrDrop = false;
                    if (!zTest) {
                        this.cancel.set(true);
                    }
                    return false;
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public Spliterator.OfDouble trySplit() {
                    if (this.cancel.get()) {
                        return null;
                    }
                    return (Spliterator.OfDouble) super.trySplit();
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
                public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble ofDouble) {
                    return new Taking(ofDouble, this);
                }
            }

            static final class Dropping extends OfDouble {
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfDouble, java9.util.Spliterator.OfPrimitive
                public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                    super.forEachRemaining(doubleConsumer);
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfDouble, java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                    return (Spliterator.OfDouble) super.trySplit();
                }

                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator.OfDouble, java9.util.stream.WhileOps.UnorderedWhileSpliterator, java9.util.Spliterator
                public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                    return (Spliterator.OfPrimitive) super.trySplit();
                }

                Dropping(Spliterator.OfDouble ofDouble, boolean z, DoublePredicate doublePredicate) {
                    super(ofDouble, z, doublePredicate);
                }

                Dropping(Spliterator.OfDouble ofDouble, OfDouble ofDouble2) {
                    super(ofDouble, ofDouble2);
                }

                @Override // java9.util.Spliterator.OfPrimitive
                public boolean tryAdvance(DoubleConsumer doubleConsumer) {
                    boolean zTryAdvance;
                    if (this.takeOrDrop) {
                        boolean z = false;
                        this.takeOrDrop = false;
                        while (true) {
                            zTryAdvance = ((Spliterator.OfDouble) this.s).tryAdvance((DoubleConsumer) this);
                            if (!zTryAdvance || !checkCancelOnCount() || !this.p.test(this.t)) {
                                break;
                            }
                            z = true;
                        }
                        if (zTryAdvance) {
                            if (z) {
                                this.cancel.set(true);
                            }
                            doubleConsumer.accept(this.t);
                        }
                        return zTryAdvance;
                    }
                    return ((Spliterator.OfDouble) this.s).tryAdvance(doubleConsumer);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // java9.util.stream.WhileOps.UnorderedWhileSpliterator
                public Spliterator.OfDouble makeSpliterator(Spliterator.OfDouble ofDouble) {
                    return new Dropping(ofDouble, this);
                }
            }
        }
    }

    private static final class TakeWhileTask<P_IN, P_OUT> extends AbstractShortCircuitTask<P_IN, P_OUT, Node<P_OUT>, TakeWhileTask<P_IN, P_OUT>> {
        private volatile boolean completed;
        private final IntFunction<P_OUT[]> generator;
        private final boolean isOrdered;
        private final AbstractPipeline<P_OUT, P_OUT, ?> op;
        private boolean shortCircuited;
        private long thisNodeSize;

        TakeWhileTask(AbstractPipeline<P_OUT, P_OUT, ?> abstractPipeline, PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<P_OUT[]> intFunction) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags());
        }

        TakeWhileTask(TakeWhileTask<P_IN, P_OUT> takeWhileTask, Spliterator<P_IN> spliterator) {
            super(takeWhileTask, spliterator);
            this.op = takeWhileTask.op;
            this.generator = takeWhileTask.generator;
            this.isOrdered = takeWhileTask.isOrdered;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public TakeWhileTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new TakeWhileTask<>(this, spliterator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractShortCircuitTask
        public final Node<P_OUT> getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public final Node<P_OUT> doLeaf() {
            Node.Builder<P_OUT> builderMakeNodeBuilder = this.helper.makeNodeBuilder(-1L, this.generator);
            boolean zCopyIntoWithCancel = this.helper.copyIntoWithCancel(this.helper.wrapSink((Sink) this.op.opWrapSink(this.helper.getStreamAndOpFlags(), builderMakeNodeBuilder)), this.spliterator);
            this.shortCircuited = zCopyIntoWithCancel;
            if (zCopyIntoWithCancel) {
                cancelLaterNodes();
            }
            Node<P_OUT> nodeBuild2 = builderMakeNodeBuilder.build2();
            this.thisNodeSize = nodeBuild2.count();
            return nodeBuild2;
        }

        @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
        public final void onCompletion(CountedCompleter<?> countedCompleter) {
            Node<P_OUT> nodeMerge;
            if (!isLeaf()) {
                this.shortCircuited = ((TakeWhileTask) this.leftChild).shortCircuited | ((TakeWhileTask) this.rightChild).shortCircuited;
                if (this.isOrdered && this.canceled) {
                    this.thisNodeSize = 0L;
                    nodeMerge = getEmptyResult();
                } else if (this.isOrdered && ((TakeWhileTask) this.leftChild).shortCircuited) {
                    this.thisNodeSize = ((TakeWhileTask) this.leftChild).thisNodeSize;
                    nodeMerge = ((TakeWhileTask) this.leftChild).getLocalResult();
                } else {
                    this.thisNodeSize = ((TakeWhileTask) this.leftChild).thisNodeSize + ((TakeWhileTask) this.rightChild).thisNodeSize;
                    nodeMerge = merge();
                }
                setLocalResult(nodeMerge);
            }
            this.completed = true;
            super.onCompletion(countedCompleter);
        }

        Node<P_OUT> merge() {
            if (((TakeWhileTask) this.leftChild).thisNodeSize == 0) {
                return ((TakeWhileTask) this.rightChild).getLocalResult();
            }
            if (((TakeWhileTask) this.rightChild).thisNodeSize == 0) {
                return ((TakeWhileTask) this.leftChild).getLocalResult();
            }
            return Nodes.conc(this.op.getOutputShape(), ((TakeWhileTask) this.leftChild).getLocalResult(), ((TakeWhileTask) this.rightChild).getLocalResult());
        }

        @Override // java9.util.stream.AbstractShortCircuitTask
        protected void cancel() {
            super.cancel();
            if (this.isOrdered && this.completed) {
                setLocalResult(getEmptyResult());
            }
        }
    }

    private static final class DropWhileTask<P_IN, P_OUT> extends AbstractTask<P_IN, P_OUT, Node<P_OUT>, DropWhileTask<P_IN, P_OUT>> {
        private final IntFunction<P_OUT[]> generator;
        private long index;
        private final boolean isOrdered;
        private final AbstractPipeline<P_OUT, P_OUT, ?> op;
        private long thisNodeSize;

        DropWhileTask(AbstractPipeline<P_OUT, P_OUT, ?> abstractPipeline, PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<P_OUT[]> intFunction) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.isOrdered = StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags());
        }

        DropWhileTask(DropWhileTask<P_IN, P_OUT> dropWhileTask, Spliterator<P_IN> spliterator) {
            super(dropWhileTask, spliterator);
            this.op = dropWhileTask.op;
            this.generator = dropWhileTask.generator;
            this.isOrdered = dropWhileTask.isOrdered;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public DropWhileTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new DropWhileTask<>(this, spliterator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public final Node<P_OUT> doLeaf() {
            boolean z = !isRoot();
            Node.Builder<P_OUT> builderMakeNodeBuilder = this.helper.makeNodeBuilder((z && this.isOrdered && StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags)) ? this.op.exactOutputSizeIfKnown(this.spliterator) : -1L, this.generator);
            DropWhileSink dropWhileSinkOpWrapSink = ((DropWhileOp) this.op).opWrapSink(builderMakeNodeBuilder, this.isOrdered && z);
            this.helper.wrapAndCopyInto(dropWhileSinkOpWrapSink, (Spliterator) this.spliterator);
            Node<P_OUT> nodeBuild2 = builderMakeNodeBuilder.build2();
            this.thisNodeSize = nodeBuild2.count();
            this.index = dropWhileSinkOpWrapSink.getDropCount();
            return nodeBuild2;
        }

        @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
        public final void onCompletion(CountedCompleter<?> countedCompleter) {
            if (!isLeaf()) {
                if (this.isOrdered) {
                    long j = ((DropWhileTask) this.leftChild).index;
                    this.index = j;
                    if (j == ((DropWhileTask) this.leftChild).thisNodeSize) {
                        this.index += ((DropWhileTask) this.rightChild).index;
                    }
                }
                this.thisNodeSize = ((DropWhileTask) this.leftChild).thisNodeSize + ((DropWhileTask) this.rightChild).thisNodeSize;
                Node<P_OUT> nodeMerge = merge();
                if (isRoot()) {
                    nodeMerge = doTruncate(nodeMerge);
                }
                setLocalResult(nodeMerge);
            }
            super.onCompletion(countedCompleter);
        }

        private Node<P_OUT> merge() {
            if (((DropWhileTask) this.leftChild).thisNodeSize == 0) {
                return ((DropWhileTask) this.rightChild).getLocalResult();
            }
            if (((DropWhileTask) this.rightChild).thisNodeSize == 0) {
                return ((DropWhileTask) this.leftChild).getLocalResult();
            }
            return Nodes.conc(this.op.getOutputShape(), ((DropWhileTask) this.leftChild).getLocalResult(), ((DropWhileTask) this.rightChild).getLocalResult());
        }

        private Node<P_OUT> doTruncate(Node<P_OUT> node) {
            return this.isOrdered ? node.truncate(this.index, node.count(), this.generator) : node;
        }
    }
}
