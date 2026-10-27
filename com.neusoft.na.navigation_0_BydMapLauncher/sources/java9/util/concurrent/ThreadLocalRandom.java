package java9.util.concurrent;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Comparator;
import java.util.Random;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;
import java9.util.stream.DoubleStream;
import java9.util.stream.IntStream;
import java9.util.stream.LongStream;
import java9.util.stream.StreamSupport;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public class ThreadLocalRandom extends Random {
    private static final String BAD_BOUND = "bound must be positive";
    private static final String BAD_RANGE = "bound must be greater than origin";
    private static final String BAD_SIZE = "size must be non-negative";
    private static final double DOUBLE_UNIT = 1.1102230246251565E-16d;
    private static final float FLOAT_UNIT = 5.9604645E-8f;
    private static final long serialVersionUID = 9123313859120073139L;
    private boolean initialized = true;
    private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField("rnd", Long.TYPE), new ObjectStreamField("initialized", Boolean.TYPE)};
    private static final ThreadLocal<Double> nextLocalGaussian = new ThreadLocal<>();
    private static final ThreadLocalRandom instance = new ThreadLocalRandom();

    private ThreadLocalRandom() {
    }

    public static ThreadLocalRandom current() {
        if (TLRandom.getThreadLocalRandomProbe() == 0) {
            TLRandom.localInit();
        }
        return instance;
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
    }

    private final long nextSeed() {
        return TLRandom.nextSeed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long internalNextLong(long j, long j2) {
        long jMix64 = TLRandom.mix64(nextSeed());
        if (j >= j2) {
            return jMix64;
        }
        long j3 = j2 - j;
        long j4 = j3 - 1;
        if ((j3 & j4) == 0) {
            return (jMix64 & j4) + j;
        }
        if (j3 > 0) {
            while (true) {
                long j5 = jMix64 >>> 1;
                long j6 = j5 + j4;
                long j7 = j5 % j3;
                if (j6 - j7 >= 0) {
                    return j7 + j;
                }
                jMix64 = TLRandom.mix64(nextSeed());
            }
        } else {
            while (true) {
                if (jMix64 >= j && jMix64 < j2) {
                    return jMix64;
                }
                jMix64 = TLRandom.mix64(nextSeed());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int internalNextInt(int i, int i2) {
        int i3;
        int iMix32 = TLRandom.mix32(nextSeed());
        if (i >= i2) {
            return iMix32;
        }
        int i4 = i2 - i;
        int i5 = i4 - 1;
        if ((i4 & i5) == 0) {
            i3 = iMix32 & i5;
        } else if (i4 > 0) {
            int iMix322 = iMix32 >>> 1;
            while (true) {
                int i6 = iMix322 + i5;
                i3 = iMix322 % i4;
                if (i6 - i3 >= 0) {
                    break;
                }
                iMix322 = TLRandom.mix32(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (iMix32 >= i && iMix32 < i2) {
                    return iMix32;
                }
                iMix32 = TLRandom.mix32(nextSeed());
            }
        }
        return i3 + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double internalNextDouble(double d, double d2) {
        double dNextLong = (nextLong() >>> 11) * DOUBLE_UNIT;
        if (d >= d2) {
            return dNextLong;
        }
        double d3 = (dNextLong * (d2 - d)) + d;
        return d3 >= d2 ? Double.longBitsToDouble(Double.doubleToLongBits(d2) - 1) : d3;
    }

    @Override // java.util.Random
    public int nextInt() {
        return TLRandom.mix32(nextSeed());
    }

    @Override // java.util.Random
    public int nextInt(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        int iMix32 = TLRandom.mix32(nextSeed());
        int i2 = i - 1;
        if ((i & i2) == 0) {
            return iMix32 & i2;
        }
        while (true) {
            int i3 = iMix32 >>> 1;
            int i4 = i3 + i2;
            int i5 = i3 % i;
            if (i4 - i5 >= 0) {
                return i5;
            }
            iMix32 = TLRandom.mix32(nextSeed());
        }
    }

    public int nextInt(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return internalNextInt(i, i2);
    }

    @Override // java.util.Random
    public long nextLong() {
        return TLRandom.mix64(nextSeed());
    }

    public long nextLong(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        long jMix64 = TLRandom.mix64(nextSeed());
        long j2 = j - 1;
        if ((j & j2) == 0) {
            return jMix64 & j2;
        }
        while (true) {
            long j3 = jMix64 >>> 1;
            long j4 = j3 + j2;
            long j5 = j3 % j;
            if (j4 - j5 >= 0) {
                return j5;
            }
            jMix64 = TLRandom.mix64(nextSeed());
        }
    }

    public long nextLong(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return internalNextLong(j, j2);
    }

    @Override // java.util.Random
    public double nextDouble() {
        return (TLRandom.mix64(nextSeed()) >>> 11) * DOUBLE_UNIT;
    }

    public double nextDouble(double d) {
        if (d <= 0.0d) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        double dMix64 = (TLRandom.mix64(nextSeed()) >>> 11) * DOUBLE_UNIT * d;
        return dMix64 < d ? dMix64 : Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
    }

    public double nextDouble(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return internalNextDouble(d, d2);
    }

    @Override // java.util.Random
    public boolean nextBoolean() {
        return TLRandom.mix32(nextSeed()) < 0;
    }

    @Override // java.util.Random
    public float nextFloat() {
        return (TLRandom.mix32(nextSeed()) >>> 8) * FLOAT_UNIT;
    }

    @Override // java.util.Random
    public double nextGaussian() {
        ThreadLocal<Double> threadLocal = nextLocalGaussian;
        Double d = threadLocal.get();
        if (d != null) {
            threadLocal.set(null);
            return d.doubleValue();
        }
        while (true) {
            double dNextDouble = (nextDouble() * 2.0d) - 1.0d;
            double dNextDouble2 = (nextDouble() * 2.0d) - 1.0d;
            double d2 = (dNextDouble * dNextDouble) + (dNextDouble2 * dNextDouble2);
            if (d2 < 1.0d && d2 != 0.0d) {
                double dSqrt = StrictMath.sqrt((StrictMath.log(d2) * (-2.0d)) / d2);
                nextLocalGaussian.set(Double.valueOf(dNextDouble2 * dSqrt));
                return dNextDouble * dSqrt;
            }
        }
    }

    @Override // java.util.Random
    public IntStream ints(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        return StreamSupport.intStream(new RandomIntsSpliterator(0L, j, Integer.MAX_VALUE, 0), false);
    }

    @Override // java.util.Random
    public IntStream ints() {
        return StreamSupport.intStream(new RandomIntsSpliterator(0L, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE, 0), false);
    }

    @Override // java.util.Random
    public IntStream ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (i >= i2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.intStream(new RandomIntsSpliterator(0L, j, i, i2), false);
    }

    @Override // java.util.Random
    public IntStream ints(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.intStream(new RandomIntsSpliterator(0L, LongCompanionObject.MAX_VALUE, i, i2), false);
    }

    @Override // java.util.Random
    public LongStream longs(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        return StreamSupport.longStream(new RandomLongsSpliterator(0L, j, LongCompanionObject.MAX_VALUE, 0L), false);
    }

    @Override // java.util.Random
    public LongStream longs() {
        return StreamSupport.longStream(new RandomLongsSpliterator(0L, LongCompanionObject.MAX_VALUE, LongCompanionObject.MAX_VALUE, 0L), false);
    }

    @Override // java.util.Random
    public LongStream longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (j2 >= j3) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.longStream(new RandomLongsSpliterator(0L, j, j2, j3), false);
    }

    @Override // java.util.Random
    public LongStream longs(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.longStream(new RandomLongsSpliterator(0L, LongCompanionObject.MAX_VALUE, j, j2), false);
    }

    @Override // java.util.Random
    public DoubleStream doubles(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, j, Double.MAX_VALUE, 0.0d), false);
    }

    @Override // java.util.Random
    public DoubleStream doubles() {
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, LongCompanionObject.MAX_VALUE, Double.MAX_VALUE, 0.0d), false);
    }

    @Override // java.util.Random
    public DoubleStream doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (d >= d2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, j, d, d2), false);
    }

    @Override // java.util.Random
    public DoubleStream doubles(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(0L, LongCompanionObject.MAX_VALUE, d, d2), false);
    }

    private static final class RandomIntsSpliterator implements Spliterator.OfInt {
        final int bound;
        final long fence;
        long index;
        final int origin;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        RandomIntsSpliterator(long j, long j2, int i, int i2) {
            this.index = j;
            this.fence = j2;
            this.origin = i;
            this.bound = i2;
        }

        @Override // java9.util.Spliterator
        public RandomIntsSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomIntsSpliterator(j, j2, this.origin, this.bound);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
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
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            intConsumer.accept(ThreadLocalRandom.current().internalNextInt(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return Spliterators.OfInt.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                int i = this.origin;
                int i2 = this.bound;
                ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
                do {
                    intConsumer.accept(threadLocalRandomCurrent.internalNextInt(i, i2));
                    j++;
                } while (j < j2);
            }
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            Spliterators.OfInt.forEachRemaining(this, consumer);
        }
    }

    private static final class RandomLongsSpliterator implements Spliterator.OfLong {
        final long bound;
        final long fence;
        long index;
        final long origin;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        RandomLongsSpliterator(long j, long j2, long j3, long j4) {
            this.index = j;
            this.fence = j2;
            this.origin = j3;
            this.bound = j4;
        }

        @Override // java9.util.Spliterator
        public RandomLongsSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomLongsSpliterator(j, j2, this.origin, this.bound);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
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
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            longConsumer.accept(ThreadLocalRandom.current().internalNextLong(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return Spliterators.OfLong.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                long j3 = this.origin;
                long j4 = this.bound;
                ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
                do {
                    longConsumer.accept(threadLocalRandomCurrent.internalNextLong(j3, j4));
                    j++;
                } while (j < j2);
            }
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Long> consumer) {
            Spliterators.OfLong.forEachRemaining(this, consumer);
        }
    }

    private static final class RandomDoublesSpliterator implements Spliterator.OfDouble {
        final double bound;
        final long fence;
        long index;
        final double origin;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        RandomDoublesSpliterator(long j, long j2, double d, double d2) {
            this.index = j;
            this.fence = j2;
            this.origin = d;
            this.bound = d2;
        }

        @Override // java9.util.Spliterator
        public RandomDoublesSpliterator trySplit() {
            long j = this.index;
            long j2 = (this.fence + j) >>> 1;
            if (j2 <= j) {
                return null;
            }
            this.index = j2;
            return new RandomDoublesSpliterator(j, j2, this.origin, this.bound);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
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
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            doubleConsumer.accept(ThreadLocalRandom.current().internalNextDouble(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Double> consumer) {
            return Spliterators.OfDouble.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                double d = this.origin;
                double d2 = this.bound;
                ThreadLocalRandom threadLocalRandomCurrent = ThreadLocalRandom.current();
                do {
                    doubleConsumer.accept(threadLocalRandomCurrent.internalNextDouble(d, d2));
                    j++;
                } while (j < j2);
            }
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Double> consumer) {
            Spliterators.OfDouble.forEachRemaining(this, consumer);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("rnd", TLRandom.getThreadLocalRandomSeed());
        putFieldPutFields.put("initialized", true);
        objectOutputStream.writeFields();
    }

    private Object readResolve() {
        return current();
    }
}
