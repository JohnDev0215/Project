package java9.util.stream;

import java.util.Iterator;
import java9.util.LongSummaryStatistics;
import java9.util.Objects;
import java9.util.OptionalDouble;
import java9.util.OptionalLong;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.BiConsumer;
import java9.util.function.IntFunction;
import java9.util.function.LongBinaryOperator;
import java9.util.function.LongConsumer;
import java9.util.function.LongFunction;
import java9.util.function.LongPredicate;
import java9.util.function.LongToDoubleFunction;
import java9.util.function.LongToIntFunction;
import java9.util.function.LongUnaryOperator;
import java9.util.function.ObjLongConsumer;
import java9.util.function.Supplier;
import java9.util.stream.DoublePipeline;
import java9.util.stream.IntPipeline;
import java9.util.stream.MatchOps;
import java9.util.stream.Node;
import java9.util.stream.ReferencePipeline;
import java9.util.stream.Sink;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
abstract class LongPipeline<E_IN> extends AbstractPipeline<E_IN, Long, LongStream> implements LongStream {
    static /* synthetic */ long[] lambda$average$1() {
        return new long[2];
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    public /* bridge */ /* synthetic */ LongStream parallel() {
        return (LongStream) super.parallel();
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    public /* bridge */ /* synthetic */ LongStream sequential() {
        return (LongStream) super.sequential();
    }

    LongPipeline(Supplier<? extends Spliterator<Long>> supplier, int i, boolean z) {
        super(supplier, i, z);
    }

    LongPipeline(Spliterator<Long> spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    LongPipeline(AbstractPipeline<?, E_IN, ?> abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    private static LongConsumer adapt(Sink<Long> sink) {
        if (sink instanceof LongConsumer) {
            return (LongConsumer) sink;
        }
        sink.getClass();
        return LongPipeline$$Lambda$1.lambdaFactory$(sink);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Spliterator.OfLong adapt(Spliterator<Long> spliterator) {
        if (spliterator instanceof Spliterator.OfLong) {
            return (Spliterator.OfLong) spliterator;
        }
        throw new UnsupportedOperationException("LongStream.adapt(Spliterator<Long> s)");
    }

    @Override // java9.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.LONG_VALUE;
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN> Node<Long> evaluateToNode(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, boolean z, IntFunction<Long[]> intFunction) {
        return Nodes.collectLong(pipelineHelper, spliterator, z);
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN> Spliterator<Long> wrap(PipelineHelper<Long> pipelineHelper, Supplier<Spliterator<P_IN>> supplier, boolean z) {
        return new StreamSpliterators.LongWrappingSpliterator(pipelineHelper, supplier, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // java9.util.stream.AbstractPipeline
    /* JADX INFO: renamed from: lazySpliterator */
    public final Spliterator<Long> lazySpliterator2(Supplier<? extends Spliterator<Long>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator.OfLong(supplier);
    }

    @Override // java9.util.stream.AbstractPipeline
    final boolean forEachWithCancel(Spliterator<Long> spliterator, Sink<Long> sink) {
        boolean zCancellationRequested;
        Spliterator.OfLong ofLongAdapt = adapt(spliterator);
        LongConsumer longConsumerAdapt = adapt(sink);
        do {
            zCancellationRequested = sink.cancellationRequested();
            if (zCancellationRequested) {
                break;
            }
        } while (ofLongAdapt.tryAdvance(longConsumerAdapt));
        return zCancellationRequested;
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.PipelineHelper
    final Node.Builder<Long> makeNodeBuilder(long j, IntFunction<Long[]> intFunction) {
        return Nodes.longBuilder(j);
    }

    private <U> Stream<U> mapToObj(final LongFunction<? extends U> longFunction, int i) {
        return new ReferencePipeline.StatelessOp<Long, U>(this, StreamShape.LONG_VALUE, i) { // from class: java9.util.stream.LongPipeline.1
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i2, Sink<U> sink) {
                return new Sink.ChainedLong<U>(sink) { // from class: java9.util.stream.LongPipeline.1.1
                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.stream.Sink.OfLong, java9.util.stream.Sink
                    public void accept(long j) {
                        this.downstream.accept((Object) longFunction.apply(j));
                    }
                };
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java9.util.Spliterator$OfLong] */
    @Override // java9.util.stream.BaseStream
    public final Iterator<Long> iterator() {
        return Spliterators.iterator((Spliterator.OfLong) spliterator2());
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator */
    public final Spliterator<Long> spliterator2() {
        return adapt((Spliterator<Long>) super.spliterator2());
    }

    @Override // java9.util.stream.LongStream
    public final DoubleStream asDoubleStream() {
        return new DoublePipeline.StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.LongPipeline.2
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedLong<Double>(sink) { // from class: java9.util.stream.LongPipeline.2.1
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
                        this.downstream.accept(j);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final Stream<Long> boxed() {
        return mapToObj(LongPipeline$$Lambda$2.instance, 0);
    }

    @Override // java9.util.stream.LongStream
    public final LongStream map(final LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longUnaryOperator);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.LongPipeline.3
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) { // from class: java9.util.stream.LongPipeline.3.1
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
                        this.downstream.accept(longUnaryOperator.applyAsLong(j));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final <U> Stream<U> mapToObj(LongFunction<? extends U> longFunction) {
        Objects.requireNonNull(longFunction);
        return mapToObj(longFunction, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT);
    }

    @Override // java9.util.stream.LongStream
    public final IntStream mapToInt(final LongToIntFunction longToIntFunction) {
        Objects.requireNonNull(longToIntFunction);
        return new IntPipeline.StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.LongPipeline.4
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedLong<Integer>(sink) { // from class: java9.util.stream.LongPipeline.4.1
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
                        this.downstream.accept(longToIntFunction.applyAsInt(j));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final DoubleStream mapToDouble(final LongToDoubleFunction longToDoubleFunction) {
        Objects.requireNonNull(longToDoubleFunction);
        return new DoublePipeline.StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.LongPipeline.5
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedLong<Double>(sink) { // from class: java9.util.stream.LongPipeline.5.1
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
                        this.downstream.accept(longToDoubleFunction.applyAsDouble(j));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final LongStream flatMap(final LongFunction<? extends LongStream> longFunction) {
        Objects.requireNonNull(longFunction);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.LongPipeline.6
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) { // from class: java9.util.stream.LongPipeline.6.1
                    boolean cancellationRequested;
                    LongConsumer downstreamAsLong;

                    {
                        Sink<? super E_OUT> sink2 = this.downstream;
                        sink2.getClass();
                        this.downstreamAsLong = LongPipeline$6$1$$Lambda$1.lambdaFactory$(sink2);
                    }

                    @Override // java9.util.stream.Sink.ChainedLong, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference failed for: r4v5, types: [java9.util.Spliterator$OfLong] */
                    @Override // java9.util.stream.Sink.OfLong, java9.util.stream.Sink
                    public void accept(long j) throws Throwable {
                        LongStream longStream = null;
                        try {
                            LongStream longStream2 = (LongStream) longFunction.apply(j);
                            if (longStream2 != null) {
                                try {
                                    if (!this.cancellationRequested) {
                                        longStream2.sequential().forEach(this.downstreamAsLong);
                                    } else {
                                        ?? Spliterator2 = longStream2.sequential().spliterator2();
                                        while (!this.downstream.cancellationRequested() && Spliterator2.tryAdvance(this.downstreamAsLong)) {
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    longStream = longStream2;
                                    if (longStream != null) {
                                        longStream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (longStream2 != null) {
                                longStream2.close();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedLong, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.BaseStream
    public LongStream unordered() {
        return !isOrdered() ? this : new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_ORDERED) { // from class: java9.util.stream.LongPipeline.7
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return sink;
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final LongStream filter(final LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.LongPipeline.8
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) { // from class: java9.util.stream.LongPipeline.8.1
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
                        if (longPredicate.test(j)) {
                            this.downstream.accept(j);
                        }
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final LongStream peek(final LongConsumer longConsumer) {
        Objects.requireNonNull(longConsumer);
        return new StatelessOp<Long>(this, StreamShape.LONG_VALUE, 0) { // from class: java9.util.stream.LongPipeline.9
            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) { // from class: java9.util.stream.LongPipeline.9.1
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
                        longConsumer.accept(j);
                        this.downstream.accept(j);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.LongStream
    public final LongStream limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return SliceOps.makeLong(this, 0L, j);
    }

    @Override // java9.util.stream.LongStream
    public final LongStream skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeLong(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // java9.util.stream.LongStream
    public final LongStream takeWhile(LongPredicate longPredicate) {
        return WhileOps.makeTakeWhileLong(this, longPredicate);
    }

    @Override // java9.util.stream.LongStream
    public final LongStream dropWhile(LongPredicate longPredicate) {
        return WhileOps.makeDropWhileLong(this, longPredicate);
    }

    @Override // java9.util.stream.LongStream
    public final LongStream sorted() {
        return SortedOps.makeLong(this);
    }

    @Override // java9.util.stream.LongStream
    public final LongStream distinct() {
        return boxed().distinct().mapToLong(LongPipeline$$Lambda$3.instance);
    }

    @Override // java9.util.stream.LongStream
    public void forEach(LongConsumer longConsumer) {
        evaluate(ForEachOps.makeLong(longConsumer, false));
    }

    @Override // java9.util.stream.LongStream
    public void forEachOrdered(LongConsumer longConsumer) {
        evaluate(ForEachOps.makeLong(longConsumer, true));
    }

    @Override // java9.util.stream.LongStream
    public final long sum() {
        return reduce(0L, LongPipeline$$Lambda$4.instance);
    }

    @Override // java9.util.stream.LongStream
    public final OptionalLong min() {
        return reduce(LongPipeline$$Lambda$5.instance);
    }

    @Override // java9.util.stream.LongStream
    public final OptionalLong max() {
        return reduce(LongPipeline$$Lambda$6.instance);
    }

    @Override // java9.util.stream.LongStream
    public final OptionalDouble average() {
        long j = ((long[]) collect(LongPipeline$$Lambda$7.instance, LongPipeline$$Lambda$8.instance, LongPipeline$$Lambda$9.instance))[0];
        if (j > 0) {
            return OptionalDouble.of(r0[1] / j);
        }
        return OptionalDouble.empty();
    }

    static /* synthetic */ void lambda$average$2(long[] jArr, long j) {
        jArr[0] = jArr[0] + 1;
        jArr[1] = jArr[1] + j;
    }

    static /* synthetic */ void lambda$average$3(long[] jArr, long[] jArr2) {
        jArr[0] = jArr[0] + jArr2[0];
        jArr[1] = jArr[1] + jArr2[1];
    }

    @Override // java9.util.stream.LongStream
    public final long count() {
        return ((Long) evaluate(ReduceOps.makeLongCounting())).longValue();
    }

    @Override // java9.util.stream.LongStream
    public final LongSummaryStatistics summaryStatistics() {
        return (LongSummaryStatistics) collect(Collectors.LNG_SUM_STATS, LongPipeline$$Lambda$10.instance, LongPipeline$$Lambda$11.instance);
    }

    @Override // java9.util.stream.LongStream
    public final long reduce(long j, LongBinaryOperator longBinaryOperator) {
        return ((Long) evaluate(ReduceOps.makeLong(j, longBinaryOperator))).longValue();
    }

    @Override // java9.util.stream.LongStream
    public final OptionalLong reduce(LongBinaryOperator longBinaryOperator) {
        return (OptionalLong) evaluate(ReduceOps.makeLong(longBinaryOperator));
    }

    @Override // java9.util.stream.LongStream
    public final <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> objLongConsumer, BiConsumer<R, R> biConsumer) {
        Objects.requireNonNull(biConsumer);
        return (R) evaluate(ReduceOps.makeLong(supplier, objLongConsumer, LongPipeline$$Lambda$12.lambdaFactory$(biConsumer)));
    }

    static /* synthetic */ Object lambda$collect$4(BiConsumer biConsumer, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        return obj;
    }

    @Override // java9.util.stream.LongStream
    public final boolean anyMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java9.util.stream.LongStream
    public final boolean allMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java9.util.stream.LongStream
    public final boolean noneMatch(LongPredicate longPredicate) {
        return ((Boolean) evaluate(MatchOps.makeLong(longPredicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java9.util.stream.LongStream
    public final OptionalLong findFirst() {
        return (OptionalLong) evaluate(FindOps.makeLong(true));
    }

    @Override // java9.util.stream.LongStream
    public final OptionalLong findAny() {
        return (OptionalLong) evaluate(FindOps.makeLong(false));
    }

    @Override // java9.util.stream.LongStream
    public final long[] toArray() {
        return Nodes.flattenLong((Node.OfLong) evaluateToArrayNode(WhileOps.LONG_ARR_GEN)).asPrimitiveArray();
    }

    static class Head<E_IN> extends LongPipeline<E_IN> {
        @Override // java9.util.stream.LongPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Long> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Long> lazySpliterator2(Supplier<? extends Spliterator<Long>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) super.parallel();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) super.sequential();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Long> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        Head(Supplier<? extends Spliterator<Long>> supplier, int i, boolean z) {
            super(supplier, i, z);
        }

        Head(Spliterator<Long> spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.AbstractPipeline
        final Sink<E_IN> opWrapSink(int i, Sink<Long> sink) {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.LongStream
        public void forEach(LongConsumer longConsumer) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            } else {
                super.forEach(longConsumer);
            }
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.LongStream
        public void forEachOrdered(LongConsumer longConsumer) {
            if (!isParallel()) {
                LongPipeline.adapt(sourceStageSpliterator()).forEachRemaining(longConsumer);
            } else {
                super.forEachOrdered(longConsumer);
            }
        }
    }

    static abstract class StatelessOp<E_IN> extends LongPipeline<E_IN> {
        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return false;
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Long> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Long> lazySpliterator2(Supplier<? extends Spliterator<Long>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) super.parallel();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) super.sequential();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Long> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatelessOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }

    static abstract class StatefulOp<E_IN> extends LongPipeline<E_IN> {
        @Override // java9.util.stream.AbstractPipeline
        abstract <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Long[]> intFunction);

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return true;
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ Iterator<Long> iterator() {
            return super.iterator();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline
        /* JADX INFO: renamed from: lazySpliterator */
        /* bridge */ /* synthetic */ Spliterator<Long> lazySpliterator2(Supplier<? extends Spliterator<Long>> supplier) {
            return super.lazySpliterator2(supplier);
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream parallel() {
            return (LongStream) super.parallel();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ LongStream sequential() {
            return (LongStream) super.sequential();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.AbstractPipeline, java9.util.stream.BaseStream
        /* JADX INFO: renamed from: spliterator */
        public /* bridge */ /* synthetic */ Spliterator<Long> spliterator2() {
            return super.spliterator2();
        }

        @Override // java9.util.stream.LongPipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatefulOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }
}
