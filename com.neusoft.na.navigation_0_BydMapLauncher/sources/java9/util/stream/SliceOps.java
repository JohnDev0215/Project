package java9.util.stream;

import java9.util.Spliterator;
import java9.util.concurrent.CountedCompleter;
import java9.util.function.IntFunction;
import java9.util.stream.DoublePipeline;
import java9.util.stream.IntPipeline;
import java9.util.stream.LongPipeline;
import java9.util.stream.Node;
import java9.util.stream.ReferencePipeline;
import java9.util.stream.Sink;
import java9.util.stream.StreamSpliterators;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
final class SliceOps {
    /* JADX INFO: Access modifiers changed from: private */
    public static long calcSliceFence(long j, long j2) {
        long j3 = j2 >= 0 ? j + j2 : Long.MAX_VALUE;
        return j3 >= 0 ? j3 : LongCompanionObject.MAX_VALUE;
    }

    private SliceOps() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long calcSize(long j, long j2, long j3) {
        if (j >= 0) {
            return Math.max(-1L, Math.min(j - j2, j3));
        }
        return -1L;
    }

    /* JADX INFO: renamed from: java9.util.stream.SliceOps$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$java8$util$stream$StreamShape;

        static {
            int[] iArr = new int[StreamShape.values().length];
            $SwitchMap$java8$util$stream$StreamShape = iArr;
            try {
                iArr[StreamShape.REFERENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java8$util$stream$StreamShape[StreamShape.INT_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java8$util$stream$StreamShape[StreamShape.LONG_VALUE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java8$util$stream$StreamShape[StreamShape.DOUBLE_VALUE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <P_IN> Spliterator<P_IN> sliceSpliterator(StreamShape streamShape, Spliterator<P_IN> spliterator, long j, long j2) {
        long jCalcSliceFence = calcSliceFence(j, j2);
        int i = AnonymousClass5.$SwitchMap$java8$util$stream$StreamShape[streamShape.ordinal()];
        if (i == 1) {
            return new StreamSpliterators.SliceSpliterator.OfRef(spliterator, j, jCalcSliceFence);
        }
        if (i == 2) {
            return new StreamSpliterators.SliceSpliterator.OfInt((Spliterator.OfInt) spliterator, j, jCalcSliceFence);
        }
        if (i == 3) {
            return new StreamSpliterators.SliceSpliterator.OfLong((Spliterator.OfLong) spliterator, j, jCalcSliceFence);
        }
        if (i == 4) {
            return new StreamSpliterators.SliceSpliterator.OfDouble((Spliterator.OfDouble) spliterator, j, jCalcSliceFence);
        }
        throw new IllegalStateException("Unknown shape " + streamShape);
    }

    public static <T> Stream<T> makeRef(AbstractPipeline<?, T, ?> abstractPipeline, final long j, final long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new ReferencePipeline.StatefulOp<T, T>(abstractPipeline, StreamShape.REFERENCE, flags(j2)) { // from class: java9.util.stream.SliceOps.1
            Spliterator<T> unorderedSkipLimitSpliterator(Spliterator<T> spliterator, long j3, long j4, long j5) {
                long j6;
                long jMin;
                if (j3 <= j5) {
                    long j7 = j5 - j3;
                    jMin = j4 >= 0 ? Math.min(j4, j7) : j7;
                    j6 = 0;
                } else {
                    j6 = j3;
                    jMin = j4;
                }
                return new StreamSpliterators.UnorderedSliceSpliterator.OfRef(spliterator, j6, jMin);
            }

            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<T> opEvaluateParallelLazy(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    Spliterator<T> spliteratorWrapSpliterator = pipelineHelper.wrapSpliterator(spliterator);
                    long j3 = j;
                    return new StreamSpliterators.SliceSpliterator.OfRef(spliteratorWrapSpliterator, j3, SliceOps.calcSliceFence(j3, j2));
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator(pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown);
                }
                return new SliceTask(this, pipelineHelper, spliterator, Nodes.castingArray(), j, j2).invoke().spliterator();
            }

            @Override // java9.util.stream.ReferencePipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<T> opEvaluateParallel(PipelineHelper<T> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<T[]> intFunction) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    return Nodes.collect(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j, j2), true, intFunction);
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Nodes.collect(this, unorderedSkipLimitSpliterator(pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown), true, intFunction);
                }
                return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j, j2).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<T> opWrapSink(int i, Sink<T> sink) {
                return new Sink.ChainedReference<T, T>(sink) { // from class: java9.util.stream.SliceOps.1.1
                    long m;
                    long n;

                    {
                        this.n = j;
                        this.m = j2 >= 0 ? j2 : LongCompanionObject.MAX_VALUE;
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
                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public void begin(long j3) {
                        this.downstream.begin(SliceOps.calcSize(j3, j, this.m));
                    }

                    /* JADX WARN: Type inference incomplete: some casts might be missing */
                    @Override // java9.util.function.Consumer
                    public void accept(T t) {
                        long j3 = this.n;
                        if (j3 == 0) {
                            long j4 = this.m;
                            if (j4 > 0) {
                                this.m = j4 - 1;
                                this.downstream.accept((Object) t);
                                return;
                            }
                            return;
                        }
                        this.n = j3 - 1;
                    }

