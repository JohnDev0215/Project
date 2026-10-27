package java9.util.stream;

import java.util.Comparator;
import java.util.Iterator;
import java9.util.Objects;
import java9.util.Optional;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;
import java9.util.function.BinaryOperator;
import java9.util.function.BinaryOperators;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.Function;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.LongConsumer;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.function.ToDoubleFunction;
import java9.util.function.ToIntFunction;
import java9.util.function.ToLongFunction;
import java9.util.stream.Collector;
import java9.util.stream.DoublePipeline;
import java9.util.stream.IntPipeline;
import java9.util.stream.LongPipeline;
import java9.util.stream.MatchOps;
import java9.util.stream.Node;
import java9.util.stream.Sink;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
abstract class ReferencePipeline<P_IN, P_OUT> extends AbstractPipeline<P_IN, P_OUT, Stream<P_OUT>> implements Stream<P_OUT> {
    ReferencePipeline(Supplier<? extends Spliterator<?>> supplier, int i, boolean z) {
        super(supplier, i, z);
    }

    ReferencePipeline(Spliterator<?> spliterator, int i, boolean z) {
        super(spliterator, i, z);
    }

    ReferencePipeline(AbstractPipeline<?, P_IN, ?> abstractPipeline, int i) {
        super(abstractPipeline, i);
    }

