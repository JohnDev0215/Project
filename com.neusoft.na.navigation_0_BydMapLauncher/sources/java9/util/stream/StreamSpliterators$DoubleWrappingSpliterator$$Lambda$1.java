package java9.util.stream;

import java9.util.function.BooleanSupplier;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$DoubleWrappingSpliterator$$Lambda$1 implements BooleanSupplier {
    private final StreamSpliterators.DoubleWrappingSpliterator arg$1;

    private StreamSpliterators$DoubleWrappingSpliterator$$Lambda$1(StreamSpliterators.DoubleWrappingSpliterator doubleWrappingSpliterator) {
        this.arg$1 = doubleWrappingSpliterator;
    }

    public static BooleanSupplier lambdaFactory$(StreamSpliterators.DoubleWrappingSpliterator doubleWrappingSpliterator) {
        return new StreamSpliterators$DoubleWrappingSpliterator$$Lambda$1(doubleWrappingSpliterator);
    }

    @Override // java9.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        StreamSpliterators.DoubleWrappingSpliterator doubleWrappingSpliterator = this.arg$1;
        return doubleWrappingSpliterator.spliterator.tryAdvance(doubleWrappingSpliterator.bufferSink);
    }
}
