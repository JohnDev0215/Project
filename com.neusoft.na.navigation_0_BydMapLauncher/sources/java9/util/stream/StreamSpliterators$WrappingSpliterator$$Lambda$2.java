package java9.util.stream;

import java9.util.function.BooleanSupplier;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$WrappingSpliterator$$Lambda$2 implements BooleanSupplier {
    private final StreamSpliterators.WrappingSpliterator arg$1;

    private StreamSpliterators$WrappingSpliterator$$Lambda$2(StreamSpliterators.WrappingSpliterator wrappingSpliterator) {
        this.arg$1 = wrappingSpliterator;
    }

    public static BooleanSupplier lambdaFactory$(StreamSpliterators.WrappingSpliterator wrappingSpliterator) {
        return new StreamSpliterators$WrappingSpliterator$$Lambda$2(wrappingSpliterator);
    }

    @Override // java9.util.function.BooleanSupplier
    public boolean getAsBoolean() {
        return StreamSpliterators.WrappingSpliterator.lambda$initPartialTraversalState$0(this.arg$1);
    }
}