    @Override // java9.util.stream.AbstractPipeline
    final StreamShape getOutputShape() {
        return StreamShape.REFERENCE;
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN_> Node<P_OUT> evaluateToNode(PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN_> spliterator, boolean z, IntFunction<P_OUT[]> intFunction) {
        return Nodes.collect(pipelineHelper, spliterator, z, intFunction);
    }

    @Override // java9.util.stream.AbstractPipeline
    final <P_IN_> Spliterator<P_OUT> wrap(PipelineHelper<P_OUT> pipelineHelper, Supplier<Spliterator<P_IN_>> supplier, boolean z) {
        return new StreamSpliterators.WrappingSpliterator(pipelineHelper, supplier, z);
    }

    @Override // java9.util.stream.AbstractPipeline
    /* JADX INFO: renamed from: lazySpliterator */
    final Spliterator<P_OUT> lazySpliterator2(Supplier<? extends Spliterator<P_OUT>> supplier) {
        return new StreamSpliterators.DelegatingSpliterator(supplier);
    }

    @Override // java9.util.stream.AbstractPipeline
    final boolean forEachWithCancel(Spliterator<P_OUT> spliterator, Sink<P_OUT> sink) {
        boolean zCancellationRequested;
        do {
            zCancellationRequested = sink.cancellationRequested();
            if (zCancellationRequested) {
                break;
            }
        } while (spliterator.tryAdvance(sink));
        return zCancellationRequested;
    }

    @Override // java9.util.stream.AbstractPipeline, java9.util.stream.PipelineHelper
    final Node.Builder<P_OUT> makeNodeBuilder(long j, IntFunction<P_OUT[]> intFunction) {
        return Nodes.builder(j, intFunction);
    }

    @Override // java9.util.stream.BaseStream
    public final Iterator<P_OUT> iterator() {
        return Spliterators.iterator(spliterator2());
    }

    @Override // java9.util.stream.BaseStream
    public Stream<P_OUT> unordered() {
        return !isOrdered() ? this : new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_ORDERED) { // from class: java9.util.stream.ReferencePipeline.1
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<P_OUT> sink) {
                return sink;
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> filter(final Predicate<? super P_OUT> predicate) {
        Objects.requireNonNull(predicate);
        return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.ReferencePipeline.2
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<P_OUT> sink) {
                return new Sink.ChainedReference<P_OUT, P_OUT>(sink) { // from class: java9.util.stream.ReferencePipeline.2.1
                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) {
                        if (predicate.test(p_out)) {
                            this.downstream.accept((Object) p_out);
                        }
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final <R> Stream<R> map(final Function<? super P_OUT, ? extends R> function) {
        Objects.requireNonNull(function);
        return new StatelessOp<P_OUT, R>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.ReferencePipeline.3
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<R> sink) {
                return new Sink.ChainedReference<P_OUT, R>(sink) { // from class: java9.util.stream.ReferencePipeline.3.1
                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) {
                        this.downstream.accept((Object) function.apply(p_out));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final IntStream mapToInt(final ToIntFunction<? super P_OUT> toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return new IntPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.ReferencePipeline.4
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedReference<P_OUT, Integer>(sink) { // from class: java9.util.stream.ReferencePipeline.4.1
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
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) {
                        this.downstream.accept(toIntFunction.applyAsInt(p_out));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final LongStream mapToLong(final ToLongFunction<? super P_OUT> toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return new LongPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.ReferencePipeline.5
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedReference<P_OUT, Long>(sink) { // from class: java9.util.stream.ReferencePipeline.5.1
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
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) {
                        this.downstream.accept(toLongFunction.applyAsLong(p_out));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final DoubleStream mapToDouble(final ToDoubleFunction<? super P_OUT> toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return new DoublePipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT) { // from class: java9.util.stream.ReferencePipeline.6
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedReference<P_OUT, Double>(sink) { // from class: java9.util.stream.ReferencePipeline.6.1
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
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) {
                        this.downstream.accept(toDoubleFunction.applyAsDouble(p_out));
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final <R> Stream<R> flatMap(final Function<? super P_OUT, ? extends Stream<? extends R>> function) {
        Objects.requireNonNull(function);
        return new StatelessOp<P_OUT, R>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.ReferencePipeline.7
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<R> sink) {
                return new Sink.ChainedReference<P_OUT, R>(sink) { // from class: java9.util.stream.ReferencePipeline.7.1
                    boolean cancellationRequested;

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) throws Throwable {
                        Throwable th;
                        Stream stream;
                        try {
                            stream = (Stream) function.apply(p_out);
                            if (stream != null) {
                                try {
                                    if (!this.cancellationRequested) {
                                        stream.sequential().forEach(this.downstream);
                                    } else {
                                        Spliterator<T> spliterator = stream.sequential().spliterator2();
                                        while (!this.downstream.cancellationRequested() && spliterator.tryAdvance(this.downstream)) {
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (stream != null) {
                                        stream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (stream != null) {
                                stream.close();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            stream = null;
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final IntStream flatMapToInt(final Function<? super P_OUT, ? extends IntStream> function) {
        Objects.requireNonNull(function);
        return new IntPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.ReferencePipeline.8
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedReference<P_OUT, Integer>(sink) { // from class: java9.util.stream.ReferencePipeline.8.1
                    boolean cancellationRequested;
                    IntConsumer downstreamAsInt;

                    {
                        Sink<? super E_OUT> sink2 = this.downstream;
                        sink2.getClass();
                        this.downstreamAsInt = ReferencePipeline$8$1$$Lambda$1.lambdaFactory$(sink2);
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference failed for: r0v6, types: [java9.util.Spliterator$OfInt] */
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) throws Throwable {
                        Throwable th;
                        IntStream intStream;
                        try {
                            intStream = (IntStream) function.apply(p_out);
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

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final DoubleStream flatMapToDouble(final Function<? super P_OUT, ? extends DoubleStream> function) {
        Objects.requireNonNull(function);
        return new DoublePipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.ReferencePipeline.9
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedReference<P_OUT, Double>(sink) { // from class: java9.util.stream.ReferencePipeline.9.1
                    boolean cancellationRequested;
                    DoubleConsumer downstreamAsDouble;

                    {
                        Sink<? super E_OUT> sink2 = this.downstream;
                        sink2.getClass();
                        this.downstreamAsDouble = ReferencePipeline$9$1$$Lambda$1.lambdaFactory$(sink2);
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference failed for: r0v6, types: [java9.util.Spliterator$OfDouble] */
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) throws Throwable {
                        Throwable th;
                        DoubleStream doubleStream;
                        try {
                            doubleStream = (DoubleStream) function.apply(p_out);
                            if (doubleStream != null) {
                                try {
                                    if (!this.cancellationRequested) {
                                        doubleStream.sequential().forEach(this.downstreamAsDouble);
                                    } else {
                                        ?? Spliterator2 = doubleStream.sequential().spliterator2();
                                        while (!this.downstream.cancellationRequested() && Spliterator2.tryAdvance(this.downstreamAsDouble)) {
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (doubleStream != null) {
                                        doubleStream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (doubleStream != null) {
                                doubleStream.close();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            doubleStream = null;
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final LongStream flatMapToLong(final Function<? super P_OUT, ? extends LongStream> function) {
        Objects.requireNonNull(function);
        return new LongPipeline.StatelessOp<P_OUT>(this, StreamShape.REFERENCE, StreamOpFlag.NOT_SORTED | StreamOpFlag.NOT_DISTINCT | StreamOpFlag.NOT_SIZED) { // from class: java9.util.stream.ReferencePipeline.10
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedReference<P_OUT, Long>(sink) { // from class: java9.util.stream.ReferencePipeline.10.1
                    boolean cancellationRequested;
                    LongConsumer downstreamAsLong;

                    {
                        Sink<? super E_OUT> sink2 = this.downstream;
                        sink2.getClass();
                        this.downstreamAsLong = ReferencePipeline$10$1$$Lambda$1.lambdaFactory$(sink2);
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j) {
                        this.downstream.begin(-1L);
                    }

                    /* JADX WARN: Type inference failed for: r0v6, types: [java9.util.Spliterator$OfLong] */
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) throws Throwable {
                        Throwable th;
                        LongStream longStream;
                        try {
                            longStream = (LongStream) function.apply(p_out);
                            if (longStream != null) {
                                try {
                                    if (!this.cancellationRequested) {
                                        longStream.sequential().forEach(this.downstreamAsLong);
                                    } else {
                                        ?? Spliterator2 = longStream.sequential().spliterator2();
                                        while (!this.downstream.cancellationRequested() && Spliterator2.tryAdvance(this.downstreamAsLong)) {
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (longStream != null) {
                                        longStream.close();
                                    }
                                    throw th;
                                }
                            }
                            if (longStream != null) {
                                longStream.close();
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            longStream = null;
                        }
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        this.cancellationRequested = true;
                        return this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> peek(final Consumer<? super P_OUT> consumer) {
        Objects.requireNonNull(consumer);
        return new StatelessOp<P_OUT, P_OUT>(this, StreamShape.REFERENCE, 0) { // from class: java9.util.stream.ReferencePipeline.11
            @Override // java9.util.stream.AbstractPipeline
            Sink<P_OUT> opWrapSink(int i, Sink<P_OUT> sink) {
                return new Sink.ChainedReference<P_OUT, P_OUT>(sink) { // from class: java9.util.stream.ReferencePipeline.11.1
                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.function.Consumer
                    public void accept(P_OUT p_out) {
                        consumer.accept(p_out);
                        this.downstream.accept((Object) p_out);
                    }
                };
            }
        };
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> distinct() {
        return DistinctOps.makeRef(this);
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> sorted() {
        return SortedOps.makeRef(this);
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> sorted(Comparator<? super P_OUT> comparator) {
        return SortedOps.makeRef(this, comparator);
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> limit(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(Long.toString(j));
        }
        return SliceOps.makeRef(this, 0L, j);
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : SliceOps.makeRef(this, j, -1L);
        }
        throw new IllegalArgumentException(Long.toString(j));
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> takeWhile(Predicate<? super P_OUT> predicate) {
        return WhileOps.makeTakeWhileRef(this, predicate);
    }

    @Override // java9.util.stream.Stream
    public final Stream<P_OUT> dropWhile(Predicate<? super P_OUT> predicate) {
        return WhileOps.makeDropWhileRef(this, predicate);
    }

    @Override // java9.util.stream.Stream
    public void forEach(Consumer<? super P_OUT> consumer) {
        evaluate(ForEachOps.makeRef(consumer, false));
    }

    @Override // java9.util.stream.Stream
    public void forEachOrdered(Consumer<? super P_OUT> consumer) {
        evaluate(ForEachOps.makeRef(consumer, true));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java9.util.stream.Stream
    public final <A> A[] toArray(IntFunction<A[]> intFunction) {
        return (A[]) Nodes.flatten(evaluateToArrayNode(intFunction), intFunction).asArray(intFunction);
    }

    static /* synthetic */ Object[] lambda$toArray$0(int i) {
        return new Object[i];
    }

    @Override // java9.util.stream.Stream
    public final Object[] toArray() {
        return toArray(ReferencePipeline$$Lambda$1.instance);
    }

    @Override // java9.util.stream.Stream
    public final boolean anyMatch(Predicate<? super P_OUT> predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ANY))).booleanValue();
    }

    @Override // java9.util.stream.Stream
    public final boolean allMatch(Predicate<? super P_OUT> predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.ALL))).booleanValue();
    }

    @Override // java9.util.stream.Stream
    public final boolean noneMatch(Predicate<? super P_OUT> predicate) {
        return ((Boolean) evaluate(MatchOps.makeRef(predicate, MatchOps.MatchKind.NONE))).booleanValue();
    }

    @Override // java9.util.stream.Stream
    public final Optional<P_OUT> findFirst() {
        return (Optional) evaluate(FindOps.makeRef(true));
    }

    @Override // java9.util.stream.Stream
    public final Optional<P_OUT> findAny() {
        return (Optional) evaluate(FindOps.makeRef(false));
    }

    @Override // java9.util.stream.Stream
    public final P_OUT reduce(P_OUT p_out, BinaryOperator<P_OUT> binaryOperator) {
        return (P_OUT) evaluate(ReduceOps.makeRef(p_out, binaryOperator, binaryOperator));
    }

    @Override // java9.util.stream.Stream
    public final Optional<P_OUT> reduce(BinaryOperator<P_OUT> binaryOperator) {
        return (Optional) evaluate(ReduceOps.makeRef(binaryOperator));
    }

    @Override // java9.util.stream.Stream
    public final <R> R reduce(R r, BiFunction<R, ? super P_OUT, R> biFunction, BinaryOperator<R> binaryOperator) {
        return (R) evaluate(ReduceOps.makeRef(r, (BiFunction<R, ? super T, R>) biFunction, binaryOperator));
    }

    @Override // java9.util.stream.Stream
    public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
        A a;
        if (isParallel() && collector.characteristics().contains(Collector.Characteristics.CONCURRENT) && (!isOrdered() || collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
            a = collector.supplier().get();
            forEach(ReferencePipeline$$Lambda$2.lambdaFactory$(collector.accumulator(), a));
        } else {
            a = (R) evaluate(ReduceOps.makeRef(collector));
        }
        return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH) ? a : (R) collector.finisher().apply(a);
    }

    @Override // java9.util.stream.Stream
    public final <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super P_OUT> biConsumer, BiConsumer<R, R> biConsumer2) {
        return (R) evaluate(ReduceOps.makeRef(supplier, biConsumer, biConsumer2));
    }

    @Override // java9.util.stream.Stream
    public final Optional<P_OUT> max(Comparator<? super P_OUT> comparator) {
        return reduce(BinaryOperators.maxBy(comparator));
    }

    @Override // java9.util.stream.Stream
    public final Optional<P_OUT> min(Comparator<? super P_OUT> comparator) {
        return reduce(BinaryOperators.minBy(comparator));
    }

    @Override // java9.util.stream.Stream
    public final long count() {
        return ((Long) evaluate(ReduceOps.makeRefCounting())).longValue();
    }

    static class Head<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        @Override // java9.util.stream.ReferencePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        Head(Supplier<? extends Spliterator<?>> supplier, int i, boolean z) {
            super(supplier, i, z);
        }

        Head(Spliterator<?> spliterator, int i, boolean z) {
            super(spliterator, i, z);
        }

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            throw new UnsupportedOperationException();
        }

        @Override // java9.util.stream.AbstractPipeline
        final Sink<E_IN> opWrapSink(int i, Sink<E_OUT> sink) {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.ReferencePipeline, java9.util.stream.Stream
        public void forEach(Consumer<? super E_OUT> consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                super.forEach(consumer);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.ReferencePipeline, java9.util.stream.Stream
        public void forEachOrdered(Consumer<? super E_OUT> consumer) {
            if (!isParallel()) {
                sourceStageSpliterator().forEachRemaining(consumer);
            } else {
                super.forEachOrdered(consumer);
            }
        }
    }

    static abstract class StatelessOp<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return false;
        }

        @Override // java9.util.stream.ReferencePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatelessOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }

    static abstract class StatefulOp<E_IN, E_OUT> extends ReferencePipeline<E_IN, E_OUT> {
        @Override // java9.util.stream.AbstractPipeline
        abstract <P_IN> Node<E_OUT> opEvaluateParallel(PipelineHelper<E_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<E_OUT[]> intFunction);

        @Override // java9.util.stream.AbstractPipeline
        final boolean opIsStateful() {
            return true;
        }

        @Override // java9.util.stream.ReferencePipeline, java9.util.stream.BaseStream
        public /* bridge */ /* synthetic */ BaseStream unordered() {
            return super.unordered();
        }

        StatefulOp(AbstractPipeline<?, E_IN, ?> abstractPipeline, StreamShape streamShape, int i) {
            super(abstractPipeline, i);
        }
    }
}
