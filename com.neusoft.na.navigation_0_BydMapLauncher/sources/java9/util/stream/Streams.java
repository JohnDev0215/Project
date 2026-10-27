package java9.util.stream;

import java.util.Comparator;
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
import java9.util.stream.SpinedBuffer;
import java9.util.stream.Stream;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
final class Streams {
    private Streams() {
        throw new Error("no instances");
    }

    static final class RangeIntSpliterator implements Spliterator.OfInt {
        private static final int BALANCED_SPLIT_THRESHOLD = 16777216;
        private static final int RIGHT_BALANCED_SPLIT_RATIO = 8;
        private int from;
        private int last;
        private final int upTo;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17749;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super Integer> getComparator() {
            return null;
        }

        RangeIntSpliterator(int i, int i2, boolean z) {
            this(i, i2, z ? 1 : 0);
        }

        private RangeIntSpliterator(int i, int i2, int i3) {
            this.from = i;
            this.upTo = i2;
            this.last = i3;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            int i = this.from;
            if (i < this.upTo) {
                this.from = i + 1;
                intConsumer.accept(i);
                return true;
            }
            if (this.last <= 0) {
                return false;
            }
            this.last = 0;
            intConsumer.accept(i);
            return true;
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return Spliterators.OfInt.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            int i = this.from;
            int i2 = this.upTo;
            int i3 = this.last;
            this.from = i2;
            this.last = 0;
            while (i < i2) {
                intConsumer.accept(i);
                i++;
            }
            if (i3 > 0) {
                intConsumer.accept(i);
            }
        }

        @Override // java9.util.Spliterator.OfInt, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            Spliterators.OfInt.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return (((long) this.upTo) - ((long) this.from)) + ((long) this.last);
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
        public Spliterator.OfInt trySplit() {
            long jEstimateSize = estimateSize();
            if (jEstimateSize <= 1) {
                return null;
            }
            int i = this.from;
            int iSplitPoint = splitPoint(jEstimateSize) + i;
            this.from = iSplitPoint;
            return new RangeIntSpliterator(i, iSplitPoint, 0);
        }

        private int splitPoint(long j) {
            return (int) (j / ((long) (j < 16777216 ? 2 : 8)));
        }
    }

    static final class RangeLongSpliterator implements Spliterator.OfLong {
        private static final long BALANCED_SPLIT_THRESHOLD = 16777216;
        private static final long RIGHT_BALANCED_SPLIT_RATIO = 8;
        private long from;
        private int last;
        private final long upTo;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17749;
        }

        @Override // java9.util.Spliterator
        public Comparator<? super Long> getComparator() {
            return null;
        }

        RangeLongSpliterator(long j, long j2, boolean z) {
            this(j, j2, z ? 1 : 0);
        }

        private RangeLongSpliterator(long j, long j2, int i) {
            this.from = j;
            this.upTo = j2;
            this.last = i;
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long j = this.from;
            if (j < this.upTo) {
                this.from = 1 + j;
                longConsumer.accept(j);
                return true;
            }
            if (this.last <= 0) {
                return false;
            }
            this.last = 0;
            longConsumer.accept(j);
            return true;
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return Spliterators.OfLong.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long j = this.from;
            long j2 = this.upTo;
            int i = this.last;
            this.from = j2;
            this.last = 0;
            while (j < j2) {
                longConsumer.accept(j);
                j = 1 + j;
            }
            if (i > 0) {
                longConsumer.accept(j);
            }
        }

        @Override // java9.util.Spliterator.OfLong, java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Long> consumer) {
            Spliterators.OfLong.forEachRemaining(this, consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return (this.upTo - this.from) + ((long) this.last);
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
        public Spliterator.OfLong trySplit() {
            long jEstimateSize = estimateSize();
            if (jEstimateSize <= 1) {
                return null;
            }
            long j = this.from;
            long jSplitPoint = j + splitPoint(jEstimateSize);
            this.from = jSplitPoint;
            return new RangeLongSpliterator(j, jSplitPoint, 0);
        }

        private long splitPoint(long j) {
            return j / (j < BALANCED_SPLIT_THRESHOLD ? 2L : 8L);
        }
    }

    private static abstract class AbstractStreamBuilderImpl<T, S extends Spliterator<T>> implements Spliterator<T> {
        int count;

