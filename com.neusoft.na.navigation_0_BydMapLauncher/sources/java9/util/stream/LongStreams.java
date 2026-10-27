package java9.util.stream;

import java9.lang.Longs;
import java9.util.J8Arrays;
import java9.util.Objects;
import java9.util.Spliterators;
import java9.util.function.LongConsumer;
import java9.util.function.LongPredicate;
import java9.util.function.LongSupplier;
import java9.util.function.LongUnaryOperator;
import java9.util.stream.LongStream;
import java9.util.stream.StreamSpliterators;
import java9.util.stream.Streams;
import java9.util.stream.WhileOps;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public final class LongStreams {
    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfLong] */
    public static LongStream takeWhile(LongStream longStream, LongPredicate longPredicate) {
        Objects.requireNonNull(longStream);
        Objects.requireNonNull(longPredicate);
        return StreamSupport.longStream(new WhileOps.UnorderedWhileSpliterator.OfLong.Taking(longStream.spliterator2(), true, longPredicate), longStream.isParallel()).onClose(StreamSupport.closeHandler(longStream));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfLong] */
    public static LongStream dropWhile(LongStream longStream, LongPredicate longPredicate) {
        Objects.requireNonNull(longStream);
        Objects.requireNonNull(longPredicate);
        return StreamSupport.longStream(new WhileOps.UnorderedWhileSpliterator.OfLong.Dropping(longStream.spliterator2(), true, longPredicate), longStream.isParallel()).onClose(StreamSupport.closeHandler(longStream));
    }

    public static LongStream.Builder builder() {
        return new Streams.LongStreamBuilderImpl();
    }

    public static LongStream empty() {
        return StreamSupport.longStream(Spliterators.emptyLongSpliterator(), false);
    }

    public static LongStream of(long j) {
        return StreamSupport.longStream(new Streams.LongStreamBuilderImpl(j), false);
    }

    public static LongStream of(long... jArr) {
        return J8Arrays.stream(jArr);
    }

    public static LongStream iterate(final long j, final LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longUnaryOperator);
        return StreamSupport.longStream(new Spliterators.AbstractLongSpliterator(LongCompanionObject.MAX_VALUE, 1296) { // from class: java9.util.stream.LongStreams.1
            long prev;
            boolean started;

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(LongConsumer longConsumer) {
                long jApplyAsLong;
                Objects.requireNonNull(longConsumer);
                if (this.started) {
                    jApplyAsLong = longUnaryOperator.applyAsLong(this.prev);
                } else {
                    jApplyAsLong = j;
                    this.started = true;
                }
                this.prev = jApplyAsLong;
                longConsumer.accept(jApplyAsLong);
                return true;
            }
        }, false);
    }

    public static LongStream iterate(final long j, final LongPredicate longPredicate, final LongUnaryOperator longUnaryOperator) {
        Objects.requireNonNull(longUnaryOperator);
        Objects.requireNonNull(longPredicate);
        return StreamSupport.longStream(new Spliterators.AbstractLongSpliterator(LongCompanionObject.MAX_VALUE, 1296) { // from class: java9.util.stream.LongStreams.2
            boolean finished;
            long prev;
            boolean started;

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(LongConsumer longConsumer) {
                long jApplyAsLong;
                Objects.requireNonNull(longConsumer);
                if (this.finished) {
                    return false;
                }
                if (this.started) {
                    jApplyAsLong = longUnaryOperator.applyAsLong(this.prev);
                } else {
                    jApplyAsLong = j;
                    this.started = true;
                }
                if (!longPredicate.test(jApplyAsLong)) {
                    this.finished = true;
                    return false;
                }
                this.prev = jApplyAsLong;
                longConsumer.accept(jApplyAsLong);
                return true;
            }

            @Override // java9.util.Spliterators.AbstractLongSpliterator, java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(LongConsumer longConsumer) {
                Objects.requireNonNull(longConsumer);
                if (this.finished) {
                    return;
                }
                this.finished = true;
                long jApplyAsLong = this.started ? longUnaryOperator.applyAsLong(this.prev) : j;
                while (longPredicate.test(jApplyAsLong)) {
                    longConsumer.accept(jApplyAsLong);
                    jApplyAsLong = longUnaryOperator.applyAsLong(jApplyAsLong);
                }
            }
        }, false);
    }

    public static LongStream generate(LongSupplier longSupplier) {
        Objects.requireNonNull(longSupplier);
        return StreamSupport.longStream(new StreamSpliterators.InfiniteSupplyingSpliterator.OfLong(LongCompanionObject.MAX_VALUE, longSupplier), false);
    }

    public static LongStream range(long j, long j2) {
        if (j >= j2) {
            return empty();
        }
        long j3 = j2 - j;
        if (j3 < 0) {
            long jDivideUnsigned = Longs.divideUnsigned(j3, 2L) + j + 1;
            return concat(range(j, jDivideUnsigned), range(jDivideUnsigned, j2));
        }
        return StreamSupport.longStream(new Streams.RangeLongSpliterator(j, j2, false), false);
    }

    public static LongStream rangeClosed(long j, long j2) {
        if (j > j2) {
            return empty();
        }
        long j3 = j2 - j;
        if (j3 + 1 <= 0) {
            long jDivideUnsigned = Longs.divideUnsigned(j3, 2L) + j + 1;
            return concat(range(j, jDivideUnsigned), rangeClosed(jDivideUnsigned, j2));
        }
        return StreamSupport.longStream(new Streams.RangeLongSpliterator(j, j2, true), false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfLong] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java9.util.Spliterator$OfLong] */
    public static LongStream concat(LongStream longStream, LongStream longStream2) {
        Objects.requireNonNull(longStream);
        Objects.requireNonNull(longStream2);
        return StreamSupport.longStream(new Streams.ConcatSpliterator.OfLong(longStream.spliterator2(), longStream2.spliterator2()), longStream.isParallel() || longStream2.isParallel()).onClose(Streams.composedClose(longStream, longStream2));
    }

    private LongStreams() {
    }
}
