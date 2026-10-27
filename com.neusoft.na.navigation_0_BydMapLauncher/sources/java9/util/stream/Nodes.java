package java9.util.stream;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java9.util.J8Arrays;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.concurrent.CountedCompleter;
import java9.util.function.BinaryOperator;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.LongConsumer;
import java9.util.function.LongFunction;
import java9.util.stream.Node;
import java9.util.stream.Sink;
import java9.util.stream.SinkDefaults;
import java9.util.stream.SpinedBuffer;

/* JADX INFO: loaded from: classes3.dex */
final class Nodes {
    static final String BAD_SIZE = "Stream size exceeds max array size";
    static final long MAX_ARRAY_SIZE = 2147483639;
    private static final Node EMPTY_NODE = new EmptyNode.OfRef(null);
    private static final Node.OfInt EMPTY_INT_NODE = new EmptyNode.OfInt();
    private static final Node.OfLong EMPTY_LONG_NODE = new EmptyNode.OfLong();
    private static final Node.OfDouble EMPTY_DOUBLE_NODE = new EmptyNode.OfDouble();
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final long[] EMPTY_LONG_ARRAY = new long[0];
    private static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

    static <T> int getChildCount() {
        return 0;
    }

    static /* synthetic */ void lambda$truncate$0(Object obj) {
    }

    private Nodes() {
        throw new Error("no instances");
    }

    static <T> StreamShape getShape() {
        return StreamShape.REFERENCE;
    }

    static <T> Node<T> getChild() {
        throw new IndexOutOfBoundsException();
    }

    static <T> Node<T> truncate(Node<T> node, long j, long j2, IntFunction<T[]> intFunction) {
        if (j == 0 && j2 == node.count()) {
            return node;
        }
        Spliterator<T> spliterator = node.spliterator();
        long j3 = j2 - j;
        Node.Builder builder = builder(j3, intFunction);
        builder.begin(j3);
        for (int i = 0; i < j && spliterator.tryAdvance(Nodes$$Lambda$1.instance); i++) {
        }
        if (j2 == node.count()) {
            spliterator.forEachRemaining(builder);
        } else {
            for (int i2 = 0; i2 < j3 && spliterator.tryAdvance(builder); i2++) {
            }
        }
        builder.end();
        return builder.build2();
    }

    static final class OfPrimitive {
        static <T, T_CONS, T_ARR, T_NODE extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> T_NODE getChild() {
            throw new IndexOutOfBoundsException();
        }

        static <T, T_CONS, T_ARR, T_NODE extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> T[] asArray(Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> ofPrimitive, IntFunction<T[]> intFunction) {
            if (ofPrimitive.count() >= Nodes.MAX_ARRAY_SIZE) {
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }
            T[] tArrApply = intFunction.apply((int) ofPrimitive.count());
            ofPrimitive.copyInto((Object[]) tArrApply, 0);
            return tArrApply;
        }

        private OfPrimitive() {
        }
    }

    static final class OfDouble {
        static /* synthetic */ void lambda$truncate$0(double d) {
        }

        /* JADX WARN: Type inference failed for: r7v6, types: [java9.util.stream.Node$OfDouble] */
        static Node.OfDouble truncate(Node.OfDouble ofDouble, long j, long j2, IntFunction<Double[]> intFunction) {
            if (j == 0 && j2 == ofDouble.count()) {
                return ofDouble;
            }
            long j3 = j2 - j;
            Spliterator.OfDouble ofDoubleSpliterator = ofDouble.spliterator();
            Node.Builder.OfDouble ofDoubleDoubleBuilder = Nodes.doubleBuilder(j3);
            ofDoubleDoubleBuilder.begin(j3);
            for (int i = 0; i < j && ofDoubleSpliterator.tryAdvance(Nodes$OfDouble$$Lambda$1.instance); i++) {
            }
            if (j2 == ofDouble.count()) {
                ofDoubleSpliterator.forEachRemaining((DoubleConsumer) ofDoubleDoubleBuilder);
            } else {
                for (int i2 = 0; i2 < j3 && ofDoubleSpliterator.tryAdvance((DoubleConsumer) ofDoubleDoubleBuilder); i2++) {
                }
            }
            ofDoubleDoubleBuilder.end();
            return ofDoubleDoubleBuilder.build2();
        }

        static void forEach(Node.OfDouble ofDouble, Consumer<? super Double> consumer) {
            if (consumer instanceof DoubleConsumer) {
                ofDouble.forEach((DoubleConsumer) consumer);
            } else {
                ofDouble.spliterator().forEachRemaining(consumer);
            }
        }

        static void copyInto(Node.OfDouble ofDouble, Double[] dArr, int i) {
            double[] dArrAsPrimitiveArray = ofDouble.asPrimitiveArray();
            for (int i2 = 0; i2 < dArrAsPrimitiveArray.length; i2++) {
                dArr[i + i2] = Double.valueOf(dArrAsPrimitiveArray[i2]);
            }
        }

        static double[] newArray(Node.OfDouble ofDouble, int i) {
            return new double[i];
        }

        static StreamShape getShape() {
            return StreamShape.DOUBLE_VALUE;
        }

        private OfDouble() {
        }
    }

    static final class OfLong {
        static /* synthetic */ void lambda$truncate$0(long j) {
        }

        /* JADX WARN: Type inference failed for: r7v6, types: [java9.util.stream.Node$OfLong] */
        static Node.OfLong truncate(Node.OfLong ofLong, long j, long j2, IntFunction<Long[]> intFunction) {
            if (j == 0 && j2 == ofLong.count()) {
                return ofLong;
            }
            long j3 = j2 - j;
            Spliterator.OfLong ofLongSpliterator = ofLong.spliterator();
            Node.Builder.OfLong ofLongLongBuilder = Nodes.longBuilder(j3);
            ofLongLongBuilder.begin(j3);
            for (int i = 0; i < j && ofLongSpliterator.tryAdvance(Nodes$OfLong$$Lambda$1.instance); i++) {
            }
            if (j2 == ofLong.count()) {
                ofLongSpliterator.forEachRemaining((LongConsumer) ofLongLongBuilder);
            } else {
                for (int i2 = 0; i2 < j3 && ofLongSpliterator.tryAdvance((LongConsumer) ofLongLongBuilder); i2++) {
                }
            }
            ofLongLongBuilder.end();
            return ofLongLongBuilder.build2();
        }

        static void forEach(Node.OfLong ofLong, Consumer<? super Long> consumer) {
            if (consumer instanceof LongConsumer) {
                ofLong.forEach((LongConsumer) consumer);
            } else {
                ofLong.spliterator().forEachRemaining(consumer);
            }
        }

        static void copyInto(Node.OfLong ofLong, Long[] lArr, int i) {
            long[] jArrAsPrimitiveArray = ofLong.asPrimitiveArray();
            for (int i2 = 0; i2 < jArrAsPrimitiveArray.length; i2++) {
                lArr[i + i2] = Long.valueOf(jArrAsPrimitiveArray[i2]);
            }
        }

        static long[] newArray(Node.OfLong ofLong, int i) {
            return new long[i];
        }

        static StreamShape getShape() {
            return StreamShape.LONG_VALUE;
        }

        private OfLong() {
        }
    }

    static final class OfInt {
        static /* synthetic */ void lambda$truncate$0(int i) {
        }

        /* JADX WARN: Type inference failed for: r7v6, types: [java9.util.stream.Node$OfInt] */
        static Node.OfInt truncate(Node.OfInt ofInt, long j, long j2, IntFunction<Integer[]> intFunction) {
            if (j == 0 && j2 == ofInt.count()) {
                return ofInt;
            }
            long j3 = j2 - j;
            Spliterator.OfInt ofIntSpliterator = ofInt.spliterator();
            Node.Builder.OfInt ofIntIntBuilder = Nodes.intBuilder(j3);
            ofIntIntBuilder.begin(j3);
            for (int i = 0; i < j && ofIntSpliterator.tryAdvance(Nodes$OfInt$$Lambda$1.instance); i++) {
            }
            if (j2 == ofInt.count()) {
                ofIntSpliterator.forEachRemaining((IntConsumer) ofIntIntBuilder);
            } else {
                for (int i2 = 0; i2 < j3 && ofIntSpliterator.tryAdvance((IntConsumer) ofIntIntBuilder); i2++) {
                }
            }
            ofIntIntBuilder.end();
            return ofIntIntBuilder.build2();
        }

        static void forEach(Node.OfInt ofInt, Consumer<? super Integer> consumer) {
            if (consumer instanceof IntConsumer) {
                ofInt.forEach((IntConsumer) consumer);
            } else {
                ofInt.spliterator().forEachRemaining(consumer);
            }
        }

        static void copyInto(Node.OfInt ofInt, Integer[] numArr, int i) {
            int[] iArrAsPrimitiveArray = ofInt.asPrimitiveArray();
            for (int i2 = 0; i2 < iArrAsPrimitiveArray.length; i2++) {
                numArr[i + i2] = Integer.valueOf(iArrAsPrimitiveArray[i2]);
            }
        }

        static int[] newArray(Node.OfInt ofInt, int i) {
            return new int[i];
        }

        static StreamShape getShape() {
            return StreamShape.INT_VALUE;
        }

        private OfInt() {
        }
    }

    static <T> IntFunction<T[]> castingArray() {
        return Nodes$$Lambda$2.instance;
    }