                    @Override // java9.util.stream.Sink.ChainedReference, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    public static IntStream makeInt(AbstractPipeline<?, Integer, ?> abstractPipeline, final long j, final long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new IntPipeline.StatefulOp<Integer>(abstractPipeline, StreamShape.INT_VALUE, flags(j2)) { // from class: java9.util.stream.SliceOps.2
            Spliterator.OfInt unorderedSkipLimitSpliterator(Spliterator.OfInt ofInt, long j3, long j4, long j5) {
                long j6;
                long jMin;
                if (j3 <= j5) {
                    long j7 = j5 - j3;
                    jMin = j4 >= 0 ? Math.min(j4, j7) : j7;
                    j6 = 0;
                } else {
                    j6 = j3;
                    jMin = j4;
                }
                return new StreamSpliterators.UnorderedSliceSpliterator.OfInt(ofInt, j6, jMin);
            }

            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<Integer> opEvaluateParallelLazy(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    Spliterator.OfInt ofInt = (Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator);
                    long j3 = j;
                    return new StreamSpliterators.SliceSpliterator.OfInt(ofInt, j3, SliceOps.calcSliceFence(j3, j2));
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown);
                }
                return new SliceTask(this, pipelineHelper, spliterator, WhileOps.INT_ARR_GEN, j, j2).invoke().spliterator();
            }

            @Override // java9.util.stream.IntPipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<Integer> opEvaluateParallel(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Integer[]> intFunction) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    return Nodes.collectInt(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j, j2), true);
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Nodes.collectInt(this, unorderedSkipLimitSpliterator((Spliterator.OfInt) pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown), true);
                }
                return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j, j2).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<Integer> opWrapSink(int i, Sink<Integer> sink) {
                return new Sink.ChainedInt<Integer>(sink) { // from class: java9.util.stream.SliceOps.2.1
                    long m;
                    long n;

                    {
                        this.n = j;
                        this.m = j2 >= 0 ? j2 : LongCompanionObject.MAX_VALUE;
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
                    @Override // java9.util.stream.Sink.ChainedInt, java9.util.stream.Sink
                    public void begin(long j3) {
                        this.downstream.begin(SliceOps.calcSize(j3, j, this.m));
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
                        long j3 = this.n;
                        if (j3 == 0) {
                            long j4 = this.m;
                            if (j4 > 0) {
                                this.m = j4 - 1;
                                this.downstream.accept(i2);
                                return;
                            }
                            return;
                        }
                        this.n = j3 - 1;
                    }

                    @Override // java9.util.stream.Sink.ChainedInt, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    public static LongStream makeLong(AbstractPipeline<?, Long, ?> abstractPipeline, final long j, final long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new LongPipeline.StatefulOp<Long>(abstractPipeline, StreamShape.LONG_VALUE, flags(j2)) { // from class: java9.util.stream.SliceOps.3
            Spliterator.OfLong unorderedSkipLimitSpliterator(Spliterator.OfLong ofLong, long j3, long j4, long j5) {
                long j6;
                long jMin;
                if (j3 <= j5) {
                    long j7 = j5 - j3;
                    jMin = j4 >= 0 ? Math.min(j4, j7) : j7;
                    j6 = 0;
                } else {
                    j6 = j3;
                    jMin = j4;
                }
                return new StreamSpliterators.UnorderedSliceSpliterator.OfLong(ofLong, j6, jMin);
            }

            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<Long> opEvaluateParallelLazy(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    Spliterator.OfLong ofLong = (Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator);
                    long j3 = j;
                    return new StreamSpliterators.SliceSpliterator.OfLong(ofLong, j3, SliceOps.calcSliceFence(j3, j2));
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown);
                }
                return new SliceTask(this, pipelineHelper, spliterator, WhileOps.LONG_ARR_GEN, j, j2).invoke().spliterator();
            }

            @Override // java9.util.stream.LongPipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<Long> opEvaluateParallel(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Long[]> intFunction) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    return Nodes.collectLong(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j, j2), true);
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Nodes.collectLong(this, unorderedSkipLimitSpliterator((Spliterator.OfLong) pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown), true);
                }
                return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j, j2).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<Long> opWrapSink(int i, Sink<Long> sink) {
                return new Sink.ChainedLong<Long>(sink) { // from class: java9.util.stream.SliceOps.3.1
                    long m;
                    long n;

                    {
                        this.n = j;
                        this.m = j2 >= 0 ? j2 : LongCompanionObject.MAX_VALUE;
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
                    @Override // java9.util.stream.Sink.ChainedLong, java9.util.stream.Sink
                    public void begin(long j3) {
                        this.downstream.begin(SliceOps.calcSize(j3, j, this.m));
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
                    public void accept(long j3) {
                        long j4 = this.n;
                        if (j4 == 0) {
                            long j5 = this.m;
                            if (j5 > 0) {
                                this.m = j5 - 1;
                                this.downstream.accept(j3);
                                return;
                            }
                            return;
                        }
                        this.n = j4 - 1;
                    }

                    @Override // java9.util.stream.Sink.ChainedLong, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    public static DoubleStream makeDouble(AbstractPipeline<?, Double, ?> abstractPipeline, final long j, final long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("Skip must be non-negative: " + j);
        }
        return new DoublePipeline.StatefulOp<Double>(abstractPipeline, StreamShape.DOUBLE_VALUE, flags(j2)) { // from class: java9.util.stream.SliceOps.4
            Spliterator.OfDouble unorderedSkipLimitSpliterator(Spliterator.OfDouble ofDouble, long j3, long j4, long j5) {
                long j6;
                long jMin;
                if (j3 <= j5) {
                    long j7 = j5 - j3;
                    jMin = j4 >= 0 ? Math.min(j4, j7) : j7;
                    j6 = 0;
                } else {
                    j6 = j3;
                    jMin = j4;
                }
                return new StreamSpliterators.UnorderedSliceSpliterator.OfDouble(ofDouble, j6, jMin);
            }

            @Override // java9.util.stream.AbstractPipeline
            <P_IN> Spliterator<Double> opEvaluateParallelLazy(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    Spliterator.OfDouble ofDouble = (Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator);
                    long j3 = j;
                    return new StreamSpliterators.SliceSpliterator.OfDouble(ofDouble, j3, SliceOps.calcSliceFence(j3, j2));
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return unorderedSkipLimitSpliterator((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown);
                }
                return new SliceTask(this, pipelineHelper, spliterator, WhileOps.DOUBLE_ARR_GEN, j, j2).invoke().spliterator();
            }

            @Override // java9.util.stream.DoublePipeline.StatefulOp, java9.util.stream.AbstractPipeline
            <P_IN> Node<Double> opEvaluateParallel(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<Double[]> intFunction) {
                long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
                if (jExactOutputSizeIfKnown > 0 && spliterator.hasCharacteristics(16384)) {
                    return Nodes.collectDouble(pipelineHelper, SliceOps.sliceSpliterator(pipelineHelper.getSourceShape(), spliterator, j, j2), true);
                }
                if (!StreamOpFlag.ORDERED.isKnown(pipelineHelper.getStreamAndOpFlags())) {
                    return Nodes.collectDouble(this, unorderedSkipLimitSpliterator((Spliterator.OfDouble) pipelineHelper.wrapSpliterator(spliterator), j, j2, jExactOutputSizeIfKnown), true);
                }
                return (Node) new SliceTask(this, pipelineHelper, spliterator, intFunction, j, j2).invoke();
            }

            @Override // java9.util.stream.AbstractPipeline
            Sink<Double> opWrapSink(int i, Sink<Double> sink) {
                return new Sink.ChainedDouble<Double>(sink) { // from class: java9.util.stream.SliceOps.4.1
                    long m;
                    long n;

                    {
                        this.n = j;
                        this.m = j2 >= 0 ? j2 : LongCompanionObject.MAX_VALUE;
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
                    @Override // java9.util.stream.Sink.ChainedDouble, java9.util.stream.Sink
                    public void begin(long j3) {
                        this.downstream.begin(SliceOps.calcSize(j3, j, this.m));
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
                        long j3 = this.n;
                        if (j3 == 0) {
                            long j4 = this.m;
                            if (j4 > 0) {
                                this.m = j4 - 1;
                                this.downstream.accept(d);
                                return;
                            }
                            return;
                        }
                        this.n = j3 - 1;
                    }

                    @Override // java9.util.stream.Sink.ChainedDouble, java9.util.stream.Sink
                    public boolean cancellationRequested() {
                        return this.m == 0 || this.downstream.cancellationRequested();
                    }
                };
            }
        };
    }

    private static int flags(long j) {
        return (j != -1 ? StreamOpFlag.IS_SHORT_CIRCUIT : 0) | StreamOpFlag.NOT_SIZED;
    }

    private static final class SliceTask<P_IN, P_OUT> extends AbstractShortCircuitTask<P_IN, P_OUT, Node<P_OUT>, SliceTask<P_IN, P_OUT>> {
        private volatile boolean completed;
        private final IntFunction<P_OUT[]> generator;
        private final AbstractPipeline<P_OUT, P_OUT, ?> op;
        private final long targetOffset;
        private final long targetSize;
        private long thisNodeSize;

        SliceTask(AbstractPipeline<P_OUT, P_OUT, ?> abstractPipeline, PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator, IntFunction<P_OUT[]> intFunction, long j, long j2) {
            super(pipelineHelper, spliterator);
            this.op = abstractPipeline;
            this.generator = intFunction;
            this.targetOffset = j;
            this.targetSize = j2;
        }

        SliceTask(SliceTask<P_IN, P_OUT> sliceTask, Spliterator<P_IN> spliterator) {
            super(sliceTask, spliterator);
            this.op = sliceTask.op;
            this.generator = sliceTask.generator;
            this.targetOffset = sliceTask.targetOffset;
            this.targetSize = sliceTask.targetSize;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public SliceTask<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator) {
            return new SliceTask<>(this, spliterator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractShortCircuitTask
        public final Node<P_OUT> getEmptyResult() {
            return Nodes.emptyNode(this.op.getOutputShape());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public final Node<P_OUT> doLeaf() {
            if (isRoot()) {
                Node.Builder<P_OUT> builderMakeNodeBuilder = this.op.makeNodeBuilder(StreamOpFlag.SIZED.isPreserved(this.op.sourceOrOpFlags) ? this.op.exactOutputSizeIfKnown(this.spliterator) : -1L, this.generator);
                this.helper.copyIntoWithCancel(this.helper.wrapSink((Sink) this.op.opWrapSink(this.helper.getStreamAndOpFlags(), builderMakeNodeBuilder)), this.spliterator);
                return builderMakeNodeBuilder.build2();
            }
            Node.Builder<P_OUT> builderMakeNodeBuilder2 = this.op.makeNodeBuilder(-1L, this.generator);
            if (this.targetOffset == 0) {
                this.helper.copyIntoWithCancel(this.helper.wrapSink((Sink) this.op.opWrapSink(this.helper.getStreamAndOpFlags(), builderMakeNodeBuilder2)), this.spliterator);
            } else {
                this.helper.wrapAndCopyInto(builderMakeNodeBuilder2, (Spliterator) this.spliterator);
            }
            Node<P_OUT> nodeBuild2 = builderMakeNodeBuilder2.build2();
            this.thisNodeSize = nodeBuild2.count();
            this.completed = true;
            this.spliterator = null;
            return nodeBuild2;
        }

        @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
        public final void onCompletion(CountedCompleter<?> countedCompleter) {
            Node<P_OUT> nodeConc;
            if (!isLeaf()) {
                this.thisNodeSize = ((SliceTask) this.leftChild).thisNodeSize + ((SliceTask) this.rightChild).thisNodeSize;
                if (this.canceled) {
                    this.thisNodeSize = 0L;
                    nodeConc = getEmptyResult();
                } else if (this.thisNodeSize == 0) {
                    nodeConc = getEmptyResult();
                } else if (((SliceTask) this.leftChild).thisNodeSize == 0) {
                    nodeConc = ((SliceTask) this.rightChild).getLocalResult();
                } else {
                    nodeConc = Nodes.conc(this.op.getOutputShape(), ((SliceTask) this.leftChild).getLocalResult(), ((SliceTask) this.rightChild).getLocalResult());
                }
                if (isRoot()) {
                    nodeConc = doTruncate(nodeConc);
                }
                setLocalResult(nodeConc);
                this.completed = true;
            }
            if (this.targetSize >= 0 && !isRoot() && isLeftCompleted(this.targetOffset + this.targetSize)) {
                cancelLaterNodes();
            }
            super.onCompletion(countedCompleter);
        }

        @Override // java9.util.stream.AbstractShortCircuitTask
        protected void cancel() {
            super.cancel();
            if (this.completed) {
                setLocalResult(getEmptyResult());
            }
        }

        private Node<P_OUT> doTruncate(Node<P_OUT> node) {
            return node.truncate(this.targetOffset, this.targetSize >= 0 ? Math.min(node.count(), this.targetOffset + this.targetSize) : this.thisNodeSize, this.generator);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private boolean isLeftCompleted(long j) {
            SliceTask sliceTask;
            long jCompletedSize = this.completed ? this.thisNodeSize : completedSize(j);
            if (jCompletedSize >= j) {
                return true;
            }
            SliceTask<P_IN, P_OUT> sliceTask2 = this;
            for (SliceTask<P_IN, P_OUT> sliceTask3 = (SliceTask) getParent(); sliceTask3 != null; sliceTask3 = (SliceTask) sliceTask3.getParent()) {
                if (sliceTask2 == sliceTask3.rightChild && (sliceTask = (SliceTask) sliceTask3.leftChild) != null) {
                    jCompletedSize += sliceTask.completedSize(j);
                    if (jCompletedSize >= j) {
                        return true;
                    }
                }
                sliceTask2 = sliceTask3;
            }
            return jCompletedSize >= j;
        }

        private long completedSize(long j) {
            if (this.completed) {
                return this.thisNodeSize;
            }
            SliceTask sliceTask = (SliceTask) this.leftChild;
            SliceTask sliceTask2 = (SliceTask) this.rightChild;
            if (sliceTask == null || sliceTask2 == null) {
                return this.thisNodeSize;
            }
            long jCompletedSize = sliceTask.completedSize(j);
            return jCompletedSize >= j ? jCompletedSize : jCompletedSize + sliceTask2.completedSize(j);
        }
    }
}
