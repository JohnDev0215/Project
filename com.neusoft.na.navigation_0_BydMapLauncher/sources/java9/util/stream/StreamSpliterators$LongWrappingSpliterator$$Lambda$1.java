package java9.util.stream;

import java9.util.function.BooleanSupplier;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$LongWrappingSpliterator$$Lambda$1 implements BooleanSupplier {
    private final StreamSpliterators.LongWrappingSpliterator arg$1;

    private StreamSpliterators$LongWrappingSpliterator$$Lambda$1(StreamSpliterators.LongWrappingSpliterator longWrappingSpliterator) {
        this.arg$1 = longWrappingSpliterator;
    }

    public static BooleanSupplier lambdaFactory$(StreamSpliterators.LongWrappingSpliterator longWrappingSpliterator) {
        return new StreamSpliterators$LongWrappingSpliterator$$Lambda$1(longWrappingSpliterator);
    }

    @Override // java9.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        StreamSpliterators.LongWrappingSpliterator longWrappingSpliterator = this.arg$1;
        return longWrappingSpliterator.spliterator.tryAdvance(longWrappingSpliterator.bufferSink);
    }
}
