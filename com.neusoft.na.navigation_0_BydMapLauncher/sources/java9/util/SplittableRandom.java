package java9.util;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicLong;
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
public final class SplittableRandom {
    private static final String BAD_BOUND = "bound must be positive";
    private static final String BAD_RANGE = "bound must be greater than origin";
    private static final String BAD_SIZE = "size must be non-negative";
    private static final double DOUBLE_UNIT = 1.1102230246251565E-16d;
    private static final long GOLDEN_GAMMA = -7046029254386353131L;
    private static final AtomicLong defaultGen = new AtomicLong(mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime()));
    private final long gamma;
    private long seed;

    private static int mix32(long j) {
        long j2 = (j ^ (j >>> 33)) * 7109453100751455733L;
        return (int) (((j2 ^ (j2 >>> 28)) * (-3808689974395783757L)) >>> 32);
    }

    private static long mix64(long j) {
        long j2 = (j ^ (j >>> 30)) * (-4658895280553007687L);
        long j3 = (j2 ^ (j2 >>> 27)) * (-7723592293110705685L);
        return j3 ^ (j3 >>> 31);
    }

    private SplittableRandom(long j, long j2) {
        this.seed = j;
        this.gamma = j2;
    }

    private static long mixGamma(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        long j4 = (j3 ^ (j3 >>> 33)) | 1;
        return Long.bitCount((j4 >>> 1) ^ j4) < 24 ? j4 ^ (-6148914691236517206L) : j4;
    }

    private long nextSeed() {
        long j = this.seed + this.gamma;
        this.seed = j;
        return j;
    }

    static {
        if (((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: java9.util.SplittableRandom.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public Boolean run() {
                return Boolean.valueOf(Boolean.getBoolean("java.util.secureRandomSeed"));
            }
        })).booleanValue()) {
            byte[] seed = SecureRandom.getSeed(8);
            long j = ((long) seed[0]) & 255;
            for (int i = 1; i < 8; i++) {
                j = (j << 8) | (((long) seed[i]) & 255);
            }
            defaultGen.set(j);
        }
    }

    final long internalNextLong(long j, long j2) {
        long jMix64 = mix64(nextSeed());
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
                jMix64 = mix64(nextSeed());
            }
        } else {
            while (true) {
                if (jMix64 >= j && jMix64 < j2) {
                    return jMix64;
                }
                jMix64 = mix64(nextSeed());
            }
        }
    }

    final int internalNextInt(int i, int i2) {
        int i3;
        int iMix32 = mix32(nextSeed());
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
                iMix322 = mix32(nextSeed()) >>> 1;
            }
        } else {
            while (true) {
                if (iMix32 >= i && iMix32 < i2) {
                    return iMix32;
                }
                iMix32 = mix32(nextSeed());
            }
        }
        return i3 + i;
    }

    final double internalNextDouble(double d, double d2) {
        double dNextLong = (nextLong() >>> 11) * DOUBLE_UNIT;
        if (d >= d2) {
            return dNextLong;
        }
        double d3 = (dNextLong * (d2 - d)) + d;
        return d3 >= d2 ? Double.longBitsToDouble(Double.doubleToLongBits(d2) - 1) : d3;
    }

    public SplittableRandom(long j) {
        this(j, GOLDEN_GAMMA);
    }

    public SplittableRandom() {
        long andAdd = defaultGen.getAndAdd(4354685564936845354L);
        this.seed = mix64(andAdd);
        this.gamma = mixGamma(andAdd + GOLDEN_GAMMA);
    }

    public SplittableRandom split() {
        return new SplittableRandom(nextLong(), mixGamma(nextSeed()));
    }

    public void nextBytes(byte[] bArr) {
        int length = bArr.length;
        int i = length >> 3;
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                break;
            }
            long jNextLong = nextLong();
            int i4 = 8;
            while (true) {
                int i5 = i4 - 1;
                if (i4 > 0) {
                    bArr[i2] = (byte) jNextLong;
                    jNextLong >>>= 8;
                    i2++;
                    i4 = i5;
                }
            }
            i = i3;
        }
        if (i2 < length) {
            long jNextLong2 = nextLong();
            while (i2 < length) {
                bArr[i2] = (byte) jNextLong2;
                jNextLong2 >>>= 8;
                i2++;
            }
        }
    }

    public int nextInt() {
        return mix32(nextSeed());
    }

    public int nextInt(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        int iMix32 = mix32(nextSeed());
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
            iMix32 = mix32(nextSeed());
        }
    }

    public int nextInt(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return internalNextInt(i, i2);
    }

    public long nextLong() {
        return mix64(nextSeed());
    }

    public long nextLong(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        long jMix64 = mix64(nextSeed());
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
            jMix64 = mix64(nextSeed());
        }
    }

    public long nextLong(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return internalNextLong(j, j2);
    }

    public double nextDouble() {
        return (mix64(nextSeed()) >>> 11) * DOUBLE_UNIT;
    }

    public double nextDouble(double d) {
        if (d <= 0.0d) {
            throw new IllegalArgumentException(BAD_BOUND);
        }
        double dMix64 = (mix64(nextSeed()) >>> 11) * DOUBLE_UNIT * d;
        return dMix64 < d ? dMix64 : Double.longBitsToDouble(Double.doubleToLongBits(d) - 1);
    }

    public double nextDouble(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return internalNextDouble(d, d2);
    }

    public boolean nextBoolean() {
        return mix32(nextSeed()) < 0;
    }

    public IntStream ints(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        return StreamSupport.intStream(new RandomIntsSpliterator(this, 0L, j, Integer.MAX_VALUE, 0), false);
    }

    public IntStream ints() {
        return StreamSupport.intStream(new RandomIntsSpliterator(this, 0L, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE, 0), false);
    }

    public IntStream ints(long j, int i, int i2) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (i >= i2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.intStream(new RandomIntsSpliterator(this, 0L, j, i, i2), false);
    }

    public IntStream ints(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.intStream(new RandomIntsSpliterator(this, 0L, LongCompanionObject.MAX_VALUE, i, i2), false);
    }

    public LongStream longs(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        return StreamSupport.longStream(new RandomLongsSpliterator(this, 0L, j, LongCompanionObject.MAX_VALUE, 0L), false);
    }

    public LongStream longs() {
        return StreamSupport.longStream(new RandomLongsSpliterator(this, 0L, LongCompanionObject.MAX_VALUE, LongCompanionObject.MAX_VALUE, 0L), false);
    }

    public LongStream longs(long j, long j2, long j3) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (j2 >= j3) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.longStream(new RandomLongsSpliterator(this, 0L, j, j2, j3), false);
    }

    public LongStream longs(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.longStream(new RandomLongsSpliterator(this, 0L, LongCompanionObject.MAX_VALUE, j, j2), false);
    }

    public DoubleStream doubles(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0L, j, Double.MAX_VALUE, 0.0d), false);
    }

    public DoubleStream doubles() {
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0L, LongCompanionObject.MAX_VALUE, Double.MAX_VALUE, 0.0d), false);
    }

    public DoubleStream doubles(long j, double d, double d2) {
        if (j < 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        if (d >= d2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0L, j, d, d2), false);
    }

    public DoubleStream doubles(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException(BAD_RANGE);
        }
        return StreamSupport.doubleStream(new RandomDoublesSpliterator(this, 0L, LongCompanionObject.MAX_VALUE, d, d2), false);
    }

    private static final class RandomIntsSpliterator implements Spliterator.OfInt {
        final int bound;
        final long fence;
        long index;
        final int origin;
        final SplittableRandom rng;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        RandomIntsSpliterator(SplittableRandom splittableRandom, long j, long j2, int i, int i2) {
            this.rng = splittableRandom;
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
            SplittableRandom splittableRandomSplit = this.rng.split();
            this.index = j2;
            return new RandomIntsSpliterator(splittableRandomSplit, j, j2, this.origin, this.bound);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            intConsumer.accept(this.rng.internalNextInt(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                SplittableRandom splittableRandom = this.rng;
                int i = this.origin;
                int i2 = this.bound;
                do {
                    intConsumer.accept(splittableRandom.internalNextInt(i, i2));
                    j++;
                } while (j < j2);
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
        public Comparator<? super Integer> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return Spliterators.OfInt.tryAdvance(this, consumer);
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
        final SplittableRandom rng;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        RandomLongsSpliterator(SplittableRandom splittableRandom, long j, long j2, long j3, long j4) {
            this.rng = splittableRandom;
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
            SplittableRandom splittableRandomSplit = this.rng.split();
            this.index = j2;
            return new RandomLongsSpliterator(splittableRandomSplit, j, j2, this.origin, this.bound);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            longConsumer.accept(this.rng.internalNextLong(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                SplittableRandom splittableRandom = this.rng;
                long j3 = this.origin;
                long j4 = this.bound;
                do {
                    longConsumer.accept(splittableRandom.internalNextLong(j3, j4));
                    j++;
                } while (j < j2);
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
        public Comparator<? super Long> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return Spliterators.OfLong.tryAdvance(this, consumer);
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
        final SplittableRandom rng;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17728;
        }

        RandomDoublesSpliterator(SplittableRandom splittableRandom, long j, long j2, double d, double d2) {
            this.rng = splittableRandom;
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
            SplittableRandom splittableRandomSplit = this.rng.split();
            this.index = j2;
            return new RandomDoublesSpliterator(splittableRandomSplit, j, j2, this.origin, this.bound);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return this.fence - this.index;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            long j = this.index;
            if (j >= this.fence) {
                return false;
            }
            doubleConsumer.accept(this.rng.internalNextDouble(this.origin, this.bound));
            this.index = j + 1;
            return true;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            long j = this.index;
            long j2 = this.fence;
            if (j < j2) {
                this.index = j2;
                SplittableRandom splittableRandom = this.rng;
                double d = this.origin;
                double d2 = this.bound;
                do {
                    doubleConsumer.accept(splittableRandom.internalNextDouble(d, d2));
                    j++;
                } while (j < j2);
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
        public Comparator<? super Double> getComparator() {
            return Spliterators.getComparator(this);
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Double> consumer) {
            return Spliterators.OfDouble.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfDouble, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Double> consumer) {
            Spliterators.OfDouble.forEachRemaining(this, consumer);
        }
    }
}