    static /* synthetic */ Object[] lambda$castingArray$1(int i) {
        return new Object[i];
    }

    /* JADX INFO: renamed from: java9.util.stream.Nodes$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
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

    static <T> Node<T> emptyNode(StreamShape streamShape) {
        int i = AnonymousClass1.$SwitchMap$java8$util$stream$StreamShape[streamShape.ordinal()];
        if (i == 1) {
            return EMPTY_NODE;
        }
        if (i == 2) {
            return EMPTY_INT_NODE;
        }
        if (i == 3) {
            return EMPTY_LONG_NODE;
        }
        if (i == 4) {
            return EMPTY_DOUBLE_NODE;
        }
        throw new IllegalStateException("Unknown shape " + streamShape);
    }

    static <T> Node<T> conc(StreamShape streamShape, Node<T> node, Node<T> node2) {
        int i = AnonymousClass1.$SwitchMap$java8$util$stream$StreamShape[streamShape.ordinal()];
        if (i == 1) {
            return new ConcNode(node, node2);
        }
        if (i == 2) {
            return new ConcNode.OfInt((Node.OfInt) node, (Node.OfInt) node2);
        }
        if (i == 3) {
            return new ConcNode.OfLong((Node.OfLong) node, (Node.OfLong) node2);
        }
        if (i == 4) {
            return new ConcNode.OfDouble((Node.OfDouble) node, (Node.OfDouble) node2);
        }
        throw new IllegalStateException("Unknown shape " + streamShape);
    }

    static <T> Node<T> node(T[] tArr) {
        return new ArrayNode(tArr);
    }

    static <T> Node<T> node(Collection<T> collection) {
        return new CollectionNode(collection);
    }

    static <T> Node.Builder<T> builder(long j, IntFunction<T[]> intFunction) {
        if (j >= 0 && j < MAX_ARRAY_SIZE) {
            return new FixedNodeBuilder(j, intFunction);
        }
        return builder();
    }

    static <T> Node.Builder<T> builder() {
        return new SpinedNodeBuilder();
    }

    static Node.OfInt node(int[] iArr) {
        return new IntArrayNode(iArr);
    }

    static Node.Builder.OfInt intBuilder(long j) {
        if (j >= 0 && j < MAX_ARRAY_SIZE) {
            return new IntFixedNodeBuilder(j);
        }
        return intBuilder();
    }

    static Node.Builder.OfInt intBuilder() {
        return new IntSpinedNodeBuilder();
    }

    static Node.OfLong node(long[] jArr) {
        return new LongArrayNode(jArr);
    }

    static Node.Builder.OfLong longBuilder(long j) {
        if (j >= 0 && j < MAX_ARRAY_SIZE) {
            return new LongFixedNodeBuilder(j);
        }
        return longBuilder();
    }

    static Node.Builder.OfLong longBuilder() {
        return new LongSpinedNodeBuilder();
    }

    static Node.OfDouble node(double[] dArr) {
        return new DoubleArrayNode(dArr);
    }

    static Node.Builder.OfDouble doubleBuilder(long j) {
        if (j >= 0 && j < MAX_ARRAY_SIZE) {
            return new DoubleFixedNodeBuilder(j);
        }
        return doubleBuilder();
    }

    static Node.Builder.OfDouble doubleBuilder() {
        return new DoubleSpinedNodeBuilder();
    }

    public static <P_IN, P_OUT> Node<P_OUT> collect(PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator, boolean z, IntFunction<P_OUT[]> intFunction) {
        long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (jExactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node<P_OUT> node = (Node) new CollectorTask.OfRef(pipelineHelper, intFunction, spliterator).invoke();
            return z ? flatten(node, intFunction) : node;
        }
        if (jExactOutputSizeIfKnown >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        P_OUT[] p_outArrApply = intFunction.apply((int) jExactOutputSizeIfKnown);
        new SizedCollectorTask.OfRef(spliterator, pipelineHelper, p_outArrApply).invoke();
        return node(p_outArrApply);
    }

    public static <P_IN> Node.OfInt collectInt(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator, boolean z) {
        long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (jExactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfInt ofInt = (Node.OfInt) new CollectorTask.OfInt(pipelineHelper, spliterator).invoke();
            return z ? flattenInt(ofInt) : ofInt;
        }
        if (jExactOutputSizeIfKnown >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        int[] iArr = new int[(int) jExactOutputSizeIfKnown];
        new SizedCollectorTask.OfInt(spliterator, pipelineHelper, iArr).invoke();
        return node(iArr);
    }

    public static <P_IN> Node.OfLong collectLong(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator, boolean z) {
        long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (jExactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfLong ofLong = (Node.OfLong) new CollectorTask.OfLong(pipelineHelper, spliterator).invoke();
            return z ? flattenLong(ofLong) : ofLong;
        }
        if (jExactOutputSizeIfKnown >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        long[] jArr = new long[(int) jExactOutputSizeIfKnown];
        new SizedCollectorTask.OfLong(spliterator, pipelineHelper, jArr).invoke();
        return node(jArr);
    }

    public static <P_IN> Node.OfDouble collectDouble(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator, boolean z) {
        long jExactOutputSizeIfKnown = pipelineHelper.exactOutputSizeIfKnown(spliterator);
        if (jExactOutputSizeIfKnown < 0 || !spliterator.hasCharacteristics(16384)) {
            Node.OfDouble ofDouble = (Node.OfDouble) new CollectorTask.OfDouble(pipelineHelper, spliterator).invoke();
            return z ? flattenDouble(ofDouble) : ofDouble;
        }
        if (jExactOutputSizeIfKnown >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        double[] dArr = new double[(int) jExactOutputSizeIfKnown];
        new SizedCollectorTask.OfDouble(spliterator, pipelineHelper, dArr).invoke();
        return node(dArr);
    }

    public static <T> Node<T> flatten(Node<T> node, IntFunction<T[]> intFunction) {
        if (node.getChildCount() <= 0) {
            return node;
        }
        long jCount = node.count();
        if (jCount >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        T[] tArrApply = intFunction.apply((int) jCount);
        new ToArrayTask.OfRef(node, tArrApply, 0, null).invoke();
        return node(tArrApply);
    }

    public static Node.OfInt flattenInt(Node.OfInt ofInt) {
        if (ofInt.getChildCount() <= 0) {
            return ofInt;
        }
        long jCount = ofInt.count();
        if (jCount >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        int[] iArr = new int[(int) jCount];
        new ToArrayTask.OfInt(ofInt, iArr, 0, null).invoke();
        return node(iArr);
    }

    public static Node.OfLong flattenLong(Node.OfLong ofLong) {
        if (ofLong.getChildCount() <= 0) {
            return ofLong;
        }
        long jCount = ofLong.count();
        if (jCount >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        long[] jArr = new long[(int) jCount];
        new ToArrayTask.OfLong(ofLong, jArr, 0, null).invoke();
        return node(jArr);
    }

    public static Node.OfDouble flattenDouble(Node.OfDouble ofDouble) {
        if (ofDouble.getChildCount() <= 0) {
            return ofDouble;
        }
        long jCount = ofDouble.count();
        if (jCount >= MAX_ARRAY_SIZE) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        double[] dArr = new double[(int) jCount];
        new ToArrayTask.OfDouble(ofDouble, dArr, 0, null).invoke();
        return node(dArr);
    }

    private static abstract class EmptyNode<T, T_ARR, T_CONS> implements Node<T> {
        public void copyInto(T_ARR t_arr, int i) {
        }

        @Override // java9.util.stream.Node
        public long count() {
            return 0L;
        }

        public void forEach(T_CONS t_cons) {
        }

        EmptyNode() {
        }

        @Override // java9.util.stream.Node
        public T[] asArray(IntFunction<T[]> intFunction) {
            return intFunction.apply(0);
        }

        @Override // java9.util.stream.Node
        public StreamShape getShape() {
            return Nodes.getShape();
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node<T> getChild(int i) {
            return Nodes.getChild();
        }

        @Override // java9.util.stream.Node
        public Node<T> truncate(long j, long j2, IntFunction<T[]> intFunction) {
            return Nodes.truncate(this, j, j2, intFunction);
        }

        private static class OfRef<T> extends EmptyNode<T, T[], Consumer<? super T>> {
            /* synthetic */ OfRef(AnonymousClass1 anonymousClass1) {
                this();
            }

            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ void copyInto(Object[] objArr, int i) {
                super.copyInto(objArr, i);
            }

            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ void forEach(Consumer consumer) {
                super.forEach(consumer);
            }

            private OfRef() {
            }

