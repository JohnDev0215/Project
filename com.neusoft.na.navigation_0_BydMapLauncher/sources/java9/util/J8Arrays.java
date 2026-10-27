package java9.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java9.util.ArrayPrefixHelpers;
import java9.util.ArraysParallelSortHelpers;
import java9.util.Spliterator;
import java9.util.concurrent.ForkJoinPool;
import java9.util.function.BinaryOperator;
import java9.util.function.DoubleBinaryOperator;
import java9.util.function.IntBinaryOperator;
import java9.util.function.IntFunction;
import java9.util.function.IntToDoubleFunction;
import java9.util.function.IntToLongFunction;
import java9.util.function.IntUnaryOperator;
import java9.util.function.LongBinaryOperator;
import java9.util.stream.DoubleStream;
import java9.util.stream.IntStream;
import java9.util.stream.IntStreams;
import java9.util.stream.LongStream;
import java9.util.stream.Stream;
import java9.util.stream.StreamSupport;

/* JADX INFO: loaded from: classes3.dex */
public final class J8Arrays {
    private static final int MIN_ARRAY_SORT_GRAN = 8192;

    public static <T> Spliterator<T> spliterator(T[] tArr) {
        return Spliterators.spliterator(tArr, 1040);
    }

    public static <T> Spliterator<T> spliterator(T[] tArr, int i, int i2) {
        return Spliterators.spliterator(tArr, i, i2, 1040);
    }

    public static Spliterator.OfInt spliterator(int[] iArr) {
        return Spliterators.spliterator(iArr, 1040);
    }

    public static Spliterator.OfInt spliterator(int[] iArr, int i, int i2) {
        return Spliterators.spliterator(iArr, i, i2, 1040);
    }

    public static Spliterator.OfLong spliterator(long[] jArr) {
        return Spliterators.spliterator(jArr, 1040);
    }

    public static Spliterator.OfLong spliterator(long[] jArr, int i, int i2) {
        return Spliterators.spliterator(jArr, i, i2, 1040);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr) {
        return Spliterators.spliterator(dArr, 1040);
    }

    public static Spliterator.OfDouble spliterator(double[] dArr, int i, int i2) {
        return Spliterators.spliterator(dArr, i, i2, 1040);
    }

    public static <T> Stream<T> stream(T[] tArr) {
        return stream(tArr, 0, tArr.length);
    }

    public static <T> Stream<T> stream(T[] tArr, int i, int i2) {
        return StreamSupport.stream(spliterator(tArr, i, i2), false);
    }

    public static IntStream stream(int[] iArr) {
        return stream(iArr, 0, iArr.length);
    }

    public static IntStream stream(int[] iArr, int i, int i2) {
        return StreamSupport.intStream(spliterator(iArr, i, i2), false);
    }

    public static LongStream stream(long[] jArr) {
        return stream(jArr, 0, jArr.length);
    }

    public static LongStream stream(long[] jArr, int i, int i2) {
        return StreamSupport.longStream(spliterator(jArr, i, i2), false);
    }

    public static DoubleStream stream(double[] dArr) {
        return stream(dArr, 0, dArr.length);
    }

    public static DoubleStream stream(double[] dArr, int i, int i2) {
        return StreamSupport.doubleStream(spliterator(dArr, i, i2), false);
    }

    static final class NaturalOrder implements Comparator<Object> {
        static final NaturalOrder INSTANCE = new NaturalOrder();