        @Override // java9.util.Spliterator
        public int characteristics() {
            return 17488;
        }

        @Override // java9.util.Spliterator
        public S trySplit() {
            return null;
        }

        private AbstractStreamBuilderImpl() {
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            return (-this.count) - 1;
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

    static final class StreamBuilderImpl<T> extends AbstractStreamBuilderImpl<T, Spliterator<T>> implements Stream.Builder<T> {
        SpinedBuffer<T> buffer;
        T first;

        StreamBuilderImpl() {
            super();
        }

        StreamBuilderImpl(T t) {
            super();
            this.first = t;
            this.count = -2;
        }

        @Override // java9.util.stream.Stream.Builder, java9.util.function.Consumer
        public void accept(T t) {
            if (this.count == 0) {
                this.first = t;
                this.count++;
            } else {
                if (this.count > 0) {
                    if (this.buffer == null) {
                        SpinedBuffer<T> spinedBuffer = new SpinedBuffer<>();
                        this.buffer = spinedBuffer;
                        spinedBuffer.accept(this.first);
                        this.count++;
                    }
                    this.buffer.accept(t);
                    return;
                }
                throw new IllegalStateException();
            }
        }

        @Override // java9.util.stream.Stream.Builder
        public Stream.Builder<T> add(T t) {
            accept(t);
            return this;
        }

        @Override // java9.util.stream.Stream.Builder
        public Stream<T> build() {
            int i = this.count;
            if (i >= 0) {
                this.count = (-this.count) - 1;
                return i < 2 ? StreamSupport.stream((Spliterator) this, false) : StreamSupport.stream((Spliterator) this.buffer.spliterator(), false);
            }
            throw new IllegalStateException();
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            if (this.count != -2) {
                return false;
            }
            consumer.accept(this.first);
            this.count = -1;
            return true;
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            if (this.count == -2) {
                consumer.accept(this.first);
                this.count = -1;
            }
        }
    }

    static final class IntStreamBuilderImpl extends AbstractStreamBuilderImpl<Integer, Spliterator.OfInt> implements IntStream.Builder, Spliterator.OfInt {
        SpinedBuffer.OfInt buffer;
        int first;

        @Override // java9.util.stream.Streams.AbstractStreamBuilderImpl, java9.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
            return (Spliterator.OfInt) super.trySplit();
        }

        @Override // java9.util.stream.Streams.AbstractStreamBuilderImpl, java9.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }

        IntStreamBuilderImpl() {
            super();
        }

        IntStreamBuilderImpl(int i) {
            super();
            this.first = i;
            this.count = -2;
        }

        @Override // java9.util.stream.IntStream.Builder, java9.util.function.IntConsumer
        public void accept(int i) {
            if (this.count == 0) {
                this.first = i;
                this.count++;
            } else {
                if (this.count > 0) {
                    if (this.buffer == null) {
                        SpinedBuffer.OfInt ofInt = new SpinedBuffer.OfInt();
                        this.buffer = ofInt;
                        ofInt.accept(this.first);
                        this.count++;
                    }
                    this.buffer.accept(i);
                    return;
                }
                throw new IllegalStateException();
            }
        }

        @Override // java9.util.stream.IntStream.Builder
        public IntStream.Builder add(int i) {
            accept(i);
            return this;
        }

        @Override // java9.util.stream.IntStream.Builder
        public IntStream build() {
            int i = this.count;
            if (i >= 0) {
                this.count = (-this.count) - 1;
                return i < 2 ? StreamSupport.intStream(this, false) : StreamSupport.intStream(this.buffer.spliterator(), false);
            }
            throw new IllegalStateException();
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            if (this.count != -2) {
                return false;
            }
            intConsumer.accept(this.first);
            this.count = -1;
            return true;
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return Spliterators.OfInt.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            if (this.count == -2) {
                intConsumer.accept(this.first);
                this.count = -1;
            }
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            Spliterators.OfInt.forEachRemaining(this, consumer);
        }
    }

    static final class LongStreamBuilderImpl extends AbstractStreamBuilderImpl<Long, Spliterator.OfLong> implements LongStream.Builder, Spliterator.OfLong {
        SpinedBuffer.OfLong buffer;
        long first;

