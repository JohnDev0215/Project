package java9.util.stream;

import java.util.Iterator;
import java9.util.DoubleSummaryStatistics;
import java9.util.Objects;
import java9.util.OptionalDouble;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.BiConsumer;
import java9.util.function.DoubleBinaryOperator;
import java9.util.function.DoubleConsumer;
import java9.util.function.DoubleFunction;
import java9.util.function.DoublePredicate;
import java9.util.function.DoubleToIntFunction;
import java9.util.function.DoubleToLongFunction;
import java9.util.function.DoubleUnaryOperator;
import java9.util.function.IntFunction;
import java9.util.function.ObjDoubleConsumer;
import java9.util.function.Supplier;
import java9.util.stream.IntPipeline;
import java9.util.stream.LongPipeline;
import java9.util.stream.MatchOps;
import java9.util.stream.Node;
import java9.util.stream.ReferencePipeline;
import java9.util.stream.Sink;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
abstract class DoublePipeline<E_IN> extends AbstractPipeline<E_IN, Double, DoubleStream> implements DoubleStream {
    static /* synthetic */ double[] lambda$average$4() {
        return new double[4];
    }

    static /* synthetic */ double[] lambda$sum$1() {
        return new double[3];
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    public /* bridge */ /* synthetic */ DoubleStream parallel() {
        return (DoubleStream) super.parallel();
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    public /* bridge */ /* synthetic */ DoubleStream sequential() {
        return (DoubleStream) super.sequential();
    }

    DoublePipeline(Supplier<? extends Spliterator<Double>> supplier, int i, boolean z) {
        super(supplier, i, z);
    }

    DoublePipeline(Spliterator<Double> spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    DoublePipeline(AbstractPipeline<?, E_IN, ?> abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    private static DoubleConsumer adapt(Sink<Double> sink) {
        if (sink instanceof DoubleConsumer) {
            return (DoubleConsumer) sink;
        }
        sink.getClass();
        return DoublePipeline$$Lambda$1.lambdaFactory$(sink);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Spliterator.OfDouble adapt(Spliterator<Double> spliterator) {
        if (spliterator instanceof Spliterator.OfDouble) {
            return (Spliterator.OfDouble) spliterator;
        }
        throw new UnsupportedOperationException("DoubleStream.adapt(Spliterator<Double> s)");
    }

    @Override // java9.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.DOUBLE_VALUE;
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN> Node<Double> evaluateToNode(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, boolean z, IntFunction<Double[]> intFunction) {
        return Nodes.collectDouble(pipelineHelper, spliterator, z);
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN> Spliterator<Double> wrap(PipelineHelper<Double> pipelineHelper, Supplier<Spliterator<P_IN>> supplier, boolean z) {
        return new StreamSpliterators.DoubleWrappingSpliterator(pipelineHelper, supplier, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // java9.util.stream.AbstractPipeline
    /* JADX INFO: renamed from: lazySpliterator, reason: merged with bridge method [inline-methods] */
    public final Spliterator<Double> lazySpliterator2(Supplier<? extends Spliterator<Double>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfDouble(supplier);
    }

    @Override // java9.util.stream.AbstractPipeline
    final boolean forEachWithCancel(Spliterator<Double> spliterator, Sink<Double> sink) {
        boolean zCancellationRequested;
        Spliterator.OfDouble ofDoubleAdapt = adapt(spliterator);
        DoubleConsumer doubleConsumerAdapt = adapt(sink);
        do {
            zCancellationRequested = sink.cancellationRequested();
            if (zCancellationRequested) {
                break;
            }
        } while (ofDoubleAdapt.tryAdvance(doubleConsumerAdapt));
        return zCancellationRequested;
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.PipelineHelper
    final Node.Builder<Double> makeNodeBuilder(long j, IntFunction<Double[]> intFunction) {
        return Nodes.doubleBuilder(j);
    }

    private <U> Stream<U> mapToObj(final DoubleFunction<? extends U> doubleFunction, int i) {
        return new ReferencePipeline.StatelessOp<Double, U>(this, StreamShape.DOUBLE_VALUE, i) { // from class: java9.util.stream.DoublePipeline.1
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i2, Sink<U> sink) {
                return new Sink.ChainedDouble<U>(sink) { // from class: java9.util.stream.DoublePipeline.1.1
                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.stream.Sink.OfDouble, java9.util.stream.Sink
                    public void accept(double d) {
                        this.downstream.accept((Object) doubleFunction.apply(d));
                    }
                };
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java9.util.Spliterator$OfDouble] */
    @Override // java9.util.stream.BaseStream
    public final Iterator<Double> iterator() {
        return Spliterators.iterator((Spliterator.OfDouble) spliterator2());
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator, reason: merged with bridge method [inline-methods] */
    public final Spliterator<Double> spliterator2() {
        return adapt((Spliterator<Double>) super.spliterator2());
    }

    @Override // java9.util.stream.DoubleStream
    public final Stream<Double> boxed() {
        return mapToObj(DoublePipeline$$Lambda$2.instance, 0);
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream map(final DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doubleUnaryOperator);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.DoublePipeline.2
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) { // from class: java9.util.stream.DoublePipeline.2.1
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
                        this.downstream.accept(doubleUnaryOperator.applyAsDouble(d));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.DoubleStream
    public final <U> Stream<U> mapToObj(DoubleFunction<? extends U> doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return mapToObj(doubleFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    @Override // java9.util.stream.DoubleStream
    public final IntStream mapToInt(final DoubleToIntFunction doubleToIntFunction) {
        Objects.requireNonNull(doubleToIntFunction);
        return new IntPipeline.StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.DoublePipeline.3
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedDouble<Integer>(sink) { // from class: java9.util.stream.DoublePipeline.3.1
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
                        this.downstream.accept(doubleToIntFunction.applyAsInt(d));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.DoubleStream
    public final LongStream mapToLong(final DoubleToLongFunction doubleToLongFunction) {
        Objects.requireNonNull(doubleToLongFunction);
        return new LongPipeline.StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.DoublePipeline.4
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedDouble<Long>(sink) { // from class: java9.util.stream.DoublePipeline.4.1
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
                        this.downstream.accept(doubleToLongFunction.applyAsLong(d));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream flatMap(final DoubleFunction<? extends DoubleStream> doubleFunction) {
        Objects.requireNonNull(doubleFunction);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.DoublePipeline.5
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) { // from class: java9.util.stream.DoublePipeline.5.1
                    boolean cancellationRequested;
                    DoubleConsumer downstreamAsDouble;

                    {
                        Sink<? super E_OUT> sink2 = this.downstream;
                        sink2.getClass();
                        this.downstreamAsDouble = DoublePipeline$5$1$$Lambda$1.lambdaFactory$(sink2);
                    }

                    @Override // java9.util.stream.Sink.ChainedDouble, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference failed for: r4v5, types: [java9.util.Spliterator$OfDouble] */
                    @Override // java9.util.stream.Sink.OfDouble, java9.util.stream.Sink
                    public void accept(double d) throws Throwable {
                        DoubleStream doubleStream = null;
                        try {
                            DoubleStream doubleStream2 = (DoubleStream) doubleFunction.apply(d);
                            if (doubleStream2 != null) {
                                try {
                                    if (!this.cancellationRequested) {
                                        doubleStream2.sequential().forEach(this.downstreamAsDouble);
                                    } else {
                                        ?? Spliterator2 = doubleStream2.sequential().spliterator2();
                                        while (!this.downstream.cancellationRequested() && Spliterator2.tryAdvance(this.downstreamAsDouble)) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    doubleStream = doubleStream2;
                                    if (doubleStream != null) {
                                        doubleStream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (doubleStream2 != null) {
                                doubleStream2.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedDouble, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.BaseStream
    public DoubleStream unordered() {
        return !isOrdered() ? this : new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_ORDERED) { // from class: java9.util.stream.DoublePipeline.6
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return sink;
            }
        };
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream filter(final DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.DoublePipeline.7
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) { // from class: java9.util.stream.DoublePipeline.7.1
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
                        if (doublePredicate.test(d)) {
                            this.downstream.accept(d);
                        }
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream peek(final DoubleConsumer doubleConsumer) {
        Objects.requireNonNull(doubleConsumer);
        return new StatelessOp<Double>(this, StreamShape.DOUBLE_VALUE, 0) { // from class: java9.util.stream.DoublePipeline.8
            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) { // from class: java9.util.stream.DoublePipeline.8.1
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
                        doubleConsumer.accept(d);
                        this.downstream.accept(d);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return SliceOps.makeDouble(this, 0L, j);
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeDouble(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream takeWhile(DoublePredicate doublePredicate) {
        return WhileOps.makeTakeWhileDouble(this, doublePredicate);
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream dropWhile(DoublePredicate doublePredicate) {
        return WhileOps.makeDropWhileDouble(this, doublePredicate);
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream sorted() {
        return SortedOps.makeDouble(this);
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleStream distinct() {
        return boxed().distinct().mapToDouble(DoublePipeline$$Lambda$3.instance);
    }

    @Override // java9.util.stream.DoubleStream
    public void forEach(DoubleConsumer doubleConsumer) {
        evaluate(ForEachOps.makeDouble(doubleConsumer, false));
    }

    @Override // java9.util.stream.DoubleStream
    public void forEachOrdered(DoubleConsumer doubleConsumer) {
        evaluate(ForEachOps.makeDouble(doubleConsumer, true));
    }

    @Override // java9.util.stream.DoubleStream
    public final double sum() {
        return Collectors.computeFinalSum((double[]) collect(DoublePipeline$$Lambda$4.instance, DoublePipeline$$Lambda$5.instance, DoublePipeline$$Lambda$6.instance));
    }

    static /* synthetic */ void lambda$sum$2(double[] dArr, double d) {
        Collectors.sumWithCompensation(dArr, d);
        dArr[2] = dArr[2] + d;
    }

    static /* synthetic */ void lambda$sum$3(double[] dArr, double[] dArr2) {
        Collectors.sumWithCompensation(dArr, dArr2[0]);
        Collectors.sumWithCompensation(dArr, -dArr2[1]);
        dArr[2] = dArr[2] + dArr2[2];
    }

    @Override // java9.util.stream.DoubleStream
    public final OptionalDouble min() {
        return reduce(DoublePipeline$$Lambda$7.instance);
    }

    @Override // java9.util.stream.DoubleStream
    public final OptionalDouble max() {
        return reduce(DoublePipeline$$Lambda$8.instance);
    }

    @Override // java9.util.stream.DoubleStream
    public final OptionalDouble average() {
        double[] dArr = (double[]) collect(DoublePipeline$$Lambda$9.instance, DoublePipeline$$Lambda$10.instance, DoublePipeline$$Lambda$11.instance);
        if (dArr[2] > 0.0d) {
            return OptionalDouble.of(Collectors.computeFinalSum(dArr) / dArr[2]);
        }
        return OptionalDouble.empty();
    }

    static /* synthetic */ void lambda$average$5(double[] dArr, double d) {
        dArr[2] = dArr[2] + 1.0d;
        Collectors.sumWithCompensation(dArr, d);
        dArr[3] = dArr[3] + d;
    }

    static /* synthetic */ void lambda$average$6(double[] dArr, double[] dArr2) {
        Collectors.sumWithCompensation(dArr, dArr2[0]);
        Collectors.sumWithCompensation(dArr, -dArr2[1]);
        dArr[2] = dArr[2] + dArr2[2];
        dArr[3] = dArr[3] + dArr2[3];
    }

    @Override // java9.util.stream.DoubleStream
    public final long count() {
        return ((Long) evaluate(ReduceOps.makeDoubleCounting())).longValue();
    }

    @Override // java9.util.stream.DoubleStream
    public final DoubleSummaryStatistics summaryStatistics() {
        return (DoubleSummaryStatistics) collect(Collectors.DBL_SUM_STATS, DoublePipeline$$Lambda$12.instance, DoublePipeline$$Lambda$13.instance);
    }

    @Override // java9.util.stream.DoubleStream
    public final double reduce(double d, DoubleBinaryOperator doubleBinaryOperator) {
        return ((Double) evaluate(ReduceOps.makeDouble(d, doubleBinaryOperator))).doubleValue();
    }

    @Override // java9.util.stream.DoubleStream
    public final OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator) {
        return (OptionalDouble) evaluate(ReduceOps.makeDouble(doubleBinaryOperator));
    }

    @Override // java9.util.stream.DoubleStream
    public final <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> objDoubleConsumer, BiConsumer<R, R> biConsumer) {
        Objects.requireNonNull(biConsumer);
        return (R) evaluate(ReduceOps.makeDouble(supplier, objDoubleConsumer, DoublePipeline$$Lambda$14.lambdaFactory$(biConsumer)));
    }

    static /* synthetic */ Object lambda$collect$7(BiConsumer biConsumer, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        return obj;
    }

    @Override // java9.util.stream.DoubleStream
    public final boolean anyMatch(DoublePredicate doublePredicate) {
        return ((Boolean) evaluate(MatchOps.makeDouble(doublePredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java9.util.stream.DoubleStream
    public final boolean allMatch(DoublePredicate doublePredicate) {
        return ((Boolean) evaluate(MatchOps.makeDouble(doublePredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java9.util.stream.DoubleStream
    public final boolean noneMatch(DoublePredicate doublePredicate) {
        return ((Boolean) evaluate(MatchOps.makeDouble(doublePredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java9.util.stream.DoubleStream
    public final OptionalDouble findFirst() {
        return (OptionalDouble) evaluate(FindOps.makeDouble(true));
    }

    @Override // java9.util.stream.DoubleStream
    public final OptionalDouble findAny() {
        return (OptionalDouble) evaluate(FindOps.makeDouble(false));
    }

    @Override // java9.util.stream.DoubleStream
    public final double[] toArray() {
        return Nodes.flattenDouble((Node.OfDouble) evaluateToArrayNode(WhileOps.DOUBLE_ARR_GEN)).asPrimitiveArray();
    }

    static class Head<E_IN> extends DoublePipeline<E_IN> {
        @Override // java9.util.stream.DoublePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Double> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Double> lazySpliterator2(Supplier<? extends Spliterator<Double>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) super.parallel();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) super.sequential();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Double> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        Head(Supplier<? extends Spliterator<Double>> supplier, int i, boolean z) {
            super(supplier, i, z);
        }

        Head(Spliterator<Double> spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.AbstractPipeline
        final Sink<E_IN> opWrapSink(int i, Sink<Double> sink) {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.DoubleStream
        public void forEach(DoubleConsumer doubleConsumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(doubleConsumer);
            } else {
                super.forEach(doubleConsumer);
            }
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.DoubleStream
        public void forEachOrdered(DoubleConsumer doubleConsumer) {
            if (!isParallel()) {
                DoublePipeline.adapt(sourceStageSpliterator()).forEachRemaining(doubleConsumer);
            } else {
                super.forEachOrdered(doubleConsumer);
            }
        }
    }

    static abstract class StatelessOp<E_IN> extends DoublePipeline<E_IN> {
        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return false;
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Double> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Double> lazySpliterator2(Supplier<? extends Spliterator<Double>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) super.parallel();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) super.sequential();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Double> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatelessOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }

    static abstract class StatefulOp<E_IN> extends DoublePipeline<E_IN> {
        @Override // java9.util.stream.AbstractPipeline
        abstract <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Double[]> intFunction);

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return true;
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Double> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Double> lazySpliterator2(Supplier<? extends Spliterator<Double>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ DoubleStream parallel() {
            return (DoubleStream) super.parallel();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ DoubleStream sequential() {
            return (DoubleStream) super.sequential();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Double> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.DoublePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatefulOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }
}
