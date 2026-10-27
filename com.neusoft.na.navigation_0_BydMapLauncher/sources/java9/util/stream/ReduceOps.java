package java9.util.stream;

import java9.util.Objects;
import java9.util.Optional;
import java9.util.OptionalDouble;
import java9.util.OptionalInt;
import java9.util.OptionalLong;
import java9.util.Spliterator;
import java9.util.concurrent.CountedCompleter;
import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;
import java9.util.function.BinaryOperator;
import java9.util.function.DoubleBinaryOperator;
import java9.util.function.IntBinaryOperator;
import java9.util.function.LongBinaryOperator;
import java9.util.function.ObjDoubleConsumer;
import java9.util.function.ObjIntConsumer;
import java9.util.function.ObjLongConsumer;
import java9.util.function.Supplier;
import java9.util.stream.Collector;
import java9.util.stream.Sink;
import java9.util.stream.SinkDefaults;

/* JADX INFO: loaded from: classes3.dex */
final class ReduceOps {

    private interface AccumulatingSink<T, R, K extends AccumulatingSink<T, R, K>> extends TerminalSink<T, R> {
        void combine(K k);
    }

    private ReduceOps() {
    }

    public static <T, U> TerminalOp<T, U> makeRef(final U u, final BiFunction<U, ? super T, U> biFunction, final BinaryOperator<U> binaryOperator) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp<T, U, C1ReducingSink>(StreamShape.REFERENCE) { // from class: java9.util.stream.ReduceOps.1
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C1ReducingSink makeSink() {
                return new C1ReducingSink(u, biFunction, binaryOperator);
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [T, U] */
    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$1ReducingSink, reason: invalid class name */
    class C1ReducingSink<T, U> extends Box<U> implements AccumulatingSink<T, U, C1ReducingSink> {
        final /* synthetic */ BinaryOperator val$combiner;
        final /* synthetic */ BiFunction val$reducer;
        final /* synthetic */ Object val$seed;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C1ReducingSink(Object obj, BiFunction biFunction, BinaryOperator binaryOperator) {
            this.val$seed = obj;
            this.val$reducer = biFunction;
            this.val$combiner = binaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = (U) this.val$seed;
        }

        @Override // java9.util.function.Consumer
        public void accept(T t) {
            this.state = (U) this.val$reducer.apply(this.state, t);
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

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C1ReducingSink c1ReducingSink) {
            this.state = (U) this.val$combiner.apply(this.state, c1ReducingSink.state);
        }
    }

    public static <T> TerminalOp<T, Optional<T>> makeRef(final BinaryOperator<T> binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp<T, Optional<T>, C2ReducingSink>(StreamShape.REFERENCE) { // from class: java9.util.stream.ReduceOps.2
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C2ReducingSink makeSink() {
                final BinaryOperator binaryOperator2 = binaryOperator;
                return new AccumulatingSink<T, Optional<T>, C2ReducingSink>() { // from class: java9.util.stream.ReduceOps.2ReducingSink
                    private boolean empty;
                    private T state;

                    @Override // java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return false;
                    }

                    @Override // java9.util.stream.Sink
                    public void end() {
                    }

                    @Override // java9.util.stream.Sink
                    public void begin(long j) {
                        this.empty = true;
                        this.state = null;
                    }

                    @Override // java9.util.function.Consumer
                    public void accept(T t) {
                        if (this.empty) {
                            this.empty = false;
                            this.state = t;
                        } else {
                            this.state = binaryOperator2.apply(this.state, t);
                        }
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

                    @Override // java9.util.function.Supplier
                    public Optional<T> get() {
                        return this.empty ? Optional.empty() : Optional.of(this.state);
                    }

                    @Override // java9.util.stream.ReduceOps.AccumulatingSink
                    public void combine(C2ReducingSink c2ReducingSink) {
                        if (c2ReducingSink.empty) {
                            return;
                        }
                        accept(c2ReducingSink.state);
                    }
                };
            }
        };
    }

    public static <T, I> TerminalOp<T, I> makeRef(final Collector<? super T, I, ?> collector) {
        final Supplier supplier = ((Collector) Objects.requireNonNull(collector)).supplier();
        final BiConsumer<I, ? super T> biConsumerAccumulator = collector.accumulator();
        final BinaryOperator<I> binaryOperatorCombiner = collector.combiner();
        return new ReduceOp<T, I, C3ReducingSink>(StreamShape.REFERENCE) { // from class: java9.util.stream.ReduceOps.3
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C3ReducingSink makeSink() {
                return new C3ReducingSink(supplier, biConsumerAccumulator, binaryOperatorCombiner);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public int getOpFlags() {
                if (collector.characteristics().contains(Collector.Characteristics.UNORDERED)) {
                    return StreamOpFlag.NOT_ORDERED;
                }
                return 0;
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [T, I] */
    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$3ReducingSink, reason: invalid class name */
    class C3ReducingSink<I, T> extends Box<I> implements AccumulatingSink<T, I, C3ReducingSink> {
        final /* synthetic */ BiConsumer val$accumulator;
        final /* synthetic */ BinaryOperator val$combiner;
        final /* synthetic */ Supplier val$supplier;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C3ReducingSink(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = biConsumer;
            this.val$combiner = binaryOperator;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java9.util.function.Consumer
        public void accept(T t) {
            this.val$accumulator.accept(this.state, t);
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

        /* JADX WARN: Type inference failed for: r3v2, types: [U, java.lang.Object] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C3ReducingSink c3ReducingSink) {
            this.state = this.val$combiner.apply(this.state, c3ReducingSink.state);
        }
    }

    public static <T, R> TerminalOp<T, R> makeRef(final Supplier<R> supplier, final BiConsumer<R, ? super T> biConsumer, final BiConsumer<R, R> biConsumer2) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return new ReduceOp<T, R, C4ReducingSink>(StreamShape.REFERENCE) { // from class: java9.util.stream.ReduceOps.4
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C4ReducingSink makeSink() {
                return new C4ReducingSink(supplier, biConsumer, biConsumer2);
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [R, T] */
    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$4ReducingSink, reason: invalid class name */
    class C4ReducingSink<R, T> extends Box<R> implements AccumulatingSink<T, R, C4ReducingSink> {
        final /* synthetic */ BiConsumer val$accumulator;
        final /* synthetic */ BiConsumer val$reducer;
        final /* synthetic */ Supplier val$seedFactory;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C4ReducingSink(Supplier supplier, BiConsumer biConsumer, BiConsumer biConsumer2) {
            this.val$seedFactory = supplier;
            this.val$accumulator = biConsumer;
            this.val$reducer = biConsumer2;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$seedFactory.get();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java9.util.function.Consumer
        public void accept(T t) {
            this.val$accumulator.accept(this.state, t);
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

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C4ReducingSink c4ReducingSink) {
            this.val$reducer.accept(this.state, c4ReducingSink.state);
        }
    }

    public static <T> TerminalOp<T, Long> makeRefCounting() {
        return new ReduceOp<T, Long, CountingSink<T>>(StreamShape.REFERENCE) { // from class: java9.util.stream.ReduceOps.5
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public CountingSink<T> makeSink() {
                return new CountingSink.OfRef();
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateSequential(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateSequential((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateParallel((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }
        };
    }

    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$5ReducingSink, reason: invalid class name */
    class C5ReducingSink implements AccumulatingSink<Integer, Integer, C5ReducingSink>, Sink.OfInt {
        private int state;
        final /* synthetic */ int val$identity;
        final /* synthetic */ IntBinaryOperator val$operator;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C5ReducingSink(int i, IntBinaryOperator intBinaryOperator) {
            this.val$identity = i;
            this.val$operator = intBinaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$identity;
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            this.state = this.val$operator.applyAsInt(this.state, i);
        }

        @Override // java9.util.function.Consumer
        public void accept(Integer num) {
            SinkDefaults.OfInt.accept(this, num);
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Supplier
        public Integer get() {
            return Integer.valueOf(this.state);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C5ReducingSink c5ReducingSink) {
            accept(c5ReducingSink.state);
        }
    }

    public static TerminalOp<Integer, Integer> makeInt(final int i, final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new ReduceOp<Integer, Integer, C5ReducingSink>(StreamShape.INT_VALUE) { // from class: java9.util.stream.ReduceOps.6
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C5ReducingSink makeSink() {
                return new C5ReducingSink(i, intBinaryOperator);
            }
        };
    }

    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$6ReducingSink, reason: invalid class name */
    class C6ReducingSink implements AccumulatingSink<Integer, OptionalInt, C6ReducingSink>, Sink.OfInt {
        private boolean empty;
        private int state;
        final /* synthetic */ IntBinaryOperator val$operator;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C6ReducingSink(IntBinaryOperator intBinaryOperator) {
            this.val$operator = intBinaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.empty = true;
            this.state = 0;
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            if (this.empty) {
                this.empty = false;
                this.state = i;
            } else {
                this.state = this.val$operator.applyAsInt(this.state, i);
            }
        }

        @Override // java9.util.function.Consumer
        public void accept(Integer num) {
            SinkDefaults.OfInt.accept(this, num);
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Supplier
        public OptionalInt get() {
            return this.empty ? OptionalInt.empty() : OptionalInt.of(this.state);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C6ReducingSink c6ReducingSink) {
            if (c6ReducingSink.empty) {
                return;
            }
            accept(c6ReducingSink.state);
        }
    }

    public static TerminalOp<Integer, OptionalInt> makeInt(final IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        return new ReduceOp<Integer, OptionalInt, C6ReducingSink>(StreamShape.INT_VALUE) { // from class: java9.util.stream.ReduceOps.7
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C6ReducingSink makeSink() {
                return new C6ReducingSink(intBinaryOperator);
            }
        };
    }

    public static <R> TerminalOp<Integer, R> makeInt(final Supplier<R> supplier, final ObjIntConsumer<R> objIntConsumer, final BinaryOperator<R> binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objIntConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp<Integer, R, C7ReducingSink>(StreamShape.INT_VALUE) { // from class: java9.util.stream.ReduceOps.8
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C7ReducingSink makeSink() {
                return new C7ReducingSink(supplier, objIntConsumer, binaryOperator);
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$7ReducingSink, reason: invalid class name */
    class C7ReducingSink<R> extends Box<R> implements AccumulatingSink<Integer, R, C7ReducingSink>, Sink.OfInt {
        final /* synthetic */ ObjIntConsumer val$accumulator;
        final /* synthetic */ BinaryOperator val$combiner;
        final /* synthetic */ Supplier val$supplier;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C7ReducingSink(Supplier supplier, ObjIntConsumer objIntConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = objIntConsumer;
            this.val$combiner = binaryOperator;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            this.val$accumulator.accept(this.state, i);
        }

        @Override // java9.util.function.Consumer
        public void accept(Integer num) {
            SinkDefaults.OfInt.accept(this, num);
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        /* JADX WARN: Type inference failed for: r3v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C7ReducingSink c7ReducingSink) {
            this.state = this.val$combiner.apply(this.state, c7ReducingSink.state);
        }
    }

    public static TerminalOp<Integer, Long> makeIntCounting() {
        return new ReduceOp<Integer, Long, CountingSink<Integer>>(StreamShape.INT_VALUE) { // from class: java9.util.stream.ReduceOps.9
            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return evaluateParallel((PipelineHelper<Integer>) pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return evaluateSequential((PipelineHelper<Integer>) pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp
            public CountingSink<Integer> makeSink() {
                return new CountingSink.OfInt();
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateSequential(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateSequential((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateParallel(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateParallel((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }
        };
    }

    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$8ReducingSink, reason: invalid class name */
    class C8ReducingSink implements AccumulatingSink<Long, Long, C8ReducingSink>, Sink.OfLong {
        private long state;
        final /* synthetic */ long val$identity;
        final /* synthetic */ LongBinaryOperator val$operator;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C8ReducingSink(long j, LongBinaryOperator longBinaryOperator) {
            this.val$identity = j;
            this.val$operator = longBinaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$identity;
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            this.state = this.val$operator.applyAsLong(this.state, j);
        }

        @Override // java9.util.function.Consumer
        public void accept(Long l) {
            SinkDefaults.OfLong.accept(this, l);
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Supplier
        public Long get() {
            return Long.valueOf(this.state);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C8ReducingSink c8ReducingSink) {
            accept(c8ReducingSink.state);
        }
    }

    public static TerminalOp<Long, Long> makeLong(final long j, final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new ReduceOp<Long, Long, C8ReducingSink>(StreamShape.LONG_VALUE) { // from class: java9.util.stream.ReduceOps.10
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C8ReducingSink makeSink() {
                return new C8ReducingSink(j, longBinaryOperator);
            }
        };
    }

    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$9ReducingSink, reason: invalid class name */
    class C9ReducingSink implements AccumulatingSink<Long, OptionalLong, C9ReducingSink>, Sink.OfLong {
        private boolean empty;
        private long state;
        final /* synthetic */ LongBinaryOperator val$operator;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C9ReducingSink(LongBinaryOperator longBinaryOperator) {
            this.val$operator = longBinaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.empty = true;
            this.state = 0L;
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            if (this.empty) {
                this.empty = false;
                this.state = j;
            } else {
                this.state = this.val$operator.applyAsLong(this.state, j);
            }
        }

        @Override // java9.util.function.Consumer
        public void accept(Long l) {
            SinkDefaults.OfLong.accept(this, l);
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Supplier
        public OptionalLong get() {
            return this.empty ? OptionalLong.empty() : OptionalLong.of(this.state);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C9ReducingSink c9ReducingSink) {
            if (c9ReducingSink.empty) {
                return;
            }
            accept(c9ReducingSink.state);
        }
    }

    public static TerminalOp<Long, OptionalLong> makeLong(final LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        return new ReduceOp<Long, OptionalLong, C9ReducingSink>(StreamShape.LONG_VALUE) { // from class: java9.util.stream.ReduceOps.11
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C9ReducingSink makeSink() {
                return new C9ReducingSink(longBinaryOperator);
            }
        };
    }

    public static <R> TerminalOp<Long, R> makeLong(final Supplier<R> supplier, final ObjLongConsumer<R> objLongConsumer, final BinaryOperator<R> binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objLongConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp<Long, R, C10ReducingSink>(StreamShape.LONG_VALUE) { // from class: java9.util.stream.ReduceOps.12
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C10ReducingSink makeSink() {
                return new C10ReducingSink(supplier, objLongConsumer, binaryOperator);
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$10ReducingSink, reason: invalid class name */
    class C10ReducingSink<R> extends Box<R> implements AccumulatingSink<Long, R, C10ReducingSink>, Sink.OfLong {
        final /* synthetic */ ObjLongConsumer val$accumulator;
        final /* synthetic */ BinaryOperator val$combiner;
        final /* synthetic */ Supplier val$supplier;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C10ReducingSink(Supplier supplier, ObjLongConsumer objLongConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = objLongConsumer;
            this.val$combiner = binaryOperator;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            this.val$accumulator.accept(this.state, j);
        }

        @Override // java9.util.function.Consumer
        public void accept(Long l) {
            SinkDefaults.OfLong.accept(this, l);
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        /* JADX WARN: Type inference failed for: r3v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C10ReducingSink c10ReducingSink) {
            this.state = this.val$combiner.apply(this.state, c10ReducingSink.state);
        }
    }

    public static TerminalOp<Long, Long> makeLongCounting() {
        return new ReduceOp<Long, Long, CountingSink<Long>>(StreamShape.LONG_VALUE) { // from class: java9.util.stream.ReduceOps.13
            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return evaluateParallel((PipelineHelper<Long>) pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return evaluateSequential((PipelineHelper<Long>) pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp
            public CountingSink<Long> makeSink() {
                return new CountingSink.OfLong();
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateSequential(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateSequential((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateParallel(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateParallel((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }
        };
    }

    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$11ReducingSink, reason: invalid class name */
    class C11ReducingSink implements AccumulatingSink<Double, Double, C11ReducingSink>, Sink.OfDouble {
        private double state;
        final /* synthetic */ double val$identity;
        final /* synthetic */ DoubleBinaryOperator val$operator;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C11ReducingSink(double d, DoubleBinaryOperator doubleBinaryOperator) {
            this.val$identity = d;
            this.val$operator = doubleBinaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$identity;
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            this.state = this.val$operator.applyAsDouble(this.state, d);
        }

        @Override // java9.util.function.Consumer
        public void accept(Double d) {
            SinkDefaults.OfDouble.accept(this, d);
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Supplier
        public Double get() {
            return Double.valueOf(this.state);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C11ReducingSink c11ReducingSink) {
            accept(c11ReducingSink.state);
        }
    }

    public static TerminalOp<Double, Double> makeDouble(final double d, final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new ReduceOp<Double, Double, C11ReducingSink>(StreamShape.DOUBLE_VALUE) { // from class: java9.util.stream.ReduceOps.14
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C11ReducingSink makeSink() {
                return new C11ReducingSink(d, doubleBinaryOperator);
            }
        };
    }

    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$12ReducingSink, reason: invalid class name */
    class C12ReducingSink implements AccumulatingSink<Double, OptionalDouble, C12ReducingSink>, Sink.OfDouble {
        private boolean empty;
        private double state;
        final /* synthetic */ DoubleBinaryOperator val$operator;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C12ReducingSink(DoubleBinaryOperator doubleBinaryOperator) {
            this.val$operator = doubleBinaryOperator;
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.empty = true;
            this.state = 0.0d;
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            if (this.empty) {
                this.empty = false;
                this.state = d;
            } else {
                this.state = this.val$operator.applyAsDouble(this.state, d);
            }
        }

        @Override // java9.util.function.Consumer
        public void accept(Double d) {
            SinkDefaults.OfDouble.accept(this, d);
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.function.Supplier
        public OptionalDouble get() {
            return this.empty ? OptionalDouble.empty() : OptionalDouble.of(this.state);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C12ReducingSink c12ReducingSink) {
            if (c12ReducingSink.empty) {
                return;
            }
            accept(c12ReducingSink.state);
        }
    }

    public static TerminalOp<Double, OptionalDouble> makeDouble(final DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        return new ReduceOp<Double, OptionalDouble, C12ReducingSink>(StreamShape.DOUBLE_VALUE) { // from class: java9.util.stream.ReduceOps.15
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C12ReducingSink makeSink() {
                return new C12ReducingSink(doubleBinaryOperator);
            }
        };
    }

    public static <R> TerminalOp<Double, R> makeDouble(final Supplier<R> supplier, final ObjDoubleConsumer<R> objDoubleConsumer, final BinaryOperator<R> binaryOperator) {
        Objects.requireNonNull(supplier);
        Objects.requireNonNull(objDoubleConsumer);
        Objects.requireNonNull(binaryOperator);
        return new ReduceOp<Double, R, C13ReducingSink>(StreamShape.DOUBLE_VALUE) { // from class: java9.util.stream.ReduceOps.16
            @Override // java9.util.stream.ReduceOps.ReduceOp
            public C13ReducingSink makeSink() {
                return new C13ReducingSink(supplier, objDoubleConsumer, binaryOperator);
            }
        };
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: java9.util.stream.ReduceOps$13ReducingSink, reason: invalid class name */
    class C13ReducingSink<R> extends Box<R> implements AccumulatingSink<Double, R, C13ReducingSink>, Sink.OfDouble {
        final /* synthetic */ ObjDoubleConsumer val$accumulator;
        final /* synthetic */ BinaryOperator val$combiner;
        final /* synthetic */ Supplier val$supplier;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        C13ReducingSink(Supplier supplier, ObjDoubleConsumer objDoubleConsumer, BinaryOperator binaryOperator) {
            this.val$supplier = supplier;
            this.val$accumulator = objDoubleConsumer;
            this.val$combiner = binaryOperator;
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.state = this.val$supplier.get();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            this.val$accumulator.accept(this.state, d);
        }

        @Override // java9.util.function.Consumer
        public void accept(Double d) {
            SinkDefaults.OfDouble.accept(this, d);
        }

        /* JADX WARN: Type inference failed for: r3v2, types: [U, java.lang.Object] */
        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(C13ReducingSink c13ReducingSink) {
            this.state = this.val$combiner.apply(this.state, c13ReducingSink.state);
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }
    }

    public static TerminalOp<Double, Long> makeDoubleCounting() {
        return new ReduceOp<Double, Long, CountingSink<Double>>(StreamShape.DOUBLE_VALUE) { // from class: java9.util.stream.ReduceOps.17
            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Object evaluateParallel(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return evaluateParallel((PipelineHelper<Double>) pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public /* bridge */ /* synthetic */ Object evaluateSequential(PipelineHelper pipelineHelper, Spliterator spliterator) {
                return evaluateSequential((PipelineHelper<Double>) pipelineHelper, spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp
            public CountingSink<Double> makeSink() {
                return new CountingSink.OfDouble();
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateSequential(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateSequential((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public <P_IN> Long evaluateParallel(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator) {
                if (StreamOpFlag.SIZED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Long.valueOf(spliterator.getExactSizeIfKnown());
                }
                return (Long) super.evaluateParallel((PipelineHelper) pipelineHelper, (Spliterator) spliterator);
            }

            @Override // java9.util.stream.ReduceOps.ReduceOp, java9.util.stream.TerminalOp
            public int getOpFlags() {
                return StreamOpFlag.NOT_ORDERED;
            }
        };
    }

    static abstract class CountingSink<T> extends Box<Long> implements AccumulatingSink<T, Long, CountingSink<T>> {
        long count;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        CountingSink() {
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            this.count = 0L;
        }

        @Override // java9.util.stream.ReduceOps.Box, java9.util.function.Supplier
        public Long get() {
            return Long.valueOf(this.count);
        }

        @Override // java9.util.stream.ReduceOps.AccumulatingSink
        public void combine(CountingSink<T> countingSink) {
            this.count += countingSink.count;
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

        static final class OfRef<T> extends CountingSink<T> {
            OfRef() {
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.AccumulatingSink
            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.Box, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            @Override // java9.util.function.Consumer
            public void accept(T t) {
                this.count++;
            }
        }

        static final class OfInt extends CountingSink<Integer> implements Sink.OfInt {
            OfInt() {
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.AccumulatingSink
            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.Box, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.Sink
            public void accept(int i) {
                this.count++;
            }

            @Override // java9.util.function.Consumer
            public void accept(Integer num) {
                SinkDefaults.OfInt.accept(this, num);
            }
        }

        static final class OfLong extends CountingSink<Long> implements Sink.OfLong {
            OfLong() {
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.AccumulatingSink
            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.Box, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.Sink
            public void accept(long j) {
                this.count++;
            }

            @Override // java9.util.function.Consumer
            public void accept(Long l) {
                SinkDefaults.OfLong.accept(this, l);
            }
        }

        static final class OfDouble extends CountingSink<Double> implements Sink.OfDouble {
            OfDouble() {
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.AccumulatingSink
            public /* bridge */ /* synthetic */ void combine(AccumulatingSink accumulatingSink) {
                super.combine((CountingSink) accumulatingSink);
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.ReduceOps.Box, java9.util.function.Supplier
            public /* bridge */ /* synthetic */ Object get() {
                return super.get();
            }

            @Override // java9.util.stream.ReduceOps.CountingSink, java9.util.stream.Sink
            public void accept(double d) {
                this.count++;
            }

            @Override // java9.util.function.Consumer
            public void accept(Double d) {
                SinkDefaults.OfDouble.accept(this, d);
            }
        }
    }

    private static abstract class Box<U> {
        U state;

        Box() {
        }

        public U get() {
            return this.state;
        }
    }

    private static abstract class ReduceOp<T, R, S extends AccumulatingSink<T, R, S>> implements TerminalOp<T, R> {
        private final StreamShape inputShape;

        @Override // java9.util.stream.TerminalOp
        public int getOpFlags() {
            return 0;
        }

        public abstract S makeSink();

        ReduceOp(StreamShape streamShape) {
            this.inputShape = streamShape;
        }

        @Override // java9.util.stream.TerminalOp
        public StreamShape inputShape() {
            return this.inputShape;
        }

        @Override // java9.util.stream.TerminalOp
        public <P_IN> R evaluateSequential(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
            return ((AccumulatingSink) pipelineHelper.wrapAndCopyInto(makeSink(), (Spliterator) spliterator)).get();
        }

        @Override // java9.util.stream.TerminalOp
        public <P_IN> R evaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
            return ((AccumulatingSink) new ReduceTask(this, pipelineHelper, spliterator).invoke()).get();
        }
    }

    private static final class ReduceTask<P_IN, P_OUT, R, S extends AccumulatingSink<P_OUT, R, S>> extends AbstractTask<P_IN, P_OUT, S, ReduceTask<P_IN, P_OUT, R, S>> {
        private final ReduceOp<P_OUT, R, S> op;

        ReduceTask(ReduceOp<P_OUT, R, S> reduceOp, PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator) {
            super(pipelineHelper, spliterator);
            this.op = reduceOp;
        }

        ReduceTask(ReduceTask<P_IN, P_OUT, R, S> reduceTask, Spliterator<P_IN> spliterator) {
            super(reduceTask, spliterator);
            this.op = reduceTask.op;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public ReduceTask<P_IN, P_OUT, R, S> makeChild(Spliterator<P_IN> spliterator) {
            return new ReduceTask<>(this, spliterator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public S doLeaf() {
            return (S) this.helper.wrapAndCopyInto(this.op.makeSink(), (Spliterator) this.spliterator);
        }

        @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
        public void onCompletion(CountedCompleter<?> countedCompleter) {
            if (!isLeaf()) {
                AccumulatingSink accumulatingSink = (AccumulatingSink) ((ReduceTask) this.leftChild).getLocalResult();
                accumulatingSink.combine((AccumulatingSink) ((ReduceTask) this.rightChild).getLocalResult());
                setLocalResult(accumulatingSink);
            }
            super.onCompletion(countedCompleter);
        }
    }
}