        @Override // java9.util.stream.Streams.AbstractStreamBuilderImpl, java9.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
            return (Spliterator.OfLong) super.trySplit();
        }

        @Override // java9.util.stream.Streams.AbstractStreamBuilderImpl, java9.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }

        LongStreamBuilderImpl() {
            super();
        }

        LongStreamBuilderImpl(long j) {
            super();
            this.first = j;
            this.count = -2;
        }

        @Override // java9.util.stream.LongStream.Builder, java9.util.function.LongConsumer
        public void accept(long j) {
            if (this.count == 0) {
                this.first = j;
                this.count++;
            } else {
                if (this.count > 0) {
                    if (this.buffer == null) {
                        SpinedBuffer.OfLong ofLong = new SpinedBuffer.OfLong();
                        this.buffer = ofLong;
                        ofLong.accept(this.first);
                        this.count++;
                    }
                    this.buffer.accept(j);
                    return;
                }
                throw new IllegalStateException();
            }
        }

        @Override // java9.util.stream.LongStream.Builder
        public LongStream.Builder add(long j) {
            accept(j);
            return this;
        }

        @Override // java9.util.stream.LongStream.Builder
        public LongStream build() {
            int i = this.count;
            if (i >= 0) {
                this.count = (-this.count) - 1;
                return i < 2 ? StreamSupport.longStream(this, false) : StreamSupport.longStream(this.buffer.spliterator(), false);
            }
            throw new IllegalStateException();
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            if (this.count != -2) {
                return false;
            }
            longConsumer.accept(this.first);
            this.count = -1;
            return true;
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return Spliterators.OfLong.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            if (this.count == -2) {
                longConsumer.accept(this.first);
                this.count = -1;
            }
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Long> consumer) {
            Spliterators.OfLong.forEachRemaining(this, consumer);
        }
    }

    static final class DoubleStreamBuilderImpl extends AbstractStreamBuilderImpl<Double, Spliterator.OfDouble> implements DoubleStream.Builder, Spliterator.OfDouble {
        SpinedBuffer.OfDouble buffer;
        double first;

        @Override // java9.util.stream.Streams.AbstractStreamBuilderImpl, java9.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
            return (Spliterator.OfDouble) super.trySplit();
        }

