package java9.util.stream;

import java9.util.J8Arrays;
import java9.util.Objects;
import java9.util.Spliterators;
import java9.util.function.DoubleConsumer;
import java9.util.function.DoublePredicate;
import java9.util.function.DoubleSupplier;
import java9.util.function.DoubleUnaryOperator;
import java9.util.stream.DoubleStream;
import java9.util.stream.StreamSpliterators;
import java9.util.stream.Streams;
import java9.util.stream.WhileOps;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes3.dex */
public final class DoubleStreams {
    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfDouble] */
    public static DoubleStream takeWhile(DoubleStream doubleStream, DoublePredicate doublePredicate) {
        Objects.requireNonNull(doubleStream);
        Objects.requireNonNull(doublePredicate);
        return StreamSupport.doubleStream(new WhileOps.UnorderedWhileSpliterator.OfDouble.Taking(doubleStream.spliterator2(), true, doublePredicate), doubleStream.isParallel()).onClose(StreamSupport.closeHandler(doubleStream));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfDouble] */
    public static DoubleStream dropWhile(DoubleStream doubleStream, DoublePredicate doublePredicate) {
        Objects.requireNonNull(doubleStream);
        Objects.requireNonNull(doublePredicate);
        return StreamSupport.doubleStream(new WhileOps.UnorderedWhileSpliterator.OfDouble.Dropping(doubleStream.spliterator2(), true, doublePredicate), doubleStream.isParallel()).onClose(StreamSupport.closeHandler(doubleStream));
    }

    public static DoubleStream.Builder builder() {
        return new Streams.DoubleStreamBuilderImpl();
    }

    public static DoubleStream empty() {
        return StreamSupport.doubleStream(Spliterators.emptyDoubleSpliterator(), false);
    }

    public static DoubleStream of(double d) {
        return StreamSupport.doubleStream(new Streams.DoubleStreamBuilderImpl(d), false);
    }

    public static DoubleStream of(double... dArr) {
        return J8Arrays.stream(dArr);
    }

    public static DoubleStream iterate(final double d, final DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doubleUnaryOperator);
        return StreamSupport.doubleStream(new Spliterators.AbstractDoubleSpliterator(LongCompanionObject.MAX_VALUE, 1296) { // from class: java9.util.stream.DoubleStreams.1
            double prev;
            boolean started;

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(DoubleConsumer doubleConsumer) {
                double dApplyAsDouble;
                Objects.requireNonNull(doubleConsumer);
                if (this.started) {
                    dApplyAsDouble = doubleUnaryOperator.applyAsDouble(this.prev);
                } else {
                    dApplyAsDouble = d;
                    this.started = true;
                }
                this.prev = dApplyAsDouble;
                doubleConsumer.accept(dApplyAsDouble);
                return true;
            }
        }, false);
    }

    public static DoubleStream iterate(final double d, final DoublePredicate doublePredicate, final DoubleUnaryOperator doubleUnaryOperator) {
        Objects.requireNonNull(doubleUnaryOperator);
        Objects.requireNonNull(doublePredicate);
        return StreamSupport.doubleStream(new Spliterators.AbstractDoubleSpliterator(LongCompanionObject.MAX_VALUE, 1296) { // from class: java9.util.stream.DoubleStreams.2
            boolean finished;
            double prev;
            boolean started;

            @Override // java9.util.Spliterator.OfPrimitive
            public boolean tryAdvance(DoubleConsumer doubleConsumer) {
                double dApplyAsDouble;
                Objects.requireNonNull(doubleConsumer);
                if (this.finished) {
                    return false;
                }
                if (this.started) {
                    dApplyAsDouble = doubleUnaryOperator.applyAsDouble(this.prev);
                } else {
                    dApplyAsDouble = d;
                    this.started = true;
                }
                if (!doublePredicate.test(dApplyAsDouble)) {
                    this.finished = true;
                    return false;
                }
                this.prev = dApplyAsDouble;
                doubleConsumer.accept(dApplyAsDouble);
                return true;
            }

            @Override // java9.util.Spliterators.AbstractDoubleSpliterator, java9.util.Spliterator.OfPrimitive
            public void forEachRemaining(DoubleConsumer doubleConsumer) {
                Objects.requireNonNull(doubleConsumer);
                if (this.finished) {
                    return;
                }
                this.finished = true;
                double dApplyAsDouble = this.started ? doubleUnaryOperator.applyAsDouble(this.prev) : d;
                while (doublePredicate.test(dApplyAsDouble)) {
                    doubleConsumer.accept(dApplyAsDouble);
                    dApplyAsDouble = doubleUnaryOperator.applyAsDouble(dApplyAsDouble);
                }
            }
        }, false);
    }

    public static DoubleStream generate(DoubleSupplier doubleSupplier) {
        Objects.requireNonNull(doubleSupplier);
        return StreamSupport.doubleStream(new StreamSpliterators.InfiniteSupplyingSpliterator.OfDouble(LongCompanionObject.MAX_VALUE, doubleSupplier), false);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java9.util.Spliterator$OfDouble] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java9.util.Spliterator$OfDouble] */
    public static DoubleStream concat(DoubleStream doubleStream, DoubleStream doubleStream2) {
        Objects.requireNonNull(doubleStream);
        Objects.requireNonNull(doubleStream2);
        return StreamSupport.doubleStream(new Streams.ConcatSpliterator.OfDouble(doubleStream.spliterator2(), doubleStream2.spliterator2()), doubleStream.isParallel() || doubleStream2.isParallel()).onClose(Streams.composedClose(doubleStream, doubleStream2));
    }

    private DoubleStreams() {
    }
}
