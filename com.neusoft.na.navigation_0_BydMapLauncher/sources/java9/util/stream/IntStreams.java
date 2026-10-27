package java9.util.stream;

import java9.util.J8Arrays;
import java9.util.Objects;
import java9.util.Spliterators;
import java9.util.function.IntConsumer;
import java9.util.function.IntPredicate;
import java9.util.function.IntSupplier;
import java9.util.function.IntUnaryOperator;
import java9.util.stream.IntStream;
import java9.util.stream.StreamSpliterators;
import java9.util.stream.Streams;
import java9.util.stream.WhileOps;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public final class IntStreams {
    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfInt] */
    public static IntStream takeWhile(IntStream intStream, IntPredicate intPredicate) {
        Objects.requireNonNull(intStream);
        Objects.requireNonNull(intPredicate);
        return StreamSupport.intStream(new WhileOps.UnorderedWhileSpliterator.OfInt.Taking(intStream.spliterator2(), true, intPredicate), intStream.isParallel()).onClose(StreamSupport.closeHandler(intStream));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfInt] */
    public static IntStream dropWhile(IntStream intStream, IntPredicate intPredicate) {
        Objects.requireNonNull(intStream);
        Objects.requireNonNull(intPredicate);
        return StreamSupport.intStream(new WhileOps.UnorderedWhileSpliterator.OfInt.Dropping(intStream.spliterator2(), true, intPredicate), intStream.isParallel()).onClose(StreamSupport.closeHandler(intStream));
    }

    public static IntStream.Builder builder() {
        return new Streams.IntStreamBuilderImpl();
    }

    public static IntStream empty() {
        return StreamSupport.intStream(Spliterators.emptyIntSpliterator(), false);
    }

    public static IntStream of(int i) {
        return StreamSupport.intStream(new Streams.IntStreamBuilderImpl(i), false);
    }

    public static IntStream of(int... iArr) {
        return J8Arrays.stream(iArr);
    }

    public static IntStream iterate(final int i, final IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        return StreamSupport.intStream(new Spliterators.AbstractIntSpliterator(LongCompanionObject.MAX_VALUE, 1296) { // from class: java9.util.stream.IntStreams.1
            int prev;
            boolean started;

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(IntConsumer intConsumer) {
                int iApplyAsInt;
                Objects.requireNonNull(intConsumer);
                if (this.started) {
                    iApplyAsInt = intUnaryOperator.applyAsInt(this.prev);
                } else {
                    iApplyAsInt = i;
                    this.started = true;
                }
                this.prev = iApplyAsInt;
                intConsumer.accept(iApplyAsInt);
                return true;
            }
        }, false);
    }

    public static IntStream iterate(final int i, final IntPredicate intPredicate, final IntUnaryOperator intUnaryOperator) {
        Objects.requireNonNull(intUnaryOperator);
        Objects.requireNonNull(intPredicate);
        return StreamSupport.intStream(new Spliterators.AbstractIntSpliterator(LongCompanionObject.MAX_VALUE, 1296) { // from class: java9.util.stream.IntStreams.2
            boolean finished;
            int prev;
            boolean started;

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(IntConsumer intConsumer) {
                int iApplyAsInt;
                Objects.requireNonNull(intConsumer);
                if (this.finished) {
                    return false;
                }
                if (this.started) {
                    iApplyAsInt = intUnaryOperator.applyAsInt(this.prev);
                } else {
                    iApplyAsInt = i;
                    this.started = true;
                }
                if (!intPredicate.test(iApplyAsInt)) {
                    this.finished = true;
                    return false;
                }
                this.prev = iApplyAsInt;
                intConsumer.accept(iApplyAsInt);
                return true;
            }

            @Override // java9.util.Spliterators.AbstractIntSpliterator, java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(IntConsumer intConsumer) {
                Objects.requireNonNull(intConsumer);
                if (this.finished) {
                    return;
                }
                this.finished = true;
                int iApplyAsInt = this.started ? intUnaryOperator.applyAsInt(this.prev) : i;
                while (intPredicate.test(iApplyAsInt)) {
                    intConsumer.accept(iApplyAsInt);
                    iApplyAsInt = intUnaryOperator.applyAsInt(iApplyAsInt);
                }
            }
        }, false);
    }

    public static IntStream generate(IntSupplier intSupplier) {
        Objects.requireNonNull(intSupplier);
        return StreamSupport.intStream(new StreamSpliterators.InfiniteSupplyingSpliterator.OfInt(LongCompanionObject.MAX_VALUE, intSupplier), false);
    }

    public static IntStream range(int i, int i2) {
        if (i >= i2) {
            return empty();
        }
        return StreamSupport.intStream(new Streams.RangeIntSpliterator(i, i2, false), false);
    }

    public static IntStream rangeClosed(int i, int i2) {
        if (i > i2) {
            return empty();
        }
        return StreamSupport.intStream(new Streams.RangeIntSpliterator(i, i2, true), false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfInt] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java9.util.Spliterator$OfInt] */
    public static IntStream concat(IntStream intStream, IntStream intStream2) {
        Objects.requireNonNull(intStream);
        Objects.requireNonNull(intStream2);
        return StreamSupport.intStream(new Streams.ConcatSpliterator.OfInt(intStream.spliterator2(), intStream2.spliterator2()), intStream.isParallel() || intStream2.isParallel()).onClose(Streams.composedClose(intStream, intStream2));
    }

    private IntStreams() {
    }
}