        NaturalOrder() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    public static void parallelSort(byte[] bArr) {
        int commonPoolParallelism;
        int length = bArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(bArr, 0, length - 1);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJByte.Sorter(null, bArr, new byte[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(byte[] bArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(bArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(bArr, i, i2 - 1);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJByte.Sorter(null, bArr, new byte[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static void parallelSort(char[] cArr) {
        int commonPoolParallelism;
        int length = cArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(cArr, 0, length - 1, (char[]) null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJChar.Sorter(null, cArr, new char[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(char[] cArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(cArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(cArr, i, i2 - 1, (char[]) null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJChar.Sorter(null, cArr, new char[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static void parallelSort(short[] sArr) {
        int commonPoolParallelism;
        int length = sArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(sArr, 0, length - 1, (short[]) null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJShort.Sorter(null, sArr, new short[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(short[] sArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(sArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(sArr, i, i2 - 1, (short[]) null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJShort.Sorter(null, sArr, new short[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static void parallelSort(int[] iArr) {
        int commonPoolParallelism;
        int length = iArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(iArr, 0, length - 1, (int[]) null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJInt.Sorter(null, iArr, new int[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(int[] iArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(iArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(iArr, i, i2 - 1, (int[]) null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJInt.Sorter(null, iArr, new int[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static void parallelSort(long[] jArr) {
        int commonPoolParallelism;
        int length = jArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(jArr, 0, length - 1, (long[]) null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJLong.Sorter(null, jArr, new long[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(long[] jArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(jArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(jArr, i, i2 - 1, (long[]) null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJLong.Sorter(null, jArr, new long[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static void parallelSort(float[] fArr) {
        int commonPoolParallelism;
        int length = fArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(fArr, 0, length - 1, (float[]) null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJFloat.Sorter(null, fArr, new float[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(float[] fArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(fArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(fArr, i, i2 - 1, (float[]) null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJFloat.Sorter(null, fArr, new float[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static void parallelSort(double[] dArr) {
        int commonPoolParallelism;
        int length = dArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(dArr, 0, length - 1, (double[]) null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJDouble.Sorter(null, dArr, new double[length], 0, length, 0, i <= 8192 ? 8192 : i).invoke();
        }
    }

    public static void parallelSort(double[] dArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(dArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            DualPivotQuicksort.sort(dArr, i, i2 - 1, (double[]) null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJDouble.Sorter(null, dArr, new double[i3], i, i3, 0, i4 <= 8192 ? 8192 : i4).invoke();
        }
    }

    public static <T extends Comparable<? super T>> void parallelSort(T[] tArr) {
        int commonPoolParallelism;
        int length = tArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            TimSort.sort(tArr, 0, length, NaturalOrder.INSTANCE, null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJObject.Sorter(null, tArr, (Comparable[]) Array.newInstance(tArr.getClass().getComponentType(), length), 0, length, 0, i <= 8192 ? 8192 : i, NaturalOrder.INSTANCE).invoke();
        }
    }

    public static <T extends Comparable<? super T>> void parallelSort(T[] tArr, int i, int i2) {
        int commonPoolParallelism;
        rangeCheck(tArr.length, i, i2);
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            TimSort.sort(tArr, i, i2, NaturalOrder.INSTANCE, null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJObject.Sorter(null, tArr, (Comparable[]) Array.newInstance(tArr.getClass().getComponentType(), i3), i, i3, 0, i4 <= 8192 ? 8192 : i4, NaturalOrder.INSTANCE).invoke();
        }
    }

    public static <T> void parallelSort(T[] tArr, Comparator<? super T> comparator) {
        int commonPoolParallelism;
        if (comparator == null) {
            comparator = NaturalOrder.INSTANCE;
        }
        int length = tArr.length;
        if (length <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            TimSort.sort(tArr, 0, length, comparator, null, 0, 0);
        } else {
            int i = length / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJObject.Sorter(null, tArr, (Object[]) Array.newInstance(tArr.getClass().getComponentType(), length), 0, length, 0, i <= 8192 ? 8192 : i, comparator).invoke();
        }
    }

    public static <T> void parallelSort(T[] tArr, int i, int i2, Comparator<? super T> comparator) {
        int commonPoolParallelism;
        rangeCheck(tArr.length, i, i2);
        if (comparator == null) {
            comparator = NaturalOrder.INSTANCE;
        }
        int i3 = i2 - i;
        if (i3 <= 8192 || (commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism()) == 1) {
            TimSort.sort(tArr, i, i2, comparator, null, 0, 0);
        } else {
            int i4 = i3 / (commonPoolParallelism << 2);
            new ArraysParallelSortHelpers.FJObject.Sorter(null, tArr, (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3), i, i3, 0, i4 <= 8192 ? 8192 : i4, comparator).invoke();
        }
    }

    public static <T> void setAll(T[] tArr, IntFunction<? extends T> intFunction) {
        Objects.requireNonNull(intFunction);
        for (int i = 0; i < tArr.length; i++) {
            tArr[i] = intFunction.apply(i);
        }
    }

    public static <T> void parallelSetAll(T[] tArr, IntFunction<? extends T> intFunction) {
        Objects.requireNonNull(intFunction);
        IntStreams.range(0, tArr.length).parallel().forEach(J8Arrays$$Lambda$1.lambdaFactory$(tArr, intFunction));
    }

    static /* synthetic */ void lambda$parallelSetAll$0(Object[] objArr, IntFunction intFunction, int i) {
        objArr[i] = intFunction.apply(i);
    }

    public static void setAll(int[] iArr, IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = intUnaryOperator.applyAsInt(i);
        }
    }

    public static void parallelSetAll(int[] iArr, IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        IntStreams.range(0, iArr.length).parallel().forEach(J8Arrays$$Lambda$2.lambdaFactory$(iArr, intUnaryOperator));
    }

    static /* synthetic */ void lambda$parallelSetAll$1(int[] iArr, IntUnaryOperator intUnaryOperator, int i) {
        iArr[i] = intUnaryOperator.applyAsInt(i);
    }

    public static void setAll(long[] jArr, IntToLongFunction intToLongFunction) {
        Objects.requireNonNull(intToLongFunction);
        for (int i = 0; i < jArr.length; i++) {
            jArr[i] = intToLongFunction.applyAsLong(i);
        }
    }

    public static void parallelSetAll(long[] jArr, IntToLongFunction intToLongFunction) {
        Objects.requireNonNull(intToLongFunction);
        IntStreams.range(0, jArr.length).parallel().forEach(J8Arrays$$Lambda$3.lambdaFactory$(jArr, intToLongFunction));
    }

    static /* synthetic */ void lambda$parallelSetAll$2(long[] jArr, IntToLongFunction intToLongFunction, int i) {
        jArr[i] = intToLongFunction.applyAsLong(i);
    }

    public static void setAll(double[] dArr, IntToDoubleFunction intToDoubleFunction) {
        Objects.requireNonNull(intToDoubleFunction);
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = intToDoubleFunction.applyAsDouble(i);
        }
    }

    public static void parallelSetAll(double[] dArr, IntToDoubleFunction intToDoubleFunction) {
        Objects.requireNonNull(intToDoubleFunction);
        IntStreams.range(0, dArr.length).parallel().forEach(J8Arrays$$Lambda$4.lambdaFactory$(dArr, intToDoubleFunction));
    }

    static /* synthetic */ void lambda$parallelSetAll$3(double[] dArr, IntToDoubleFunction intToDoubleFunction, int i) {
        dArr[i] = intToDoubleFunction.applyAsDouble(i);
    }

    public static <T> void parallelPrefix(T[] tArr, BinaryOperator<T> binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        if (tArr.length > 0) {
            new ArrayPrefixHelpers.CumulateTask(null, binaryOperator, tArr, 0, tArr.length).invoke();
        }
    }

    public static <T> void parallelPrefix(T[] tArr, int i, int i2, BinaryOperator<T> binaryOperator) {
        Objects.requireNonNull(binaryOperator);
        rangeCheck(tArr.length, i, i2);
        if (i < i2) {
            new ArrayPrefixHelpers.CumulateTask(null, binaryOperator, tArr, i, i2).invoke();
        }
    }

    public static void parallelPrefix(long[] jArr, LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        if (jArr.length > 0) {
            new ArrayPrefixHelpers.LongCumulateTask(null, longBinaryOperator, jArr, 0, jArr.length).invoke();
        }
    }

    public static void parallelPrefix(long[] jArr, int i, int i2, LongBinaryOperator longBinaryOperator) {
        Objects.requireNonNull(longBinaryOperator);
        rangeCheck(jArr.length, i, i2);
        if (i < i2) {
            new ArrayPrefixHelpers.LongCumulateTask(null, longBinaryOperator, jArr, i, i2).invoke();
        }
    }

    public static void parallelPrefix(double[] dArr, DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        if (dArr.length > 0) {
            new ArrayPrefixHelpers.DoubleCumulateTask(null, doubleBinaryOperator, dArr, 0, dArr.length).invoke();
        }
    }

    public static void parallelPrefix(double[] dArr, int i, int i2, DoubleBinaryOperator doubleBinaryOperator) {
        Objects.requireNonNull(doubleBinaryOperator);
        rangeCheck(dArr.length, i, i2);
        if (i < i2) {
            new ArrayPrefixHelpers.DoubleCumulateTask(null, doubleBinaryOperator, dArr, i, i2).invoke();
        }
    }

    public static void parallelPrefix(int[] iArr, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        if (iArr.length > 0) {
            new ArrayPrefixHelpers.IntCumulateTask(null, intBinaryOperator, iArr, 0, iArr.length).invoke();
        }
    }

    public static void parallelPrefix(int[] iArr, int i, int i2, IntBinaryOperator intBinaryOperator) {
        Objects.requireNonNull(intBinaryOperator);
        rangeCheck(iArr.length, i, i2);
        if (i < i2) {
            new ArrayPrefixHelpers.IntCumulateTask(null, intBinaryOperator, iArr, i, i2).invoke();
        }
    }

    public static <T> T[] toArray(Collection<T> collection, IntFunction<T[]> intFunction) {
        return (T[]) collection.toArray(intFunction.apply(0));
    }

    private static void rangeCheck(int i, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException("fromIndex(" + i2 + ") > toIndex(" + i3 + ")");
        }
        if (i2 < 0) {
            throw new ArrayIndexOutOfBoundsException(i2);
        }
        if (i3 > i) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
    }

    private J8Arrays() {
    }
}
