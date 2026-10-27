package java9.util.stream;

import java.util.Collection;
import java9.util.Objects;
import java9.util.Spliterator;
import java9.util.Spliterators;
import java9.util.function.Supplier;
import java9.util.stream.DoublePipeline;
import java9.util.stream.IntPipeline;
import java9.util.stream.LongPipeline;
import java9.util.stream.ReferencePipeline;

/* JADX INFO: loaded from: classes3.dex */
public final class StreamSupport {
    private StreamSupport() {
    }

    public static <T> Stream<T> stream(Collection<? extends T> collection) {
        return stream(Spliterators.spliterator(collection), false);
    }

    public static <T> Stream<T> parallelStream(Collection<? extends T> collection) {
        return stream(Spliterators.spliterator(collection), true);
    }

    public static <T> Stream<T> stream(Collection<? extends T> collection, int i) {
        return stream((Collection) collection, i, false);
    }

    public static <T> Stream<T> stream(Collection<? extends T> collection, int i, boolean z) {
        Objects.requireNonNull(collection);
        return stream(Spliterators.spliterator(collection, i), z);
    }

    public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean z) {
        Objects.requireNonNull(spliterator);
        return new ReferencePipeline.Head((Spliterator<?>) spliterator, StreamOpFlag.fromCharacteristics((Spliterator<?>) spliterator), z);
    }

    public static <T> Stream<T> stream(Supplier<? extends Spliterator<T>> supplier, int i, boolean z) {
        Objects.requireNonNull(supplier);
        return new ReferencePipeline.Head((Supplier<? extends Spliterator<?>>) supplier, StreamOpFlag.fromCharacteristics(i), z);
    }

    public static IntStream intStream(Spliterator.OfInt ofInt, boolean z) {
        return new IntPipeline.Head(ofInt, StreamOpFlag.fromCharacteristics(ofInt), z);
    }

    public static IntStream intStream(Supplier<? extends Spliterator.OfInt> supplier, int i, boolean z) {
        return new IntPipeline.Head(supplier, StreamOpFlag.fromCharacteristics(i), z);
    }

    public static LongStream longStream(Spliterator.OfLong ofLong, boolean z) {
        return new LongPipeline.Head(ofLong, StreamOpFlag.fromCharacteristics(ofLong), z);
    }

    public static LongStream longStream(Supplier<? extends Spliterator.OfLong> supplier, int i, boolean z) {
        return new LongPipeline.Head(supplier, StreamOpFlag.fromCharacteristics(i), z);
    }

    public static DoubleStream doubleStream(Spliterator.OfDouble ofDouble, boolean z) {
        return new DoublePipeline.Head(ofDouble, StreamOpFlag.fromCharacteristics(ofDouble), z);
    }

    public static DoubleStream doubleStream(Supplier<? extends Spliterator.OfDouble> supplier, int i, boolean z) {
        return new DoublePipeline.Head(supplier, StreamOpFlag.fromCharacteristics(i), z);
    }

    static Runnable closeHandler(BaseStream<?, ?> baseStream) {
        baseStream.getClass();
        return StreamSupport$$Lambda$1.lambdaFactory$(baseStream);
    }
}
