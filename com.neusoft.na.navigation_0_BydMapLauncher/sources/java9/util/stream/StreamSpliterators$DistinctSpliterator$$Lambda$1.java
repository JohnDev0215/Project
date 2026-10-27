package java9.util.stream;

import java9.util.function.Consumer;
import java9.util.stream.StreamSpliterators;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class StreamSpliterators$DistinctSpliterator$$Lambda$1 implements Consumer {
    private final StreamSpliterators.DistinctSpliterator arg$1;
    private final Consumer arg$2;

    private StreamSpliterators$DistinctSpliterator$$Lambda$1(StreamSpliterators.DistinctSpliterator distinctSpliterator, Consumer consumer) {
        this.arg$1 = distinctSpliterator;
        this.arg$2 = consumer;
    }

    public static Consumer lambdaFactory$(StreamSpliterators.DistinctSpliterator distinctSpliterator, Consumer consumer) {
        return new StreamSpliterators$DistinctSpliterator$$Lambda$1(distinctSpliterator, consumer);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        StreamSpliterators.DistinctSpliterator.lambda$forEachRemaining$0(this.arg$1, this.arg$2, obj);
    }
}