        @Override // java9.util.stream.Streams.AbstractStreamBuilderImpl, java9.util.Spliterator
        public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
            return (Spliterator.OfPrimitive) super.trySplit();
        }

        DoubleStreamBuilderImpl() {
            super();
        }

        DoubleStreamBuilderImpl(double d) {
            super();
            this.first = d;
            this.count = -2;
        }

        @Override // java9.util.stream.DoubleStream.Builder, java9.util.function.DoubleConsumer
        public void accept(double d) {
            if (this.count == 0) {
                this.first = d;
                this.count++;
            } else {
                if (this.count > 0) {
                    if (this.buffer == null) {
                        SpinedBuffer.OfDouble ofDouble = new SpinedBuffer.OfDouble();
                        this.buffer = ofDouble;
                        ofDouble.accept(this.first);
                        this.count++;
                    }
                    this.buffer.accept(d);
                    return;
                }
                throw new IllegalStateException();
            }
        }

        @Override // java9.util.stream.DoubleStream.Builder
        public DoubleStream.Builder add(double d) {
            accept(d);
            return this;
        }

        @Override // java9.util.stream.DoubleStream.Builder
        public DoubleStream build() {
            int i = this.count;
            if (i >= 0) {
                this.count = (-this.count) - 1;
                return i < 2 ? StreamSupport.doubleStream(this, false) : StreamSupport.doubleStream(this.buffer.spliterator(), false);
            }
            throw new IllegalStateException();
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public boolean tryAdvance(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            if (this.count != -2) {
                return false;
            }
            doubleConsumer.accept(this.first);
            this.count = -1;
            return true;
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super Double> consumer) {
            return Spliterators.OfDouble.tryAdvance(this, consumer);
        }

        @Override // java9.util.Spliterator.OfPrimitive
        public void forEachRemaining(DoubleConsumer doubleConsumer) {
            Objects.requireNonNull(doubleConsumer);
            if (this.count == -2) {
                doubleConsumer.accept(this.first);
                this.count = -1;
            }
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super Double> consumer) {
            Spliterators.OfDouble.forEachRemaining(this, consumer);
        }
    }

    static abstract class ConcatSpliterator<T, T_SPLITR extends Spliterator<T>> implements Spliterator<T> {
        protected final T_SPLITR aSpliterator;
        protected final T_SPLITR bSpliterator;
        boolean beforeSplit = true;
        final boolean unsized;

        public ConcatSpliterator(T_SPLITR t_splitr, T_SPLITR t_splitr2) {
            this.aSpliterator = t_splitr;
            this.bSpliterator = t_splitr2;
            this.unsized = t_splitr.estimateSize() + t_splitr2.estimateSize() < 0;
        }

        @Override // java9.util.Spliterator
        public T_SPLITR trySplit() {
            T_SPLITR t_splitr = this.beforeSplit ? this.aSpliterator : (T_SPLITR) this.bSpliterator.trySplit();
            this.beforeSplit = false;
            return t_splitr;
        }

        @Override // java9.util.Spliterator
        public boolean tryAdvance(Consumer<? super T> consumer) {
            if (this.beforeSplit) {
                boolean zTryAdvance = this.aSpliterator.tryAdvance(consumer);
                if (zTryAdvance) {
                    return zTryAdvance;
                }
                this.beforeSplit = false;
                return this.bSpliterator.tryAdvance(consumer);
            }
            return this.bSpliterator.tryAdvance(consumer);
        }

        @Override // java9.util.Spliterator
        public void forEachRemaining(Consumer<? super T> consumer) {
            if (this.beforeSplit) {
                this.aSpliterator.forEachRemaining(consumer);
            }
            this.bSpliterator.forEachRemaining(consumer);
        }

        @Override // java9.util.Spliterator
        public long estimateSize() {
            if (this.beforeSplit) {
                long jEstimateSize = this.aSpliterator.estimateSize() + this.bSpliterator.estimateSize();
                return jEstimateSize >= 0 ? jEstimateSize : LongCompanionObject.MAX_VALUE;
            }
            return this.bSpliterator.estimateSize();
        }

        @Override // java9.util.Spliterator
        public int characteristics() {
            if (this.beforeSplit) {
                return this.aSpliterator.characteristics() & this.bSpliterator.characteristics() & (~((this.unsized ? 16448 : 0) | 5));
            }
            return this.bSpliterator.characteristics();
        }

        @Override // java9.util.Spliterator
        public Comparator<? super T> getComparator() {
            if (this.beforeSplit) {
                throw new IllegalStateException();
            }
            return this.bSpliterator.getComparator();
        }

        static class OfRef<T> extends ConcatSpliterator<T, Spliterator<T>> {
            OfRef(Spliterator<T> spliterator, Spliterator<T> spliterator2) {
                super(spliterator, spliterator2);
            }

            @Override // java9.util.Spliterator
            public long getExactSizeIfKnown() {
                return Spliterators.getExactSizeIfKnown(this);
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }
        }

        private static abstract class OfPrimitive<T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>> extends ConcatSpliterator<T, T_SPLITR> implements Spliterator.OfPrimitive<T, T_CONS, T_SPLITR> {
            @Override // java9.util.stream.Streams.ConcatSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfPrimitive trySplit() {
                return (Spliterator.OfPrimitive) super.trySplit();
            }

            private OfPrimitive(T_SPLITR t_splitr, T_SPLITR t_splitr2) {
                super(t_splitr, t_splitr2);
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(T_CONS t_cons) {
                if (this.beforeSplit) {
                    boolean zTryAdvance = ((Spliterator.OfPrimitive) this.aSpliterator).tryAdvance(t_cons);
                    if (zTryAdvance) {
                        return zTryAdvance;
                    }
                    this.beforeSplit = false;
                    return ((Spliterator.OfPrimitive) this.bSpliterator).tryAdvance(t_cons);
                }
                return ((Spliterator.OfPrimitive) this.bSpliterator).tryAdvance(t_cons);
            }

            @Override // java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(T_CONS t_cons) {
                if (this.beforeSplit) {
                    ((Spliterator.OfPrimitive) this.aSpliterator).forEachRemaining(t_cons);
                }
                ((Spliterator.OfPrimitive) this.bSpliterator).forEachRemaining(t_cons);
            }

            @Override // java9.util.Spliterator
            public long getExactSizeIfKnown() {
                return Spliterators.getExactSizeIfKnown(this);
            }

            @Override // java9.util.Spliterator
            public boolean hasCharacteristics(int i) {
                return Spliterators.hasCharacteristics(this, i);
            }
        }

        static class OfInt extends OfPrimitive<Integer, IntConsumer, Spliterator.OfInt> implements Spliterator.OfInt {
            @Override // java9.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ void forEachRemaining(IntConsumer intConsumer) {
                super.forEachRemaining(intConsumer);
            }

            @Override // java9.util.Spliterator.OfInt
            public /* bridge */ /* synthetic */ boolean tryAdvance(IntConsumer intConsumer) {
                return super.tryAdvance(intConsumer);
            }

            @Override // java9.util.stream.Streams.ConcatSpliterator.OfPrimitive, java9.util.stream.Streams.ConcatSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfInt trySplit() {
                return (Spliterator.OfInt) super.trySplit();
            }

            OfInt(Spliterator.OfInt ofInt, Spliterator.OfInt ofInt2) {
                super(ofInt, ofInt2);
            }
        }

        static class OfLong extends OfPrimitive<Long, LongConsumer, Spliterator.OfLong> implements Spliterator.OfLong {
            @Override // java9.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ void forEachRemaining(LongConsumer longConsumer) {
                super.forEachRemaining(longConsumer);
            }

            @Override // java9.util.Spliterator.OfLong
            public /* bridge */ /* synthetic */ boolean tryAdvance(LongConsumer longConsumer) {
                return super.tryAdvance(longConsumer);
            }

            @Override // java9.util.stream.Streams.ConcatSpliterator.OfPrimitive, java9.util.stream.Streams.ConcatSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfLong trySplit() {
                return (Spliterator.OfLong) super.trySplit();
            }

            OfLong(Spliterator.OfLong ofLong, Spliterator.OfLong ofLong2) {
                super(ofLong, ofLong2);
            }
        }

        static class OfDouble extends OfPrimitive<Double, DoubleConsumer, Spliterator.OfDouble> implements Spliterator.OfDouble {
            @Override // java9.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ void forEachRemaining(DoubleConsumer doubleConsumer) {
                super.forEachRemaining(doubleConsumer);
            }

            @Override // java9.util.Spliterator.OfDouble
            public /* bridge */ /* synthetic */ boolean tryAdvance(DoubleConsumer doubleConsumer) {
                return super.tryAdvance(doubleConsumer);
            }

            @Override // java9.util.stream.Streams.ConcatSpliterator.OfPrimitive, java9.util.stream.Streams.ConcatSpliterator, java9.util.Spliterator
            public /* bridge */ /* synthetic */ Spliterator.OfDouble trySplit() {
                return (Spliterator.OfDouble) super.trySplit();
            }

            OfDouble(Spliterator.OfDouble ofDouble, Spliterator.OfDouble ofDouble2) {
                super(ofDouble, ofDouble2);
            }
        }
    }

    static Runnable composeWithExceptions(final Runnable runnable, final Runnable runnable2) {
        return new Runnable() { // from class: java9.util.stream.Streams.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                    runnable2.run();
                } catch (Throwable th) {
                    try {
                        runnable2.run();
                    } catch (Throwable unused) {
                    }
                    if (th instanceof RuntimeException) {
                        throw ((RuntimeException) th);
                    }
                    if (th instanceof Error) {
                        throw ((Error) th);
                    }
                    throw new IllegalStateException(th);
                }
            }
        };
    }

    static Runnable composedClose(final BaseStream<?, ?> baseStream, final BaseStream<?, ?> baseStream2) {
        return new Runnable() { // from class: java9.util.stream.Streams.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    baseStream.close();
                    baseStream2.close();
                } catch (Throwable th) {
                    try {
                        baseStream2.close();
                    } catch (Throwable unused) {
                    }
                    if (th instanceof RuntimeException) {
                        throw ((RuntimeException) th);
                    }
                    if (th instanceof Error) {
                        throw ((Error) th);
                    }
                    throw new IllegalStateException(th);
                }
            }
        };
    }
}
