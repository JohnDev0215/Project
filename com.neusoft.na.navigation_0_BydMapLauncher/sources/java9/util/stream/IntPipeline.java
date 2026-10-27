package java9.util.stream;

import java.util.Iterator;
import java9.util.IntSummaryStatistics;
import java9.util.Objects;
import java9.util.OptionalDouble;
import java9.util.OptionalInt;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.BiConsumer;
import java9.util.function.IntBinaryOperator;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.IntPredicate;
import java9.util.function.IntToDoubleFunction;
import java9.util.function.IntToLongFunction;
import java9.util.function.IntUnaryOperator;
import java9.util.function.ObjIntConsumer;
import java9.util.function.Supplier;
import java9.util.stream.DoublePipeline;
import java9.util.stream.LongPipeline;
import java9.util.stream.MatchOps;
import java9.util.stream.Node;
import java9.util.stream.ReferencePipeline;
import java9.util.stream.Sink;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
abstract class IntPipeline<E_IN> extends AbstractPipeline<E_IN, Integer, IntStream> implements IntStream {
    static /* synthetic */ long[] lambda$average$1() {
        return new long[2];
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    public /* bridge */ /* synthetic */ IntStream parallel() {
        return (IntStream) super.parallel();
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    public /* bridge */ /* synthetic */ IntStream sequential() {
        return (IntStream) super.sequential();
    }

    IntPipeline(Supplier<? extends Spliterator<Integer>> supplier, int i, boolean z) {
        super(supplier, i, z);
    }

    IntPipeline(Spliterator<Integer> spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    IntPipeline(AbstractPipeline<?, E_IN, ?> abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    private static IntConsumer adapt(Sink<Integer> sink) {
        if (sink instanceof IntConsumer) {
            return (IntConsumer) sink;
        }
        sink.getClass();
        return IntPipeline$$Lambda$1.lambdaFactory$(sink);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Spliterator.OfInt adapt(Spliterator<Integer> spliterator) {
        if (spliterator instanceof Spliterator.OfInt) {
            return (Spliterator.OfInt) spliterator;
        }
        throw new UnsupportedOperationException("IntStream.adapt(Spliterator<Integer> s)");
    }

    @Override // java9.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.INT_VALUE;
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN> Node<Integer> evaluateToNode(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, boolean z, IntFunction<Integer[]> intFunction) {
        return Nodes.collectInt(pipelineHelper, spliterator, z);
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN> Spliterator<Integer> wrap(PipelineHelper<Integer> pipelineHelper, Supplier<Spliterator<P_IN>> supplier, boolean z) {
        return new StreamSpliterators.IntWrappingSpliterator(pipelineHelper, supplier, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // java9.util.stream.AbstractPipeline
    /* JADX INFO: renamed from: lazySpliterator */
    public final Spliterator<Integer> lazySpliterator2(Supplier<? extends Spliterator<Integer>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfInt(supplier);
    }

    @Override // java9.util.stream.AbstractPipeline
    final boolean forEachWithCancel(Spliterator<Integer> spliterator, Sink<Integer> sink) {
        boolean zCancellationRequested;
        Spliterator.OfInt ofIntAdapt = adapt(spliterator);
        IntConsumer intConsumerAdapt = adapt(sink);
        do {
            zCancellationRequested = sink.cancellationRequested();
            if (zCancellationRequested) {
                break;
            }
        } while (ofIntAdapt.tryAdvance(intConsumerAdapt));
        return zCancellationRequested;
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.PipelineHelper
    final Node.Builder<Integer> makeNodeBuilder(long j, IntFunction<Integer[]> intFunction) {
        return Nodes.intBuilder(j);
    }

    private <U> Stream<U> mapToObj(final IntFunction<? extends U> intFunction, int i) {
        return new ReferencePipeline.StatelessOp<Integer, U>(this, StreamShape.INT_VALUE, i) { // from class: java9.util.stream.IntPipeline.1
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i2, Sink<U> sink) {
                return new Sink.ChainedInt<U>(sink) { // from class: java9.util.stream.IntPipeline.1.1
                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.stream.Sink.OfInt, java9.util.stream.Sink
                    public void accept(int i3) {
                        this.downstream.accept((Object) intFunction.apply(i3));
                    }
                };
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java9.util.Spliterator$OfInt] */
    @Override // java9.util.stream.BaseStream
    public final Iterator<Integer> iterator() {
        return Spliterators.iterator((Spliterator.OfInt) spliterator2());
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator */
    public final Spliterator<Integer> spliterator2() {
        return adapt((Spliterator<Integer>) super.spliterator2());
    }

    @Override // java9.util.stream.IntStream
    public final LongStream asLongStream() {
        return new LongPipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, 0) { // from class: java9.util.stream.IntPipeline.2
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedInt<Long>(sink) { // from class: java9.util.stream.IntPipeline.2.1
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
                        this.downstream.accept(i2);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, 0) { // from class: java9.util.stream.IntPipeline.3
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedInt<Double>(sink) { // from class: java9.util.stream.IntPipeline.3.1
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
                        this.downstream.accept(i2);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final Stream<Integer> boxed() {
        return mapToObj(IntPipeline$$Lambda$2.instance, 0);
    }

    @Override // java9.util.stream.IntStream
    public final IntStream map(final IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.IntPipeline.4
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) { // from class: java9.util.stream.IntPipeline.4.1
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
                        this.downstream.accept(intUnaryOperator.applyAsInt(i2));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final <U> Stream<U> mapToObj(IntFunction<? extends U> intFunction) {
        Objects.requireNonNull(intFunction);
        return mapToObj(intFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    @Override // java9.util.stream.IntStream
    public final LongStream mapToLong(final IntToLongFunction intToLongFunction) {
        Objects.requireNonNull(intToLongFunction);
        return new LongPipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.IntPipeline.5
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedInt<Long>(sink) { // from class: java9.util.stream.IntPipeline.5.1
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
                        this.downstream.accept(intToLongFunction.applyAsLong(i2));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final DoubleStream mapToDouble(final IntToDoubleFunction intToDoubleFunction) {
        Objects.requireNonNull(intToDoubleFunction);
        return new DoublePipeline.StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.IntPipeline.6
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedInt<Double>(sink) { // from class: java9.util.stream.IntPipeline.6.1
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
                        this.downstream.accept(intToDoubleFunction.applyAsDouble(i2));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final IntStream flatMap(final IntFunction<? extends IntStream> intFunction) {
        Objects.requireNonNull(intFunction);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.IntPipeline.7
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) { // from class: java9.util.stream.IntPipeline.7.1
                    boolean cancellationRequested;
                    IntConsumer downstreamAsInt;

                    {
                        Sink<? super E_OUT> sink2 = this.downstream;
                        sink2.getClass();
                        this.downstreamAsInt = IntPipeline$7$1$$Lambda$1.lambdaFactory$(sink2);
                    }

                    @Override // java9.util.stream.Sink.ChainedInt, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference failed for: r0v6, types: [java9.util.Spliterator$OfInt] */
                    @Override // java9.util.stream.Sink.OfInt, java9.util.stream.Sink
                    public void accept(int i2) throws Throwable {
                        Throwable th;
                        IntStream intStream;
                        try {
                            intStream = (IntStream) intFunction.apply(i2);
                            if (intStream != null) {
                                try {
                                    if (!this.cancellationRequested) {
                                        intStream.sequential().forEach(this.downstreamAsInt);
                                    } else {
                                        ?? Spliterator2 = intStream.sequential().spliterator2();
                                        while (!this.downstream.cancellationRequested() && Spliterator2.tryAdvance(this.downstreamAsInt)) {
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (intStream != null) {
                                        intStream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (intStream != null) {
                                intStream.close();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            intStream = null;
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedInt, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.BaseStream
    public IntStream unordered() {
        return !isOrdered() ? this : new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_ORDERED) { // from class: java9.util.stream.IntPipeline.8
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return sink;
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final IntStream filter(final IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.IntPipeline.9
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) { // from class: java9.util.stream.IntPipeline.9.1
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
                        if (intPredicate.test(i2)) {
                            this.downstream.accept(i2);
                        }
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final IntStream peek(final IntConsumer intConsumer) {
        Objects.requireNonNull(intConsumer);
        return new StatelessOp<Integer>(this, StreamShape.INT_VALUE, 0) { // from class: java9.util.stream.IntPipeline.10
            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) { // from class: java9.util.stream.IntPipeline.10.1
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
                        intConsumer.accept(i2);
                        this.downstream.accept(i2);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.IntStream
    public final IntStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return SliceOps.makeInt(this, 0L, j);
    }

    @Override // java9.util.stream.IntStream
    public final IntStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeInt(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // java9.util.stream.IntStream
    public final IntStream takeWhile(IntPredicate intPredicate) {
        return WhileOps.makeTakeWhileInt(this, intPredicate);
    }

    @Override // java9.util.stream.IntStream
    public final IntStream dropWhile(IntPredicate intPredicate) {
        return WhileOps.makeDropWhileInt(this, intPredicate);
    }

    @Override // java9.util.stream.IntStream
    public final IntStream sorted() {
        return SortedOps.makeInt(this);
    }

    @Override // java9.util.stream.IntStream
    public final IntStream distinct() {
        return boxed().distinct().mapToInt(IntPipeline$$Lambda$3.instance);
    }

    @Override // java9.util.stream.IntStream
    public void forEach(IntConsumer intConsumer) {
        evaluate(ForEachOps.makeInt(intConsumer, false));
    }

    @Override // java9.util.stream.IntStream
    public void forEachOrdered(IntConsumer intConsumer) {
        evaluate(ForEachOps.makeInt(intConsumer, true));
    }

    @Override // java9.util.stream.IntStream
    public final int sum() {
        return reduce(0, IntPipeline$$Lambda$4.instance);
    }

    @Override // java9.util.stream.IntStream
    public final OptionalInt min() {
        return reduce(IntPipeline$$Lambda$5.instance);
    }

    @Override // java9.util.stream.IntStream
    public final OptionalInt max() {
        return reduce(IntPipeline$$Lambda$6.instance);
    }

    @Override // java9.util.stream.IntStream
    public final long count() {
        return ((Long) evaluate(ReduceOps.makeIntCounting())).longValue();
    }

    @Override // java9.util.stream.IntStream
    public final OptionalDouble average() {
        long j = ((long[]) collect(IntPipeline$$Lambda$7.instance, IntPipeline$$Lambda$8.instance, IntPipeline$$Lambda$9.instance))[0];
        if (j > 0) {
            return OptionalDouble.of(r0[1] / j);
        }
        return OptionalDouble.empty();
    }

    static /* synthetic */ void lambda$average$2(long[] jArr, int i) {
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + ((long) i);
    }

    static /* synthetic */ void lambda$average$3(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    @Override // java9.util.stream.IntStream
    public final IntSummaryStatistics summaryStatistics() {
        return (IntSummaryStatistics) collect(Collectors.INT_SUM_STATS, IntPipeline$$Lambda$10.instance, IntPipeline$$Lambda$11.instance);
    }

    @Override // java9.util.stream.IntStream
    public final int reduce(int i, IntBinaryOperator intBinaryOperator) {
        return ((Integer) evaluate(ReduceOps.makeInt(i, intBinaryOperator))).intValue();
    }

    @Override // java9.util.stream.IntStream
    public final OptionalInt reduce(IntBinaryOperator intBinaryOperator) {
        return (OptionalInt) evaluate(ReduceOps.makeInt(intBinaryOperator));
    }

    @Override // java9.util.stream.IntStream
    public final <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> objIntConsumer, BiConsumer<R, R> biConsumer) {
        Objects.requireNonNull(biConsumer);
        return (R) evaluate(ReduceOps.makeInt(supplier, objIntConsumer, IntPipeline$$Lambda$12.lambdaFactory$(biConsumer)));
    }

    static /* synthetic */ Object lambda$collect$4(BiConsumer biConsumer, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        return obj;
    }

    @Override // java9.util.stream.IntStream
    public final boolean anyMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java9.util.stream.IntStream
    public final boolean allMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java9.util.stream.IntStream
    public final boolean noneMatch(IntPredicate intPredicate) {
        return ((Boolean) evaluate(MatchOps.makeInt(intPredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java9.util.stream.IntStream
    public final OptionalInt findFirst() {
        return (OptionalInt) evaluate(FindOps.makeInt(true));
    }

    @Override // java9.util.stream.IntStream
    public final OptionalInt findAny() {
        return (OptionalInt) evaluate(FindOps.makeInt(false));
    }

    @Override // java9.util.stream.IntStream
    public final int[] toArray() {
        return Nodes.flattenInt((Node.OfInt) evaluateToArrayNode(WhileOps.INT_ARR_GEN)).asPrimitiveArray();
    }

    static class Head<E_IN> extends IntPipeline<E_IN> {
        @Override // java9.util.stream.IntPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Integer> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Integer> lazySpliterator2(Supplier<? extends Spliterator<Integer>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) super.parallel();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) super.sequential();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Integer> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        Head(Supplier<? extends Spliterator<Integer>> supplier, int i, boolean z) {
            super(supplier, i, z);
        }

        Head(Spliterator<Integer> spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.AbstractPipeline
        final Sink<E_IN> opWrapSink(int i, Sink<Integer> sink) {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.IntStream
        public void forEach(IntConsumer intConsumer) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            } else {
                super.forEach(intConsumer);
            }
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.IntStream
        public void forEachOrdered(IntConsumer intConsumer) {
            if (!isParallel()) {
                IntPipeline.adapt(sourceStageSpliterator()).forEachRemaining(intConsumer);
            } else {
                super.forEachOrdered(intConsumer);
            }
        }
    }

    static abstract class StatelessOp<E_IN> extends IntPipeline<E_IN> {
        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return false;
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Integer> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Integer> lazySpliterator2(Supplier<? extends Spliterator<Integer>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) super.parallel();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) super.sequential();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Integer> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatelessOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }

    static abstract class StatefulOp<E_IN> extends IntPipeline<E_IN> {
        @Override // java9.util.stream.AbstractPipeline
        abstract <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> intFunction);

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return true;
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Integer> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Integer> lazySpliterator2(Supplier<? extends Spliterator<Integer>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream parallel() {
            return (IntStream) super.parallel();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ IntStream sequential() {
            return (IntStream) super.sequential();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Integer> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.IntPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatefulOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }
}
