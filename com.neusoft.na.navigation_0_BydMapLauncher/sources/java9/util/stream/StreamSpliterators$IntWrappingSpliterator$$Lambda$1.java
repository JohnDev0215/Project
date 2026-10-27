package java9.util.stream;

import java9.util.function.BooleanSupplier;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$IntWrappingSpliterator$$Lambda$1 implements BooleanSupplier {
    private final StreamSpliterators.IntWrappingSpliterator arg$1;

    private StreamSpliterators$IntWrappingSpliterator$$Lambda$1(StreamSpliterators.IntWrappingSpliterator intWrappingSpliterator) {
        this.arg$1 = intWrappingSpliterator;
    }

    public static BooleanSupplier lambdaFactory$(StreamSpliterators.IntWrappingSpliterator intWrappingSpliterator) {
        return new StreamSpliterators$IntWrappingSpliterator$$Lambda$1(intWrappingSpliterator);
    }

    @Override // java9.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        StreamSpliterators.IntWrappingSpliterator intWrappingSpliterator = this.arg$1;
        return intWrappingSpliterator.spliterator.tryAdvance(intWrappingSpliterator.bufferSink);
    }
}
