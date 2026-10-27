package java9.util;

import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java9.util.PrimitiveIterator;
import java9.util.Spliterator;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public final class Spliterators {
    private static final boolean ALLOW_RNDACC_SPLITER_OPT;
    static final boolean DELEGATION_ENABLED;
    private static final String DELEGATION_ENABLED_P;
    private static final Spliterator.OfDouble EMPTY_DOUBLE_SPLITERATOR;
    private static final Spliterator.OfInt EMPTY_INT_SPLITERATOR;
    private static final Spliterator.OfLong EMPTY_LONG_SPLITERATOR;
    private static final Spliterator<Object> EMPTY_SPLITERATOR;
    static final boolean HAS_STREAMS;
    static final boolean IS_ANDROID;
    static final boolean IS_ANDROID_O;
    static final boolean IS_HARMONY_ANDROID;
    static final boolean IS_JAVA6;
    static final boolean IS_JAVA9;
    private static final boolean IS_ROBOVM;
    private static final String NATIVE_OPT_ENABLED_P;
    static final boolean NATIVE_SPECIALIZATION;
    private static final String RNDACC_SPLITER_ENABLED_P;

    static {
        String str = Spliterators.class.getName() + ".assume.oracle.collections.impl";
        NATIVE_OPT_ENABLED_P = str;
        String str2 = Spliterators.class.getName() + ".jre.delegation.enabled";
        DELEGATION_ENABLED_P = str2;
        String str3 = Spliterators.class.getName() + ".randomaccess.spliterator.enabled";
        RNDACC_SPLITER_ENABLED_P = str3;
        NATIVE_SPECIALIZATION = getBooleanPropVal(str, true);
        DELEGATION_ENABLED = getBooleanPropVal(str2, true);
        ALLOW_RNDACC_SPLITER_OPT = getBooleanPropVal(str3, true);
        IS_ROBOVM = isRoboVm();
        boolean zIsAndroid = isAndroid();
        IS_ANDROID = zIsAndroid;
        IS_HARMONY_ANDROID = zIsAndroid && !isClassPresent("android.opengl.GLES32$DebugProc");
        IS_ANDROID_O = zIsAndroid && isClassPresent("java.time.DateTimeException");
        IS_JAVA6 = !zIsAndroid && isJava6();
        HAS_STREAMS = isStreamEnabled();
        IS_JAVA9 = isClassPresent("java.lang.StackWalker$Option");
        EMPTY_SPLITERATOR = new EmptySpliterator.OfRef();
        EMPTY_INT_SPLITERATOR = new EmptySpliterator.OfInt();
        EMPTY_LONG_SPLITERATOR = new EmptySpliterator.OfLong();
        EMPTY_DOUBLE_SPLITERATOR = new EmptySpliterator.OfDouble();
    }

    private Spliterators() {
    }

    public static <T> void forEachRemaining(Spliterator<T> spliterator, Consumer<? super T> consumer) {
        while (spliterator.tryAdvance(consumer)) {
        }
    }

    public static <T> long getExactSizeIfKnown(Spliterator<T> spliterator) {
        if ((spliterator.characteristics() & 64) == 0) {
            return -1L;
        }
        return spliterator.estimateSize();
    }

    public static <T> boolean hasCharacteristics(Spliterator<T> spliterator, int i) {
        return (spliterator.characteristics() & i) == i;
    }

    public static <T> Comparator<? super T> getComparator(Spliterator<T> spliterator) {
        throw new IllegalStateException();
    }

    public static final class OfPrimitive {
        public static <T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> void forEachRemaining(Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> ofPrimitive, T_CONS t_cons) {
            while (ofPrimitive.tryAdvance(t_cons)) {
            }
        }

        private OfPrimitive() {
        }
    }

    public static final class OfInt {
        public static void forEachRemaining(Spliterator.OfInt ofInt, IntConsumer intConsumer) {
            while (ofInt.tryAdvance(intConsumer)) {
            }
        }

        public static boolean tryAdvance(Spliterator.OfInt ofInt, Consumer<? super Integer> consumer) {
            if (consumer instanceof IntConsumer) {
                return ofInt.tryAdvance((IntConsumer) consumer);
            }
            return ofInt.tryAdvance(toIntConsumer(consumer));
        }

        private static IntConsumer toIntConsumer(Consumer<? super Integer> consumer) {
            consumer.getClass();
            return Spliterators$OfInt$$Lambda$1.lambdaFactory$(consumer);
        }

        public static void forEachRemaining(Spliterator.OfInt ofInt, Consumer<? super Integer> consumer) {
            if (consumer instanceof IntConsumer) {
                ofInt.forEachRemaining((IntConsumer) consumer);
            } else {
                ofInt.forEachRemaining(toIntConsumer(consumer));
            }
        }

        private OfInt() {
        }
    }

    public static final class OfLong {
        public static void forEachRemaining(Spliterator.OfLong ofLong, LongConsumer longConsumer) {
            while (ofLong.tryAdvance(longConsumer)) {
            }
        }

        public static boolean tryAdvance(Spliterator.OfLong ofLong, Consumer<? super Long> consumer) {
            if (consumer instanceof LongConsumer) {
                return ofLong.tryAdvance((LongConsumer) consumer);
            }
            return ofLong.tryAdvance(toLongConsumer(consumer));
        }

        private static LongConsumer toLongConsumer(Consumer<? super Long> consumer) {
            consumer.getClass();
            return Spliterators$OfLong$$Lambda$1.lambdaFactory$(consumer);
        }

        public static void forEachRemaining(Spliterator.OfLong ofLong, Consumer<? super Long> consumer) {
            if (consumer instanceof LongConsumer) {
                ofLong.forEachRemaining((LongConsumer) consumer);
            } else {
                ofLong.forEachRemaining(toLongConsumer(consumer));
            }
        }

        private OfLong() {
        }
    }

    public static final class OfDouble {
        public static void forEachRemaining(Spliterator.OfDouble ofDouble, DoubleConsumer doubleConsumer) {
            while (ofDouble.tryAdvance(doubleConsumer)) {
            }
        }

        public static boolean tryAdvance(Spliterator.OfDouble ofDouble, Consumer<? super Double> consumer) {
            if (consumer instanceof DoubleConsumer) {
                return ofDouble.tryAdvance((DoubleConsumer) consumer);
            }
            return ofDouble.tryAdvance(toDoubleConsumer(consumer));
        }

        private static DoubleConsumer toDoubleConsumer(Consumer<? super Double> consumer) {
            consumer.getClass();
            return Spliterators$OfDouble$$Lambda$1.lambdaFactory$(consumer);
        }

        public static void forEachRemaining(Spliterator.OfDouble ofDouble, Consumer<? super Double> consumer) {
            if (consumer instanceof DoubleConsumer) {
                ofDouble.forEachRemaining((DoubleConsumer) consumer);
            } else {
                ofDouble.forEachRemaining(toDoubleConsumer(consumer));
            }
        }

        private OfDouble() {
        }
    }

    public static <T> Spliterator<T> emptySpliterator() {
        return (Spliterator<T>) EMPTY_SPLITERATOR;
    }

    public static Spliterator.OfInt emptyIntSpliterator() {
        return EMPTY_INT_SPLITERATOR;
    }

    public static Spliterator.OfLong emptyLongSpliterator() {
        return EMPTY_LONG_SPLITERATOR;
    }

    public static Spliterator.OfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBLE_SPLITERATOR;
    }

    public static <T> Spliterator<T> spliterator(Object[] objArr, int i) {
        return new ArraySpliterator((Object[]) Objects.requireNonNull(objArr), i);
    }

    public static <T> Spliterator<T> spliterator(Object[] objArr, int i, int i2, int i3) {
        checkFromToBounds(((Object[]) Objects.requireNonNull(objArr)).length, i, i2);
        return new ArraySpliterator(objArr, i, i2, i3);
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i) {
        return new IntArraySpliterator((int[]) Objects.requireNonNull(iArr), i);
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2, int i3) {
        checkFromToBounds(((int[]) Objects.requireNonNull(iArr)).length, i, i2);
        return new IntArraySpliterator(iArr, i, i2, i3);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i) {
        return new LongArraySpliterator((long[]) Objects.requireNonNull(jArr), i);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2, int i3) {
        checkFromToBounds(((long[]) Objects.requireNonNull(jArr)).length, i, i2);
        return new LongArraySpliterator(jArr, i, i2, i3);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i) {
        return new DoubleArraySpliterator((double[]) Objects.requireNonNull(dArr), i);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2, int i3) {
        checkFromToBounds(((double[]) Objects.requireNonNull(dArr)).length, i, i2);
        return new DoubleArraySpliterator(dArr, i, i2, i3);
    }

    private static void checkFromToBounds(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new ArrayIndexOutOfBoundsException("origin(" + i2 + ") > fence(" + i3 + ")");
        }
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        if (i3 > i) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
    }

    public static <T> Spliterator<T> spliterator(Collection<? extends T> collection) {
        Objects.requireNonNull(collection);
        String name = collection.getClass().getName();
        if (collection instanceof List) {
            return listSpliterator((List) collection, name);
        }
        if (collection instanceof Set) {
            return setSpliterator((Set) collection, name);
        }
        if (collection instanceof Queue) {
            return queueSpliterator((Queue) collection);
        }
        if (!IS_HARMONY_ANDROID && NATIVE_SPECIALIZATION && "java.util.HashMap$Values".equals(name)) {
            return HMSpliterators.getValuesSpliterator(collection);
        }
        return spliterator(collection, 0);
    }

    private static <T> Spliterator<T> listSpliterator(List<? extends T> list, String str) {
        if (NATIVE_SPECIALIZATION || IS_ANDROID) {
            if (list instanceof ArrayList) {
                return ArrayListSpliterator.spliterator((ArrayList) list);
            }
            if ("java.util.Arrays$ArrayList".equals(str)) {
                return ArraysArrayListSpliterator.spliterator(list);
            }
            if (list instanceof CopyOnWriteArrayList) {
                return COWArrayListSpliterator.spliterator((CopyOnWriteArrayList) list);
            }
            if (list instanceof LinkedList) {
                return LinkedListSpliterator.spliterator((LinkedList) list);
            }
            if (list instanceof Vector) {
                return VectorSpliterator.spliterator((Vector) list);
            }
        }
        if (ALLOW_RNDACC_SPLITER_OPT && (list instanceof RandomAccess)) {
            if (!(list instanceof AbstractList) && isFromJdk(str)) {
                return spliterator(list, 16);
            }
            if (!(list instanceof CopyOnWriteArrayList)) {
                return RASpliterator.spliterator(list);
            }
        }
        return spliterator(list, 16);
    }

    private static <T> Spliterator<T> setSpliterator(final Set<? extends T> set, String str) {
        boolean z = IS_HARMONY_ANDROID;
        if (!z && NATIVE_SPECIALIZATION) {
            if ("java.util.HashMap$EntrySet".equals(str)) {
                return HMSpliterators.getEntrySetSpliterator(set);
            }
            if ("java.util.HashMap$KeySet".equals(str)) {
                return HMSpliterators.getKeySetSpliterator(set);
            }
        }
        if (set instanceof LinkedHashSet) {
            return spliterator(set, 17);
        }
        if (!z && NATIVE_SPECIALIZATION && (set instanceof HashSet)) {
            return HMSpliterators.getHashSetSpliterator((HashSet) set);
        }
        if (set instanceof SortedSet) {
            return new IteratorSpliterator<T>(set, 21) { // from class: java9.util.Spliterators.1
                @Override // java9.util.Spliterators.IteratorSpliterator, java9.util.Spliterator
                public Comparator<? super T> getComparator() {
                    return ((SortedSet) set).comparator();
                }
            };
        }
        if ((NATIVE_SPECIALIZATION || IS_ANDROID) && (set instanceof CopyOnWriteArraySet)) {
            return COWArraySetSpliterator.spliterator((CopyOnWriteArraySet) set);
        }
        return spliterator(set, 1);
    }

    private static <T> Spliterator<T> queueSpliterator(Queue<? extends T> queue) {
        if (queue instanceof ArrayBlockingQueue) {
            return spliterator(queue, 4368);
        }
        if (NATIVE_SPECIALIZATION || IS_ANDROID) {
            if (queue instanceof LinkedBlockingQueue) {
                return LBQSpliterator.spliterator((LinkedBlockingQueue) queue);
            }
            if (queue instanceof ArrayDeque) {
                return ArrayDequeSpliterator.spliterator((ArrayDeque) queue);
            }
            if (queue instanceof LinkedBlockingDeque) {
                return LBDSpliterator.spliterator((LinkedBlockingDeque) queue);
            }
            if (queue instanceof PriorityBlockingQueue) {
                return PBQueueSpliterator.spliterator((PriorityBlockingQueue) queue);
            }
            if (queue instanceof PriorityQueue) {
                return PQueueSpliterator.spliterator((PriorityQueue) queue);
            }
        }
        if ((queue instanceof Deque) || !(!queue.getClass().getName().startsWith("java.util") || (queue instanceof PriorityBlockingQueue) || (queue instanceof PriorityQueue) || (queue instanceof DelayQueue) || (queue instanceof SynchronousQueue))) {
            return spliterator(queue, queue instanceof ArrayDeque ? 272 : 16);
        }
        return spliterator(queue, 0);
    }

    public static <T> Spliterator<T> spliterator(Collection<? extends T> collection, int i) {
        return new IteratorSpliterator((Collection) Objects.requireNonNull(collection), i);
    }

    public static <T> Spliterator<T> spliterator(Iterator<? extends T> it, long j, int i) {
        return new IteratorSpliterator((Iterator) Objects.requireNonNull(it), j, i);
    }

    public static <T> Spliterator<T> spliteratorUnknownSize(Iterator<? extends T> it, int i) {
        return new IteratorSpliterator((Iterator) Objects.requireNonNull(it), i);
    }

    public static Spliterator.OfInt spliterator(PrimitiveIterator.OfInt ofInt, long j, int i) {
        return new IntIteratorSpliterator((PrimitiveIterator.OfInt) Objects.requireNonNull(ofInt), j, i);
    }

    public static Spliterator.OfInt spliteratorUnknownSize(PrimitiveIterator.OfInt ofInt, int i) {
        return new IntIteratorSpliterator((PrimitiveIterator.OfInt) Objects.requireNonNull(ofInt), i);
    }

    public static Spliterator.OfLong spliterator(PrimitiveIterator.OfLong ofLong, long j, int i) {
        return new LongIteratorSpliterator((PrimitiveIterator.OfLong) Objects.requireNonNull(ofLong), j, i);
    }

    public static Spliterator.OfLong spliteratorUnknownSize(PrimitiveIterator.OfLong ofLong, int i) {
        return new LongIteratorSpliterator((PrimitiveIterator.OfLong) Objects.requireNonNull(ofLong), i);
    }

    public static Spliterator.OfDouble spliterator(PrimitiveIterator.OfDouble ofDouble, long j, int i) {
        return new DoubleIteratorSpliterator((PrimitiveIterator.OfDouble) Objects.requireNonNull(ofDouble), j, i);
    }

    public static Spliterator.OfDouble spliteratorUnknownSize(PrimitiveIterator.OfDouble ofDouble, int i) {
        return new DoubleIteratorSpliterator((PrimitiveIterator.OfDouble) Objects.requireNonNull(ofDouble), i);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: java9.util.Spliterators$1Adapter, reason: invalid class name */
    class C1Adapter<T> implements Iterator<T>, Consumer<T> {
        T nextElement;
        final /* synthetic */ Spliterator val$spliterator;
        boolean valueReady = false;

        C1Adapter(Spliterator spliterator) {
            this.val$spliterator = spliterator;
        }

        @Override // java9.util.function.Consumer
        public void accept(T t) {
            this.valueReady = true;
            this.nextElement = t;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance(this);
            }
            return this.valueReady;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public static <T> Iterator<T> iterator(Spliterator<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);
        return new C1Adapter(spliterator);
    }

    /* JADX INFO: renamed from: java9.util.Spliterators$2Adapter, reason: invalid class name */
    class C2Adapter implements PrimitiveIterator.OfInt, IntConsumer {
        int nextElement;
        final /* synthetic */ Spliterator.OfInt val$spliterator;
        boolean valueReady = false;

        C2Adapter(Spliterator.OfInt ofInt) {
            this.val$spliterator = ofInt;
        }

        @Override // java9.util.function.IntConsumer
        public void accept(int i) {
            this.valueReady = true;
            this.nextElement = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((IntConsumer) this);
            }
            return this.valueReady;
        }

        @Override // java9.util.PrimitiveIterator.OfInt
        public int nextInt() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }

        @Override // java.util.Iterator
        public Integer next() {
            return Integer.valueOf(nextInt());
        }

        @Override // java9.util.PrimitiveIterator
        public void forEachRemaining(IntConsumer intConsumer) {
            Iterators.forEachRemaining(this, intConsumer);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public static PrimitiveIterator.OfInt iterator(Spliterator.OfInt ofInt) {
        Objects.requireNonNull(ofInt);
        return new C2Adapter(ofInt);
    }

    /* JADX INFO: renamed from: java9.util.Spliterators$3Adapter, reason: invalid class name */
    class C3Adapter implements PrimitiveIterator.OfLong, LongConsumer {
        long nextElement;
        final /* synthetic */ Spliterator.OfLong val$spliterator;
        boolean valueReady = false;

        C3Adapter(Spliterator.OfLong ofLong) {
            this.val$spliterator = ofLong;
        }

        @Override // java9.util.function.LongConsumer
        public void accept(long j) {
            this.valueReady = true;
            this.nextElement = j;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((LongConsumer) this);
            }
            return this.valueReady;
        }

        @Override // java9.util.PrimitiveIterator.OfLong
        public long nextLong() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }

        @Override // java.util.Iterator
        public Long next() {
            return Long.valueOf(nextLong());
        }

        @Override // java9.util.PrimitiveIterator
        public void forEachRemaining(LongConsumer longConsumer) {
            Iterators.forEachRemaining(this, longConsumer);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public static PrimitiveIterator.OfLong iterator(Spliterator.OfLong ofLong) {
        Objects.requireNonNull(ofLong);
        return new C3Adapter(ofLong);
    }

    /* JADX INFO: renamed from: java9.util.Spliterators$4Adapter, reason: invalid class name */
    class C4Adapter implements PrimitiveIterator.OfDouble, DoubleConsumer {
        double nextElement;
        final /* synthetic */ Spliterator.OfDouble val$spliterator;
        boolean valueReady = false;

        C4Adapter(Spliterator.OfDouble ofDouble) {
            this.val$spliterator = ofDouble;
        }

        @Override // java9.util.function.DoubleConsumer
        public void accept(double d) {
            this.valueReady = true;
            this.nextElement = d;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (!this.valueReady) {
                this.val$spliterator.tryAdvance((DoubleConsumer) this);
            }
            return this.valueReady;
        }

        @Override // java9.util.PrimitiveIterator.OfDouble
        public double nextDouble() {
            if (!this.valueReady && !hasNext()) {
                throw new NoSuchElementException();
            }
            this.valueReady = false;
            return this.nextElement;
        }

        @Override // java.util.Iterator
        public Double next() {
            return Double.valueOf(nextDouble());
        }

        @Override // java9.util.PrimitiveIterator
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            Iterators.forEachRemaining(this, doubleConsumer);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }

    public static PrimitiveIterator.OfDouble iterator(Spliterator.OfDouble ofDouble) {
        Objects.requireNonNull(ofDouble);
        return new C4Adapter(ofDouble);
    }

    private static abstract class EmptySpliterator<T, S extends Spliterator<T>, C> {
        public int characteristics() {
            return 16448;
        }

        public long estimateSize() {
            return 0L;
        }

        public S trySplit() {
            return null;
        }

        EmptySpliterator() {
        }

        public boolean tryAdvance(C c) {
            Objects.requireNonNull(c);
            return false;
        }

        public void forEachRemaining(C c) {
            Objects.requireNonNull(c);
        }

        private static final class OfRef<T> extends EmptySpliterator<T, Spliterator<T>, Consumer<? super T>> implements Spliterator<T> {
            @Override // java9.util.Spliterator
            public /* bridge */ /* synthetic */ void forEachRemaining(Consumer consumer) {
                super.forEachRemaining(consumer);
            }

            @Override // java9.util.Spliterator
            public /* bridge */ /* synthetic */ boolean tryAdvance(Consumer consumer) {
                return super.tryAdvance(consumer);
            }

            OfRef() {
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
                throw new IllegalStateException();
            }
        }

        private static final class OfInt extends EmptySpliterator<Integer, Spliterator.OfInt, IntConsumer> implements Spliterator.OfInt {
            @Override // java9.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining(intConsumer);
            }

            @Override // java9.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance(intConsumer);
            }

            @Override // java9.util.Spliterators.EmptySpliterator, java9.util.Spliterator.OfDouble, java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            @Override // java9.util.Spliterators.EmptySpliterator, java9.util.Spliterator.OfDouble, java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfInt() {
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
            public Comparator<? super Integer> getComparator() {
                throw new IllegalStateException();
            }

            @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Integer> consumer) {
                return OfInt.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Integer> consumer) {
                OfInt.forEachRemaining(this, consumer);
            }
        }

        private static final class OfLong extends EmptySpliterator<Long, Spliterator.OfLong, LongConsumer> implements Spliterator.OfLong {
            @Override // java9.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining(longConsumer);
            }

            @Override // java9.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance(longConsumer);
            }

            @Override // java9.util.Spliterators.EmptySpliterator, java9.util.Spliterator.OfDouble, java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            @Override // java9.util.Spliterators.EmptySpliterator, java9.util.Spliterator.OfDouble, java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfLong() {
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
            public Comparator<? super Long> getComparator() {
                throw new IllegalStateException();
            }

            @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Long> consumer) {
                return OfLong.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Long> consumer) {
                OfLong.forEachRemaining(this, consumer);
            }
        }

        private static final class OfDouble extends EmptySpliterator<Double, Spliterator.OfDouble, DoubleConsumer> implements Spliterator.OfDouble {
            @Override // java9.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining(doubleConsumer);
            }

            @Override // java9.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance(doubleConsumer);
            }

            @Override // java9.util.Spliterators.EmptySpliterator, java9.util.Spliterator.OfDouble, java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            @Override // java9.util.Spliterators.EmptySpliterator, java9.util.Spliterator.OfDouble, java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            OfDouble() {
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
            public Comparator<? super Double> getComparator() {
                throw new IllegalStateException();
            }

            @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
            public boolean tryAdvance(Consumer<? super Double> consumer) {
                return OfDouble.tryAdvance(this, consumer);
            }

            @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
            public void forEachRemaining(Consumer<? super Double> consumer) {
                OfDouble.forEachRemaining(this, consumer);
            }
        }
    }

    static final class ArraySpliterator<T> implements Spliterator<T> {
        private final Object[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public ArraySpliterator(Object[] objArr, int i) {
            this(objArr, 0, objArr.length, i);
        }

        public ArraySpliterator(Object[] objArr, int i, int i2, int i3) {
            this.array = objArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 64 | 16384;
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
        public Spliterator<T> trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            Object[] objArr = this.array;
            this.index = i2;
            return new ArraySpliterator(objArr, i, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            int i;
            Objects.requireNonNull(consumer);
            Object[] objArr = this.array;
            int length = objArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    consumer.accept(objArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            Object[] objArr = this.array;
            this.index = i + 1;
            consumer.accept(objArr[i]);
            return true;
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class IntArraySpliterator implements Spliterator.OfInt {
        private final int[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public IntArraySpliterator(int[] iArr, int i) {
            this(iArr, 0, iArr.length, i);
        }

        public IntArraySpliterator(int[] iArr, int i, int i2, int i3) {
            this.array = iArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 64 | 16384;
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfInt trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            int[] iArr = this.array;
            this.index = i2;
            return new IntArraySpliterator(iArr, i, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            int i;
            Objects.requireNonNull(intConsumer);
            int[] iArr = this.array;
            int length = iArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    intConsumer.accept(iArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            int[] iArr = this.array;
            this.index = i + 1;
            intConsumer.accept(iArr[i]);
            return true;
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return OfInt.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            OfInt.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class LongArraySpliterator implements Spliterator.OfLong {
        private final long[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public LongArraySpliterator(long[] jArr, int i) {
            this(jArr, 0, jArr.length, i);
        }

        public LongArraySpliterator(long[] jArr, int i, int i2, int i3) {
            this.array = jArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 64 | 16384;
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfLong trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            long[] jArr = this.array;
            this.index = i2;
            return new LongArraySpliterator(jArr, i, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            int i;
            Objects.requireNonNull(longConsumer);
            long[] jArr = this.array;
            int length = jArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    longConsumer.accept(jArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Long> consumer) {
            OfLong.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            long[] jArr = this.array;
            this.index = i + 1;
            longConsumer.accept(jArr[i]);
            return true;
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return OfLong.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
        public Comparator<? super Long> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class DoubleArraySpliterator implements Spliterator.OfDouble {
        private final double[] array;
        private final int characteristics;
        private final int fence;
        private int index;

        public DoubleArraySpliterator(double[] dArr, int i) {
            this(dArr, 0, dArr.length, i);
        }

        public DoubleArraySpliterator(double[] dArr, int i, int i2, int i3) {
            this.array = dArr;
            this.index = i;
            this.fence = i2;
            this.characteristics = i3 | 64 | 16384;
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            int i = this.index;
            int i2 = (this.fence + i) >>> 1;
            if (i >= i2) {
                return null;
            }
            double[] dArr = this.array;
            this.index = i2;
            return new DoubleArraySpliterator(dArr, i, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            int i;
            Objects.requireNonNull(doubleConsumer);
            double[] dArr = this.array;
            int length = dArr.length;
            int i2 = this.fence;
            if (length < i2 || (i = this.index) < 0) {
                return;
            }
            this.index = i2;
            if (i < i2) {
                do {
                    doubleConsumer.accept(dArr[i]);
                    i++;
                } while (i < i2);
            }
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Double> consumer) {
            OfDouble.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            int i = this.index;
            if (i < 0 || i >= this.fence) {
                return false;
            }
            double[] dArr = this.array;
            this.index = i + 1;
            doubleConsumer.accept(dArr[i]);
            return true;
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Double> consumer) {
            return OfDouble.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
        public Comparator<? super Double> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    public static abstract class AbstractSpliterator<T> implements Spliterator<T> {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractSpliterator(long j, int i) {
            this.est = j;
            this.characteristics = (i & 64) != 0 ? i | 16384 : i;
        }

        static final class HoldingConsumer<T> implements Consumer<T> {
            Object value;

            HoldingConsumer() {
            }

            @Override // java9.util.function.Consumer
            public void accept(T t) {
                this.value = t;
            }
        }

        @Override // java9.util.Spliterator
        public Spliterator<T> trySplit() {
            HoldingConsumer holdingConsumer = new HoldingConsumer();
            long j = this.est;
            if (j <= 1 || !tryAdvance(holdingConsumer)) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = holdingConsumer.value;
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (tryAdvance(holdingConsumer));
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new ArraySpliterator(objArr, 0, i2, characteristics());
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java9.util.Spliterator
        public long getExactSizeIfKnown() {
            if ((characteristics() & 64) == 0) {
                return -1L;
            }
            return estimateSize();
        }

        @Override // java9.util.Spliterator
        public boolean hasCharacteristics(int i) {
            return (characteristics() & i) == i;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super T> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            while (tryAdvance(consumer)) {
            }
        }
    }

    public static abstract class AbstractIntSpliterator implements Spliterator.OfInt {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractIntSpliterator(long j, int i) {
            this.est = j;
            this.characteristics = (i & 64) != 0 ? i | 16384 : i;
        }

        static final class HoldingIntConsumer implements IntConsumer {
            int value;

            HoldingIntConsumer() {
            }

            @Override // java9.util.function.IntConsumer
            public void accept(int i) {
                this.value = i;
            }
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfInt trySplit() {
            HoldingIntConsumer holdingIntConsumer = new HoldingIntConsumer();
            long j = this.est;
            if (j <= 1 || !tryAdvance((IntConsumer) holdingIntConsumer)) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            int[] iArr = new int[i];
            int i2 = 0;
            do {
                iArr[i2] = holdingIntConsumer.value;
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (tryAdvance((IntConsumer) holdingIntConsumer));
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new IntArraySpliterator(iArr, 0, i2, characteristics());
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java9.util.Spliterator
        public long getExactSizeIfKnown() {
            if ((characteristics() & 64) == 0) {
                return -1L;
            }
            return estimateSize();
        }

        @Override // java9.util.Spliterator
        public boolean hasCharacteristics(int i) {
            return (characteristics() & i) == i;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super Integer> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            while (tryAdvance(intConsumer)) {
            }
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            OfInt.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return OfInt.tryAdvance(this, consumer);
        }
    }

    public static abstract class AbstractLongSpliterator implements Spliterator.OfLong {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractLongSpliterator(long j, int i) {
            this.est = j;
            this.characteristics = (i & 64) != 0 ? i | 16384 : i;
        }

        static final class HoldingLongConsumer implements LongConsumer {
            long value;

            HoldingLongConsumer() {
            }

            @Override // java9.util.function.LongConsumer
            public void accept(long j) {
                this.value = j;
            }
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfLong trySplit() {
            HoldingLongConsumer holdingLongConsumer = new HoldingLongConsumer();
            long j = this.est;
            if (j <= 1 || !tryAdvance((LongConsumer) holdingLongConsumer)) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            long[] jArr = new long[i];
            int i2 = 0;
            do {
                jArr[i2] = holdingLongConsumer.value;
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (tryAdvance((LongConsumer) holdingLongConsumer));
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new LongArraySpliterator(jArr, 0, i2, characteristics());
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java9.util.Spliterator
        public long getExactSizeIfKnown() {
            if ((characteristics() & 64) == 0) {
                return -1L;
            }
            return estimateSize();
        }

        @Override // java9.util.Spliterator
        public boolean hasCharacteristics(int i) {
            return (characteristics() & i) == i;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super Long> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            while (tryAdvance(longConsumer)) {
            }
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Long> consumer) {
            OfLong.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return OfLong.tryAdvance(this, consumer);
        }
    }

    public static abstract class AbstractDoubleSpliterator implements Spliterator.OfDouble {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;

        protected AbstractDoubleSpliterator(long j, int i) {
            this.est = j;
            this.characteristics = (i & 64) != 0 ? i | 16384 : i;
        }

        static final class HoldingDoubleConsumer implements DoubleConsumer {
            double value;

            HoldingDoubleConsumer() {
            }

            @Override // java9.util.function.DoubleConsumer
            public void accept(double d) {
                this.value = d;
            }
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            HoldingDoubleConsumer holdingDoubleConsumer = new HoldingDoubleConsumer();
            long j = this.est;
            if (j <= 1 || !tryAdvance((DoubleConsumer) holdingDoubleConsumer)) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            double[] dArr = new double[i];
            int i2 = 0;
            do {
                dArr[i2] = holdingDoubleConsumer.value;
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (tryAdvance((DoubleConsumer) holdingDoubleConsumer));
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new DoubleArraySpliterator(dArr, 0, i2, characteristics());
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java9.util.Spliterator
        public long getExactSizeIfKnown() {
            if ((characteristics() & 64) == 0) {
                return -1L;
            }
            return estimateSize();
        }

        @Override // java9.util.Spliterator
        public boolean hasCharacteristics(int i) {
            return (characteristics() & i) == i;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super Double> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            while (tryAdvance(doubleConsumer)) {
            }
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Double> consumer) {
            OfDouble.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Double> consumer) {
            return OfDouble.tryAdvance(this, consumer);
        }
    }

    static class IteratorSpliterator<T> implements Spliterator<T> {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private final Collection<? extends T> collection;
        private long est;
        private Iterator<? extends T> it;

        public IteratorSpliterator(Collection<? extends T> collection, int i) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (i & 4096) == 0 ? i | 64 | 16384 : i;
        }

        public IteratorSpliterator(Iterator<? extends T> it, long j, int i) {
            this.collection = null;
            this.it = it;
            this.est = j;
            this.characteristics = (i & 4096) == 0 ? i | 64 | 16384 : i;
        }

        public IteratorSpliterator(Iterator<? extends T> it, int i) {
            this.collection = null;
            this.it = it;
            this.est = LongCompanionObject.MAX_VALUE;
            this.characteristics = i & (-16449);
        }

        @Override // java9.util.Spliterator
        public Spliterator<T> trySplit() {
            long size;
            Iterator<? extends T> it = this.it;
            if (it == null) {
                it = this.collection.iterator();
                this.it = it;
                size = this.collection.size();
                this.est = size;
            } else {
                size = this.est;
            }
            if (size <= 1 || !it.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > size) {
                i = (int) size;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            Object[] objArr = new Object[i];
            int i2 = 0;
            do {
                objArr[i2] = it.next();
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (it.hasNext());
            this.batch = i2;
            long j = this.est;
            if (j != LongCompanionObject.MAX_VALUE) {
                this.est = j - ((long) i2);
            }
            return new ArraySpliterator(objArr, 0, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            Iterator<? extends T> it = this.it;
            if (it == null) {
                it = this.collection.iterator();
                this.it = it;
                this.est = this.collection.size();
            }
            Iterators.forEachRemaining(it, consumer);
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            if (this.it == null) {
                this.it = this.collection.iterator();
                this.est = this.collection.size();
            }
            if (!this.it.hasNext()) {
                return false;
            }
            consumer.accept(this.it.next());
            return true;
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            if (this.it == null) {
                this.it = this.collection.iterator();
                long size = this.collection.size();
                this.est = size;
                return size;
            }
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class IntIteratorSpliterator implements Spliterator.OfInt {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;
        private PrimitiveIterator.OfInt it;

        public IntIteratorSpliterator(PrimitiveIterator.OfInt ofInt, long j, int i) {
            this.it = ofInt;
            this.est = j;
            this.characteristics = (i & 4096) == 0 ? i | 64 | 16384 : i;
        }

        public IntIteratorSpliterator(PrimitiveIterator.OfInt ofInt, int i) {
            this.it = ofInt;
            this.est = LongCompanionObject.MAX_VALUE;
            this.characteristics = i & (-16449);
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfInt trySplit() {
            PrimitiveIterator.OfInt ofInt = this.it;
            long j = this.est;
            if (j <= 1 || !ofInt.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            int[] iArr = new int[i];
            int i2 = 0;
            do {
                iArr[i2] = ofInt.nextInt();
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (ofInt.hasNext());
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new IntArraySpliterator(iArr, 0, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            Iterators.forEachRemaining(this.it, (IntConsumer) Objects.requireNonNull(intConsumer));
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            if (!this.it.hasNext()) {
                return false;
            }
            intConsumer.accept(this.it.nextInt());
            return true;
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return OfInt.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            OfInt.forEachRemaining(this, consumer);
        }
    }

    static final class LongIteratorSpliterator implements Spliterator.OfLong {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;
        private PrimitiveIterator.OfLong it;

        public LongIteratorSpliterator(PrimitiveIterator.OfLong ofLong, long j, int i) {
            this.it = ofLong;
            this.est = j;
            this.characteristics = (i & 4096) == 0 ? i | 64 | 16384 : i;
        }

        public LongIteratorSpliterator(PrimitiveIterator.OfLong ofLong, int i) {
            this.it = ofLong;
            this.est = LongCompanionObject.MAX_VALUE;
            this.characteristics = i & (-16449);
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfLong trySplit() {
            PrimitiveIterator.OfLong ofLong = this.it;
            long j = this.est;
            if (j <= 1 || !ofLong.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            long[] jArr = new long[i];
            int i2 = 0;
            do {
                jArr[i2] = ofLong.nextLong();
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (ofLong.hasNext());
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new LongArraySpliterator(jArr, 0, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            Iterators.forEachRemaining(this.it, (LongConsumer) Objects.requireNonNull(longConsumer));
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Long> consumer) {
            OfLong.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            if (!this.it.hasNext()) {
                return false;
            }
            longConsumer.accept(this.it.nextLong());
            return true;
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return OfLong.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
        public Comparator<? super Long> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    static final class DoubleIteratorSpliterator implements Spliterator.OfDouble {
        static final int BATCH_UNIT = 1024;
        static final int MAX_BATCH = 33554432;
        private int batch;
        private final int characteristics;
        private long est;
        private PrimitiveIterator.OfDouble it;

        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble ofDouble, long j, int i) {
            this.it = ofDouble;
            this.est = j;
            this.characteristics = (i & 4096) == 0 ? i | 64 | 16384 : i;
        }

        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble ofDouble, int i) {
            this.it = ofDouble;
            this.est = LongCompanionObject.MAX_VALUE;
            this.characteristics = i & (-16449);
        }

        @Override // java9.util.Spliterator
        public Spliterator.OfDouble trySplit() {
            PrimitiveIterator.OfDouble ofDouble = this.it;
            long j = this.est;
            if (j <= 1 || !ofDouble.hasNext()) {
                return null;
            }
            int i = this.batch + 1024;
            if (i > j) {
                i = (int) j;
            }
            if (i > 33554432) {
                i = 33554432;
            }
            double[] dArr = new double[i];
            int i2 = 0;
            do {
                dArr[i2] = ofDouble.nextDouble();
                i2++;
                if (i2 >= i) {
                    break;
                }
            } while (ofDouble.hasNext());
            this.batch = i2;
            long j2 = this.est;
            if (j2 != LongCompanionObject.MAX_VALUE) {
                this.est = j2 - ((long) i2);
            }
            return new DoubleArraySpliterator(dArr, 0, i2, this.characteristics);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            Iterators.forEachRemaining(this.it, (DoubleConsumer) Objects.requireNonNull(doubleConsumer));
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Double> consumer) {
            OfDouble.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            if (!this.it.hasNext()) {
                return false;
            }
            doubleConsumer.accept(this.it.nextDouble());
            return true;
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Double> consumer) {
            return OfDouble.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            return this.characteristics;
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
        public Comparator<? super Double> getComparator() {
            if (hasCharacteristics(4)) {
                return null;
            }
            throw new IllegalStateException();
        }
    }

    private static boolean getBooleanPropVal(final String str, final boolean z) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: java9.util.Spliterators.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Boolean run() {
                boolean z2 = z;
                try {
                    z2 = Boolean.parseBoolean(System.getProperty(str, Boolean.toString(z2)).trim());
                } catch (IllegalArgumentException | NullPointerException unused) {
                }
                return Boolean.valueOf(z2);
            }
        })).booleanValue();
    }

    private static boolean isClassPresent(String str) {
        Class<?> cls;
        try {
            cls = Class.forName(str, false, Spliterators.class.getClassLoader());
        } catch (Throwable unused) {
            cls = null;
        }
        return cls != null;
    }

    private static boolean isJava6() {
        return isVersionBelow("java.class.version", 51.0d);
    }

    private static boolean isRoboVm() {
        return isClassPresent("org.robovm.rt.bro.Bro");
    }

    private static boolean isAndroid() {
        return isClassPresent("android.util.DisplayMetrics") || IS_ROBOVM;
    }

    private static boolean isStreamEnabled() {
        if (!isAndroid() && isVersionBelow("java.class.version", 52.0d)) {
            return false;
        }
        String[] strArr = {"java.util.function.Consumer", "java.util.Spliterator"};
        Method declaredMethod = null;
        Class<?> cls = null;
        for (int i = 0; i < 2; i++) {
            try {
                cls = Class.forName(strArr[i]);
            } catch (Exception unused) {
                return false;
            }
        }
        if (cls != null) {
            try {
                declaredMethod = Collection.class.getDeclaredMethod("spliterator", new Class[0]);
            } catch (Exception unused2) {
                return false;
            }
        }
        return declaredMethod != null;
    }

    private static boolean isVersionBelow(String str, double d) {
        try {
            String property = System.getProperty(str);
            if (property != null) {
                return Double.parseDouble(property) < d;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean isFromJdk(String str) {
        return str.startsWith("java.util.Collections$", 0) && str.endsWith("RandomAccessList");
    }

    private static boolean hasAndroid7LHMBug(Collection<?> collection) {
        if (!IS_ANDROID || IS_HARMONY_ANDROID || IS_ANDROID_O || !collection.getClass().getName().startsWith("java.util.HashMap$")) {
            return false;
        }
        return collection.spliterator().hasCharacteristics(16);
    }
}