            @Override // java9.util.stream.Node
            public Spliterator<T> spliterator() {
                return Spliterators.emptySpliterator();
            }
        }

        private static final class OfInt extends EmptyNode<Integer, int[], IntConsumer> implements Node.OfInt {
            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
            }

            OfInt() {
            }

            @Override // java9.util.stream.Node
            public void forEach(Consumer<? super Integer> consumer) {
                OfInt.forEach(this, consumer);
            }

            @Override // java9.util.stream.Node
            public Spliterator.OfInt spliterator() {
                return Spliterators.emptyIntSpliterator();
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public Node.OfInt getChild(int i) {
                return (Node.OfInt) OfPrimitive.getChild();
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public Node.OfInt truncate(long j, long j2, IntFunction<Integer[]> intFunction) {
                return OfInt.truncate(this, j, j2, intFunction);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public int[] asPrimitiveArray() {
                return Nodes.EMPTY_INT_ARRAY;
            }

            @Override // java9.util.stream.Node
            public void copyInto(Integer[] numArr, int i) {
                OfInt.copyInto(this, numArr, i);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public int[] newArray(int i) {
                return OfInt.newArray(this, i);
            }
        }

        private static final class OfLong extends EmptyNode<Long, long[], LongConsumer> implements Node.OfLong {
            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Long[]>) intFunction);
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Long[]>) intFunction);
            }

            OfLong() {
            }

            @Override // java9.util.stream.Node
            public void forEach(Consumer<? super Long> consumer) {
                OfLong.forEach(this, consumer);
            }

            @Override // java9.util.stream.Node
            public Spliterator.OfLong spliterator() {
                return Spliterators.emptyLongSpliterator();
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public Node.OfLong getChild(int i) {
                return (Node.OfLong) OfPrimitive.getChild();
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public Node.OfLong truncate(long j, long j2, IntFunction<Long[]> intFunction) {
                return OfLong.truncate(this, j, j2, intFunction);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public long[] asPrimitiveArray() {
                return Nodes.EMPTY_LONG_ARRAY;
            }

            @Override // java9.util.stream.Node
            public void copyInto(Long[] lArr, int i) {
                OfLong.copyInto(this, lArr, i);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public long[] newArray(int i) {
                return OfLong.newArray(this, i);
            }
        }

        private static final class OfDouble extends EmptyNode<Double, double[], DoubleConsumer> implements Node.OfDouble {
            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Double[]>) intFunction);
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Double[]>) intFunction);
            }

            OfDouble() {
            }

            @Override // java9.util.stream.Node
            public void forEach(Consumer<? super Double> consumer) {
                OfDouble.forEach(this, consumer);
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public Node.OfDouble getChild(int i) {
                return (Node.OfDouble) OfPrimitive.getChild();
            }

            @Override // java9.util.stream.Nodes.EmptyNode, java9.util.stream.Node
            public Node.OfDouble truncate(long j, long j2, IntFunction<Double[]> intFunction) {
                return OfDouble.truncate(this, j, j2, intFunction);
            }

            @Override // java9.util.stream.Node
            public Spliterator.OfDouble spliterator() {
                return Spliterators.emptyDoubleSpliterator();
            }

            @Override // java9.util.stream.Node
            public void copyInto(Double[] dArr, int i) {
                OfDouble.copyInto(this, dArr, i);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public double[] asPrimitiveArray() {
                return Nodes.EMPTY_DOUBLE_ARRAY;
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public double[] newArray(int i) {
                return OfDouble.newArray(this, i);
            }
        }
    }

    private static class ArrayNode<T> implements Node<T> {
        final T[] array;
        int curSize;

        ArrayNode(long j, IntFunction<T[]> intFunction) {
            if (j >= Nodes.MAX_ARRAY_SIZE) {
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }
            this.array = intFunction.apply((int) j);
            this.curSize = 0;
        }

        ArrayNode(T[] tArr) {
            this.array = tArr;
            this.curSize = tArr.length;
        }

        @Override // java9.util.stream.Node
        public Spliterator<T> spliterator() {
            return J8Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java9.util.stream.Node
        public StreamShape getShape() {
            return Nodes.getShape();
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node<T> getChild(int i) {
            return Nodes.getChild();
        }

        @Override // java9.util.stream.Node
        public Node<T> truncate(long j, long j2, IntFunction<T[]> intFunction) {
            return Nodes.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public void copyInto(T[] tArr, int i) {
            System.arraycopy(this.array, 0, tArr, i, this.curSize);
        }

        @Override // java9.util.stream.Node
        public T[] asArray(IntFunction<T[]> intFunction) {
            T[] tArr = this.array;
            if (tArr.length == this.curSize) {
                return tArr;
            }
            throw new IllegalStateException();
        }

        @Override // java9.util.stream.Node
        public long count() {
            return this.curSize;
        }

        @Override // java9.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            for (int i = 0; i < this.curSize; i++) {
                consumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("ArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class CollectionNode<T> implements Node<T> {
        private final Collection<T> c;

        CollectionNode(Collection<T> collection) {
            this.c = collection;
        }

        @Override // java9.util.stream.Node
        public Spliterator<T> spliterator() {
            return Spliterators.spliterator(this.c);
        }

        @Override // java9.util.stream.Node
        public StreamShape getShape() {
            return Nodes.getShape();
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node<T> getChild(int i) {
            return Nodes.getChild();
        }

        @Override // java9.util.stream.Node
        public Node<T> truncate(long j, long j2, IntFunction<T[]> intFunction) {
            return Nodes.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public void copyInto(T[] tArr, int i) {
            Iterator<T> it = this.c.iterator();
            while (it.hasNext()) {
                tArr[i] = it.next();
                i++;
            }
        }

        @Override // java9.util.stream.Node
        public T[] asArray(IntFunction<T[]> intFunction) {
            Collection<T> collection = this.c;
            return (T[]) collection.toArray(intFunction.apply(collection.size()));
        }

        @Override // java9.util.stream.Node
        public long count() {
            return this.c.size();
        }

        @Override // java9.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            Iterator<T> it = this.c.iterator();
            while (it.hasNext()) {
                consumer.accept(it.next());
            }
        }

        public String toString() {
            return String.format("CollectionNode[%d][%s]", Integer.valueOf(this.c.size()), this.c);
        }
    }

    private static abstract class AbstractConcNode<T, T_NODE extends Node<T>> implements Node<T> {
        protected final T_NODE left;
        protected final T_NODE right;
        private final long size;

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return 2;
        }

        AbstractConcNode(T_NODE t_node, T_NODE t_node2) {
            this.left = t_node;
            this.right = t_node2;
            this.size = t_node.count() + t_node2.count();
        }

        @Override // java9.util.stream.Node
        public StreamShape getShape() {
            return Nodes.getShape();
        }

        @Override // java9.util.stream.Node
        public T_NODE getChild(int i) {
            if (i == 0) {
                return this.left;
            }
            if (i == 1) {
                return this.right;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // java9.util.stream.Node
        public long count() {
            return this.size;
        }
    }

    static final class ConcNode<T> extends AbstractConcNode<T, Node<T>> implements Node<T> {
        ConcNode(Node<T> node, Node<T> node2) {
            super(node, node2);
        }

        @Override // java9.util.stream.Node
        public Spliterator<T> spliterator() {
            return new InternalNodeSpliterator.OfRef(this);
        }

        @Override // java9.util.stream.Node
        public void copyInto(T[] tArr, int i) {
            Objects.requireNonNull(tArr);
            this.left.copyInto(tArr, i);
            this.right.copyInto(tArr, i + ((int) this.left.count()));
        }

        @Override // java9.util.stream.Node
        public T[] asArray(IntFunction<T[]> intFunction) {
            long jCount = count();
            if (jCount >= Nodes.MAX_ARRAY_SIZE) {
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }
            T[] tArrApply = intFunction.apply((int) jCount);
            copyInto(tArrApply, 0);
            return tArrApply;
        }

        @Override // java9.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            this.left.forEach(consumer);
            this.right.forEach(consumer);
        }

        @Override // java9.util.stream.Node
        public Node<T> truncate(long j, long j2, IntFunction<T[]> intFunction) {
            if (j == 0 && j2 == count()) {
                return this;
            }
            long jCount = this.left.count();
            if (j >= jCount) {
                return this.right.truncate(j - jCount, j2 - jCount, intFunction);
            }
            if (j2 <= jCount) {
                return this.left.truncate(j, j2, intFunction);
            }
            return Nodes.conc(getShape(), this.left.truncate(j, jCount, intFunction), this.right.truncate(0L, j2 - jCount, intFunction));
        }

        public String toString() {
            return count() < 32 ? String.format("ConcNode[%s.%s]", this.left, this.right) : String.format("ConcNode[size=%d]", Long.valueOf(count()));
        }

        private static abstract class OfPrimitive<E, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<E, T_CONS, T_SPLITR>, T_NODE extends Node.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE>> extends AbstractConcNode<E, T_NODE> implements Node.OfPrimitive<E, T_CONS, T_ARR, T_SPLITR, T_NODE> {
            @Override // java9.util.stream.Nodes.AbstractConcNode, java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive getChild(int i) {
                return (Node.OfPrimitive) super.getChild(i);
            }

            OfPrimitive(T_NODE t_node, T_NODE t_node2) {
                super(t_node, t_node2);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public void forEach(T_CONS t_cons) {
                ((Node.OfPrimitive) this.left).forEach(t_cons);
                ((Node.OfPrimitive) this.right).forEach(t_cons);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public void copyInto(T_ARR t_arr, int i) {
                ((Node.OfPrimitive) this.left).copyInto(t_arr, i);
                ((Node.OfPrimitive) this.right).copyInto(t_arr, i + ((int) ((Node.OfPrimitive) this.left).count()));
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public T_ARR asPrimitiveArray() {
                long jCount = count();
                if (jCount >= Nodes.MAX_ARRAY_SIZE) {
                    throw new IllegalArgumentException(Nodes.BAD_SIZE);
                }
                T_ARR t_arrNewArray = newArray((int) jCount);
                copyInto(t_arrNewArray, 0);
                return t_arrNewArray;
            }

            @Override // java9.util.stream.Node
            public E[] asArray(IntFunction<E[]> intFunction) {
                return (E[]) OfPrimitive.asArray(this, intFunction);
            }

            public String toString() {
                return count() < 32 ? String.format("%s[%s.%s]", getClass().getName(), this.left, this.right) : String.format("%s[size=%d]", getClass().getName(), Long.valueOf(count()));
            }
        }

        static final class OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, Node.OfInt> implements Node.OfInt {
            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
            }

            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
            }

            OfInt(Node.OfInt ofInt, Node.OfInt ofInt2) {
                super(ofInt, ofInt2);
            }

            @Override // java9.util.stream.Node
            public void forEach(Consumer<? super Integer> consumer) {
                OfInt.forEach(this, consumer);
            }

            @Override // java9.util.stream.Node
            public Spliterator.OfInt spliterator() {
                return new InternalNodeSpliterator.OfInt(this);
            }

            @Override // java9.util.stream.Node
            public Node.OfInt truncate(long j, long j2, IntFunction<Integer[]> intFunction) {
                return OfInt.truncate(this, j, j2, intFunction);
            }

            @Override // java9.util.stream.Node
            public void copyInto(Integer[] numArr, int i) {
                OfInt.copyInto(this, numArr, i);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public int[] newArray(int i) {
                return OfInt.newArray(this, i);
            }
        }

        static final class OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, Node.OfLong> implements Node.OfLong {
            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Long[]>) intFunction);
            }

            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Long[]>) intFunction);
            }

            OfLong(Node.OfLong ofLong, Node.OfLong ofLong2) {
                super(ofLong, ofLong2);
            }

            @Override // java9.util.stream.Node
            public void forEach(Consumer<? super Long> consumer) {
                OfLong.forEach(this, consumer);
            }

            @Override // java9.util.stream.Node
            public Node.OfLong truncate(long j, long j2, IntFunction<Long[]> intFunction) {
                return OfLong.truncate(this, j, j2, intFunction);
            }

            @Override // java9.util.stream.Node
            public Spliterator.OfLong spliterator() {
                return new InternalNodeSpliterator.OfLong(this);
            }

            @Override // java9.util.stream.Node
            public void copyInto(Long[] lArr, int i) {
                OfLong.copyInto(this, lArr, i);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public long[] newArray(int i) {
                return OfLong.newArray(this, i);
            }
        }

        static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, Node.OfDouble> implements Node.OfDouble {
            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Double[]>) intFunction);
            }

            @Override // java9.util.stream.Node
            public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
                return truncate(j, j2, (IntFunction<Double[]>) intFunction);
            }

            OfDouble(Node.OfDouble ofDouble, Node.OfDouble ofDouble2) {
                super(ofDouble, ofDouble2);
            }

            @Override // java9.util.stream.Node
            public void forEach(Consumer<? super Double> consumer) {
                OfDouble.forEach(this, consumer);
            }

            @Override // java9.util.stream.Node
            public Node.OfDouble truncate(long j, long j2, IntFunction<Double[]> intFunction) {
                return OfDouble.truncate(this, j, j2, intFunction);
            }

            @Override // java9.util.stream.Node
            public Spliterator.OfDouble spliterator() {
                return new InternalNodeSpliterator.OfDouble(this);
            }

            @Override // java9.util.stream.Node
            public void copyInto(Double[] dArr, int i) {
                OfDouble.copyInto(this, dArr, i);
            }

            @Override // java9.util.stream.Node.OfPrimitive
            public double[] newArray(int i) {
                return OfDouble.newArray(this, i);
            }
        }
    }

    private static abstract class InternalNodeSpliterator<T, S extends Spliterator<T>, N extends Node<T>> implements Spliterator<T> {
        int curChildIndex;
        N curNode;
        S lastNodeSpliterator;
        S tryAdvanceSpliterator;
        Deque<N> tryAdvanceStack;

        @Override // java9.util.Spliterator
        public final int characteristics() {
            return 64;
        }

        InternalNodeSpliterator(N n) {
            this.curNode = n;
        }

        protected final Deque<N> initStack() {
            ArrayDeque arrayDeque = new ArrayDeque(8);
            int childCount = this.curNode.getChildCount();
            while (true) {
                childCount--;
                if (childCount < this.curChildIndex) {
                    return arrayDeque;
                }
                arrayDeque.addFirst(this.curNode.getChild(childCount));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        protected final N findNextLeafNode(Deque<N> deque) {
            while (true) {
                N n = (N) deque.pollFirst();
                if (n == null) {
                    return null;
                }
                if (n.getChildCount() != 0) {
                    for (int childCount = n.getChildCount() - 1; childCount >= 0; childCount--) {
                        deque.addFirst(n.getChild(childCount));
                    }
                } else if (n.count() > 0) {
                    return n;
                }
            }
        }

        protected final boolean initTryAdvance() {
            if (this.curNode == null) {
                return false;
            }
            if (this.tryAdvanceSpliterator != null) {
                return true;
            }
            S s = this.lastNodeSpliterator;
            if (s == null) {
                Deque<N> dequeInitStack = initStack();
                this.tryAdvanceStack = dequeInitStack;
                Node nodeFindNextLeafNode = findNextLeafNode(dequeInitStack);
                if (nodeFindNextLeafNode != null) {
                    this.tryAdvanceSpliterator = (S) nodeFindNextLeafNode.spliterator();
                    return true;
                }
                this.curNode = null;
                return false;
            }
            this.tryAdvanceSpliterator = s;
            return true;
        }

        @Override // java9.util.Spliterator
        public final S trySplit() {
            if (this.curNode == null || this.tryAdvanceSpliterator != null) {
                return null;
            }
            S s = this.lastNodeSpliterator;
            if (s != null) {
                return (S) s.trySplit();
            }
            if (this.curChildIndex < r0.getChildCount() - 1) {
                N n = this.curNode;
                int i = this.curChildIndex;
                this.curChildIndex = i + 1;
                return n.getChild(i).spliterator();
            }
            N n2 = (N) this.curNode.getChild(this.curChildIndex);
            this.curNode = n2;
            if (n2.getChildCount() == 0) {
                S s2 = (S) this.curNode.spliterator();
                this.lastNodeSpliterator = s2;
                return (S) s2.trySplit();
            }
            N n3 = this.curNode;
            this.curChildIndex = 0 + 1;
            return n3.getChild(0).spliterator();
        }

        @Override // java9.util.Spliterator
        public final long estimateSize() {
            long jCount = 0;
            if (this.curNode == null) {
                return 0L;
            }
            S s = this.lastNodeSpliterator;
            if (s != null) {
                return s.estimateSize();
            }
            for (int i = this.curChildIndex; i < this.curNode.getChildCount(); i++) {
                jCount += this.curNode.getChild(i).count();
            }
            return jCount;
        }

        private static final class OfRef<T> extends InternalNodeSpliterator<T, Spliterator<T>, Node<T>> {
            OfRef(Node<T> node) {
                super(node);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super T> consumer) {
                Node<T> nodeFindNextLeafNode;
                if (!initTryAdvance()) {
                    return false;
                }
                boolean zTryAdvance = this.tryAdvanceSpliterator.tryAdvance(consumer);
                if (!zTryAdvance) {
                    if (this.lastNodeSpliterator == null && (nodeFindNextLeafNode = findNextLeafNode(this.tryAdvanceStack)) != null) {
                        this.tryAdvanceSpliterator = nodeFindNextLeafNode.spliterator();
                        return this.tryAdvanceSpliterator.tryAdvance(consumer);
                    }
                    this.curNode = null;
                }
                return zTryAdvance;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super T> consumer) {
                if (this.curNode == null) {
                    return;
                }
                if (this.tryAdvanceSpliterator == null) {
                    if (this.lastNodeSpliterator == null) {
                        Deque dequeInitStack = initStack();
                        while (true) {
                            Node nodeFindNextLeafNode = findNextLeafNode(dequeInitStack);
                            if (nodeFindNextLeafNode != null) {
                                nodeFindNextLeafNode.forEach(consumer);
                            } else {
                                this.curNode = null;
                                return;
                            }
                        }
                    } else {
                        this.lastNodeSpliterator.forEachRemaining(consumer);
                    }
                } else {
                    while (tryAdvance(consumer)) {
                    }
                }
            }

            @Override // java9.util.Spliterator
            public long getExactSizeIfKnown() {
                return Spliterators.getExactSizeIfKnown(this);
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }

            @Override // java9.util.Spliterator
            public Comparator<? super T> getComparator() {
                return Spliterators.getComparator(this);
            }
        }

        private static abstract class OfPrimitive<T, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, N extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, N>> extends InternalNodeSpliterator<T, T_SPLITR, N> implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            @Override // java9.util.stream.Nodes.InternalNodeSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfPrimitive(N n) {
                super(n);
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS t_cons) {
                N nFindNextLeafNode;
                if (!initTryAdvance()) {
                    return false;
                }
                boolean zTryAdvance = ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance(t_cons);
                if (!zTryAdvance) {
                    if (this.lastNodeSpliterator == null && (nFindNextLeafNode = findNextLeafNode(this.tryAdvanceStack)) != null) {
                        this.tryAdvanceSpliterator = nFindNextLeafNode.spliterator();
                        return ((Spliterator.OfPrimitive) this.tryAdvanceSpliterator).tryAdvance(t_cons);
                    }
                    this.curNode = null;
                }
                return zTryAdvance;
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS t_cons) {
                if (this.curNode == null) {
                    return;
                }
                if (this.tryAdvanceSpliterator == null) {
                    if (this.lastNodeSpliterator == null) {
                        Deque<N> dequeInitStack = initStack();
                        while (true) {
                            N nFindNextLeafNode = findNextLeafNode(dequeInitStack);
                            if (nFindNextLeafNode != null) {
                                nFindNextLeafNode.forEach(t_cons);
                            } else {
                                this.curNode = null;
                                return;
                            }
                        }
                    } else {
                        ((Spliterator.OfPrimitive) this.lastNodeSpliterator).forEachRemaining(t_cons);
                    }
                } else {
                    while (tryAdvance(t_cons)) {
                    }
                }
            }

            @Override // java9.util.Spliterator
            public long getExactSizeIfKnown() {
                return Spliterators.getExactSizeIfKnown(this);
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }

            @Override // java9.util.Spliterator
            public Comparator<? super T> getComparator() {
                return Spliterators.getComparator(this);
            }
        }

        private static final class OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, Node.OfInt> implements Spliterator.OfInt {
            @Override // java9.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining(intConsumer);
            }

            @Override // java9.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance(intConsumer);
            }

            @Override // java9.util.stream.Nodes.InternalNodeSpliterator.OfPrimitive, java9.util.stream.Nodes.InternalNodeSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            OfInt(Node.OfInt ofInt) {
                super(ofInt);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Integer> consumer) {
                return Spliterators.OfInt.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Integer> consumer) {
                Spliterators.OfInt.forEachRemaining(this, consumer);
            }
        }

        private static final class OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, Node.OfLong> implements Spliterator.OfLong {
            @Override // java9.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining(longConsumer);
            }

            @Override // java9.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance(longConsumer);
            }

            @Override // java9.util.stream.Nodes.InternalNodeSpliterator.OfPrimitive, java9.util.stream.Nodes.InternalNodeSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            OfLong(Node.OfLong ofLong) {
                super(ofLong);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Long> consumer) {
                return Spliterators.OfLong.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Long> consumer) {
                Spliterators.OfLong.forEachRemaining(this, consumer);
            }
        }

        private static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, Node.OfDouble> implements Spliterator.OfDouble {
            @Override // java9.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining(doubleConsumer);
            }

            @Override // java9.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance(doubleConsumer);
            }

            @Override // java9.util.stream.Nodes.InternalNodeSpliterator.OfPrimitive, java9.util.stream.Nodes.InternalNodeSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            OfDouble(Node.OfDouble ofDouble) {
                super(ofDouble);
            }

            @Override // java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Double> consumer) {
                return Spliterators.OfDouble.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Double> consumer) {
                Spliterators.OfDouble.forEachRemaining(this, consumer);
            }
        }
    }

    private static final class FixedNodeBuilder<T> extends ArrayNode<T> implements Node.Builder<T> {
        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        FixedNodeBuilder(long j, IntFunction<T[]> intFunction) {
            super(j, intFunction);
        }

        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<T> build2() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override // java9.util.function.Consumer
        public void accept(T t) {
            if (this.curSize < this.array.length) {
                T[] tArr = this.array;
                int i = this.curSize;
                this.curSize = i + 1;
                tArr[i] = t;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
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

        @Override // java9.util.stream.Nodes.ArrayNode
        public String toString() {
            return String.format("FixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class SpinedNodeBuilder<T> extends SpinedBuffer<T> implements Node<T>, Node.Builder<T> {
        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<T> build2() {
            return this;
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        SpinedNodeBuilder() {
        }

        @Override // java9.util.stream.SpinedBuffer, java9.util.stream.Node
        public Spliterator<T> spliterator() {
            return super.spliterator();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.SpinedBuffer, java9.util.stream.Node
        public void forEach(Consumer<? super T> consumer) {
            super.forEach(consumer);
        }

        @Override // java9.util.stream.Node
        public StreamShape getShape() {
            return Nodes.getShape();
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node<T> getChild(int i) {
            return Nodes.getChild();
        }

        @Override // java9.util.stream.Node
        public Node<T> truncate(long j, long j2, IntFunction<T[]> intFunction) {
            return Nodes.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            clear();
            ensureCapacity(j);
        }

        @Override // java9.util.stream.SpinedBuffer, java9.util.function.Consumer
        public void accept(T t) {
            super.accept(t);
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

        @Override // java9.util.stream.SpinedBuffer, java9.util.stream.Node
        public void copyInto(T[] tArr, int i) {
            super.copyInto(tArr, i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java9.util.stream.SpinedBuffer, java9.util.stream.Node
        public T[] asArray(IntFunction<T[]> intFunction) {
            return (T[]) super.asArray(intFunction);
        }
    }

    private static class IntArrayNode implements Node.OfInt {
        final int[] array;
        int curSize;

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return asArray((IntFunction<Integer[]>) intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
        }

        @Override // java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
        }

        IntArrayNode(long j) {
            if (j >= Nodes.MAX_ARRAY_SIZE) {
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }
            this.array = new int[(int) j];
            this.curSize = 0;
        }

        IntArrayNode(int[] iArr) {
            this.array = iArr;
            this.curSize = iArr.length;
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node.OfInt, java9.util.stream.Node
        public void forEach(Consumer<? super Integer> consumer) {
            OfInt.forEach(this, consumer);
        }

        @Override // java9.util.stream.Node
        public Node.OfInt getChild(int i) {
            return (Node.OfInt) OfPrimitive.getChild();
        }

        @Override // java9.util.stream.Node
        public Spliterator.OfInt spliterator() {
            return J8Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java9.util.stream.Node.OfInt, java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Node.OfInt truncate(long j, long j2, IntFunction<Integer[]> intFunction) {
            return OfInt.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public void copyInto(Integer[] numArr, int i) {
            OfInt.copyInto(this, numArr, i);
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public int[] asPrimitiveArray() {
            int[] iArr = this.array;
            int length = iArr.length;
            int i = this.curSize;
            return length == i ? iArr : Arrays.copyOf(iArr, i);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Integer[] asArray(IntFunction<Integer[]> intFunction) {
            return (Integer[]) OfPrimitive.asArray(this, intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public int[] newArray(int i) {
            return OfInt.newArray(this, i);
        }

        @Override // java9.util.stream.Node.OfInt, java9.util.stream.Node
        public StreamShape getShape() {
            return OfInt.getShape();
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public void copyInto(int[] iArr, int i) {
            System.arraycopy(this.array, 0, iArr, i, this.curSize);
        }

        @Override // java9.util.stream.Node
        public long count() {
            return this.curSize;
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public void forEach(IntConsumer intConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                intConsumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("IntArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static class LongArrayNode implements Node.OfLong {
        final long[] array;
        int curSize;

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return asArray((IntFunction<Long[]>) intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Long[]>) intFunction);
        }

        @Override // java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Long[]>) intFunction);
        }

        LongArrayNode(long j) {
            if (j >= Nodes.MAX_ARRAY_SIZE) {
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }
            this.array = new long[(int) j];
            this.curSize = 0;
        }

        LongArrayNode(long[] jArr) {
            this.array = jArr;
            this.curSize = jArr.length;
        }

        @Override // java9.util.stream.Node.OfLong, java9.util.stream.Node
        public void forEach(Consumer<? super Long> consumer) {
            OfLong.forEach(this, consumer);
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node.OfLong getChild(int i) {
            return (Node.OfLong) OfPrimitive.getChild();
        }

        @Override // java9.util.stream.Node
        public void copyInto(Long[] lArr, int i) {
            OfLong.copyInto(this, lArr, i);
        }

        @Override // java9.util.stream.Node.OfLong, java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Node.OfLong truncate(long j, long j2, IntFunction<Long[]> intFunction) {
            return OfLong.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public Spliterator.OfLong spliterator() {
            return J8Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public long[] asPrimitiveArray() {
            long[] jArr = this.array;
            int length = jArr.length;
            int i = this.curSize;
            return length == i ? jArr : Arrays.copyOf(jArr, i);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Long[] asArray(IntFunction<Long[]> intFunction) {
            return (Long[]) OfPrimitive.asArray(this, intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public long[] newArray(int i) {
            return OfLong.newArray(this, i);
        }

        @Override // java9.util.stream.Node.OfLong, java9.util.stream.Node
        public StreamShape getShape() {
            return OfLong.getShape();
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public void copyInto(long[] jArr, int i) {
            System.arraycopy(this.array, 0, jArr, i, this.curSize);
        }

        @Override // java9.util.stream.Node
        public long count() {
            return this.curSize;
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public void forEach(LongConsumer longConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                longConsumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("LongArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static class DoubleArrayNode implements Node.OfDouble {
        final double[] array;
        int curSize;

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return asArray((IntFunction<Double[]>) intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Double[]>) intFunction);
        }

        @Override // java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Double[]>) intFunction);
        }

        DoubleArrayNode(long j) {
            if (j >= Nodes.MAX_ARRAY_SIZE) {
                throw new IllegalArgumentException(Nodes.BAD_SIZE);
            }
            this.array = new double[(int) j];
            this.curSize = 0;
        }

        DoubleArrayNode(double[] dArr) {
            this.array = dArr;
            this.curSize = dArr.length;
        }

        @Override // java9.util.stream.Node.OfDouble, java9.util.stream.Node
        public void forEach(Consumer<? super Double> consumer) {
            OfDouble.forEach(this, consumer);
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node.OfDouble getChild(int i) {
            return (Node.OfDouble) OfPrimitive.getChild();
        }

        @Override // java9.util.stream.Node.OfDouble, java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Node.OfDouble truncate(long j, long j2, IntFunction<Double[]> intFunction) {
            return OfDouble.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public Spliterator.OfDouble spliterator() {
            return J8Arrays.spliterator(this.array, 0, this.curSize);
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public double[] asPrimitiveArray() {
            double[] dArr = this.array;
            int length = dArr.length;
            int i = this.curSize;
            return length == i ? dArr : Arrays.copyOf(dArr, i);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Double[] asArray(IntFunction<Double[]> intFunction) {
            return (Double[]) OfPrimitive.asArray(this, intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public double[] newArray(int i) {
            return OfDouble.newArray(this, i);
        }

        @Override // java9.util.stream.Node
        public void copyInto(Double[] dArr, int i) {
            OfDouble.copyInto(this, dArr, i);
        }

        @Override // java9.util.stream.Node.OfDouble, java9.util.stream.Node
        public StreamShape getShape() {
            return OfDouble.getShape();
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public void copyInto(double[] dArr, int i) {
            System.arraycopy(this.array, 0, dArr, i, this.curSize);
        }

        @Override // java9.util.stream.Node
        public long count() {
            return this.curSize;
        }

        @Override // java9.util.stream.Node.OfPrimitive
        public void forEach(DoubleConsumer doubleConsumer) {
            for (int i = 0; i < this.curSize; i++) {
                doubleConsumer.accept(this.array[i]);
            }
        }

        public String toString() {
            return String.format("DoubleArrayNode[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class IntFixedNodeBuilder extends IntArrayNode implements Node.Builder.OfInt {
        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        IntFixedNodeBuilder(long j) {
            super(j);
        }

        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<Integer> build2() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            if (this.curSize < this.array.length) {
                int[] iArr = this.array;
                int i2 = this.curSize;
                this.curSize = i2 + 1;
                iArr[i2] = i;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.function.Consumer
        public void accept(Integer num) {
            SinkDefaults.OfInt.accept(this, num);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Nodes.IntArrayNode
        public String toString() {
            return String.format("IntFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class LongFixedNodeBuilder extends LongArrayNode implements Node.Builder.OfLong {
        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        LongFixedNodeBuilder(long j) {
            super(j);
        }

        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<Long> build2() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override // java9.util.stream.Sink
        public void accept(long j) {
            if (this.curSize < this.array.length) {
                long[] jArr = this.array;
                int i = this.curSize;
                this.curSize = i + 1;
                jArr[i] = j;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.function.Consumer
        public void accept(Long l) {
            SinkDefaults.OfLong.accept(this, l);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
            }
        }

        @Override // java9.util.stream.Sink
        public void accept(int i) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            SinkDefaults.reject();
        }

        @Override // java9.util.stream.Nodes.LongArrayNode
        public String toString() {
            return String.format("LongFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class DoubleFixedNodeBuilder extends DoubleArrayNode implements Node.Builder.OfDouble {
        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        DoubleFixedNodeBuilder(long j) {
            super(j);
        }

        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
        public Node<Double> build2() {
            if (this.curSize >= this.array.length) {
                return this;
            }
            throw new IllegalStateException(String.format("Current size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            if (j != this.array.length) {
                throw new IllegalStateException(String.format("Begin size %d is not equal to fixed size %d", Long.valueOf(j), Integer.valueOf(this.array.length)));
            }
            this.curSize = 0;
        }

        @Override // java9.util.stream.Sink
        public void accept(double d) {
            if (this.curSize < this.array.length) {
                double[] dArr = this.array;
                int i = this.curSize;
                this.curSize = i + 1;
                dArr[i] = d;
                return;
            }
            throw new IllegalStateException(String.format("Accept exceeded fixed size of %d", Integer.valueOf(this.array.length)));
        }

        @Override // java9.util.function.Consumer
        public void accept(Double d) {
            SinkDefaults.OfDouble.accept(this, d);
        }

        @Override // java9.util.stream.Sink
        public void end() {
            if (this.curSize < this.array.length) {
                throw new IllegalStateException(String.format("End size %d is less than fixed size %d", Integer.valueOf(this.curSize), Integer.valueOf(this.array.length)));
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

        @Override // java9.util.stream.Nodes.DoubleArrayNode
        public String toString() {
            return String.format("DoubleFixedNodeBuilder[%d][%s]", Integer.valueOf(this.array.length - this.curSize), Arrays.toString(this.array));
        }
    }

    private static final class IntSpinedNodeBuilder extends SpinedBuffer.OfInt implements Node.OfInt, Node.Builder.OfInt {
        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<Integer> build2() {
            return this;
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return asArray((IntFunction<Integer[]>) intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
        }

        @Override // java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Integer[]>) intFunction);
        }

        IntSpinedNodeBuilder() {
        }

        @Override // java9.util.stream.Node
        public Spliterator.OfInt spliterator() {
            return super.spliterator();
        }

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public void forEach(IntConsumer intConsumer) {
            super.forEach(intConsumer);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            clear();
            ensureCapacity(j);
        }

        @Override // java9.util.stream.SpinedBuffer.OfInt, java9.util.function.IntConsumer
        public void accept(int i) {
            super.accept(i);
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

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public void copyInto(int[] iArr, int i) throws IndexOutOfBoundsException {
            super.copyInto(iArr, i);
        }

        @Override // java9.util.stream.Node
        public void copyInto(Integer[] numArr, int i) {
            OfInt.copyInto(this, numArr, i);
        }

        @Override // java9.util.stream.Node.OfInt, java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Node.OfInt truncate(long j, long j2, IntFunction<Integer[]> intFunction) {
            return OfInt.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public int[] asPrimitiveArray() {
            return (int[]) super.asPrimitiveArray();
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Integer[] asArray(IntFunction<Integer[]> intFunction) {
            return (Integer[]) OfPrimitive.asArray(this, intFunction);
        }

        @Override // java9.util.stream.Node.OfInt, java9.util.stream.Node
        public StreamShape getShape() {
            return OfInt.getShape();
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node.OfInt getChild(int i) {
            return (Node.OfInt) OfPrimitive.getChild();
        }
    }

    private static final class LongSpinedNodeBuilder extends SpinedBuffer.OfLong implements Node.OfLong, Node.Builder.OfLong {
        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<Long> build2() {
            return this;
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return asArray((IntFunction<Long[]>) intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Long[]>) intFunction);
        }

        @Override // java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Long[]>) intFunction);
        }

        LongSpinedNodeBuilder() {
        }

        @Override // java9.util.stream.Node.OfLong, java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Node.OfLong truncate(long j, long j2, IntFunction<Long[]> intFunction) {
            return OfLong.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public Spliterator.OfLong spliterator() {
            return super.spliterator();
        }

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public void forEach(LongConsumer longConsumer) {
            super.forEach(longConsumer);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            clear();
            ensureCapacity(j);
        }

        @Override // java9.util.stream.SpinedBuffer.OfLong, java9.util.function.LongConsumer
        public void accept(long j) {
            super.accept(j);
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

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public void copyInto(long[] jArr, int i) {
            super.copyInto(jArr, i);
        }

        @Override // java9.util.stream.Node
        public void copyInto(Long[] lArr, int i) {
            OfLong.copyInto(this, lArr, i);
        }

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public long[] asPrimitiveArray() {
            return (long[]) super.asPrimitiveArray();
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Long[] asArray(IntFunction<Long[]> intFunction) {
            return (Long[]) OfPrimitive.asArray(this, intFunction);
        }

        @Override // java9.util.stream.Node.OfLong, java9.util.stream.Node
        public StreamShape getShape() {
            return OfLong.getShape();
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node.OfLong getChild(int i) {
            return (Node.OfLong) OfPrimitive.getChild();
        }
    }

    private static final class DoubleSpinedNodeBuilder extends SpinedBuffer.OfDouble implements Node.OfDouble, Node.Builder.OfDouble {
        @Override // java9.util.stream.Node.Builder
        /* JADX INFO: renamed from: build */
        public Node<Double> build2() {
            return this;
        }

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Object[] asArray(IntFunction intFunction) {
            return asArray((IntFunction<Double[]>) intFunction);
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node.OfPrimitive truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Double[]>) intFunction);
        }

        @Override // java9.util.stream.Node
        public /* bridge */ /* synthetic */ Node truncate(long j, long j2, IntFunction intFunction) {
            return truncate(j, j2, (IntFunction<Double[]>) intFunction);
        }

        DoubleSpinedNodeBuilder() {
        }

        @Override // java9.util.stream.Node.OfDouble, java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Node.OfDouble truncate(long j, long j2, IntFunction<Double[]> intFunction) {
            return OfDouble.truncate(this, j, j2, intFunction);
        }

        @Override // java9.util.stream.Node
        public Spliterator.OfDouble spliterator() {
            return super.spliterator();
        }

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public void forEach(DoubleConsumer doubleConsumer) {
            super.forEach(doubleConsumer);
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            clear();
            ensureCapacity(j);
        }

        @Override // java9.util.stream.SpinedBuffer.OfDouble, java9.util.function.DoubleConsumer
        public void accept(double d) {
            super.accept(d);
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

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public void copyInto(double[] dArr, int i) {
            super.copyInto(dArr, i);
        }

        @Override // java9.util.stream.Node
        public void copyInto(Double[] dArr, int i) {
            OfDouble.copyInto(this, dArr, i);
        }

        @Override // java9.util.stream.SpinedBuffer.OfPrimitive, java9.util.stream.Node.OfPrimitive
        public double[] asPrimitiveArray() {
            return (double[]) super.asPrimitiveArray();
        }

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        public Double[] asArray(IntFunction<Double[]> intFunction) {
            return (Double[]) OfPrimitive.asArray(this, intFunction);
        }

        @Override // java9.util.stream.Node
        public int getChildCount() {
            return Nodes.getChildCount();
        }

        @Override // java9.util.stream.Node
        public Node.OfDouble getChild(int i) {
            return (Node.OfDouble) OfPrimitive.getChild();
        }

        @Override // java9.util.stream.Node.OfDouble, java9.util.stream.Node
        public StreamShape getShape() {
            return OfDouble.getShape();
        }
    }

    private static abstract class SizedCollectorTask<P_IN, P_OUT, T_SINK extends Sink<P_OUT>, K extends SizedCollectorTask<P_IN, P_OUT, T_SINK, K>> extends CountedCompleter<Void> implements Sink<P_OUT> {
        protected int fence;
        protected final PipelineHelper<P_OUT> helper;
        protected int index;
        protected long length;
        protected long offset;
        protected final Spliterator<P_IN> spliterator;
        protected final long targetSize;

        @Override // java9.util.stream.Sink
        public boolean cancellationRequested() {
            return false;
        }

        @Override // java9.util.stream.Sink
        public void end() {
        }

        abstract K makeChild(Spliterator<P_IN> spliterator, long j, long j2);

        SizedCollectorTask(Spliterator<P_IN> spliterator, PipelineHelper<P_OUT> pipelineHelper, int i) {
            this.spliterator = spliterator;
            this.helper = pipelineHelper;
            this.targetSize = AbstractTask.suggestTargetSize(spliterator.estimateSize());
            this.offset = 0L;
            this.length = i;
        }

        SizedCollectorTask(K k, Spliterator<P_IN> spliterator, long j, long j2, int i) {
            super(k);
            this.spliterator = spliterator;
            this.helper = k.helper;
            this.targetSize = k.targetSize;
            this.offset = j;
            this.length = j2;
            if (j < 0 || j2 < 0 || (j + j2) - 1 >= i) {
                throw new IllegalArgumentException(String.format("offset and length interval [%d, %d + %d) is not within array size interval [0, %d)", Long.valueOf(j), Long.valueOf(j), Long.valueOf(j2), Integer.valueOf(i)));
            }
        }

        @Override // java9.util.concurrent.CountedCompleter
        public void compute() {
            Spliterator<P_IN> spliteratorTrySplit;
            Spliterator<P_IN> spliterator = this.spliterator;
            SizedCollectorTask<P_IN, P_OUT, T_SINK, K> sizedCollectorTaskMakeChild = this;
            while (spliterator.estimateSize() > sizedCollectorTaskMakeChild.targetSize && (spliteratorTrySplit = spliterator.trySplit()) != null) {
                sizedCollectorTaskMakeChild.setPendingCount(1);
                long jEstimateSize = spliteratorTrySplit.estimateSize();
                sizedCollectorTaskMakeChild.makeChild(spliteratorTrySplit, sizedCollectorTaskMakeChild.offset, jEstimateSize).fork();
                sizedCollectorTaskMakeChild = sizedCollectorTaskMakeChild.makeChild(spliterator, sizedCollectorTaskMakeChild.offset + jEstimateSize, sizedCollectorTaskMakeChild.length - jEstimateSize);
            }
            sizedCollectorTaskMakeChild.helper.wrapAndCopyInto(sizedCollectorTaskMakeChild, (Spliterator) spliterator);
            sizedCollectorTaskMakeChild.propagateCompletion();
        }

        @Override // java9.util.stream.Sink
        public void begin(long j) {
            long j2 = this.length;
            if (j > j2) {
                throw new IllegalStateException("size passed to Sink.begin exceeds array length");
            }
            int i = (int) this.offset;
            this.index = i;
            this.fence = i + ((int) j2);
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

        static final class OfRef<P_IN, P_OUT> extends SizedCollectorTask<P_IN, P_OUT, Sink<P_OUT>, OfRef<P_IN, P_OUT>> implements Sink<P_OUT> {
            private final P_OUT[] array;

            OfRef(Spliterator<P_IN> spliterator, PipelineHelper<P_OUT> pipelineHelper, P_OUT[] p_outArr) {
                super(spliterator, pipelineHelper, p_outArr.length);
                this.array = p_outArr;
            }

            OfRef(OfRef<P_IN, P_OUT> ofRef, Spliterator<P_IN> spliterator, long j, long j2) {
                super(ofRef, spliterator, j, j2, ofRef.array.length);
                this.array = ofRef.array;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.Nodes.SizedCollectorTask
            public OfRef<P_IN, P_OUT> makeChild(Spliterator<P_IN> spliterator, long j, long j2) {
                return new OfRef<>(this, spliterator, j, j2);
            }

            @Override // java9.util.function.Consumer
            public void accept(P_OUT p_out) {
                if (this.index >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                P_OUT[] p_outArr = this.array;
                int i = this.index;
                this.index = i + 1;
                p_outArr[i] = p_out;
            }
        }

        static final class OfInt<P_IN> extends SizedCollectorTask<P_IN, Integer, Sink.OfInt, OfInt<P_IN>> implements Sink.OfInt {
            private final int[] array;

            OfInt(Spliterator<P_IN> spliterator, PipelineHelper<Integer> pipelineHelper, int[] iArr) {
                super(spliterator, pipelineHelper, iArr.length);
                this.array = iArr;
            }

            OfInt(OfInt<P_IN> ofInt, Spliterator<P_IN> spliterator, long j, long j2) {
                super(ofInt, spliterator, j, j2, ofInt.array.length);
                this.array = ofInt.array;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.Nodes.SizedCollectorTask
            public OfInt<P_IN> makeChild(Spliterator<P_IN> spliterator, long j, long j2) {
                return new OfInt<>(this, spliterator, j, j2);
            }

            @Override // java9.util.stream.Nodes.SizedCollectorTask, java9.util.stream.Sink
            public void accept(int i) {
                if (this.index >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                int[] iArr = this.array;
                int i2 = this.index;
                this.index = i2 + 1;
                iArr[i2] = i;
            }

            @Override // java9.util.function.Consumer
            public void accept(Integer num) {
                SinkDefaults.OfInt.accept(this, num);
            }
        }

        static final class OfLong<P_IN> extends SizedCollectorTask<P_IN, Long, Sink.OfLong, OfLong<P_IN>> implements Sink.OfLong {
            private final long[] array;

            OfLong(Spliterator<P_IN> spliterator, PipelineHelper<Long> pipelineHelper, long[] jArr) {
                super(spliterator, pipelineHelper, jArr.length);
                this.array = jArr;
            }

            OfLong(OfLong<P_IN> ofLong, Spliterator<P_IN> spliterator, long j, long j2) {
                super(ofLong, spliterator, j, j2, ofLong.array.length);
                this.array = ofLong.array;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.Nodes.SizedCollectorTask
            public OfLong<P_IN> makeChild(Spliterator<P_IN> spliterator, long j, long j2) {
                return new OfLong<>(this, spliterator, j, j2);
            }

            @Override // java9.util.stream.Nodes.SizedCollectorTask, java9.util.stream.Sink
            public void accept(long j) {
                if (this.index >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                long[] jArr = this.array;
                int i = this.index;
                this.index = i + 1;
                jArr[i] = j;
            }

            @Override // java9.util.function.Consumer
            public void accept(Long l) {
                SinkDefaults.OfLong.accept(this, l);
            }
        }

        static final class OfDouble<P_IN> extends SizedCollectorTask<P_IN, Double, Sink.OfDouble, OfDouble<P_IN>> implements Sink.OfDouble {
            private final double[] array;

            OfDouble(Spliterator<P_IN> spliterator, PipelineHelper<Double> pipelineHelper, double[] dArr) {
                super(spliterator, pipelineHelper, dArr.length);
                this.array = dArr;
            }

            OfDouble(OfDouble<P_IN> ofDouble, Spliterator<P_IN> spliterator, long j, long j2) {
                super(ofDouble, spliterator, j, j2, ofDouble.array.length);
                this.array = ofDouble.array;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.Nodes.SizedCollectorTask
            public OfDouble<P_IN> makeChild(Spliterator<P_IN> spliterator, long j, long j2) {
                return new OfDouble<>(this, spliterator, j, j2);
            }

            @Override // java9.util.stream.Nodes.SizedCollectorTask, java9.util.stream.Sink
            public void accept(double d) {
                if (this.index >= this.fence) {
                    throw new IndexOutOfBoundsException(Integer.toString(this.index));
                }
                double[] dArr = this.array;
                int i = this.index;
                this.index = i + 1;
                dArr[i] = d;
            }

            @Override // java9.util.function.Consumer
            public void accept(Double d) {
                SinkDefaults.OfDouble.accept(this, d);
            }
        }
    }

    private static abstract class ToArrayTask<T, T_NODE extends Node<T>, K extends ToArrayTask<T, T_NODE, K>> extends CountedCompleter<Void> {
        protected final T_NODE node;
        protected final int offset;

        abstract void copyNodeToArray();

        abstract K makeChild(int i, int i2);

        ToArrayTask(T_NODE t_node, int i) {
            this.node = t_node;
            this.offset = i;
        }

        ToArrayTask(K k, T_NODE t_node, int i) {
            super(k);
            this.node = t_node;
            this.offset = i;
        }

        @Override // java9.util.concurrent.CountedCompleter
        public void compute() {
            ToArrayTask<T, T_NODE, K> toArrayTaskMakeChild = this;
            while (toArrayTaskMakeChild.node.getChildCount() != 0) {
                toArrayTaskMakeChild.setPendingCount(toArrayTaskMakeChild.node.getChildCount() - 1);
                int i = 0;
                int iCount = 0;
                while (i < toArrayTaskMakeChild.node.getChildCount() - 1) {
                    ToArrayTask toArrayTaskMakeChild2 = toArrayTaskMakeChild.makeChild(i, toArrayTaskMakeChild.offset + iCount);
                    iCount = (int) (((long) iCount) + toArrayTaskMakeChild2.node.count());
                    toArrayTaskMakeChild2.fork();
                    i++;
                }
                toArrayTaskMakeChild = toArrayTaskMakeChild.makeChild(i, toArrayTaskMakeChild.offset + iCount);
            }
            toArrayTaskMakeChild.copyNodeToArray();
            toArrayTaskMakeChild.propagateCompletion();
        }

        private static final class OfRef<T> extends ToArrayTask<T, Node<T>, OfRef<T>> {
            private final T[] array;

            /* synthetic */ OfRef(Node node, Object[] objArr, int i, AnonymousClass1 anonymousClass1) {
                this(node, objArr, i);
            }

            private OfRef(Node<T> node, T[] tArr, int i) {
                super(node, i);
                this.array = tArr;
            }

            private OfRef(OfRef<T> ofRef, Node<T> node, int i) {
                super(ofRef, node, i);
                this.array = ofRef.array;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.Nodes.ToArrayTask
            public OfRef<T> makeChild(int i, int i2) {
                return new OfRef<>(this, this.node.getChild(i), i2);
            }

            @Override // java9.util.stream.Nodes.ToArrayTask
            void copyNodeToArray() {
                this.node.copyInto(this.array, this.offset);
            }
        }

        private static class OfPrimitive<T, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, T_NODE extends Node.OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> extends ToArrayTask<T, T_NODE, OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> {
            private final T_ARR array;

            /* synthetic */ OfPrimitive(Node.OfPrimitive ofPrimitive, Object obj, int i, AnonymousClass1 anonymousClass1) {
                this(ofPrimitive, obj, i);
            }

            private OfPrimitive(T_NODE t_node, T_ARR t_arr, int i) {
                super(t_node, i);
                this.array = t_arr;
            }

            private OfPrimitive(OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> ofPrimitive, T_NODE t_node, int i) {
                super(ofPrimitive, t_node, i);
                this.array = ofPrimitive.array;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // java9.util.stream.Nodes.ToArrayTask
            public OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE> makeChild(int i, int i2) {
                return new OfPrimitive<>(this, ((Node.OfPrimitive) this.node).getChild(i), i2);
            }

            @Override // java9.util.stream.Nodes.ToArrayTask
            void copyNodeToArray() {
                ((Node.OfPrimitive) this.node).copyInto(this.array, this.offset);
            }
        }

        private static final class OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, Node.OfInt> {
            /* synthetic */ OfInt(Node.OfInt ofInt, int[] iArr, int i, AnonymousClass1 anonymousClass1) {
                this(ofInt, iArr, i);
            }

            private OfInt(Node.OfInt ofInt, int[] iArr, int i) {
                super(ofInt, iArr, i, null);
            }
        }

        private static final class OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, Node.OfLong> {
            /* synthetic */ OfLong(Node.OfLong ofLong, long[] jArr, int i, AnonymousClass1 anonymousClass1) {
                this(ofLong, jArr, i);
            }

            private OfLong(Node.OfLong ofLong, long[] jArr, int i) {
                super(ofLong, jArr, i, null);
            }
        }

        private static final class OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, Node.OfDouble> {
            /* synthetic */ OfDouble(Node.OfDouble ofDouble, double[] dArr, int i, AnonymousClass1 anonymousClass1) {
                this(ofDouble, dArr, i);
            }

            private OfDouble(Node.OfDouble ofDouble, double[] dArr, int i) {
                super(ofDouble, dArr, i, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CollectorTask<P_IN, P_OUT, T_NODE extends Node<P_OUT>, T_BUILDER extends Node.Builder<P_OUT>> extends AbstractTask<P_IN, P_OUT, T_NODE, CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER>> {
        protected final LongFunction<T_BUILDER> builderFactory;
        protected final BinaryOperator<T_NODE> concFactory;
        protected final PipelineHelper<P_OUT> helper;

        CollectorTask(PipelineHelper<P_OUT> pipelineHelper, Spliterator<P_IN> spliterator, LongFunction<T_BUILDER> longFunction, BinaryOperator<T_NODE> binaryOperator) {
            super(pipelineHelper, spliterator);
            this.helper = pipelineHelper;
            this.builderFactory = longFunction;
            this.concFactory = binaryOperator;
        }

        CollectorTask(CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> collectorTask, Spliterator<P_IN> spliterator) {
            super(collectorTask, spliterator);
            this.helper = collectorTask.helper;
            this.builderFactory = collectorTask.builderFactory;
            this.concFactory = collectorTask.concFactory;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public CollectorTask<P_IN, P_OUT, T_NODE, T_BUILDER> makeChild(Spliterator<P_IN> spliterator) {
            return new CollectorTask<>(this, spliterator);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java9.util.stream.AbstractTask
        public T_NODE doLeaf() {
            return (T_NODE) ((Node.Builder) this.helper.wrapAndCopyInto(this.builderFactory.apply(this.helper.exactOutputSizeIfKnown(this.spliterator)), (Spliterator) this.spliterator)).build2();
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
        @Override // java9.util.stream.AbstractTask, java9.util.concurrent.CountedCompleter
        public void onCompletion(CountedCompleter<?> countedCompleter) {
            if (!isLeaf()) {
                setLocalResult(this.concFactory.apply(((CollectorTask) this.leftChild).getLocalResult(), ((CollectorTask) this.rightChild).getLocalResult()));
            }
            super.onCompletion(countedCompleter);
        }

        /* JADX INFO: Access modifiers changed from: private */
        static final class OfRef<P_IN, P_OUT> extends CollectorTask<P_IN, P_OUT, Node<P_OUT>, Node.Builder<P_OUT>> {
            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }

            OfRef(PipelineHelper<P_OUT> pipelineHelper, IntFunction<P_OUT[]> intFunction, Spliterator<P_IN> spliterator) {
                super(pipelineHelper, spliterator, Nodes$CollectorTask$OfRef$$Lambda$1.lambdaFactory$(intFunction), Nodes$CollectorTask$OfRef$$Lambda$2.instance);
            }

            static /* synthetic */ Node.Builder lambda$new$0(IntFunction intFunction, long j) {
                return Nodes.builder(j, intFunction);
            }
        }

        private static final class OfInt<P_IN> extends CollectorTask<P_IN, Integer, Node.OfInt, Node.Builder.OfInt> {
            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }

            OfInt(PipelineHelper<Integer> pipelineHelper, Spliterator<P_IN> spliterator) {
                super(pipelineHelper, spliterator, Nodes$CollectorTask$OfInt$$Lambda$1.instance, Nodes$CollectorTask$OfInt$$Lambda$2.instance);
            }
        }

        private static final class OfLong<P_IN> extends CollectorTask<P_IN, Long, Node.OfLong, Node.Builder.OfLong> {
            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }

            OfLong(PipelineHelper<Long> pipelineHelper, Spliterator<P_IN> spliterator) {
                super(pipelineHelper, spliterator, Nodes$CollectorTask$OfLong$$Lambda$1.instance, Nodes$CollectorTask$OfLong$$Lambda$2.instance);
            }
        }

        private static final class OfDouble<P_IN> extends CollectorTask<P_IN, Double, Node.OfDouble, Node.Builder.OfDouble> {
            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ Object doLeaf() {
                return super.doLeaf();
            }

            @Override // java9.util.stream.Nodes.CollectorTask, java9.util.stream.AbstractTask
            protected /* bridge */ /* synthetic */ AbstractTask makeChild(Spliterator spliterator) {
                return super.makeChild(spliterator);
            }

            OfDouble(PipelineHelper<Double> pipelineHelper, Spliterator<P_IN> spliterator) {
                super(pipelineHelper, spliterator, Nodes$CollectorTask$OfDouble$$Lambda$1.instance, Nodes$CollectorTask$OfDouble$$Lambda$2.instance);
            }
        }
    }
}
